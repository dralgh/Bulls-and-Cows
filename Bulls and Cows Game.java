package bullscows;

public class Main {
    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        int turnCount = 0;
        int desiredL;
        int posibleSymbols;
        System.out.println("Enter the desired length.");
        desiredL = numberCheck(scanner.nextLine());
        System.out.println("Enter the amount of possible symbols in the code");
        posibleSymbols = numberCheck(scanner.nextLine());
        while (desiredL == 0 || desiredL > posibleSymbols) {
            System.out.println("Error: Desired length has to be smaller " +
                    "or equal to the amount of possible symbols! Try again:");
            System.out.println("Enter the desired length.");
            desiredL = numberCheck(new java.util.Scanner(System.in).nextLine());
            System.out.println("Enter the amount of possible symbols in the code");
            posibleSymbols = numberCheck(new java.util.Scanner(System.in).nextLine());
        }
        String secret = Generator(desiredL, posibleSymbols);
        // number generated
        int stars = 0;
        System.out.print("The secret is prepared: ");
        do {
            System.out.print("*");
            stars += 1;
        } while (stars < desiredL);
        System.out.println(" " + printGenerator(posibleSymbols));
        // Printed information about number,announced start of the game.
        String ans;
        do {
            turnCount += 1;
            System.out.println("Turn " + turnCount + ":");
            ans = scanner.next();
            while (ans.length() != desiredL) {
                System.out.println("Answer needs to have 4 symbols, try again.");
                ans = scanner.next();
            }
            System.out.println(checkAnswer(answer(ans), secret));
        } while (checkAnswer(answer(ans), secret).charAt(7)
                != String.valueOf(desiredL).charAt(0)
                || checkAnswer(answer(ans), secret).charAt(9) != 'b');
        System.out.println("Congratulations! You guessed the secret code.");
    }

    private static int numberCheck(String stringToCheck) {
        boolean trigger = false;
        while (!stringToCheck.matches("\\d+")) {
            System.out.println("Error: Please input only a number!");
            stringToCheck = new java.util.Scanner(System.in).nextLine();
            trigger = true;
        }
        if (trigger){numberCheck(stringToCheck);}
        return Integer.parseInt(stringToCheck);
    }


    private static String printGenerator(int posibleSymbols) {
        if (posibleSymbols >= 1 && posibleSymbols <= 10) {
            String numLength = "0-" + (posibleSymbols - 1);
            return "(" + numLength + ")";
        } else if (posibleSymbols == 11) {
            String numLength = "0-9, a";
            return "(" + numLength + ")";
        } else if (posibleSymbols >= 11) {
            char desiredLN = (char) (posibleSymbols + 86);
            String numLength = "0-9, a-" + desiredLN;
            return "(" + numLength + ")";
        } else {
            return "ERROR: impossible amount of symbols";
        }
    }

    private static int[] answer(String temp) {
        int[] ans = new int[temp.length()];
        for (int i = 0; i < temp.length(); i++) {
            ans[i] = temp.charAt(i);
        }
        return ans;
    }

    private static String checkAnswer(int[] ans, String secret) {
        int cows = 0;
        int bulls = 0;
        for (int i = 0; i < secret.length(); i++) {
            if (ans[i] == secret.charAt(i)) {
                bulls++;
                cows--;
            }
            for (int j = 0; j < secret.length(); j++) {
                if (ans[i] == secret.charAt(j)) {
                    cows++;
                }
            }
        }
        String checked;
        if (bulls > 0 && cows == 0) {
            checked = "Grade: " + bulls + " bull(s).";
        } else if (cows > 0 && bulls == 0) {
            checked = "Grade: " + cows + " cow(s).";
        } else if (cows > 0 && bulls > 0) {
            checked = "Grade: " + bulls + " bull(s) and " + cows + " cow(s).";
        } else {
            checked = "none.    ";
        }
        return checked;
    }

    private static String Generator(int desiredLength, int posibleSymbols) {
        int characterNumberInt;
        StringBuilder r = new StringBuilder();
        while (r.length() < desiredLength) {
            double characterNumber = (Math.random() * (posibleSymbols));
            characterNumberInt = (int) characterNumber;
            if (characterNumberInt >= 0 && characterNumberInt <= 9) {
                char toBeAdded = (char) (characterNumberInt + 48);
                r.append(toBeAdded);
            } else if (characterNumberInt >= 10) {
                char toBeAdded = (char) (characterNumberInt + 87);
                r.append(toBeAdded);
            }
            r = removeDuplicate(r);
        }
        System.out.println("String returned:" + r);
        return r.toString();
    }

    private static StringBuilder removeDuplicate(StringBuilder str) {
        StringBuilder stringWithoutDupe = new StringBuilder();
        str.chars().distinct().forEach(c -> stringWithoutDupe.append((char) c));
        return stringWithoutDupe;
    }
}