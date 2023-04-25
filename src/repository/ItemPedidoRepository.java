/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import entity.ItemPedido;
import entity.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import org.postgresql.util.PSQLException;
import resourses.Util;

/**
 *
 * @author Escola
 */
public class ItemPedidoRepository {
    
     private Util util = new Util();
    Connection conn;
    PreparedStatement ppst;
    
    public ItemPedido salvarItemPedido( ItemPedido itemPedido, int idPedido) {
         conn = util.conexao();
        String sql = "INSERT INTO item_pedido("
                + "valor,"
                + "quantidade,"
                + "id_pedido,"
                + "id_produto) "
                + "VALUES(?,?,?,?)";
        try {
            ppst = conn.prepareStatement(sql);
            ppst.setDouble(1, itemPedido.getValor());
            ppst.setInt(2, itemPedido.getQuantidade());
            ppst.setInt(3, idPedido);
            ppst.setInt(4, itemPedido.getProduto().getId());
            ppst.executeUpdate();
            ppst.close();
            conn.close();
            
        } catch (PSQLException ex){
            JOptionPane.showMessageDialog(null, "Não foi possível salvar o item de Pedido");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return null;
    }
    
}
