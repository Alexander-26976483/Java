package org.example;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    private static final String[] month_name = {"январь", "февраль", "март", "апрель",
            "май", "июнь", "июль", "август", "сентябрь", "октябрь", "ноябрь", "декабрь"};

    private static final String[] service_names = {"газоснабжение", "ГВС", "домофон", "капремонт",
            "квартплата", "ТБО", "теплоснабжение", "ХВС", "электроснабжение"};

    public static void main(String[] args) {

        Map<String, Integer> months = new HashMap<>();
        Map<String, Month> monthsWithServices = new HashMap<>();

        for (int i = 0; i < month_name.length; i++) {
            months.put(month_name[i], i);
            monthsWithServices.put(month_name[i], new Month(month_name[i]));
            for (int j = 0; j < service_names.length; j++) {
                monthsWithServices.get(month_name[i]).setNamesOfServicesCheck(service_names[j], false);
            }
        }

        ArrayList<Receipts> receipts = new ArrayList<>();
        String name = "d:\\чеки.txt";
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get(name)), StandardCharsets.UTF_8))) {

            String line, extractedMonth = null;
            String[] extractedLines;

            while ((line = bufferedReader.readLine()) != null) {
                extractedLines = line.toLowerCase().split("_");
                if (extractedLines.length == 2) {
                    extractedMonth = extractedLines[1].split("\\.", 2)[0];
                }

                if (months.get(extractedMonth) != null) {
                    receipts.add(new Receipts(months.get(extractedMonth), line, extractedLines[0]));
                    monthsWithServices.get(extractedMonth).setNamesOfServicesCheck(extractedLines[0], true);
                }
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        try (FileOutputStream fos = new FileOutputStream("чеки_по_папкам.txt", true)) {
            Receipts[] arrReceipts = receipts.toArray(new Receipts[0]);
            bubbleSort(arrReceipts);

            String text;
            byte[] buffer;

            for (Receipts receipt : arrReceipts) {
                text = "/" + month_name[receipt.getIndexMonth()] + "/" + receipt.getNameFile() + "\n";
                buffer = text.getBytes();
                fos.write(buffer, 0, buffer.length);
            }

            buffer = "не оплачены:\n".getBytes();
            fos.write(buffer, 0, buffer.length);

            for (String s : month_name) {
                text = s + ":\n";
                buffer = text.getBytes();
                fos.write(buffer, 0, buffer.length);
                for (String serviceName : service_names) {
                    if (!(monthsWithServices.get(s).getNamesOfServicesCheck(serviceName))) {
                        text = serviceName + "\n";
                        buffer = text.getBytes();
                        fos.write(buffer, 0, buffer.length);
                    }
                }
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void bubbleSort(Receipts[] array) {
        boolean sorted = false;
        Receipts temp;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i].getIndexMonth() > array[i + 1].getIndexMonth()) {
                    temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    sorted = false;
                }
            }
        }
    }
}