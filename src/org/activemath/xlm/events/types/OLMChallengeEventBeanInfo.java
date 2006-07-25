package org.activemath.xlm.events.types;

import org.activemath.events.types.ActivemathEventBeanInfo;
import org.scre.evtgenerator.editor.DescriptorEditor;

import com.l2fprod.common.beans.ExtendedPropertyDescriptor;

public class OLMChallengeEventBeanInfo extends ActivemathEventBeanInfo {

    private final String CATEGORY = "OLMMetacogEvent";

    public OLMChallengeEventBeanInfo() {
        super(OLMChallengeEvent.class);

        ExtendedPropertyDescriptor prop=null;
        
        prop = addProperty("descriptor");
        prop.setCategory(CATEGORY);
        prop.setPropertyEditorClass(DescriptorEditor.class);
        
        prop = addProperty("target");
        prop.setCategory(CATEGORY);
        prop = addProperty("confidence");
        prop.setCategory(CATEGORY);
        prop = addProperty("intransigence");
        prop.setCategory(CATEGORY);    
        prop = addProperty("level");
        prop.setCategory(CATEGORY);    
        prop = addProperty("evidence");
        prop.setCategory(CATEGORY);    
        prop = addProperty("attribute");
        prop.setCategory(CATEGORY);    
        prop = addProperty("value");
        prop.setCategory(CATEGORY);
    }
}
