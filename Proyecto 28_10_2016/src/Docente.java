import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Docente extends Persona{

	private String contrasena;
	
	public Docente(){
		this ("","","","","",0,0);
		this.contrasena = "";
	}
	
	public Docente(String nombrePersona, String rut, String direccion, String correo, String sexo, int edad,
			int telefono) {
		super(nombrePersona, rut, direccion, correo, sexo, edad, telefono);
		}

	public Docente(String nombrePersona, String rut, String direccion, String correo, String sexo, int edad,
			int telefono,String contrasena) {
		super(nombrePersona, rut, direccion, correo, sexo, edad, telefono);
		this.setContrasena(contrasena);
		}
	
	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String generarContraseña(String rut) 
	{
		int pass,i,digito= 0, aux=0;
		String contrasena;
		pass = Integer.parseInt(rut);
		
		for(i=0;i<5;i++)				//se obtienen los ultimos digitos del rut
		{								//y se convierte en la contraseña
			digito = pass%10;
			pass = pass/10;
			aux = aux*10 + digito;
		}
		this.contrasena = String.valueOf(aux);
		contrasena = String.valueOf(aux);
		return contrasena;
	}
	
	public boolean validarDocente(String rut, String password) 
	{															//valida que los datos del docente sean correctos
		if(rut.equals(super.getRut()) && password.equals(getContrasena()))
		{
			return true;
		}
		return false;
	}
	
	public void actualizarTxtDocente() throws IOException {
		File archivoDocentes = new File("Universidad Catolica De Gastronomia\\Docentes\\"+getRut()+"\\DatosDocente"+getRut()+".txt");
												//abre el txt de los datos del docente						
		if(!archivoDocentes.exists()){
			crearCarpetaDocente();
		}										

		FileWriter fEscrit = new FileWriter(archivoDocentes);
	    BufferedWriter bEscrit = new BufferedWriter(fEscrit);
		PrintWriter pEscrit = new PrintWriter(bEscrit);
																//escribe los datos del docente en el txt 
		pEscrit.println(getNombrePersona()+"|"+getRut()+"|"+getCorreo()+"|"+getEdad()+"|"+getSexo()+"|"+getDireccion()+"|"+getTelefono()+"|"+getContrasena());	
		pEscrit.close();
		bEscrit.close();
	}	
	
	public void crearCarpetaDocente(){			//crea la carpeta del docente con su rut
		File carpeta = new File("Universidad Catolica De Gastronomia\\Docentes\\"+getRut());
		File archivo = new File(carpeta,"DatosDocente"+getRut()+".txt");
			
		if(!carpeta.exists()){
			carpeta.mkdirs();
		}	
		if(!archivo.exists()){
			try{
			archivo.createNewFile();
			}catch(IOException ex){ex.printStackTrace();}
		}
	}
}
