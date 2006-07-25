package org.activemath.xlm.events.types;

import org.activemath.events.types.ActivemathEventBeanInfo;
import org.scre.evtgenerator.editor.DescriptorEditor;

import com.l2fprod.common.beans.ExtendedPropertyDescriptor;

public class OLMMetacogEventBeanInfo extends ActivemathEventBeanInfo {

    private final String CATEGORY = "OLMMetacogEvent";
    
    public OLMMetacogEventBeanInfo() 
    {
        super(OLMMetacogEvent.class);
        ExtendedPropertyDescriptor prop=null;
        
        prop = addProperty("descriptor");
        prop.setCategory(CATEGORY);
        prop.setPropertyEditorClass(DescriptorEditor.class);
        
        prop = addProperty("moveID");
        prop.setCategory(CATEGORY);
        prop = addProperty("depth");
        prop.setCategory(CATEGORY);
        prop = addProperty("initiative");
        prop.setCategory(CATEGORY);
    }
}
