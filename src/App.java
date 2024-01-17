import java.sql.Connection;
import java.sql.DriverManager;

import DYNMathOptimisation.Constraint;
import DYNMathOptimisation.Simplex;
import medicament.Medicament;
import symptome.Symptome;

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
            Symptome[] s = new Symptome[]{ new Symptome("lelo",4,15) ,new Symptome("caca",-8,15),  new Symptome("fatigue",-2,15)} ;
            Medicament[] mds = Medicament.select(c);
            String [] ss=  Medicament.formerEquationContrainteEquilibre(mds );
            ss[0] =ss[0].replace("= 0", "="+ -1* s[1].getNiveau() +"" ) ;
            ss[1] =ss[1].replace("= 0", "="+-1* s[2].getNiveau() +"" ) ;
            ss[2] =ss[2].replace("= 0", "="+-1* s[0].getNiveau() +"" ) ;
            

            for (int i = 0; i < ss.length; i++) {
                System.out.println(ss[i]);
            }
            System.out.println(Medicament.getEquationToMinimize(mds));

            System.out.println(Medicament.getLogicalContrainte(mds));


            Simplex x = new Simplex() ;
            x.simplexMinimisation(Medicament.getEquationToMinimize(mds),ss, new Constraint(Medicament.getLogicalContrainte(mds))) ;
        }
        finally{
            c.close();
        }
        
    }
}
