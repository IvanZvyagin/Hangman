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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws FileNotFoundException {
        startGame();
        readAndOutWord();
    }


    public static void startGame() {
        System.out.println("Хотите начать новую игру? Введите: ");
        System.out.println("[N]ew game или [E]xit");
        //gameNotOver
        //playerTurn

    }

    public static void readAndOutWord() throws FileNotFoundException {
        List<String> words = new ArrayList<>();
        try (BufferedReader bf = new BufferedReader(new FileReader("src/words.txt"))) {
            String word;
            while ((word = bf.readLine()) != null) {
                words.add(word);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String secretWord = getRandomWord(words);
        String maskSecretWord = secretWord.replaceAll(".", "*");
        System.out.println(maskSecretWord);

        //вывод слова на экран
    }

    public static String getRandomWord(List<String> words) {
        Random random = new Random();
        int randomIndex = random.nextInt(words.size());
        return words.get(randomIndex);
    }

    public static void makePlayerTurn() {
        char letter = scanner.nextLine().charAt(0);
        //ввод с консоли буквы
        //проверка правильного введения
        //проверка буквы в слове
        //проверка окончания игры
    }

    public static void checkGameStatus() {
        //проверить максимально количество ошибок
        //остались ошибки или нет
        //если ошибок осталось 0 то проиграл
        //если ошибки еще есть игра продолжается
        //проиграл или выиграл
    }

    public static void isValidationInput() {
        //проверка что введена именно буква
        //проверка что введена одна буква
    }
}
