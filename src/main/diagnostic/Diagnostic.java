package main.diagnostic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import main.medicament.CompositionMedicament;
import main.medicament.Medicament;
import main.symptome.Symptome;


/**
 * Diagnostic
 */
public class Diagnostic {

    String nomMaladie ;
    Double probabilite ;
    Symptome[] symptomes;

    CompositionMedicament compositionMedicamentOptimal ;

    /**
     * Objectif : maka anle diagnostic @ anarana nle client
     * @param idClient
     * @return
     * @throws Exception
     */
    public static Diagnostic getFullDiagnostic(String idClient) throws Exception{
        Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/etsabo","postgres","post");
        try{
            Symptome[] s = Symptome.getStaticSymptome(idClient, c);
            return new Diagnostic(s, c);
        }
        finally{
            c.close();
        }
    }


    public String toHtml() {
        StringBuilder htmlBuilder = new StringBuilder();

        // // En-tête HTML
        // htmlBuilder.append("<!DOCTYPE html>");
        // htmlBuilder.append("<html lang=\"en\">");
        // htmlBuilder.append("<head>");
        // htmlBuilder.append("<!-- Les balises meta, title, stylesheets, etc. vont ici -->");
        // htmlBuilder.append("</head>");
        // htmlBuilder.append("<body>");

        // Contenu HTML
        // htmlBuilder.append("<div class=\"container\">");
        htmlBuilder.append("<h2>Rapport de Diagnostic</h2>");
        htmlBuilder.append("<p><strong>Nom de la maladie:</strong> " + nomMaladie + "</p>");

        if (compositionMedicamentOptimal != null) {
            htmlBuilder.append("<h3>Composition de médicaments optimal:</h3>");
            htmlBuilder.append("<p><strong>Coût total:</strong> " + compositionMedicamentOptimal.getSum() + "</p>");
            htmlBuilder.append("<ul>");

            for (Medicament medicament : compositionMedicamentOptimal.getMedicaments()) {
                if (medicament.getQuantite()!=0.0) {
                    htmlBuilder.append("<li>");
                    htmlBuilder.append("<p><strong>Médicament:</strong> " + medicament.getMedicament() + "</p>");
                    htmlBuilder.append("<p><strong>Prix unitaire:</strong> " + medicament.getPrixUnitaire() + "</p>");
                    htmlBuilder.append("<p><strong>Quantité:</strong> " + medicament.getQuantite() + "</p>");
                    htmlBuilder.append("</li>");                    
                }

            }

            htmlBuilder.append("</ul>");
        }

        htmlBuilder.append("</div>");

        // Scripts et autres balises HTML vont ici

        htmlBuilder.append("</body>");
        htmlBuilder.append("</html>");

        return htmlBuilder.toString();
    }

    /**
     * Mi former anle diagnostic
     * @param symptomes
     * @param connection
     * @throws Exception
     */
    public Diagnostic(Symptome[] symptomes , Connection connection) throws Exception{
        setSymptomes(symptomes);
        // mamantatra anle nom anle aretina @ alalan'ny acces base
        setNomMaladie(connection);        
        // mamoaka anle composition ana fana
        try {
            setCompositionMedicament(symptomes, connection);            
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void setCompositionMedicament(Symptome[] symptomes2, Connection connection) throws Exception {
        compositionMedicamentOptimal = new CompositionMedicament(symptomes2, connection);
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

    public static Diagnostic[] getProbaDiagnostic(Symptome[] symptomes ,Connection connection) throws Exception{
        Statement statement = connection.createStatement() ;
        if (!createQueryForSetNomMaladieFrom(symptomes).isBlank()) {
            ResultSet res=  statement.executeQuery(createQueryForSetNomMaladieFrom(symptomes)) ;
            ArrayList<Diagnostic> diagnostics = new ArrayList<Diagnostic>();
            while (res.next()) {
                Diagnostic d = new Diagnostic();          
                d.setSymptomes(symptomes);      
                d.setNomMaladie(res.getString("maladie"));
                d.setProba(res.getDouble("proba"));
                diagnostics.add(d);
            }
            return diagnostics.toArray(new Diagnostic[diagnostics.size()]) ;
        }else{
            return null;
        }
    }

    private void setProba(double double1) {
        probabilite = 100 * double1 / symptomes.length ;
    }

    public static String createQueryForSetNomMaladieFrom (Symptome[] symptomes) throws Exception{
        if ( symptomes!= null) {
            String x ="with listemaladie as (";
            Symptome[]s = symptomes ;

            for (int i = 0; i < s.length; i++) {
                x= x + " select maladie from diagnostic where niveaumin <= "+s[i].getNiveau()+" and "+s[i].getNiveau()+"< niveaumax and agepersonmin<= "+s[i].getAgeCaracteristique()+" and "+s[i].getAgeCaracteristique()+" < agepersonmax and idsymptome = '"+s[i].getSymptome()+"'" ;
                x= x+" UNION ALL";
            }

            x =x.substring(0, x.lastIndexOf(" UNION ALL")) +")" ;

            x = x+ ", countMaladie as ( select count(maladie) as proba , maladie from listemaladie group by maladie)  select proba ,maladie from countMaladie order by proba desc" ;
            return x ;            
        }else{
            return "";
        }
    }

    private String createQueryForSetNomMaladie(){
        if ( getSymptomes()!= null) {
            String x ="with listemaladie as (";
            Symptome[]s = getSymptomes() ;

            for (int i = 0; i < s.length; i++) {
                x= x + " select maladie from diagnostic where niveaumin <= "+s[i].getNiveau()+" and "+s[i].getNiveau()+"< niveaumax and agepersonmin<= "+s[i].getAgeCaracteristique()+" and "+s[i].getAgeCaracteristique()+" < agepersonmax and idsymptome = '"+s[i].getSymptome()+"'" ;
                x= x+" UNION ALL";
            }

            x =x.substring(0, x.lastIndexOf(" UNION ALL")) +")" ;

            x = x+ ", countMaladie as ( select count(maladie) as proba , maladie from listemaladie group by maladie)  select proba ,maladie from countMaladie order by proba desc" ;
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