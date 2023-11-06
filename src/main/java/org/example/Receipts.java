package org.example;

public class Receipts {
    private int indexMonth;
    private String nameFile;

    public Receipts(int indexMonth, String nameFile) {
        this.indexMonth = indexMonth;
        this.nameFile = nameFile;
    }

    public String getNameFile() {
        return nameFile;
    }

    public int getIndexMonth() {
        return indexMonth;
    }
}
