package client;

import java.sql.Connection;
import java.sql.DriverManager;

import orm.ORM;

public class ClientSymptome extends ORM<ClientSymptome>{
    Integer clientsymptomeid ;
    String idclient ;
    String idsymptome ;
    Integer niveau ;
    public static void insertStatic(String idClient ,String allSymptome , String allniveau) throws Exception{
        Connection c= DriverManager.getConnection("jdbc:postgresql://localhost:5432/etsabo","postgres","post") ;
        try{
            c.setAutoCommit(false);
            String [] s = allSymptome.split(";");
            String [] n = allniveau.split(";");
            for (int i = 0; i < n.length; i++) {
                ClientSymptome cl = new ClientSymptome();
                cl.setIdclient(idClient);
                cl.setIdsymptome(s[i]);
                cl.setNiveau(Integer.parseInt(n[i]));
                cl.insert(c, true);
            }
            c.commit();
    
        } catch (Exception e) {
            c.rollback();
        }
        finally{
            c.close();
        }
    }



    public void setClientsymptomeid(Integer clientsymptomeid) {
        this.clientsymptomeid = clientsymptomeid;
    }
    public void setIdclient(String idclient) {
        this.idclient = idclient;
    }
    public void setIdsymptome(String idsymptome) {
        this.idsymptome = idsymptome;
    }
    public void setNiveau(Integer niveau) {
        this.niveau = niveau;
    }
}
