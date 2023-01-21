
package Views.Components;

import ViewsControllers.Components.NotificacionComponentViewController;

public class NotificacionComponent extends javax.swing.JPanel {

    private NotificacionComponentViewController vc;
    
    public NotificacionComponent() {
        initComponents();
        
        vc = new NotificacionComponentViewController(lbTitulo, txtContenido, lbHoraFecha);
    }
    
    public void cargarNotificacion(int NotificacionID){
        vc.cargarNotificacion(NotificacionID);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbTitulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtContenido = new javax.swing.JTextArea();
        lbHoraFecha = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(642, 180));
        setMinimumSize(new java.awt.Dimension(602, 170));
        setPreferredSize(new java.awt.Dimension(642, 180));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 220, 220)));
        jPanel1.setMaximumSize(new java.awt.Dimension(32767, 168));
        jPanel1.setMinimumSize(new java.awt.Dimension(0, 0));
        jPanel1.setPreferredSize(new java.awt.Dimension(567, 168));

        lbTitulo.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        lbTitulo.setText("Titulo");

        jScrollPane1.setBorder(null);

        txtContenido.setEditable(false);
        txtContenido.setBackground(new java.awt.Color(255, 255, 255));
        txtContenido.setColumns(20);
        txtContenido.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtContenido.setLineWrap(true);
        txtContenido.setText("Contenido");
        txtContenido.setWrapStyleWord(true);
        txtContenido.setBorder(null);
        jScrollPane1.setViewportView(txtContenido);

        lbHoraFecha.setFont(new java.awt.Font("Roboto", 0, 17)); // NOI18N
        lbHoraFecha.setForeground(new java.awt.Color(102, 102, 102));
        lbHoraFecha.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbHoraFecha.setText("1/20/2023 8:55");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE)
                    .addComponent(lbHoraFecha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbHoraFecha))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbHoraFecha;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JTextArea txtContenido;
    // End of variables declaration//GEN-END:variables
}
