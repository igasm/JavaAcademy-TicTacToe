package io;

import java.io.*;
import java.util.HashMap;

public class WriterBuilder {

    public Writer byTargetName(String targetName){
        Writer writer;

        try {
            switch (targetName.toUpperCase()) {
                case "ERR":
                    writer = new Writer(System.err);
                    break;
                case "OUT":
                    writer = new Writer(System.out);
                    break;
                case "FILE": {
                    String fileName = "./out.txt";
                    PrintStream printOut =  new PrintStream(new BufferedOutputStream(new FileOutputStream(fileName)), true);
                    writer = new Writer(printOut);
                    break;
                }
                default: writer = byDefault();
            }
        }catch (FileNotFoundException e){
            writer = byDefault();
        }

        return writer;

    }

    public Writer byDefault(){
        return new Writer(System.out);
    }

}
