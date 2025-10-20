package calculator;
import camp.nextstep.edu.missionutils.Console;

import java.util.regex.Pattern;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        System.out.println("덧셈할 문자열을 입력해주세요.");
        String input = Console.readLine();
        int result = add(input);
        System.out.println("결과 : " + result);

        Console.close();
    }

    public static int add(String input){

        if(input == null || input.isBlank()) return 0;

        String numbers = input;
        String separator = "[,:]";

        if(input.startsWith("//")){
            int newIndex = input.indexOf("\n");
            int len = 1;

            if(newIndex < 0){
                newIndex = input.indexOf("\\n");
                len = 2;
            }

            if(newIndex < 0) return 0;

            String customSeparator = input.substring(2, newIndex);
            numbers = input.substring(newIndex + len);
            separator = Pattern.quote(customSeparator);
        }

        String[] arr = numbers.split(separator);
        int sum = 0;
        for(String s : arr){
            if(!s.isBlank()){
                sum += Integer.parseInt(s.trim());
            }
        }
        return sum;
    }
}
