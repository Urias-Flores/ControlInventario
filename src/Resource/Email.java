package Resource;

import Controllers.ConfiguracionJpaController;
import Models.Configuracion;
import Views.Dialogs.Dialogs;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
    
    private String[] getInformationForSendEmailOfDataBase(){
        Code code = new Code();
        Configuracion configuracion = new ConfiguracionJpaController(Conection.createEntityManagerFactory()).findConfiguracion(1);
        
        String email = code.decodeString(configuracion.getDato());
        String contrasena = code.decodeString(configuracion.getExtra());
        
        String[] values = {email, contrasena};
        return values;
    }
    
    private String[] getInformationForSendEmailOfLocalData(){
        LocalDataController ldc = new LocalDataController();
        String Email = ldc.getValue("Email");
        String Password = ldc.getValue("Password");
        Code code = new Code();
        String[] values = {code.decodeString(Email), code.decodeString(Password)};
        
        return values;
    }
    
    public boolean SendEmail(String CorreoDestino, String Asunto, String Mensaje)
    {
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.auth", "true");
        
        LocalDataController ldc = new LocalDataController();
        
        boolean state = Boolean.parseBoolean(ldc.getValue("PersonalizeEmail"));
        String[] InformationForSend = {};
        
        if(state){
            InformationForSend = getInformationForSendEmailOfLocalData();
        }else{
            InformationForSend = getInformationForSendEmailOfDataBase();
        }
        
        String CorreoRemitente = InformationForSend[0];
        String Contrasena  = InformationForSend[1];
        
        Session session = Session.getDefaultInstance(props);      
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(CorreoRemitente));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(CorreoDestino));
            message.setSubject(Asunto);
            message.setText(Mensaje, "ISO-8859-1", "html");
            try (Transport t = session.getTransport("smtp")){
                t.connect(CorreoRemitente, Contrasena);
                t.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            }
            System.out.print("Correo Enviado");
        } catch (MessagingException ex) {
            System.err.println("Message: "+ex.getMessage());
            Dialogs.ShowMessageDialog("Ups... Ha ocurrido un error correo no enviado", Dialogs.ERROR_ICON);
            return false;
        }
        return true;
    }
    
    /*
    public void SendEmailFile(String Correo_remitente, String Correo_destino, String Asunto, String Mensaje, String Archivo)
    {
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.auth", "true");
        
        Session session = Session.getDefaultInstance(props);      
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom( new InternetAddress(Correo_remitente));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(Correo_destino));
            message.setSubject(Asunto);
            message.setText(Mensaje);
            try (Transport t = session.getTransport("smtp"))
            {
                t.connect(Correo_remitente, "");
                t.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            }
            JOptionPane.showMessageDialog(null, "Mensaje Enviado");
        } catch (MessagingException ex) {
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    public void enviar_correo_masivo(String Correo_remitente, String[] Correo_destino, String Asunto, String Mensaje)
    {
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.auth", "true");
        
        Session session = Session.getDefaultInstance(props);      
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom( new InternetAddress(Correo_remitente));
            //message.addRecipient(Message.RecipientType.TO, new InternetAddress(Correo_destino));
            message.setSubject(Asunto);
            message.setText(Mensaje);
            try (Transport t = session.getTransport("smtp"))
            {
                t.connect(Correo_remitente, "");
                t.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            }
            JOptionPane.showMessageDialog(null, "Mensaje Enviado");
        } catch (MessagingException ex) {
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    */
    
    public String generateRecoverAccountMessage(String Empleado, String Cuenta, String Codigo){
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Document</title>\n" +
                "\n" +
                "    <style>\n" +
                "        body {\n" +
                "            background-color: #f5f5f5;\n" +
                "            font-family: Arial, Helvetica, sans-serif;\n" +
                "            line-height: 1.5;\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "\n" +
                "        .titulo{\n" +
                "            text-align: center;\n"+
                "            max-width: 500px;\n" +
                "            width: 90%;\n" +
                "            margin: 20px auto 0 auto;\n" +
                "            padding: 20px;\n" +
                "            background-color: rgb(3, 57, 103);\n" +
                "            border-radius: 15px 15px 0px 0px;\n" +
                "            box-shadow: 0px -2px 7px 0px rgba(166,166,166,1);\n" +
                "        }\n" +
                "\n" +
                "        .contenedor{\n" +
                "            text-align: center;\n"+
                "            max-width: 500px;\n" +
                "            width: 90%;\n" +
                "            margin: 0 auto;\n" +
                "            padding: 20px;\n" +
                "            border-radius: 0px 0px 15px 15px;\n" +
                "            background-color: white;\n" +
                "            box-shadow: 0px 2px 7px 0px rgba(166,166,166,1);\n" +
                "        }\n" +
                "\n" +
                "        h1 {\n" +
                "            width: 100%;\n" +
                "            color: #fff;\n" +
                "            margin: 0;\n" +
                "        }\n" +
                "\n" +
                "        p{\n" +
                "            color: #333;\n" +
                "            font-size: 18px;\n" +
                "        }\n" +
                "\n" +
                "        .codigo {\n" +
                "            text-align: center;\n" +
                "            width: 140px;\n" +
                "            background-color: rgb(3, 57, 103);\n" +
                "            color: white;\n" +
                "            padding: 10px;\n" +
                "            border-radius: 5px;\n" +
                "            font-size: 20px;\n" +
                "            font-weight: bold;\n" +
                "            margin: 0 auto;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "    <body>\n" +
                "        <div class=\"titulo\">\n" +
                "            <h1>Olvidaste tu contraseña</h1>\n" +
                "        </div>\n" +
                "\n" +
                "        <div class=\"contenedor\">\n" +
                "            <p>\n" +
                "                Hola, <strong>"+Empleado+"</strong> se ha solicitado el cambio de contraseña para tu cuenta \n" +
                "                <strong>"+Cuenta+"</strong>, para poder reestablecer se requiere unicamente del codigo de recuparacion que se te otorgara\n" +
                "                por medio de este correo.\n" +
                "            </p>\n" +
                "\n" +
                "            <p>\n" +
                "                Tu codigo de para la recuperacion de tu cuenta es el siguiente:\n" +
                "            </p>\n" +
                "\n" +
                "            <p class=\"contenedor-codigo\">\n" +
                "                <div class=\"codigo\">"+Codigo.replace("", " ")+"</div>\n" +
                "            </p>\n" +
                "\n" +
                "            <p>\n" +
                "                Te recomendamos no compartir este codigo con nadie. <br>\n" +
                "                Si no has solicitado el cambio de contraseña, por favor ignora este correo. <br>\n" +
                "            </p>\n" +
                "            </div>\n" +
                "    </body>\n" +
                "</html>";
    }
    
    public String generateCreateAccountMessage(String Empleado, String Cuenta, String Codigo){
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Document</title>\n" +
                "\n" +
                "    <style>\n" +
                "        body {\n" +
                "            background-color: #f5f5f5;\n" +
                "            font-family: Arial, Helvetica, sans-serif;\n" +
                "            line-height: 1.5;\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "\n" +
                "        .titulo{\n" +
                "            text-align: center;\n"+
                "            max-width: 500px;\n" +
                "            width: 90%;\n" +
                "            margin: 20px auto 0 auto;\n" +
                "            padding: 20px;\n" +
                "            background-color: rgb(3, 57, 103);\n" +
                "            border-radius: 15px 15px 0px 0px;\n" +
                "            box-shadow: 0px -2px 7px 0px rgba(166,166,166,1);\n" +
                "        }\n" +
                "\n" +
                "        .contenedor{\n" +
                "            text-align: center;\n"+
                "            max-width: 500px;\n" +
                "            width: 90%;\n" +
                "            margin: 0 auto;\n" +
                "            padding: 20px;\n" +
                "            border-radius: 0px 0px 15px 15px;\n" +
                "            background-color: white;\n" +
                "            box-shadow: 0px 2px 7px 0px rgba(166,166,166,1);\n" +
                "        }\n" +
                "\n" +
                "        h1 {\n" +
                "            width: 100%;\n" +
                "            color: #fff;\n" +
                "            margin: 0;\n" +
                "        }\n" +
                "\n" +
                "        p{\n" +
                "            color: #333;\n" +
                "            font-size: 18px;\n" +
                "        }\n" +
                "\n" +
                "        .codigo {\n" +
                "            text-align: center;\n" +
                "            width: 140px;\n" +
                "            background-color: rgb(3, 57, 103);\n" +
                "            color: white;\n" +
                "            padding: 10px;\n" +
                "            border-radius: 5px;\n" +
                "            font-size: 20px;\n" +
                "            font-weight: bold;\n" +
                "            margin: 0 auto;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "    <body>\n" +
                "        <div class=\"titulo\">\n" +
                "            <h1>Crea tu nueva cuenta</h1>\n" +
                "        </div>\n" +
                "\n" +
                "        <div class=\"contenedor\">\n" +
                "            <p>\n" +
                "                Hola, <strong>"+Empleado+"</strong> se ha solicitado la creacion de la cuenta \n" +
                "                <strong>"+Cuenta+"</strong>, para poder continuar con la creacion de la misma unicamente es nesesario del codigo de seguridad\n" +
                "                el cual sera otorgado por medio de este correo.\n" +
                "            </p>\n" +
                "\n" +
                "            <p>\n" +
                "                Tu codigo de para la creacion de tu cuenta es el siguiente:\n" +
                "            </p>\n" +
                "\n" +
                "            <p class=\"contenedor-codigo\">\n" +
                "                <div class=\"codigo\">"+Codigo.replace("", " ")+"</div>\n" +
                "            </p>\n" +
                "\n" +
                "            <p>\n" +
                "                Te recomendamos no compartir este codigo con nadie. <br>\n" +
                "                Si no has solicitado la creacion de tu cuenta, por favor ignora este correo. <br>\n" +
                "            </p>\n" +
                "            </div>\n" +
                "    </body>\n" +
                "</html>";
    }
    
    public String generateTryAccountMessage(String Empleado, String Cuenta){
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Document</title>\n" +
                "\n" +
                "    <style>\n" +
                "        body {\n" +
                "            background-color: #f5f5f5;\n" +
                "            font-family: Arial, Helvetica, sans-serif;\n" +
                "            line-height: 1.5;\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "\n" +
                "        .titulo{\n" +
                "            text-align: center;\n"+
                "            max-width: 500px;\n" +
                "            width: 90%;\n" +
                "            margin: 20px auto 0 auto;\n" +
                "            padding: 20px;\n" +
                "            background-color: rgb(3, 57, 103);\n" +
                "            border-radius: 15px 15px 0px 0px;\n" +
                "            box-shadow: 0px -2px 7px 0px rgba(166,166,166,1);\n" +
                "        }\n" +
                "\n" +
                "        .contenedor{\n" +
                "            text-align: center;\n"+
                "            max-width: 500px;\n" +
                "            width: 90%;\n" +
                "            margin: 0 auto;\n" +
                "            padding: 20px;\n" +
                "            border-radius: 0px 0px 15px 15px;\n" +
                "            background-color: white;\n" +
                "            box-shadow: 0px 2px 7px 0px rgba(166,166,166,1);\n" +
                "        }\n" +
                "\n" +
                "        h1 {\n" +
                "            width: 100%;\n" +
                "            color: #fff;\n" +
                "            margin: 0;\n" +
                "        }\n" +
                "\n" +
                "        p{\n" +
                "            color: #333;\n" +
                "            font-size: 18px;\n" +
                "        }\n" +
                "\n" +
                "        .codigo {\n" +
                "            text-align: center;\n" +
                "            width: 140px;\n" +
                "            background-color: rgb(3, 57, 103);\n" +
                "            color: white;\n" +
                "            padding: 10px;\n" +
                "            border-radius: 5px;\n" +
                "            font-size: 20px;\n" +
                "            font-weight: bold;\n" +
                "            margin: 0 auto;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "    <body>\n" +
                "        <div class=\"titulo\">\n" +
                "            <h1>Crea tu nueva cuenta</h1>\n" +
                "        </div>\n" +
                "\n" +
                "        <div class=\"contenedor\">\n" +
                "            <p>\n" +
                "                Hola, <strong>"+Empleado+"</strong> se ha solicitado el cambio de correo electronico \n" +
                "                para el envio de correos por medio de la cuenta<strong>"+Cuenta+"</strong>.\n" +
                "            </p>\n" +
                "        </div>\n" +
                "    </body>\n" +
                "</html>";
    }
}
