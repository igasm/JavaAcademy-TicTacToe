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
        consoleWriter.addNewLine();
        consoleWriter.printlnViaTranslator("ask_for_settings_change");

        if(consoleReader.getString().toUpperCase().equals("Y")){
            consoleWriter.addNewLine();
            settings = reconfigure();
        }
        return settings;
    }

    private Settings load(){
        Settings settings;

        try {
            settings = new SettingsReader(settingsFilePath).load();
            consoleWriter.printlnViaTranslator("loaded_board_settings_header");
            consoleWriter.printViaTranslator("board_dimensions");
            consoleWriter.print(Integer.toString(settings.getBoardDimensions().getWidth()));
            consoleWriter.print("x");
            consoleWriter.println(Integer.toString(settings.getBoardDimensions().getHeight()));
            consoleWriter.printViaTranslator("winning_conditon");
            consoleWriter.println(Integer.toString(settings.getWinningCondition()));
        } catch (SettingsLoadingException e) {
            settings =  new Settings(new BoardDimensions(3, 3), 3);
            consoleWriter.printlnViaTranslator("default_loaded_board_settings_header");
            consoleWriter.printViaTranslator("board_dimensions");
            consoleWriter.print(Integer.toString(settings.getBoardDimensions().getWidth()));
            consoleWriter.print("x");
            consoleWriter.println(Integer.toString(settings.getBoardDimensions().getHeight()));
            consoleWriter.printViaTranslator("winning_conditon");
            consoleWriter.println(Integer.toString(settings.getWinningCondition()));
        }

        return settings;
    }

    private Settings reconfigure() {
        SettingsValidator settingsValidator = new SettingsValidator(minBorderDimension, maxBorderDimension, minWinningCondition);
        consoleWriter.printViaTranslator("min_board_dimension_info");
        consoleWriter.println(" " + minBorderDimension.toString());
        consoleWriter.printViaTranslator("max_board_dimension_info");
        consoleWriter.println(" " + maxBorderDimension.toString());


        Integer width = askForBoardDimension("ask_for_border_width", settingsValidator);
        Integer height = askForBoardDimension("ask_for_border_height", settingsValidator);
        BoardDimensions boardDimensions = new BoardDimensions(width, height);
        Integer winningCondition = askForWinningCondition(settingsValidator, boardDimensions);
        Settings settings = new Settings(boardDimensions, winningCondition);

        SettingsWriter settingsWriter = new SettingsWriter(settingsFilePath);
        if(settingsWriter.save(settings)){
            consoleWriter.printlnViaTranslator("settings_saved_info");
        }else{
            consoleWriter.printlnViaTranslator("settings_not_saved_info");
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
            consoleWriter.printlnViaTranslator(messageForUser);
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
            consoleWriter.printViaTranslator("ask_for_winning_condition");
            consoleWriter.println(" max " + boardDimensions.getMinDimension());
            winningCondition = consoleReader.getInt();
            validValue = settingsValidator.validWinningCondition(boardDimensions, winningCondition);
        }
        return winningCondition;
    }

}
