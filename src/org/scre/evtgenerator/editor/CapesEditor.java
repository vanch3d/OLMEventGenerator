package org.scre.evtgenerator.editor;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.UIManager;

import org.activemath.xlm.XlmManager;
import org.activemath.xlm.model.cmaps.CAPEsMap;
import org.activemath.xlm.model.cmaps.CMapNode;
import org.activemath.xlm.model.cmaps.CMapTopic;

import com.l2fprod.common.beans.editor.ComboBoxPropertyEditor;

public class CapesEditor extends ComboBoxPropertyEditor {


    public CapesEditor() {
        super();    
        
        XlmManager manager = XlmManager.getInstance();
        CAPEsMap map = manager.getMapCAPEs();
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
