package Excel;

import Werkuren.WerkurenObj;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelFIle {

    private static String[] columns = {
            "timeSpendInMinutes", "WERKNEMER","WERKBONNUMMER", "DATUM", "BEGINTIJDH", "BEGINTIJDM60", "EINDTIJDH", "EINDTIJDM60"};


    public void ExcelFIle(){};





    public void CreateFile2(List<WerkurenObj> werkurenObjs) throws IOException {
        Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

        CreationHelper createHelper = workbook.getCreationHelper();

        // Create a Sheet
        Sheet sheet = workbook.createSheet("sheet1");

        // Create a Font for styling header cells
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());

        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // Create a Row
        Row headerRow = sheet.createRow(0);

        // Create cells
        for(int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

        // Create Cell Style for formatting Date
        CellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

        // Create Other rows and cells with employees data
        int rowNum = 1;
        for(WerkurenObj obj: werkurenObjs) {
            Row row = sheet.createRow(rowNum++);



            row.createCell(0)
                    .setCellValue(obj.getTimeSpendInMinutes());

            row.createCell(1)
                    .setCellValue(obj.getWERKNEMER());

            row.createCell(2)
                    .setCellValue(obj.getWERKBONNUMMER());


            row.createCell(3)
                    .setCellValue(obj.getDATUM() );


            row.createCell(4)
                    .setCellValue(obj.getBEGINTIJDH());


            row.createCell(5)
                    .setCellValue(obj.getBEGINTIJDM60() );

            row.createCell(6)
                    .setCellValue(obj.getEINDTIJDH() );

            row.createCell(7)
                    .setCellValue(obj.getEINDTIJDM60());
        }

        // Resize all columns to fit the content size
        for(int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }


        createFile(workbook);

        workbook.close();
    }

    private void createFile(Workbook workbook) throws IOException {



        File f = new File( Parameters.Parameters.getPathToSaveFile());

        if(!f.exists()) {
            f.createNewFile();
        }else {
            f = new File("this_year_only_2.xlsx");
            f.createNewFile();
        }

        FileOutputStream fileOut = new FileOutputStream(f);



        workbook.write(fileOut);
        fileOut.close();
    }

}


