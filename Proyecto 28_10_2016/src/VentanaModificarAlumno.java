import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;

public class VentanaModificarAlumno extends JFrame {

	private JPanel contentPane;
	
	public VentanaModificarAlumno(final Alumno usuario,final VentanaDocente ventanaAnterior){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 370, 293);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		final JLabel lblNombre = new JLabel("Nombre : "+usuario.getNombrePersona());
		lblNombre.setBounds(10, 30, 280, 14);
		contentPane.add(lblNombre);
		
		final JLabel lblDireccion = new JLabel("Direccion : "+usuario.getDireccion());
		lblDireccion.setBounds(10, 60, 280, 14);
		contentPane.add(lblDireccion);
		
		final JLabel lblRut = new JLabel("Rut : "+usuario.getRut());
		lblRut.setBounds(10, 90, 280, 14);
		contentPane.add(lblRut);
		
		final JLabel lblTelefono = new JLabel("Telefono : "+usuario.getTelefono());
		lblTelefono.setBounds(10, 150, 280, 14);
		contentPane.add(lblTelefono);
		
		final JLabel lblEdad = new JLabel("Edad : "+usuario.getEdad());
		lblEdad.setBounds(10, 120, 201, 14);
		contentPane.add(lblEdad);
		
		final JLabel lblCorreo = new JLabel("Correo : "+usuario.getCorreo());
		lblCorreo.setBounds(10, 180, 280, 14);
		contentPane.add(lblCorreo);
		
		JButton btnCerrarSesion = new JButton("Cerrar Sesi\u00F3n");
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCerrarSesion.setBounds(239, 219, 110, 31);
		contentPane.add(btnCerrarSesion);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				dispose();
				ventanaAnterior.setVisible(true);
			}
		});
		btnAtras.setBounds(10, 219, 110, 31);
		contentPane.add(btnAtras);
		
//------------------------------------------------------------------------------------------------------------------------	
												//MODIFICAR NOMBRE
		final JLabel lblmodificarNombre = new JLabel("[Modificar]");
		lblmodificarNombre.setForeground(Color.BLUE);
		lblmodificarNombre.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblmodificarNombre.setBounds(300, 30, 49, 14);
		contentPane.add(lblmodificarNombre);
		
													//Modifica el nombre al presionar en el label [Modificar]
		lblmodificarNombre.addMouseListener(new MouseListener() 
		{
			public void mouseClicked(MouseEvent arg0) 	{
				String editarNombre = JOptionPane.showInputDialog(null, "Ingrese el nuevo nombre : ", "Edite nombre : ", JOptionPane.QUESTION_MESSAGE);
				usuario.setNombrePersona(editarNombre);
				lblNombre.setText("Nombre: "+usuario.getNombrePersona());
			}
			public void mouseEntered(MouseEvent arg0) 
			{
				lblmodificarNombre.setForeground(Color.red);
			}
			public void mouseExited(MouseEvent arg0) 
			{
				lblmodificarNombre.setForeground(Color.blue);
			}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
		});

//------------------------------------------------------------------------------------------------------------------------	
													//MODIFICAR DIRECCION
		final JLabel lblmodificarDireccion = new JLabel("[Modificar]");
		lblmodificarDireccion.setForeground(Color.BLUE);
		lblmodificarDireccion.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblmodificarDireccion.setBounds(300, 60, 49, 14);
		contentPane.add(lblmodificarDireccion);
		
		lblmodificarDireccion.addMouseListener(new MouseListener() 
		{
			public void mouseClicked(MouseEvent arg0) 	{
				String editarDireccion = JOptionPane.showInputDialog(null, "Ingrese su nueva dirección: ", "Edite dirección : ", JOptionPane.QUESTION_MESSAGE);
				usuario.setDireccion(editarDireccion);
				lblDireccion.setText("Direccion: "+usuario.getDireccion());
			}
			public void mouseEntered(MouseEvent arg0) 
			{
				lblmodificarDireccion.setForeground(Color.red);
			}
			public void mouseExited(MouseEvent arg0) 
			{
				lblmodificarDireccion.setForeground(Color.blue);
			}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
		});
//------------------------------------------------------------------------------------------------------------------------	
													//MODIFICAR RUT
		final JLabel lblmodificarRut = new JLabel("[Modificar]");
		lblmodificarRut.setForeground(Color.BLUE);
		lblmodificarRut.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblmodificarRut.setBounds(300, 90, 49, 14);
		contentPane.add(lblmodificarRut);
		
		lblmodificarRut.addMouseListener(new MouseListener() 
		{
			public void mouseClicked(MouseEvent arg0) 	{
				String editarRutr = JOptionPane.showInputDialog(null, "Ingrese el nuevo rut : ", "Edite rut : ", JOptionPane.QUESTION_MESSAGE);
				usuario.setRut(editarRutr);
				lblRut.setText("Direccion: "+usuario.getDireccion());
			}
			public void mouseEntered(MouseEvent arg0) 
			{
				lblmodificarRut.setForeground(Color.red);
			}
			public void mouseExited(MouseEvent arg0) 
			{
				lblmodificarRut.setForeground(Color.blue);
			}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
		});		
//----------------------------------------------------------------------------------------------------------------------		
													//MODIFICAR EDAD
		final JLabel lblmodificarEdad = new JLabel("[Modificar]");
		lblmodificarEdad.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblmodificarEdad.setForeground(Color.BLUE);
		lblmodificarEdad.setBounds(300, 120, 49, 14);
		contentPane.add(lblmodificarEdad);

		lblmodificarEdad.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent arg0) 
			{
				String editarEdad = JOptionPane.showInputDialog(null, "Ingrese la nueva edad: ", "Edite edad : ", JOptionPane.QUESTION_MESSAGE);
				int nuevaEdad = Integer.parseInt(editarEdad);
				if(!verificarEdad(nuevaEdad)){ 
					JOptionPane.showMessageDialog(VentanaModificarAlumno.this, "La  es incorrecto o faltó rellenar campo");
				}else{
					usuario.setEdad(nuevaEdad);
					lblEdad.setText("Telefono: "+usuario.getTelefono());
				}
			}
				
			public void mouseEntered(MouseEvent arg0) 
			{
				lblmodificarEdad.setForeground(Color.red);
			}
			public void mouseExited(MouseEvent arg0) 
			{
				lblmodificarEdad.setForeground(Color.blue);
			}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
		});
//----------------------------------------------------------------------------------------------------------------------		
												//MODIFICAR TELEFONO
		final JLabel lblmodificarTelefono = new JLabel("[Modificar]");
		lblmodificarTelefono.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblmodificarTelefono.setForeground(Color.BLUE);
		lblmodificarTelefono.setBounds(300, 150, 49, 14);
		contentPane.add(lblmodificarTelefono);

		lblmodificarTelefono.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent arg0) 
			{
				String editarTelefono = JOptionPane.showInputDialog(null, "Ingrese su nuevo teléfono: ", "Edite teléfono : ", JOptionPane.QUESTION_MESSAGE);
				int nuevoTelefono = Integer.parseInt(editarTelefono);
				if(!verificarTelefono(nuevoTelefono)){ 
					JOptionPane.showMessageDialog(VentanaModificarAlumno.this, "El teléfono es incorrecto o faltó rellenar campo");
				}else{
					usuario.setTelefono(nuevoTelefono);
					lblTelefono.setText("Telefono: "+usuario.getTelefono());
				}
			}
				
			public void mouseEntered(MouseEvent arg0) 
			{
				lblmodificarTelefono.setForeground(Color.red);
			}
			public void mouseExited(MouseEvent arg0) 
			{
				lblmodificarTelefono.setForeground(Color.blue);
			}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
		});				
		
//----------------------------------------------------------------------------------------------------------------------		
											//MODIFICAR CORREO
		final JLabel lblmodificarCorreo = new JLabel("[Modificar]");					
		lblmodificarCorreo.setForeground(Color.BLUE);
		lblmodificarCorreo.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblmodificarCorreo.setBounds(300, 180, 49, 14);
		contentPane.add(lblmodificarCorreo);
		
		lblmodificarCorreo.addMouseListener(new MouseListener() 
		{
			public void mouseClicked(MouseEvent arg0) 
			{				
				String editarCorreo = JOptionPane.showInputDialog(null, "Ingrese su nuevo correo: ", "Edite correo : ", JOptionPane.QUESTION_MESSAGE);
				if(!verificarEmail(editarCorreo)){
					JOptionPane.showMessageDialog(VentanaModificarAlumno.this, "El correo es incorrecto o faltó rellenar campo");
				}else{
					usuario.setCorreo(editarCorreo);
					lblCorreo.setText("Correo: "+usuario.getCorreo());
				}
				
			
			}
			public void mouseEntered(MouseEvent arg0) 
			{
				lblmodificarCorreo.setForeground(Color.red);
			}
			public void mouseExited(MouseEvent arg0) 
			{
				lblmodificarCorreo.setForeground(Color.blue);
			}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
		});
	}

	public boolean verificarEdad(int nuevaEdad)  //verifica que la edad sea mayor que 0
	{
		if(nuevaEdad>0){
			return true;
		}else
			return false;
	}
	public boolean verificarTelefono(int nuevoTelefono)	//verifica que el telefono tenga 8 digitos
	{
		int cont = 0;
		while(nuevoTelefono>0){
			nuevoTelefono = nuevoTelefono/10;
			cont++;
		}
		if(cont>=8)
			return true;
		else
			return false;
	}
	
	public boolean verificarEmail(String email){
		int verificarArroba = 0;
		int verificarPuntos = 0;
		
		for(int i = 0;i < email.length(); i++){
			if(email.charAt(i) == '@')//ExtraerCaracter
			verificarArroba++;
	
		if(email.charAt(i) == '.')
				verificarPuntos++;
		}
		
		if(verificarArroba != 1 && verificarPuntos < 1)
			return true;
		
		return false;
	}
}
