package org.activemath.xlm.model;

import org.scre.evtgenerator.editor.AffectEditor;
import org.scre.evtgenerator.editor.CapesEditor;
import org.scre.evtgenerator.editor.CompetencyEditor;
import org.scre.evtgenerator.editor.DomainEditor;
import org.scre.evtgenerator.editor.MetacogEditor;
import org.scre.evtgenerator.editor.MotivationEditor;

import com.l2fprod.common.beans.BaseBeanInfo;
import com.l2fprod.common.beans.ExtendedPropertyDescriptor;

public class BeliefDescriptBeanInfo extends BaseBeanInfo {

    private final String CATEGORY = "BeliefDescriptor";

    public BeliefDescriptBeanInfo() {
        super(BeliefDescript.class);
        // TODO Auto-generated constructor stub
        ExtendedPropertyDescriptor prop=null;
        
        prop = addProperty("domainId");
        prop.setCategory(CATEGORY);
        prop.setPropertyEditorClass(DomainEditor.class);
        prop = addProperty("metacogId");
        prop.setCategory(CATEGORY);
        prop.setPropertyEditorClass(MetacogEditor.class);
        prop = addProperty("motivationId");
        prop.setCategory(CATEGORY);
        prop.setPropertyEditorClass(MotivationEditor.class);
        prop = addProperty("affectId");
        prop.setCategory(CATEGORY);
        prop.setPropertyEditorClass(AffectEditor.class);
        prop = addProperty("competencyId");
        prop.setPropertyEditorClass(CompetencyEditor.class);
        prop.setCategory(CATEGORY);
        prop = addProperty("capeId");
        prop.setCategory(CATEGORY);
        prop.setPropertyEditorClass(CapesEditor.class);
    }

}
