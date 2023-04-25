/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import entity.Cliente;
import entity.Produto;
import entity.TipoDeCliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import resourses.Util;

/**
 *
 * @author Escola
 */
public class ClienteRepository {

    private final Util util = new Util();
    Connection conn;
    PreparedStatement ppst;

    public Cliente validarLogin(Cliente cliente) {

        try {
            String sql = "SELECT * FROM cliente where email = ? and senha = ?";
            conn = util.conexao();
            ppst = conn.prepareStatement(sql);
            ppst.setString(1, cliente.getEmail());
            ppst.setString(2, cliente.getSenha());
            ResultSet rs = ppst.executeQuery();
            while (rs.next()) {
                return new Cliente( rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), TipoDeCliente.valueOf(rs.getString(5)));
            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Não foi possível consultar o banco!");

        }
        return null;
    }
    public Cliente salvarCliente(Cliente cliente) {
        conn = util.conexao();
        String sql = "INSERT INTO cliente ("
                + "nome,"
                + "email,"
                + "senha,"
                + "tipo) "
                + "VALUES(?,?,?,?)";
        try {
            ppst = conn.prepareStatement(sql);
            
            ppst.setString(1, cliente.getNome());
            ppst.setString(2, cliente.getEmail());
            ppst.setString(3, cliente.getSenha());
            ppst.setString(4, cliente.getTipo().toString());

            ppst.executeUpdate();
            ppst.close();
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return null;
    }
    
}
