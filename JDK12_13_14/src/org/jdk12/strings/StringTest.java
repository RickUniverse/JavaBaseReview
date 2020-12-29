package org.jdk12.strings;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lijichen
 * @date 2020/8/9 - 19:43
 */
public class StringTest {
    public static void main(String[] args) {
        //
        var str = "world";
        String transform = str.transform(s -> "hello," + s).transform(String::toUpperCase);
        System.out.println(transform);

        //
        List<String> strings = Arrays.asList("qwe ", " sdfa", "d ddd", "eee");
        var aList = new ArrayList<String>();
        strings.stream().map(String::strip).forEach(s -> aList.add(s.transform(String::toUpperCase)
                .transform(t -> "hi," + t)));

        aList.forEach(StringTest::printTest);

        //printTest("sdffffffffffffffffffff");
        //
        var str2 = "asdf\n asdf\nwer\n";
        String intern = str2.indent(3);
        System.out.println(intern);

    }
//    static void printTest(String str){
//        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
//        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(System.out);
//        try (outputStreamWriter;inputStreamReader) {
//            //System.out.println(str);
//            outputStreamWriter.write(new String(str));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
        static void printTest(String str) {
            System.out.println(str+"33333");
        }
}
