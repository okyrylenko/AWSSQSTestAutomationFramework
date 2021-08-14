package core.helpers;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.commons.io.FilenameUtils;

public class LocalFileReader {

    private static String readFile(String location) throws IOException {
        return new String(Files.readAllBytes(Paths.get(location)));
    }

    public static String getCustomer() throws IOException {
        return readFile(FilenameUtils.separatorsToSystem("src/test/java/dataaccess/testdata/customer.json"));
    }

    public static String getPurchase() throws IOException {
        return readFile(FilenameUtils.separatorsToSystem("src/test/java/dataaccess/testdata/purachase.json"));
    }

}
