package game.io;

import java.util.Scanner;

public class ConsoleReader {

    private final Scanner scanner;
    private final Writer consoleWriter;

    public ConsoleReader(Writer consoleWriter) {
        this.consoleWriter = consoleWriter;
        scanner = new Scanner(System.in);
    }

    public String getString(){
        return scanner.nextLine();
    }

    public int getInt(){
        Integer value = null;
        String str;
        while (value == null) {
            try {
                str = scanner.nextLine();
                value = Integer.parseInt(str);
            } catch (NumberFormatException e) {
                consoleWriter.accept("Podaj liczbę całkowitą");
            }
        }
        return value;
    }

    @Override
    public void finalize(){
        scanner.close();
    }
}
