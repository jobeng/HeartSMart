package model;

import java.util.List;

/**
 * Created by User on 2016-04-05.
 */
public class Question {

    private List<Evidence> evidencelist;

    public List<Evidence> getEvidencelist() {
        return evidencelist;
    }

    public void setEvidencelist(List<Evidence> evidenceList) {
        this.evidencelist = evidenceList;
    }
}
