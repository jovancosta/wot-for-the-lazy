package info.walltime.bitcoinwot;

import static info.walltime.bitcoinwot.BitcoinWot.BOT;
import java.awt.Cursor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import org.bitcoinj.core.Address;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.params.MainNetParams;

public class Password extends javax.swing.JFrame {

    public Password() {
        BitcoinWot.PASSWORD = this;
        
        initComponents();
        BitcoinWot.centreWindow(this);
       
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Autenticação");

        jLabel1.setText("Digite a sua senha:");

        jButton1.setText("Entrar no canal #bitcoin-otc...");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPasswordField1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        jButton1.setEnabled(false);
        setCursor(new Cursor(Cursor.WAIT_CURSOR));

        BitcoinWot.PASSWORD_STRING = new String(jPasswordField1.getPassword());

        BitcoinWot.BOT.sendMessage("nickserv", "REGAIN " 
                + BitcoinWot.LOGIN.getjTextField1().getText()
                + " " + new String(jPasswordField1.getPassword()));

        BitcoinWot.BOT.joinChannel("#bitcoin-otc");

        try {
            ECKey generatedKey = new KeyDerivator(
                    BitcoinWot.LOGIN.getjTextField1().getText(), 
                    new String(jPasswordField1.getPassword())).generateKey();

            BitcoinWot.KEY = generatedKey;

            BitcoinWot.BOT.sendMessage("gribble", ";;bcauth " 
                    + BitcoinWot.LOGIN.getjTextField1().getText());
        } catch (Exception e) {
            e.printStackTrace();            

            JOptionPane.showMessageDialog(null, 
                    "Erro ao derivar as chaves: " + e);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPasswordField jPasswordField1;
    // End of variables declaration//GEN-END:variables
}
