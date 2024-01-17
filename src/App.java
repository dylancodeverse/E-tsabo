import java.sql.Connection;
import java.sql.DriverManager;

import medicament.Medicament;

public class App {
    public static void main(String[] args) throws Exception {
        Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/etsabo","postgres","post");
        // Diagnostic d = new Diagnostic(new Symptome[]{ new Symptome("lelo",5,15) ,new Symptome("migraine",5,15), 
        // new Symptome("caca",5,15), new Symptome("fatigue",5,15)} ,
        // DriverManager.getConnection("jdbc:postgresql://localhost:5432/etsabo","postgres","post")) ;

        // System.out.println(d);


        // Diagnostic.getProbaDiagnostic(new Symptome[]{ new Symptome("lelo",5,15) ,new Symptome("migraine",5,15), 
        // new Symptome("caca",5,15), new Symptome("fatigue",5,15)} ,
        // DriverManager.getConnection("jdbc:postgresql://localhost:5432/etsabo","postgres","post")) ;
        try{
            Medicament.select(c);
        }
        finally{
            c.close();
        }
        
    }
}
