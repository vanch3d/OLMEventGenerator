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
import org.activemath.content.metadata.Representation;

import com.l2fprod.common.beans.editor.ComboBoxPropertyEditor;

/**
 * @author FTYK9
 *
 */
public class RepresentationEditor extends ComboBoxPropertyEditor {
    public RepresentationEditor() {
        super();        
        List list = Representation.VALUES;
        setAvailableValues(list.toArray());

//        setAvailableValues(new String[]{
//                "verbal",
//                "visual",
//                "numeric",
//                "symbolic"});
        
        Icon[] icons = new Icon[list.size()];
        Arrays.fill(icons, UIManager.getIcon("Tree.openIcon"));
        setAvailableIcons(icons);
      }

}
