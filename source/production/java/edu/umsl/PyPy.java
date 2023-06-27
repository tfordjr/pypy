// Terry Ford Jr - Project 4 - Knight PyPy and The Magical Hydra! - CS 2261
// I'm very proud of how the project turned out! Short and efficient, and I was able to express S algebraically
// Let me know if there is anything I can improve on @ tgfkf9@umsystem.edu Thank you!

package edu.umsl;
import java.util.Scanner;

public class PyPy {
    public static void main(String[] args) {
//        String input = "hello";
//        System.out.println(input[0]);

        System.out.print("Princess Perly has been kidnapped by the magical Hydra! The kingdom is in chaos. " +
                "Now only PyPy — the bravest Knight of the country — can save the day!\n" +
                "How many heads will the magical Hydra have? ");
        int heads = inputValidate(0, 1000, 50, 50);
        System.out.print("How many tails will the magical Hydra have? ");
        int tails = inputValidate(0, 1000, 50, 50);
        printGameInfo();
        boolean gameOver = false;
        int turnCount = 0, S = calculateS(heads, tails);     // S calculation

        while (!gameOver) {           // MAIN GAME LOOP
            turnCount++;
            System.out.println("Hydra has " + heads + " heads and " + tails + " tails. " +
                    "What will Knight PyPy do on turn " + turnCount + "? Select 1-7: ");
            int choice = inputValidate(1, 7, heads, tails);
            switch (choice) {
                case 1:
                    System.out.println("Much like Sisyphus endlessly pushing a boulder up the hill, PyPy cuts a head" +
                            " off the grotesque mythical creature and another grows back in its place.");
                    break;
                case 2:
                    tails++;
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
                    System.out.println("This Hydra can be slain in " + S + " turns.");
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
        }                                    // GAME OVER STATE
        if (heads == 0 && tails == 0) {      // PLAYER WIN
            System.out.println("\tKnight PyPy has defeated the Hydra! Congratulations!\n" +
                    "You completed the challenge in " + turnCount + " turns, minimum turn count achievable was " + S);
        }
        else {                               // PLAYER QUIT
            System.out.println("You have quit the game. Please play again!");
        }
    }

    public static int calculateS(int h, int t) { // S Calculation
        int S = (h/2) + 3 * (t/4);               // This calculation says it takes 1 turn to remove 2 heads
        if (h % 2 == 1 && t == 0)                // And 3 turns to remove 4 tails. ( Moves 4, 4, 3 )
            S = -1;                              // Thanks to integer division, it considers all but the last few turns
        else if (h % 2 == 0 && t % 4 != 0)       // It took me a ton of brainstorming to express S algebraically
            S += 7 - (t % 4);                    // I'm very proud of that. In a previous version, calculateS()
        else if (h % 2 == 1 && t % 4 == 1)       // was a long while loop with a counter, that played out the game
            S += 3;                              // for you, and returned the turn counter as S.
        else if (h % 2 == 1 && t % 4 == 2)       // It worked, but this is significantly more efficient and shorter.
            S += 2;
        else if (h % 2 == 1 && t % 4 == 3)
            S += 8;
        return S;
    }

    public static void printGameInfo() {     // PRINT INSTRUCTIONS METHOD
        System.out.println("How do I play the game? PyPy has 4 gameplay options, and you have 3 help features:\n" +
                "\t- 1. PyPy will cut off one of Hydra's heads, Hydra will grow one head\n" +
                "\t- 2. PyPy will cut off one of Hydra's tails, Hydra will grow two tails\n" +
                "\t- 3. PyPy will cut off two of Hydra's heads, Hydra will do nothing\n" +
                "\t- 4. PyPy will cut off two of Hydra's tails, Hydra will grow one head\n" +
                "\t- 5. Print minimum number of turns required to solve the puzzle\n" +
                "\t- 6. Print game instructions\n" +
                "\t- 7. Quit the game");
    }

    public static int inputValidate(int min, int max, int heads, int tails) { // VALIDATES HEADS/TAILS & GAMEPLAY INPUT
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
                                                            // THIS SECTION EXCLUSIVELY USED FOR GAMEPLAY INPUT
            if (input == 1 && heads == 0 ) {                // I ENSURED IT NEVER INTERFERES WITH HEADS/TAILS INPUT
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
