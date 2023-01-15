package Service.iml;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileServiceImpl implements FileService {
    @Value("${path.to.data.file}")
    private String dataFilepath;

    @Override
    public void saveToJsonFile(Object object, String fileName) {
        Path path = Path.of(dataFilepath, fileName);
        try {
            String json = new ObjectMapper().writeValueAsString(object);
            Files.deleteIfExists(path);
            Files.createFile(path);
            Files.writeString(path, json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String readFromFile(String fileName) {
        try {
            return Files.readString(Path.of(dataFilepath, fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}