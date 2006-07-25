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

import com.l2fprod.common.beans.editor.ComboBoxPropertyEditor;

/**
 * @author FTYK9
 *
 */
public class AbstractnessEditor extends ComboBoxPropertyEditor {
    public AbstractnessEditor() {
        super();      
        
        List list = Abstractness.VALUES;
        setAvailableValues(list.toArray());
        
        Icon[] icons = new Icon[list.size()];
        Arrays.fill(icons, UIManager.getIcon("Tree.openIcon"));
        setAvailableIcons(icons);
      }

}
