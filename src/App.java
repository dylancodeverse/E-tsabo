import java.sql.Connection;
import java.sql.DriverManager;

import main.diagnostic.Diagnostic;

public class App {
    public static void main(String[] args) throws Exception {
        Diagnostic.getFullDiagnostic("dylan");
        
    }
}
