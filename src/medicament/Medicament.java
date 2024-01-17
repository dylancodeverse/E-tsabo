package medicament;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class Medicament{
    
    String medicament ;
    ArrayList<Soins> soins ;
    Double prixUnitaire ;

    public static Medicament[] select(Connection connection)throws Exception{
        Statement statement = connection.createStatement() ;
        ResultSet res= statement.executeQuery("select * from v_soinssymptome ");
        ArrayList<Medicament> ls= new ArrayList<Medicament>();
        Medicament m = new Medicament();
        String medicament ="";
        if (res.next()) {
            medicament= res.getString("idmedicament");
            m.setMedicament(medicament);
            Soins s = new Soins();
            s.setSymptome(res.getString("idsymptome"));
            s.setEfficacite(res.getDouble("efficacite"));
            m.setSoins(s);
            m.setPrixUnitaire(res.getDouble("prix"));
        }
        while (res.next()) {
            if (!res.getString("idmedicament").equals(medicament)) {
                Medicament x = new Medicament();
                x.setMedicament(m.getMedicament());
                x.setSoins(m.getSoins());
                x.setPrixUnitaire(m.getPrixUnitaire());
                ls.add(x) ;
                medicament= res.getString("idmedicament");
                m = new Medicament();
                m.setMedicament(medicament);
            }
            Soins s = new Soins();
            s.setSymptome(res.getString("idsymptome"));
            s.setEfficacite(res.getDouble("efficacite"));
            m.setSoins(s);
            m.setPrixUnitaire(res.getDouble("prix"));
        }
        Medicament x = new Medicament();
        x.setMedicament(m.getMedicament());
        x.setSoins(m.getSoins());
        x.setPrixUnitaire(m.getPrixUnitaire());
        ls.add(x) ;

        return ls.toArray(new Medicament[ls.size()]);
    }


    public String getVarContrainteEquilibre(int index){
        return "("+soins.get(index).getEfficacite()+")"+"["+medicament+"]";
    }

    public static String [] formerEquationContrainteEquilibre(Medicament [] medicaments){
        String [] s = new String[medicaments[0].getSoins().size()];
        for (int i = 0; i < s.length; i++) {
            s[i] = "";
        }
        for (int i = 0; i < s.length; i++) {
            for (int j = 0; j < s.length; j++) {
                s[i] = s[i] +medicaments[j].getVarContrainteEquilibre(i) ;           
            }
        }
        for (int i = 0; i < s.length; i++) {
            s[i]= s[i]+" = 0";
        }
        return s ;
    }

    public static String getEquationToMinimize(Medicament[] medicaments){
        String s= "";
        for (int i = 0; i < medicaments.length; i++) {
            s = s+"(" +medicaments[i].getPrixUnitaire() +")" + "["+medicaments[i].getMedicament() +"]" ;
        }
        return s; 
    }

    public static String getLogicalContrainte(Medicament[] medicaments){
        String s= "";
        for (int i = 0; i < medicaments.length; i++) {
            s = s + "(1)"+"["+medicaments[i].getMedicament() +"]" ;
        }
        s = s+ ">=0";
        return s ;
    }


    private void setSoins(Soins s) {
        if (soins ==null) {
            soins = new ArrayList<Soins>();
        }
        soins.add(s);
    }

    public String getMedicament() {
        return medicament;
    }
    public void setMedicament(String medicament) {
        this.medicament = medicament;
    }

    public ArrayList<Soins> getSoins() {
        return soins;
    }

    public void setSoins(ArrayList<Soins> soins) {
        this.soins = soins;
    }

    public Double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(Double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

}
