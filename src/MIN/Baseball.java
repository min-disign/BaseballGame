package MIN;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Baseball {
    public int strikeCount = 0;
    public int ballCount = 0;

    public Baseball() {
    }

    protected boolean validateInput(String input) {
        if (input.length() != 3) {
            System.out.println("잘못된 입력: 세 자리 숫자를 입력하세요.");
            return false;
        }

        for (char c : input.toCharArray()) {
            if (!Character.isDigit(c)) {
                System.out.println("잘못된 입력: 숫자만 입력하세요.");
                return false;
            }
        }

        Set<Character> uniqueDigits = new HashSet<>();
        for (char c : input.toCharArray()) {
            if (c == '0') {
                System.out.println("잘못된 입력: 0은 입력할 수 없습니다.");
                return false;
            }
            uniqueDigits.add(c);
        }

        if (uniqueDigits.size() != 3) {
            System.out.println("잘못된 입력: 중복된 숫자는 입력할 수 없습니다.");
            return false;
        }

        return true;
    }

    public int play() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Random random = new Random();
            Set<Integer> digits = new HashSet<>();


            while (digits.size() < 3) {
                digits.add(random.nextInt(10));
            }

            Integer[] digitArray = digits.toArray(new Integer[0]);
            int[] number = {digitArray[0], digitArray[1], digitArray[2]};

            String userInputStr;
            do {
                System.out.print("숫자를 하나씩 입력하세요: ");
                userInputStr = scanner.next();
            } while (!validateInput(userInputStr));

            // 유저 입력을 정수 배열로 변환
            int[] userInput = new int[3];
            for (int a = 0; a < 3; a++) {
                userInput[a] = Character.getNumericValue(userInputStr.charAt(a));
            }

            System.out.println("입력된 숫자: " + userInput[0] + " " + userInput[1] + " " + userInput[2]);

            int strikes = countStrike(number, userInput);
            int balls = countBall(number, userInput);
            strikeCount += strikes;
            ballCount += balls;

            System.out.println("스트라이크: " + strikes + ", 볼: " + balls);
            System.out.println("누적된 스트라이크: " + strikeCount + ", 누적된 볼: " + ballCount);


            if (strikeCount >= 3) {
                System.out.println("스트라이크가 3번 나와서 종료합니다.");
                break;
            }

            if (strikes == 3) {
                System.out.println("정답입니다!");
                break;
            }

            System.out.println("정답: " + number[0] + "" + number[1] + "" + number[2]);
        }

        scanner.close();
        return strikeCount;
    }

    private int countStrike(int[] number, int[] userInput) {
        int strike = 0;
        for (int i = 0; i < 3; i++) {
            if (number[i] == userInput[i]) {
                strike++;
            }
        }
        return strike;
    }

    private int countBall(int[] number, int[] userInput) {
        int ball = 0;
        for (int i = 0; i < 3; i++) {
            if (number[0] == userInput[i] && number[0] != userInput[0]) ball++;
            if (number[1] == userInput[i] && number[1] != userInput[1]) ball++;
            if (number[2] == userInput[i] && number[2] != userInput[2]) ball++;
        }
        return ball;
    }

    public static void main(String[] args) {
        Baseball game = new Baseball();
        game.play();
    }
}
