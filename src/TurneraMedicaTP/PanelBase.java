package TurneraMedicaTP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public abstract class PanelBase<T> extends JPanel implements ActionListener {
    protected JTable tabla;
    protected BaseTableModel<T> modelo;
    protected JScrollPane scrollPane;
    protected JButton borrarBtn;
    protected JButton botonCrear;
    protected JButton botonActualizar;
    protected JButton botonBuscar;
    protected JTextField nombreField;
    protected JTextField idField; 
    protected JTextField fieldTres;
    protected JTextField fieldCuatro; 

    protected abstract String getNombreLabelText();
    protected abstract String getIdLabelText();
    protected abstract String getFieldTresLabelText();
    protected abstract String getFieldCuatroLabelText();
    protected abstract BaseTableModel<T> createTableModel();
    protected abstract T createEntityFromFields() throws ServiceException;
    protected abstract String getEntityId(T entity);
    protected abstract String mensajeCreado();
    protected abstract String mensajeActualizado();
    protected abstract String mensajeBorrado();
    protected abstract Service<T> createService();
    
    protected JLabel nombreLbl;
    protected JLabel idLbl;
    protected JLabel fieldTresLbl;
    protected JLabel fieldCuatroLbl;

    public PanelBase() {
        super();
    }

    public void armarPanel() {
        this.setLayout(new FlowLayout());

        botonCrear = new JButton("Crear");
        botonActualizar = new JButton("Actualizar");

        JLabel nombreLbl = new JLabel(getNombreLabelText());
        JLabel idLbl = new JLabel(getIdLabelText());
        JLabel fieldTresLbl = new JLabel(getFieldTresLabelText());
        JLabel fieldCuatroLbl = new JLabel(getFieldCuatroLabelText());

        this.nombreField = new JTextField(40);
        this.idField = new JTextField(40);
        this.fieldTres = new JTextField(40);
        this.fieldCuatro = new JTextField(40);

        modelo = createTableModel();
        tabla = new JTable(modelo);

        scrollPane = new JScrollPane(tabla);

        Service<T> service = createService();
        List<T> lista = null;
		try {
			lista = service.getAll();
		} catch (ServiceException e) {
			e.printStackTrace();
		}

        borrarBtn = new JButton("Borrar seleccionado");

        botonBuscar = new JButton("Buscar");

        borrarBtn.addActionListener(this);
        botonCrear.addActionListener(this);
        botonActualizar.addActionListener(this);
        botonBuscar.addActionListener(this);

        this.add(nombreLbl);
        this.add(nombreField);
        this.add(idLbl);
        this.add(idField);
        this.add(fieldTresLbl);
        this.add(fieldTres);
        this.add(fieldCuatroLbl);
        this.add(fieldCuatro);
        this.add(botonCrear);
        this.add(botonActualizar);
        this.add(scrollPane);
        this.add(borrarBtn);
        this.add(botonBuscar);

        modelo.setContenido(lista);
        modelo.fireTableDataChanged();
    }
    
    
    public void actionPerformed(ActionEvent e) {
    	// CREAR 
    	if (e.getSource() == botonCrear) {
    		//T entity = null;
    		Service<T> service = createService();
    		try {
    			T entity = createEntityFromFields();
    			service.create(entity);
    			JOptionPane.showMessageDialog(this, mensajeCreado());
                modelo.getContenido().add(entity);
                modelo.fireTableDataChanged();
    		} catch (ServiceException e1) {
    			JOptionPane.showMessageDialog(this, e1.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
    		}
    		
    	// ACTUALIZAR
    	} else if (e.getSource() == botonActualizar) {
    		T entity = null;
			try {
				entity = createEntityFromFields();
			} catch (ServiceException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
    		Service<T> service = createService();
    		try {
    			service.update(entity);
    			JOptionPane.showMessageDialog(this, mensajeActualizado());
    			refreshTableData();
    		} catch (ServiceException e1) {
    			JOptionPane.showMessageDialog(this, e1.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
    		}
    	
    	// BUSCAR
    	}  else if (e.getSource() == botonBuscar) {
            mostrarPanelBusqueda();
            
        // BORRAR
        } else if (e.getSource() == borrarBtn) {
        	int filaSeleccionada = tabla.getSelectedRow();
            if (filaSeleccionada >= 0) {
                try {
                	T entity = modelo.getContenido().get(filaSeleccionada);
                    Service<T> service = createService();
                    String entityId = getEntityId(entity); 
					service.delete(entityId);
					JOptionPane.showMessageDialog(this, mensajeBorrado());
	                modelo.getContenido().remove(filaSeleccionada);
	                modelo.fireTableDataChanged();
				} catch (ServiceException e1) {
					JOptionPane.showMessageDialog(this, e1.getMessage() , "Error", JOptionPane.ERROR_MESSAGE);
				}
            } else {
            	JOptionPane.showMessageDialog(this, "Debe seleccionar una fila con el registro a borrar", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }		
    }
    
    protected abstract void mostrarPanelBusqueda();
    
    private void refreshTableData() {
        Service<T> service = createService();
        try {
            List<T> lista = service.getAll(); 
            modelo.setContenido(lista); 
            modelo.fireTableDataChanged();
        } catch (ServiceException e) {
            JOptionPane.showMessageDialog(this, "Hubo un error al actualizar la tabla", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
