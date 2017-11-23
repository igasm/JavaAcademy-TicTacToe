package settings;

class SettingsValidator {

    private final Integer minBorderDimension;
    private final Integer maxBorderDimension;
    private final Integer minWinningCondition;

    SettingsValidator(Integer minBorderDimension, Integer maxBorderDimension, Integer minWinningCondition) {
        this.minBorderDimension = minBorderDimension;
        this.maxBorderDimension = maxBorderDimension;
        this.minWinningCondition = minWinningCondition;
    }

    boolean validWinningCondition(BoardDimensions boardDimensions, Integer winningCondition){
        return winningCondition >=3 && winningCondition <= boardDimensions.getMinDimension();
    }

    boolean validBorderDimension(Integer dimension){
        return (dimension >= minBorderDimension && dimension <= maxBorderDimension);
    }

}
