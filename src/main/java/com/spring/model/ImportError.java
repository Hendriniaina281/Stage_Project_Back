package com.spring.model;

public class ImportError {
    private int rowNumber;
    private String message;

    public ImportError(int rowNumber, String message) {
        this.rowNumber = rowNumber;
        this.message = message;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Row: " + rowNumber + " - Message: " + message;
    }
}
