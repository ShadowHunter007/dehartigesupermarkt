package serviceTests;

import avansivh11.dehartigesupermarkt.service.logging.CreateFileSingleton;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileCreationTest {

    @Test
    public void CreateFileTest(){
        //Create file
        CreateFileSingleton.getInstance();

        //Name used to create file
        String timeStamp = new SimpleDateFormat("yyyyMMdd").format(new Date());
        File f = new File("src/output/Log"+ timeStamp +".txt");

        //Assert that file exists
        Assertions.assertThat(f.exists());
    }
}
