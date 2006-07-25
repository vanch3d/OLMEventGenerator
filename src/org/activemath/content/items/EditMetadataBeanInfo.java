/**
 * Created on 08-Aug-2005
 * @author FTYK9
 */
package org.activemath.content.items;

import org.scre.evtgenerator.editor.AbstractnessEditor;
import org.scre.evtgenerator.editor.CompetLevelEditor;
import org.scre.evtgenerator.editor.CompetencyEditor;
import org.scre.evtgenerator.editor.DifficultyEditor;
import org.scre.evtgenerator.editor.ExerciseTypeEditor;
import org.scre.evtgenerator.editor.RepresentationEditor;

import com.l2fprod.common.beans.BaseBeanInfo;

/**
 * @author FTYK9
 *
 */
public class EditMetadataBeanInfo extends BaseBeanInfo {

    private final String CATEGORY = "Metadata";
    
    public EditMetadataBeanInfo() {
        super(EditMetadata.class);
        // TODO Auto-generated constructor stub
        
        addProperty("itemId").setCategory(CATEGORY);
        addProperty("abstractness").setCategory(CATEGORY)
                .setPropertyEditorClass(AbstractnessEditor.class);
        addProperty("difficulty").setCategory(CATEGORY)
                .setPropertyEditorClass(DifficultyEditor.class);
        addProperty("competency").setCategory(CATEGORY)
                .setPropertyEditorClass(CompetencyEditor.class);
        addProperty("competencyLevel").setCategory(CATEGORY)
                .setPropertyEditorClass(CompetLevelEditor.class);
        addProperty("representation").setCategory(CATEGORY)
            .setPropertyEditorClass(RepresentationEditor.class);
        addProperty("exerciseType").setCategory(CATEGORY)
            .setPropertyEditorClass(ExerciseTypeEditor.class);
        addProperty("typicalLearningTime").setCategory(CATEGORY);
    }

}
