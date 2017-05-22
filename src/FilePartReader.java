import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class FilePartReader {
    private String filePath;
    private Integer fromLine;
    private Integer toLine;

    public FilePartReader(){
        this.filePath = "";
        this.fromLine = 0;
        this.toLine= 0;
    }
    public void setUp(String filePath, Integer fromLine, Integer toLine){
        this.filePath = filePath;
        this.fromLine = fromLine;
        this.toLine = toLine;
        if (toLine < fromLine || fromLine < 1) throw new IllegalArgumentException();
    }
    private String read() throws FileNotFoundException {
        Scanner scanner = new Scanner( new File(this.filePath));
        String text = scanner.useDelimiter("\\A").next();
        scanner.close();
        return text;
    }

    public String readLines() throws FileNotFoundException{
        try {
            String fileString = read();
            List<String> items = Arrays.asList(fileString.split("\n"));
            if (this.fromLine != null && this.toLine != null) {
                int i = this.fromLine--;
                int j = this.toLine--;
                if (this.fromLine == this.toLine) {
                    return items.get(this.fromLine--);
                }
                String stringToReturn = "";
                for (int a = this.fromLine; a < j; a++) {
                    String line = items.get(a) + "\n";
                    stringToReturn += line;
                }
                return stringToReturn.trim();
            }
            } catch(IOException ex){
                throw new FileNotFoundException();
            }
            return null;
    }
}
