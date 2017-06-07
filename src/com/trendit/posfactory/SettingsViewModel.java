/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trendit.posfactory;

import java.awt.Component;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import static com.trendit.posfactory.Constants.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentEvent.EventType;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;

/**
 * settings view model
 * @author yinlijun
 */
public class SettingsViewModel {
    public interface PropertyListener {
        void onPropertyChanged(String prop, Object obj);
    }
    private static HashMap<String, HashSet<PropertyListener>> mapLisenters = new HashMap<String, HashSet<PropertyListener>>();
    private static HashMap<String, HashSet<Component>> mapComponents = new HashMap<String, HashSet<Component>>();
    public static void registerListener(String prop, PropertyListener listener) {
        if(mapLisenters.containsKey(prop)) {
            HashSet<PropertyListener> set = mapLisenters.get(prop);
            set.add(listener);
            mapLisenters.put(prop, set);
        } else {
            HashSet<PropertyListener> set = new HashSet<PropertyListener>();
            set.add(listener);
            mapLisenters.put(prop, set);
        }
    }
    public static void unregisterListener(String prop, PropertyListener listener) {
        if(mapLisenters.containsKey(prop)) {
            mapLisenters.get(prop).remove(listener);
        }
    }
    
    public static void bindComponent(String prop, Component com) {
        if(mapComponents.containsKey(prop)) {
            HashSet<Component> set = mapComponents.get(prop);
            set.add(com);
            mapComponents.put(prop, set);
        } else {
            HashSet<Component> set = new HashSet<Component>();
            set.add(com);
            mapComponents.put(prop, set);
        }
    }
    
    public static void unBindComponent(String prop, Component com) {
        if(mapComponents.containsKey(prop)) {
            mapComponents.get(prop).remove(com);
        }
    }
            
    public static class ItemListenerWrapper<T> implements ItemListener {
        private String prop;
        private T val;
        public ItemListenerWrapper(String prop, T val) {
            this.prop = prop;
            this.val = val;
        }
        public void itemStateChanged(ItemEvent e) {
            if(e.getStateChange() == ItemEvent.DESELECTED) {
                return;
            }
            
            T item = null;
            Component comp = (Component)e.getSource();
            if(comp instanceof JComboBox) {
                item = (T)e.getItem();
            } else if(comp instanceof JCheckBox) {
                item = (T)e.getItem();
            }
            if(val != item) {
                setProp(prop, item, comp);   
                val = item;
            }
        }
    }
    
    public static class ActionListenerWrapper implements ActionListener {
        private String prop;
        private String val;
        public ActionListenerWrapper(String prop, String val) {
            this.prop = prop;
            this.val = val;
        }
        public void actionPerformed(ActionEvent evt) {
            String s = ((JTextComponent)evt.getSource()).getText();
            System.out.println(s);
            
            if(val == null || !s.equals(val)) {
                setProp(prop, s, (Component)evt.getSource());
            }
         }
    }
    
    public static class KeyAdaperWrapper extends KeyAdapter {
        private String prop;
        private String val;
        public KeyAdaperWrapper(String prop, String val) {
            this.prop = prop;
            this.val = val;
        }
        public void keyReleased(KeyEvent e) {
            JTextArea com = (JTextArea) e.getSource();
            if(!com.getText().equals(val)) {
              setProp(prop, com.getText().getBytes(), com);  
            }
        }
    }
    
    public static void setProp(String prop, Object val, Component orig) {
        HashSet<Component> set = mapComponents.get(prop);
        
        for(Component com : set) {
            if(com instanceof JComboBox && orig != com) {
                ((JComboBox)com).setSelectedItem(val);
            } else if(com instanceof JTextField) {
                ((JTextField)com).setText(new String((byte[])val));
            } else if(com instanceof JTextArea) {
                String str = new String((byte[])val);
                if(!((JTextArea)com).getText().equals(str)) {
                    SwingUtilities.invokeLater(new HighLightRunnable((JTextArea)com, (byte[])val));
                }
            }
        }
    }
    
    private static class HighLightRunnable implements Runnable {
        private JTextArea component;
        private byte[] val;
        public HighLightRunnable(JTextArea component, byte[] val) {
            this.component = component;
            this.val = val;
        }
        @Override
        public void run() {
            component.setText(new String(val));
        }
    };       
    
    public static void appendRecvData(byte[] data) {
        byte[] newData = new byte[recvData.length + data.length];
        setProp(COMM_RECV_DATA, newData, null);
    }

    
    private static int commType;
    public final static ItemListenerWrapper<Integer> mCommTypeListener 
            = new ItemListenerWrapper<Integer>(COMM_TYPE, COMM_TYPE_SERIAL);
    
    private static String serialPort;
    public final static ItemListenerWrapper<String> mCommSerialPortListener 
            = new ItemListenerWrapper<String>(COMM_SERIAL_PORT, null);
    
    private static String ipAddr;
    public final static ItemListenerWrapper<String> mCommIpAddrListener 
            = new ItemListenerWrapper<String>(COMM_TCP_IP, null);
    
    private static int tcpPort;
    public final static ItemListenerWrapper<Integer> mCommTcpPortListener
            =  new ItemListenerWrapper<Integer>(COMM_TCP_PORT, 80);
    
    private static boolean serialDtr;
    public final static ItemListenerWrapper<Boolean> mCommSerialDtrListener
            = new ItemListenerWrapper<Boolean>(COMM_SERIAL_DTR_ENABLE, false);
    
    private static boolean serialRts;
    public final static ItemListenerWrapper<Boolean> mCommSerialRtsListener
            = new ItemListenerWrapper<Boolean>(COMM_SERIAL_RTS_ENABLE, false);
    
    private static int serialBuad;
    public final static ItemListenerWrapper<Integer> mCommSerialBaudListener
            = new ItemListenerWrapper<Integer>(COMM_SERIAL_BAUDRATE, 115200);
    
    private static boolean sendPeriodEnable;
    public final static ItemListenerWrapper<Boolean> mCommSendPeriodListener
            = new ItemListenerWrapper<Boolean>(COMM_SEND_PERIOD_ENABLE, false);
    
    private static int sendPeriodTime;
    public final static ItemListenerWrapper<Integer> mCommSendPeriodTimeListener
            = new ItemListenerWrapper<Integer>(COMM_SEND_PERIOD_TIME, -1);
    
    private static int sendTimout;
    public final static ItemListenerWrapper<Integer> mCommSendTimeoutListener
            = new ItemListenerWrapper<Integer>(COMM_SEND_TIMEOUT, -1);
    
    private static boolean sendDisplayHex;
    public final static ItemListenerWrapper<Boolean> mCommSendDisplayHexListener
        = new ItemListenerWrapper<Boolean>(COMM_SEND_DISPLAY_HEX, false);

    private static boolean showSendTimestamp;
    public final static ItemListenerWrapper<Boolean> mCommShowSendTimestampListener
        = new ItemListenerWrapper<Boolean>(COMM_SHOW_SEND_TIMESTAMP, false);

    private static boolean recvDisplayHex;
    public final static ItemListenerWrapper<Boolean> mCommRecvDisplayHexListener
        = new ItemListenerWrapper<Boolean>(COMM_RECV_DISPAY_HEX, false);

    private static boolean saveRecvToFile;
    public final static ItemListenerWrapper<Boolean> mCommSaveRecvToFileListener
        = new ItemListenerWrapper<Boolean>(COMM_SAVE_RECV_TO_FILE, false);

    private static String sendEccType;
    public final static ItemListenerWrapper<String> mCommSendEccTypeListener 
            = new ItemListenerWrapper<String>(COMM_ADD_SEND_ECC_TYPE, null);
    
    private static int sendEccStartBit;
    public final static ItemListenerWrapper<Integer> mCommSendEccStartBitListener
            = new ItemListenerWrapper<Integer>(COMM_SEND_ECC_START_BIT, -1);

    
    private static boolean sendAppednCrLf;
    public final static ItemListenerWrapper<Boolean> mCommSendAppendCrLfListener
        = new ItemListenerWrapper<Boolean>(COMM_SEND_APPEND_CR_LF, false);
    
    private static byte[] recvData;
    public final static KeyAdaperWrapper mCommRecvDataListener
        = new KeyAdaperWrapper(COMM_RECV_DATA, null);
    
    private static byte[] sendData;
    public final static KeyAdaperWrapper mCommSendDataListener
        = new KeyAdaperWrapper(COMM_SEND_DATA, null);        
}
