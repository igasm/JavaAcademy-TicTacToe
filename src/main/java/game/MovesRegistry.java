package game;

import java.util.*;

public class MovesRegistry implements Observed {

    private List<Observer> observers = new ArrayList<>();
    private Map<Integer, String> moves = new HashMap<>();

    public void register(Observer observer){
        observers.add(observer);
    }

    public void addMove(int fieldNumber, String mark){
        moves.put(fieldNumber, mark);
        makeNotify();
    }

    public void makeNotify(){
        for(Observer observer : observers){
            observer.update(this);
        }
    }

    public String getMove(int fieldNumber) {
        return moves.get(fieldNumber);
    }

    public boolean moveExists(int fieldNumber){
        return moves.containsKey(fieldNumber);
    }

    public void clear(){
        moves.clear();
    }

    public int movesCount(){
        return moves.size();
    }
}
