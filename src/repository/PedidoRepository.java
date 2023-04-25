/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import entity.Pedido;
import entity.Produto;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.postgresql.util.PSQLException;
import resourses.Util;

/**
 *
 * @author Escola
 */
public class PedidoRepository {

    private Util util = new Util();
    Connection conn;
    PreparedStatement ppst;
    
    public Pedido salvarPedido(Pedido pedido) {
        conn = util.conexao();
        String sql = "INSERT INTO pedido ("
                + "valor_total,"
                + "id_cliente) "
                + "VALUES(?,?) RETURNING id";
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setDouble(1, pedido.getValorTotal());
            ppst.setInt(2, pedido.getCliente().getId());
            ResultSet rs = ppst.executeQuery();
            while(rs.next()){
                pedido.setId(rs.getInt(1));
            }
            ppst.close();
            conn.close();
            
        } catch (PSQLException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível registrar seu Pedido!!!\n" + ex);
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        return pedido;
    }

//    public Produto buscarPedido(int id) {
//        try {
//            String sql = "SELECT * FROM pedido where id = ?";
//            conn = util.conexao();
//            ppst = conn.prepareStatement(sql);
//            ppst.setInt(1, id);
//            ResultSet rs = ppst.executeQuery();
//            while (rs.next()) {
//                return new Pedido( rs.getInt(1), LocalDate.parse(rs.getDate(2).toString()), rs.getDouble(3),  rs.getArray(4), rs.ge(5), );
//            }
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Não foi possível consultar o banco.");
//        }
//        return null;
//    }

}
