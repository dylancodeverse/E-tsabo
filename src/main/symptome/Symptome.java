package main.symptome;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

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

    /**
     * Maka anle symptomes anle client anaty base de donnnees
     * @param idclient
     * @param connection
     * @return
     * @throws Exception
     */
    public static Symptome[] getStaticSymptome( String idclient,Connection connection)throws Exception{
        Statement statement= connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from vclientsymptome where idclient = '"+idclient+"'");
        ArrayList<Symptome> ls = new ArrayList<>();
        while (resultSet.next()) {
            Symptome s = new Symptome(resultSet.getString("idsymptome"), resultSet.getInt("niveau"), resultSet.getInt("agecaracteristique"));
            ls.add(s);
        }
        return ls.toArray(new Symptome[ls.size()]);
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