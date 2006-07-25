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
public class ItemSeenEventBeanInfo extends ActivemathEventBeanInfo {

    private final String CATEGORY = "ItemSeenEvent";

    public ItemSeenEventBeanInfo() 
    {
        super(ItemSeenEvent.class);
        ExtendedPropertyDescriptor prop=null;

        prop = addProperty("itemId");
        prop.setCategory(CATEGORY);
        prop.setPropertyEditorClass(MBaseItemEditor.class);
        
        prop = addProperty("duration");
        prop.setCategory(CATEGORY);
   }

}
