package game.players;

class PlayerNameValidator {

    boolean validate(String name) throws InvalidPlayerNameException {
        boolean isValid = false;
        if(name.matches("^\\s+$")){
            throw new InvalidPlayerNameException("Imię gracza nie może składac się wyłącznie z baiłych znaków");
        }else if(name.equals("")){
            throw new InvalidPlayerNameException("Imię gracza nie może byc puste");
        }else if(!name.matches("[a-zA-Z0-9]+")){
            throw new InvalidPlayerNameException("Imię gracza może się składać wyłącznie ze znaków alfanumerycznych");
        }else{
            isValid = true;
        }
        return isValid;
    }

}
