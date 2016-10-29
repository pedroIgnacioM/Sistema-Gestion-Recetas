import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Archivos 
{
	
	
	public Archivos()
	{
		
	}
	
	//se cargar los datos de todos los usuarios de los txt y se genera el arrayList de alumnos
	public ArrayList <Alumno> cargarArchivoTextoUsuario(ArrayList <Alumno> arrayUsuarios)
	{
		
		File raiz = new File("Universidad Catolica De Gastronomia\\Alumnos");
		if(raiz.exists() && raiz.list()!=null){
			String [] carpeta = raiz.list();
		    arrayUsuarios = new ArrayList <Alumno>();
			for(int i=0;i<carpeta.length;i++){
				try(FileReader archivoLectura = new FileReader("Universidad Catolica De Gastronomia\\Alumnos\\"+carpeta[i]+"\\DatosUsuario"+carpeta[i]+".txt")){
					BufferedReader buffer = new BufferedReader(archivoLectura);
					if(buffer.ready()){
						String cadena = buffer.readLine();
				    	if(cadena != null){
				    		StringTokenizer st = new StringTokenizer(cadena,"|");
				    		String nombre = st.nextToken();
					    	String rut = st.nextToken();
					    	String correo = st.nextToken();
					    	int edad = Integer.parseInt(st.nextToken());
					    	String sexo = st.nextToken();
					    	String direccion = st.nextToken();
					    	int telefono = Integer.parseInt(st.nextToken());
					    	String contrasena = st.nextToken();
					    												//se agrega al ArrayList de usuarios
					    	arrayUsuarios.add(new Alumno(nombre,rut,direccion,correo,sexo,edad,telefono,contrasena));
					    	cargarDatosRecetas(arrayUsuarios.get(i));	//se llama a la funcion que carga las recetas de los txt
				    	}
				    buffer.close();
				   	archivoLectura.close();
					}
				}catch(IOException x){
					System.out.println("Error E/S: "+x);
				}
			}
		}
		return arrayUsuarios;
	}
	
	
	
	
	
	//se cargan los datos de las recetas de todos los txt del usuario
	public void cargarDatosRecetas(Alumno alumno)
	{				
		File raiz = new File("Universidad Catolica De Gastronomia\\Alumnos\\"+alumno.getRut()+"\\Recetas");
		if(raiz.exists() && raiz.list() != null){
			File [] archivosReceta = raiz.listFiles();
			for(int i=0;i<archivosReceta.length;i++){
				if(archivosReceta[i].isFile())
				{
					try(FileReader archivoLectura = new FileReader(archivosReceta[i]))
					{
						BufferedReader buffer = new BufferedReader(archivoLectura);
					
						if(buffer.ready()){
							String nombreReceta = buffer.readLine();
							String cadena = buffer.readLine();
							StringTokenizer st = new StringTokenizer(cadena,"|");
							int calificacionReceta = Integer.parseInt(st.nextToken());
							int tiempoEstimadoPreparacion = Integer.parseInt(st.nextToken());
				    		int caloriasTotales = Integer.parseInt(st.nextToken());
				    		String[] ingredientes = leerListaTxt(buffer);
					    	String[] utensilios = leerListaTxt(buffer);
					    	String[] tipos = leerListaTxt(buffer);
					    	String intrucciones = leerInstruccionesTxt(buffer);
					    	ArrayList<Comentario> comentarios = leerComentariosTxt(buffer);
					    	alumno.getListaRecetas().agregarReceta(new Receta(nombreReceta,ingredientes,utensilios,caloriasTotales,intrucciones,tiempoEstimadoPreparacion,tipos,calificacionReceta,comentarios,alumno));
					    }											//se agrega al ArrayList de recetas
						buffer.close();
				    	archivoLectura.close();
					}catch(IOException x){
						System.out.println("Error E/S: "+x);
					}
				}
			}
		}
		
	}		

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void escribirListaTxt(String [] lista,PrintWriter pEscrit)
	{						//este metodo escribe las lista de los utensilios,ingredientes y categorias en el txt de la receta
		if(lista!=null)
		{
			if(lista.length==0)
				pEscrit.println("0");	
			else
			{
				pEscrit.println(lista.length);			//primero ingresa el largo de la lista
				for(int i=0;i<lista.length;i++)
				{
					pEscrit.println(lista[i].toString());	//despues ingresa cada elemento del a lista
				}
			}
		}
		else
			pEscrit.println("0");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void escribirInstrucciones(String instruccion,PrintWriter pEscrit)
	{												//metodo que escribe las instrucciones de la receta en el txt de la receta
		if(instruccion!=null)
		{
			if(!instruccion.isEmpty())
			{
				if(!instruccion.equals(""))
				{
					pEscrit.println(instruccion+"|");
				}
				else
					pEscrit.println("0");
			}
			else
				pEscrit.println("0");
		}
		else
			pEscrit.println("0");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void escribirComentariosTxt(ArrayList<Comentario> listaComentarios,PrintWriter pEscrit)
	{														//metodo que escribe los comentarios de la receta en el txt
		if(listaComentarios!=null)
		{
			pEscrit.println(listaComentarios.size());	//lee primero la cantidad de elementos de la lista de comentarios
			for(int i=0;i<listaComentarios.size();i++)
			{
				if(listaComentarios.get(i)!=null)
				{							//separa cada elemento del comentario por un |
					pEscrit.println(listaComentarios.get(i).getAutor().getRut()+"|"+listaComentarios.get(i).getTexto()+"|"+listaComentarios.get(i).getNumero());
				}
			}
		}
		else
			pEscrit.println("0");
	}
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String [] leerListaTxt(BufferedReader buffer) throws NumberFormatException, IOException
	{													//lee cada elemento de una lista(ingredientes,utensilios y categorias)
		int cantidad = Integer.parseInt(buffer.readLine());		//primero lee la cantidad de elementos de la lista
		String[] lista = new String[cantidad];

		if(cantidad>0){
			for(int i=0;i<cantidad;i++){		//despues los lee un por uno
				lista[i] = buffer.readLine();
			}
		}
		return lista;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String leerInstruccionesTxt(BufferedReader buffer) throws NumberFormatException, IOException
	{											//lee las instrucciones de la receta
		String instruccion = new String();
		String cadena = new String();
		while(buffer.ready())	
		{
			cadena=buffer.readLine();
			if(cadena.indexOf('|')==-1)
			{				//si no existe el caracter | lo agrega al String instruccion
				instruccion+=cadena;
			}else
			{				//si existe lo lee sin el carcater | y retorna el String instruccion
				instruccion+=cadena.replace('|','\0');
				return instruccion;
			}
		}
		return null; 
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public ArrayList<Comentario> leerComentariosTxt(BufferedReader buffer) throws NumberFormatException, IOException
	{												//metodo que lee todos los comentarios del txt de la receta
		ArrayList<Comentario> comentarios= new ArrayList<Comentario>();
		String cadena = buffer.readLine();
		if(cadena!=null)
		{
			if(!cadena.equals(""))
			{
				int cantidad = Integer.parseInt(cadena); //primero lee la cantidad de comentarios
				String autor,codigo,texto;
				if(cantidad>0)
				for(int i=0;i<cantidad;i++)
				{								//despues lee uno por uno cada elemento del comentario
						cadena= buffer.readLine();
						StringTokenizer st = new StringTokenizer(cadena,"|");
						autor= st.nextToken();
						texto= st.nextToken();
						codigo= st.nextToken();
						if(!autor.equals("0") && !texto.equals("0"))
						{
							Comentario  comentarioAgregar = new Comentario(autor,texto,Integer.parseInt(codigo));
							comentarios.add(comentarioAgregar);
						}
				}
			}
		}
		return comentarios;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//ingresa todos los datos del usuario en su respectivo txt
	public void actualizarTxtUsuario(Alumno alumno) throws IOException 
	{							
		File archivoUsuarios;
		FileWriter fEscrit;
		BufferedWriter bEscrit;
		PrintWriter pEscrit;
		
		archivoUsuarios = new File("Universidad Catolica De Gastronomia\\Alumnos\\"
									+alumno.getRut()+"\\DatosUsuario"+alumno.getRut()+".txt");
		
		if(!archivoUsuarios.exists()){
			crearCarpetaUsuario(alumno);
		}

		fEscrit = new FileWriter(archivoUsuarios);
		bEscrit = new BufferedWriter(fEscrit);
		pEscrit = new PrintWriter(bEscrit);

		pEscrit.println(alumno.getNombrePersona()+"|"+alumno.getRut()+"|"
									+alumno.getCorreo()+"|"+alumno.getEdad()+"|"+alumno.getSexo()
									+"|"+alumno.getDireccion()+"|"+alumno.getTelefono()
									+"|"+alumno.getContrasena());
	
		pEscrit.close();
		bEscrit.close();
	}
	

	
	
	
	
	
	
	
	
	//elimina una receta "SOLO" del txt manteniendo intacto el ArrayList de recetas
	public boolean eliminarRecetaTxt(Receta receta, String rut)
	{																						
		File raiz = new File("Universidad Catolica De Gastronomia\\Alumnos\\"+rut+"\\Recetas");
		if(raiz.exists()){
			File archivoReceta = new File(raiz,receta.getNombreReceta()+".txt");
				if(archivoReceta.delete())
				{
					return true;
				}
		}
		return false;
	}
	
	
	
	
	
	
	// los datos de la receta se escriben en su respectivo txt
	public void actualizarDatosReceta(Alumno alumno,Receta receta) throws IOException
	{
		File archivoReceta = new File("Universidad Catolica De Gastronomia\\Alumnos\\"+alumno.getRut()
									+"\\Recetas\\"+receta.getNombreReceta()+".txt");
														
		if(!archivoReceta.exists()){
			crearCarpetaRecetas(alumno,receta);
		}
		
		FileWriter fEscrit = new FileWriter(archivoReceta);
		BufferedWriter bEscrit = new BufferedWriter(fEscrit);
		PrintWriter pEscrit = new PrintWriter(bEscrit);
	
		pEscrit.println(receta.getNombreReceta());
		pEscrit.println(receta.getCalificacionReceta()+"|"+receta.getTiempoEstimadoPreparacion()
																+"|"+receta.getCaloriasTotales());
		
		escribirListaTxt(receta.getIngredientes(), pEscrit);
		escribirListaTxt(receta.getUtensilios(), pEscrit);
		escribirListaTxt(receta.getCategorias(), pEscrit);
		escribirInstrucciones(receta.getInstrucciones(),pEscrit);
		escribirComentariosTxt(receta.getListaComentarios().getArrayComentarios(),pEscrit);
		
		pEscrit.close();
		bEscrit.close();
		System.out.println("actualizo datos receta");
	}
	
	public void crearCarpetaRecetas(Alumno alumno,Receta receta)
	{									// se crea una carpeta nueva con el rut del autor de la receta			
		File carpeta = new File("Universidad Catolica De Gastronomia\\Alumnos\\"+alumno.getRut()+"\\Recetas");
		File archivo = new File(carpeta,receta.getNombreReceta()+".txt");
		if(!carpeta.exists()){
			carpeta.mkdirs();	
		}
		
		if(!archivo.exists()){
			try{
				archivo.createNewFile();
			}catch(IOException ex){ex.printStackTrace();}
		}			
	}
	
	
	
	
	
	
	
	
	
	public void crearCarpetaUsuario(Alumno alumno)
	{							//este metodo crea una carpeta para el usuario
		File carpeta = new File("Universidad Catolica De Gastronomia\\Alumnos\\"+alumno.getRut());
		File archivo = new File(carpeta,"DatosUsuario"+alumno.getRut()+".txt");
		
		if(!carpeta.exists()){
			carpeta.mkdirs();
		}
		if(!archivo.exists()){
			try{
				archivo.createNewFile();
			}catch(IOException ex){ex.printStackTrace();}
		}
			
	}
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	public ArrayList <Docente> cargarArchivoTextoDocente(ArrayList <Docente> arrayDocentes)
	{										//se cargan los datos de todos los docentes de los txt
		//Generar Array De Docente

		File raiz = new File("Universidad Catolica De Gastronomia\\Docentes");
		if(raiz.exists() && raiz.list()!=null){
			String [] carpeta = raiz.list();
			arrayDocentes = new ArrayList <Docente>();
			for(int i=0;i<carpeta.length;i++){	
				try(FileReader archivoLectura = new FileReader("Universidad Catolica De Gastronomia\\Docentes\\"+carpeta[i]+"\\DatosDocente"+carpeta[i]+".txt")){
					BufferedReader buffer = new BufferedReader(archivoLectura);
					if(buffer.ready()){
						String cadena = buffer.readLine();
						if(cadena != null) {
							StringTokenizer st = new StringTokenizer(cadena, "|");
					    	String nombre = st.nextToken();
					    	String rut = st.nextToken();
					    	String correo = st.nextToken();
					    	int edad = Integer.parseInt(st.nextToken());
					    	String sexo = st.nextToken();
					    	String direccion = st.nextToken();
					    	int telefono = Integer.parseInt(st.nextToken());
					    	String contrasena = st.nextToken();		
					    	arrayDocentes.add(new Docente(nombre,rut,direccion,correo,sexo,edad,telefono,contrasena));
						};										//se agrega al ArrayList de docentes
				    buffer.close();
					archivoLectura.close();
					}
				}catch(IOException x){
					System.out.println("Error E/S: "+x);
				}
			}
			return arrayDocentes;
		}
		return arrayDocentes;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//elimina el usuario recibido del ArrayList de usuarios
	public boolean eliminarTxtAlumno(Alumno usuario)
	{    	
		if(eliminarCarpetaUsuario(usuario))
		{
			return true;
		}
		return false;	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public boolean eliminarCarpetaUsuario(Alumno usuario){
		File carpeta = new File("Universidad Catolica De Gastronomia\\Alumnos\\"+usuario.getRut());
		if(carpeta.exists()){							//elimina la carpeta del usuario recibido
			File[] archivos = carpeta.listFiles();
			if(eliminarArchivos(archivos))
				if(carpeta.delete())	
					return true;
		}
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//elimina los ficheros recibidos recibidos
	public boolean eliminarArchivos(File []archivos)
	{
		int cont=0;					
		
		for(int i=0;i<archivos.length;i++){
			if(archivos[i].isFile()){
				if(archivos[i].delete())
					cont++;
			}else{
				if(archivos[i].listFiles()!=null)
					if(!eliminarArchivos(archivos[i].listFiles()))
						return false;	
				archivos[i].delete();
				cont++;
			}
		}
		
		if(archivos.length==cont)
			return true;
		
		return false;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//llama al metodo actualizarTxtUsuario de cada usuario del ArrayList
	public boolean guardarTxtUsuarios(ArrayList<Alumno> arrayUsuarios) throws IOException 
	{									
		if(!arrayUsuarios.isEmpty()){	
			for(int i=0;i<arrayUsuarios.size();i++)
			{	
				actualizarTxtUsuario(arrayUsuarios.get(i));
			}	//este metodo guarda la informacion del usuario en su respectivo txt
			return true;
		}
		return false;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//llama al metodo actualizarTxtDocente de cada usuario del ArrayList
	public boolean guardarTxtDocentes(ArrayList<Docente> arrayDocentes) throws IOException 
	{		
		if(!arrayDocentes.isEmpty()){
			for(int i = 0;i<arrayDocentes.size();i++){
				arrayDocentes.get(i).actualizarTxtDocente();
			}	//este metodo guarda la informacion del docente en su respectivo txt
			return true;
		}
		return false;
	}
}
