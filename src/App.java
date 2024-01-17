import java.sql.DriverManager;

import diagnostic.Diagnostic;
import symptome.Symptome;

public class App {
    public static void main(String[] args) throws Exception {
        Diagnostic d = new Diagnostic(new Symptome[]{ new Symptome("lelo",5,15) ,new Symptome("migraine",5,15), 
        new Symptome("caca",5,15), new Symptome("fatigue",5,15)} ,
        DriverManager.getConnection("jdbc:postgresql://localhost:5432/etsabo","postgres","post")) ;

        System.out.println(d);


        Diagnostic.getProbaDiagnostic(new Symptome[]{ new Symptome("lelo",5,15) ,new Symptome("migraine",5,15), 
        new Symptome("caca",5,15), new Symptome("fatigue",5,15)} ,
        DriverManager.getConnection("jdbc:postgresql://localhost:5432/etsabo","postgres","post")) ;
    }
}
