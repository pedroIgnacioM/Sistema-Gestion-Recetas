import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

public class EnviarContrasenaCorreo{

    public static String correo = "projectGRDC@gmail.com";
    public static String contrasena = "GRDCproject1234";
    private String mensaje;
    private String destinatario;
    private String asunto;

    
    
    
    
    
    
    
    
    
    // Autentifica los datos de smtp y luego envia un mensaje con la contraseña al correo del usuario
    public void SendMail() 
    {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
        	new javax.mail.Authenticator() {
            	protected PasswordAuthentication getPasswordAuthentication() {
            		return new PasswordAuthentication(correo, contrasena);
            	}
            });
        session.setDebug(true);

        try {
        	
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(correo));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(destinatario));
            message.setSubject(asunto);
            message.setText(mensaje);

            Transport.send(message);
            JOptionPane.showMessageDialog(null, "La contraseña se ha enviado a su correo");
        } catch (MessagingException e) {
        	JOptionPane.showMessageDialog(null, "No se ha enviado el mensaje");
            throw new RuntimeException(e);
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //Crea el mensaje y llama a la funcion que lo envia
    public void crearMensaje(Universidad universidad,String correoUsuarioBuscado, int posUsuarioRecuperado) 
	{	
		mensaje = "Hola, "+universidad.getListaAlumnos().getArrayAlumnos().get(posUsuarioRecuperado).getNombrePersona()+":\n"
				+ "Su contraseña para ingresar a la Pontificia Universidad Católica de Gastronomia es "+universidad.getListaAlumnos().getArrayAlumnos().get(posUsuarioRecuperado).getContrasena()
				+".\n\nAtentamente,\nEl Equipo de GRDC";
		destinatario = correoUsuarioBuscado;
		asunto = "Ud. ha solicitado recuperar contraseña";
		SendMail();
		
	}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
 

}
