package diagnostic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import symptome.Symptome;

/**
 * Diagnostic
 */
public class Diagnostic {

    String nomMaladie ;
    Double probabilite ;
    Symptome[] symptomes;



    public Diagnostic(Symptome[] symptomes , Connection connection) throws Exception{
        setSymptomes(symptomes);
        setNomMaladie(connection);        
    }

    public Diagnostic() {
    }

    /**
     * mi dependre @ symptomes
     */
    private void setNomMaladie(Connection connection) throws Exception {
        Statement statement = connection.createStatement() ;
        if (!createQueryForSetNomMaladie().isBlank()) {
            ResultSet res=  statement.executeQuery(createQueryForSetNomMaladie()) ;
            if (res.next()) {
                nomMaladie= res.getString("maladie") ;
                setProba(res.getDouble("proba"));
            }            
        }

    }

    public Diagnostic[] getProbaDiagnostic(Symptome[] symptomes ,Connection connection) throws Exception{
        Statement statement = connection.createStatement() ;
        if (!createQueryForSetNomMaladie().isBlank()) {
            ResultSet res=  statement.executeQuery(createQueryForSetNomMaladie()) ;
            ArrayList<Diagnostic> diagnostics = new ArrayList<Diagnostic>();
            while (res.next()) {
                Diagnostic d = new Diagnostic();                
                d.setNomMaladie(res.getString("maladie"));
                d.setProba(res.getDouble("proba"));
            }
            return diagnostics.toArray(new Diagnostic[diagnostics.size()]) ;
        }else{
            return null;
        }
    }

    private void setProba(double double1) {
        probabilite = 100 * double1 / symptomes.length ;
    }

    private String createQueryForSetNomMaladie(){
        if ( getSymptomes()!= null) {
            String x ="with listemaladie as (";
            Symptome[]s = getSymptomes() ;

            for (int i = 0; i < s.length; i++) {
                x= x + " select maladie from diagnostic where niveaumin <= "+s[i].getNiveau()+" and "+s[i].getNiveau()+"< niveaumax and agepersonmin<= "+s[i].getAgeCaracteristique()+" and "+s[i].getAgeCaracteristique()+" < agepersonmax where idsymptome = "+s[i].getSymptome() ;
                x= x+" UNION ALL";
            }

            x =x.substring(0, x.lastIndexOf(" UNION ALL")) +")" ;

            x = x+ ", countMaladie as ( select count(maladie) as proba , maladie from listemaladie group by maladie) ) select proba ,maladie from countMaladie order by proba" ;
            return x ;            
        }else{
            return "";
        }
    }

    public void setSymptomes(Symptome[] symptomes) {
        this.symptomes = symptomes;
    }
    public Symptome[] getSymptomes() {
        return symptomes;
    }

    public String getNomMaladie() {
        return nomMaladie;
    }

    public void setNomMaladie(String nomMaladie) {
        this.nomMaladie = nomMaladie;
    }

    public Double getProbabilite() {
        return probabilite;
    }

    public void setProbabilite(Double probabilite) {
        this.probabilite = probabilite;
    }


    
}