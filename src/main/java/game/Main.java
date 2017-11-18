package game;

import settings.Settings;

import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        Supplier<String> consoleReader = () -> new Scanner(System.in).nextLine();
        Consumer<String> consoleWriter = System.out::println;
        Consumer<Exception> exceptionHandler = e -> System.out.println("Exception occured " + e.getMessage());
        consoleWriter.accept("Witaj w grze kółko i krzyżyk\n");
        Settings settingsLoader = new Settings(exceptionHandler);
        consoleWriter.accept(settingsLoader.load());

        GameLoader gameLoader = new GameLoader(consoleWriter, consoleReader, settingsLoader);
        BoardManager boardManager = gameLoader.load();
        boardManager.runGame();
    }
}
