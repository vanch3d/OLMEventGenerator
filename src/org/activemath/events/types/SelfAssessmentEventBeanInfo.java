/**
 * Created on 04-Aug-2005
 * @author FTYK9
 */
package org.activemath.events.types;

import org.scre.evtgenerator.editor.MBaseItemEditor;

import com.l2fprod.common.beans.ExtendedPropertyDescriptor;

/**
 * @author FTYK9
 *
 */
public class SelfAssessmentEventBeanInfo extends ActivemathEventBeanInfo {

    private final String CATEGORY = "SelfAssessmentEvent";

    
    public SelfAssessmentEventBeanInfo() 
    {
        super(SelfAssessmentEvent.class);
        ExtendedPropertyDescriptor prop=null;

        prop = addProperty("itemId");
        prop.setCategory(CATEGORY);
        prop.setPropertyEditorClass(MBaseItemEditor.class);

        prop = addProperty("mastery");
        prop.setCategory(CATEGORY);

    }

}
