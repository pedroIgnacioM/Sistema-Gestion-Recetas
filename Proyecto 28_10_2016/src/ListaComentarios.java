import java.util.ArrayList;


public class ListaComentarios 
{
	private ArrayList<Comentario> arrayComentarios;
	
	
	
	
	
	
	
	public ListaComentarios(ArrayList<Comentario> arrayComentarios)
	{
		this.arrayComentarios= arrayComentarios;
	}
	
	
	
	
	
	
	
	
	public ListaComentarios()
	{
		this.arrayComentarios= new ArrayList<Comentario>();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//se cargan los arrayComentarios de todas las recetas
	public void  cargarArrayComentarios(Universidad universidad)
	{						
		for(int i=0;i<arrayComentarios.size();i++)
		{
			Alumno usuarioEncontrado = 
				universidad.getListaAlumnos().getAlumno(arrayComentarios.get(i).getRut());
			if(usuarioEncontrado!=null)
				arrayComentarios.get(i).setAutor(usuarioEncontrado);
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//agrega un comentario nuevo a la receta
	public Comentario agregarComentario(String comentario, Alumno comentador)
	{												
		Comentario comentarioNuevo = new Comentario(comentador,comentario,identificadorComentario());
		arrayComentarios.add(comentarioNuevo);
		return comentarioNuevo;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public ArrayList<Comentario> getArrayComentarios() {
		return arrayComentarios;
	}








	public void setArrayComentarios(ArrayList<Comentario> arrayComentarios) {
		this.arrayComentarios = arrayComentarios;
	}








	//crea un numero que identifique al comentario para poder modificarlo o eliminarlo
	public int identificadorComentario()
	{									
		int numero=0,cont;
		int correcto=0;
		while(correcto!=1)
		{
			cont=numero;
			if(arrayComentarios==null)
				arrayComentarios= new ArrayList<Comentario>();
			for(int i=0;i<arrayComentarios.size();i++)
			{
				if(arrayComentarios.get(i).getNumero()==numero)
					numero++;
			}
			if(cont==numero)
				correcto=1;		//se valida que el numero no lo tenga otro comentario
		}
		return numero;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//retorna un comentario segun el numero identificador que tenga
	public Comentario pedirComentario(int code)
	{					
		for(int i=0;i<arrayComentarios.size();i++)
		{
			if(arrayComentarios.get(i).getNumero()==code)
				return arrayComentarios.get(i);
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//elimina un comentario de la receta
	public boolean eliminarComentario(int codeNumerico)
	{												
		for(int i=0;i<arrayComentarios.size();i++)
		{
			if(arrayComentarios.get(i).getNumero()==codeNumerico)
			{
				arrayComentarios.remove(i);
				return true;
			}
		}
	
		return false;
	}
	
	
	
	
	
	


	
	
	
	
	
	
	
	
	
	

	//modifica un comentario de la receta
	public boolean modificarComentario(int codeNumerico,String nuevoComentario)
	{												
		for(int i=0;i<arrayComentarios.size();i++)
		{
			if(arrayComentarios.get(i).getNumero()==codeNumerico)
			{	
				arrayComentarios.get(i).setTexto(nuevoComentario);
				return true;
			}
		}
		return false;
	}
	
	
	
	
	
}

