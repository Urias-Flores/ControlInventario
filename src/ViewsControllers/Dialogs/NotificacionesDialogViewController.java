
package ViewsControllers.Dialogs;

import Controllers.LocalDataController;
import Controllers.NotificacionJpaController;
import Models.Notificacion;
import Resource.Conection;
import Views.Components.NotificacionComponent;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JPanel;

public class NotificacionesDialogViewController {
    
    private NotificacionJpaController controller;
    private JPanel Notificaciones;

    public NotificacionesDialogViewController(JPanel Notificaciones) {
        this.Notificaciones = Notificaciones;
        
        controller = new NotificacionJpaController(Conection.CreateEntityManager());
    }
    
    public void cargarNotificaciones(){
        List<Object[]> notificaciones = Conection.CreateEntityManager().createEntityManager()
                .createNativeQuery("SELECT * FROM Notificacion ORDER BY Fecha desc , Hora desc")
                .getResultList();
        
        notificaciones.forEach(Item -> {
            Notificacion notificacion = new Notificacion(Integer.valueOf(Item[0].toString()));
            NotificacionComponent nc = new NotificacionComponent();
            nc.cargarNotificacion(notificacion.getNotificacionID());
            nc.setPreferredSize(new Dimension(567, 137));
            Notificaciones.add(nc);
            
            Notificaciones.revalidate();
            Notificaciones.repaint();
        });
    }
}
