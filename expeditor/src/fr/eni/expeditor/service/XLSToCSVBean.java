package fr.eni.expeditor.service;


import javax.ejb.Stateless;


import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.jboss.logging.Logger;

/**
 * Created by d1503betournej on 26/10/2016.
 */
@Stateless
public class XLSToCSVBean extends AbstractService {


    private static Logger LOGGER = Logger.getLogger(XLSToCSVBean.class.getName());


    public void xls(File inputFile, File outputFile) {
        // For storing data into CSV files
        StringBuffer data = new StringBuffer();
        try {

            Writer out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(outputFile), "UTF8"));


            // Get the workbook object for XLS file
            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(inputFile));
            // Get first sheet from the workbook
            HSSFSheet sheet = workbook.getSheetAt(0);
            Cell cell;
            Row row;
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

            // Iterate through each rows from first sheet
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                row = rowIterator.next();
                // For each row, iterate through each columns
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    cell = cellIterator.next();

                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_BOOLEAN:
                            data.append(cell.getBooleanCellValue() + ",");
                            break;

                        case Cell.CELL_TYPE_NUMERIC:
                            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                data.append(df.format(cell.getDateCellValue()) + ",");
                            } else {
                                data.append(cell.getNumericCellValue() + ",");
                            }
                            break;

                        case Cell.CELL_TYPE_STRING:
                            String value = cell.getStringCellValue();
                            if (value.contains("(") && value.contains(")")) {
                                data.append(value);
                            } else {
                                data.append(value + ",");
                            }
                            break;

                        case Cell.CELL_TYPE_BLANK:
                            data.append("" + ",");
                            break;

                        default:
                            data.append(cell + ",");
                    }

                }
                data.append('\n');
            }

            try {
                out.write(data.toString());
            } finally {
                out.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String xlsToCsv(String cheminXLS) {
        File inputFile = new File(cheminXLS);
        LOGGER.info(cheminXLS);

        String cheminCSV = cheminXLS.substring(0, cheminXLS.length() - 3) + "csv";
        LOGGER.info(cheminCSV);
        File outputFile = new File(cheminCSV);

        xls(inputFile, outputFile);
        return cheminCSV;
    }
}
