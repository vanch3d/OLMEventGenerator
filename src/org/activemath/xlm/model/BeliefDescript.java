package org.activemath.xlm.model;

import java.util.Vector;

public class BeliefDescript {

    private String domainId;
    private String competencyId;
    private String motivationId;
    private String affectId;
    private String metacogId;
    private String capeId;
    
    public String getAffectId() {return affectId;}
    public String getCapeId() {return capeId;}
    public String getCompetencyId() {return competencyId;}
    public String getDomainId() {return domainId;}
    public String getMetacogId() {return metacogId;}
    public String getMotivationId() {return motivationId;}

    public void setAffectId(String affectId) {this.affectId = affectId;}
    public void setCapeId(String capeId) {this.capeId = capeId;}
    public void setCompetencyId(String competencyId) {this.competencyId = competencyId;}
    public void setDomainId(String domainId) {this.domainId = domainId;}
    public void setMetacogId(String metacogId) {this.metacogId = metacogId;}
    public void setMotivationId(String motivationId) {this.motivationId = motivationId;}
    /**
     * 
     */
    public BeliefDescript() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public BeliefDescript(Vector vec) {
        if (vec==null || vec.size()!=6) return;
        
        this.setDomainId((String)vec.get(0));
        this.setCapeId((String)vec.get(1));
        this.setCompetencyId((String)vec.get(2));
        this.setMotivationId((String)vec.get(3));
        this.setAffectId((String)vec.get(4));
        this.setMetacogId((String)vec.get(5));
    }

    protected String printable( String dimensionId)
    {
        if (dimensionId == null || dimensionId.equals(""))
            return " ";
        else
            return dimensionId;
    }

    public String toString()
    {
        return "(" + printable( metacogId) + "," + printable( motivationId)
                + "," + printable( affectId) + "," + printable( competencyId)
                + "," + printable( capeId) + "," + printable( capeId) + ")";
    }
    
    
}
