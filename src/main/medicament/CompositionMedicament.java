package main.medicament;

import java.sql.Connection;

import main.DYNMathOptimisation.Constraint;
import main.DYNMathOptimisation.Simplex;
import main.symptome.Symptome;


public class CompositionMedicament {
    Medicament[] medicaments ;
    Double sum ;

    

    /**
     * Mi creer composition ana fanafody @ alalan'ny symptomes:
     * 
     * zavatra anakiroa no ataony :
     *  mi former anle syntax anle formule izay ho ampiasaina @ simplexe anaovana minimisation
     *  mi execute anle minimisation de maka ny resultat
     * 
     * @param symptomes
     * @param connection
     * @throws Exception
     */
    public CompositionMedicament(Symptome [] symptomes , Connection connection) throws Exception{ 
        Medicament[] mds = Medicament.select(connection);

        // formule
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
                            // formule                                                               formule
        x.simplexMinimisation(Medicament.getEquationToMinimize(mds),ss, new Constraint(Medicament.getLogicalContrainte(mds))) ;

        sum = x.getEquation().getSecondMember() ;

        setMedicaments(x,connection);
    }

    private void setMedicaments(Simplex x , Connection connection) throws Exception {
        Medicament[] medicaments = Medicament.selectWhere(connection, x);
        for (int i = 0; i < medicaments.length; i++) {
            for (int j = 0; j < x.getMatrix().length; j++) {
                if (medicaments[i].getMedicament().equals(x.getMatrix()[j].getBasicVariables())) {
                    medicaments[i].setQuantite(x.getMatrix()[j].getSecondMember());
                    break;
                }
            }
        }
        this.medicaments = medicaments ;
    }
    public Double getSum() {
        return sum;
    }

    public Medicament[] getMedicaments() {
        return medicaments;
    }

    public void setMedicaments(Medicament[] medicaments) {
        this.medicaments = medicaments;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }
}
