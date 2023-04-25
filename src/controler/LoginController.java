package controler;

import entity.Cliente;
import entity.TipoDeCliente;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import service.ClienteService;

public class LoginController extends javax.swing.JFrame {

    public LoginController() {
        initComponents();
        this.setLocationRelativeTo(null);

    }

    public LoginController(Cliente cliente) {

    }

    public void validarLogin(Cliente cliente) {
        ClienteService clienteService = new ClienteService();
        cliente = clienteService.validarLogin(cliente);

        if (cliente != null) {
            if (cliente.getTipo().equals(TipoDeCliente.CLIENTE)) {
                new ProdutoController(cliente).setVisible(true);
                this.dispose();
            } else {
                new ProdutoController().setVisible(true);
                this.dispose();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possível fazer login!" + "\nEmail ou senha Incorretos!");

        }
    }
    private void cadastrarLogin(Cliente cliente) {
        ClienteService clienteService = new ClienteService();
        cliente.setTipo(TipoDeCliente.CLIENTE);
        cliente.setNome(jtfEmail.getText().substring(0, jtfEmail.getText().indexOf('@')));
        cliente = clienteService.salvarCliente(cliente);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlTitulo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jbEntrar = new javax.swing.JButton();
        jlCadastrar = new javax.swing.JLabel();
        jbCancelar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jtfEmail = new javax.swing.JTextField();
        jtfSenha = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jlTitulo.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jlTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlTitulo.setText("Usuário");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Email:");

        jbEntrar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbEntrar.setText("Entrar");
        jbEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEntrarActionPerformed(evt);
            }
        });

        jlCadastrar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlCadastrar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlCadastrar.setText("Cadastrar Novo Cliente");
        jlCadastrar.setToolTipText("Cadastar um novo cliente!!");
        jlCadastrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlCadastrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlCadastrarMouseClicked(evt);
            }
        });

        jbCancelar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbCancelar.setText("Cancelar");
        jbCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCancelarActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Senha:");

        jtfEmail.setText("tbabichcuk@gmail.com");

        jtfSenha.setText("2005");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jtfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jtfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(170, 170, 170)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jbEntrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jbCancelar))))
                        .addGap(0, 48, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(142, 142, 142)
                .addComponent(jlCadastrar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jlTitulo)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addComponent(jbEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jlCadastrar)
                .addGap(18, 18, 18)
                .addComponent(jbCancelar)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEntrarActionPerformed

        Cliente cliente = new Cliente();
        cliente.setEmail(jtfEmail.getText());
        cliente.setSenha(jtfSenha.getText());

        if (jbEntrar.getText().equals("Entrar")) {
            validarLogin(cliente);
        } else {
            cadastrarLogin(cliente);
        }
    }//GEN-LAST:event_jbEntrarActionPerformed

    private void jbCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelarActionPerformed
        System.exit(WIDTH);

    }//GEN-LAST:event_jbCancelarActionPerformed

    private void jlCadastrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlCadastrarMouseClicked
        jtfEmail.setText("");
        jtfSenha.setText("");
        jlTitulo.setText("Cadastrar novo usuário");
        jbEntrar.setText("Cadastrar");
        jlCadastrar.setText("");


    }//GEN-LAST:event_jlCadastrarMouseClicked

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.fast.FastLookAndFeel");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginController().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JButton jbCancelar;
    private javax.swing.JButton jbEntrar;
    private javax.swing.JLabel jlCadastrar;
    private javax.swing.JLabel jlTitulo;
    private javax.swing.JTextField jtfEmail;
    private javax.swing.JPasswordField jtfSenha;
    // End of variables declaration//GEN-END:variables

}
