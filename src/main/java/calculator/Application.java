package calculator;
import camp.nextstep.edu.missionutils.Console;

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

        String[] arr = input.split("[,:]");
        int sum = 0;
        for(String s : arr){
            if(!s.isBlank()){
                sum += Integer.parseInt(s.trim());
            }
        }
        return sum;
    }
}
