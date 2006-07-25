/**
 * Created on 02-Sep-2005
 * @author FTYK9
 * @version $Revision: 1.3 $
 */
package org.scre.evtgenerator.editor;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.activemath.content.items.EditMetadata;
import org.scre.evtgenerator.dialog.ItemDialog;
import org.scre.evtgenerator.dialog.MetadataDialog;

import com.l2fprod.common.beans.editor.AbstractPropertyEditor;
import com.l2fprod.common.beans.editor.FixedButton;
import com.l2fprod.common.swing.LookAndFeelTweaks;

/**
 * @author FTYK9
 *
 */
public class MBaseItemEditor extends AbstractPropertyEditor {
    protected JTextField textfield;
    private JButton button;
    
    private String itemid = null;
    
    /**
     * 
     */
    public MBaseItemEditor() {
        editor = new JPanel(new BorderLayout(0, 0));
        ((JPanel)editor).add("Center", textfield = new JTextField());
        ((JPanel)editor).add("East", button = new FixedButton());
        textfield.setBorder(LookAndFeelTweaks.EMPTY_BORDER);   
        
        button.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            selectMetadata();
          }
        });

    }

    /* (non-Javadoc)
     * @see com.l2fprod.common.beans.editor.AbstractPropertyEditor#getAsText()
     */
    public String getAsText() {
        // TODO Auto-generated method stub
        return super.getAsText();
    }

    /* (non-Javadoc)
     * @see com.l2fprod.common.beans.editor.AbstractPropertyEditor#getValue()
     */
    public Object getValue() {
        // TODO Auto-generated method stub
        String str = (String) textfield.getText();
        return textfield.getText();
    }

    /* (non-Javadoc)
     * @see com.l2fprod.common.beans.editor.AbstractPropertyEditor#setAsText(java.lang.String)
     */
    public void setAsText(String text) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        itemid = (String)text;
        if (itemid!=null)
            textfield.setText(itemid);
        else
            textfield.setText("");
    }

    /* (non-Javadoc)
     * @see com.l2fprod.common.beans.editor.AbstractPropertyEditor#setValue(java.lang.Object)
     */
    public void setValue(Object value) {
        // TODO Auto-generated method stub
        itemid = (String)value;
        if (itemid!=null)
            textfield.setText(itemid);
        else
            textfield.setText("");
    }

    protected void selectMetadata() {

        ItemDialog newdlg = new ItemDialog();
        newdlg.centerOnScreen();
        boolean rep = newdlg.ask();
        if (rep)
        {
            String res = newdlg.getSelectedItem();
            if (res==null) return;
            String olditem = itemid;
            setValue(res);
            itemid = res;
            firePropertyChange(olditem, itemid);
        }
      }
    
    

}
