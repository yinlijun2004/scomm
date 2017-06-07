/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trendit.posfactory;

import static com.trendit.posfactory.Constants.*;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Enumeration;
import gnu.io.CommPortIdentifier;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashSet;
import javax.swing.AbstractButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.DocumentListener;
import java.awt.event.KeyAdapter;
import javax.swing.text.JTextComponent;

/**
 *
 * @author yinlijun
 */
public class MainJFrame extends javax.swing.JFrame 
        implements SerialConnection.SerialConnectionListener {
    /**
     * Creates new form MainJFrame
     */
    private SerialConnection mConnection;
    public MainJFrame() {
        initComponents();
        initPort();
        initViewModel();
    }
    
    private void bindSingleComponent(String prop, AbstractButton comp, ItemListener listener) {
        SettingsViewModel.bindComponent(prop, comp);
        comp.addItemListener(SettingsViewModel.mCommSerialBaudListener);
    }
    
    private void bindSingleComponent(String prop, JComboBox comp, ItemListener listener) {
        SettingsViewModel.bindComponent(prop, comp);
        comp.addItemListener(SettingsViewModel.mCommSerialBaudListener);
    }

    private void bindSingleComponent(String prop, JTextField comp, ActionListener listener) {
        SettingsViewModel.bindComponent(prop, comp);
        //comp.getDocument().addDocumentListener(listener);
        comp.addActionListener(listener);
    }
    
    private void bindSingleComponent(String prop, JTextArea comp, KeyAdapter listener) {
        SettingsViewModel.bindComponent(prop, comp);
        //comp.getDocument().addDocumentListener(listener);
        comp.addKeyListener(listener);
    }
    
    
    private void initViewModel() {
        bindSingleComponent(COMM_SERIAL_BAUDRATE, baudrateCombox, SettingsViewModel.mCommSerialBaudListener);
        
        bindSingleComponent(COMM_SERIAL_PORT, portSelectCombox, SettingsViewModel.mCommSerialPortListener);
        
        bindSingleComponent(COMM_SERIAL_DTR_ENABLE, dtrCheckBox, SettingsViewModel.mCommSerialDtrListener);
        
        bindSingleComponent(COMM_SERIAL_RTS_ENABLE , rtsCheckBox, SettingsViewModel.mCommSerialDtrListener);
        
        bindSingleComponent(COMM_RECV_DISPAY_HEX, hexRecvBtn, SettingsViewModel.mCommRecvDisplayHexListener);
        
        bindSingleComponent(COMM_RECV_DISPAY_HEX, hexRecvBtn, SettingsViewModel.mCommRecvDisplayHexListener);

        bindSingleComponent(COMM_SERIAL_PORT, portSelectCombox, SettingsViewModel.mCommSerialPortListener);
        
        bindSingleComponent(COMM_RECV_DATA, recvText, SettingsViewModel.mCommRecvDataListener);
        
        bindSingleComponent(COMM_SEND_DATA, sendText, SettingsViewModel.mCommSendDataListener);
    }
    
    private void initPort() {
        Enumeration portIdentifiers = CommPortIdentifier.getPortIdentifiers();
        CommPortIdentifier portId = null;
        
        while (portIdentifiers.hasMoreElements()) {
            CommPortIdentifier pid = (CommPortIdentifier) portIdentifiers.nextElement();
            if(pid.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                portSelectCombox.addItem(pid.getName());    
            }
        }
    }
    
    @Override
    public void onDataReceive(byte[] data) {
        String orig = recvText.getText();
        String str =new String(data);
        recvText.setText(str + orig);
        recvText.setAutoscrolls(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        txtTitle = new javax.swing.JLabel();
        sendPanel = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        sendHexCheckBox = new javax.swing.JCheckBox();
        sendPeriodEnableCheckBox = new javax.swing.JCheckBox();
        sendPeriodTimeText = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        jCheckBox7 = new javax.swing.JCheckBox();
        jCheckBox8 = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jFormattedTextField3 = new javax.swing.JFormattedTextField();
        jComboBox3 = new javax.swing.JComboBox<>();
        sendButton = new javax.swing.JButton();
        clearSendBtn = new javax.swing.JButton();
        sendText = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        sendFilePathText = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        serialPortPropPanel = new javax.swing.JPanel();
        openPortBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        portSelectCombox = new javax.swing.JComboBox<>();
        dtrCheckBox = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        rtsCheckBox = new javax.swing.JCheckBox();
        jSeparator2 = new javax.swing.JSeparator();
        baudrateCombox = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        moreSerialPropBtn = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        receivePanel = new javax.swing.JPanel();
        clearRecvButton = new javax.swing.JButton();
        saveRecvBtn = new javax.swing.JButton();
        hexRecvBtn = new javax.swing.JCheckBox();
        saveRecvToFileBtn = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        recvText = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(300, 300));
        setMinimumSize(new java.awt.Dimension(300, 300));

        txtTitle.setFont(new java.awt.Font("宋体", 1, 24)); // NOI18N
        txtTitle.setText("RS232通讯工具");
        txtTitle.setAlignmentX(0.5F);
        txtTitle.setAutoscrolls(true);
        txtTitle.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtTitle.setFocusable(false);
        txtTitle.setMaximumSize(new java.awt.Dimension(48, 30));
        txtTitle.setMinimumSize(new java.awt.Dimension(48, 30));

        sendHexCheckBox.setText("HEX发送");

        sendPeriodEnableCheckBox.setText("定时发送");

        sendPeriodTimeText.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, sendPeriodEnableCheckBox, org.jdesktop.beansbinding.ELProperty.create("${selected}"), sendPeriodTimeText, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jLabel3.setText("ms/次");

        jCheckBox7.setText("加回车换行");

        jCheckBox8.setText("加时间戳和分包显示,");

        jLabel4.setText("超时时间");

        jLabel5.setText("ms");

        jFormattedTextField2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        jLabel6.setText("第");

        jLabel7.setText("字节至末尾加校验");

        jFormattedTextField3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        sendButton.setBackground(new java.awt.Color(204, 204, 204));
        sendButton.setText("发送");
        sendButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sendButtonMouseClicked(evt);
            }
        });

        clearSendBtn.setText("清发送区");
        clearSendBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clearSendBtnMouseClicked(evt);
            }
        });

        sendText.setColumns(20);
        sendText.setRows(5);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(sendHexCheckBox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sendPeriodEnableCheckBox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(sendPeriodTimeText, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCheckBox8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFormattedTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCheckBox7)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(sendButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(clearSendBtn)
                        .addContainerGap())))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(sendText, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sendHexCheckBox)
                    .addComponent(sendPeriodEnableCheckBox)
                    .addComponent(sendPeriodTimeText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jCheckBox8)
                    .addComponent(jLabel4)
                    .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jFormattedTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sendButton)
                    .addComponent(clearSendBtn))
                .addContainerGap())
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addContainerGap(71, Short.MAX_VALUE)
                    .addComponent(sendText, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(44, Short.MAX_VALUE)))
        );

        sendPanel.addTab("发送", jPanel3);

        jButton1.setText("选择文件");

        jButton2.setText("发送文件");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(sendFilePathText, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(sendFilePathText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addContainerGap(125, Short.MAX_VALUE))
        );

        sendPanel.addTab("发送文件", jPanel2);

        serialPortPropPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("串口设置"));

        openPortBtn.setText("打开串口");
        openPortBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                openPortBtnMouseClicked(evt);
            }
        });

        jLabel1.setText("端口号");

        portSelectCombox.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                portSelectComboxPropertyChange(evt);
            }
        });

        dtrCheckBox.setText("DTR");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        rtsCheckBox.setText("RTS");

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel2.setText("波特率");

        moreSerialPropBtn.setText("更多串口设置");
        moreSerialPropBtn.setToolTipText("");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, portSelectCombox, org.jdesktop.beansbinding.ELProperty.create("${selectedItem}"), jLabel8, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        javax.swing.GroupLayout serialPortPropPanelLayout = new javax.swing.GroupLayout(serialPortPropPanel);
        serialPortPropPanel.setLayout(serialPortPropPanelLayout);
        serialPortPropPanelLayout.setHorizontalGroup(
            serialPortPropPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(serialPortPropPanelLayout.createSequentialGroup()
                .addGroup(serialPortPropPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(serialPortPropPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(portSelectCombox, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dtrCheckBox))
                    .addComponent(openPortBtn))
                .addGap(2, 2, 2)
                .addGroup(serialPortPropPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rtsCheckBox)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(baudrateCombox, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(moreSerialPropBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        serialPortPropPanelLayout.setVerticalGroup(
            serialPortPropPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(serialPortPropPanelLayout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(serialPortPropPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(serialPortPropPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(serialPortPropPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(portSelectCombox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jSeparator1)
                        .addGroup(serialPortPropPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rtsCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dtrCheckBox))
                        .addComponent(jSeparator2))
                    .addGroup(serialPortPropPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(baudrateCombox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(moreSerialPropBtn)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(serialPortPropPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(openPortBtn)
                    .addComponent(jLabel8)))
        );

        receivePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("接收设置"));

        clearRecvButton.setText("清除窗口");
        clearRecvButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clearRecvButtonMouseClicked(evt);
            }
        });

        saveRecvBtn.setText("保存数据");
        saveRecvBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveRecvBtnActionPerformed(evt);
            }
        });

        hexRecvBtn.setText("HEX显示");

        saveRecvToFileBtn.setText("接收数据到文件");

        javax.swing.GroupLayout receivePanelLayout = new javax.swing.GroupLayout(receivePanel);
        receivePanel.setLayout(receivePanelLayout);
        receivePanelLayout.setHorizontalGroup(
            receivePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(receivePanelLayout.createSequentialGroup()
                .addComponent(clearRecvButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(hexRecvBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saveRecvBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(saveRecvToFileBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        receivePanelLayout.setVerticalGroup(
            receivePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(receivePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(clearRecvButton)
                .addComponent(saveRecvBtn)
                .addComponent(hexRecvBtn)
                .addComponent(saveRecvToFileBtn))
        );

        recvText.setColumns(20);
        recvText.setRows(5);
        jScrollPane1.setViewportView(recvText);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(168, 168, 168))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(receivePanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(sendPanel, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(serialPortPropPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(receivePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(serialPortPropPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sendPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveRecvBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveRecvBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_saveRecvBtnActionPerformed

    private void clearSendBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearSendBtnMouseClicked
        sendText.setText("");
    }//GEN-LAST:event_clearSendBtnMouseClicked

    private void clearRecvButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearRecvButtonMouseClicked
        recvText.setText("");
    }//GEN-LAST:event_clearRecvButtonMouseClicked

    private void openPortBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_openPortBtnMouseClicked
        if(mConnection != null) {
            mConnection.disconnect();
        } else {
            try {
                mConnection = new SerialConnection((String)portSelectCombox.getSelectedItem(), MainJFrame.this);
            } catch (Exception ex) {
                ex.printStackTrace();
                return;
            }
        }       
    }//GEN-LAST:event_openPortBtnMouseClicked

    private void sendButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sendButtonMouseClicked
        byte[] data = sendText.getText().getBytes();
        if(data.length > 0) {
            try {
                mConnection.writeSerial(data);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_sendButtonMouseClicked

    private void portSelectComboxPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_portSelectComboxPropertyChange

    }//GEN-LAST:event_portSelectComboxPropertyChange

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        /*
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        */
        try {
            //javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());//当前系统风格

            //javax.swing.UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());//跨平台的Java界面风格，不太明白这种说法

            //javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");//Nimbus风格，新出来的外观，jdk6 update10版本以后的才会出现

            //javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");//Motif风格，外观接近windows经典，但宽宽大大，而且不是黑灰主色，而是蓝黑

            javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");//windows风格

            //javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");//java风格
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MainJFrame frame = new MainJFrame();
                frame.setVisible(true);
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> baudrateCombox;
    private javax.swing.JButton clearRecvButton;
    private javax.swing.JButton clearSendBtn;
    private javax.swing.JCheckBox dtrCheckBox;
    private javax.swing.JCheckBox hexRecvBtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JFormattedTextField jFormattedTextField3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JButton moreSerialPropBtn;
    private javax.swing.JButton openPortBtn;
    private javax.swing.JComboBox<String> portSelectCombox;
    private javax.swing.JPanel receivePanel;
    private javax.swing.JTextArea recvText;
    private javax.swing.JCheckBox rtsCheckBox;
    private javax.swing.JButton saveRecvBtn;
    private javax.swing.JCheckBox saveRecvToFileBtn;
    private javax.swing.JButton sendButton;
    private javax.swing.JTextField sendFilePathText;
    private javax.swing.JCheckBox sendHexCheckBox;
    private javax.swing.JTabbedPane sendPanel;
    private javax.swing.JCheckBox sendPeriodEnableCheckBox;
    private javax.swing.JFormattedTextField sendPeriodTimeText;
    private javax.swing.JTextArea sendText;
    private javax.swing.JPanel serialPortPropPanel;
    private javax.swing.JLabel txtTitle;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
