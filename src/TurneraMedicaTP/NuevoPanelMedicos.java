package TurneraMedicaTP;

import javax.swing.JOptionPane;

public class NuevoPanelMedicos extends PanelBase<Medico> {
	private PanelManager panelManager;


    public NuevoPanelMedicos(PanelManager m) {
        super();
        this.panelManager = m;
    }

    @Override
    protected String getNombreLabelText() {
        return "Ingrese el nombre del medico:";
    }

    @Override
    protected String getIdLabelText() {
        return "Ingrese la matricula:";
    }
    
    @Override
	protected String getFieldTresLabelText() {
		return "Ingrese la especialidad";
	}

	@Override
	protected String getFieldCuatroLabelText() {
		return "Ingrese el precio de la consulta";
	}

    @Override
    protected BaseTableModel<Medico> createTableModel() {
        return new MedicoTableModel();
    }

    @Override
    protected MedicoDAO createDAO() {
        return new MedicoDAOMySQLImpl();
    }

    @Override
    protected Medico createEntityFromFields() {
        String nombre = nombreField.getText();
        String matricula = idField.getText();
        String especialidad = fieldTres.getText();        
        double precioConsulta = 0.0;
		try {
			precioConsulta = Double.parseDouble(fieldCuatro.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(NuevoPanelMedicos.this, "Ingrese un número válido para el precio", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return new Medico(nombre, matricula, especialidad, precioConsulta);
        
    }

	@Override
	protected String getEntityId(Medico entity) {
		return entity.getMatricula();
	}

	@Override
	protected void mostrarPanelBusqueda() {
		panelManager.mostrarPanelBusquedaMedicos();
	}

	
}