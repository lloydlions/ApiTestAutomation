package utility;

import com.opencsv.CSVReader;
import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class CSVDataProvider {

    private static CSVReader reader = null;
    private static Object[][] data = null;

    @Test
    static Iterator<Object[]> parseCsvData(String fileName) throws IOException, FileNotFoundException {
        BufferedReader input = null;
        File file = new File(fileName);
        input = new BufferedReader(new FileReader(file));
        String line = null;
        ArrayList<Object[]> data = new ArrayList<Object[]>();
        while ((line = input.readLine()) != null)
        {
            String in = line.trim();
            String[] temp = in.split(",");
            List<Object> arrray = new ArrayList<Object>();
            for(String s : temp)
            {
                arrray.add(s); //locations
            }
            data.add(arrray.toArray());
        }
        input.close();
        return data.iterator();
    }

}
