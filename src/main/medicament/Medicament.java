package main.medicament;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import main.DYNMathOptimisation.Simplex;
import main.symptome.Symptome;



public class Medicament{
    
    String medicament ;
    ArrayList<Soins> soins ;
    Double prixUnitaire ;

    Double quantite;


    public static Medicament[] selectWhere(Connection connection , Simplex simplex) throws Exception {
        Statement statement = connection.createStatement() ;
        ResultSet res= statement.executeQuery("select * from v_soinssymptome " + getWhere(simplex));        
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

    private static String getWhere(Simplex simplex) {
        String s = " where ";
        for (int i = 0; i < simplex.getMatrix().length; i++) {
            s = s +  " idmedicament = '"+simplex.getMatrix()[i].getBasicVariables() +"' or" ;            
        }
        return s.substring( 0, s.lastIndexOf(" or"));
        
    }

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
            for (int j = 0; j < medicaments.length; j++) {
                s[i] = s[i] +medicaments[j].getVarContrainteEquilibre(i) ;           
            }
        }
        for (int i = 0; i < s.length; i++) {
            s[i]= s[i]+" = 0";
        }
        return s ;
    }

    public static String [] formerEquationContrainteEquilibre(Medicament [] medicaments , Symptome[] symptomes) throws Exception{
        String [] equations = formerEquationContrainteEquilibre(medicaments);
        for (int i = 0; i < symptomes.length; i++) {
            int indexSoins = medicaments[0].getIndexSoins(symptomes[i]);
            equations[indexSoins] =equations[indexSoins].replace("= 0", "="+ -1* symptomes[i].getNiveau() ) ;
        }
        return equations ;
    }

    private int getIndexSoins(Symptome symptome) throws Exception {
        for (int i = 0; i < soins.size(); i++) {
            if (soins.get(i).getSymptome().equals(symptome.getSymptome())) {
                return i ;
            }
        }
        throw new Exception("Symptome introuvable");
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


    public Double getQuantite() {
        return quantite;
    }


    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

}
