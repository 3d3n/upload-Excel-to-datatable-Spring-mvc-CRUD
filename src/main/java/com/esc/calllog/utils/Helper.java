package com.esc.calllog.utils;

import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Helper {

    public static List<String[]> readXcelDocuments(InputStream file) throws Exception {

        ArrayList<String[]> trans = new ArrayList<String[]>();

        Workbook workbook = WorkbookFactory.create(file);

        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

        //Get first sheet from the workbook
        Sheet sheet = workbook.getSheetAt(0);

        for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {

            Row row = sheet.getRow(rowIndex);
            //creates a string array with the length of the last cell number
            String[] tran = new String[row.getLastCellNum()];

            for (int colIndex = 0; colIndex < row.getLastCellNum(); colIndex++) {

                Cell cell = row.getCell(colIndex);

                CellValue cellValue = evaluator.evaluate(cell);

                if (cellValue == null) {
                    tran[colIndex] = "";
                    continue;
                }

                switch (cellValue.getCellType()) {
                    case NUMERIC:
                        if (DateUtil.isCellDateFormatted(cell)) {
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
                            //for time
                            Date date =cell.getDateCellValue();
                            SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm:ss");
                            SimpleDateFormat formatYearOnly = new SimpleDateFormat("yyyy");
                            String dateStamp = formatYearOnly.format(date);

                            if (dateStamp.equals("1899")){
                                //Return "Time-only" value as String HH:mm:ss
                                tran[colIndex] =  formatTime.format(cell.getDateCellValue());
                            }else {
                                tran[colIndex] = String.valueOf(cell.getDateCellValue());
                                //tran[colIndex] = dateFormat.format(cell.getDateCellValue());
                            }
                            //tran[colIndex] = String.valueOf(cell.getDateCellValue());

                        } else {
                            tran[colIndex] = String.valueOf(cell.getNumericCellValue());
                        }
                        break;
                    case STRING:
                        tran[colIndex] = cell.getStringCellValue();
                        break;
                    case BLANK:
                        tran[colIndex] = "";

                    default:
                        throw new Exception("Error in Document at Row " + (rowIndex + 1 + ", Column " + (colIndex + 1)));
                }
            }
//            if (tran[0].trim().isEmpty())
//                break;
            trans.add(tran); //adds a record

        }
        //System.out.println("End");
        return trans;


    }


    public List<String[]> readAndInsert(File file) throws FileNotFoundException {


        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));

        List<String[]> data = new ArrayList<>();
        try {
            data = Helper.readXcelDocuments(bis);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Logger
        //LOGGER.info(AUDIT_UNIVERSE_SERVICE + ": size of data is ==> " + data.size());

        return data;

    }


    //format date
//    public static String dateFormatter(String date) throws ParseException {
//        System.out.println("\t"+date);
//        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(date);
//        System.out.println(date1);
//        return date1.toString();
//    }

//    //format date joda
//    public static LocalDate dateFormatter(String date) throws ParseException {
//        System.out.println("this is the date  "+ date);
//        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/YYYY");
//        LocalDate date1 =  LocalDate.parse(date, dateFormat);
//
//        System.out.println("Date after format  "+date1);
//
//        return date1;
//    }

    //format time
    public  static Time timeFormatter(String time) throws ParseException {
        //System.out.println("time before format  "+ time);

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

        java.util.Date d1 = dateFormat.parse(time);
        java.sql.Time ppstime = new java.sql.Time(d1.getTime());

        return ppstime;
    }
}
