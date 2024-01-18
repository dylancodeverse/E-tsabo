package medicament;

import java.sql.Connection;

import DYNMathOptimisation.Constraint;
import DYNMathOptimisation.Simplex;
import symptome.Symptome;

public class CompositionMedicament {
    Medicament[] medicaments ;
    Double sum ;

    public CompositionMedicament(Symptome [] symptomes , Connection connection) throws Exception{ 
        Medicament[] mds = Medicament.select(connection);
        String [] ss=  Medicament.formerEquationContrainteEquilibre(mds ,symptomes);
        
        for (int i = 0; i < ss.length; i++) {
            System.out.println("Les equations contraintes:");
            System.out.println(ss[i]);
            System.out.println("-----");
        }
        System.out.println("A minimiser:");
        System.out.println(Medicament.getEquationToMinimize(mds));
        System.out.println("---");
        System.out.println("logique:");
        System.out.println(Medicament.getLogicalContrainte(mds));
        System.out.println("---");


        Simplex x = new Simplex() ;

        x.simplexMinimisation(Medicament.getEquationToMinimize(mds),ss, new Constraint(Medicament.getLogicalContrainte(mds))) ;

        sum = x.getEquation().getSecondMember() ;

        setMedicaments(x);
    }

    private void setMedicaments(Simplex x) {

    }
}
