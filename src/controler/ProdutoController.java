/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import entity.Cliente;
import entity.ItemPedido;
import entity.Pedido;
import entity.Produto;
import entity.TipoDeCliente;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import service.PedidoService;
import service.ProdutoService;
import controler.CarrinhoController;
import java.util.ArrayList;

/**
 *
 * @author Escola
 */
/**
 * Creates new form produtoControler
 */
public class ProdutoController extends javax.swing.JFrame {

    private ProdutoService produtoService = new ProdutoService();
    DecimalFormat df = new DecimalFormat("###,###,##0.00");

    private Cliente cliente;
    private Pedido pedido;

    public ProdutoController() {
        initComponents();
        this.setLocationRelativeTo(null);
        preencheTabela(produtoService.buscarProdutos(0));
        desabilitarOpcoesCliente();
    }

    public ProdutoController(Cliente cliente) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.cliente = cliente;
        desabilitarOpcoesAdmin();
        configurarLarguraColunas();
        preencheTabela(produtoService.buscarProdutos(0));

        this.pedido = new Pedido();
        this.pedido.setCliente(this.cliente);
    }

    private void desabilitarOpcoesAdmin() {
        jbNovoProduto.setVisible(false);
        jbEditarProduto.setVisible(false);
        jbExcluirProduto.setVisible(false);
    }

    private void desabilitarOpcoesCliente() {
        jbComprar.setVisible(false);
    }

    private void preencheTabela(List<Produto> produtos) {
        int i = 0;
        for (Produto p : produtos) {
            jlListaProdutos.setValueAt(p.getId(), i, 0);
            jlListaProdutos.setValueAt(p.getNome(), i, 1);
            jlListaProdutos.setValueAt(p.getDescricao(), i, 2);
            jlListaProdutos.setValueAt("R$ " + df.format(p.getPreco()), i, 3);
            jlListaProdutos.setValueAt(p.getEstoque(), i, 4);
            i++;
        }
    }

    public void preencheTabela(Produto produto, int linha) {
        jlListaProdutos.setValueAt("", linha, 0);
        jlListaProdutos.setValueAt(produto.getId(), linha, 0);
        jlListaProdutos.setValueAt("", linha, 1);
        jlListaProdutos.setValueAt(produto.getNome(), linha, 1);
        jlListaProdutos.setValueAt("", linha, 2);
        jlListaProdutos.setValueAt(produto.getDescricao(), linha, 2);
        jlListaProdutos.setValueAt("", linha, 3);
        jlListaProdutos.setValueAt("R$ " + df.format(produto.getPreco()), linha, 3);
        jlListaProdutos.setValueAt("", linha, 4);
        jlListaProdutos.setValueAt(produto.getEstoque(), linha, 4);

    }

    public void limparTabela() {
        for (int i = 1; i < jlListaProdutos.getRowCount(); i++) {
            jlListaProdutos.setValueAt(null, i, 0);
            jlListaProdutos.setValueAt(null, i, 1);
            jlListaProdutos.setValueAt(null, i, 2);
            jlListaProdutos.setValueAt(null, i, 3);
            jlListaProdutos.setValueAt(null, i, 4);
        }
    }

    private void configurarLarguraColunas() {
        jlListaProdutos.getColumnModel().getColumn(0).setPreferredWidth(jlListaProdutos.getWidth() / 20);//5%
        jlListaProdutos.getColumnModel().getColumn(1).setPreferredWidth(jlListaProdutos.getWidth() / 4);//25%
        jlListaProdutos.getColumnModel().getColumn(2).setPreferredWidth(jlListaProdutos.getWidth() / 2);//50%
        jlListaProdutos.getColumnModel().getColumn(3).setPreferredWidth(jlListaProdutos.getWidth() / 10);//10%
        jlListaProdutos.getColumnModel().getColumn(4).setPreferredWidth(jlListaProdutos.getWidth() / 10);//10%
        alinharColuna(0, 0);
        alinharColuna(3, 4);
    }

    private void alinharColuna(int coluna, int alinhameto) {
        DefaultTableCellRenderer alinharDireita = new DefaultTableCellRenderer();
        alinharDireita.setHorizontalAlignment(alinhameto);
        jlListaProdutos.getColumnModel().getColumn(coluna).setCellRenderer(alinharDireita);
    }

    private Produto lerDadosDaLinhaSelecionada() {
        int linha = jlListaProdutos.getSelectedRow();
        System.out.println(Double.parseDouble(jlListaProdutos.getValueAt(linha, 3).toString().replace(".", "").replace(",", ".").substring(3, jlListaProdutos.getValueAt(linha, 3).toString().length() - 1)));
        Produto produto = new Produto(
                Integer.parseInt(jlListaProdutos.getValueAt(linha, 0).toString()),
                jlListaProdutos.getValueAt(linha, 1).toString(),
                jlListaProdutos.getValueAt(linha, 2).toString(),
                Double.parseDouble(jlListaProdutos.getValueAt(linha, 3).toString().replace(".", "").replace(",", ".").substring(3, jlListaProdutos.getValueAt(linha, 3).toString().length() - 1)),
                Integer.parseInt(jlListaProdutos.getValueAt(linha, 4).toString()));
        return produto;
    }

    private int ObterQuantidadeDoItem() {
        if ((jlListaProdutos.getSelectedRow() < 0)
                || (jlListaProdutos.getValueAt(jlListaProdutos.getSelectedRow(), 0) == null)) {
            JOptionPane.showMessageDialog(null, "Você precisa selecionar um Produto para Adicionar");
            return 0;
        } else {
            return Integer.parseInt(JOptionPane.showInputDialog("Informe quantas unidades do produto deseja adicionar ao Pedido"));
        }
    }

    private boolean verificarEstoque(Produto produto, int quantidade) {
        if ((quantidade > 0) && (produto.getEstoque() >= quantidade)) {
            return true;
        }
        return false;
    }

    private void adicionarNovoItem(Produto produto, int quantidade) {
        try {
            if (verificarEstoque(produto, quantidade)) {
                pedido.getItensPedido().add(new ItemPedido(quantidade * produto.getPreco(), quantidade, produto));
                jbComprar.setText("Comprar(" + pedido.getItensPedido().size() + ")");
            } else {
                JOptionPane.showMessageDialog(null, "A quantidade indicada não está no intervalo válido.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void incrementarQuantidadeDoItem(Produto produto, int quantidade) {
        if (verificarEstoque(produto, quantidade)) {
            for (ItemPedido ip : pedido.getItensPedido()) {
                if (ip.getProduto().getId() == produto.getId()) {
                    ip.setQuantidade(quantidade);
                    ip.setValor(ip.getProduto().getPreco() * quantidade);
                    return;
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Quantidade indicada não esta no intervalo válido");
        }
    }

    public int quantidadeNoPedido(int idProduto) {
        for (ItemPedido ip : pedido.getItensPedido()) {
            if (ip.getProduto().getId() == idProduto) {
                return ip.getQuantidade();
            }

        }
        return 0;
    }

    public void buscarProdutoID() {
        ProdutoService produtoService = new ProdutoService();
        Produto produto = new Produto();
        int id = Integer.parseInt(JOptionPane.showInputDialog("Informe o ID do Produto: "));
        produto = produtoService.buscarProduto(id);
        if (produto != null) {
            limparTabela();
            preencheTabela(produto, 0);
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum Produto encontrado com esse ID.");
        }
    }

    public void buscarProdutoDescricao() {
        List<Produto> produto = new ArrayList<>();
        ProdutoService produtoService = new ProdutoService();
        String descricao = JOptionPane.showInputDialog("Informe a descrição Produto: ");
        produto = produtoService.buscarProdutoNomeDescricao(descricao);

        if (!produto.isEmpty()) {
            preencheTabela(produto);
            limparTabela();
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum Produto encontrado!");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        jbNovoProduto = new javax.swing.JButton();
        jbEditarProduto = new javax.swing.JButton();
        jbExcluirProduto = new javax.swing.JButton();
        jbLocalizarProduto = new javax.swing.JButton();
        jbComprar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jlListaProdutos = new javax.swing.JTable();
        jbProximaPagina = new javax.swing.JButton();
        jlPagina = new javax.swing.JLabel();
        jbPaginaAnterior = new javax.swing.JButton();
        jbListar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jmiFinalizarPedido = new javax.swing.JMenuItem();
        jmiCarrinho = new javax.swing.JMenuItem();

        jMenu3.setText("jMenu3");

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("NOVO PRODUTO");

        jbNovoProduto.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jbNovoProduto.setText("NOVO");
        jbNovoProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbNovoProdutoActionPerformed(evt);
            }
        });

        jbEditarProduto.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jbEditarProduto.setText("EDITAR");
        jbEditarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEditarProdutoActionPerformed(evt);
            }
        });

        jbExcluirProduto.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jbExcluirProduto.setText("EXCLUIR");
        jbExcluirProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbExcluirProdutoActionPerformed(evt);
            }
        });

        jbLocalizarProduto.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jbLocalizarProduto.setText("LOCALIZAR");
        jbLocalizarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbLocalizarProdutoActionPerformed(evt);
            }
        });

        jbComprar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jbComprar.setText("COMPRAR");
        jbComprar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbComprarActionPerformed(evt);
            }
        });

        jlListaProdutos.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jlListaProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "NOME", "DESCRIÇÃO", "PREÇO", "ESTOQUE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jlListaProdutos);

        jbProximaPagina.setText(">>");
        jbProximaPagina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbProximaPaginaActionPerformed(evt);
            }
        });

        jlPagina.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlPagina.setText("1");

        jbPaginaAnterior.setText("<<");
        jbPaginaAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbPaginaAnteriorActionPerformed(evt);
            }
        });

        jbListar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jbListar.setText("LISTAR");
        jbListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbListarActionPerformed(evt);
            }
        });

        jMenu1.setText("ARQUIVO");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("EDITAR");
        jMenuBar1.add(jMenu2);

        jMenu4.setText("PEDIDO");

        jmiFinalizarPedido.setText("FINALIZAR PEDIDO");
        jmiFinalizarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiFinalizarPedidoActionPerformed(evt);
            }
        });
        jMenu4.add(jmiFinalizarPedido);

        jmiCarrinho.setText("CARRINHO");
        jmiCarrinho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiCarrinhoActionPerformed(evt);
            }
        });
        jMenu4.add(jmiCarrinho);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jbNovoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbEditarProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbExcluirProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbLocalizarProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbListar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 138, Short.MAX_VALUE)
                .addComponent(jbComprar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbPaginaAnterior)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlPagina)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbProximaPagina)
                .addGap(47, 47, 47))
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbNovoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbEditarProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbExcluirProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbLocalizarProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbComprar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbListar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbPaginaAnterior)
                    .addComponent(jbProximaPagina)
                    .addComponent(jlPagina))
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbEditarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEditarProdutoActionPerformed
        // TODO add your handling code here:

        if ((jlListaProdutos.getSelectedRow() < 0) || (jlListaProdutos.getValueAt(jlListaProdutos.getSelectedRow(), 0) == null)) {
            JOptionPane.showMessageDialog(null, "Você precisa selecionar um registro para Editar");
        } else {
            new NovoProdutoController(lerDadosDaLinhaSelecionada(), jlListaProdutos.getSelectedRow()).setVisible(true);
        }

    }//GEN-LAST:event_jbEditarProdutoActionPerformed

    private void jbNovoProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbNovoProdutoActionPerformed

        new NovoProdutoController().setVisible(true);

    }//GEN-LAST:event_jbNovoProdutoActionPerformed

    private void jbProximaPaginaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbProximaPaginaActionPerformed

        List<Produto> produtos = produtoService.buscarProdutos(Integer.parseInt(jlPagina.getText()));
        if (!produtos.isEmpty()) {
            limparTabela();
            preencheTabela(produtos);
            jlPagina.setText((Integer.parseInt(jlPagina.getText()) + 1) + "");
        }

    }//GEN-LAST:event_jbProximaPaginaActionPerformed

    private void jbPaginaAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPaginaAnteriorActionPerformed

        if (!jlPagina.getText().equals("1")) {

            int pagina = (Integer.parseInt(jlPagina.getText()) - 1);
            limparTabela();
            preencheTabela(produtoService.buscarProdutos(pagina - 1));
            jlPagina.setText((Integer.parseInt(jlPagina.getText()) + 1) + "");
            jlPagina.setText(pagina + "");
        }

    }//GEN-LAST:event_jbPaginaAnteriorActionPerformed

    private void jbComprarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbComprarActionPerformed
        Produto produto = lerDadosDaLinhaSelecionada();
        int quantidade = ObterQuantidadeDoItem();

        int quantidadeNoPedido = quantidadeNoPedido(produto.getId());
        if (quantidadeNoPedido != 0) {
            incrementarQuantidadeDoItem(produto, quantidade + quantidadeNoPedido);
        } else {
            adicionarNovoItem(produto, quantidade);
        }


    }//GEN-LAST:event_jbComprarActionPerformed

    private void jbLocalizarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbLocalizarProdutoActionPerformed
        
        try {

            if (cliente.getTipo().equals(TipoDeCliente.ADMIN)) {
                buscarProdutoID();
            } else {
                buscarProdutoDescricao();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }


    }//GEN-LAST:event_jbLocalizarProdutoActionPerformed

    private void jbExcluirProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbExcluirProdutoActionPerformed

        if ((jlListaProdutos.getSelectedRow() < 0) || (jlListaProdutos.getValueAt(jlListaProdutos.getSelectedRow(), 0) == null)) {
            JOptionPane.showMessageDialog(null, "Você precisa selecionar um registro para Editar");
        } else if (JOptionPane.showConfirmDialog(null, "Voce tem certeza que deseja excluir o produto?\n"
                + lerDadosDaLinhaSelecionada()) == 0) {
            produtoService.excluirProduto(lerDadosDaLinhaSelecionada().getId());
            limparTabela();
            preencheTabela(produtoService.buscarProdutos(0));
        }

    }//GEN-LAST:event_jbExcluirProdutoActionPerformed

    private void jbListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbListarActionPerformed

        limparTabela();
        preencheTabela(produtoService.buscarProdutos(0));

    }//GEN-LAST:event_jbListarActionPerformed

    private void jmiFinalizarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiFinalizarPedidoActionPerformed

        if (pedido.getItensPedido().size() > 0) {
            PedidoService pedidoService = new PedidoService();
            pedidoService.salvarPedido(pedido);
            limparTabela();
            preencheTabela(produtoService.buscarProdutos(0));
            jbComprar.setText("COMPRAR");
        } else {
            JOptionPane.showMessageDialog(null, "Você não tem produtos no carrinho");
        }

    }//GEN-LAST:event_jmiFinalizarPedidoActionPerformed

    private void jmiCarrinhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiCarrinhoActionPerformed
        // TODO add your handling code here:
        new CarrinhoController(pedido).setVisible(true);

    }//GEN-LAST:event_jmiCarrinhoActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProdutoController().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbComprar;
    private javax.swing.JButton jbEditarProduto;
    private javax.swing.JButton jbExcluirProduto;
    private javax.swing.JButton jbListar;
    private javax.swing.JButton jbLocalizarProduto;
    private javax.swing.JButton jbNovoProduto;
    private javax.swing.JButton jbPaginaAnterior;
    private javax.swing.JButton jbProximaPagina;
    private javax.swing.JTable jlListaProdutos;
    private javax.swing.JLabel jlPagina;
    private javax.swing.JMenuItem jmiCarrinho;
    private javax.swing.JMenuItem jmiFinalizarPedido;
    // End of variables declaration//GEN-END:variables

}
