import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;

public class VentanaBuscarReceta extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombreReceta;
	private DefaultListModel recetas;
													
	public VentanaBuscarReceta(final ArrayList<Receta> arrayReceta, final VentanaAlumno ventanaUsuario,final Alumno usuario) 
	{											//ventana para la busqueda de recetas de todos los alumnos
		recetas = new DefaultListModel();
		final DefaultListModel recetasEncontradas = new DefaultListModel();
		AbstractListModel modelFiltro;
		setResizable(false);
		setTitle("Ventana Buscar Receta");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 632, 397);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblReceta = new JLabel("Receta: ");
		lblReceta.setBounds(67, 29, 46, 14);
		contentPane.add(lblReceta);
//----------------------------------------------------------------------------------------------------------------------------
		JPanel panelMostrarRecetas = new JPanel();		//LISTA DE RECETAS ENCONTRADAS
		panelMostrarRecetas.setBounds(15, 60, 500, 290);
		contentPane.add(panelMostrarRecetas);
		panelMostrarRecetas.setLayout(new GridLayout());
		
		final JList listaRecetas = new JList();
		listaRecetas.setModel(recetas);
		panelMostrarRecetas.add(listaRecetas);
		
		JScrollPane scrollRecetas = new JScrollPane(listaRecetas);
		panelMostrarRecetas.add(scrollRecetas);
		
		txtNombreReceta = new JTextField();
		txtNombreReceta.setBounds(123, 26, 326, 20);
		contentPane.add(txtNombreReceta);
		txtNombreReceta.setColumns(10);
//-------------------------------------------------------------------------------------------------------------------------
		final JCheckBox checkFiltro = new JCheckBox("");	//FILTRO
		checkFiltro.setBounds(554, 25, 97, 23);
		contentPane.add(checkFiltro);
		checkFiltro.setSelected(false);
		
		final Receta recetaBuscada = new Receta();
		JButton btnFiltro = new JButton("Filtro");											
		btnFiltro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) //boton de filtro
			{
				VentanaFiltroRecetas ventanaFiltroRecetas = new VentanaFiltroRecetas(VentanaBuscarReceta.this,recetaBuscada);
				ventanaFiltroRecetas.setVisible(true);					//se envia una receta vacia
				setVisible(false);										//para que se llene con valores que el usuario desea
			}															//y posteriormente utilizar esos valores para filtrar
																		//la busqueda
		});
		btnFiltro.setBounds(459, 25, 89, 23);
		contentPane.add(btnFiltro);
//--------------------------------------------------------------------------------------------------------------------------
		JButton btnBuscar = new JButton("Buscar");			//BOTON BUSCAR RECETAS
		
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				if(!txtNombreReceta.getText().isEmpty() && !txtNombreReceta.getText().equals(""))
				{
					String nombreRecetaBuscada = txtNombreReceta.getText();
					int cont;
					recetas.removeAllElements(); //se parte con la lista de recetas vacias
					if(checkFiltro.isSelected())
					{					//si se selecciono el ticket filtro se compara cada elemento de la busqueda con la receta del filtro
						for(int i=0;i<arrayReceta.size();i++)
						{
							cont=0;
							if(!String.valueOf(recetaBuscada.getTiempoEstimadoPreparacion()).isEmpty() && !String.valueOf(recetaBuscada.getTiempoEstimadoPreparacion()).equals(""))
							{
								if(arrayReceta.get(i).getTiempoEstimadoPreparacion() > recetaBuscada.getTiempoEstimadoPreparacion())
									cont++;
							}
							if(recetaBuscada.getAutor()!=null)
							{
								if((arrayReceta.get(i).getAutor().getNombrePersona().indexOf(recetaBuscada.getAutor().getNombrePersona())==-1))
									cont++;
							}
							if(recetaBuscada.getIngredientes()!=null)
							{
								if(!existenString(arrayReceta.get(i).getIngredientes(),recetaBuscada.getIngredientes()))
									cont++;
							}
							if(recetaBuscada.getUtensilios()!=null)
							{
								if(!existenString(arrayReceta.get(i).getUtensilios(),recetaBuscada.getIngredientes()))
									cont++;
							}
							if(recetaBuscada.getCategorias()!=null)
							{
								if(!existenString(arrayReceta.get(i).getCategorias(),recetaBuscada.getIngredientes()))
									cont++;
							}
							if(cont==0)
								recetas.addElement(arrayReceta.get(i)); //si paso el filtro se agrega a la lista
						}												
					}
					else
					{
						for(int i=0;i<arrayReceta.size();i++)
						{
							if(arrayReceta.get(i).getNombreReceta().indexOf(nombreRecetaBuscada)!=-1)
							{
								recetas.addElement(arrayReceta.get(i));
							}
						}
					}
				}
			}
		});
		btnBuscar.setBounds(525, 59, 84, 33);
		contentPane.add(btnBuscar);
//----------------------------------------------------------------------------------------------------------------------------
		JButton btnMostrar = new JButton("Mostrar");			//BOTON MOSTRAR
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				if(listaRecetas.getSelectedIndex()!=-1)
				{					//muestra la ventanaPrincipal de la receta seleccionada
					((Receta)recetas.get(listaRecetas.getSelectedIndex())).getVentanaPrincipal().mostrarVentana(VentanaBuscarReceta.this,usuario);
					setVisible(false);
				}
			}
		});
		btnMostrar.setBounds(525, 103, 84, 33);
		contentPane.add(btnMostrar);
//----------------------------------------------------------------------------------------------------------------------------
		JButton btnAtras = new JButton("Atras");					//BOTON ATRAS
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ventanaUsuario.setVisible(true);				//se devuelve a la ventana anterior
				dispose();
			}
		});
		btnAtras.setBounds(525, 317, 84, 33);
		contentPane.add(btnAtras);
		
		
	}//FIN CONTRUCTOR
	
	public void convertir(DefaultListModel recetasList,ArrayList<Receta> arrayList) // cambia de arrayList a DefaultListModel
	{
		int cont;
		for(int i=0;i<arrayList.size();i++)
		{
			cont=0;
			for(int j=0;j<recetasList.size();j++)
			{
				if(arrayList.get(i)==((Receta)recetasList.getElementAt(j)))
					cont++;
			}
			if(cont==0)
				recetasList.addElement(arrayList.get(i));
		}
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
	
	public boolean existenString(String[] listaAgregar,String[] listaIngredientes)
	{													//recibe una lista y verifica que si se repite un valor de una lista
		int cont=0,contTotal=0;							//en la otra
		for(int i=0;i<listaIngredientes.length;i++)
		{
			cont=0;
			for(int j=0;j<listaAgregar.length;j++)
			{
				if(listaAgregar[i].indexOf(listaIngredientes[j])!=-1)
				{
					cont++;
				}
				if(cont!=0)
					contTotal++;
			}
		}
		if(contTotal==listaIngredientes.length)
			return true;
		return false;
	}
	
}


