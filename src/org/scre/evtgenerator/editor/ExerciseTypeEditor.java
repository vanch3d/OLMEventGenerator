/**
 * Created on 12-Aug-2005
 * @author FTYK9
 * @version $Revision: 1.1 $
 */
package org.scre.evtgenerator.editor;

import java.util.Arrays;

import javax.swing.Icon;
import javax.swing.UIManager;

import com.l2fprod.common.beans.editor.ComboBoxPropertyEditor;

/**
 * @author FTYK9
 *
 */
public class ExerciseTypeEditor extends ComboBoxPropertyEditor {
    public ExerciseTypeEditor() {
        super();        
        setAvailableValues(new String[]{
                "mcq_sigle_answer",
                "mcq_multiple_answer",
                "fill_in_blank",
                "puzzle",
                "drag_and_drop",
                "concept map",
                "other"});
        
        Icon[] icons = new Icon[7];
        Arrays.fill(icons, UIManager.getIcon("Tree.openIcon"));
        setAvailableIcons(icons);
      }

}
