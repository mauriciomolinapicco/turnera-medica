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
    protected abstract DAO<T> createDAO();
    protected abstract T createEntityFromFields();
    protected abstract String getEntityId(T entity);
    protected abstract boolean entityExists(String id);
    protected abstract String mensajeCreado();
    protected abstract String mensajeActualizado();
    protected abstract String mensajeBorrado();
    protected abstract String mensajeYaExiste();
    protected abstract String mensajeNoExiste();

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

        DAO<T> dao = createDAO();
        List<T> lista = null;
		try {
			lista = dao.getAll();
		} catch (Exception e) {
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
        if (e.getSource() == botonCrear || e.getSource() == botonActualizar) {
            T entity = createEntityFromFields();
            DAO<T> dao = createDAO();
            if (e.getSource() == botonCrear) {
            	if (entityExists(idField.getText())) {
            		JOptionPane.showMessageDialog(this, mensajeYaExiste(), "Error",JOptionPane.ERROR_MESSAGE);
            	} else {
            		try {
    					dao.create(entity);
    					JOptionPane.showMessageDialog(this, mensajeCreado());
    	                modelo.getContenido().add(entity);
    	                modelo.fireTableDataChanged();
    				} catch (Exception e1) {
    					JOptionPane.showMessageDialog(this, "Hubo un error a la hora de insertar a la base de datos","Error", JOptionPane.ERROR_MESSAGE);
    					e1.printStackTrace();
    				}
            	}
                
                
            } else {
            	if (!entityExists(idField.getText())) {
            		JOptionPane.showMessageDialog(this, mensajeNoExiste(), "Error",JOptionPane.ERROR_MESSAGE);
            	} else {
            		try {
     					dao.update(entity);
     					JOptionPane.showMessageDialog(this, mensajeActualizado());
     	                refreshTableData();
     				} catch (Exception e1) {
     					JOptionPane.showMessageDialog(this, "Hubo un error a la hora de actualizar de la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
     				}
            	}
               
                
            }
        } else if (e.getSource() == borrarBtn) {
            int filaSeleccionada = tabla.getSelectedRow();
            if (filaSeleccionada >= 0) {
                try {
                    T entity = modelo.getContenido().get(filaSeleccionada);
                    DAO<T> dao = createDAO();
                    String entityId = getEntityId(entity); 
					dao.delete(entityId);
					JOptionPane.showMessageDialog(this, mensajeBorrado());
	                modelo.getContenido().remove(filaSeleccionada);
	                modelo.fireTableDataChanged();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(this, "Hubo un error a la hora de borrar de la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
                
            } else {
            	JOptionPane.showMessageDialog(this, "Debe seleccionar una fila con el registro a borrar", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == botonBuscar) {
            mostrarPanelBusqueda();
        }
    }
    
    protected abstract void mostrarPanelBusqueda();
    
    private void refreshTableData() {
        DAO<T> dao = createDAO();
        try {
            List<T> lista = dao.getAll(); 
            modelo.setContenido(lista); 
            modelo.fireTableDataChanged();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Hubo un error al actualizar la tabla", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
