/**
 * Created on 12-Aug-2005
 * @author FTYK9
 * @version $Revision: 1.2 $
 */
package org.scre.evtgenerator.editor;

import java.util.Arrays;
import java.util.List;

import javax.swing.Icon;
import javax.swing.UIManager;

import org.activemath.content.metadata.Abstractness;
import org.activemath.content.metadata.Difficulty;

import com.l2fprod.common.beans.editor.ComboBoxPropertyEditor;

/**
 * @author FTYK9
 *
 */
public class DifficultyEditor extends ComboBoxPropertyEditor {
      public DifficultyEditor() {
        super();    
        
        List list = Difficulty.VALUES;
        setAvailableValues(list.toArray());
//        setAvailableValues(new String[]{
//                "very_easy",
//                "easy",
//                "medium",
//                "difficult",
//                "very_difficult"});
        
        Icon[] icons = new Icon[list.size()];
        Arrays.fill(icons, UIManager.getIcon("Tree.openIcon"));
        setAvailableIcons(icons);
      }

}
