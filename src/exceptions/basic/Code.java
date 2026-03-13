package exceptions.basic;

import org.assertj.core.api.Fail;

import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Code {

    public String readDataFrom(FakeFile file) {
        boolean opened = false;
        try {
            file.open();
            opened = true;
            return file.read();


            }
         catch (Exception e) {
        return "some default value";
    }
    finally{
            if(opened){
                file.close();}}

    }

    public static Integer minimumElement(int[] integers) {
        if (integers == null || integers.length == 0) {
            throw new RuntimeException("IllegalArgumentException");
        }

        int minimumElement = integers[0];

        for (int current : integers) {
            if (current < minimumElement) {
                minimumElement = current;
            }
        }

        return minimumElement;
    }

    public static boolean containsSingleLetters(String s) {
        if(s == null || s.equals("")){
            return false;
        }
        int index = 0;

        try {
            while (index < s.length()) {
                if (s.charAt(index) == s.charAt(index + 1)){
                    return false;
                }

                index++;
            }
        } catch (Exception e) {
            return true;
        }

        return true;
    }
}
