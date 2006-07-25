package org.scre.evtgenerator.dialog;

import java.awt.LayoutManager;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.UIManager;

import org.scre.evtgenerator.utils.ServerConfig;

public class SettingsPanel extends JPanel {

    private JPanel jEventPanel = null;
    private JTextField jTextServer = null;
    //private JLabel jLabel = null;
    private JTextField jTextPort = null;
    private JTextField jTextURI = null;
    //private JLabel jLabel1 = null;
    //private JLabel jLabel2 = null;
    //private JLabel jLabel3 = null;
    private JTextField jTextHandler = null;
    //private JLabel jLabel4 = null;
    private JTextField jTextMBase = null;
    private JButton jConnectBtn = null;
    private JPanel jLeAMPanel = null;
    //private JLabel jLabel7 = null;
    //private JLabel jLabel8 = null;
    private JTextField jTextHome = null;
    //private JLabel jLabel5 = null;
    
    private Dimension labelSize = new Dimension(50,16);
    
    public SettingsPanel() {
        super();
        // TODO Auto-generated constructor stub

        initialize();
    }

    public SettingsPanel(boolean arg0) {
        super(arg0);
        // TODO Auto-generated constructor stub

        initialize();
    }

    public SettingsPanel(LayoutManager arg0) {
        super(arg0);
        // TODO Auto-generated constructor stub

        initialize();
    }

    public SettingsPanel(LayoutManager arg0, boolean arg1) {
        super(arg0, arg1);
        // TODO Auto-generated constructor stub

        initialize();
    }

    /**
     * This method initializes this
     * 
     * @return void
     */
    private void initialize() {
        
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
        gridBagConstraints21.gridx = 0;
        gridBagConstraints21.insets = new java.awt.Insets(5,5,5,5);
        gridBagConstraints21.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints21.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints21.weighty = 0.0;
        gridBagConstraints21.gridy = 0;
        GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
        gridBagConstraints14.gridx = 0;
        gridBagConstraints14.insets = new java.awt.Insets(15,5,5,5);
        gridBagConstraints14.gridy = 3;
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5,5,5,5);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.CENTER;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.gridy = 1;
        this.setLayout(new GridBagLayout());
        this.setSize(300, 270);
        //this.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        this.add(getJEventPanel(), gridBagConstraints);
        this.add(getJConnectBtn(), gridBagConstraints14);
        this.add(getJLeAMPanel(), gridBagConstraints21);
    }

    /**
     * This method initializes jPanel	
     * 	
     * @return javax.swing.JPanel	
     */
    private JPanel getJEventPanel() {
        if (jEventPanel == null) {
            GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
            gridBagConstraints10.gridx = 0;
            gridBagConstraints10.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints10.gridwidth = 3;
            gridBagConstraints10.anchor = java.awt.GridBagConstraints.CENTER;
            gridBagConstraints10.gridy = 0;
            JLabel jLabel4 = new JLabel();
            jLabel4.setText("Event Listener");
            jLabel4.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 12));
            jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
            gridBagConstraints9.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints9.gridy = 4;
            gridBagConstraints9.weightx = 1.0;
            gridBagConstraints9.gridx = 1;
            GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
            gridBagConstraints8.gridx = 0;
            gridBagConstraints8.gridy = 4;
            JLabel jLabel3 = new JLabel();
            jLabel3.setText("Handler");
            jLabel3.setPreferredSize(labelSize);
            GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
            gridBagConstraints7.gridx = 0;
            gridBagConstraints7.gridy = 3;
            JLabel jLabel2 = new JLabel();
            jLabel2.setText("URI");
            jLabel2.setPreferredSize(labelSize);
            GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
            gridBagConstraints6.gridx = 0;
            gridBagConstraints6.gridy = 2;
            JLabel jLabel1 = new JLabel();
            jLabel1.setText("Port");
            jLabel1.setPreferredSize(labelSize);
            GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
            gridBagConstraints5.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints5.gridy = 3;
            gridBagConstraints5.weightx = 1.0;
            gridBagConstraints5.gridx = 1;
            GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
            gridBagConstraints4.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints4.gridy = 2;
            gridBagConstraints4.weightx = 1.0;
            gridBagConstraints4.gridx = 1;
            GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
            gridBagConstraints3.gridx = 0;
            gridBagConstraints3.weightx = 0.0;
            gridBagConstraints3.ipadx = 0;
            gridBagConstraints3.gridy = 1;
            JLabel jLabel = new JLabel();
            jLabel.setText("Server");
            jLabel.setPreferredSize(labelSize);
            GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
            gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints2.gridx = 1;
            gridBagConstraints2.gridy = 1;
            gridBagConstraints2.weightx = 1.0;
            jEventPanel = new JPanel();
            jEventPanel.setLayout(new GridBagLayout());
            //jPanel.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.SystemColor.controlShadow,1));
            jEventPanel.add(getJTextServer(), gridBagConstraints2);
            jEventPanel.add(jLabel, gridBagConstraints3);
            jEventPanel.add(getJTextPort(), gridBagConstraints4);
            jEventPanel.add(getJTextURI(), gridBagConstraints5);
            jEventPanel.add(jLabel1, gridBagConstraints6);
            jEventPanel.add(jLabel2, gridBagConstraints7);
            jEventPanel.add(jLabel3, gridBagConstraints8);
            jEventPanel.add(getJTextHandler(), gridBagConstraints9);
            jEventPanel.add(jLabel4, gridBagConstraints10);
        }
        return jEventPanel;
    }

    /**
     * This method initializes jTextField	
     * 	
     * @return javax.swing.JTextField	
     */
    private JTextField getJTextServer() {
        if (jTextServer == null) {
            jTextServer = new JTextField();
            jTextServer.setText(ServerConfig.server);
            jTextServer.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    ServerConfig.server = jTextServer.getText();
                }
            });
            jTextServer.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusLost(java.awt.event.FocusEvent e) {
                    ServerConfig.server = jTextServer.getText();
                }
            });
        }
        return jTextServer;
    }

    /**
     * This method initializes jTextField1	
     * 	
     * @return javax.swing.JTextField	
     */
    private JTextField getJTextPort() {
        if (jTextPort == null) {
            jTextPort = new JTextField();
            jTextPort.setText(ServerConfig.serverPort);
            jTextPort.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    ServerConfig.serverPort = jTextPort.getText();

                }
            });
            jTextPort.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusLost(java.awt.event.FocusEvent e) {
                    ServerConfig.serverPort = jTextPort.getText();
                }
            });

        }
        return jTextPort;
    }

    /**
     * This method initializes jTextField2	
     * 	
     * @return javax.swing.JTextField	
     */
    private JTextField getJTextURI() {
        if (jTextURI == null) {
            jTextURI = new JTextField();
            jTextURI.setText(ServerConfig.serverUri);
            jTextURI.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    ServerConfig.serverUri = jTextURI.getText();
                }
            });
            jTextURI.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusLost(java.awt.event.FocusEvent e) {
                    ServerConfig.serverUri = jTextURI.getText();
                }
            });
        }
        return jTextURI;
    }

    /**
     * This method initializes jTextField3	
     * 	
     * @return javax.swing.JTextField	
     */
    private JTextField getJTextHandler() {
        if (jTextHandler == null) {
            jTextHandler = new JTextField();
            jTextHandler.setText(ServerConfig.serverHandler);
            jTextHandler.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    ServerConfig.serverHandler = jTextHandler.getText();
                }
            });
            jTextHandler.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusLost(java.awt.event.FocusEvent e) {
                    ServerConfig.serverHandler = jTextHandler.getText();
                }
            });
        }
        return jTextHandler;
    }

    /**
     * This method initializes jTextField4	
     * 	
     * @return javax.swing.JTextField	
     */
    private JTextField getJTextMBase() {
        if (jTextMBase == null) {
            jTextMBase = new JTextField();
            jTextMBase.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                }
            });
            jTextMBase.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusLost(java.awt.event.FocusEvent e) {
                }
            });
        }
        return jTextMBase;
    }

    /**
     * This method initializes jButton	
     * 	
     * @return javax.swing.JButton	
     */
    private JButton getJConnectBtn() {
        if (jConnectBtn == null) {
            jConnectBtn = new JButton();
            jConnectBtn.setText("Try Connection with LeAM");
        }
        return jConnectBtn;
    }

    /**
     * This method initializes jPanel2	
     * 	
     * @return javax.swing.JPanel	
     */
    private JPanel getJLeAMPanel() {
        if (jLeAMPanel == null) {
            GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
            gridBagConstraints1.gridx = 0;
            gridBagConstraints1.gridy = 2;
            JLabel jLabel5 = new JLabel();
            jLabel5.setText("MBase");
            jLabel5.setPreferredSize(labelSize);

            GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
            gridBagConstraints12.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints12.gridy = 2;
            gridBagConstraints12.weightx = 1.0;
            gridBagConstraints12.gridx = 1;
            GridBagConstraints gridBagConstraints17 = new GridBagConstraints();
            gridBagConstraints17.gridwidth = 3;
            gridBagConstraints17.weightx = 0.0;
            gridBagConstraints17.fill = java.awt.GridBagConstraints.HORIZONTAL;
            GridBagConstraints gridBagConstraints16 = new GridBagConstraints();
            gridBagConstraints16.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints16.gridy = 1;
            gridBagConstraints16.weightx = 1.0;
            gridBagConstraints16.gridx = 1;
            GridBagConstraints gridBagConstraints15 = new GridBagConstraints();
            gridBagConstraints15.gridx = 0;
            gridBagConstraints15.weightx = 0.0;
            gridBagConstraints15.ipadx = 0;
            gridBagConstraints15.gridy = 1;
            JLabel jLabel8 = new JLabel();
            jLabel8.setText("Home");
            jLabel8.setPreferredSize(labelSize);
            JLabel jLabel7 = new JLabel();
            jLabel7.setText("LeActiveMath");
            jLabel7.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 12));
            jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLeAMPanel = new JPanel();
            jLeAMPanel.setLayout(new GridBagLayout());
            //jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.SystemColor.controlShadow,1));

            jLeAMPanel.add(jLabel7, gridBagConstraints17);
            jLeAMPanel.add(jLabel8, gridBagConstraints15);
            jLeAMPanel.add(getJTextHome(), gridBagConstraints16);
            jLeAMPanel.add(getJTextMBase(), gridBagConstraints12);
            jLeAMPanel.add(jLabel5, gridBagConstraints1);
        }
        return jLeAMPanel;
    }

    /**
     * This method initializes jTextField5	
     * 	
     * @return javax.swing.JTextField	
     */
    private JTextField getJTextHome() {
        if (jTextHome == null) {
            jTextHome = new JTextField();
            jTextHome.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                }
            });
            jTextHome.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusLost(java.awt.event.FocusEvent e) {
                }
            });
        }
        return jTextHome;
    }

}  //  @jve:decl-index=0:visual-constraint="10,10"
