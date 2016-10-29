
public abstract class Persona {
	private String nombrePersona;
	private String rut;
	private String direccion;
	private String sexo;
	private String correo;
	private int edad;
	private int telefono;
	
	public Persona(String nombrePersona, String rut, String direccion, String correo,
			String sexo, int edad, int telefono) {
		this.nombrePersona = nombrePersona;
		this.rut = rut;
		this.direccion = direccion;
		this.correo = correo;
		this.sexo = sexo;
		this.edad = edad;
		this.telefono = telefono;
	}
	
	public Persona(String nombrePersona) 
	{
		this.nombrePersona=nombrePersona;
	}

	public String getNombrePersona() {
		return nombrePersona;
	}

	public void setNombrePersona(String nombrePersona) {
		this.nombrePersona = nombrePersona;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getSexo() {
		return sexo;
	}
	
	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getCorreo() {
		return correo;
	}
	
	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
}
