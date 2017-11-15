public class Arbiter {
    private final int winningCondition;

    public Arbiter(int winningCondition) {
        this.winningCondition = winningCondition;
    }

    public boolean isWin(String mark, String seqenceString) {
        String regex = ".*"+mark+"{" + winningCondition +",}.*";
        return seqenceString.matches(regex);
    }
}
