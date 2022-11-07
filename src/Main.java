import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) throws IOException {

        Path fileName = Path.of("inputs//input_AoC_4b.txt");

        String s = Files.readString(fileName);

        String [] listOfStrings = s.split("\n\r");

        List<String []> listOfSplitedStrings = new ArrayList<>();

        for(String str : listOfStrings){
            //ovim izrazom rastavlja string po novom redu razmaku
            String [] listTemp = str.split("[\n ]");

            listOfSplitedStrings.add(listTemp);
        }

        int numOfValidPassport = 0;

        boolean byr = false;
        boolean iyr = false;
        boolean eyr = false;
        boolean hgt = false;
        boolean hcl = false;
        boolean ecl = false;
        boolean pid = false;


        for (String [] strin : listOfSplitedStrings){
            for(String st : strin){

                st = st.replaceAll("\r", "");
                st = st.replaceAll("\n", "");

                if(st.contains("byr")){
                    int byrInt = Integer.parseInt(st.substring(4,8));
                    if(byrInt >= 1920 && byrInt <= 2002)
                        byr = true;
                }

                else if(st.contains("iyr")){
                    int iyrInt = Integer.parseInt(st.substring(4,8));
                    if(iyrInt >= 2010 && iyrInt <= 2020)
                        iyr = true;
                }

                else if(st.contains("eyr")){
                    int eyrInt = Integer.parseInt(st.substring(4,8));
                    if(eyrInt >= 2020 && eyrInt <= 2030)
                        eyr = true;
                }

                else if(st.contains("hgt")){
                    String hgtString = st.substring(st.length()-2);
                    if(!hgtString.equals("cm") && !hgtString.equals("in")){
                        break;
                    }
                    int hgtInt = Integer.parseInt(st.substring(4,st.length()-2));
                    if(hgtString.equals("cm") && (hgtInt >= 150 && hgtInt <= 193)) hgt = true;
                    if(hgtString.equals("in") && (hgtInt >= 59 && hgtInt <= 76)) hgt = true;
                }

                else if(st.contains("hcl")){
                    if(st.charAt(4) != '#')break;
                    char [] controlChar = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
                    String controlString = st.substring(5);

                    int count = 0;
                    for(int i=0; i<controlString.length(); i++){
                        for(int j=0; j<controlChar.length; j++){
                            if(controlString.charAt(i) == controlChar[j]) count++;
                        }
                    }

                    if(count == 6) hcl = true;
                }

                else if(st.contains("ecl")){
                    List<String> controlString = new ArrayList<>();
                    controlString.add("amb");
                    controlString.add("blu");
                    controlString.add("brn");
                    controlString.add("gry");
                    controlString.add("grn");
                    controlString.add("hzl");
                    controlString.add("oth");

                    String color = st.substring(4);

                    for (String col : controlString){
                        if(col.equals(color)) ecl = true;
                    }
                }

                else if(st.contains("pid")){

                    String num = st.substring(4);

                    if(num.length() == 9) pid = true;
                }
            }



            if(byr && iyr && eyr && hgt && hcl && ecl && pid) numOfValidPassport++;

            byr = false;
            iyr = false;
            eyr = false;
            hgt = false;
            hcl = false;
            ecl = false;
            pid = false;


        }

        System.out.println(numOfValidPassport);
    }
}