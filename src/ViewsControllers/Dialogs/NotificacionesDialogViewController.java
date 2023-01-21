
package ViewsControllers.Dialogs;

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
        List<Notificacion> notificaciones = controller.findNotificacionEntities(50, 0);
        
        notificaciones.forEach(notificacion -> {
            NotificacionComponent nc = new NotificacionComponent();
            nc.cargarNotificacion(notificacion.getNotificacionID());
            nc.setPreferredSize(new Dimension(567, 137));
            Notificaciones.add(nc);
            
            Notificaciones.revalidate();
            Notificaciones.repaint();
        });
    }
}
