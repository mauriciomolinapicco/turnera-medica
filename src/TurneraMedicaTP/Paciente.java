package TurneraMedicaTP;

public class Paciente {
	private String dni;
	private String nombreCompleto;
	private String fichaMedica;
	private int telefono;
	
	public Paciente(String dni, String nombreCompleto, String fichaMedica, int telefono) {
		this.dni = dni;
		this.nombreCompleto = nombreCompleto;
		this.fichaMedica = fichaMedica;
		this.telefono = telefono;
	}
	
	public String getDni() {
		return dni;
	}
	
	public String getFichaMedica() {
		return fichaMedica;
	}
	
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	
	public int getTelefono() {
		return telefono;
	}
	
	public void setDni(String dni) {
		this.dni = dni;
	}
	
	public void setFichaMedica(String fichaMedica) {
		this.fichaMedica = fichaMedica;
	}
	
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
}
