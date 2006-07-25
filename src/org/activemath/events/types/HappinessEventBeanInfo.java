/**
 * Created on 04-Aug-2005
 * @author FTYK9
 */
package org.activemath.events.types;

import com.l2fprod.common.beans.ExtendedPropertyDescriptor;

/**
 * @author FTYK9
 *
 */
public class HappinessEventBeanInfo extends ActivemathEventBeanInfo {

    private final String CATEGORY = "HappinessEvent";

    public HappinessEventBeanInfo() 
    {
        super(HappinessEvent.class);
        ExtendedPropertyDescriptor prop=null;

        prop = addProperty("value");
        prop.setCategory(CATEGORY);
        
    }

}
