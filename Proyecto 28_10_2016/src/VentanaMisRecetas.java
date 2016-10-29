import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;

public class VentanaMisRecetas extends JFrame 
{

	private JPanel contentPane;
	private VentanaCrearReceta recetaVentanaNueva;
	private DefaultListModel recetas;
	public VentanaMisRecetas(final Alumno alumno,final VentanaAlumno ventanaUsuario) 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		
		JLabel lblRecetas = new JLabel("Recetas");
		lblRecetas.setBounds(10, 23, 85, 14);
		contentPane.add(lblRecetas);
		
		recetas=new DefaultListModel();
		
		convertir(recetas,alumno.getListaRecetas().getArrayRecetas());
//------------------------------------------------------------------------------------------------------------------------		
		JPanel panel_recetas = new JPanel();										//LISTA RECETAS
		panel_recetas.setBounds(10, 38, 250, 212);
		contentPane.add(panel_recetas);
		panel_recetas.setLayout(new GridLayout());
		
		final JList lista_recetas = new JList();
		panel_recetas.add(lista_recetas);
		lista_recetas.setModel(recetas);
//------------------------------------------------------------------------------------------------------------------------		
		JButton btnMostrarReceta = new JButton("Mostrar Receta");					//BOTON MOSTRAR RECETA
		btnMostrarReceta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				if(lista_recetas.getSelectedIndex()!=-1)
				{
					Receta recetaMostrar= ((Receta)recetas.getElementAt(lista_recetas.getSelectedIndex()));
					if(recetaMostrar!=null)
						recetaMostrar.getVentanaPrincipal().mostrarVentana(VentanaMisRecetas.this,alumno);
					setVisible(false);
				}
			}
		});
		btnMostrarReceta.setBounds(291, 143, 133, 30);
				contentPane.add(btnMostrarReceta);
//------------------------------------------------------------------------------------------------------------------------		
		JButton btnCrearReceta = new JButton("Crear Receta");						//BOTON CREAR RECETA
		btnCrearReceta.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				recetaVentanaNueva = new VentanaCrearReceta(alumno,VentanaMisRecetas.this);
				recetaVentanaNueva.setVisible(true);
				setVisible(false);
			}
		});
		contentPane.setLayout(null);
		btnCrearReceta.setBounds(291, 38, 133, 30);
		contentPane.add(btnCrearReceta);
		
//------------------------------------------------------------------------------------------------------------------------		
		final JButton btnModificarReceta = new JButton("Modificar Receta");				//BOTON MODIFICAR RECETA
		btnModificarReceta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(lista_recetas.getSelectedIndex()!=-1)
				{
					setVisible(false);
					recetaVentanaNueva = new VentanaCrearReceta(((Receta)recetas.getElementAt(lista_recetas.getSelectedIndex())),alumno,VentanaMisRecetas.this);
					recetaVentanaNueva.setVisible(true);
				}
				
			}
		});
		btnModificarReceta.setBounds(291, 72, 133, 30);
		contentPane.add(btnModificarReceta);
		
//------------------------------------------------------------------------------------------------------------------------		
		JButton btnEliminarReceta = new JButton("Eliminar Receta");					//BOTON ELIMINAR RECETA
		btnEliminarReceta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(lista_recetas.getSelectedIndex()!=-1)
				{
					int reply = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar de forma permanente esta receta?", "Confirmacion", JOptionPane.YES_NO_OPTION);
				    if (reply == JOptionPane.YES_OPTION)
				    {
				    	if(alumno.getListaRecetas().eliminarReceta((Receta)(recetas.getElementAt(lista_recetas.getSelectedIndex())),alumno.getRut()))
				    	{
				    		recetas.remove(lista_recetas.getSelectedIndex());
				    	}
				    }
				}
			}
		});
		btnEliminarReceta.setBounds(291, 107, 133, 30);
		contentPane.add(btnEliminarReceta);

//------------------------------------------------------------------------------------------------------------------------
		JButton btnAtras = new JButton("Atras");									//BOTON ATRAS
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				ventanaUsuario.setVisible(true);
				dispose();
			}
		});
		btnAtras.setBounds(291, 220, 133, 30);
		contentPane.add(btnAtras);
		
	}//FIN CONTRUCTOR
	
	// cambia de arrayList a DefaultListModel
	public void convertir(DefaultListModel recetasList,ArrayList<Receta> recetasArray) 
	{
		int cont;
		for(int i=0;i<recetasArray.size();i++)
		{
			cont=0;
			for(int j=0;j<recetasList.size();j++)
			{
				if(recetasArray.get(i)==((Receta)recetasList.getElementAt(j)))
					cont++;
			}
			if(cont==0)
				recetasList.addElement(recetasArray.get(i));
		}
	}
	public void actualizar(ArrayList<Receta> recetas)
	{
		convertir(this.recetas,recetas);
	}
	
}
