/**
 * Created on 04-Aug-2005
 * @author FTYK9
 */
package org.activemath.events.types;

import org.scre.evtgenerator.editor.MetadataEditor;
import org.scre.evtgenerator.editor.MBaseItemEditor;

import com.l2fprod.common.beans.ExtendedPropertyDescriptor;

/**
 * @author FTYK9
 *
 */
public class ExerciseStepEventBeanInfo extends ActivemathEventBeanInfo {

    private final String CATEGORY = "ExerciseStepEvent";


    public ExerciseStepEventBeanInfo()
    {
        super(ExerciseStepEvent.class);
        ExtendedPropertyDescriptor prop=null;
        
        prop = addProperty("itemId");
        prop.setCategory(CATEGORY);
        prop.setPropertyEditorClass(MBaseItemEditor.class);
        
        prop = addProperty("Metadata");
        prop.setCategory(CATEGORY);
        prop.setPropertyEditorClass(MetadataEditor.class);
        //prop.setReadOnly();

        prop = addProperty("UserInput");
        prop.setCategory(CATEGORY);
        //prop.setReadOnly();
        //prop.setPropertyEditorClass(SeasonEditor.class);
        
   }
}
