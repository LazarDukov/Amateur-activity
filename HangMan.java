import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HangMan {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("                          Hello! \n" +
                "               You can choose your word now!");
        String word = scanner.nextLine();
        String searchedWord = word;
        int mistake = 0;
        StringBuilder hideWord = new StringBuilder(word);
        for (int i = 0; i < word.length(); i++) {  // WORD HIDING
            hideWord.replace(i, i + 1, "_");
        }
        System.out.println(String.format("%n%n%n%n%n%n%n%n%n%n%n%n%n%n%n%n%n%n%n%n%n%n%n%n-----------------%n" +
                "                |" + "      " + "             The word is: " + hideWord +
                "%n%n               You can make maximum 6 mistakes or you will be dead!\n      " +
                "You should use only one letter, lowercase letters and characters from 'a' to 'z'!\n           " +
                "                       Good Luck! %n " +
                "                       You can start choosing a letters!"));

        String symbolIn = scanner.nextLine();
        List<Character> symbolsOfWord = new ArrayList<>();
        List<Character> alreadyInpud = new ArrayList<>();

        for (int i = 0; i < word.length(); i++) {
            symbolsOfWord.add(i, word.charAt(i));
        }
        while (mistake < 7) {
            while (symbolIn.length() != 1 || (symbolIn.charAt(0) < 97 || symbolIn.charAt(0) > 122)) {
                System.out.println("            You should use only one letter and it should be from 'a' to 'z' character!");
                symbolIn = scanner.nextLine();
            }
            int isValidSymbol = 0;
            if (alreadyInpud.size() >= 1) {
                for (int i = 0; i < alreadyInpud.size(); i++) {
                    String toCheck = alreadyInpud.get(i).toString();
                    if (symbolIn.equals(toCheck)) {

                        isValidSymbol = -1;
                        break;
                    }
                }
            }
            alreadyInpud.add(symbolIn.charAt(0));
            for (int i = 0; i < symbolsOfWord.size(); i++) {
                if (isValidSymbol == -1) {
                    break;
                }
                char symbolChecking = symbolsOfWord.get(i);
                String symbolForEqual = Character.toString(symbolChecking);
                if (symbolIn.contains(symbolForEqual)) {
                    char symbolToChange = symbolsOfWord.get(i);
                    String symbolToChangeConvert = Character.toString(symbolToChange);
                    hideWord.replace(i, i + 1, symbolToChangeConvert);
                    isValidSymbol += 1;
                }

            }
            if (!hideWord.toString().contains("_")) {
                System.out.println("                                CONGRATULATIONS! \n                        " +
                        "       You won the game! \n                            " +
                        "The searched word is: " + hideWord);
                break;
            }
            if (isValidSymbol == 0) {
                if (mistake == 6) {
                    mistake++;
                    System.out.println(man(mistake, hideWord, searchedWord));
                } else {
                    mistake++;
                    System.out.println(man(mistake, hideWord, searchedWord) + "\n                   Mistake!");
                }
            } else if (isValidSymbol == -1) {
                System.out.println("            You already input this letter! Try another letter!");
            } else {
                System.out.println(man(mistake, hideWord, searchedWord));
            }
            symbolIn = scanner.nextLine();
        }

    }

    public static String man(int counter, StringBuilder word, String searchedWord) {
        String searched = searchedWord;
        String man = "";
        char leftSlash = 92;
        char straightSlash = 124;
        char rightSlash = 47;
        if (counter == 7) {
            man = String.format("-----------------%n" + "                |%n" + "                O " + "      " + "             The word is: " + word + "%n" +
                    "               " + leftSlash + straightSlash + rightSlash + "%n" +
                    "                " + straightSlash + "%n" +
                    "               " + rightSlash + " " + leftSlash);

            return man +
                    "\n             The searched word is " + "\"" + searched + "\"" + " but you didn't find it!\n                             GAME OVER!";

        } else if (counter == 6) {
            man = String.format("-----------------%n" + "                |%n" + "                O " + "      " + "             The word is: " + word + "%n" +
                    "               " + leftSlash + straightSlash + rightSlash + "%n" +
                    "                " + straightSlash + "%n" +
                    "               " + rightSlash);
            return man;
        } else if (counter == 5) {
            man = String.format("-----------------%n" + "                |%n" + "                O " + "      " + "             The word is: " + word + "%n" +
                    "               " + leftSlash + straightSlash + rightSlash + "%n" +
                    "                " + straightSlash);
            return man;
        } else if (counter == 4) {
            man = String.format("-----------------%n" + "                |%n" + "                O " + "      " + "             The word is: " + word + "%n" +
                    "               " + leftSlash + straightSlash + rightSlash);
            return man;
        } else if (counter == 3) {
            man = String.format("-----------------%n" + "                |%n" + "                O " + "      " + "             The word is: " + word + "%n" +
                    "               " + leftSlash + straightSlash);
            return man;
        } else if (counter == 2) {
            man = String.format("-----------------%n" + "                |%n" + "                O " + "      " + "             The word is: " + word + "%n" +
                    "                " + straightSlash);
            return man;
        } else if (counter == 1) {
            man = String.format("-----------------%n" + "                |%n" + "                O " + "      " + "             The word is: " + word + "%n");
            return man;
        } else if (counter == 0) {
            man = String.format("-----------------%n" + "                |%n" + "                 " + "      " + "             The word is: " + word + "%n");
            return man;
        }
        // String whatToReturn = String.format(man + "      " + word);
        return "You are dead!";
    }
}
