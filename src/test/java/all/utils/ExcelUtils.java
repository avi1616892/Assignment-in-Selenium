package all.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelUtils {

    /**
     * Reads an Excel file and extracts its content into a List of List of Strings.
     * Each row in the Excel sheet is represented by a List of Strings, and all rows are
     * combined into a List.
     *
     * @param filePath The path to the Excel file to be read.
     * @return A List of List of Strings containing the data from the first sheet of the Excel file.
     */
    public static List<List<String>> readExcelFile(String filePath) {
        List<List<String>> data = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            // Access the first sheet of the workbook
            Sheet sheet = workbook.getSheetAt(0);

            // Skip the first row (header)
            boolean isFirstRow = true;

            // Iterate through each row in the sheet
            for (Row row : sheet) {
                if (isFirstRow) {
                    isFirstRow = false;
                    continue; // Skip the first row, as it is assumed to be the header
                }

                List<String> rowData = new ArrayList<>();

                // Iterate through each cell in the row
                for (Cell cell : row) {
                    switch (cell.getCellType()) {
                        case STRING:
                            rowData.add(cell.getStringCellValue());
                            break;
                        case NUMERIC:
                            rowData.add(String.valueOf(cell.getNumericCellValue()));
                            break;
                        case BOOLEAN:
                            rowData.add(String.valueOf(cell.getBooleanCellValue()));
                            break;
                        default:
                            rowData.add(""); // Add an empty string for unsupported cell types
                    }
                }
                // Add the row data to the main data list
                data.add(rowData);
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle any IO exceptions that occur during file reading
        }

        return data; // Return the extracted data
    }
}
