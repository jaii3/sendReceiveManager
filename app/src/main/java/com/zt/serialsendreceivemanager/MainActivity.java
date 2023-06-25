package com.zt.serialsendreceivemanager;

import static java.lang.Thread.sleep;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testjni.ZtSerialPort;


/**
 * @author HP
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    SerialPortManager serialPortManagerS0 = null;
    SerialPortManager serialPortManagerS3 = null;
    SerialPortManager serialPortManagerS4 = null;
    SerialPortManager serialPortManagerS5 = null;

    SerialPortManager serialPortManagerS6 = null;
    SerialPortManager serialPortManagerS7 = null;
    SerialPortManager serialPortManagerS8 = null;
    SerialPortManager serialPortManagerS9 = null;

    ZtSerialPort ztSerialPortS0 = null;
    ZtSerialPort ztSerialPortS3 = null;
    ZtSerialPort ztSerialPortS4 = null;
    ZtSerialPort ztSerialPortS5 = null;

    ZtSerialPort ztSerialPortS6 = null;
    ZtSerialPort ztSerialPortS7 = null;
    ZtSerialPort ztSerialPortS8 = null;
    ZtSerialPort ztSerialPortS9 = null;

    SerialPortDevice deviceS0;
    SerialPortDevice deviceS3;
    SerialPortDevice deviceS4;
    SerialPortDevice deviceS5;

    SerialPortDevice deviceS6;
    SerialPortDevice deviceS7;
    SerialPortDevice deviceS8;
    SerialPortDevice deviceS9;

    Thread sendTread;
    private LinearLayout mLinearLayoutS6;
    private Spinner mSpS6baud;
    private Spinner mSpS6DataBits;
    private Spinner mSpS6Parity;
    private Button mButtonSettingS6;
    private LinearLayout mLinearLayoutS7;
    private Spinner mSpS7baud;
    private Spinner mSpS7DataBits;
    private Spinner mSpS7Parity;
    private Button mButtonSettingS7;
    private LinearLayout mLinearLayoutS8;
    private Spinner mSpS8baud;
    private Spinner mSpS8DataBits;
    private Spinner mSpS8Parity;
    private Button mButtonSettingS8;
    private LinearLayout mLinearLayoutS9;
    private Spinner mSpS9baud;
    private Spinner mSpS9DataBits;
    private Spinner mSpS9Parity;
    private Button mButtonSettingS9;
    private LinearLayout mLinearLayoutS0;
    private Spinner mSpS0baud;
    private Spinner mSpS0DataBits;
    private Spinner mSpS0Parity;
    private Button mButtonSettingS0;
    private LinearLayout mLinearLayoutS3;
    private Spinner mSpS3baud;
    private Spinner mSpS3DataBits;
    private Spinner mSpS3Parity;
    private Button mButtonSettingS3;
    private LinearLayout mLinearLayoutS4;
    private Spinner mSpS4baud;
    private Spinner mSpS4DataBits;
    private Spinner mSpS4Parity;
    private Button mButtonSettingS4;
    private LinearLayout mLinearLayoutS5;
    private Spinner mSpS5baud;
    private Spinner mSpS5DataBits;
    private Spinner mSpS5Parity;
    private Button mButtonSettingS5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        sendTread = new Thread(() -> {
            try {
                while (sendTread != null && !sendTread.isInterrupted()) {
                    try {
                        sleep(3000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    if (serialPortManagerS6 != null) {
                        serialPortManagerS6.sendData("ztSerialPortS6", "S6", new byte[]{0x06, 0x02, 0x03, 0x04, 0x05}, false, 100);
                    }
                    if (serialPortManagerS7 != null) {
                        serialPortManagerS7.sendData("ztSerialPortS7", "S7", new byte[]{0x07, 0x02, 0x03, 0x04, 0x05}, false, 100);
                    }
                    if (serialPortManagerS8 != null) {
                        serialPortManagerS8.sendData("ztSerialPortS8", "S8", new byte[]{0x08, 0x02, 0x03, 0x04, 0x05}, false, 100);
                    }
                    if (serialPortManagerS9 != null) {
                        serialPortManagerS9.sendData("ztSerialPortS9", "S9", new byte[]{0x09, 0x02, 0x03, 0x04, 0x05}, false, 100);
                    }
                    if (serialPortManagerS4 != null) {
                        serialPortManagerS4.sendData("ztSerialPortS4", "S4", new byte[]{0x04, 0x02, 0x03, 0x04, 0x05}, false, 100);
                    }
                    if (serialPortManagerS0 != null) {
                        serialPortManagerS0.sendData("ztSerialPortS0", "S0", new byte[]{0x00, 0x02, 0x03, 0x04, 0x05}, false, 100);
                    }
                    if (serialPortManagerS3 != null) {
                        serialPortManagerS3.sendData("ztSerialPortS3", "S3", new byte[]{
                                0x03, 0x02, 0x03, 0x04, 0x05,
                                0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02,
                                0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03,
                                0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04,
                                0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05,
                                0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02,
                                0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03,
                                0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04,
                                0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05,
                                0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02,
                                0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03,
                                0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04,
                                0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05,
                                0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02,
                                0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03,
                                0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04,
                                0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05,
                                0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02,
                                0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03,
                                0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04,
                                0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05,
                                0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02,
                                0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03,
                                0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04,
                                0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05,
                                0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02,
                                0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03,
                                0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04,
                                0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05,
                                0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02,
                                0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03,
                                0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04,
                                0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05,
                                0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02,
                                0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03,
                                0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04,
                                0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05,
                                0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02,
                                0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03,
                                0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04,
                                0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05,
                                0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02,
                                0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03,
                                0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04,
                                0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05,
                                0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02,
                                0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03,
                                0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x04, 0x05, 0x02, 0x03, 0x33}, false, 100);
                    }
                    if (serialPortManagerS4 != null) {
                        serialPortManagerS4.sendData("ztSerialPortS4", "S4", new byte[]{0x04, 0x02, 0x03, 0x04, 0x05}, false, 100);
                    }
                    if (serialPortManagerS5 != null) {
                        serialPortManagerS5.sendData("ztSerialPortS5", "S5", new byte[]{0x05, 0x02, 0x03, 0x04, 0x05}, false, 100);
                    }
                }
            } catch (Exception e) {
                Log.e("sendTread", e.getMessage());
            }
        });
        sendTread.start();
    }


    private void extracteds0(boolean open, int speed, int dataBits, char parity) {

        if (serialPortManagerS0 != null) {
            serialPortManagerS0.close();
        }
        if (open) {
            ztSerialPortS0 = new ZtSerialPort("/dev/ttyS0", speed, dataBits, 1, parity, 1);

            deviceS0 = new SerialPortDevice();
            deviceS0.path = "/dev/ttyS0";
            deviceS0.speed = speed;
            serialPortManagerS0 = new SerialPortManager(deviceS0, false, 25, new SerialPortManager.OnDataReceiveListener() {

                @Override
                public boolean receiveParsing(String str, String portName, byte[] rs) {
                    if (rs != null) {
                        Log.i("ztSerialPortS0", "len" + rs.length + "  " + str + bytesToHexString(rs, rs.length));

                        serialPortManagerS0.sendData("ztSerialPortS0", deviceS0.path, new byte[]{0x01, 0x12, 0x33, 0x44, 0x05}, false, 25);
                    }
                    return false;
                }

                @Override
                public <T> void sendData(T data) {
                    ztSerialPortS0.sendData(data);
                }

                @Override
                public <T> T receiveData() {
                    return ztSerialPortS0.receiveData();
                }

                @Override
                public void close() {
                    ztSerialPortS0.close();
                }

                @Override
                public boolean isClose() {
                    return ztSerialPortS0.isClose();
                }
            });
        }

    }

    private void extracteds3(boolean open, int speed, int dataBits, char parity) {

        if (serialPortManagerS3 != null) {
            serialPortManagerS3.close();
        }

        if (open) {
            ztSerialPortS3 = new ZtSerialPort("/dev/ttyS3", speed, dataBits, 1, parity, 1);

            deviceS3 = new SerialPortDevice();
            deviceS3.path = "/dev/ttyS3";
            deviceS3.speed = speed;
            serialPortManagerS3 = new SerialPortManager(deviceS3, false, 25, new SerialPortManager.OnDataReceiveListener() {

                @Override
                public boolean receiveParsing(String str, String portName, byte[] rs) {
                    if (rs != null) {
                        Log.i("ztSerialPortS3", "len" + rs.length + "  " + str + bytesToHexString(rs, rs.length));

                        serialPortManagerS3.sendData("ztSerialPortS3", deviceS3.path, new byte[]{0x31, 0x12, 0x33, 0x44, 0x05}, false, 25);
                    }
                    return false;
                }

                @Override
                public <T> void sendData(T data) {
                    ztSerialPortS3.sendData(data);
                }

                @Override
                public <T> T receiveData() {
                    return ztSerialPortS3.receiveData();
                }

                @Override
                public void close() {
                    ztSerialPortS3.close();
                }

                @Override
                public boolean isClose() {
                    return ztSerialPortS3.isClose();
                }
            });
        }
    }

    private void extracteds4(boolean open, int speed, int dataBits, char parity) {

        if (serialPortManagerS4 != null) {
            serialPortManagerS4.close();
        }
        if (open) {
            ztSerialPortS4 = new ZtSerialPort("/dev/ttyS4", speed, dataBits, 1, parity, 1);

            deviceS4 = new SerialPortDevice();
            deviceS4.path = "/dev/ttyS4";
            deviceS4.speed = speed;
            serialPortManagerS4 = new SerialPortManager(deviceS4, false, 25, new SerialPortManager.OnDataReceiveListener() {

                @Override
                public boolean receiveParsing(String str, String portName, byte[] rs) {
                    if (rs != null) {
                        Log.i("ztSerialPortS4", "len" + rs.length + "  " + str + bytesToHexString(rs, rs.length));

                        serialPortManagerS4.sendData("ztSerialPortS4", deviceS4.path, new byte[]{0x11, 0x12, 0x33, 0x44, 0x05}, false, 25);
                    }
                    return false;
                }

                @Override
                public <T> void sendData(T data) {
                    ztSerialPortS4.sendData(data);
                }

                @Override
                public <T> T receiveData() {
                    return ztSerialPortS4.receiveData();
                }

                @Override
                public void close() {
                    ztSerialPortS4.close();
                }

                @Override
                public boolean isClose() {
                    return ztSerialPortS4.isClose();
                }
            });
        }
    }

    private void extracteds5(boolean open, int speed, int dataBits, char parity) {

        if (serialPortManagerS5 != null) {
            serialPortManagerS5.close();
        }
        if (open) {
            ztSerialPortS5 = new ZtSerialPort("/dev/ttyS5", speed, dataBits, 1, parity, 1);

            deviceS5 = new SerialPortDevice();
            deviceS5.path = "/dev/ttyS5";
            deviceS5.speed = speed;
            serialPortManagerS5 = new SerialPortManager(deviceS5, false, 25, new SerialPortManager.OnDataReceiveListener() {

                @Override
                public boolean receiveParsing(String str, String portName, byte[] rs) {
                    if (rs != null) {
                        Log.i("ztSerialPortS5", "len" + rs.length + "  " + str + bytesToHexString(rs, rs.length));

                        serialPortManagerS5.sendData("ztSerialPortS5", "S5", new byte[]{0x51, 0x12, 0x33, 0x44, 0x05}, false, 25);
                    }
                    return false;
                }

                @Override
                public <T> void sendData(T data) {
                    ztSerialPortS5.sendData(data);
                }

                @Override
                public <T> T receiveData() {
                    return ztSerialPortS5.receiveData();
                }

                @Override
                public void close() {
                    ztSerialPortS5.close();
                }

                @Override
                public boolean isClose() {
                    return ztSerialPortS5.isClose();
                }
            });
        }

    }

    private void extracteds6(boolean open, int speed, int dataBits, char parity) {

        if (serialPortManagerS6 != null) {
            serialPortManagerS6.close();
        }
        if (open) {
            ztSerialPortS6 = new ZtSerialPort("/dev/ttyS6", speed, dataBits, 1, parity, 1);

            deviceS6 = new SerialPortDevice();
            deviceS6.path = "/dev/ttyS6";
            deviceS6.speed = speed;
            serialPortManagerS6 = new SerialPortManager(deviceS6, false, 25, new SerialPortManager.OnDataReceiveListener() {

                @Override
                public boolean receiveParsing(String str, String portName, byte[] rs) {
                    if (rs != null) {
                        Log.i("ztSerialPortS6", "len" + rs.length + "  " + str + bytesToHexString(rs, rs.length));

                        serialPortManagerS6.sendData("ztSerialPortS6", deviceS6.path, new byte[]{0x61, 0x12, 0x33, 0x44, 0x05}, false, 25);
                    }
                    return false;
                }

                @Override
                public <T> void sendData(T data) {
                    ztSerialPortS6.sendData(data);
                }

                @Override
                public <T> T receiveData() {
                    return ztSerialPortS6.receiveData();
                }

                @Override
                public void close() {
                    ztSerialPortS6.close();
                }

                @Override
                public boolean isClose() {
                    return ztSerialPortS6.isClose();
                }
            });
        }
    }

    private void extracteds7(boolean open, int speed, int dataBits, char parity) {

        if (serialPortManagerS7 != null) {
            serialPortManagerS7.close();
        }
        if (open) {
            ztSerialPortS7 = new ZtSerialPort("/dev/ttyS7", speed, dataBits, 1, parity, 1);

            deviceS7 = new SerialPortDevice();
            deviceS7.path = "/dev/ttyS7";
            deviceS7.speed = speed;
            serialPortManagerS7 = new SerialPortManager(deviceS7, false, 25, new SerialPortManager.OnDataReceiveListener() {

                @Override
                public boolean receiveParsing(String str, String portName, byte[] rs) {
                    if (rs != null) {
                        Log.i("ztSerialPortS7", "len" + rs.length + "  " + str + bytesToHexString(rs, rs.length));

                        serialPortManagerS7.sendData("ztSerialPortS7", deviceS7.path, new byte[]{0x11, 0x12, 0x33, 0x44, 0x05}, false, 25);
                    }
                    return false;
                }

                @Override
                public <T> void sendData(T data) {
                    ztSerialPortS7.sendData(data);
                }

                @Override
                public <T> T receiveData() {
                    return ztSerialPortS7.receiveData();
                }

                @Override
                public void close() {
                    ztSerialPortS7.close();
                }

                @Override
                public boolean isClose() {
                    return ztSerialPortS7.isClose();
                }
            });
        }

    }

    private void extracteds8(boolean open, int speed, int dataBits, char parity) {

        if (serialPortManagerS8 != null) {
            serialPortManagerS8.close();
        }
        if (open) {
            ztSerialPortS8 = new ZtSerialPort("/dev/ttyS8", speed, dataBits, 1, parity, 1);

            deviceS8 = new SerialPortDevice();
            deviceS8.path = "/dev/ttyS8";
            deviceS8.speed = speed;
            serialPortManagerS8 = new SerialPortManager(deviceS8, false, 25, new SerialPortManager.OnDataReceiveListener() {

                @Override
                public boolean receiveParsing(String str, String portName, byte[] rs) {
                    if (rs != null) {
                        Log.i("ztSerialPortS8", "len" + rs.length + "  " + str + bytesToHexString(rs, rs.length));

                        serialPortManagerS8.sendData("ztSerialPortS8", deviceS8.path, new byte[]{(byte) 0x81, 0x12, 0x33, 0x44, 0x05}, false, 25);

                    }
                    return false;
                }

                @Override
                public <T> void sendData(T data) {
                    ztSerialPortS8.sendData(data);
                }

                @Override
                public <T> T receiveData() {
                    return ztSerialPortS8.receiveData();
                }

                @Override
                public void close() {
                    ztSerialPortS8.close();
                }

                @Override
                public boolean isClose() {
                    return ztSerialPortS8.isClose();
                }
            });
        }
    }

    private void extracteds9(boolean open, int speed, int dataBits, char parity) {

        if (serialPortManagerS9 != null) {
            serialPortManagerS9.close();
        }
        if (open) {
            ztSerialPortS9 = new ZtSerialPort("/dev/ttyS9", speed, dataBits, 1, parity, 1);

            deviceS9 = new SerialPortDevice();
            deviceS9.path = "/dev/ttyS9";
            deviceS9.speed = speed;
            serialPortManagerS9 = new SerialPortManager(deviceS9, false, 25, new SerialPortManager.OnDataReceiveListener() {

                @Override
                public boolean receiveParsing(String str, String portName, byte[] rs) {
                    if (rs != null) {
                        Log.i("ztSerialPortS9", "len" + rs.length + "  " + str + bytesToHexString(rs, rs.length));

                        serialPortManagerS9.sendData("ztSerialPortS9", deviceS9.path, new byte[]{(byte) 0x91, 0x12, 0x33, 0x44, 0x05}, false, 25);
                    }
                    return false;
                }

                @Override
                public <T> void sendData(T data) {
                    ztSerialPortS9.sendData(data);
                }

                @Override
                public <T> T receiveData() {
                    return ztSerialPortS9.receiveData();
                }

                @Override
                public void close() {
                    ztSerialPortS9.close();
                }

                @Override
                public boolean isClose() {
                    return ztSerialPortS9.isClose();
                }
            });
        }
    }

    public static String bytesToHexString(byte[] src, int size) {
        String ret = "";
        if (src == null || size <= 0) {
            return null;
        }
        for (int i = 0; i < size; i++) {
            String hex = Integer.toHexString(src[i] & 0xFF);
            if (hex.length() < 2) {
                hex = "0" + hex;
            }
            hex += " ";
            ret += hex;
        }
        return ret.toUpperCase();
    }

    private void initView() {

        mLinearLayoutS6 = findViewById(R.id.linearLayoutS6);
        mSpS6baud = findViewById(R.id.spS6baud);
        mSpS6DataBits = findViewById(R.id.spS6DataBits);
        mSpS6Parity = findViewById(R.id.spS6Parity);
        mButtonSettingS6 = findViewById(R.id.buttonSettingS6);
        mLinearLayoutS7 = findViewById(R.id.linearLayoutS7);
        mSpS7baud = findViewById(R.id.spS7baud);
        mSpS7DataBits = findViewById(R.id.spS7DataBits);
        mSpS7Parity = findViewById(R.id.spS7Parity);
        mButtonSettingS7 = findViewById(R.id.buttonSettingS7);
        mLinearLayoutS8 = findViewById(R.id.linearLayoutS8);
        mSpS8baud = findViewById(R.id.spS8baud);
        mSpS8DataBits = findViewById(R.id.spS8DataBits);
        mSpS8Parity = findViewById(R.id.spS8Parity);
        mButtonSettingS8 = findViewById(R.id.buttonSettingS8);
        mLinearLayoutS9 = findViewById(R.id.linearLayoutS9);
        mSpS9baud = findViewById(R.id.spS9baud);
        mSpS9DataBits = findViewById(R.id.spS9DataBits);
        mSpS9Parity = findViewById(R.id.spS9Parity);
        mButtonSettingS9 = findViewById(R.id.buttonSettingS9);
        mLinearLayoutS0 = findViewById(R.id.linearLayoutS0);
        mSpS0baud = findViewById(R.id.spS0baud);
        mSpS0DataBits = findViewById(R.id.spS0DataBits);
        mSpS0Parity = findViewById(R.id.spS0Parity);
        mButtonSettingS0 = findViewById(R.id.buttonSettingS0);
        mLinearLayoutS3 = findViewById(R.id.linearLayoutS3);
        mSpS3baud = findViewById(R.id.spS3baud);
        mSpS3DataBits = findViewById(R.id.spS3DataBits);
        mSpS3Parity = findViewById(R.id.spS3Parity);
        mButtonSettingS3 = findViewById(R.id.buttonSettingS3);
        mLinearLayoutS4 = findViewById(R.id.linearLayoutS4);
        mSpS4baud = findViewById(R.id.spS4baud);
        mSpS4DataBits = findViewById(R.id.spS4DataBits);
        mSpS4Parity = findViewById(R.id.spS4Parity);
        mButtonSettingS4 = findViewById(R.id.buttonSettingS4);
        mLinearLayoutS5 = findViewById(R.id.linearLayoutS5);
        mSpS5baud = findViewById(R.id.spS5baud);
        mSpS5DataBits = findViewById(R.id.spS5DataBits);
        mSpS5Parity = findViewById(R.id.spS5Parity);
        mButtonSettingS5 = findViewById(R.id.buttonSettingS5);
        mButtonSettingS0.setOnClickListener(this::onClick);
        mButtonSettingS3.setOnClickListener(this::onClick);
        mButtonSettingS4.setOnClickListener(this::onClick);
        mButtonSettingS5.setOnClickListener(this::onClick);
        mButtonSettingS6.setOnClickListener(this::onClick);
        mButtonSettingS7.setOnClickListener(this::onClick);
        mButtonSettingS8.setOnClickListener(this::onClick);
        mButtonSettingS9.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.buttonSettingS0) {
            String sBtn = ((Button) v).getText().toString();
            ((Button) v).setText(sBtn.equals("关") ? "开" : "关");

            String sParity = mSpS0Parity.getSelectedItem().toString();
            char parity = sParity.equals("N") ? 'N' : sParity.equals("O") ? 'O' : 'E';
            extracteds0(!sBtn.equals("开"), Integer.parseInt(mSpS0baud.getSelectedItem().toString()), Integer.parseInt(mSpS0DataBits.getSelectedItem().toString()), parity);
        } else if (id == R.id.buttonSettingS3) {
            String sBtn = ((Button) v).getText().toString();
            ((Button) v).setText(sBtn.equals("关") ? "开" : "关");

            String sParity = mSpS3Parity.getSelectedItem().toString();
            char parity = sParity.equals("N") ? 'N' : sParity.equals("O") ? 'O' : 'E';
            extracteds3(!sBtn.equals("开"), Integer.parseInt(mSpS3baud.getSelectedItem().toString()), Integer.parseInt(mSpS3DataBits.getSelectedItem().toString()), parity);
        } else if (id == R.id.buttonSettingS4) {
            String sBtn = ((Button) v).getText().toString();
            ((Button) v).setText(sBtn.equals("关") ? "开" : "关");

            String sParity = mSpS4Parity.getSelectedItem().toString();
            char parity = sParity.equals("N") ? 'N' : sParity.equals("O") ? 'O' : 'E';
            extracteds4(!sBtn.equals("开"), Integer.parseInt(mSpS4baud.getSelectedItem().toString()), Integer.parseInt(mSpS4DataBits.getSelectedItem().toString()), parity);
        } else if (id == R.id.buttonSettingS5) {
            String sBtn = ((Button) v).getText().toString();
            ((Button) v).setText(sBtn.equals("关") ? "开" : "关");

            String sParity = mSpS5Parity.getSelectedItem().toString();
            char parity = sParity.equals("N") ? 'N' : sParity.equals("O") ? 'O' : 'E';
            extracteds5(!sBtn.equals("开"), Integer.parseInt(mSpS5baud.getSelectedItem().toString()), Integer.parseInt(mSpS5DataBits.getSelectedItem().toString()), parity);
        } else if (id == R.id.buttonSettingS6) {
            String sBtn = ((Button) v).getText().toString();
            ((Button) v).setText(sBtn.equals("关") ? "开" : "关");
            String sParity = mSpS6Parity.getSelectedItem().toString();
            char parity = sParity.equals("N") ? 'N' : sParity.equals("O") ? 'O' : 'E';
            extracteds6(!sBtn.equals("开"), Integer.parseInt(mSpS6baud.getSelectedItem().toString()), Integer.parseInt(mSpS6DataBits.getSelectedItem().toString()), parity);
        } else if (id == R.id.buttonSettingS7) {
            String sBtn = ((Button) v).getText().toString();
            ((Button) v).setText(sBtn.equals("关") ? "开" : "关");

            String sParity = mSpS7Parity.getSelectedItem().toString();
            char parity = sParity.equals("N") ? 'N' : sParity.equals("O") ? 'O' : 'E';
            extracteds7(!sBtn.equals("开"), Integer.parseInt(mSpS7baud.getSelectedItem().toString()), Integer.parseInt(mSpS7DataBits.getSelectedItem().toString()), parity);
        } else if (id == R.id.buttonSettingS8) {
            String sBtn = ((Button) v).getText().toString();
            ((Button) v).setText(sBtn.equals("关") ? "开" : "关");

            String sParity = mSpS8Parity.getSelectedItem().toString();
            char parity = sParity.equals("N") ? 'N' : sParity.equals("O") ? 'O' : 'E';
            extracteds8(!sBtn.equals("开"), Integer.parseInt(mSpS8baud.getSelectedItem().toString()), Integer.parseInt(mSpS8DataBits.getSelectedItem().toString()), parity);
        } else if (id == R.id.buttonSettingS9) {
            String sBtn = ((Button) v).getText().toString();
            ((Button) v).setText(sBtn.equals("关") ? "开" : "关");

            String sParity = mSpS9Parity.getSelectedItem().toString();
            char parity = sParity.equals("N") ? 'N' : sParity.equals("O") ? 'O' : 'E';
            extracteds9(!sBtn.equals("开"), Integer.parseInt(mSpS9baud.getSelectedItem().toString()), Integer.parseInt(mSpS9DataBits.getSelectedItem().toString()), parity);
        }
    }
}