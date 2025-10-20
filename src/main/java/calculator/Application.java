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

    public static int add(String input) {

        if (input == null || input.isBlank()) return 0;

        String numbers = input;
        String separator = "[,:]";

        if (input.startsWith("//")) {
            int newIndex = input.indexOf ("\n");
            int len = 1;

            if (newIndex < 0) {
                newIndex = input.indexOf ("\\n");
                len = 2;
            }

            if (newIndex < 0) {
                throw new IllegalArgumentException("잘못된 입력입니다.");
            }

            String customSeparator = input.substring(2, newIndex);
            numbers = input.substring(newIndex + len);
            separator = Pattern.quote(customSeparator);
        }

        String[] arr = numbers.split(separator);
        int sum = 0;
        for (String s : arr) {
            String value = s.trim();
            if (value.isBlank()) throw new IllegalArgumentException("빈 값은 허용되지 않습니다.");
            if (!value.matches("-?\\d+")) throw new IllegalArgumentException("숫자 형식에 오류가 있습니다.");
            int n = Integer.parseInt(value);
            if (n < 0) throw new IllegalArgumentException("음수는 허용되지 않습니다.");
            sum += n;
        }
        return sum;
    }
}
