package org.activemath.xlm.events.types;

import org.activemath.events.types.ActivemathEventBeanInfo;
import org.scre.evtgenerator.editor.DescriptorEditor;
import org.scre.evtgenerator.editor.MBaseItemEditor;

import com.l2fprod.common.beans.ExtendedPropertyDescriptor;

public class BeliefUpdatedEventBeanInfo extends ActivemathEventBeanInfo {

    private final String CATEGORY = "BeliefUpdatedEvent";

    public BeliefUpdatedEventBeanInfo() 
    {
        super(BeliefUpdatedEvent.class);
        ExtendedPropertyDescriptor prop=null;

        prop = addProperty("descriptor");
        prop.setCategory(CATEGORY);
        prop.setPropertyEditorClass(DescriptorEditor.class);

        prop = addProperty("mass");
        prop.setCategory(CATEGORY);
    }

}
