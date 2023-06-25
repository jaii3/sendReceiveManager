# sendReceiveManager

## 数据收发管理

    将数据收发过程进行封装处理

## 1、引用库

    将 zt_sendReceiveManager-1.0.0.aar 文件拷贝到 libs 目录下。
    在app目录中的build.gradle文件中添加如下依赖：

````
    implementation(name: 'zt_sendReceiveManager-1.0.0', ext: 'aar')
````

## 2、使用接口

### 发送说明

````
    1、初始化管理类
          /**
         * @param device                设备类
         * @param isSupportSyn          1：同步发送接收， 0：异步发送接收
         * @param sleepTime             isSupportSyn = 0 异步： 接收数据间隔判断时间 （典型值25）
         * @param onDataReceiveListener 收发数据执行接口
         */
         public SerialPortManager(SerialPortDevice device, boolean isSupportSyn, int sleepTime, OnDataReceiveListener onDataReceiveListener) ;
        
    
     2、数据发送类
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
        public <T> T sendData(String commandDescribe, String portName, T sendData, boolean synchronous, int outTime) ;
         
     3、关闭类
         /**
         * 关闭串口发送接收
         */
        public void close() ;
````

### 使用示例（串口收发）

````    
        ZtSerialPort ztSerialPortS0 = null;
        SerialPortDevice deviceS0;
        if (serialPortManagerS0 != null) {
            serialPortManagerS0.close();
        }
       
        //定义了一个串口
        ztSerialPortS0 = new ZtSerialPort("/dev/ttyS0", speed, dataBits, 1, parity, 1);

        deviceS0 = new SerialPortDevice();
        deviceS0.path = "/dev/ttyS0";
        deviceS0.speed = 9600;
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



````