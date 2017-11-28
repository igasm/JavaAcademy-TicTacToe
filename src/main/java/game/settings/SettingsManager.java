package game.settings;

import game.io.ConsoleReader;
import game.io.Writer;

public class SettingsManager {

    private final Integer minBorderDimension = 3;
    private final Integer maxBorderDimension = 101;
    private final Integer minWinningCondition = minBorderDimension;
    private final String settingsFilePath = "./src/main/resources/settings.json";
    private final Writer consoleWriter;
    private final ConsoleReader consoleReader;

    public SettingsManager(Writer consoleWriter, ConsoleReader consoleReader) {
        this.consoleWriter = consoleWriter;
        this.consoleReader = consoleReader;
    }

    public Settings run(){
        Settings settings = load();
        consoleWriter.accept("Czy chcesz modyfikować ustawienia planszy? [T/N]");

        if(consoleReader.getString().toUpperCase().equals("T")){
            consoleWriter.addNewLine();
            settings = reconfigure();
        }
        return settings;
    }

    Settings load(){
        String newline = System.getProperty("line.separator");
        String message;
        Settings settings;

        try {
            settings = new SettingsReader(settingsFilePath).load();
            message = "Wczytane ustawienia planszy:" +
                    newline + "\tWymiary planszy: "
                    + settings.getBoardDimensions().getWidth() + "x" + settings.getBoardDimensions().getWidth() +
                    newline + "\tWarunek wygranej: " + settings.getWinningCondition() + " znaków" + newline;
        } catch (SettingsLoadingException e) {
            settings =  new Settings(new BoardDimensions(3, 3), 3);
            message = "Błąd podczas wczytywania ustawień planszy." + newline +
                    "Wczytywanie domyślnych ustawień" + newline +
                    "\tWymiary planszy: 3x3." + newline +
                    "\tWarunek wygranej: 3 znaki" + newline;
        }

        consoleWriter.accept(message);
        return settings;
    }

    Settings reconfigure() {
        SettingsValidator settingsValidator = new SettingsValidator(minBorderDimension, maxBorderDimension, minWinningCondition);
        consoleWriter.accept("Minimalna szerokość/wysokość planszy  to " + minBorderDimension);
        consoleWriter.accept("Maksymalna szerokość/wysokość planszy  to " +  maxBorderDimension);

        Integer width = askForBoardDimension("Podaj szerokość planszy", settingsValidator);
        Integer height = askForBoardDimension("Podaj wysokość planszy", settingsValidator);
        BoardDimensions boardDimensions = new BoardDimensions(width, height);
        Integer winningCondition = askForWinningCondition(settingsValidator, boardDimensions);
        Settings settings = new Settings(boardDimensions, winningCondition);

        SettingsWriter settingsWriter = new SettingsWriter(settingsFilePath);
        if(settingsWriter.save(settings)){
            consoleWriter.accept("Ustawienia zostały zapisane");
        }else{
            consoleWriter.accept("Zapisywanie ustawień nie powiodło się");
        }
        consoleWriter.addNewLine();
        return settings;
    }

    Integer askForBoardDimension(String messageForUser, SettingsValidator settingsValidator){
        boolean validValue = false;
        Integer dimension = -1;
        consoleWriter.addNewLine();
        validValue = false;
        while (!validValue){
            consoleWriter.accept(messageForUser);
            dimension = consoleReader.getInt();
            validValue = settingsValidator.validBorderDimension(dimension);
        }
        return dimension;
    }

    Integer askForWinningCondition(SettingsValidator settingsValidator, BoardDimensions boardDimensions){
        boolean validValue = false;
        Integer winningCondition = -1;
        consoleWriter.addNewLine();
        while (!validValue){
            consoleWriter.accept("Podaj warunek wygranej (liczba znaków w linii)");
            consoleWriter.accept("(Maksymalny dopuszczalny warunek zwycięstwa to "
                    + boardDimensions.getMinDimension() + " znaki)");
            winningCondition = consoleReader.getInt();
            validValue = settingsValidator.validWinningCondition(boardDimensions, winningCondition);
        }
        return winningCondition;
    }

}
