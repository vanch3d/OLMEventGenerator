/**
 * Created on 31-Aug-2005
 * @author FTYK9
 * @version $Revision: 1.11 $
 */
package org.scre.evtgenerator;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ItemEvent;
import java.beans.BeanInfo;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.UIManager;

import org.activemath.abstractcontent.MBaseException;
import org.activemath.abstractcontent.MBaseID;
import org.activemath.abstractcontent.MBaseRef;
import org.activemath.config.Manager;
import org.activemath.content.ContentManager;
import org.activemath.content.items.EditMetadata;
import org.activemath.content.items.Item;
import org.activemath.content.items.Metadata;
import org.activemath.events.ActivemathEvent;
import org.activemath.events.impl.EventEncoder;
import org.activemath.events.types.ExerciseActionEvent;
import org.activemath.events.types.ExerciseFinishedEvent;
import org.activemath.events.types.ExerciseHelpRequestEvent;
import org.activemath.events.types.ExerciseStartedEvent;
import org.activemath.events.types.ExerciseStepEvent;
import org.activemath.events.types.ItemSeenEvent;
import org.activemath.events.types.SelfReportEvent;
import org.activemath.omdocjdom.OJElement;
import org.activemath.xlm.events.types.BeliefUpdatedEvent;
import org.activemath.xlm.events.types.OLMChallengeEvent;
import org.activemath.xlm.events.types.OLMMetacogEvent;
import org.activemath.xlm.events.types.SituationFactorChangedEvent;
import org.activemath.xlm.model.BeliefDescript;
import org.activemath.xlm.model.BeliefDescriptor;
import org.activemath.xlm.model.XlmModelImpl;
import org.apache.log4j.PropertyConfigurator;
import org.apache.xmlrpc.applet.SimpleXmlRpcClient;
import org.apache.xmlrpc.applet.XmlRpcException;
import org.scre.evtgenerator.dialog.SettingsPanel;
import org.scre.evtgenerator.utils.LeAMContent;
import org.scre.evtgenerator.utils.ServerConfig;

import EDU.oswego.cs.dl.util.concurrent.misc.SwingWorker;

import com.l2fprod.common.model.DefaultBeanInfoResolver;
import com.l2fprod.common.propertysheet.Property;
import com.l2fprod.common.propertysheet.PropertySheet;
import com.l2fprod.common.propertysheet.PropertySheetPanel;

/**
 * @author FTYK9
 *
 */
public class EventGenerator extends JFrame {

    private JPanel jContentPane = null;
    private JPanel jOptionPanel = null;
    private JPanel jCommandPanel = null;
    private JComboBox jCBEvent = null;

    private JButton jBtnFire = null;
    private PropertySheetPanel jPropSheet = null;
    private JTabbedPane jTabbedPane = null;
    private JPanel jPanelEvent = null;
    private JButton jBtnValid = null;
    private JProgressBar jProgressBar = null;
    private JButton jScanBtn = null;
    private SettingsPanel jSettingsPanel = null;

    private SimpleXmlRpcClient client=null;
    private Vector eventList = null;
    private boolean DEBUG_TOPIDs = false;

    private Hashtable index = new Hashtable();
    private JPanel jScanPanel = null;
    private JLabel jScanLabel = null;
    /**
     * @throws HeadlessException
     */
    public EventGenerator() throws HeadlessException {
        super();
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

        // Get the collections from the content
        // @todo I only wotk with LeAM_calculus; need to extend it to all collections.
        LeAMContent.collections.add("LeAM_calculus");

        // Initialise the events that could be fired
        // MAKE SURE TO DO IT BEFORE BUILDING THE GUI
        eventList = new Vector();
        eventList.add(new ExerciseStartedEvent());
        //eventList.add(new ExerciseStepEvent());
        eventList.add(new ExerciseFinishedEvent());
        //eventList.add(new HappinessEvent());
        eventList.add(new SelfReportEvent());
        eventList.add(new ItemSeenEvent());
        //eventList.add(new ExerciseActionEvent());
        //eventList.add(new ExerciseHelpRequestEvent());
        eventList.add(new SituationFactorChangedEvent());
        eventList.add(new BeliefUpdatedEvent());
        eventList.add(new OLMMetacogEvent());
        eventList.add(new OLMChallengeEvent());
       
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(371, 404);
        this.setContentPane(getJContentPane());
        this.setTitle("XLM Event Generator");

        // Initialise LeAM connection and other lengthy processes
        SwingWorker worker = new SwingWorker() 
        {
            protected Object construct() throws Exception 
            {
                // @todo find a better way to do it not hard-coded.
                // Make sure that LeAM home is on the system properties
                //System.setProperty("activemath.home","F:/activemath");
                System.setProperty("mbaseRef.class","org.activemath.omdocjdom.mbase.OJXmlRpcMBaseRef");
                System.setProperty("mbaseRef.detach","true");
                System.setProperty("mbaseRef.url","http://127.0.0.1:27000/");

                // @todo Call these method just to initialise the singleton now
                MBaseRef mbaseRef= Manager.getOfficialMBase();
                ContentManager contentManager = ContentManager.getInstance();
                XlmModelImpl    impl = XlmModelImpl.getInstance();

                // Adjust the length of the progressbar 
                int nItems = LeAMContent.theories.size()-1;
                try {
                    File file = new File("EG.ser");
                    ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
                    // Deserialize the object
                    index = (Hashtable) in.readObject();
                    Enumeration enum = index.elements();
                    while (enum.hasMoreElements())
                    {
                        Integer elt = (Integer)enum.nextElement();
                        nItems += elt.intValue();
                    }
                    in.close();

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    //e.printStackTrace();
                }
                jProgressBar.setMaximum(nItems);
                        
                // Get all the theories in the content
                Iterator theories = null;
                try {
                    theories = mbaseRef.listTheories(LeAMContent.collections);
                    while (theories.hasNext()) 
                    {
                        MBaseID theory = (MBaseID) theories.next();
                        LeAMContent.theories.add(theory);
                    }
                } catch (MBaseException e) {
                    //e.printStackTrace();
                    System.err.println(e.getMessage());
                }
                
                // @todo This patch is need for cutting down the level of log produce by LeAM
                // It means that I had to add the log4j jar to the projec (damn!).
                Properties prop= new Properties();
                prop.setProperty("log4j.appender.SysOut","org.apache.log4j.ConsoleAppender");
                prop.setProperty("log4j.appender.SysOut.layout","org.apache.log4j.PatternLayout");
                prop.setProperty("log4j.rootLogger","ERROR,SysOut");
                prop.setProperty("log4j.logger.org.activemath.omdocjdom.lucene","ERROR");
                prop.setProperty("log4j.logger.org.activemath.presentation","ERROR");
                prop.setProperty("log4j.logger.org.activemath.webapp","ERROR");
                PropertyConfigurator.configure(prop);
                
                return null;
            }
            
            /* (non-Javadoc)
             * @see EDU.oswego.cs.dl.util.concurrent.misc.SwingWorker#finished()
             */
            protected void finished() {
                // TODO Auto-generated method stub
                super.finished();
                // Initialise the editor with the default selected event
                initEditor();

                //jPropSheet.setVisible(true);
                jScanBtn.setEnabled(true);
                jBtnFire.setEnabled(true);
                jBtnValid.setEnabled(true);
                jCBEvent.setEnabled(true);
                jProgressBar.setEnabled(true);
            }
            
        };
        worker.start();
    }

    /**
     * This method initializes jContentPane
     * 
     * @return javax.swing.JPanel
     */
    private JPanel getJContentPane() {
        if (jContentPane == null) {
            jContentPane = new JPanel();
            jContentPane.setLayout(new BorderLayout());
            jContentPane.add(getJTabbedPane(), java.awt.BorderLayout.CENTER);
            jContentPane.add(getJScanPanel(), java.awt.BorderLayout.SOUTH);
        }
        return jContentPane;
    }


    /**
     * This method initializes jPanel   
     *  
     * @return javax.swing.JPanel   
     */    
    private JPanel getJOptionPanel() {
        if (jOptionPanel == null) {
            GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
            gridBagConstraints5.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints5.gridx = 1;
            gridBagConstraints5.gridy = 0;
            gridBagConstraints5.weightx = 1.0;
            gridBagConstraints5.insets = new java.awt.Insets(5,2,5,2);
            GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
            gridBagConstraints4.gridx = 0;
            gridBagConstraints4.gridy = 0;
            gridBagConstraints4.insets = new java.awt.Insets(2,2,2,2);
            JLabel jLabel1 = new JLabel();
            jLabel1.setText("Event Type");
            jOptionPanel = new JPanel();
            jOptionPanel.setLayout(new GridBagLayout());
            jOptionPanel.add(getJCBEvent(), gridBagConstraints5);
            jOptionPanel.add(jLabel1, gridBagConstraints4);
        }
        return jOptionPanel;
    }

    /**
     * This method initializes jPanel1  
     *  
     * @return javax.swing.JPanel   
     */    
    private JPanel getJCommandPanel() {
        if (jCommandPanel == null) {
            jCommandPanel = new JPanel();
            jCommandPanel.add(getJBtnValid(), null);
            jCommandPanel.add(getJBtnFire(), getJBtnFire().getName());
        }
        return jCommandPanel;
    }


    /**
     * This method initializes jComboBox    
     *  
     * @return javax.swing.JComboBox    
     */    
    private JComboBox getJCBEvent() {
        if (jCBEvent == null) {
            jCBEvent = new JComboBox();
            jCBEvent.setEnabled(false);
            jCBEvent.setModel(new DefaultComboBoxModel(eventList));
            jCBEvent.setRenderer(new DefaultListCellRenderer()
            {   
                public Component getListCellRendererComponent(
                    JList list,
                    Object value,   // value to display
                    int index,      // cell index
                    boolean iss,    // is the cell selected
                    boolean chf)    // the list and the cell have the focus
                {
                    super.getListCellRendererComponent(list, value, index, iss, chf);
                    String text = null;
                    if (value instanceof ActivemathEvent) {
                        ActivemathEvent amevt = (ActivemathEvent) value;
                        text = amevt.getType();
                    }
                    else if (value instanceof Metadata) {
                        Metadata metada = (Metadata) value;
                        text = EditMetadata.class.toString();
                        
                    }
                    else if (value instanceof BeliefDescript) {
                        BeliefDescript metada = (BeliefDescript) value;
                        text = BeliefDescript.class.toString();
                        
                    }
                    setText(text);
                    return this;
                }
            });
            jCBEvent.addItemListener(new java.awt.event.ItemListener() { 
                public void itemStateChanged(ItemEvent evt) {  
                    // Get the affected item
                    Object item = evt.getItem();
                    if (evt.getStateChange() == ItemEvent.SELECTED) {
                        // Item was just selected
                        System.out.println("Item changed to" + item );
                        initEditor();
                    } else if (evt.getStateChange() == ItemEvent.DESELECTED) {
                        // Item is no longer selected
                    }
                }
            });
            
        }
        return jCBEvent;
    }

    /**
     * This method initializes jButton  
     *  
     * @return javax.swing.JButton  
     */    
    private JButton getJBtnFire() {
        if (jBtnFire == null) {
            jBtnFire = new JButton();
            jBtnFire.setEnabled(false);
            jBtnFire.setText("Fire");
            jBtnFire.addActionListener(new java.awt.event.ActionListener() { 
                public void actionPerformed(java.awt.event.ActionEvent e) {    
                    System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
                    sendEvent();
                }
            });
        }
        return jBtnFire;
    }

    /**
     * This method initializes propertySheetPanel   
     *  
     * @return com.l2fprod.common.propertysheet.PropertySheetPanel  
     */    
    private PropertySheetPanel getJPropSheet() {
        if (jPropSheet == null) {
            jPropSheet = new PropertySheetPanel();
            
            jPropSheet.setMode(PropertySheet.VIEW_AS_CATEGORIES);
            jPropSheet.setDescriptionVisible(false);
            jPropSheet.setSortingCategories(false);
            jPropSheet.setSortingProperties(false);
            jPropSheet.setMode(PropertySheet.VIEW_AS_FLAT_LIST);
            //jPropSheet.setToolBarVisible(false);
            //jPropSheet.setVisible(false);
            
            // everytime a property change, update the button with it
            PropertyChangeListener listener = new PropertyChangeListener() {
              public void propertyChange(PropertyChangeEvent e) {
                Property prop = (Property)e.getSource();
                Object value = prop.getValue();
                
                Object obj = getJCBEvent().getSelectedItem();
                prop.writeToObject(obj);
                /*if (obj instanceof ActivemathEvent) {
                    ActivemathEvent evt = (ActivemathEvent) obj;
                    prop.writeToObject(evt);
                    //System.out.println("prop " + value);
                    //System.out.println("Updated object to " + evt);
                }*/
              }
            };
            jPropSheet.addPropertySheetChangeListener(listener);
           
        }
        return jPropSheet;
    }

    
    private void initEditor()
    {      
        Object obj = getJCBEvent().getSelectedItem();
        DefaultBeanInfoResolver resolver = new DefaultBeanInfoResolver();
        BeanInfo beanInfo = resolver.getBeanInfo(obj);
        if (beanInfo==null) return;
        
        PropertyDescriptor[] propdesc = beanInfo.getPropertyDescriptors();
        if (propdesc==null) return;
        
        getJPropSheet().setProperties(beanInfo.getPropertyDescriptors());
        getJPropSheet().readFromObject(obj);

//        if (obj instanceof ActivemathEvent) {
//            ActivemathEvent evt = (ActivemathEvent) obj;
//            DefaultBeanInfoResolver resolver = new DefaultBeanInfoResolver();
//            BeanInfo beanInfo = resolver.getBeanInfo(evt);
//            if (beanInfo==null) return;
//            
//            PropertyDescriptor[] propdesc = beanInfo.getPropertyDescriptors();
//            /*for (int i=0;i<propdesc.length;i++)
//            {
//                System.out.println("property : " + propdesc[i].getName());
//                System.out.println("    type : " + propdesc[i].getPropertyType());
//                System.out.println("   write : " + propdesc[i].getWriteMethod());
//            }*/
//            if (propdesc==null) return;
//            
//            getJPropSheet().setProperties(beanInfo.getPropertyDescriptors());
//            getJPropSheet().readFromObject(evt);
//        }
//        if (obj instanceof BeliefDescript) 
//        {
//            BeliefDescript metadata = (BeliefDescript) obj;
//            DefaultBeanInfoResolver resolver = new DefaultBeanInfoResolver();
//            BeanInfo beanInfo = resolver.getBeanInfo(metadata);
//            if (beanInfo==null) return;
//            PropertyDescriptor[] propdesc = beanInfo.getPropertyDescriptors();
//            if (propdesc==null) return;
//            getJPropSheet().setProperties(beanInfo.getPropertyDescriptors());
//            getJPropSheet().readFromObject(metadata);
//            
//        }
    }
    
    private void sendEvent()
    {
        if (client==null)
        {
            try {
                client = new SimpleXmlRpcClient("http://" + ServerConfig.server + ":"  + ServerConfig.serverPort + "/" + ServerConfig.serverUri);
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                System.out.println("URL malformed : " + "http://" + ServerConfig.server + ":"  + ServerConfig.serverPort + "/" + ServerConfig.serverUri);
                e.printStackTrace();
                return;
            }
        }
        System.out.println("connecting to : " + "http://" + ServerConfig.server + ":"  + ServerConfig.serverPort + "/" + ServerConfig.serverUri);
        
        Property prop[] = getJPropSheet().getProperties();
        
        Hashtable eventProps = new Hashtable();
        for (int i=0;i<prop.length;i++)
        {
            String name = prop[i].getName();
            String type = prop[i].getClass().toString();
            Object value = prop[i].getValue();
            if (name!=null && value !=null)
            {
                // Convert into XLM-RPC complient types if necessary
                if (value instanceof Long) 
                {
                    eventProps.put(name,value.toString());
                }
                else if (value instanceof ArrayList) 
                {
                    ArrayList arr = (ArrayList)value;
                    Vector vec = new Vector(arr);
                    eventProps.put(name,vec);
                }
                else
                    eventProps.put(name,value);
                type = value.getClass().toString();
                System.out.println(name + " (" + type + ") - " + value);
            }
        }
        
        Vector params = new Vector();
        params.addElement(eventProps);

        
        try {
            client.execute(ServerConfig.serverHandler, params);
        } catch (XmlRpcException e) {
            // TODO Auto-generated catch block
            System.err.println("Cannot execute XML-RPC command : " + e.getMessage());
            //e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.err.println("Cannot execute XML-RPC command : " + e.getMessage());
            //e.printStackTrace();
        }
         
        
    }
    /**
     * This method initializes jTabbedPane	
     * 	
     * @return javax.swing.JTabbedPane	
     */    
    private JTabbedPane getJTabbedPane() {
    	if (jTabbedPane == null) {
    		jTabbedPane = new JTabbedPane();
    		jTabbedPane.addTab("Event", null, getJPanelEvent(), null);
            jTabbedPane.addTab("Settings", null, getJSettingsPanel(), null);
    	}
    	return jTabbedPane;
    }

    /**
     * This method initializes jPanel	
     * 	
     * @return javax.swing.JPanel	
     */    
    private JPanel getJPanelEvent() {
    	if (jPanelEvent == null) {
    		jPanelEvent = new JPanel();
    		jPanelEvent.setLayout(new BorderLayout());
    		jPanelEvent.add(getJPropSheet(), java.awt.BorderLayout.CENTER);
    		jPanelEvent.add(getJCommandPanel(), java.awt.BorderLayout.SOUTH);
    		jPanelEvent.add(getJOptionPanel(), java.awt.BorderLayout.NORTH);
    	}
    	return jPanelEvent;
    }

    /**
     * This method initializes jButton	
     * 	
     * @return javax.swing.JButton	
     */    
    private JButton getJBtnValid() {
    	if (jBtnValid == null) {
    		jBtnValid = new JButton();
            jBtnValid.setEnabled(false);
    		jBtnValid.setText("Check Validity");
    		jBtnValid.addActionListener(new java.awt.event.ActionListener() { 
    			public void actionPerformed(java.awt.event.ActionEvent e) {    
    				System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
                    
                    Object obj = getJCBEvent().getSelectedItem();
                    if (obj instanceof ActivemathEvent) {
                        ActivemathEvent evt = (ActivemathEvent) obj;
                        //String str = EGEventEncoderXML.toXML(evt);
                        String str = EventEncoder.toXML(evt);
                        System.out.println(str);
                    }
    			}
    		});
    	}
    	return jBtnValid;
    }

    /**
     * This method initializes jProgressBar	
     * 	
     * @return javax.swing.JProgressBar	
     */
    private JProgressBar getJProgressBar() {
        if (jProgressBar == null) {
            jProgressBar = new JProgressBar();
            jProgressBar.setValue(0);
            jProgressBar.setMinimum(0);
            jProgressBar.setEnabled(false);
            jProgressBar.setStringPainted(true);
            //jProgressBar.setString("0/"+LeAMContent.theories.size());
        }
        return jProgressBar;
    }

    /**
     * This method initializes jButton	
     * 	
     * @return javax.swing.JButton	
     */
    private JButton getJScanBtn() {
        if (jScanBtn == null) {
            jScanBtn = new JButton();
            jScanBtn.setText("Parse Content");
            jScanBtn.setEnabled(false);
            jScanBtn.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    parseContent();
                }
            });
        }
        return jScanBtn;
    }

    /**
     * This method initializes settingsPanel	
     * 	
     * @return org.scre.evtgenerator.SettingsPanel	
     */
    private SettingsPanel getJSettingsPanel() {
        if (jSettingsPanel == null) {
            jSettingsPanel = new SettingsPanel();
        }
        return jSettingsPanel;
    }

    /**
     * This method initializes jPanel	
     * 	
     * @return javax.swing.JPanel	
     */
    private JPanel getJScanPanel() {
        if (jScanPanel == null) {
            GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
            gridBagConstraints2.gridheight = 1;
            gridBagConstraints2.insets = new java.awt.Insets(2,0,0,2);
            GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
            gridBagConstraints1.gridx = 0;
            gridBagConstraints1.gridwidth = 1;
            gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints1.gridheight = 1;
            gridBagConstraints1.insets = new java.awt.Insets(0,2,2,2);
            gridBagConstraints1.gridy = 1;
            jScanLabel = new JLabel();
            jScanLabel.setText(" ");
            GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.insets = new java.awt.Insets(2,2,0,2);
            gridBagConstraints.weightx = 1.0;
            jScanPanel = new JPanel();
            jScanPanel.setLayout(new GridBagLayout());
            jScanPanel.add(getJProgressBar(), gridBagConstraints);
            jScanPanel.add(getJScanBtn(), gridBagConstraints2);
            jScanPanel.add(jScanLabel, gridBagConstraints1);
        }
        return jScanPanel;
    }

    /**
     * Launches this application
     */
    public static void main(String[] args) {
        EventGenerator application = new EventGenerator();
        application.show();
    }

    private String stripID(String id)
    {
        String temp = id;
        String temp2 = temp.replaceAll("mbase://LeAM_calculus/","");
        return temp2;
    }

    private void parseContent()
    {
        jScanBtn.setEnabled(false);
        jProgressBar.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        SwingWorker worker = new SwingWorker() 
        {

            protected Object construct() throws Exception 
            {
                ContentManager contentManager = ContentManager.getInstance();
                MBaseRef mbaseRef= Manager.getOfficialMBase();
                XlmModelImpl    impl = XlmModelImpl.getInstance();

                System.out.println("*** Start building list from idx=" + 
                        (LeAMContent.inc+1) + "/"+LeAMContent.theories.size());
                
                for (int i=LeAMContent.inc;i<LeAMContent.theories.size();i++)
                {
                    List tempitems = new ArrayList();
                    try
                    {
                        MBaseID theory = (MBaseID)LeAMContent.theories.get(i);
                        Iterator items = null;
                        items = mbaseRef.listItems(theory);
                        jScanLabel.setText("Parsing " + theory.toString() + " ...");
                        
                        Integer ff = (Integer)index.get(LeAMContent.theories.get(i).toString());
                        
                        while (items!= null && items.hasNext()) 
                        {
                            MBaseID itemMbid = (MBaseID) items.next();
                            Item item = contentManager.getContentItem( itemMbid);
                            if (item==null) continue;
                            //if (!(item instanceof ExerciseItem)) continue;
                            Set topics = impl.getMapDomain().getTopContentIds(item.getId());
    
                            //Metadata meta = item.getMetadata();
                            // @todo    This methods logs an ERROR when no metada exists for the item;
                            //          better do it indirectly and get rid of the message
                            Metadata meta = null;
                            try {
                                OJElement metadataElem = mbaseRef.getMetadata(item.getMBaseId());
                                meta = new Metadata(item.getId(),metadataElem);
                            } catch (MBaseException e) 
                            {
                                // I know that it failsbut I don't want the stupid error message
                                //e.printStackTrace();
                                
                            }
                            

                            if (meta==null)continue;
                            String type = item.getOmdocTag();
                            if ("".equals(type)) continue;
    
                            // add in the table
                            LeAMContent.types.add(item.getOmdocTag());
                            Vector vec = new Vector();
                            vec.add(stripID(item.getId()));
                            if (!"".equals(item.getOmdocType()))
                                type = type + " (" + item.getOmdocType() + ")";
                            vec.add(type);
                            vec.add(meta.getDifficulty());
                            vec.add(meta.getCompetencies());
                            vec.add(meta.getCompetencyLevel());
    
                            Iterator topite = topics.iterator();
                            Set mylist = new HashSet();
                            while (topite.hasNext())
                            {
                                String topid = (String)topite.next();
                                Set topic = impl.getMapDomain().getFocusTopicFromContent(topid);
                                if (topic==null || topic.isEmpty())
                                {
                                    if (DEBUG_TOPIDs)
                                        mylist.add("---"+stripID(topid)+"---");
                                    else
                                        mylist.add("*");
                                }
                                else
                                {
                                    mylist.addAll(topic);
                                    
                                }
                            }
    
                            vec.add(mylist);
                            //LeAMContent.items.add(vec);
                            tempitems.add(vec);
                            //tModel.addRow(vec);
                            if (ff!=null && ff.intValue()!=0) 
                                jProgressBar.setValue(jProgressBar.getValue()+1);
                            else
                                jProgressBar.setIndeterminate(true);
                        } 
                    }catch (MBaseException e) 
                    {
                        // TODO Auto-generated catch block
//                        e.printStackTrace();
                        System.err.println(e.getMessage());
                        System.err.println("*** Process stopped. Will start again from idx=" + 
                                (LeAMContent.inc+1) + "/" + LeAMContent.theories.size());
                        return new HashSet(); 
                    }
                    LeAMContent.items.addAll(tempitems);
                    int nItem = tempitems.size();
                    index.put(LeAMContent.theories.get(i).toString(),new Integer(nItem));
                    LeAMContent.inc = i;
                    jProgressBar.setIndeterminate(false);
                    jProgressBar.setValue(jProgressBar.getValue()+1);
                }
                // Serialize to a file
                ObjectOutput out = new ObjectOutputStream(new FileOutputStream("EG.ser"));
                out.writeObject(index);
                out.close();
                
                return new HashSet();                
            }

            /* (non-Javadoc)
             * @see EDU.oswego.cs.dl.util.concurrent.misc.SwingWorker#finished()
             */
            protected void finished() {
                // TODO Auto-generated method stub
                super.finished();
                jProgressBar.setCursor(Cursor.getDefaultCursor());
                if (LeAMContent.inc == (LeAMContent.theories.size()-1)) 
                {
                    System.out.println("*** Content parsing completed");
                    jProgressBar.setString("Done");
                    jScanLabel.setText(" ");
                    return;
                }
                jScanBtn.setEnabled(true);
           }
        
            
        };

        worker.start();
    }
    
}  
