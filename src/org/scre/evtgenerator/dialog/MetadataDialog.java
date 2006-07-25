/**
 * Created on 08-Aug-2005
 * @author FTYK9
 */
package org.scre.evtgenerator.dialog;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.beans.BeanInfo;
import java.beans.PropertyDescriptor;
import java.util.Hashtable;

import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.activemath.content.items.EditMetadata;

import com.l2fprod.common.model.DefaultBeanInfoResolver;
import com.l2fprod.common.propertysheet.Property;
import com.l2fprod.common.propertysheet.PropertySheet;
import com.l2fprod.common.propertysheet.PropertySheetPanel;
import com.l2fprod.common.swing.BaseDialog;

/**
 * @author FTYK9
 *
 */
public class MetadataDialog extends BaseDialog {

    private EditMetadata metadata = null;
    
    private JPanel jContentPane = null;
    private PropertySheetPanel propertySheetPanel = null;

    /**
     * @throws HeadlessException
     */
    public MetadataDialog() throws HeadlessException {
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
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName() //UIManager.getCrossPlatformLookAndFeelClassName()
            );
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }     

        this.setSize(300, 286);
        //this.setContentPane(getJContentPane());
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(getPropertySheetPanel(), java.awt.BorderLayout.CENTER);
        setDialogMode(BaseDialog.OK_CANCEL_DIALOG);
        getBanner().setVisible(false);
        setModal(true);
        setTitle("Metadata for ...");
        
        if (metadata==null)
            metadata = new EditMetadata();
        DefaultBeanInfoResolver resolver = new DefaultBeanInfoResolver();
        BeanInfo beanInfo = resolver.getBeanInfo(metadata);
        if (beanInfo==null) return;
        PropertyDescriptor[] propdesc = beanInfo.getPropertyDescriptors();
        for (int i=0;i<propdesc.length;i++)
        {
            System.out.println("property : " + propdesc[i].getName());
            System.out.println("    type : " + propdesc[i].getPropertyType());
            System.out.println("   write : " + propdesc[i].getWriteMethod());
        }
        if (propdesc==null) return;
        getPropertySheetPanel().setProperties(beanInfo.getPropertyDescriptors());
        getPropertySheetPanel().readFromObject(metadata);

    }

    /**
     * This method initializes jContentPane
     * 
     * @return javax.swing.JPanel
     */
/*    private JPanel getJContentPane() {
        if (jContentPane == null) {
            jContentPane = new JPanel();
            jContentPane.setLayout(new BorderLayout());
            //jContentPane.add(getPropertySheetPanel(), java.awt.BorderLayout.CENTER);
        }
        return jContentPane;
    }*/


    /**
     * This method initializes propertySheetPanel	
     * 	
     * @return com.l2fprod.common.propertysheet.PropertySheetPanel	
     */    
    private PropertySheetPanel getPropertySheetPanel() {
    	if (propertySheetPanel == null) {
    		propertySheetPanel = new PropertySheetPanel();
            propertySheetPanel.setMode(PropertySheet.VIEW_AS_CATEGORIES);
            propertySheetPanel.setDescriptionVisible(false);
            propertySheetPanel.setSortingCategories(false);
            propertySheetPanel.setSortingProperties(false);
            propertySheetPanel.setMode(PropertySheet.VIEW_AS_FLAT_LIST);
    	}
    	return propertySheetPanel;
    }
    
    public Hashtable getMetadataList()
    {
        Hashtable mylist = new Hashtable();
        Property prop[] = getPropertySheetPanel().getProperties();
        
        for (int i=0;i<prop.length;i++)
        {
            String name = prop[i].getName();
            Object value = prop[i].getValue();
            mylist.put((name==null)?"":name,(value==null)?"":value);
            System.out.println("metadata : " + name + " - " + value);
        }
        return  mylist;
    }


    /**
     * @return Returns the metadata.
     */
    public EditMetadata getMetadata() {
        return metadata;
    }


    /**
     * @param metadata The metadata to set.
     */
    public void setMetadata(EditMetadata metadata) {
        this.metadata = metadata;
    }
    
    
    

}  //  @jve:decl-index=0:visual-constraint="10,10"
