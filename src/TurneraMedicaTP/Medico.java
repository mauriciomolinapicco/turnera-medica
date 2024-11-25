package TurneraMedicaTP;

public class Medico {
	private String nombreCompleto;
	private String matricula;
	private String especialidad;
	private double precioConsulta;
	
	public Medico() {
	}
	
	public Medico(String nombre, String matricula, String especialidad, double precioConsulta) {
		this.nombreCompleto = nombre;
		this.matricula = matricula;
		this.especialidad = especialidad;
		this.precioConsulta = precioConsulta;
	}
	
	public String getEspecialidad() {
		return especialidad;
	}
	
	public String getMatricula() {
		return matricula;
	}
	
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	
	public double getPrecioConsulta() {
		return precioConsulta;
	}
	
	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	
	public void setPrecioConsulta(double precioConsulta) {
		this.precioConsulta = precioConsulta;
	}
}

