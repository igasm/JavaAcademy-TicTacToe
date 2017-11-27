package game;

import io.ConsoleReader;
import io.Writer;
import io.WriterBuilder;
import settings.Settings;
import settings.SettingsManager;

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
