import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class FilePartReaderTest {

    @Test
    public void testReadLinesBeforeSetup(){
        FilePartReader filePartReader = new FilePartReader();
        assertThrows(FileNotFoundException.class, filePartReader::readLines);
    }

    @Test
    public void testSetupFromLineLT1(){
        FilePartReader filePartReader = new FilePartReader();
        assertThrows(IllegalArgumentException.class, () -> {filePartReader.setUp("path", 12,-3);});
    }
    @Test
    public void testSetupToLineLTFromLine(){
        FilePartReader filePartReader = new FilePartReader();
        assertThrows(IllegalArgumentException.class, () -> {filePartReader.setUp("path", 12,6);});
    }
    @Test
    public void testReadLines1_2() throws FileNotFoundException {
        FilePartReader filePartReader = new FilePartReader();
        filePartReader.setUp("resource_test/test_data.txt",1,2);
        String str = filePartReader.readLines();
        assertEquals(str, "1a1\n" +
                "2b 2a");
    }
    @Test
    public void testReadLines3_4() throws FileNotFoundException {
        FilePartReader filePartReader = new FilePartReader();
        filePartReader.setUp("resource_test/test_data.txt",3,4);
        String str = filePartReader.readLines();
        assertEquals(str, "3c 3b 3a\n" +
                "4d 4cr 4bb4 4a");
    }
    @Test
    public void testReadLinesAll() throws FileNotFoundException {
            FilePartReader filePartReader = new FilePartReader();
            filePartReader.setUp("resource_test/test_data.txt",1,7);
            String str = filePartReader.readLines();
            assertEquals(str, "1a1\n" +
                    "2b 2a\n" +
                    "3c 3b 3a\n" +
                    "4d 4cr 4bb4 4a\n" +
                    "5e 5d 5c 5b 5ax\n" +
                    "6f 6ea 6d 6ca 6bb 6a\n" +
                    "7g 7f 7ea");
    }
    @Test
    public void testReadLinesPastEof() throws FileNotFoundException{
            FilePartReader filePartReader = new FilePartReader();
            filePartReader.setUp("resource_test/test_data.txt",7,7);
            String str = filePartReader.readLines();
            assertEquals(str, "7g 7f 7ea");
    }
}