package com.zt.serialsendreceivemanager;

import static java.lang.System.arraycopy;
import static java.lang.Thread.sleep;

import android.util.Log;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author HP
 */
public class SerialPortManager {

    private final String TAG = "SerialPortManager";

    private final AtomicBoolean mThreadStatusRunning = new AtomicBoolean(false);
    private SerialPortDevice device;
    /**
     * 串口接收发送关闭 方法
     */
    private OnDataReceiveListener onDataReceiveListener;
    /**
     * 串口异步接收线程
     */
    public Thread asynchronousReceivingThread = null;


    /**
     * 设置串口发送接收关闭 方法
     *
     * @param dataReceiveListener 监听事件
     */
    public void setOnDataReceiveListener(OnDataReceiveListener dataReceiveListener) {
        this.onDataReceiveListener = dataReceiveListener;
    }

    public interface OnDataReceiveListener {
        /**
         * 接收解析处理
         *
         * @param str      描述
         * @param portName 串口名称
         * @param rs       数据
         * @return
         */
        boolean receiveParsing(String str, String portName, byte[] rs);


        /**
         * 发送串口数据
         *
         * @param data 数据buffer
         */
        <T> void sendData(T data);

        /**
         * 接收串口数据
         *
         * @param <T>
         * @return
         */
        <T> T receiveData();

        /**
         * 关闭串口
         */
        void close();

        /**
         * 串口是否关闭
         *
         * @return true 串口关闭 false:串口开
         */
        boolean isClose();

    }

    private ExecutorService getExecutor() {
        return executor;
    }

    private void setExecutor(ExecutorService executor) {
        this.executor = executor;
    }

    private ExecutorService executor;

    /**
     * 获取串口是否是同步收发
     *
     * @return true: 串口是同步发送接收
     */
    public boolean isSynchronous() {
        return isSynchronous;
    }

    private void setSynchronous(boolean synchronous) {
        isSynchronous = synchronous;
    }

    private boolean isSynchronous;

    private boolean isSendSynchronousCmd() {
        return isSendSynchronousCmd;
    }

    private void setSendSynchronousCmd(boolean sendSynchronousCmd) {
        isSendSynchronousCmd = sendSynchronousCmd;
    }

    private boolean isSendSynchronousCmd = false;


    /**
     * @param device                设备类
     * @param isSupportSyn          1：同步发送接收， 0：异步发送接收
     * @param sleepTime             isSupportSyn = 0 异步： 接收数据间隔判断时间 （典型值25）
     * @param onDataReceiveListener 收发数据执行接口
     */
    public SerialPortManager(SerialPortDevice device, boolean isSupportSyn, int sleepTime, OnDataReceiveListener onDataReceiveListener) {
        this.device = device;
        setOnDataReceiveListener(onDataReceiveListener);
        setExecutor(Executors.newSingleThreadExecutor());
        setSynchronous(isSupportSyn);
        if (!isSupportSyn) {
            // 异步线程，只有当串口关闭的时候回退出线程；
            if (asynchronousReceivingThread == null) {
                mThreadStatusRunning.set(true);
                asynchronousReceivingThread = new ReadThread(device.path, sleepTime);
                asynchronousReceivingThread.start();
            }
        }

    }
    /*以下数据收发处理*********************************************************************************************/

    byte[] reData = new byte[2000];

    /**
     * 发送不带接收返回
     */
    private class SendThread implements Runnable {
        private final Object object;
        private int sleep;

        SendThread(String commandDescribe, Object object, int sleep) {
            this.object = object;
            this.sleep = sleep;
        }

        @Override
        public void run() {
            int iSendDelay = 10;
            try {
                if (onDataReceiveListener != null) {
                    onDataReceiveListener.sendData(object);
                }
                try {
                    // 计算数据发送时间
                    iSendDelay = (int) ((((byte[]) object).length * 10L) / device.speed * 1000L);
                    iSendDelay += 10;
                    if (sleep < iSendDelay) {
                        sleep = iSendDelay;
                    }
                    sleep(sleep);
                } catch (InterruptedException e) {
                    Log.e("except", e.getMessage());
                }
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }

    /**
     * 获取串口数据
     *
     * @return 接收到数据
     */
    private byte[] getPortData(int sleep) {
        byte[] byRs = new byte[0];
        int len = 0;
        int timeOut = 0;
        byte[] rs = new byte[0];
        int iSpaceTimes = 0;
        while (onDataReceiveListener != null && !onDataReceiveListener.isClose()) {
            if (onDataReceiveListener != null) {
                byRs = onDataReceiveListener.receiveData();
            }
            if (byRs != null) {
                if (byRs.length > 0) {
                    if (byRs.length < reData.length && byRs.length < (reData.length - len)) {
                        arraycopy(byRs, 0, reData, len, byRs.length);
                        len += byRs.length;
                    }
                }
                iSpaceTimes = 0;
            } else {
                iSpaceTimes++;
                if (len > 0 && iSpaceTimes >= 3) {
                    rs = new byte[len];
                    arraycopy(reData, 0, rs, 0, len);
                    break;
                }
            }
            try {
                sleep(12);
                if ((++timeOut * 12) > sleep) {
                    rs = null;
                    break;
                }
            } catch (InterruptedException e) {
                Log.e(TAG, e.getMessage());
            }
        }
        return rs;
    }

    /**
     * 发送后再一定时间内等待数据返回
     */
    private class SendThreadCallable implements Callable {

        private final Object object;
        private final int outTime;
        String portName;

        SendThreadCallable(String portName, int outTime, Object object) {
            this.outTime = outTime;
            this.object = object;
            this.portName = portName;
        }

        @Override
        public Object call() {
            Object rsObject = null;
            long lSendDelay = 10;
            try {
                setSendSynchronousCmd(true);
                // 清一下缓存
                if (onDataReceiveListener != null) {
                    onDataReceiveListener.receiveData();
                }
                try {
                    sleep(2);
                } catch (InterruptedException e) {
                    Log.e(TAG, e.getMessage());
                }
                //发送数据
                if (onDataReceiveListener != null) {
                    onDataReceiveListener.sendData(object);
                }
                // 计算数据发送时间
                lSendDelay = ((((byte[]) object).length * 10L) / device.speed * 1000L);
                lSendDelay += 10;
                try {
                    sleep(lSendDelay);
                } catch (InterruptedException e) {
                    Log.e(TAG, e.getMessage());
                }
                // 返回接收数据
                rsObject = getPortData(outTime);
                setSendSynchronousCmd(false);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
            return rsObject;
        }
    }


    /**
     * 数据发送
     *
     * @param commandDescribe 发生描述
     * @param portName        串口名称
     * @param sendData        发送数据
     * @param synchronous     true：同步发送/ false：异步发送
     * @param outTime         同步发送:等待数据超时时间 / 异步发送 ：发送后等待时间
     * @param <T>
     * @return 同步发送: 接收到的数据 /  异步发送 ： null
     */
    public <T> T sendData(String commandDescribe, String portName, T sendData, boolean synchronous, int outTime) {
        // 同步发送需要返回接收数据
        ExecutorService service = getExecutor();
        if (service != null) {
            if (synchronous) {
                Future future1 = service.submit(new SendThreadCallable(portName, outTime, sendData));
                try {
                    onDataReceiveListener.receiveParsing(commandDescribe, portName, (byte[]) future1.get());
                    return (T) future1.get();
                } catch (ExecutionException | InterruptedException e) {
                    Log.e(TAG, e.getMessage());
                }
            } else {
                service.execute(new SendThread(commandDescribe, sendData, outTime));
            }
        }
        return null;
    }


    /**
     * 关闭串口发送接收
     */
    public void close() {
        try {
            if (getExecutor() != null) {
                getExecutor().shutdownNow();
            }
            setExecutor(null);
            if (null != asynchronousReceivingThread) {
                mThreadStatusRunning.set(false);
                asynchronousReceivingThread.interrupt();
                asynchronousReceivingThread = null;
            }
            if (null != onDataReceiveListener) {
                onDataReceiveListener.close();
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

    }

    private class ReadThread extends Thread {
        private final int spaceReadTime;                  // 串口读取间隔时间
        String portName;

        private ReadThread(String portName, int spaceReadTime) {
            this.spaceReadTime = spaceReadTime;
            this.portName = portName;
        }

        public void run() {
            super.run();
            byte[] rs = new byte[0];

            int len = 0;
            int iSpaceTimes = 0;

            try {
                while (onDataReceiveListener != null && !onDataReceiveListener.isClose() && mThreadStatusRunning.get()) {
                    // 串口关退出
                    // 如果要发送同步命令则需要暂停异步接收命令接收数据
                    while (onDataReceiveListener != null && isSendSynchronousCmd() && !onDataReceiveListener.isClose()) {
                        try {
                            Thread.sleep(5);
                        } catch (InterruptedException e) {
                            Log.e(TAG, e.getMessage());
                            break;
                        }
                    }
                    if (onDataReceiveListener != null && !onDataReceiveListener.isClose()) {
                        rs = onDataReceiveListener.receiveData();
                        if (rs != null) {
                            if (rs.length > 0) {
                                if (rs.length < reData.length && rs.length < (reData.length - len)) {
                                    arraycopy(rs, 0, reData, len, rs.length);
                                    len += rs.length;
                                }
                            }
                            iSpaceTimes = 0;
                        } else {
                            iSpaceTimes++;
                            if (len > 0 && iSpaceTimes >= 3) {
                                byte[] tempData = new byte[len];
                                arraycopy(reData, 0, tempData, 0, len);
                                onDataReceiveListener.receiveParsing("", portName, tempData);
                                len = 0;
                                iSpaceTimes = 0;
                                Arrays.fill(reData, (byte) 0);
                            }
                        }
                    }
                    try {
                        Thread.sleep(spaceReadTime);
                    } catch (InterruptedException e) {
                        Log.e(TAG, e.getMessage());
                        break;
                    }
                }
            } catch (Exception e) {
                Log.i(TAG, e.getMessage());
            }
        }
    }

}
