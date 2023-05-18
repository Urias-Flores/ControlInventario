package Views.Panels.Estadisticas;

import ViewsControllers.Panels.Estadisticas.ResumenesViewController;
import java.awt.Font;
import javax.swing.JLabel;

public class Resumenes extends javax.swing.JPanel {

    private ResumenesViewController vc;
    
    public Resumenes() {
        initComponents();
        vc = new ResumenesViewController(lbCargando, cmbTipo, cmbTiempo, pnGrafico);
        vc.loadDayGrafic();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cmbTipo = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cmbTiempo = new javax.swing.JComboBox<>();
        pnGrafico = new javax.swing.JPanel();
        lbCargando = new javax.swing.JLabel();
        btnActualizar = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        jLabel1.setText("Registro de:");

        cmbTipo.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ventas", "Compras", "Ventas y compras" }));
        cmbTipo.setPreferredSize(new java.awt.Dimension(246, 38));
        cmbTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipoActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        jLabel3.setText("En un lapso de tiempo de:");

        cmbTiempo.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        cmbTiempo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hoy", "Ultima semana", "Ultimo mes", "Ultimo año" }));
        cmbTiempo.setPreferredSize(new java.awt.Dimension(246, 38));
        cmbTiempo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTiempoActionPerformed(evt);
            }
        });

        pnGrafico.setBackground(new java.awt.Color(255, 255, 255));
        pnGrafico.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 220, 220)));
        pnGrafico.setLayout(new java.awt.CardLayout());

        lbCargando.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btnActualizar.setBackground(new java.awt.Color(255, 255, 255));
        btnActualizar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/actualizar.png"))); // NOI18N
        btnActualizar.setToolTipText("Actualizar gráfico");
        btnActualizar.setOpaque(true);
        btnActualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnActualizarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnGrafico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cmbTipo, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cmbTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 446, Short.MAX_VALUE)
                                .addComponent(lbCargando, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnActualizar)))))
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbCargando, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbTiempo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(18, 18, 18)
                .addComponent(pnGrafico, javax.swing.GroupLayout.DEFAULT_SIZE, 634, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmbTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoActionPerformed
        cargarGrafico();
    }//GEN-LAST:event_cmbTipoActionPerformed

    private void cmbTiempoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTiempoActionPerformed
        cargarGrafico();
    }//GEN-LAST:event_cmbTiempoActionPerformed

    private void btnActualizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActualizarMouseClicked
        cargarGrafico();
    }//GEN-LAST:event_btnActualizarMouseClicked
    
    public void cargarGrafico(){
        pnGrafico.removeAll();
        JLabel noDisponible = new JLabel("grafico aun no disponible");
        noDisponible.setFont(new Font("Roboto", Font.BOLD, 26));
        noDisponible.setHorizontalAlignment(0);
        
        switch (cmbTiempo.getSelectedIndex()) {
            case 0:
                vc.loadDayGrafic();
                break;
            case 1:
                vc.loadWeekGhrapic();
                break;
            case 2:
                vc.loadMonthGrafic();
                break;
            case 3:
                vc.loadYearGrafic();
                break;
            case 4:
                pnGrafico.add(noDisponible);
                break;
            default:
                throw new AssertionError();
        }
        pnGrafico.revalidate();
        pnGrafico.repaint();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnActualizar;
    private javax.swing.JComboBox<String> cmbTiempo;
    private javax.swing.JComboBox<String> cmbTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lbCargando;
    private javax.swing.JPanel pnGrafico;
    // End of variables declaration//GEN-END:variables
}
