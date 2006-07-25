package org.activemath.xlm.events.types;

import org.activemath.events.types.ActivemathEventBeanInfo;
import org.scre.evtgenerator.editor.MBaseItemEditor;

import com.l2fprod.common.beans.ExtendedPropertyDescriptor;

public class SituationFactorChangedEventBeanInfo extends
        ActivemathEventBeanInfo {

    private final String CATEGORY = "SituationFactorChangedEvent";
    
    public SituationFactorChangedEventBeanInfo() 
    {
        super(SituationFactorChangedEvent.class);
        ExtendedPropertyDescriptor prop=null;
        
        prop = addProperty("itemId");
        prop.setCategory(CATEGORY);
        prop.setPropertyEditorClass(MBaseItemEditor.class);
        
        prop = addProperty("confidence");
        prop.setCategory(CATEGORY);
        prop = addProperty("interest");
        prop.setCategory(CATEGORY);
        prop = addProperty("aptitude");
        prop.setCategory(CATEGORY);
        prop = addProperty("effort");
        prop.setCategory(CATEGORY);

    }

}
