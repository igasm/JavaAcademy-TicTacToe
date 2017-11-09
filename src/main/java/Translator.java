import java.util.ArrayList;

public class Translator {
    private ArrayList<String> avaibleLanguages;
    private String currentLanguage;

    public Translator() {
        avaibleLanguages = new ArrayList<String>();
    }

    public void registerLanguage(String language){
        avaibleLanguages.add(language);
    }

    public String getCurrentLanguage() {
        return currentLanguage;
    }

    public void setCurrentLanguage(String currentLanguage) {
        this.currentLanguage = currentLanguage;
    }
}
