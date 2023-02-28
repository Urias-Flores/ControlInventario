package ViewsControllers.Panels.Ajustes;

import Controllers.ConfiguracionJpaController;
import Models.Configuracion;
import Resource.Conection;
import Resource.LocalDataController;
import Resource.Utilities;
import Views.Dialogs.Dialogs;
import java.awt.Color;
import java.util.Date;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class EmpresaViewController {
    
    private JTextField Nombre;
    private JTextField RTN;
    private JTextField Telefono;
    private JTextArea Direccion;
    private JTextField CAI;
    private JTextField Correo;
    private JTextField Desde;
    private JTextField Hasta;
    private JComboBox Dia;
    private JComboBox Mes;
    private JComboBox Anio;
    
    private JLabel Error;
    private JLabel Cargando;

    public EmpresaViewController(JTextField Nombre, JTextField RTN, JTextField Telefono, JTextArea Direccion, 
            JTextField CAI, JTextField Correo, JTextField Desde, JTextField Hasta, JComboBox Dia, JComboBox Mes, JComboBox Anio,
            JLabel Error, JLabel Cargando) {
        this.Nombre = Nombre;
        this.RTN = RTN;
        this.Telefono = Telefono;
        this.Direccion = Direccion;
        this.CAI = CAI;
        this.Correo = Correo;
        this.Desde = Desde;
        this.Hasta = Hasta;
        this.Dia = Dia;
        this.Mes = Mes;
        this.Anio = Anio;

        this.Error = Error;
        this.Cargando = Cargando;
        
        loadActualInformation();
    }
    
    private void setLoad(boolean state){
        Icon icon = new ImageIcon(getClass().getResource(Utilities.getLoadingImage()));
        Cargando.setIcon(state ? icon : null);
    }
    
    //Task
    private void loadActualInformation(){
        setLoad(true);
        Runnable run = () ->{
            LocalDataController ldc = new LocalDataController();
            //Obteniendo informacion del archivo de configuracion local
            Nombre.setText(ldc.getValue("Company"));
            RTN.setText(ldc.getValue("RTN"));
            Telefono.setText(ldc.getValue("NumberPhone"));
            Direccion.setText(ldc.getValue("Address"));

            List<Configuracion> configurations = new ConfiguracionJpaController(Conection.createEntityManagerFactory())
                    .findConfiguracionEntities();
            //Obteniendo informacion de base de datos
            CAI.setText(configurations.get(4).getDato());
            Correo.setText(configurations.get(5).getDato());
            Desde.setText(configurations.get(1).getDato());
            Hasta.setText(configurations.get(2).getDato());

            String[] fecha = configurations.get(3).getDato().split("/");
            Dia.setSelectedIndex(Integer.parseInt(fecha[0]) - 1);
            Mes.setSelectedIndex(Integer.parseInt(fecha[1]) - 1);
            Anio.setSelectedItem(fecha[2]);

            paintBlack();
            setLoad(false);
        };
        new Thread(run).start();
    }

    //Task
    public void saveInformation(){
        if(validateLocalInformation() && validateDBInformation()){
            setLoad(true);
            Runnable run = () ->{
                saveLocalInformation();
                saveDBInformation();
                
                setLoad(false);
                Error.setBackground(Color.white);
                loadActualInformation();
            };
            new Thread(run).start();
        } else {
            Error.setBackground(new Color(185, 0, 0));
        }
    }
    
    private void saveLocalInformation(){
        LocalDataController ldc = new LocalDataController();
        ldc.UpdateData("Company", Nombre.getText());
        ldc.UpdateData("RTN", RTN.getText());
        ldc.UpdateData("NumberPhone", Telefono.getText());
        ldc.UpdateData("Address", Direccion.getText());
    }
    
    //Only inside task
    private void saveDBInformation(){
        if(validateDBInformation()){
            try {
                ConfiguracionJpaController controller = new ConfiguracionJpaController(Conection.createEntityManagerFactory());
                Configuracion configDesde = controller.findConfiguracion(2);
                Configuracion configHasta = controller.findConfiguracion(3);
                Configuracion configFecha = controller.findConfiguracion(4);
                Configuracion configCAI = controller.findConfiguracion(5);
                Configuracion configEmail = controller.findConfiguracion(6);
                
                configCAI.setDato(CAI.getText());
                configEmail.setDato(Correo.getText());
                
                configDesde.setDato(Desde.getText());
                configDesde.setExtra(String.valueOf(Integer.parseInt(Desde.getText().split("-")[3])));
                
                configHasta.setDato(Hasta.getText());
                configHasta.setExtra(String.valueOf(Integer.parseInt(Hasta.getText().split("-")[3])));
                
                int anio = Integer.parseInt(Anio.getSelectedItem().toString());
                int mes = Mes.getSelectedIndex() + 1;
                int dia = Dia.getSelectedIndex() + 1;
                configFecha.setDato(dia+"/"+mes+"/"+anio);
                
                controller.edit(configCAI);
                controller.edit(configEmail);
                controller.edit(configDesde);
                controller.edit(configHasta);
                controller.edit(configFecha);
                
                setLoad(false);
                Error.setBackground(Color.white);
                Dialogs.ShowMessageDialog("Toda la informacion ha sido actualizada exitosamente", Dialogs.COMPLETE_ICON);
            } catch (Exception ex) {
                System.err.println("Error al editar configuracion: "+ex.getMessage());
                Dialogs.ShowMessageDialog("Ah ecurrido un error al editar una coniguracion", Dialogs.ERROR_ICON);
            }
        }
    }
    
    private boolean validateLocalInformation(){
        Color emptyColor = new Color(180, 180, 180);
        if(Nombre.getText().isEmpty() || Nombre.getForeground().equals(emptyColor)){
            Error.setText("El nombre de la empresa es obligatorio");
            return false;
        }
        if(RTN.getText().isEmpty() || RTN.getForeground().equals(emptyColor)){
            Error.setText("El RTN de la empresa es obligatorio");
            return false;
        }
        if(Telefono.getText().isEmpty() || Telefono.getForeground().equals(emptyColor)){
            Error.setText("El Número teléfonico de la empresa es obligatorio");
            return false;
        }
        if(Direccion.getText().isEmpty() || Direccion.getForeground().equals(emptyColor)){
            Error.setText("El Dirección de la empresa es obligatorio");
            return false;
        }
        return true;
    }
    
    private boolean validateDBInformation(){
        Color emptyColor = new Color(180, 180, 180);
        if(Correo.getText().isEmpty() || Correo.getForeground().equals(emptyColor)){
            Error.setText("El correo electronico es obligatorio");
            return false;
        }
        if(!Correo.getText().contains("@") || !Correo.getText().contains(".")){
            Error.setText("El correo electronico ingresado no es valido");
            return false;
        }
        if(!validateCAI() || !validateRangoDesde() || !validateRangoHasta()){
            return false;
        }
        return validateFechaEmision();
    }
    
    private boolean validateCAI(){
        Color emptyColor = new Color(180, 180, 180);
        if(CAI.getText().isEmpty() || CAI.getForeground().equals(emptyColor)){
            Error.setText("El número codigo CAI es obligatorio");
            return false;
        }
        if(CAI.getText().length() < 37){
            Error.setText("El codigo CAI debe contener al menos 37 caracteres");
            return false;
        }
        String[] cai = CAI.getText().split("-");
        if(cai.length != 6){
            Error.setText("El número codigo CAI no es valido");
            return false;
        }
        for(int i = 0; i < cai.length; i++){
            if((cai[i].length() != 6) && (i != cai.length - 1)){
                Error.setText("El número codigo CAI no es valido");
                return false;
            }
        }
        return true;
    }
    
    private boolean validateRangoDesde(){
        Color emptyColor = new Color(180, 180, 180);
        if(Desde.getText().isEmpty() || Desde.getForeground().equals(emptyColor)){
            Error.setText("El inicio de rango de facturación es obligatorio");
            return false;
        }
        String[] desde = Desde.getText().split("-");
        if(desde.length != 4){
            Error.setText("El inicio de rango de facturación no es valido");
            return false;
        }
        if(desde[0].length() != 3 || desde[1].length() != 3 || desde[2].length() != 2 || desde[3].length() != 8){
            Error.setText("El inicio de rango de facturación no es valido");
            return false;
        }
        for(String fragment : desde){
            try {
                int number = Integer.parseInt(fragment);
            } catch (NumberFormatException e) {
                Error.setText("El inicio de rango solo puede contener números");
                return false;
            }
        }
        return true;
    }
    
    private boolean validateRangoHasta(){
        Color emptyColor = new Color(180, 180, 180);
        if(Hasta.getText().isEmpty() || Hasta.getForeground().equals(emptyColor)){
            Error.setText("El final de rango de facturación es obligatorio");
            return false;
        }
        String[] hasta = Hasta.getText().split("-");
        if(hasta.length != 4){
            Error.setText("El final de rango de facturación no es valido");
            return false;
        }
        if(hasta[0].length() != 3 || hasta[1].length() != 3 || hasta[2].length() != 2 || hasta[3].length() != 8){
            Error.setText("El final de rango de facturación no es valido");
            return false;
        }
        for(String fragment : hasta){
            try {
                int number = Integer.parseInt(fragment);
            } catch (NumberFormatException e) {
                Error.setText("El final de rango solo puede contener números");
                return false;
            }
        }
        return true;
    }
    
    private void paintBlack(){
        Nombre.setForeground(Color.BLACK);
        RTN.setForeground(Color.BLACK);
        Telefono.setForeground(Color.BLACK);
        Direccion.setForeground(Color.BLACK);
        CAI.setForeground(Color.BLACK);
        Correo.setForeground(Color.BLACK);
        Desde.setForeground(Color.BLACK);
        Hasta.setForeground(Color.BLACK);
    }
    
    private boolean validateFechaEmision(){
        int anio = Integer.parseInt(Anio.getSelectedItem().toString());
        int mes = Mes.getSelectedIndex();
        int dia = Dia.getSelectedIndex() + 1;
        Date date = new Date(anio-1900, mes, dia);
        if(date.before(Utilities.getDate())){
            Error.setText("La fecha limite de emision no es valida");
            return false;
        }
        return true;
    }
}
