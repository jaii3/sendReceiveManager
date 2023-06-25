package com.zt.serialsendreceivemanager;

import static java.lang.System.arraycopy;
import static java.lang.Thread.sleep;

import android.util.Log;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * 项目名称    BaseProject
 * 类描述
 * 创建人      hp
 * 创建时间    2019/11/22
 *
 * @author HP
 */
public abstract class CommunicationSerial {

    private final String TAG = "CommunicationSerial";


    /**
     * @param executor 单线程池对象
     */
    public abstract void setExecutor(ExecutorService executor);

    /**
     * @return 单线程池对象
     */
    public abstract ExecutorService getExecutor();

    /**
     * 若此通信口 接收数据处理是阻塞式的此必须为异步
     * 通信方式
     *
     * @return true:同步，False：异步
     */
    public abstract boolean isSupportSynchronous();

    /**
     * 若此通信口 接收数据处理是阻塞式的此必须为异步
     * 通信方式
     */
    public abstract void setSupportSynchronous(boolean sync);

    /**
     * 数据接收解析
     *
     * @param str  数据描述
     * @param port 通讯口
     * @param rs   数据内容
     */
    public abstract boolean receiveParsing(String str, CommunicationSerial port, byte[] rs);


    /**
     * @param doing 是否在发送同步命令
     */
    public abstract void setIsSendSynchronousCmd(boolean doing);

    /**
     * @return 是否在发送同步命令
     */
    public abstract boolean getIsSendSynchronousCmd();

    /**
     * 异步接收线程
     */
    public Thread asynchronousReceivingThread = null;

    OnDataReceiveListener onDataReceiveListener;

    public void setOnDataReceiveListener(OnDataReceiveListener dataReceiveListener) {
        this.onDataReceiveListener = dataReceiveListener;
    }

    public interface OnDataReceiveListener {
        <T> void sendData(T data);

        <T> T receiveData();
        boolean isEnable();

        boolean receiveParsing(String str,  byte[] rs);

    }




}
