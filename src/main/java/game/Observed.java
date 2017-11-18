package game;

public interface Observed {
    void register(Observer observer);
    void makeNotify();
}
