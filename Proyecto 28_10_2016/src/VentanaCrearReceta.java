import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import java.awt.event.KeyAdapter;
import javax.swing.JList;

public class VentanaCrearReceta extends JFrame  
{
	private JPanel contentPane;
	private JTextField nombreReceta;
	private JTextField tiempoEstimado;
	private int tamMinNombre= 2;
	private int tamMaxNombre = 100;
	private DefaultListModel ingredientes,categorias,utensilios;
	private JTextField txtIngrediente;
	private JTextField txtUtensilio;
	private JTextField txtCategoria;
	
/*||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
  ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
  ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
  ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
  												CONSTRUCTOR CREAR RECETA
 */
	/**
	 * @wbp.parser.constructor
	 */
	public VentanaCrearReceta(final Alumno alumno,final VentanaMisRecetas ventanaAnterior) 
	{																					
		final Receta receta = new Receta();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 15, 1000, 700);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bienvenido a la creacion de tu receta");
		lblNewLabel.setBounds(10, 35, 260, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("No olvides ser ordenado, escribir correctamente y coherente");
		lblNewLabel_1.setBounds(10, 60, 359, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblAsiLosDemas = new JLabel("asi los demas puedan ocupar tu receta sin problemas");
		lblAsiLosDemas.setBounds(10, 83, 394, 14);
		contentPane.add(lblAsiLosDemas);
		
		JLabel lblSeCreativo = new JLabel("SE CREATIVO!!");
		lblSeCreativo.setBounds(10, 108, 86, 14);
		contentPane.add(lblSeCreativo);
		
		final JLabel lblVernombre = new JLabel("");
		lblVernombre.setBounds(682, 13, 46, 14);
		contentPane.add(lblVernombre);
//-------------------------------------------------------------------------------------------------------------------------------------
		JLabel lblNombreReceta = new JLabel("Nombre Receta"); 			//NOMBRE RECETA
		lblNombreReceta.setBounds(256, 13, 113, 14);
		contentPane.add(lblNombreReceta);
		
		nombreReceta = new JTextField();
		nombreReceta.setBounds(356, 10, 320, 20);
		contentPane.add(nombreReceta);
		nombreReceta.setColumns(10);
		
		nombreReceta.addKeyListener(new KeyListener()
		{
			public void keyTyped(KeyEvent e)
			{
				soloLetras(e);
				validar(e);
				if (nombreReceta.getText().length() == tamMaxNombre)
					e.consume();
			}
			public void keyPressed(KeyEvent e){
				if(nombreReceta.getText().length() <= tamMaxNombre && nombreReceta.getText().length() > tamMinNombre){
					nombreReceta.setBackground(Color.green);
					lblVernombre.setText("\u2713");
				}
			}
			public void keyReleased(KeyEvent e)
			{
				if(nombreReceta.getText().length() <= tamMinNombre){
					nombreReceta.setBackground(Color.red);
					lblVernombre.setText("\u2717");
				}
			}
		});
//-------------------------------------------------------------------------------------------------------------------------------------
		JLabel lblNewLabel_2 = new JLabel("Tiempo estimado");	//TIEMPO ESTIMADO PREPARACIÓN
		lblNewLabel_2.setBounds(10, 150, 113, 14);
		contentPane.add(lblNewLabel_2);
		
		final JLabel lblVerifTiempoEst = new JLabel();
		lblVerifTiempoEst.setBounds(177, 150, 46, 14);
		contentPane.add(lblVerifTiempoEst);
		
		tiempoEstimado = new JTextField();
		tiempoEstimado.setBounds(111, 147, 56, 20);
		contentPane.add(tiempoEstimado);
		tiempoEstimado.setColumns(10);
		tiempoEstimado.addKeyListener(new KeyListener()
		{
			int tamanoTiempoEst = 3;
			public void keyTyped(KeyEvent e)
			{
				soloNumeros(e);
				if (tiempoEstimado.getText().length() == tamanoTiempoEst)
					e.consume();
			}
			public void keyPressed(KeyEvent e){
				if(tiempoEstimado.getText().length() <= tamanoTiempoEst && !(tiempoEstimado.getText().isEmpty())){
					tiempoEstimado.setBackground(Color.green);
					lblVerifTiempoEst.setText("\u2713");
				}
			}
			public void keyReleased(KeyEvent e)
			{
				if(tiempoEstimado.getText().length() == 0){
					tiempoEstimado.setBackground(Color.red);
					lblVerifTiempoEst.setText("\u2717");
				}
			}
		});
		
//-------------------------------------------------------------------------------------------------------------------------------------
		JPanel panel_instrucciones = new JPanel();				//INSTRUCCIONES
		panel_instrucciones.setBounds(10, 235, 666, 353);
		contentPane.add(panel_instrucciones);
		panel_instrucciones.setLayout(new GridLayout());
		
		final JTextArea instrucciones = new JTextArea();
		panel_instrucciones.add(instrucciones);
		instrucciones.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyTyped(KeyEvent x) 
			{	validar(x);
				if(instrucciones.getText().length() == 800)
					x.consume();
			}
		});
		instrucciones.setLineWrap(true);   		//Limitar caracteres dentro del JTextArea
		instrucciones.setWrapStyleWord(true);
		
		JScrollPane scroll_instrucciones = new JScrollPane(instrucciones);
		panel_instrucciones.add(scroll_instrucciones);
		
		JLabel lblInstrucciones = new JLabel("Instrucciones");
		lblInstrucciones.setBounds(302, 204, 235, 20);
		contentPane.add(lblInstrucciones);
		
//-------------------------------------------------------------------------------------------------------------------------------------	
		ingredientes = new DefaultListModel();
		JPanel panel_ingredientes = new JPanel();				//INGREDIENTES
		panel_ingredientes.setBounds(730, 45, 113, 161);
		contentPane.add(panel_ingredientes);
		panel_ingredientes.setLayout(new GridLayout());
		
		txtIngrediente = new JTextField();
		txtIngrediente.setBounds(853, 48, 131, 20);
		contentPane.add(txtIngrediente);
		txtIngrediente.setColumns(10);
		txtIngrediente.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyTyped(KeyEvent x) 
			{	validar(x);
				if(txtIngrediente.getText().length() == 50)
					x.consume();
			}
		});
		
		final JList lista_ing = new JList();
		lista_ing.setModel(ingredientes);
		panel_ingredientes.add(lista_ing);
		
		JScrollPane scroll_ingredientes = new JScrollPane(lista_ing);
		panel_ingredientes.add(scroll_ingredientes);
		
		JButton btnAgregarIng = new JButton("Agregar");
		btnAgregarIng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				if(!txtIngrediente.getText().isEmpty())
				{
				
					ingredientes.addElement(txtIngrediente.getText());
					txtIngrediente.setText("");
				}
			}
		});
		btnAgregarIng.setBounds(853, 79, 131, 23);
		contentPane.add(btnAgregarIng);
		
		final JButton btnEliminarIng = new JButton("Eliminar");
		btnEliminarIng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(lista_ing.getSelectedIndex() != -1)
				{
					ingredientes.remove(lista_ing.getSelectedIndex());
				}
				if(ingredientes.isEmpty())
					btnEliminarIng.setEnabled(false);
			}
		});
		btnEliminarIng.setBounds(853, 114, 131, 23);
		contentPane.add(btnEliminarIng);
			
		JLabel lblIngredientes = new JLabel("Ingredientes");
		lblIngredientes.setBounds(753, 24, 129, 14);
		contentPane.add(lblIngredientes);
		
//-------------------------------------------------------------------------------------------------------------------------------------		
		utensilios = new DefaultListModel();
		JPanel panel_utensilios = new JPanel();					//UTENSILIOS
		panel_utensilios.setBounds(730, 235, 113, 161);
		contentPane.add(panel_utensilios);
		panel_utensilios.setLayout(new GridLayout());
		
		txtUtensilio = new JTextField();
		txtUtensilio.setBounds(853, 235, 131, 20);
		contentPane.add(txtUtensilio);
		txtUtensilio.setColumns(10);
		txtUtensilio.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyTyped(KeyEvent x) 
			{	validar(x);
				if(instrucciones.getText().length() == 50)
					x.consume();
			}
		});
		
		final JList lista_uten = new JList();
		lista_uten.setModel(utensilios);
		panel_utensilios.add(lista_uten);
		
		JScrollPane scroll_utensilios = new JScrollPane(lista_uten);
		panel_utensilios.add(scroll_utensilios);
		
		JButton btnAgregarUten = new JButton("Agregar");
		btnAgregarUten.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(!txtUtensilio.getText().isEmpty())
				{
					utensilios.addElement(txtUtensilio.getText());
					txtUtensilio.setText("");
				}
			}
		});
		btnAgregarUten.setBounds(853, 266, 131, 23);
		contentPane.add(btnAgregarUten);
		
		final JButton btnEliminarUten = new JButton("Eliminar");
		btnEliminarUten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(lista_uten.getSelectedIndex()!=-1)
				{
					utensilios.remove(lista_uten.getSelectedIndex());
				}
				if(utensilios.isEmpty())
				{
					btnEliminarUten.setEnabled(false);
				}
			}
		});
		btnEliminarUten.setBounds(853, 300, 131, 23);
		contentPane.add(btnEliminarUten);	
		
		JLabel lblUtensilios = new JLabel("Utensilios");
		lblUtensilios.setBounds(753, 217, 86, 14);
		contentPane.add(lblUtensilios);
//-------------------------------------------------------------------------------------------------------------------------------------		
		categorias = new DefaultListModel();
		JPanel panel_categorias = new JPanel();						//CATEGORIAS
		panel_categorias.setBounds(730, 424, 113, 161);
		contentPane.add(panel_categorias);
		panel_categorias.setLayout(new GridLayout());
		
		txtCategoria = new JTextField();
		txtCategoria.setBounds(853, 424, 131, 20);
		contentPane.add(txtCategoria);
		txtCategoria.setColumns(10);
		txtCategoria.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyTyped(KeyEvent x) 
			{	validar(x);
				if(txtCategoria.getText().length() == 50)
					x.consume();
			}
		});
		
		final JList lista_categ = new JList();
		lista_categ.setModel(categorias);
		panel_categorias.add(lista_categ);
		
		JScrollPane scroll_categorias = new JScrollPane(lista_categ);
		panel_categorias.add(scroll_categorias);
		
		JButton btnAgregarCateg = new JButton("Agregar");
		btnAgregarCateg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(!txtCategoria.getText().isEmpty())
				{
					categorias.addElement(txtCategoria.getText());
					txtCategoria.setText("");
				}
			}
		});
		btnAgregarCateg.setBounds(853, 455, 131, 23);
		contentPane.add(btnAgregarCateg);
		
		final JButton btnEliminarCateg = new JButton("Eliminar");
		btnEliminarCateg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(lista_categ.getSelectedIndex()!=-1)
				{
					btnEliminarCateg.setEnabled(true);
					categorias.remove(lista_categ.getSelectedIndex());
				}
				if(categorias.isEmpty())
					btnEliminarCateg.setEnabled(false);
			}
		});
		btnEliminarCateg.setBounds(853, 489, 131, 23);
		contentPane.add(btnEliminarCateg);
		
		JLabel lblCategorias = new JLabel("Categorias");
		lblCategorias.setBounds(753, 407, 99, 14);
		contentPane.add(lblCategorias);
		
//-------------------------------------------------------------------------------------------------------------------------------------
		JButton btnCancelar = new JButton("Cancelar");				//BOTON CANCELAR
		btnCancelar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) //vuelve a la ventana anterior sin ningun cambio 
			{
				dispose();
				ventanaAnterior.setVisible(true);	
			}
			
		});
		btnCancelar.setBounds(47, 627, 120, 33);
		contentPane.add(btnCancelar);
		
//-------------------------------------------------------------------------------------------------------------------------------------
		JButton btnFinalizar = new JButton("Finalizar"); 		//BOTON FINALIZAR
		btnFinalizar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 	//al presionar finalizar se agregan todos los datos llenados a la receta
			{
				if(nombreReceta.getText().length() < tamMinNombre)
					JOptionPane.showMessageDialog(VentanaCrearReceta.this,"Ingresó mal o faltó llenar algun campo","Error",0);
				else
				{
					receta.setNombreReceta(nombreReceta.getText());
					receta.setIngredientes(casteo(ingredientes));
					receta.setInstrucciones(instrucciones.getText());
					receta.setTiempoEstimadoPreparacion(Integer.parseInt(tiempoEstimado.getText()));
					receta.setUtensilios(casteo(utensilios));
					receta.setCategorias(casteo(categorias));
					receta.setAutor(alumno);
					VentanaReceta ventanaReceta=new VentanaReceta(receta);
					int reply = JOptionPane.showConfirmDialog(null, "¿Esta seguro?", "Confirmacion", JOptionPane.YES_NO_OPTION);
				    
					if (reply == JOptionPane.YES_OPTION)	//si presiona que si se agrega la receta al ArrayList de recetas
				    {	
						Archivos archivo = new Archivos();
				    	receta.setVentanaPrincipal(ventanaReceta);	//se muestra la ventanaPrincipal de la receta
				    	setVisible(false);
				    	receta.getVentanaPrincipal().mostrarVentana(ventanaAnterior,alumno);
				    	alumno.getListaRecetas().agregarReceta(receta);					
				    	ventanaAnterior.actualizar(alumno.getListaRecetas().getArrayRecetas());				    	
				    	try {						//se guarda la receta en el txt
				    		archivo.actualizarDatosReceta(alumno,receta);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
				    	dispose();
				    }
				}	
			}
		});
		btnFinalizar.setBounds(837, 627, 120, 33);
		contentPane.add(btnFinalizar);
	} //FIN DEL CONTRUCTOR

/*||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
  ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
  ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
  ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
  												CONTRUCTOR MODIFICAR RECETA
 */
	public VentanaCrearReceta(final Receta receta,final Alumno alumno,final VentanaMisRecetas ventanaAnterior)
	{																						
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 15, 1000, 700);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bienvenido a la creacion de tu receta");
		lblNewLabel.setBounds(10, 35, 260, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("No olvides ser ordenado, escribir correctamente y coherente");
		lblNewLabel_1.setBounds(10, 60, 359, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblAsiLosDemas = new JLabel("asi los demas puedan ocupar tu receta sin problemas");
		lblAsiLosDemas.setBounds(10, 83, 394, 14);
		contentPane.add(lblAsiLosDemas);
		
		JLabel lblSeCreativo = new JLabel("SE CREATIVO!!");
		lblSeCreativo.setBounds(10, 108, 86, 14);
		contentPane.add(lblSeCreativo);
		
		final JLabel lblVernombre = new JLabel("");
		lblVernombre.setBounds(682, 13, 46, 14);
		contentPane.add(lblVernombre);
//-------------------------------------------------------------------------------------------------------------------------------------
		JLabel lblNombreReceta = new JLabel("Nombre Receta"); 							//NOMBRE RECETA
		lblNombreReceta.setBounds(256, 13, 113, 14);
		contentPane.add(lblNombreReceta);
		
		nombreReceta = new JTextField(receta.getNombreReceta());
		nombreReceta.setBounds(356, 10, 320, 20);
		contentPane.add(nombreReceta);
		nombreReceta.setColumns(10);
		
		nombreReceta.addKeyListener(new KeyListener()
		{
			public void keyTyped(KeyEvent e)
			{
				soloLetras(e);
				if (nombreReceta.getText().length() == tamMaxNombre)
					e.consume();
			}
			public void keyPressed(KeyEvent e){
				if(nombreReceta.getText().length() <= tamMaxNombre && nombreReceta.getText().length() > tamMinNombre){
					nombreReceta.setBackground(Color.green);
					lblVernombre.setText("\u2713");
				}
			}
			public void keyReleased(KeyEvent e)
			{
				if(nombreReceta.getText().length() <= tamMinNombre){
					nombreReceta.setBackground(Color.red);
					lblVernombre.setText("\u2717");
				}
			}
		});
//-------------------------------------------------------------------------------------------------------------------------------------
		JLabel lblNewLabel_2 = new JLabel("Tiempo estimado");			//TIEMPO ESTIMADO PREPARACIÓN
		lblNewLabel_2.setBounds(10, 150, 113, 14);
		contentPane.add(lblNewLabel_2);
		
		final JLabel lblVerifTiempoEst = new JLabel();
		lblVerifTiempoEst.setBounds(177, 150, 46, 14);;
		contentPane.add(lblVerifTiempoEst);
		
		tiempoEstimado = new JTextField(String.valueOf(receta.getTiempoEstimadoPreparacion()));
		tiempoEstimado.setBounds(111, 147, 56, 20);
		contentPane.add(tiempoEstimado);
		tiempoEstimado.setColumns(10);
		tiempoEstimado.addKeyListener(new KeyListener()
		{
			int tamanoTiempoEst = 3;
			public void keyTyped(KeyEvent e)
			{
				soloNumeros(e);
				if (tiempoEstimado.getText().length() == tamanoTiempoEst)
					e.consume();
			}
			public void keyPressed(KeyEvent e){
				if(tiempoEstimado.getText().length() <= tamanoTiempoEst && !(tiempoEstimado.getText().isEmpty())){
					tiempoEstimado.setBackground(Color.green);
					lblVerifTiempoEst.setText("\u2713");
				}
			}
			public void keyReleased(KeyEvent e)
			{
				if(tiempoEstimado.getText().length() == 0){
					tiempoEstimado.setBackground(Color.red);
					lblVerifTiempoEst.setText("\u2717");
				}
			}
		});
		
//-------------------------------------------------------------------------------------------------------------------------------------
		JPanel panel_instrucciones = new JPanel();								//INSTRUCCIONES
		panel_instrucciones.setBounds(10, 235, 666, 353);
		contentPane.add(panel_instrucciones);
		panel_instrucciones.setLayout(new GridLayout());
		
		final JTextArea instrucciones = new JTextArea(receta.getInstrucciones());							
		panel_instrucciones.add(instrucciones);
		instrucciones.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyTyped(KeyEvent x) 
			{
				validar(x);
				if(instrucciones.getText().length() == 800)
					x.consume();
			}
		});
		instrucciones.setLineWrap(true);   		//Limitar caracteres dentro del JTextArea
		instrucciones.setWrapStyleWord(true);
		
		JScrollPane scroll_instrucciones = new JScrollPane(instrucciones);
		panel_instrucciones.add(scroll_instrucciones);
		
		JLabel lblInstrucciones = new JLabel("Instrucciones");
		lblInstrucciones.setBounds(302, 204, 235, 20);
		contentPane.add(lblInstrucciones);
		
//-------------------------------------------------------------------------------------------------------------------------------------	
		ingredientes = casteoAlReves(receta.getIngredientes());
		JPanel panel_ingredientes = new JPanel();							//INGREDIENTES
		panel_ingredientes.setBounds(730, 45, 113, 161);
		contentPane.add(panel_ingredientes);
		panel_ingredientes.setLayout(new GridLayout());
		
		txtIngrediente = new JTextField();
		txtIngrediente.setBounds(853, 48, 131, 20);
		contentPane.add(txtIngrediente);
		txtIngrediente.setColumns(10);
		
		final JList lista_ing = new JList();
		lista_ing.setModel(ingredientes);
		panel_ingredientes.add(lista_ing);
		
		JButton btnAgregarIng = new JButton("Agregar");
		btnAgregarIng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				if(!txtIngrediente.getText().isEmpty())
				{
				
					ingredientes.addElement(txtIngrediente.getText());
					txtIngrediente.setText("");
				}
			}
		});
		btnAgregarIng.setBounds(853, 79, 131, 23);
		contentPane.add(btnAgregarIng);
		
		JScrollPane scroll_ingredientes = new JScrollPane(lista_ing);
		panel_ingredientes.add(scroll_ingredientes);
		
		final JButton btnEliminarIng = new JButton("Eliminar");
		btnEliminarIng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(lista_ing.getSelectedIndex() != -1)
				{
					ingredientes.remove(lista_ing.getSelectedIndex());
				}
			}
		});
		btnEliminarIng.setBounds(853, 114, 131, 23);
		contentPane.add(btnEliminarIng);
			
		JLabel lblIngredientes = new JLabel("Ingredientes");
		lblIngredientes.setBounds(753, 24, 129, 14);
		contentPane.add(lblIngredientes);
		
//-------------------------------------------------------------------------------------------------------------------------------------		
		utensilios = casteoAlReves(receta.getUtensilios());
		JPanel panel_utensilios = new JPanel();									//UTENSILIOS
		panel_utensilios.setBounds(730, 235, 113, 161);
		contentPane.add(panel_utensilios);
		panel_utensilios.setLayout(new GridLayout());
		
		txtUtensilio = new JTextField();
		txtUtensilio.setBounds(853, 235, 131, 20);
		contentPane.add(txtUtensilio);
		txtUtensilio.setColumns(10);
		
		final JList lista_uten = new JList();
		lista_uten.setModel(utensilios);
		panel_utensilios.add(lista_uten);
		
		JScrollPane scroll_utensilios = new JScrollPane(lista_uten);
		panel_utensilios.add(scroll_utensilios);
		
		JButton btnAgregarUten = new JButton("Agregar");
		btnAgregarUten.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(!txtUtensilio.getText().isEmpty())
				{
					utensilios.addElement(txtUtensilio.getText());
					txtUtensilio.setText("");
				}
			}
		});
		btnAgregarUten.setBounds(853, 266, 131, 23);
		contentPane.add(btnAgregarUten);
		
		final JButton btnEliminarUten = new JButton("Eliminar");
		btnEliminarUten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(lista_uten.getSelectedIndex()!=-1)
				{
					utensilios.remove(lista_uten.getSelectedIndex());
				}
			}
		});
		btnEliminarUten.setBounds(853, 300, 131, 23);
		contentPane.add(btnEliminarUten);	
		
		JLabel lblUtensilios = new JLabel("Utensilios");
		lblUtensilios.setBounds(753, 217, 86, 14);
		contentPane.add(lblUtensilios);
//-------------------------------------------------------------------------------------------------------------------------------------		
		categorias = casteoAlReves(receta.getCategorias());
		JPanel panel_categorias = new JPanel();									//CATEGORIAS
		panel_categorias.setBounds(730, 424, 113, 161);
		contentPane.add(panel_categorias);
		panel_categorias.setLayout(new GridLayout());
		
		txtCategoria = new JTextField();
		txtCategoria.setBounds(853, 424, 131, 20);
		contentPane.add(txtCategoria);
		txtCategoria.setColumns(10);
		
		final JList lista_categ = new JList();
		lista_categ.setModel(categorias);
		panel_categorias.add(lista_categ);
		
		JScrollPane scroll_categorias = new JScrollPane(lista_categ);
		panel_categorias.add(scroll_categorias);
		
		JButton btnAgregarCateg = new JButton("Agregar");				//BOTON AGREGAR
		btnAgregarCateg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(!txtCategoria.getText().isEmpty())
				{
					categorias.addElement(txtCategoria.getText());
					txtCategoria.setText("");
				}
			}
		});
		btnAgregarCateg.setBounds(853, 455, 131, 23);
		contentPane.add(btnAgregarCateg);
		
		final JButton btnEliminarCateg = new JButton("Eliminar");
		btnEliminarCateg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(lista_categ.getSelectedIndex()!=-1)
				{
					btnEliminarCateg.setEnabled(true);
					categorias.remove(lista_categ.getSelectedIndex());
				}
			}
		});
		btnEliminarCateg.setBounds(853, 489, 131, 23);
		contentPane.add(btnEliminarCateg);
		
		JLabel lblCategorias = new JLabel("Categorias");
		lblCategorias.setBounds(753, 407, 99, 14);
		contentPane.add(lblCategorias);
		
//-------------------------------------------------------------------------------------------------------------------------------------
		JButton btnCancelar = new JButton("Cancelar");						//BOTON CANCELAR
		btnCancelar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{	
				dispose();
				ventanaAnterior.setVisible(true);
			}
			
		});
		btnCancelar.setBounds(47, 627, 120, 33);
		contentPane.add(btnCancelar);
		
//-------------------------------------------------------------------------------------------------------------------------------------
		JButton btnFinalizar = new JButton("Finalizar"); 					//BOTON FINALIZAR
		btnFinalizar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) //al presionar finalizar se agregan todos los datos llenados a la receta
			{
				if(nombreReceta.getText().length() < tamMinNombre)
					JOptionPane.showMessageDialog(VentanaCrearReceta.this,"Ingresó mal o faltó llenar algun campo","Error",0);
				else
				{
					receta.setNombreReceta(nombreReceta.getText());
					receta.setIngredientes(casteo(ingredientes));
					receta.setInstrucciones(instrucciones.getText());
					receta.setTiempoEstimadoPreparacion(Integer.parseInt(tiempoEstimado.getText()));
					receta.setUtensilios(casteo(utensilios));
					receta.setCategorias(casteo(categorias));
					receta.setAutor(alumno);
					VentanaReceta ventanaReceta=new VentanaReceta(receta);
					receta.setVentanaPrincipal(ventanaReceta);
					int reply = JOptionPane.showConfirmDialog(null, "¿Esta seguro?", "Confirmacion", JOptionPane.YES_NO_OPTION);
				    if (reply == JOptionPane.YES_OPTION)	//si presiona que si, se modifican los datos en el txt
				    {
				    	if(alumno.getListaRecetas().modificarReceta(receta))
				    	{
				    		dispose();
				    		receta.getVentanaPrincipal().mostrarVentana(ventanaAnterior,alumno);
				    	}
				    	else
				    		JOptionPane.showMessageDialog(VentanaCrearReceta.this,"No se pudo modificar la receta","Error",0);
				    }
				}
			}

		});
		btnFinalizar.setBounds(837, 627, 120, 33);
		contentPane.add(btnFinalizar);
	}
	
	public void soloLetras(KeyEvent letra){		//solo deja escribir letras
		char c=letra.getKeyChar(); 
	       
		if(Character.isDigit(c)) { 
			getToolkit().beep(); 
	        letra.consume(); 
	    }
	}
	
	public void validar(KeyEvent letra)			//no deja escribir el caracter |
	{
		char c=letra.getKeyChar();
		if(c=='|')
		{
			getToolkit().beep(); 
	        letra.consume();
		}
	}
	
	public void soloNumeros(KeyEvent letra) 	//solo deja escribir numeros
	{ 
	    char c=letra.getKeyChar();     
	    
	    if(!Character.isDigit(c)) { 
	    	getToolkit().beep(); 
	    	letra.consume();  
	    } 
	 
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
	public DefaultListModel casteoAlReves(String[] listaEntrada) //convierte de String[] a DefaultListModel
	{
		DefaultListModel listaFinal= new DefaultListModel();
		int i;
		for(i=0;i<listaEntrada.length;i++)
		{
			listaFinal.addElement(listaEntrada[i]);
		}
		return listaFinal;
	}
		
}
