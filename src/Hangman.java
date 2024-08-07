import java.util.Scanner;
import java.util.Random;

public class Hangman {
    private static final String[] words = {"человек", "работа", "вопрос", "сторона", "страна", "случай", "голова", "ребенок", "система", "отношение", "женщина", "деньги", "машина", "проблема", "решение", "история", "власть", "тысяча", "возможность", "результат", "область", "статья", "компания", "группа", "развитие", "процесс", "условие", "средство", "начало", "уровень", "минута", "качество", "программирование", "тестировщик"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput;

        while (true) {
            System.out.print("Начать игру или выйти? (да/нет): ");
            userInput = scanner.nextLine().toLowerCase();

            if (userInput.equals("да")) {
                String word = getRandomWord();
                int state = 0;
                int index = 0;
                int tries = 0;
                char[] hidingWord = new char[word.length()];
                char[] lettersUsed = new char[33];
                char userLetter;


                while (true) {

                    if (isLose(state)) {
                        showUserState(state);
                        showFarewell(word);
                        break;
                    }

                    showInformation(state, word, hidingWord, lettersUsed);
                    userLetter = getUserInput(scanner);

                    if (isLetterInWord(word, userLetter)) {
                        if (isLetterInUsedLetters(hidingWord, userLetter)) {
                            state++;
                            System.out.println("\nТакая буква уже есть в слове!");
                        } else {
                            changeHidingWord(word, hidingWord, userLetter);
                        }
                    } else {
                        state++;
                        if (isLetterUsed(lettersUsed, userLetter)) {
                            System.out.println("\nЭту букву вы уже использовали!");
                        } else {
                            addUsedLetterInArray(lettersUsed, index, userLetter);
                            index++;
                        }
                    }
                    tries++;

                    if (isWin(word, hidingWord, state)) {
                        System.out.println("Поздравляю. Вы выиграли! Потрачено попыток: " + tries);
                        break;
                    }
                }
            } else if (userInput.equals("нет")) {
                System.out.println("Вы вышли из игры.");
                break;
            }
        }
    }

    private static char getUserInput(Scanner scanner) {
        System.out.print("\nВведите предполагаемую букву: ");
        return scanner.next().toLowerCase().charAt(0);
    }


    private static String getRandomWord() {
        Random random = new Random();
        return words[random.nextInt(words.length)];
    }


    private static StringBuilder getHidingWord(String word, char[] hidingWord, int state) {
        if (hidingWord[0] == 0 && state < 1) {
            for (int i = 0; i < word.length(); i++) {
                hidingWord[i] = '_';
            }
        }

        StringBuilder hidingWordString = new StringBuilder();
        for (char letter : hidingWord) {
            hidingWordString.append(letter);
        }
        return hidingWordString;
    }

    private static boolean isLetterInWord(String word, char userLetter) {
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == userLetter) {
                return true;
            }
        }
        return false;
    }

    private static boolean isLetterUsed(char[] lettersUsed, char userLetter) {
        for (char letter : lettersUsed) {
            if (letter == userLetter) {
                return true;
            }
        }
        return false;
    }

    private static boolean isLetterInUsedLetters(char[] lettersUser, char userLetter) {
        for (char letter : lettersUser) {
            if (letter == userLetter) {
                return true;
            }
        }
        return false;
    }

    private static boolean isLose(int state) {
        return state == 6;
    }

    private static boolean isWin(String word, char[] hidingWord, int state) {
        return getHidingWord(word, hidingWord, state).toString().equals(word);
    }

    private static void changeHidingWord(String word, char[] hidingWord, char userLetter) {
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == userLetter) {
                hidingWord[i] = userLetter;
            }
        }
    }

    private static void addUsedLetterInArray(char[] lettersUsed, int index, char userLetter) {
        lettersUsed[index] = userLetter;
    }

    private static void showFarewell(String word) {
        System.out.println("К сожалению вы проиграли!");
        System.out.println("А загаданным словом было: " + word);
    }

    public static void showLettersUsed(char[] lettersUsed) {
        System.out.print("Ипользованные буквы: ");
        for (char letter : lettersUsed) {
            if (letter != 0) {
                System.out.print(letter + " ");
            }
        }
        if (lettersUsed[0] == 0) {
            System.out.print("отсутствуют");
        }
    }

    private static void showUserState(int state) {
        StringBuilder currentState = new StringBuilder("\nТекущее состояние:\n_____________\n▉           | ");
        switch (state) {
            case 0 -> currentState.append("\n▊\n▊\n▊\n▊\n▊▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁");
            case 1 -> currentState.append("\n▊           0\n▊\n▊\n▊\n▊▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁");
            case 2 -> currentState.append("\n▊           0\n▊           |\n▊\n▊\n▊▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁");
            case 3 -> currentState.append("\n▊           0\n▊          /|\n▊\n▊\n▊▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁");
            case 4 -> currentState.append("\n▊           0\n▊          /|\\\n▊\n▊\n▊▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁");
            case 5 ->
                    currentState.append("\n▊           0\n▊          /|\\\n▊          /\n▊\n▊▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁");
            case 6 ->
                    currentState.append("\n▊           0\n▊          /|\\\n▊          / \\\n▊\n▊▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁");
        }
        System.out.println(currentState);
    }

    private static void showInformation(int state, String word, char[] hidingWord, char[] lettersUsed) {
        showUserState(state);
        showHiddenWord(word, hidingWord, state);
        showLettersUsed(lettersUsed);

    }

    private static void showHiddenWord(String word, char[] hidingWord, int state) {
        System.out.println("Загаданное слово: " + getHidingWord(word, hidingWord, state));
    }
}
