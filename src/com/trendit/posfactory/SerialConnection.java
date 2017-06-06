/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trendit.posfactory;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.RXTXPort;
import gnu.io.UnsupportedCommOperationException;

/**
 *
 * @author PVer
 */
public class SerialConnection {
    public interface SerialConnectionListener {
        void onDataReceive(byte[] data);
    }
    private RXTXPort mSerialPort;
    private OutputStream mOutputStream;
    private boolean mReceiving = true;
    private BufferedInputStream mInputStream;
    private SerialConnectionListener mListener;
    private ReadThread mThread;
    
    public SerialConnection(String port, SerialConnectionListener listener) 
            throws NoSuchPortException, PortInUseException, UnsupportedCommOperationException, IOException{
        System.out.println("port:" + port);
        CommPortIdentifier portId = CommPortIdentifier.getPortIdentifier(port);
        mSerialPort = (RXTXPort)portId.open("SampleSerialPort", 2000);
        mOutputStream = mSerialPort.getOutputStream();
        mInputStream = new BufferedInputStream(mSerialPort.getInputStream());
        mListener = listener;
        
        mSerialPort.setSerialPortParams(115200, 
                RXTXPort.DATABITS_8, 
                RXTXPort.STOPBITS_1, 
                RXTXPort.PARITY_NONE);
        mThread = new ReadThread();
        mThread.start();
    }
    
    public void writeSerial(byte[] data) throws IOException {
        System.out.println("write " + data.length + " bytes data");
        mOutputStream.write(data);
    }
    
    public void disconnect() {
       mSerialPort.close();
       mReceiving = false;
    }
    
    private class ReadThread extends Thread {
        private final static int BUFFER_LENGTH = 1024;
        private byte[] buffer;
        public ReadThread() {
            buffer = new byte[BUFFER_LENGTH];
        }
        @Override 
        public void run() {
            while(mReceiving) {
                try {
                    int len = mInputStream.read(buffer);
                    if(len > 0 && mListener != null) {
                        System.out.println("receive " + len + " bytes data");
                        mListener.onDataReceive(Arrays.copyOf(buffer, len));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    disconnect();
                }
            }
        }
    }
}
