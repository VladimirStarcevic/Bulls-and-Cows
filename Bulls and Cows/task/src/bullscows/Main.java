package bullscows;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BullsAndCowsGame game = new BullsAndCowsGame();
        startGame(game, scanner);
    }


    public static void startGame(BullsAndCowsGame game, Scanner scanner) {
        int secretLength;
        int symbolRange;

        System.out.println("Input the length of the secret code:");
        String secretLengthInput = scanner.nextLine();

        // Validate secret code length input
        if (!isNumeric(secretLengthInput)) {
            System.out.printf("Error: \"%s\" isn't a valid number.\n", secretLengthInput);
            return;
        } else {
            secretLength = Integer.parseInt(secretLengthInput);
            if (secretLength < 1) {
                System.out.println("Error: the length of the secret code must be greater than 0.");
                return;
            }
        }

        System.out.println("Input the number of possible symbols in the code:");
        String symbolRangeInput = scanner.nextLine();


        if (!isNumeric(symbolRangeInput)) {
            System.out.printf("Error: \"%s\" isn't a valid number.\n", symbolRangeInput);
            return;
        } else {
            symbolRange = Integer.parseInt(symbolRangeInput);
            if (symbolRange < 1) {
                System.out.println("Error: the number of possible symbols must be greater than 0.");
                return;
            }
            if (symbolRange > 36) {
                System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
                return;
            }
        }


        if (secretLength > symbolRange) {
            System.out.printf("Error: it's not possible to generate a code with a length of %d with %d unique symbols.\n",
                    secretLength, symbolRange);
            return;
        }

        String secretCode = game.generateSecretCode(secretLength, symbolRange);
        String symbols = "0123456789abcdefghijklmnopqrstuvwxyz";
        String symbolSet = symbols.substring(0, symbolRange);
        String symbolRangeDisplay = getSymbolRangeDisplay(symbolRange, symbols);

        System.out.print("The secret is prepared: ");
        for (int i = 0; i < secretLength; i++) {
            System.out.print("*");
        }
        System.out.printf(" %s\n", symbolRangeDisplay);

        System.out.println("Okay, let's start a game!");

        int turn = 1;
        while (true) {
            System.out.printf("Turn %d:\n", turn);
            String guess = scanner.nextLine();

            if (!game.isValidGuess(guess, secretLength)) {
                System.out.println("Invalid input! Please enter a code with valid characters.");
                continue;
            }

            int[] result = game.evaluateGuess(guess);
            game.printGrade(result);

            if (result[0] == secretLength) {
                System.out.println("Congratulations! You guessed the secret code.");
                break;
            }

            turn++;
        }
    }

    public static boolean isNumeric(String str) {
        return str.matches("\\d+");
    }

    // Method to prepare the symbol range display
    public static String getSymbolRangeDisplay(int symbolRange, String symbols) {
        StringBuilder display = new StringBuilder();
        display.append("(");

        if (symbolRange <= 10) {
            display.append("0-").append(symbols.charAt(symbolRange - 1));
        } else if (symbolRange > 10) {
            display.append("0-9, a-").append(symbols.charAt(symbolRange - 1));
        }

        display.append(")");
        return display.toString();
    }
}


