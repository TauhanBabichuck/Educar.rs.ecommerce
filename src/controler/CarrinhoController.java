/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import entity.ItemPedido;
import entity.Pedido;
import java.time.LocalDate;

/**
 *
 * @author Escola
 */
public class CarrinhoController extends javax.swing.JFrame {

    public CarrinhoController(Pedido pedido) {
        initComponents();
        this.setLocationRelativeTo(null);
        preencheCabecalho(pedido);
        preencheTabela(pedido);

    }

    private void preencheCabecalho(Pedido pedido) {

        if (pedido.getId() == 0) {
            int i = 0;
            jtfNome.setText(pedido.getCliente().getNome());
            jtfData.setText(LocalDate.now().toString());
            Double valorTotal = 0.0;
            for (ItemPedido ip : pedido.getItensPedido()) {
                valorTotal+=ip.getValor();
            }
            jtfValorTotalPedido.setText(valorTotal+"");
        } else {
            jtfId.setText(pedido.getId() + "");
            jtfNome.setText(pedido.getCliente().getNome());
            jtfData.setText(pedido.getData().toString());

        }

    }

    private void preencheTabela(Pedido pedido) {

        int i = 0;
        for (ItemPedido ip : pedido.getItensPedido()) {
            jtTabela.setValueAt(ip.getProduto().getNome(), i, 0);
            jtTabela.setValueAt(ip.getQuantidade(), i, 2);
            jtTabela.setValueAt(ip.getProduto().getPreco(), i, 1);
            jtTabela.setValueAt(ip.getValor(), i, 3);

            i++;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jtfData = new javax.swing.JTextField();
        jtfNome = new javax.swing.JTextField();
        jtfId = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jlId = new javax.swing.JLabel();
        jlNome = new javax.swing.JLabel();
        jlData = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtTabela = new javax.swing.JTable();
        jtfValorTotalPedido = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jtfData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfDataActionPerformed(evt);
            }
        });

        jtfNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfNomeActionPerformed(evt);
            }
        });

        jtfId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfIdActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PEDIDO");

        jlId.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jlId.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlId.setText("ID:");

        jlNome.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jlNome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlNome.setText("NOME:");

        jlData.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jlData.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlData.setText("DATA:");

        jtTabela.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jtTabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nome", "Preço", "Quantidade", "Valor Total"
            }
        ));
        jScrollPane1.setViewportView(jtTabela);

        jtfValorTotalPedido.setEditable(false);
        jtfValorTotalPedido.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtfValorTotalPedido.setToolTipText("");
        jtfValorTotalPedido.setName(""); // NOI18N
        jtfValorTotalPedido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtfValorTotalPedidoMouseClicked(evt);
            }
        });
        jtfValorTotalPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfValorTotalPedidoActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Valor total do Pedido:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfValorTotalPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlId)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfId, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85)
                        .addComponent(jlNome)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfNome, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                        .addComponent(jlData)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfData, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(51, 51, 51))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfNome, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfId, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfData, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlId, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlNome, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlData, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jtfValorTotalPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtfDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfDataActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jtfDataActionPerformed

    private void jtfIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfIdActionPerformed

    private void jtfNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfNomeActionPerformed

    private void jtfValorTotalPedidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtfValorTotalPedidoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfValorTotalPedidoMouseClicked

    private void jtfValorTotalPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfValorTotalPedidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfValorTotalPedidoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CarrinhoController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CarrinhoController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CarrinhoController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CarrinhoController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlData;
    private javax.swing.JLabel jlId;
    private javax.swing.JLabel jlNome;
    private javax.swing.JTable jtTabela;
    private javax.swing.JTextField jtfData;
    private javax.swing.JTextField jtfId;
    private javax.swing.JTextField jtfNome;
    private javax.swing.JTextField jtfValorTotalPedido;
    // End of variables declaration//GEN-END:variables
}
