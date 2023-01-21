package ViewsControllers.Components;

import Controllers.NotificacionJpaController;
import Models.Notificacion;
import Resource.Conection;
import java.text.SimpleDateFormat;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class NotificacionComponentViewController {
    
    private NotificacionJpaController controller;
    
    private JLabel Titulo;
    private JTextArea Contenido;
    private JLabel FechaHora;

    public NotificacionComponentViewController(JLabel Titulo, JTextArea Contenido, JLabel FechaHora) {
        this.Titulo = Titulo;
        this.Contenido = Contenido;
        this.FechaHora = FechaHora;
        
        controller = new NotificacionJpaController(Conection.CreateEntityManager());
    }
    
    public void cargarNotificacion(int NotificacionID){
        Notificacion notificacion = controller.findNotificacion(NotificacionID);
        
        Titulo.setText(notificacion.getTitulo());
        Contenido.setText(notificacion.getContenido());
        SimpleDateFormat formaterFecha = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formaterHora = new SimpleDateFormat("HH:mm");
        FechaHora.setText(formaterFecha.format(notificacion.getFecha()) + " " + formaterHora.format(notificacion.getHora()));
    }
}
