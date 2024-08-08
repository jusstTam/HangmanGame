package main;

import java.util.Random;

public class RandomWord {
    private static final String[] words = {"человек", "работа", "вопрос", "сторона",
            "страна", "случай", "голова", "ребенок", "система", "отношение", "женщина",
            "деньги", "машина", "проблема", "решение", "история", "власть", "тысяча",
            "возможность", "результат", "область", "статья", "компания", "группа",
            "развитие", "процесс", "условие", "средство", "начало", "уровень", "минута",
            "качество", "программирование", "тестировщик"};

    protected String getRandomWord() {
        Random random = new Random();
        return words[random.nextInt(words.length)];
    }
}
