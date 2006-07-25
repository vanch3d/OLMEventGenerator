/**
 * Created on 08-Aug-2005
 * @author FTYK9
 */
package org.activemath.content.items;

import org.activemath.content.metadata.Abstractness;
import org.activemath.content.metadata.CompetencyLevel;
import org.activemath.content.metadata.Difficulty;
import org.activemath.content.metadata.Representation;


/**
 * @author FTYK9
 *
 */
public class EditMetadata extends Metadata {

    public EditMetadata() {
        super(null,null);
    }
    
    /**
     * @return Returns the itemId.
     */
    public String getItemId() {
        return itemId;
    }

    /**
     * @param itemId The itemId to set.
     */
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    /**
     * @param abstractness The abstractness to set.
     */
    public void setAbstractness(Abstractness abstractness) {
        //this.abstractness = Abstractness.getByName(abstractness);
        this.abstractness = abstractness;
    }

    /**
     * @param competency The competency to set.
     */
    public void setCompetency(String competency) {
        //this.competency = competency;
    }

    /**
     * @param competencyLevel The competencyLevel to set.
     */
    public void setCompetencyLevel(CompetencyLevel competencyLevel) {
        this.competencyLevel = competencyLevel;
    }

    /**
     * @param difficulty The difficulty to set.
     */
    public void setDifficulty(Difficulty difficulty) {
        //this.difficulty = difficulty;
    }

    /**
     * @param exerciseType The exerciseType to set.
     */
    public void setExerciseType(String exerciseType) {
        this.exerciseType = exerciseType;
    }

    /**
     * @param interactivityLevel The interactivityLevel to set.
     */
    public void setInteractivityLevel(String interactivityLevel) {
        this.interactivityLevel = interactivityLevel;
    }

    /**
     * @param interactivityType The interactivityType to set.
     */
    public void setInteractivityType(String interactivityType) {
        this.interactivityType = interactivityType;
    }

    /**
     * @param learningContext The learningContext to set.
     */
    public void setLearningContext(String learningContext) {
        //this.learningContext = learningContext;
    }

    /**
     * @param representation The representation to set.
     */
    public void setRepresentation(Representation representation) {
        this.representation = representation;
    }

    /**
     * @param typicalLearningTime The typicalLearningTime to set.
     */
    public void setTypicalLearningTime(String typicalLearningTime) {
        //this.typicalLearningTime = typicalLearningTime;
    }

    

}
