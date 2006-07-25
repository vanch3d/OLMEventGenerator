/**
 * Created on 12-Aug-2005
 * @author FTYK9
 * @version $Revision: 1.2 $
 */
package org.scre.evtgenerator.editor;

import java.awt.Component;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Set;
import java.util.Vector;

import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.UIManager;

import org.activemath.xlm.XlmManager;
import org.activemath.xlm.model.cmaps.CMapNode;
import org.activemath.xlm.model.cmaps.CMapTopic;
import org.activemath.xlm.model.cmaps.CompetencyMap;

import com.l2fprod.common.beans.editor.ComboBoxPropertyEditor;
import com.l2fprod.common.beans.editor.ComboBoxPropertyEditor.Renderer;
import com.l2fprod.common.beans.editor.ComboBoxPropertyEditor.Value;

/**
 * @author FTYK9
 *
 */
public class CompetencyEditor extends ComboBoxPropertyEditor {
    
    public CompetencyEditor() {
        super();    
        
        XlmManager manager = XlmManager.getInstance();
        CompetencyMap map = manager.getMapCompetency();
        Hashtable table = map.getNodes();
        Vector vec = new Vector();
        // Browse through all the nodes and add them
        for (Enumeration e = table.elements(); e.hasMoreElements();)
        {
            CMapNode source = (CMapNode) e.nextElement();
            if (!source.isPublic()) continue;
            if (source instanceof CMapTopic) {
                CMapTopic topic = (CMapTopic) source;
                vec.add(topic.getNodeID());
            }
        }
        
        Object[] tt = vec.toArray();
        setAvailableValues(tt);

        Icon[] icons = new Icon[tt.length];
        Arrays.fill(icons, UIManager.getIcon("Tree.openIcon"));
        setAvailableIcons(icons);
      }
}
