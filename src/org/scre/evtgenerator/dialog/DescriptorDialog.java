package org.scre.evtgenerator.dialog;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.beans.BeanInfo;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyDescriptor;

import javax.swing.UIManager;

import org.activemath.content.items.EditMetadata;
import org.activemath.xlm.model.BeliefDescript;

import com.l2fprod.common.swing.BaseDialog;
import com.l2fprod.common.model.DefaultBeanInfoResolver;
import com.l2fprod.common.propertysheet.Property;
import com.l2fprod.common.propertysheet.PropertySheetPanel;

public class DescriptorDialog extends BaseDialog {

    private PropertySheetPanel propertySheetPanel = null;  //  @jve:decl-index=0:visual-constraint="18,48"

    private BeliefDescript descript = null;
    
    public DescriptorDialog() throws HeadlessException {
        super();
		initialize();
        // TODO Auto-generated constructor stub
    }

    public DescriptorDialog(Dialog owner) throws HeadlessException {
        super(owner);
		initialize();
        // TODO Auto-generated constructor stub
    }

    public DescriptorDialog(Dialog owner, boolean modal)
            throws HeadlessException {
        super(owner, modal);
		initialize();
        // TODO Auto-generated constructor stub
    }

    public DescriptorDialog(Frame owner) throws HeadlessException {
        super(owner);
		initialize();
        // TODO Auto-generated constructor stub
    }

    public DescriptorDialog(Frame owner, boolean modal)
            throws HeadlessException {
        super(owner, modal);
		initialize();
        // TODO Auto-generated constructor stub
    }

    public DescriptorDialog(Dialog owner, String title)
            throws HeadlessException {
        super(owner, title);
		initialize();
        // TODO Auto-generated constructor stub
    }

    public DescriptorDialog(Dialog owner, String title, boolean modal)
            throws HeadlessException {
        super(owner, title, modal);
		initialize();
        // TODO Auto-generated constructor stub
    }

    public DescriptorDialog(Frame owner, String title) throws HeadlessException {
        super(owner, title);
		initialize();
        // TODO Auto-generated constructor stub
    }

    public DescriptorDialog(Frame owner, String title, boolean modal)
            throws HeadlessException {
        super(owner, title, modal);
		initialize();
        // TODO Auto-generated constructor stub
    }

    public DescriptorDialog(Dialog owner, String title, boolean modal,
            GraphicsConfiguration gc) throws HeadlessException {
        super(owner, title, modal, gc);
		initialize();
        // TODO Auto-generated constructor stub
    }

    public DescriptorDialog(Frame owner, String title, boolean modal,
            GraphicsConfiguration gc) {
        super(owner, title, modal, gc);
		initialize();
        // TODO Auto-generated constructor stub
    }

    /**
     * This method initializes this
     * 
     */
    private void initialize() {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e)
        {
        }
        
        this.setSize(new java.awt.Dimension(391,283));
        getContentPane().setLayout(new BorderLayout());
        //this.setContentPane(getPropertySheetPanel());
        getContentPane().add(getPropertySheetPanel(), java.awt.BorderLayout.CENTER);
        this.setDialogMode(BaseDialog.OK_CANCEL_DIALOG);
        this.setModal(true);
        this.getBanner().setVisible(false);
        this.setTitle("Build the Belief Descriptor");	
    }

    /**
     * This method initializes propertySheetPanel	
     * 	
     * @return com.l2fprod.common.propertysheet.PropertySheetPanel	
     */
    private PropertySheetPanel getPropertySheetPanel() {
        if (propertySheetPanel == null) {
            propertySheetPanel = new PropertySheetPanel();
            propertySheetPanel.setSize(new java.awt.Dimension(371,190));

            PropertyChangeListener listener = new PropertyChangeListener()
            {
                public void propertyChange(PropertyChangeEvent e)
                {
                    if (descript==null) return;
                    Property prop = (Property)e.getSource();
                    Object value = prop.getValue();
                  
                    prop.writeToObject(descript);
                }
            };
            propertySheetPanel.addPropertySheetChangeListener(listener);
        }
        return propertySheetPanel;
    }

    public void setDescript(BeliefDescript descript) {
        this.descript = descript;
        
        DefaultBeanInfoResolver resolver = new DefaultBeanInfoResolver();
        BeanInfo beanInfo = resolver.getBeanInfo(descript);
        if (beanInfo==null) return;
        PropertyDescriptor[] propdesc = beanInfo.getPropertyDescriptors();
        if (propdesc==null) return;
        getPropertySheetPanel().setProperties(beanInfo.getPropertyDescriptors());
        getPropertySheetPanel().readFromObject(descript);
        
    }

    public BeliefDescript getDescript() {
        return descript;
    }
    
    
    

}  //  @jve:decl-index=0:visual-constraint="10,10"
