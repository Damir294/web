package Service;

public interface FileService {


    void saveToJsonFile(Object object, String fileName);

    String readFromFile(String fileName);
}