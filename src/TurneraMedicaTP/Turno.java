package TurneraMedicaTP;

import java.time.LocalDateTime;

public class Turno {
    private int id; 
    private Medico medico;  
    private Paciente paciente;  
    private LocalDateTime fechaHora; 

    public Turno(int id, Medico medico, Paciente paciente, LocalDateTime fechaHora) {
        this.id = id;
        this.medico = medico;
        this.paciente = paciente;
        this.fechaHora = fechaHora;
    }

    public Turno() {
        this.id = 0;  // o cualquier valor predeterminado adecuado
        this.paciente = null;
        this.medico = null;
        this.fechaHora = null;  // o un valor predeterminado si es necesario
    }

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

}
