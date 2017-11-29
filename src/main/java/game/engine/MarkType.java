package game.engine;

public enum MarkType {
    CROSS("x"),
    NAUGHT("o");

    private String mark;

    MarkType(String mark){
        this.mark = mark;
    }

    @Override
    public String toString() {
        return mark;
    }


}
