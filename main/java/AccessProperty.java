import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;


public class AccessProperty {

    public static void main(String[] args) {
        ArrayList<String> multipleKeyWords = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        String keyWord;
        System.out.print("Enter the words you are looking in the text - > ");
        while(!(keyWord = input.nextLine()).contains("exit")){
            multipleKeyWords.add(keyWord);
        }
        FileReader fr = null;
        String line;
        // open the file
        try {
            fr = new FileReader("C:/Users/Emir/OneDrive/Documents/NewOne/Related_Text/src/main/resources/TestDrive1");
        } catch (FileNotFoundException e) {
            System.out.println("Can not open the file!");
            System.exit(404);
        }

        BufferedReader bfr = new BufferedReader(fr);
        // read the lines:
        ArrayList<String> keyWordLine = new ArrayList<>();
        ArrayList<String> nonKeyWordLine = new ArrayList<>();
        try {
            // next task is making the improvement and making the code cleaner
            while ((line = bfr.readLine()) != null) {
                line = line.trim().replaceAll("\\s*([,.])\\s*", "$1 ");
                int i = 0;
                for (int j = 0; j < multipleKeyWords.size(); j++) {
                    if (line.contains(multipleKeyWords.get(i))) {
                        keyWordLine.add(line);
                        break;
                    } else if( i == multipleKeyWords.size()-1){
                        nonKeyWordLine.add(line);
                    }
                    i++;
                }
            }
        } catch (IOException e) {
            System.out.println("Can not read the file!");
            System.exit(303);
        }

        // close the file
        try {
            fr.close();
        } catch (IOException e) {
            System.out.println("error can not close the file");
            System.exit(504);
        }

        final Comparator<String> JAVA_PREFIX_FIRST = Comparator.comparing(x -> !x.contains(multipleKeyWords.get(0)));
        keyWordLine.sort(JAVA_PREFIX_FIRST);
        keyWordLine.forEach(System.out::println);
        nonKeyWordLine.sort(JAVA_PREFIX_FIRST);
        nonKeyWordLine.forEach(System.out::println);
    }
}