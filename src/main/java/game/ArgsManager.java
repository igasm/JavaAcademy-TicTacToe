package game;

public class ArgsManager {
    private final String args[];

    ArgsManager(String[] args) {
        this.args = args;
    }

    private String byIndex(Integer index){
        if(args == null) {
            return "";
        } else if(args.length > index){
            return args[index];
        }else{
            return "";
        }
    }

    String getOutputArg(){
        return byIndex(0);
    }

    String getLanguageArg() { return byIndex(1); }
}
