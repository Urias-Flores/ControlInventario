package Resource;

import Controllers.UsuarioJpaController;
import Models.Usuario;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Utilities {

    private static Usuario UsuarioActual = new Usuario();
    private static boolean runProcess = true;
    private static boolean stateProcess = false;
    
    private static MouseListener mlButtonClose = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            JLabel l = (JLabel) e.getComponent();
            l.setIcon(new ImageIcon(getClass().getResource("/Icons/closeWhite.png")));
            l.setBackground(Color.red);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            JLabel l = (JLabel) e.getComponent();
            l.setIcon(new ImageIcon(getClass().getResource("/Icons/closeBlue.png")));
            l.setBackground(Color.white);
        }
    };
    private static MouseListener mlGeneralButton = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            JLabel btn = (JLabel) e.getComponent();
            btn.setBackground(new Color(4, 78, 141));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            JLabel btn = (JLabel) e.getComponent();
            btn.setBackground(new Color(3, 57, 103));
        }
    };
    private static MouseListener mlGrayButton = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            JLabel l = (JLabel) e.getComponent();
            l.setBackground(new Color(210, 210, 210));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            JLabel l = (JLabel) e.getComponent();
            l.setBackground(Color.white);
        }
    };
    private static MouseListener mlButtonCloseBlue = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            JLabel l = (JLabel) e.getComponent();
            l.setBackground(Color.red);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            JLabel l = (JLabel) e.getComponent();
            l.setBackground(new Color(3, 57, 103));
        }
    };
    
    private static FocusListener flSearchTextField = new FocusListener() {
        @Override
        public void focusGained(FocusEvent e) {
            JTextField text = (JTextField) e.getComponent();
            if(text.getText().equals("Buscar...")){
                text.setForeground(Color.black);
                text.setText("");
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            JTextField text = (JTextField) e.getComponent();
            if(text.getText().isEmpty()){
                text.setForeground(new Color(180, 180, 180));
                text.setText("Buscar...");
            }
        }
    };
    private static FocusListener flPlaceHolderEffect = new FocusListener() {
        String placeHolder;
        @Override
        public void focusGained(FocusEvent e) {
            JTextField text = (JTextField) e.getComponent();
            if(text.getForeground().equals(new Color(180, 180, 180))){
                placeHolder = text.getText();
                text.setText("");
                text.setForeground(Color.black);
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            JTextField text = (JTextField) e.getComponent();
            if(text.getForeground().equals(Color.black) && text.getText().isEmpty()){
                text.setText(placeHolder);
                text.setForeground(new Color(180, 180, 180));
            }
        }
    };
    
    public static void CargarAnios(JComboBox cmb){
        Calendar FechaActual = Calendar.getInstance();
        int AnioActual = FechaActual.get(Calendar.YEAR);
        for(int i = AnioActual; i >= 1920; i--){
            cmb.addItem(i+"");
        }
    }

    public static boolean isRunProcess() {
        return runProcess;
    }

    public static void setRunProcess(boolean runProcess) {
        Utilities.runProcess = runProcess;
    }

    public static boolean isStateProcess() {
        return stateProcess;
    }

    public static void setStateProcess(boolean stateProcess) {
        Utilities.stateProcess = stateProcess;
    }
    
    public static Date getDate(){
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(calendar.get(Calendar.YEAR) - 1900, calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
        return date;
    }
    
    public static Time getTime(){
        Calendar calendar = Calendar.getInstance();
        Time time = new Time(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND));
        return time;
    }
    
    public static String getMonth(int Month){
        Map<Integer, String> month = new HashMap<>();
        month.put(1, "Enero");
        month.put(2, "Febrero");
        month.put(3, "Marzo");
        month.put(4, "Abril");
        month.put(5, "Mayo");
        month.put(6, "Junio");
        month.put(7, "Julio");
        month.put(8, "Agosto");
        month.put(9, "Septiembre");
        month.put(10, "Octubre");
        month.put(11, "Noviembre");
        month.put(12, "Diciembre");
        return month.get(Month);
    }
    
    public static void EditarTableHeader(JTable Tabla){
        Tabla.getTableHeader().setFont(new Font("Roboto", Font.BOLD, 22));
    }

    public static int IniciarSesion(String Nombre, String Contrasena) {
        UsuarioJpaController usuarioJpaController = new UsuarioJpaController(Conection.CreateEntityManager());
        List<Usuario> usuarios = usuarioJpaController.findUsuarioEntities();
        Usuario user = new Usuario();
        usuarios.forEach((usuario) -> {
            if (usuario.getNombre().equals(Nombre)) {
                user.setNombre(usuario.getNombre());
                if (Security.validatePassword(Contrasena, usuario.getContrasena())) {
                    
                    user.setUsuarioID(usuario.getUsuarioID());
                    user.setContrasena(usuario.getContrasena());
                    user.setEstado(usuario.getEstado());
                    user.setCargo(usuario.getCargo());
                }
                
            }
        });

        if (user.getNombre() != null) {
            
            if (user.getContrasena() != null) {
                
                if(user.getEstado() == 1){
                    UsuarioActual = user;
                    return user.getUsuarioID();
                }else{
                    return -2;
                }
                
            } else {
                
                return 0;
                
            }
        }
        return -1;
    }
    
    public static Usuario getUsuarioActual(){
        return UsuarioActual;
    }

    public static MouseListener getMLButtonClose() {
        return mlButtonClose;
    }
    
    public static MouseListener getMLButtonCloseBlue(){
        return mlButtonCloseBlue;
    }

    public static MouseListener getMLGeneralButton() {
        return mlGeneralButton;
    }
    
    public static MouseListener getMLGrayButton(){
        return mlGrayButton;
    }
    
    public static FocusListener getFLSearchTextField(){
        return flSearchTextField;
    }
    
    public static FocusListener getFLPlaceHolderEfect(){
        return flPlaceHolderEffect;
    }

}
