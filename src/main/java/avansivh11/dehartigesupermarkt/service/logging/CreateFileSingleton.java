package avansivh11.dehartigesupermarkt.service.logging;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class CreateFileSingleton {
    private static volatile CreateFileSingleton instance;
    private Path file;

    private CreateFileSingleton(){
        createFile();
    }

    public static CreateFileSingleton getInstance(){
        if(instance == null){
            synchronized (CreateFileSingleton.class){
                if(instance == null){
                    instance = new CreateFileSingleton();
                }
            }
        }

        return instance;
    }

    public Path getFile(){
        return file;
    }

    private void createFile() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd").format(new Date());
        file = Paths.get("src/output/Log"+ timeStamp +".txt");
        try {
            Files.createFile(file);
        }catch(FileAlreadyExistsException x){
            System.err.format("file named %s" +
                    " already exists%n", file);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
