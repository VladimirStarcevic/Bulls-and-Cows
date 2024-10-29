package bullscows;


import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class BullsAndCowsGame {
    private String secretCode;
    private String availableSymbols;

    public int[] evaluateGuess(String guess) {
        int bulls = 0;
        int cows = 0;

        for (int i = 0; i < secretCode.length(); i++) {
            char guessChar = guess.charAt(i);
            char secretChar = secretCode.charAt(i);

            if (guessChar == secretChar) {
                bulls++;
            } else if (secretCode.indexOf(guessChar) != -1) {
                cows++;
            }
        }

        return new int[]{bulls, cows};
    }

    public boolean isValidGuess(String guess, int secretLength) {
        if (guess.length() != secretLength) {
            return false;
        }


        for (char c : guess.toCharArray()) {
            if (availableSymbols.indexOf(c) == -1) {
                return false;
            }
        }

        return true;
    }

    public String generateSecretCode(int secretLength, int symbolRange) {
        String symbols = "0123456789abcdefghijklmnopqrstuvwxyz";
        this.availableSymbols = symbols.substring(0, symbolRange);

        Random random = new Random();
        StringBuilder codeBuilder = new StringBuilder();
        Set<Character> uniqueChars = new HashSet<>();

        while (codeBuilder.length() < secretLength) {
            char symbol = availableSymbols.charAt(random.nextInt(availableSymbols.length()));
            if (uniqueChars.add(symbol)) {
                codeBuilder.append(symbol);
            }
        }

        secretCode = codeBuilder.toString();
        return secretCode;
    }

    public void printGrade(int[] result) {
        int bulls = result[0];
        int cows = result[1];

        if (bulls == 0 && cows == 0) {
            System.out.println("Grade: None.");
        } else if (bulls > 0 && cows == 0) {
            System.out.printf("Grade: %d bull%s.\n", bulls, bulls > 1 ? "s" : "");
        } else if (bulls == 0 && cows > 0) {
            System.out.printf("Grade: %d cow%s.\n", cows, cows > 1 ? "s" : "");
        } else {
            System.out.printf("Grade: %d bull%s and %d cow%s.\n",
                    bulls, bulls > 1 ? "s" : "", cows, cows > 1 ? "s" : "");
        }
    }


}
