import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFile{
    public ArrayList<Integer> readFileInteger(String doc){
        try {
            File myObj = new File(doc);
            Scanner myReader = new Scanner(myObj);
            ArrayList<Integer> arrayList = new ArrayList<>();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                arrayList.add(Integer.valueOf(data));
            }
            myReader.close();
            return arrayList;
        } catch (
                FileNotFoundException e) {
            System.out.println("Файл с данным именем не найден...");
            e.printStackTrace();
        }
        return null;
    }
    public ArrayList<String> readFileString(String doc){
        try {
            File myObj = new File(doc);
            Scanner myReader = new Scanner(myObj);
            ArrayList<String> arrayList = new ArrayList<>();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                arrayList.add(data);
            }
            myReader.close();
            return arrayList;
        } catch (
                FileNotFoundException e) {
            System.out.println("Файл с данным именем не найден...");
            e.printStackTrace();
        }
        return null;
    }

}
