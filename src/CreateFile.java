import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class CreateFile {
    public void createFileInteger(String doc, ArrayList<Integer> arrayList){
        try {
            File myObj = new File(doc);
            if (myObj.createNewFile()) {
                System.out.println("Файл создан: " + myObj.getName());
                FileWriter fr = new FileWriter(myObj, true);
                for (Integer arg: arrayList){
                    System.out.println(arg);
                    fr.write(arg.toString() + System.lineSeparator());
                }
                fr.close();
            } else {
                System.out.println("Произошла ошибка при создании файла...");
            }
        } catch (IOException e) {
            System.out.println("Произошла ошибка...");
            e.printStackTrace();
        }
    }
    public void createFileStrings(String doc, ArrayList<String> arrayList){
        try {
            File myObj = new File(doc);
            if (myObj.createNewFile()) {
                System.out.println("Файл создан: " + myObj.getName());
                FileWriter fr = new FileWriter(myObj, true);
                for (String arg: arrayList){
                    System.out.println(arg);
                    fr.write(arg + System.lineSeparator());
                }
                fr.close();
            } else {
                System.out.println("Произошла ошибка при создании файла...");
            }
        } catch (IOException e) {
            System.out.println("Произошла ошибка...");
            e.printStackTrace();
        }
    }
}

