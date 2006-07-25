/**
 * Created on 30-Aug-2005
 * @author FTYK9
 * @version $Revision: 1.3 $
 */
package org.activemath.events.types;

import com.l2fprod.common.beans.ExtendedPropertyDescriptor;


/**
 * @author FTYK9
 *
 */
public class ExerciseActionEventBeanInfo extends ActivemathEventBeanInfo {

    private final String CATEGORY = "ExerciseActionEvent";

    public ExerciseActionEventBeanInfo()
    {
        super(ExerciseActionEvent.class);
        ExtendedPropertyDescriptor prop=null;

        prop = addProperty("actionId");
        prop.setCategory(CATEGORY);
        
        prop = addProperty("actionStatus");
        prop.setCategory(CATEGORY);
        
        prop = addProperty("currentTask");
        prop.setCategory(CATEGORY);
        
        prop = addProperty("actionOutput");
        prop.setCategory(CATEGORY);
        
        //TODO add getter/setter to the ExerciseActionEvent.metadata
        //prop = addProperty("metadata").setCategory(CATEGORY);
        //prop.setCategory(CATEGORY);
    }

}
