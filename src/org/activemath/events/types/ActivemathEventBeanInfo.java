/**
 * Created on 08-Aug-2005
 * @author FTYK9
 */
package org.activemath.events.types;

import org.scre.evtgenerator.editor.TimeStampEditor;

import com.l2fprod.common.beans.BaseBeanInfo;
import com.l2fprod.common.beans.ExtendedPropertyDescriptor;

/**
 * @author FTYK9
 *
 */
public class ActivemathEventBeanInfo extends BaseBeanInfo {

    private final String CATEGORY = "ActivemathEvent";

    
    /**
     * @param type
     */
    protected ActivemathEventBeanInfo(Class type) 
    {
        super(type);
        ExtendedPropertyDescriptor prop=null;
        
        prop = addProperty("type");
        prop.setCategory(CATEGORY);
        prop.setShortDescription("Type of the event (cannot be edited)");
        
        prop = addProperty("Timestamp");
        prop.setCategory(CATEGORY);
        prop.setPropertyEditorClass(TimeStampEditor.class);
        prop.setShortDescription("System time when the event happened (click on \"now\" for the current time)");

        prop = addProperty("sessionId");
        prop.setCategory(CATEGORY);
        prop.setShortDescription("The identifier of the session (string)");

        prop = addProperty("userId");
        prop.setCategory(CATEGORY);
        prop.setShortDescription("The identifier of the user (string)");
    }

}
