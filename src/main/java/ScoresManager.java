public class ScoresManager {
    private final PlayersRegister playersRegister;

    public ScoresManager(PlayersRegister playersRegister) {
        this.playersRegister = playersRegister;
    }

    public void addWin(Player player){
        player.addPoints(1);
    }

    public void addDraw(){
        for(Player player : playersRegister.getPlayersList()){
            player.addPoints(1);
        }
    }

    public String getSubmit(){
        String newline = System.getProperty("line.separator");
        StringBuilder stringBuilder = new StringBuilder();
        for(Player player : playersRegister.getPlayersList()){
            stringBuilder.append(player.getName() + ": " + player.getMark() + newline);
        }
        return stringBuilder.toString();
    }
}
