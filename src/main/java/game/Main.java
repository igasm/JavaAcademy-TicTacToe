package game;

import game.io.ConsoleReader;
import game.io.Writer;
import game.io.WriterBuilder;
import game.settings.Settings;
import game.settings.SettingsManager;

public class Main {
    public static void main(String[] args) {
        ArgsManager argsManager = new ArgsManager(args);
        Writer consoleWriter = new WriterBuilder().byTargetName(argsManager.getOutputArg());
        ConsoleReader consoleReader = new ConsoleReader(consoleWriter);
        consoleWriter.accept("Witaj w grze kółko i krzyżyk"+System.getProperty("line.separator"));
        Settings settings = new SettingsManager(consoleWriter, consoleReader).run();
        GameManager boardManager = new GameLoader(consoleWriter, consoleReader, settings).load();
        boardManager.runGame();
    }
}
