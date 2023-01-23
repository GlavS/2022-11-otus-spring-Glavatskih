package ru.glavs.hw005.service.display;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class SandboxTODELETE {


    @Test
    public void SANDBOX(){
        String longString = "";

        List<String> splittedText = List.of(longString.split(" "));
        List<String> text = new ArrayList<>();
        int counter = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < splittedText.size(); i++) {
            String word = splittedText.get(i).strip();
            counter += (word.length() + 1);
            if(counter > 50){
                text.add(sb.toString());
                sb.setLength(0);
                counter = 0;
            }
            sb.append(word).append(" ");
        }
        text.forEach(System.out::println);
    }
    @Test
    public void sandbox2(){
        int counter = 0;
        int countlines = 10;
        for (int i = 0; i < 20; i++) {
            for (int j = counter; ; j++) {
                if (j == countlines) break;
                System.out.print(j);
                System.out.print(" ");
            }
            System.out.println();
            counter += 10;
            countlines += 10;
        }
    }


    @Configuration
    public static class LocalConfig{
    }
}
