package game.io;

import java.util.Scanner;

public class ConsoleReader implements AutoCloseable {

    private final Scanner scanner;
    private final Writer consoleWriter;

    public ConsoleReader(Writer consoleWriter) {
        this.consoleWriter = consoleWriter;
        scanner = new Scanner(System.in);
    }

    public String getString(){
        String line = scanner.nextLine();
        checkExit(line);
        return line;
    }

    public int getInt(){
        Integer value = null;
        String str = "";
        while (value == null) {
            try {
                str = scanner.nextLine();
                value = Integer.parseInt(str);
            } catch (NumberFormatException e) {
                checkExit(str);
                consoleWriter.printlnViaTranslator("integer_enter");
            }
        }
        return value;
    }

    @Override
    public void close() throws Exception {
        scanner.close();
    }

    private void checkExit(String s){
        if(s.equals(":q")){
            if(confirmExit()){
                System.exit(0);
            }
        }
    }

    private boolean confirmExit() {
        consoleWriter.printlnViaTranslator("ask_for_exit");
        return getString().equalsIgnoreCase("y");
    }
}
