package TurneraMedicaTP;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;

public class PanelTurnos extends PanelBase<Turno> {
    private PanelManager panelManager;
    private JComboBox<Paciente> comboPacientes;
    private JComboBox<Medico> comboMedicos;

    public PanelTurnos(PanelManager m) {
        super();
        this.panelManager = m;
    }

    @Override
    protected String getNombreLabelText() {
        return "";
    }

    @Override
    protected String getIdLabelText() {
        return "ID Turno";
    }

    @Override
    protected String getFieldTresLabelText() {
        return "";
    }

    @Override
    protected String getFieldCuatroLabelText() {
        return "Fecha y Hora YYYY-MM-DDThh:mm:ss";
    }

    @Override
    protected BaseTableModel<Turno> createTableModel() {
        return new TurnoTableModel();
    }

    @Override
    public void armarPanel() {
        super.armarPanel();

        // Configurar JComboBox para pacientes
        PacienteService pacienteService = new PacienteService();
        List<Paciente> pacientes;
        try {
            pacientes = pacienteService.getAll();
            comboPacientes = new JComboBox<>(pacientes.toArray(new Paciente[0]));

            comboPacientes.setRenderer(new ListCellRenderer<Paciente>() {
                @Override
                public Component getListCellRendererComponent(JList<? extends Paciente> list, Paciente value, int index, boolean isSelected, boolean cellHasFocus) {
                    JLabel label = new JLabel(value.getNombreCompleto()); 
                    if (isSelected) {
                        label.setBackground(list.getSelectionBackground());
                        label.setForeground(list.getSelectionForeground());
                    } else {
                        label.setBackground(list.getBackground());
                        label.setForeground(list.getForeground());
                    }
                    label.setOpaque(true);
                    return label;
                }
            });

        } catch (ServiceException e) {
            e.printStackTrace();
            comboPacientes = new JComboBox<>();
        }

        // Configurar JComboBox para médicos
        MedicoService medicoService = new MedicoService();
        List<Medico> medicos;
        try {
            medicos = medicoService.getAll();
            comboMedicos = new JComboBox<>(medicos.toArray(new Medico[0]));

            comboMedicos.setRenderer(new ListCellRenderer<Medico>() {
                @Override
                public Component getListCellRendererComponent(JList<? extends Medico> list, Medico value, int index, boolean isSelected, boolean cellHasFocus) {
                    JLabel label = new JLabel(value.getNombreCompleto());
                    if (isSelected) {
                        label.setBackground(list.getSelectionBackground());
                        label.setForeground(list.getSelectionForeground());
                    } else {
                        label.setBackground(list.getBackground());
                        label.setForeground(list.getForeground());
                    }
                    label.setOpaque(true);
                    return label;
                }
            });

        } catch (ServiceException e) {
            e.printStackTrace();
            comboMedicos = new JComboBox<>();
        }
        
        
        JSeparator separator = new JSeparator();
        separator.setOrientation(SwingConstants.HORIZONTAL); //hacer que sea horizontal
        separator.setPreferredSize(new Dimension(500, 1));
        JSeparator separator1 = new JSeparator();
        separator1.setOrientation(SwingConstants.HORIZONTAL); 
        separator1.setPreferredSize(new Dimension(500, 1)); 

        this.add(separator);
        this.add(new JLabel("Seleccion de medico y paciente para crear turno"));
        this.add(separator1); 

        this.add(new JLabel("Seleccione paciente"));
        this.remove(nombreField);
        this.add(comboPacientes);

        this.add(new JLabel("Seleccione medico"));
        this.remove(fieldTres);
        this.add(comboMedicos);
        
    }

    @Override
    protected Turno createEntityFromFields() throws ServiceException {
        // Crear un objeto Turno con los valores seleccionados
        Paciente paciente = (Paciente) comboPacientes.getSelectedItem();
        Medico medico = (Medico) comboMedicos.getSelectedItem();
        String turnoId = idField.getText();
        String fechaHora = fieldCuatro.getText();

        if (paciente == null || medico == null || turnoId == null || turnoId.isBlank() || fechaHora == null || fechaHora.isBlank()) {
            throw new ServiceException("Todos los campos deben estar completos.");
        }

        LocalDateTime fechaParseada = null;
        Integer id = 0;
        try {
        	fechaParseada = LocalDateTime.parse(fechaHora);
        	id = Integer.parseInt(turnoId);
        } catch (Exception e) {
            throw new ServiceException("Error en el formato de la fecha/hora");
        }

        Turno turno = new Turno(id, medico, paciente, fechaParseada);
        return turno;
    }

    @Override
    protected String getEntityId(Turno turno) {
        return String.valueOf(turno.getId());
    }

    @Override
    protected String mensajeCreado() {
        return "Turno creado con éxito.";
    }

    @Override
    protected String mensajeActualizado() {
        return "Turno actualizado con éxito.";
    }

    @Override
    protected String mensajeBorrado() {
        return "Turno borrado con éxito.";
    }

    @Override
    protected Service<Turno> createService() {
        return new TurnoService();
    }

	@Override
	protected void mostrarPanelBusqueda() {
		panelManager.mostrarPanelBuscarTurnos();
		// TODO Auto-generated method stub
	}
}
