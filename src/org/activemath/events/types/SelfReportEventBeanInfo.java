/**
 * @file dfdfdfdf.java
 */
package org.activemath.events.types;

import org.scre.evtgenerator.editor.MBaseItemEditor;

import com.l2fprod.common.beans.ExtendedPropertyDescriptor;

/**
 * @author vanlabeke
 * @version $Revision: 1.1 $
 */
public class SelfReportEventBeanInfo extends ActivemathEventBeanInfo {
    private final String CATEGORY = "SelfReportEvent";

    
    public SelfReportEventBeanInfo() 
    {
        super(SelfReportEvent.class);
        ExtendedPropertyDescriptor prop=null;

        prop = addProperty("itemId");
        prop.setCategory(CATEGORY);
        prop.setPropertyEditorClass(MBaseItemEditor.class);

        prop = addProperty("liking");
        prop.setCategory(CATEGORY);
        prop = addProperty("pride");
        prop.setCategory(CATEGORY);
        prop = addProperty("satisfaction");
        prop.setCategory(CATEGORY);

    }

}
