package all.tests;

import org.testng.annotations.DataProvider;
import all.utils.ExcelUtils;

import java.util.List;

public class TestDataProvider {

    /**
     * Provides data from an Excel file for the email tests.
     *
     * @return A 2D Object array representing the data from the Excel sheet.
     * Each row in the Excel sheet corresponds to one row in the 2D array.
     */
    @DataProvider(name = "emailData")
    public Object[][] getData() {
        // Reading the Excel file and converting it to a List of List of Strings
        List<List<String>> excelData = ExcelUtils.readExcelFile("./Data.xlsx");

        // Creating a 2D array to hold the data
        Object[][] data = new Object[excelData.size()][];

        // Populating the 2D array with data from the Excel file
        for (int i = 0; i < excelData.size(); i++) {
            data[i] = excelData.get(i).toArray(new Object[0]);
        }

        return data;
    }
}
