package game;

class Arbiter {
    private final int winningCondition;

    Arbiter(int winningCondition) {
        this.winningCondition = winningCondition;
    }

    boolean isWin(MarkType mark, String seqenceString) {
        String regex = ".*"+mark.toString()+"{" + winningCondition +",}.*";
        return seqenceString.matches(regex);
    }
}
