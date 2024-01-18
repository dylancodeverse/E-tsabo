import java.sql.Connection;
import java.sql.DriverManager;

import DYNMathOptimisation.Constraint;
import DYNMathOptimisation.Simplex;
import client.ClientSymptome;
import diagnostic.Diagnostic;
import medicament.CompositionMedicament;
import medicament.Medicament;
import symptome.Symptome;

public class App {
    public static void main(String[] args) throws Exception {
        Diagnostic.getFullDiagnostic("dylan");
        
    }
}
