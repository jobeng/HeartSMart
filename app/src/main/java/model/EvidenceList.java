package model;

import java.util.List;

/**
 * Created by Jessie Obeng on 2016-04-06.
 *
 * Evidence list makes a list of all the returns in the JSON evidence List Seciton
 *  Used by the List view to display the list
 */
public class EvidenceList {
    public List<Evidence> evidenceList;

    public EvidenceList(List<Evidence> evidenceList){
        this.evidenceList = evidenceList;
    }

    public void setEvidenceList(List<Evidence> evidenceList) {
        this.evidenceList = evidenceList;
    }
}
