package symptome;

/**
 * Symptome
 */
public class Symptome {

    String symptome ;
    Integer niveau ;
    Integer ageCaracteristique ;



    public Symptome(String symptome, Integer niveau, Integer ageCaracteristique) {
        this.symptome = symptome;
        this.niveau = niveau;
        this.ageCaracteristique = ageCaracteristique;
    }
    public String getSymptome() {
        return symptome;
    }
    public void setSymptome(String symptome) {
        this.symptome = symptome;
    }
    public Integer getNiveau() {
        return niveau;
    }
    public void setNiveau(Integer niveau) {
        this.niveau = niveau;
    }
    public Integer getAgeCaracteristique() {
        return ageCaracteristique;
    }
    public void setAgeCaracteristique(Integer ageCaracteristique) {
        this.ageCaracteristique = ageCaracteristique;
    }


    
}