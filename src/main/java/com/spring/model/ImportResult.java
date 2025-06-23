package com.spring.model;

import java.util.List;

public class ImportResult {
    private List<Csvmatiereclasse> csvMatiereList;
    private List<ImportError> errorList;
    
    public List<Csvmatiereclasse> getCsvMatiereList() {
        return csvMatiereList;
    }
    public void setCsvMatiereList(List<Csvmatiereclasse> csvMatiereList) {
        this.csvMatiereList = csvMatiereList;
    }
    public List<ImportError> getErrorList() {
        return errorList;
    }
    public void setErrorList(List<ImportError> errorList) {
        this.errorList = errorList;
    }

    
}
