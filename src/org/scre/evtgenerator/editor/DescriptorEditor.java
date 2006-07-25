package org.scre.evtgenerator.editor;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.activemath.xlm.model.BeliefDescript;
import org.activemath.xlm.model.BeliefDescriptor;
import org.activemath.xlm.model.XlmException;
import org.scre.evtgenerator.dialog.DescriptorDialog;
import org.scre.evtgenerator.dialog.ItemDialog;

import com.l2fprod.common.beans.editor.AbstractPropertyEditor;
import com.l2fprod.common.beans.editor.FixedButton;
import com.l2fprod.common.swing.LookAndFeelTweaks;

public class DescriptorEditor extends AbstractPropertyEditor {

    protected JTextField textfield;
    private JButton button;
    
    private Vector descript = null;
    /**
     * 
     */
    public DescriptorEditor() {
        editor = new JPanel(new BorderLayout(0, 0));
        ((JPanel)editor).add("Center", textfield = new JTextField());
        ((JPanel)editor).add("East", button = new FixedButton());
        textfield.setBorder(LookAndFeelTweaks.EMPTY_BORDER);
        textfield.setEditable(false);
        descript = new Vector();
        setValue(descript);
        button.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
              buildDescriptor();
          }
        });

    }
    
    /* (non-Javadoc)
     * @see com.l2fprod.common.beans.editor.AbstractPropertyEditor#getAsText()
     */
    public String getAsText() {
        // TODO Auto-generated method stub
        //return super.getAsText();
        textfield.setText(descript.toString());
        return descript.toString();
    }

    /* (non-Javadoc)
     * @see com.l2fprod.common.beans.editor.AbstractPropertyEditor#getValue()
     */
    public Object getValue() {
        // TODO Auto-generated method stub
        //String str = (String) textfield.getText();
        //return textfield.getText();
        return descript;
    }

    /* (non-Javadoc)
     * @see com.l2fprod.common.beans.editor.AbstractPropertyEditor#setAsText(java.lang.String)
     */
    public void setAsText(String text) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        //
        //super.setAsText(text);
    }

    /* (non-Javadoc)
     * @see com.l2fprod.common.beans.editor.AbstractPropertyEditor#setValue(java.lang.Object)
     */
    public void setValue(Object value) {
        // TODO Auto-generated method stub
        //descript = (BeliefDescript)value;
        //if (descript!=null)
        //    textfield.setText(descript.toString());
        //else
        //    textfield.setText("");
        if (value instanceof Vector) {
            Vector desc = (Vector) value;
            descript = desc;
            textfield.setText(descript.toString());
            
        }
        else
            super.setValue(value);
    }  
    
    protected void buildDescriptor() {

        DescriptorDialog newdlg = new DescriptorDialog();
        BeliefDescript descr = new BeliefDescript(descript);
        newdlg.setDescript(descr);
        newdlg.centerOnScreen();
        boolean rep = newdlg.ask();
        if (rep)
        {
            newdlg.getDescript();
            BeliefDescript res = newdlg.getDescript();
            if (res==null) return;
            Vector olditem = descript;
            BeliefDescriptor desc = new BeliefDescriptor();
            try {
                desc.setAffectId(res.getAffectId());
                desc.setCapeId(res.getCapeId());
                desc.setCompetencyId(res.getCompetencyId());
                desc.setDomainId(res.getDomainId());
                desc.setMetacogId(res.getMetacogId());
                desc.setMotivationId(res.getMotivationId());
            } catch (XlmException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Vector newitem = desc.toVector();
            setValue(newitem);
            descript = newitem;
            textfield.setText(descript.toString());
            firePropertyChange(olditem, descript);
            
        }
      }
}
