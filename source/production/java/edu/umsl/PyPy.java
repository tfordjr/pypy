package edu.umsl;
import java.util.Scanner;

public class PyPy {
    public static void main(String[] args) {
        System.out.print("Princess Perly has been kidnapped by the magical Hydra! The kingdom is in chaos. " +
                "Now only PyPy — the bravest Knight of the country — can save the day!\n" +
                "How many heads will the magical Hydra have? ");
        int heads = inputValidate(0, 100);
        System.out.print("How many tails will the magical Hydra have? ");
        int tails = inputValidate(0, 100);
        printGameInfo();

        boolean gameOver = false;
        int S = 555, turnCount = 0;      // NUMBER OF MOVES REMAINING

        while (!gameOver) {           // MAIN GAME LOOP
            turnCount++;
            System.out.println("Hydra has " + heads + " heads and " + tails + " tails. " +
                    "What will Knight PyPy do? Select 1-7: ");
            int choice = inputValidate(1, 7, heads, tails);
            switch (choice) {
                case 1:
                    heads--;
                    heads++;
                    System.out.println("Much like Sisyphus endlessly pushing a boulder up the hill, PyPy cuts a head" +
                            " off of the Hydra and another grows back in its place.");
                    break;
                case 2:
                    tails--;
                    tails += 2;
                    System.out.println("PyPy chops off one tail and two sprout up in its place. Whoops!");
                    break;
                case 3:
                    heads -= 2;
                    System.out.println("PyPy valiantly cleaves two of Hydra's snarling maws. None grow in its place!");
                    break;
                case 4:
                    tails -= 2;
                    heads++;
                    System.out.println("PyPy dismembers two of Hydra's jagged tails and one head grows in its place");
                    break;
                case 5:
                    System.out.println(S);
                    turnCount--;
                    break;
                case 6:
                    printGameInfo();
                    turnCount--;
                    break;
                case 7:
                    gameOver = true;
                    turnCount--;
                    break;
            }
            if (heads == 0 && tails == 0)
                gameOver = true;
        }

        if (gameOver && heads == 0 && tails == 0) {                       // GAME OVER STATE!
            System.out.println("Knight PyPy has defeated the Hydra! Congratulations!\n" +
                    "You completed the challenge in " + turnCount + " turns, minimum turn count was " + S);
        }
        else {
            System.out.println("You have quit the game. Please play again!");
        }
    }

    public static void printGameInfo() {
        System.out.println("How do I play the game? PyPy has 4 gameplay options, and you have 3 help features:\n" +
                "\t- 1. PyPy will cut off one of Hydra's heads, Hydra will grow one head.\n" +
                "\t- 2. PyPy will cut off one of Hydra's tails, Hydra will grow two tails.\n" +
                "\t- 3. PyPy will cut off two of Hydra's heads, Hydra will do nothing\n" +
                "\t- 4. PyPy will cut off two of Hydra's tails, Hydra will grow one head\n" +
                "\t- 5. Print minimum number of turns required to solve the puzzle\n" +
                "\t- 6. Print game info\n" +
                "\t- 7. Quit the game");
    }

    public static int inputValidate(int min, int max) {    // VALIDATE HEADS/TAILS INPUT
        int input = 5000;
        boolean retry;
        do {
            Scanner sc = new Scanner(System.in);
            retry = false;

            try {
                input = sc.nextInt();
            } catch (Exception ex) {
                System.out.println("Exceptions handled.");
                retry = true;
            }
            if (input < min || input > max) {
                System.out.println("Input not accepted, please try again. ");
                retry = true;
            }
        } while (retry);
        return input;
    }

    public static int inputValidate(int min, int max, int heads, int tails) {    // VALIDATE GAMEPLAY INPUT
        int input = 5000;
        boolean retry;
        do {
            Scanner sc = new Scanner(System.in);
            retry = false;

            try {
                input = sc.nextInt();
            } catch (Exception ex) {
                System.out.println("Exceptions handled.");
                retry = true;
            }
            if (input < min || input > max) {
                System.out.println("Input not accepted, please try again. ");
                retry = true;
            }

            if (input == 1 && heads == 0 ) {
                System.out.println("Hydra has no heads, cannot perform this action. Please try again");
                retry = true;
            }
            else if (input == 2 && tails == 0 ) {
                System.out.println("Hydra has no tails, cannot perform this action. Please try again");
                retry = true;
            }
            else if (input == 3 && heads == 0 || input == 3 && heads == 1 ) {
                System.out.println("Hydra has " + heads + " head(s), cannot perform this action. Please try again");
                retry = true;
            }
            else if (input == 4 && tails == 0 || input == 4 && tails == 1 ) {
                System.out.println("Hydra has " + tails + " tail(s), cannot perform this action. Please try again");
                retry = true;
            }
        } while (retry);
        return input;
    }
}
