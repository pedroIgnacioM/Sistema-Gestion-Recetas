
public class Comentario 
{
	private Alumno autor;
	private String texto;
	private int numero;
	private String rut;
	/* se ocupa el rut
	 * porque, cuando se cargan los comentarios de una receta, no se han ingresado todos los usuarios,
	 * por ende solo se carga el rut del creador de la receta,
	 * para que, cuando se carguen todos los usuarios poder direccionarlos con su respectico usuario
	*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	public Comentario(Alumno autor, String texto,int i) 
	{
		this.autor = autor;
		this.texto = texto;
		this.numero=i;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public Comentario(String rut, String texto,int i) 
	{
		this.rut=rut;
		this.texto = texto;
		this.numero=i;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String getRut() {
		return rut;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void setRut(String rut) {
		this.rut = rut;
	}
	
	
	
	
	
	
	
	
	
	public Alumno getAutor() {
		return autor;
	}
	
	
	
	
	
	
	
	
	
	
	
	public void setAutor(Alumno autor) {
		this.autor = autor;
	}
	
	
	
	
	
	
	
	
	
	
	
	public String getTexto() {
		return texto;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void setTexto(String texto) {
		this.texto = texto;
	}

	
	
	
	
	
	
	
	
	
	
	public int getNumero() {
		return numero;
	}
	
	
	
	
	
	
	
	
	
	
	
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public String toString()
	{
		String visible= autor.getNombrePersona()+"("+String.valueOf(numero)+")\n";
		return visible+texto+"\n";
	}
}
