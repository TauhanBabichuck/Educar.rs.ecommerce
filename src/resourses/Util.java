/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resourses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Escola
 */
public class Util {
    private static final String ur1 = "jdbc:postgresql://localhost:5432/ecommerce";
    private static final String driver = "org.postgresql.Driver";
    private static final String usuario = "postgres";
    private static final String senha = "tecnico";
    Connection c;

    public Connection conexao() {
        try {
            
            Class.forName(driver);
            
            c = DriverManager.getConnection(ur1, usuario, senha);
            
        }catch (ClassNotFoundException | SQLException ex){
            
            JOptionPane.showMessageDialog(null, "Não foi possível estabelecer uma conexão com o banco");
        }
        return c;
    }
}
