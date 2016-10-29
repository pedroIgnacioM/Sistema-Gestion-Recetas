import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaFiltroRecetas extends JFrame {

	private JPanel contentPane;
	private JTextField txtTiempoEstimado;
	private JTextField txtCalificacion;
	private JTextField txtCreador;
	private DefaultListModel ingredientes, utensilios, categorias;
	private JTextField txtAgregarI;
	private JTextField textAgregarC;
	private JTextField txtAgregarU;
	
	public VentanaFiltroRecetas(final VentanaBuscarReceta ventanaBuscarReceta, final Receta receta) 
	{
		
		ingredientes = new DefaultListModel();
		utensilios = new DefaultListModel();
		categorias = new DefaultListModel();
		setResizable(false);
		
		setTitle("Ventana Filtro Receta");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 670, 399);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
//-----------------------------------------------------------------------------------------------------------------------------
		JLabel lblTiempoEstimado = new JLabel("Tiempo Estimado Max");				//TIEMPO ESTIMADO
		lblTiempoEstimado.setBounds(10, 24, 167, 14);
		contentPane.add(lblTiempoEstimado);
		
		txtTiempoEstimado = new JTextField();
		txtTiempoEstimado.setBounds(132, 21, 45, 20);
		contentPane.add(txtTiempoEstimado);
		txtTiempoEstimado.setColumns(10);
		
//-----------------------------------------------------------------------------------------------------------------------------
		/*JLabel lblCalificacion = new JLabel("Calificaci\u00F3n");				//CALIFICACION
		lblCalificacion.setBounds(315, 24, 69, 14);				//todavia no esta implementado
		contentPane.add(lblCalificacion);
		
		txtCalificacion = new JTextField();
		txtCalificacion.setBounds(394, 21, 45, 20);
		contentPane.add(txtCalificacion);
		txtCalificacion.setColumns(10);
		
		JRadioButton radioButtonCalificacion = new JRadioButton("");
		radioButtonCalificacion.setBounds(445, 21, 21, 23);
		contentPane.add(radioButtonCalificacion); */
//-----------------------------------------------------------------------------------------------------------------------------
		JLabel lblCreador = new JLabel("Creador");								//CREADOR
		lblCreador.setBounds(189, 52, 46, 14);
		contentPane.add(lblCreador);
		
		txtCreador = new JTextField();
		txtCreador.setBounds(239, 49, 103, 20);
		contentPane.add(txtCreador);
		txtCreador.setColumns(10);
		
//-----------------------------------------------------------------------------------------------------------------------------
		JPanel panelIngredientes = new JPanel();							//INGREDIENTES
		panelIngredientes.setBounds(10, 173, 110, 152);
		panelIngredientes.setLayout(new GridLayout());
		contentPane.add(panelIngredientes);
		
		JList listIngredientes = new JList();
		listIngredientes.setModel(ingredientes);
		panelIngredientes.add(listIngredientes);
		
		JButton btnAgregarI = new JButton("Agregar");						
		btnAgregarI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!txtAgregarI.getText().isEmpty())
				{
				
					ingredientes.addElement(txtAgregarI.getText());
					txtAgregarI.setText("");
				}
			}
		});
		
		JLabel lblIngredientes = new JLabel("Ingredientes");
		lblIngredientes.setBounds(31, 155, 61, 14);
		contentPane.add(lblIngredientes);
		
		txtAgregarI = new JTextField();
		txtAgregarI.setBounds(130, 187, 86, 20);
		contentPane.add(txtAgregarI);
		txtAgregarI.setColumns(10);
		btnAgregarI.setBounds(130, 218, 89, 23);
		contentPane.add(btnAgregarI);
		
		JButton btnBorrarI = new JButton("Borrar");
		btnBorrarI.setBounds(130, 252, 89, 23);
		contentPane.add(btnBorrarI);
		
//-----------------------------------------------------------------------------------------------------------------------------		
		JPanel panelCategorias = new JPanel();								//CATEGORIAS
		panelCategorias.setBounds(232, 173, 106, 152);
		panelCategorias.setLayout(new GridLayout());
		contentPane.add(panelCategorias);
		
		JList listCategorias = new JList();
		panelCategorias.add(listCategorias);
		listCategorias.setModel(categorias);
		
		JButton btnAgregarC = new JButton("Agregar");
		btnAgregarC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!textAgregarC.getText().isEmpty())
				{		
					categorias.addElement(textAgregarC.getText());
					textAgregarC.setText("");
				}
			}
		});
		
		btnAgregarC.setBounds(348, 218, 89, 23);
		contentPane.add(btnAgregarC);
		
		JLabel lblCategorias = new JLabel("Categorias");
		lblCategorias.setBounds(255, 155, 61, 14);
		contentPane.add(lblCategorias);
		
		textAgregarC = new JTextField();
		textAgregarC.setColumns(10);
		textAgregarC.setBounds(348, 187, 86, 20);
		contentPane.add(textAgregarC);
		
		JButton btnBorrarC = new JButton("Borrar");
		btnBorrarC.setBounds(348, 252, 89, 23);
		contentPane.add(btnBorrarC);
		
//-----------------------------------------------------------------------------------------------------------------------------		
		JPanel panelUtensilios = new JPanel();								//UTENSILIOS
		panelUtensilios.setBounds(447, 173, 103, 152);
		panelUtensilios.setLayout(new GridLayout());
		contentPane.add(panelUtensilios);
		
		JList listUtensilios = new JList();
		panelUtensilios.add(listUtensilios);
		listUtensilios.setModel(utensilios);
		
		txtAgregarU = new JTextField();
		txtAgregarU.setBounds(560, 187, 86, 20);
		contentPane.add(txtAgregarU);
		txtAgregarU.setColumns(10);
		
		JButton btnAgregarU = new JButton("Agregar");
		btnAgregarU.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txtAgregarU.getText().isEmpty())
				{		
					utensilios.addElement(txtAgregarU.getText());
					txtAgregarU.setText("");
				}
			}
		});
		btnAgregarU.setBounds(560, 218, 89, 23);
		contentPane.add(btnAgregarU);
		
		JLabel lblUtensilios = new JLabel("Utensilios");
		lblUtensilios.setBounds(475, 155, 46, 14);
		contentPane.add(lblUtensilios);
		
		JButton btnBorrarU = new JButton("Borrar");
		btnBorrarU.setBounds(560, 252, 89, 23);
		contentPane.add(btnBorrarU);
		
//-----------------------------------------------------------------------------------------------------------------------------		
		JButton btnAtras = new JButton("Ok");								//BOTON OK
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if(!txtTiempoEstimado.getText().isEmpty() && !txtTiempoEstimado.getText().equals(""))
							receta.setTiempoEstimadoPreparacion(Integer.parseInt(txtTiempoEstimado.getText()));
				if(!txtCreador.getText().isEmpty() && !txtCreador.getText().equals(""))
				{
					Alumno usuarioAux= new Alumno(txtCreador.getText());
				}
				if(!ingredientes.isEmpty())
					receta.setIngredientes(casteo(ingredientes));
				if(!utensilios.isEmpty())
					receta.setUtensilios(casteo(utensilios));
				if(!categorias.isEmpty())
					receta.setCategorias(casteo(categorias));
				
				ventanaBuscarReceta.setVisible(true);	//cierra la ventana actual y abre la anterior
				dispose();
			}
		});
		btnAtras.setBounds(555, 20, 91, 23);
		contentPane.add(btnAtras);
	}
	public String[] casteo(DefaultListModel listaEntrada) //convierte de DefaultListModel a String[]
	{
		String[] listaFinal= new String[listaEntrada.getSize()];
		int i;
		for(i=0;i<listaEntrada.getSize();i++)
		{
			listaFinal[i]=((String)listaEntrada.getElementAt(i));
		}
		return listaFinal;
	}
}
