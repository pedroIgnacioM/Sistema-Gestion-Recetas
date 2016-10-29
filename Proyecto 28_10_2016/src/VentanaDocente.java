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
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
 
public class VentanaDocente extends JFrame {
 
    private JPanel contentPane;
    
    
    public VentanaDocente(final Docente docenteFinal,final Universidad universidad) {
    	setTitle("VentanaDocente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 361, 335);
        contentPane = new JPanel();
        contentPane.setBackground(SystemColor.menu);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
 
 //--------------------------------------------------------------------------------------------------------------------------
    												//BOTONES
        JButton btnCrearAlumno = new JButton("Crear Alumno");
        btnCrearAlumno.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnCrearAlumno.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                setVisible(false);
                VentanaCrearAlumno ventanaCrearAlumno = new VentanaCrearAlumno (universidad,VentanaDocente.this);     
                ventanaCrearAlumno.setVisible(true);
            }
        });
        btnCrearAlumno.setBounds(10, 169, 151, 36);
        contentPane.add(btnCrearAlumno);
         
        JButton btnEliminarAlumno = new JButton("Eliminar Alumno");
        btnEliminarAlumno.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnEliminarAlumno.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String rutAlumno = JOptionPane.showInputDialog(null,"Ingrese rut del alumno","Buscar alumno",JOptionPane.QUESTION_MESSAGE);
                if(rutAlumno != null){
                	if(!rutAlumno.equals("")){
                		if(universidad.getListaAlumnos().existeAlumnoRut(rutAlumno))
                        {
                            setVisible(false);
                            VentanaEliminarAlumno ventanaEliminarAlumno = new VentanaEliminarAlumno(universidad,universidad.getListaAlumnos().getAlumno(rutAlumno),VentanaDocente.this);
                            ventanaEliminarAlumno.setVisible(true);
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"Alumno no encontrado","Eliminar Alumno",JOptionPane.INFORMATION_MESSAGE);
                        }
                	}
                }
            }
        });
        btnEliminarAlumno.setBounds(184, 169, 151, 36);
        contentPane.add(btnEliminarAlumno);
         
        JButton btnModifAlumno = new JButton("Modificar datos alumno");
        btnModifAlumno.setFont(new Font("Tahoma", Font.PLAIN, 9));
        btnModifAlumno.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String rutAlumno = JOptionPane.showInputDialog(null,"Ingrese rut del alumno","Buscar alumno",JOptionPane.QUESTION_MESSAGE);
                if(rutAlumno != null){
                    if(universidad.getListaAlumnos().existeAlumnoRut(rutAlumno)){               
                        setVisible(false);
                        VentanaModificarAlumno ventanaModificarAlumno = new VentanaModificarAlumno(universidad.getListaAlumnos().getAlumno(rutAlumno),VentanaDocente.this);
                        ventanaModificarAlumno.setVisible(true);
                    }else
                        JOptionPane.showMessageDialog(VentanaDocente.this,"No se encontró usuario","Error de busqueda",0);
                }
            }
        });
        btnModifAlumno.setBounds(10, 208, 151, 36);
        contentPane.add(btnModifAlumno);
         
        JButton btnCerrarSesión = new JButton("Cerrar Sesi\u00F3n");
        btnCerrarSesión.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnCerrarSesión.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) 
            {
                VentanaPrincipal ventanaInicial = new VentanaPrincipal(universidad);
                ventanaInicial.setVisible(true);
                dispose();
            }
        });
        btnCerrarSesión.setBounds(99, 256, 151, 29);
        contentPane.add(btnCerrarSesión);
         
        JButton btnMostrarDatosAlumno = new JButton("Mostrar datos alumno");
        btnMostrarDatosAlumno.setFont(new Font("Tahoma", Font.PLAIN, 9));
        btnMostrarDatosAlumno.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(universidad.getListaAlumnos().getArrayAlumnos()!=null || !(universidad.getListaAlumnos().getArrayAlumnos().isEmpty())){
                    setVisible(false);
                    VentanaMostrarListaAlumnos ventanaMostrarUsuarios = new VentanaMostrarListaAlumnos(universidad.getListaAlumnos().getArrayAlumnos(),VentanaDocente.this);
                    ventanaMostrarUsuarios.setVisible(true);
                }else
                    JOptionPane.showMessageDialog(VentanaDocente.this,"No existen alumnos ingresados","Error al mostrar",0);
            }
        });
        btnMostrarDatosAlumno.setBounds(184, 208, 151, 36);
        contentPane.add(btnMostrarDatosAlumno);
        
        JPanel panelDatosDocente = new JPanel();
        panelDatosDocente.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Bienvenido/a", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panelDatosDocente.setBounds(10, 11, 325, 145);
        contentPane.add(panelDatosDocente);
        panelDatosDocente.setLayout(null);
        
        final JLabel lblNombre = new JLabel("Nombre : "+docenteFinal.getNombrePersona());
        lblNombre.setBounds(21, 20, 264, 14);
        panelDatosDocente.add(lblNombre);
        lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNombre.setForeground(Color.BLACK);
        
        final JLabel lblRut = new JLabel("RUT : "+docenteFinal.getRut());
        lblRut.setBounds(21, 40, 223, 14);
        panelDatosDocente.add(lblRut);
        lblRut.setForeground(Color.BLACK);
        lblRut.setFont(new Font("Tahoma", Font.PLAIN, 11));

//------------------------------------------------------------------------------------------------------------------------  
            												//MODIFICAR RUT
        final JLabel lblmodificarRut = new JLabel("[Modificar]");
        lblmodificarRut.setBounds(266, 40, 49, 14);
        panelDatosDocente.add(lblmodificarRut);
        lblmodificarRut.setForeground(Color.BLACK);
        lblmodificarRut.setFont(new Font("Tahoma", Font.PLAIN, 10));
            
//------------------------------------------------------------------------------------------------------------------------  
        										//MODIFICAR NOMBRE
    final JLabel lblmodificarNombre = new JLabel("[Modificar]");
    lblmodificarNombre.setBounds(266, 20, 49, 14);
    panelDatosDocente.add(lblmodificarNombre);
    lblmodificarNombre.setForeground(Color.BLACK);
    lblmodificarNombre.setFont(new Font("Tahoma", Font.PLAIN, 10));
    
        final JLabel lblDireccion = new JLabel("Direccion : "+docenteFinal.getDireccion());
        lblDireccion.setBounds(21, 60, 242, 14);
        panelDatosDocente.add(lblDireccion);
        lblDireccion.setForeground(Color.BLACK);
        lblDireccion.setFont(new Font("Tahoma", Font.PLAIN, 11));
        
        final JLabel lblEdad = new JLabel("Edad : "+docenteFinal.getEdad());
        lblEdad.setBounds(21, 80, 87, 14);
        panelDatosDocente.add(lblEdad);
        lblEdad.setForeground(Color.BLACK);
        lblEdad.setFont(new Font("Tahoma", Font.PLAIN, 11));
        
        final JLabel lblTelefono = new JLabel("Telefono : "+docenteFinal.getTelefono());
        lblTelefono.setBounds(21, 100, 242, 14);
        panelDatosDocente.add(lblTelefono);
        lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblTelefono.setForeground(Color.BLACK);
        
        final JLabel lblCorreo = new JLabel("Correo : "+docenteFinal.getCorreo());
        lblCorreo.setBounds(21, 120, 242, 14);
        panelDatosDocente.add(lblCorreo);
        lblCorreo.setBackground(Color.WHITE);
        lblCorreo.setForeground(Color.BLACK);
        lblCorreo.setFont(new Font("Tahoma", Font.PLAIN, 11));
        
//------------------------------------------------------------------------------------------------------------------------  
           											//MODIFICAR DIRECCION
           final JLabel lblmodificarDireccion = new JLabel("[Modificar]");
           lblmodificarDireccion.setBounds(266, 60, 49, 14);
           panelDatosDocente.add(lblmodificarDireccion);
           lblmodificarDireccion.setForeground(Color.BLACK);
           lblmodificarDireccion.setFont(new Font("Tahoma", Font.PLAIN, 10));
           //----------------------------------------------------------------------------------------------------------------------        
               												//MODIFICAR EDAD
               final JLabel lblmodificarEdad = new JLabel("[Modificar]");
               lblmodificarEdad.setBounds(266, 80, 49, 14);
               panelDatosDocente.add(lblmodificarEdad);
               lblmodificarEdad.setFont(new Font("Tahoma", Font.PLAIN, 10));
               lblmodificarEdad.setForeground(Color.BLACK);
               //-----------------------------------------------------------------------------------------------------------------------        
                   											//MODIFICAR TELEFONO
                   final JLabel lblmodificarTelefono = new JLabel("[Modificar]");
                   lblmodificarTelefono.setBounds(266, 100, 49, 14);
                   panelDatosDocente.add(lblmodificarTelefono);
                   lblmodificarTelefono.setFont(new Font("Tahoma", Font.PLAIN, 10));
                   lblmodificarTelefono.setForeground(Color.BLACK);
                   
//------------------------------------------------------------------------------------------------------------------------        
    											//MODIFICAR CORREO
    final JLabel lblmodificarCorreo = new JLabel("[Modificar]");                    
    lblmodificarCorreo.setBounds(266, 120, 49, 14);
    panelDatosDocente.add(lblmodificarCorreo);
    lblmodificarCorreo.setForeground(Color.BLACK);
    lblmodificarCorreo.setFont(new Font("Tahoma", Font.PLAIN, 10));
    
    lblmodificarCorreo.addMouseListener(new MouseListener() 
    {
       public void mouseClicked(MouseEvent arg0) 
       {               
           String editarCorreo = JOptionPane.showInputDialog(null, "Ingrese su nuevo correo: ", "Edite correo : ", JOptionPane.QUESTION_MESSAGE);
           if(editarCorreo != null){
               if(!editarCorreo.equals("")){
                   if(!verificarEmail(editarCorreo)){
                       docenteFinal.setCorreo(editarCorreo);
                       lblCorreo.setText("Correo: "+docenteFinal.getCorreo());
                   }else{
                       JOptionPane.showMessageDialog(VentanaDocente.this, "El correo ingresado es incorrecto");
                   }
               }else{
                   JOptionPane.showMessageDialog(VentanaDocente.this, "Faltó rellenar campo");
               }
           }
       }
    
       public void mouseEntered(MouseEvent arg0) 
       {
           lblmodificarCorreo.setForeground(Color.red);
       }
       public void mouseExited(MouseEvent arg0) 
       {
           lblmodificarCorreo.setForeground(Color.BLACK);
       }
       public void mousePressed(MouseEvent arg0) {}
       public void mouseReleased(MouseEvent arg0) {}
    });
                   
                      lblmodificarTelefono.addMouseListener(new MouseListener() {
                          public void mouseClicked(MouseEvent arg0) 
                          {
                              String editarTelefono = JOptionPane.showInputDialog(null, "Ingrese su nuevo teléfono: ", "Edite teléfono : ", JOptionPane.QUESTION_MESSAGE);
                              if(editarTelefono != null){
                                  if(!editarTelefono.equals("")){
                                      int nuevoTelefono = Integer.parseInt(editarTelefono);
                                      if(verificarTelefono(nuevoTelefono)){
                                          docenteFinal.setTelefono(nuevoTelefono);
                                          lblTelefono.setText("Telefono: "+docenteFinal.getTelefono());
                                      }else{
                                          JOptionPane.showMessageDialog(VentanaDocente.this, "El teléfono ingresado es incorrecto");
                                      }   
                                  }else{
                                      JOptionPane.showMessageDialog(VentanaDocente.this, "Faltó rellenar campo");
                                  }
                              }
                          }
                               
                          public void mouseEntered(MouseEvent arg0) 
                          {
                              lblmodificarTelefono.setForeground(Color.red);
                          }
                          public void mouseExited(MouseEvent arg0) 
                          {
                              lblmodificarTelefono.setForeground(Color.BLACK);
                          }
                          public void mousePressed(MouseEvent arg0) {}
                          public void mouseReleased(MouseEvent arg0) {}
                      });             
               
                  lblmodificarEdad.addMouseListener(new MouseListener() {
                      public void mouseClicked(MouseEvent arg0) 
                      {
                          String editarEdad = JOptionPane.showInputDialog(null, "Ingrese la nueva edad: ", "Edite edad : ", JOptionPane.QUESTION_MESSAGE);
                          if(editarEdad != null){
                              if(!editarEdad.equals("")){
                                  int nuevaEdad = Integer.parseInt(editarEdad);
                                  if(verificarEdad(nuevaEdad)){
                                      docenteFinal.setEdad(nuevaEdad);
                                      lblEdad.setText("Edad: "+docenteFinal.getEdad());
                                  }else{
                                      JOptionPane.showMessageDialog(VentanaDocente.this, "La edad ingresada es incorrecta");
                                  }
                              }else{
                                  JOptionPane.showMessageDialog(VentanaDocente.this, "Faltó rellenar campo");
                              }   
                          }
                      }
                           
                      public void mouseEntered(MouseEvent arg0) 
                      {
                          lblmodificarEdad.setForeground(Color.red);
                      }
                      public void mouseExited(MouseEvent arg0) 
                      {
                          lblmodificarEdad.setForeground(Color.BLACK);
                      }
                      public void mousePressed(MouseEvent arg0) {}
                      public void mouseReleased(MouseEvent arg0) {}
                  });
           
    lblmodificarDireccion.addMouseListener(new MouseListener() 
    {
              public void mouseClicked(MouseEvent arg0)   {
                  String editarDireccion = JOptionPane.showInputDialog(null, "Ingrese su nueva dirección: ", "Edite dirección : ", JOptionPane.QUESTION_MESSAGE);
                  if(editarDireccion != null){
                      if(!editarDireccion.equals("")){
                          docenteFinal.setDireccion(editarDireccion);
                          lblDireccion.setText("Direccion: "+docenteFinal.getDireccion());
                      }else{
                          JOptionPane.showMessageDialog(VentanaDocente.this, "Faltó rellenar campo");
                      }
                           
                  }
              }
              public void mouseEntered(MouseEvent arg0) 
              {
                  lblmodificarDireccion.setForeground(Color.red);
              }
              public void mouseExited(MouseEvent arg0) 
              {
                  lblmodificarDireccion.setForeground(Color.BLACK);
              }
              public void mousePressed(MouseEvent arg0) {}
              public void mouseReleased(MouseEvent arg0) {}
    });
    
                                               //Modifica el nombre al presionar en el label [Modificar]
    lblmodificarNombre.addMouseListener(new MouseListener() 
    {
       public void mouseClicked(MouseEvent arg0)   {
           String editarNombre = JOptionPane.showInputDialog(null, "Ingrese el nuevo nombre : ", "Edite nombre : ", JOptionPane.QUESTION_MESSAGE);
           if (editarNombre != null){
               if(!editarNombre.equals("")){
                   docenteFinal.setNombrePersona(editarNombre);
                   lblNombre.setText("Bienvenido: "+docenteFinal.getNombrePersona());
               }else{
                   JOptionPane.showMessageDialog(VentanaDocente.this, "Faltó rellenar campo");
               }
           }
       }
       public void mouseEntered(MouseEvent arg0) 
       {
           lblmodificarNombre.setForeground(Color.red);
       }
       public void mouseExited(MouseEvent arg0) 
       {
           lblmodificarNombre.setForeground(Color.BLACK);
       }
       public void mousePressed(MouseEvent arg0) {}
       public void mouseReleased(MouseEvent arg0) {}
    });
            
    lblmodificarRut.addMouseListener(new MouseListener() 
    {
               public void mouseClicked(MouseEvent arg0)   {
                   String editarRut = JOptionPane.showInputDialog(null, "Ingrese el nuevo rut : ", "Edite rut : ", JOptionPane.QUESTION_MESSAGE);
                   if(editarRut != null){
                       if(!editarRut.equals("")){
                           docenteFinal.setRut(editarRut);
                           lblRut.setText("RUT: "+docenteFinal.getRut());
                       }else{
                           JOptionPane.showMessageDialog(VentanaDocente.this, "Faltó rellenar campo");
                       }
                   }
               }
               public void mouseEntered(MouseEvent arg0) 
               {
                   lblmodificarRut.setForeground(Color.red);
               }
               public void mouseExited(MouseEvent arg0) 
               {
                   lblmodificarRut.setForeground(Color.BLACK);
               }
               public void mousePressed(MouseEvent arg0) {}
               public void mouseReleased(MouseEvent arg0) {}
    });     
    }
     
    public boolean verificarEdad(int nuevaEdad)
    {
        if(nuevaEdad>0){
            return true;
        }else
            return false;
    }
     
    public boolean verificarTelefono(int nuevoTelefono)
    {
        int cont = 0;
        while(nuevoTelefono>0){
            nuevoTelefono/=10;
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