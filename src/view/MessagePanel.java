/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.JButton;
import javax.swing.JLabel;
import model.Message;

/**
 *
 * @author Ayoze Gil Sosa
 */
public class MessagePanel extends javax.swing.JPanel {

    private Message message;
    
    /**
     * Creates new form MessagePanel
     */
    public MessagePanel() {
        initComponents();
    }

    public MessagePanel(Message m) {
        initComponents();
        this.message = m;
        initProcess();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblMessage = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();
        btnModify = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();

        setMaximumSize(new java.awt.Dimension(32767, 35));
        setMinimumSize(new java.awt.Dimension(300, 35));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(486, 35));

        lblMessage.setText("Mensaje");

        btnDelete.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 0, 0));
        btnDelete.setText("x");
        btnDelete.setActionCommand("Delete_Message");
        btnDelete.setMargin(new java.awt.Insets(2, 2, 2, 2));

        btnModify.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnModify.setForeground(new java.awt.Color(0, 0, 255));
        btnModify.setText("M");
        btnModify.setActionCommand("Modify_Message");
        btnModify.setMargin(new java.awt.Insets(2, 2, 2, 2));

        jPanel2.setMaximumSize(new java.awt.Dimension(73, 32767));
        jPanel2.setMinimumSize(new java.awt.Dimension(73, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 73, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 9, Short.MAX_VALUE)
        );

        jPanel3.setMaximumSize(new java.awt.Dimension(5, 30));
        jPanel3.setMinimumSize(new java.awt.Dimension(5, 30));
        jPanel3.setPreferredSize(new java.awt.Dimension(5, 30));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 64, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btnModify, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14)
                .addComponent(lblMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDelete)
                            .addComponent(btnModify)
                            .addComponent(lblMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnModify;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblMessage;
    // End of variables declaration//GEN-END:variables

    private void initProcess() {
        this.getLblMessage().setText("@"+message.getUser().getUsername()+": "+message.getContent());
    }
        
    
    public Message getMessage() {
        return message;
    }

    public void setMessage(Message m) {
        this.message = m;
    }

    public JButton getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(JButton btnDelete) {
        this.btnDelete = btnDelete;
    }

    public JButton getBtnModify() {
        return btnModify;
    }

    public void setBtnModify(JButton btnModify) {
        this.btnModify = btnModify;
    }

    public JLabel getLblMessage() {
        return lblMessage;
    }

    public void setLblMessage(JLabel lblMessage) {
        this.lblMessage = lblMessage;
    }

    




}