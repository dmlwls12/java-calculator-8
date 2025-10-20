package calculator;
import camp.nextstep.edu.missionutils.Console;

public class Application {

    private static final String ERR_INVALID_FORMAT = "잘못된 입력입니다.";
    private static final String ERR_EMPTY_TOKEN = "빈 값은 허용되지 않습니다.";
    private static final String ERR_NUMBER_FORMAT = "숫자 형식에 오류가 있습니다.";
    private static final String ERR_NEGATIVE = "음수는 허용되지 않습니다.";

    public static void main(String[] args) {

        System.out.println("덧셈할 문자열을 입력해주세요.");
        try {
            String input = Console.readLine();
            int result = add(input);
            System.out.println("결과 : " + result);
        } finally {
            Console.close();
        }
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
                throw new IllegalArgumentException(ERR_INVALID_FORMAT);
            }

            String customSeparator = input.substring(2, newIndex);
            numbers = input.substring(newIndex + len);
            separator = escapeRegex(customSeparator);
        }

        String[] arr = numbers.split(separator);
        int sum = 0;
        for (String s : arr) {
            String value = s.trim();
            if (value.isBlank()) {
                throw new IllegalArgumentException(ERR_EMPTY_TOKEN);
            }

            if (!value.matches("-?\\d+")) {
                throw new IllegalArgumentException(ERR_NUMBER_FORMAT);
            }

            int n = Integer.parseInt(value);

            if (n < 0) {
                throw new IllegalArgumentException(ERR_NEGATIVE);
            }

            sum += n;
        }
        return sum;
    }

    private static String escapeRegex(String str) {
        String chars = "\\.^$|?*+()[]{}";
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (chars.indexOf(c) >= 0) {
                sb.append("\\");
            }
            sb.append(c);
        }
        return sb.toString();
    }
}
