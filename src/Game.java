/* Игра виселица - разгадывать слова вводя с клавиатуры буквы
1.Нужно написать минимально работающую версию этой игры
запуск -> предлоежние начать новую игру или выйти - вывод загаданного слова - предложение ввести 1 букву с клавиатуры
 - проверка на вхождение этой буквы в загаданное слово(если входит, то отобразить эту букву в слове.
 Если не входит, показать ошибку и вывести на экран картинку)
 изначально упрощенный вариант (без проверок, без скрытия буквы, без рисования)
нужно написать методы:
Считать список слов из файла(через filereader), это будет список(arraylist)
слова должны доставаться рандомно



 2.Добавить цикл для перезапуска игры

*/

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Game {
    private static final List<String> words = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static String secretWord;
    private static String maskSecretWord;
    private static final int MAX_ERRORS = 6;
    private static int errors = 0;

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Добро пожаловать в игру \"Виселица\" \nВведите: ");
        while (true) {
            startGame();
        }

    }


    public static void gameLoop(String currentMask, Set<Character> usedLetters) throws FileNotFoundException {
        while (currentMask.contains("*")) {
            currentMask = makePlayerTurn(currentMask,usedLetters);
            System.out.println("Текущее состояние: " + currentMask);
            if (isGameOver(currentMask, secretWord, errors)) {
                return;
            }
        }
    }
    public static void startGame() throws FileNotFoundException {
        System.out.println("[N]ew game чтобы начать или [E]xit чтобы выйти");
        String start = scanner.nextLine();
        //добавить зацикливание, если введена неверная буква
        if (start.toLowerCase().contains("n".toLowerCase())) {
            Set<Character> usedLetters = new HashSet<>();
            String[] gameData = readAndOutWord(words);
            String original = gameData[0];
            String currentMask = gameData[1];
            errors = 0;
            gameLoop(currentMask,usedLetters);

        } else if (start.toLowerCase().contains("e".toLowerCase())) {
            System.out.println("Вы не захотели играть");
        }
    }

    public static String[] readAndOutWord(List<String> words) throws FileNotFoundException {
        try (BufferedReader bf = new BufferedReader(new FileReader("src/words.txt"))) {
            String word;
            while ((word = bf.readLine()) != null) {
                words.add(word);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //Получение слова и маскируем под звездочки
        secretWord = getRandomIndexWord(words).toLowerCase();
        maskSecretWord = secretWord.replaceAll(".", "*");

        //вывод слова на экран
        System.out.println("Загаданное слово: " + maskSecretWord);
        return new String[]{secretWord, maskSecretWord};
    }

    public static String getRandomIndexWord(List<String> words) {
        //Генерирование случайного индекса для вывода слова из списка ArrayList
        Random random = new Random();
        int randomIndex = random.nextInt(words.size());
        return words.get(randomIndex);
    }


    public static String makePlayerTurn(String currentMask, Set<Character> usedLetters) throws FileNotFoundException {
        System.out.println("Введите букву: ");
        //ввод с консоли буквы
        String input = scanner.nextLine().trim().toLowerCase();
        //проверка буквы в слове
        if (input.isEmpty()) {
            System.out.println("Поле ввода не может быть пустым!");
            return currentMask;
        }
        if (input.length() > 1) {
            System.out.println("Введите только одну букву");
            return currentMask;
        }
        char letter = input.charAt(0);
        //проверка введенной буквы рус алфавита
        if (!Character.UnicodeBlock.of(letter).equals(Character.UnicodeBlock.CYRILLIC)) {
            System.out.println("Введите букву русского алфавита!");
            return currentMask;
        }
        if (!secretWord.contains(String.valueOf(letter))) {
            System.out.println("Буквы " + letter + " нет в слове!");
            checkErrors(secretWord,letter);
        }

        if(usedLetters.contains(letter)){
            System.out.println("Буква " + letter + " уже использовалась");
        }
        usedLetters.add(letter);
        System.out.println("Использованные буквы " + usedLetters);


        char[] maskChars = currentMask.toCharArray();
        for (int i = 0; i < secretWord.length(); i++) {
            if (secretWord.charAt(i) == letter) {
                maskChars[i] = letter;
            }
        }
        return new String(maskChars);
        //проверка окончания игры
    }

    public static boolean isGameOver(String currentMask, String secretWord, int errors) throws FileNotFoundException {

            if (errors >= MAX_ERRORS) {
                System.out.println("Игра окончена, вы были повешены! Загаданное слово " + secretWord);
                return true;
            } else if(!currentMask.contains("*")){
                System.out.println("Поздравляем! Вы угадали слово: " + secretWord);
        }
        return false;
    }


    public static void checkErrors(String secretWord, char letter) {
        if(!secretWord.contains(String.valueOf(letter))){
            errors++;
            System.out.println("Количество ошибок " + errors + "/6");
        }else if(letter == letter){
        }
    }
    public static void drawHangman(){
    }
}
