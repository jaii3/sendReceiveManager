package com.zt.serialsendreceivemanager;

/**
 * @author HP
 */
public class SerialPortDevice {

    public String path;
    public int speed = 9600;
    public int dataBits = 8;
    public int stopBits = 1;
    public char parity = 'N';
    /**
     * 是否支持同步发送
     */
    public boolean isSynchronous;

    public SerialPortDevice() {
    }
}
