package game.io;

import java.io.*;

public class WriterBuilder {

    private PrintStream printStream = null;
    private Translator translator = null;

    public WriterBuilder byOutputName(String targetName){

        try {
            switch (targetName.toUpperCase()) {
                case "ERR":
                    printStream = System.err;
                    break;
                case "OUT":
                    printStream = System.out;
                    break;
                case "FILE": {
                    String fileName = "./out.txt";
                    printStream =  new PrintStream(new BufferedOutputStream(new FileOutputStream(fileName)), true, "UTF-8");
                    break;
                }
                default: printStream = System.out;
            }
        }catch (FileNotFoundException e){
            printStream = System.out;
        } catch (UnsupportedEncodingException e) {
            printStream = System.out;
        }

        return this;

    }

    public WriterBuilder byLanguage(String language){

        translator = new Translator();

        if(language.toLowerCase().equals("pl") || language.toLowerCase().equals("en")){
            translator.load(language.toLowerCase());
        }else{
            translator.load("pl");
        }

        return this;
    }

    public Writer buildByDefault(){
        printStream = System.out;
        translator = new Translator();
        translator.load("pl");
        return new Writer(printStream, translator);
    }

    public Writer build(){
        return new Writer(printStream, translator);
    }

}
