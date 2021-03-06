package game.engine.move.scanner;

import java.util.List;

public class Sequence {

    private final List<String> sequencyList;

    Sequence(List<String> sequencyList) {
        this.sequencyList = sequencyList;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(String mark : sequencyList){
            stringBuilder.append(mark);
        }
        return stringBuilder.toString();
    }
}
