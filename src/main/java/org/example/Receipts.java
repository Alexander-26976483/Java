package org.example;

public class Receipts {
    private int indexMonth;
    private String nameFile;
    private String nameService;

    public Receipts(int indexMonth, String nameFile, String nameService) {
        this.indexMonth = indexMonth;
        this.nameFile = nameFile;
        this.nameService = nameService;
    }

    public String getNameFile() {
        return nameFile;
    }

    public int getIndexMonth() {
        return indexMonth;
    }

    public String getNameService() {
        return nameService;
    }
}
