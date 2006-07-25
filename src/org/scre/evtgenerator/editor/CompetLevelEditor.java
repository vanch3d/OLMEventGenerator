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
import org.activemath.content.metadata.CompetencyLevel;

import com.l2fprod.common.beans.editor.ComboBoxPropertyEditor;

/**
 * @author FTYK9
 *
 */
public class CompetLevelEditor extends ComboBoxPropertyEditor {
    public CompetLevelEditor() {
        super();    
        
        List list = CompetencyLevel.VALUES;
        setAvailableValues(list.toArray());

//        setAvailableValues(new String[]{
//                "elementary",
//                "simple_conceptual",
//                "multi_step",
//                "complex"});
        
        Icon[] icons = new Icon[list.size()];
        Arrays.fill(icons, UIManager.getIcon("Tree.openIcon"));
        setAvailableIcons(icons);
      }

}
