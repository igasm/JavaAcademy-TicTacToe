package game;

import io.ConsoleReader;
import settings.BoardDimensions;
import settings.Settings;

import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        ConsoleReader consoleReader = new ConsoleReader();
        Consumer<String> consoleWriter = System.out::println;
        Consumer<Exception> exceptionHandler = e -> System.out.println("Exception occured " + e.getMessage());
        consoleWriter.accept("Witaj w grze kółko i krzyżyk\n");
        Settings settingsLoader = new Settings(exceptionHandler, new BoardDimensions(3, 3), 3);
//      consoleWriter.accept(settingsLoader.loadFromJSONFile());

        GameLoader gameLoader = new GameLoader(consoleWriter, consoleReader, settingsLoader);
        GameManager boardManager = gameLoader.load();
        boardManager.runGame();
    }
}
