/**
 * Created on 04-Aug-2005
 * @author FTYK9
 */
package org.scre.evtgenerator.editor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.l2fprod.common.beans.editor.AbstractPropertyEditor;
import com.l2fprod.common.swing.LookAndFeelTweaks;
import com.l2fprod.common.util.converter.ConverterRegistry;
import com.l2fprod.common.util.converter.NumberConverters;

/**
 * @author FTYK9
 *
 */
public class TimeStampEditor extends AbstractPropertyEditor {

    private final class TimeButton extends JButton {

        public TimeButton() {
          super("Now");

          //if (OS.isMacOSX() && UIManager.getLookAndFeel().isNativeLookAndFeel()) {
          //  setPreferredSize(new Dimension(40, 30));
          //}
          setPreferredSize(new Dimension(40, 30));
          setMargin(new Insets(1, 1, 1, 1));
        }

      }

    
    private final Class type;
    private Object lastGoodValue;
    private JButton button;
    private JFormattedTextField field;
    
    public TimeStampEditor() {
      
      if (!Number.class.isAssignableFrom(Long.class)) {
        throw new IllegalArgumentException("type must be a subclass of Number");
      }
      
      editor = new JPanel(new BorderLayout(0, 0));
      ((JPanel)editor).add("Center", field = new JFormattedTextField());
      ((JPanel)editor).add("East", button = new TimeButton());

      this.type = Long.class;

      field.setValue(getDefaultValue());
      field.setBorder(LookAndFeelTweaks.EMPTY_BORDER);
      //field.enable(false);

      // use a custom formatter to have numbers with up to 64 decimals
      NumberFormat format = NumberConverters.getDefaultFormat();

      //field.setFormatterFactory(
      //    new DefaultFormatterFactory(new NumberFormatter(format))
      //);
      
      button.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          //selectFile();
            Long val = new Long(System.currentTimeMillis());
            setValue(val);
        }
      });
      

    }

    public Object getValue() {
      String text = ((JTextField)field).getText();
      if (text == null || text.trim().length() == 0) {
        return getDefaultValue();
      }
      
      // allow comma or colon
      text = text.replace(',', '.');
      
      // collect all numbers from this textfield
      StringBuffer number = new StringBuffer();
      number.ensureCapacity(text.length());
      for (int i = 0, c = text.length(); i < c; i++) {
        char character = text.charAt(i);
        if ('.' == character || '-' == character
          || (Double.class.equals(type) && 'E' == character)
          || (Float.class.equals(type) && 'E' == character)
          || Character.isDigit(character)) {
          number.append(character);
        } else if (' ' == character) {
          continue;
        } else {
          break;
        }
      }
    
      try {
        lastGoodValue = ConverterRegistry.instance().convert(type,
          number.toString());      
      } catch (Exception e) {
        UIManager.getLookAndFeel().provideErrorFeedback(field);
      }
      
      return lastGoodValue;
    }

    public void setValue(Object value) {
      if (value instanceof Number) {
          field.setText(value.toString());
      } /*else {
          field.setValue(getDefaultValue());
      }*/
      lastGoodValue = value;
    }

    private Object getDefaultValue() {
      try {
        return type.getConstructor(new Class[] {String.class}).newInstance(
          new Object[] {"0"});
      } catch (Exception e) {
        // will not happen
        throw new RuntimeException(e);
      }
    }

}
