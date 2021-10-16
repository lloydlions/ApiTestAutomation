package utility;

import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.Iterator;

public class DataProviderClass {

    @DataProvider(name="getDataFromCsv")
    public Iterator<Object[]> getDataFromCsv() throws IOException {
        String path = System.getProperty("user.dir") + "/src/test/java/testdata/sample/sample.csv";
        return CSVDataProvider.parseCsvData(path);

    }

}
