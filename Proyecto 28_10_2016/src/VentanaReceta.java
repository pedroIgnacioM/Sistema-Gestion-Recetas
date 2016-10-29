import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.DefaultListModel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class VentanaReceta extends JFrame {

	private JPanel contentPane;
	private DefaultListModel comentarios;
	private boolean ventanaAnteriorBuscarReceta;
	private VentanaBuscarReceta ventanaBuscarReceta;
	private VentanaMisRecetas ventanaMisRecetas;
	Alumno alumno;
	public VentanaReceta(final Receta receta) 
	{
		setTitle("VentanaReceta");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 50, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		JLabel lblNewLabel = new JLabel(receta.getNombreReceta());
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(95, 11, 230, 26);
		contentPane.add(lblNewLabel);
		
		JLabel lblTiempoEstimadoDe = new JLabel("Tiempo Estimado De Preparacion "+ receta.getTiempoEstimadoPreparacion() + " minutos");
		lblTiempoEstimadoDe.setBounds(10, 221, 354, 26);
		contentPane.add(lblTiempoEstimadoDe);
		
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(424, 0, 10, 561);		
		contentPane.add(separator);

//------------------------------------------------------------------------------------------------------------------------
		JPanel panel_ingredientes = new JPanel();							//INGREDIENTES
		panel_ingredientes.setBounds(10, 60, 100, 150);
		panel_ingredientes.setLayout(new GridLayout());
		contentPane.add(panel_ingredientes);
		
		JList lista_ingredientes = new JList(receta.getIngredientes());
		panel_ingredientes.add(lista_ingredientes);
		
		JScrollPane scroll_ingredientes = new JScrollPane(lista_ingredientes);
		panel_ingredientes.add(scroll_ingredientes);
		
		JLabel lblIngredientes = new JLabel("Ingredientes");
		lblIngredientes.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngredientes.setBounds(10, 45, 100, 14);
		contentPane.add(lblIngredientes);
		
//-------------------------------------------------------------------------------------------------------------------------
		JPanel panel_utensilios = new JPanel();								//UTENSILIOS
		panel_utensilios.setBounds(160, 60, 100, 150);
		panel_utensilios.setLayout(new GridLayout());
		contentPane.add(panel_utensilios);
		
		JList lista_utensilios = new JList(receta.getUtensilios());
		panel_utensilios.add(lista_utensilios);
		
		JScrollPane scroll_utensilios = new JScrollPane(lista_utensilios);
		panel_utensilios.add(scroll_utensilios);
		
		JLabel lblUtensilios = new JLabel("Utensilios");
		lblUtensilios.setHorizontalAlignment(SwingConstants.CENTER);
		lblUtensilios.setBounds(160, 45, 100, 14);
		contentPane.add(lblUtensilios);
		
//-------------------------------------------------------------------------------------------------------------------------
		JPanel panel_categorias = new JPanel();								//CATEGORIAS
		panel_categorias.setBounds(310, 60, 100, 150);
		panel_categorias.setLayout(new GridLayout());
		contentPane.add(panel_categorias);
		
		JList lista_categorias = new JList(receta.getCategorias());
		panel_categorias.add(lista_categorias);
		
		JScrollPane scroll_categorias = new JScrollPane(lista_categorias);
		panel_categorias.add(scroll_categorias);

		JLabel lblCategorias = new JLabel("Categorias");
		lblCategorias.setHorizontalAlignment(SwingConstants.CENTER);
		lblCategorias.setBounds(310, 45, 100, 14);
		contentPane.add(lblCategorias);
		
//-------------------------------------------------------------------------------------------------------------------------
		JPanel panel_instrucciones = new JPanel();						//INSTRUCCIONES
		panel_instrucciones.setBounds(10, 279, 400, 220);
		contentPane.add(panel_instrucciones);
		panel_instrucciones.setLayout(new GridLayout());
		
		JTextArea txtInstrucciones = new JTextArea(receta.getInstrucciones());
		panel_instrucciones.add(txtInstrucciones);
		txtInstrucciones.setEditable(false);
		txtInstrucciones.setLineWrap(true);   		
		txtInstrucciones.setWrapStyleWord(true);

		JScrollPane scroll_instrucciones = new JScrollPane(txtInstrucciones);
		panel_instrucciones.add(scroll_instrucciones);
//--------------------------------------------------------------------------------------------------------------------------
		JButton btnAtras = new JButton("Atras");						//BOTON ATRAS
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(receta.getAutor().getListaRecetas().modificarReceta(receta))
				{
					
					if(ventanaAnteriorBuscarReceta)
							ventanaBuscarReceta.setVisible(true);
					else
							ventanaMisRecetas.setVisible(true);
					dispose();
				}
				else
					JOptionPane.showMessageDialog(VentanaReceta.this,"Error","Error",0);
			}
		});
		btnAtras.setBounds(321, 527, 89, 23);
		contentPane.add(btnAtras);
		
		JLabel lblInstrucciones = new JLabel("Instrucciones");
		lblInstrucciones.setBounds(10, 258, 151, 14);
		contentPane.add(lblInstrucciones);
//-------------------------------------------------------------------------------------------------------------------------
		JPanel panelComentarios = new JPanel();							//LISTA COMENTARIOS
		panelComentarios.setBounds(444, 27, 330, 310);
		panelComentarios.setLayout(new GridLayout());
		contentPane.add(panelComentarios);
		
		//final ArrayList<Comentario> listaComentarios = receta.getComentarios().getComentarios(); 
		final JTextArea textComentarios = new JTextArea();
		panelComentarios.add(textComentarios);
		textComentarios.setEditable(false);
		if(receta.getComentarios()!=null)
			textComentarios.setText(actualizarLista(receta.getComentarios().getArrayComentarios()));
		
		JScrollPane scrollComentarios = new JScrollPane(textComentarios);
		panelComentarios.add(scrollComentarios);
//-------------------------------------------------------------------------------------------------------------------------
		JPanel panelAgregarComent = new JPanel();					//AGREGAR COMENTARIO
		panelAgregarComent.setBounds(440, 368, 334, 150);
		panelAgregarComent.setLayout(new GridLayout());
		contentPane.add(panelAgregarComent);
		
		final JTextArea txtComentarioNuevo = new JTextArea();
		panelAgregarComent.add(txtComentarioNuevo);
		txtComentarioNuevo.setLineWrap(true);
			
		JScrollPane scrollAgregarComent = new JScrollPane(txtComentarioNuevo);
		panelAgregarComent.add(scrollAgregarComent);
		
		JLabel lblComentarios = new JLabel("Comentarios");
		lblComentarios.setHorizontalAlignment(SwingConstants.CENTER);
		lblComentarios.setBounds(560, 4, 132, 14);
		contentPane.add(lblComentarios);
		
		JButton btnComentar = new JButton("Comentar");
		btnComentar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				Comentario comentarioNuevo= receta.getComentarios().agregarComentario(txtComentarioNuevo.getText(), alumno);
				textComentarios.setText(actualizarLista(receta.getComentarios().getArrayComentarios()));
				txtComentarioNuevo.setText("");
			}
		});
		btnComentar.setBounds(560, 527, 188, 23);
		contentPane.add(btnComentar);
		
		JLabel lblAgregarComentario = new JLabel("Agregar Comentario");
		lblAgregarComentario.setBounds(444, 531, 132, 14);
		contentPane.add(lblAgregarComentario);
//-------------------------------------------------------------------------------------------------------------------------------------
		JButton btnEliminarComentario = new JButton("Eliminar Comentario");	//ELIMINAR COMENTARIO
		btnEliminarComentario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				if(receta.getAutor()==alumno) //entra aca si es el creador de la receta
				{
					String code= JOptionPane.showInputDialog(null, "Ingrese numero de comentario: ", "Eliminar comentario: ", JOptionPane.QUESTION_MESSAGE);
					if(code!=null && !code.equals(""))
					{
						if(code.matches("[0-9]"))
						{
							int codeNumerico= Integer.parseInt(code);
							if(receta.getComentarios().eliminarComentario(codeNumerico))
							{
								textComentarios.setText(actualizarLista(receta.getComentarios().getArrayComentarios()));
								JOptionPane.showMessageDialog(VentanaReceta.this, "Se elimino con exito");
							}
							else
							{
								JOptionPane.showMessageDialog(VentanaReceta.this, "El numero ingresado no es valido");
							}
						}
						else
						{
							JOptionPane.showMessageDialog(VentanaReceta.this, "Debe ingresar un numero");
						}
							
					}
				}
				else
				{		
					String code= JOptionPane.showInputDialog(null, "Ingrese numero de comentario: ", "Eliminar comentario: ", JOptionPane.QUESTION_MESSAGE);
					if(code!=null && !code.equals(""))
					{
						int codeNumerico= Integer.parseInt(code);
						Comentario comentarioEliminar= receta.getComentarios().pedirComentario(codeNumerico);
						if(comentarioEliminar!=null)
						{
							if(comentarioEliminar.getAutor()==alumno)
							{
								if(receta.getComentarios().eliminarComentario(codeNumerico))
								{
									textComentarios.setText(actualizarLista(receta.getComentarios().getArrayComentarios()));
									JOptionPane.showMessageDialog(VentanaReceta.this, "Se elimino con exito");
								}
								else
								{
									JOptionPane.showMessageDialog(VentanaReceta.this, "El numero ingresado no es valido");
								}
							}
							else
							{
								JOptionPane.showMessageDialog(VentanaReceta.this, "El numero ingresado no es valido");
							}
						}
						else
						{
							JOptionPane.showMessageDialog(VentanaReceta.this, "El numero ingresado no es valido");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(VentanaReceta.this, "El numero ingresado no es valido");
					}
				}
			}
		});
		btnEliminarComentario.setBounds(623, 344, 151, 23);
		contentPane.add(btnEliminarComentario);
//--------------------------------------------------------------------------------------------------------------------------
		JButton btnModificarComentario = new JButton("Modificar Comentario");	//MODIFICAR COMENTARIO
		btnModificarComentario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				String code= JOptionPane.showInputDialog(null, "Ingrese numero de comentario: ", "Modificar comentario: ", JOptionPane.QUESTION_MESSAGE);
				if(code!=null && !code.equals(""))
				{
					int codeNumerico= Integer.parseInt(code);
					Comentario comentarioModificar= receta.getComentarios().pedirComentario(codeNumerico);
					if(comentarioModificar!=null)
					{
						if(comentarioModificar.getAutor()==alumno)
						{
							String nuevoComent = JOptionPane.showInputDialog(comentarioModificar.getTexto());
							if(receta.getComentarios().modificarComentario(codeNumerico,nuevoComent))
							{
								textComentarios.setText(actualizarLista(receta.getComentarios().getArrayComentarios()));
								JOptionPane.showMessageDialog(VentanaReceta.this, "Se modificó con exito");
							}
							else
							{
								JOptionPane.showMessageDialog(VentanaReceta.this, "El numero ingresado no es valido");
							}
						}
						else
						{
							JOptionPane.showMessageDialog(VentanaReceta.this, "El numero ingresado no es valido");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(VentanaReceta.this, "El numero ingresado no es valido");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(VentanaReceta.this, "El numero ingresado no es valido");
				}
			}
		});
		btnModificarComentario.setBounds(444, 344, 169, 23);
		contentPane.add(btnModificarComentario);
		
		JLabel lblSubidoPor = new JLabel("Subido por: "+receta.getAutor());
		lblSubidoPor.setBounds(10, 504, 404, 14);
		contentPane.add(lblSubidoPor);
		

	}//FIN CONTRUCTOR
	
	public String actualizarLista(ArrayList<Comentario> listaComentarios)	//retorna un JtextArea con todos los comentarios en la lista
	{
		JTextArea textNuevo= new JTextArea();
		for(int i=0;i<listaComentarios.size();i++)
		{
			textNuevo.append(listaComentarios.get(i).toString());
			textNuevo.append(System.getProperty("line.separator"));
		}
		return textNuevo.getText();
	}
	public void mostrarVentanaAnteriorMisRecetas()	//muestra la ventana anterior cuando es VentanaMisRecetas
	{
		ventanaMisRecetas.setVisible(true);
	}
	public void mostrarVentanaAnteriorBuscarReceta() //muestra la ventana anterior cuando es VentanaBuscarReceta
	{
		ventanaBuscarReceta.setVisible(true);
	}
	public void mostrarVentana(VentanaBuscarReceta ventanaAnterior,Alumno alumno)
	{																			//muestra la ventana actual
		ventanaAnteriorBuscarReceta=true;								
		this.alumno=alumno;											//y le da valor a la ventanaBuscarReceta
		ventanaBuscarReceta=ventanaAnterior;
		setVisible(true);
	}
	public void mostrarVentana(VentanaMisRecetas ventanaAnterior,Alumno alumno)
	{															//muestra la ventana actual
		this.alumno=alumno;							
		ventanaMisRecetas= ventanaAnterior;			//y le da valor a la ventanaMisRecetas
		setVisible(true);
	}
}

