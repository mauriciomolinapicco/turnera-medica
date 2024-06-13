package TurneraMedicaTP;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseTableModel<T> extends AbstractTableModel {
    protected List<T> contenido;

    public BaseTableModel() {
        contenido = new ArrayList<>();
    }

    public List<T> getContenido() {
        return contenido;
    }

    public void setContenido(List<T> contenido) {
        this.contenido = contenido;
    }

    @Override
    public int getRowCount() {
        return contenido.size();
    }

    @Override
    public abstract int getColumnCount();

    @Override
    public abstract Object getValueAt(int rowIndex, int columnIndex);
}
