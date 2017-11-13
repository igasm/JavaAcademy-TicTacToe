public class Arbiter {
    private final int winningCondition;

    public Arbiter(int winningCondition) {
        this.winningCondition = winningCondition;
    }

    public boolean isXWins(String seqenceString) {
        String regex = ".*x{" + winningCondition +",}.*";
        return seqenceString.matches(regex);
    }
}
