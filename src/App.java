import java.sql.Connection;
import java.sql.DriverManager;

import main.diagnostic.Diagnostic;

public class App {
    public static void main(String[] args) throws Exception {
        Diagnostic x = null;
        try {
             x = Diagnostic.getFullDiagnostic("dylan");
             
             System.out.println(x.getNomMaladie());            
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(x.getNomMaladie());
        }

    }
}
