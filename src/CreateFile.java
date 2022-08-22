import java.io.File;
import java.io.IOException;

public class CreateFile {
    public void createFile(String doc){
        try {
            File myObj = new File(doc);
            if (myObj.createNewFile()) {
                System.out.println("Файл создан: " + myObj.getName());

            } else {
                System.out.println("Произошла ошибка при создании файла...");
            }
        } catch (IOException e) {
            System.out.println("Произошла ошибка...");
            e.printStackTrace();
        }
    }
}

