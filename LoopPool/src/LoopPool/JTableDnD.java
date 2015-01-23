/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoopPool;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DragSource;
import java.util.ArrayList;
import java.util.List;

import javax.activation.ActivationDataFlavor;
import javax.activation.DataHandler;
import javax.swing.DropMode;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.TransferHandler;
import javax.swing.table.AbstractTableModel;

public class JTableDnD extends JFrame {

    private JTable tableA;
    private JTable tableB;

    public JTableDnD() {
        // *** Create First Table ***

        List<Object[]> dataA = new ArrayList<Object[]>();
        dataA.add(new Object[]{"A1", "A1"});
        dataA.add(new Object[]{"A2", "A2"});
        dataA.add(new Object[]{"A3", "A3"});

        List<String> columnsA = new ArrayList<String>();
        columnsA.add("Column 1");
        columnsA.add("Column 2");

        tableA = new JTable(new TableModel(columnsA, dataA));

        tableA.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableA.setDragEnabled(true);
        tableA.setDropMode(DropMode.INSERT_ROWS);
        tableA.setFillsViewportHeight(true);
        tableA.setTransferHandler(new TableTransferHandler(tableA));

        JScrollPane scrollPaneA = new JScrollPane(tableA);

        // *** Create Second Table ***
        List<Object[]> dataB = new ArrayList<Object[]>();
        dataB.add(new Object[]{"B1", "B1"});
        dataB.add(new Object[]{"B2", "B2"});
        dataB.add(new Object[]{"B3", "B3"});

        List<String> columnsB = new ArrayList<String>();
        columnsB.add("Column 1");
        columnsB.add("Column 2");

        tableB = new JTable(new TableModel(columnsB, dataB));

        tableB.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableB.setDragEnabled(true);
        tableB.setDropMode(DropMode.INSERT_ROWS);
        tableB.setFillsViewportHeight(true);
        tableB.setTransferHandler(new TableTransferHandler(tableB));

        JScrollPane scrollPaneB = new JScrollPane(tableB);

        // *** Add ScrollPanes to Panel ***
        this.getContentPane().setLayout(new FlowLayout());

        add(scrollPaneA);

        JPanel emptyPanel = new JPanel();
        emptyPanel.setPreferredSize(new Dimension(100, 200));
        add(emptyPanel);

        add(scrollPaneB);

    }  // end JTableDnD constructor

    private static void createAndShowGUI() {
        JFrame frame = new JTableDnD();
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}

interface Reorderable {

    public void reorder(int from, int to);
}

class TableModel extends AbstractTableModel implements Reorderable {

    private List<String> columnNames;
    private List<Object[]> data;

    public TableModel(List<String> columnNames, List<Object[]> data) {
        super();
        this.columnNames = columnNames;
        this.data = data;
    }

    @Override
    public void reorder(int from, int to) {
        if (from < to) {
            to--;
        }

        Object[] row = data.remove(from);

        data.add(to, row);

        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.size();
    }

    @Override
    public String getColumnName(int column) {
        return columnNames.get(column);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex)[columnIndex];
    }

}  // end TableModel

class TableTransferHandler extends TransferHandler {

    private final DataFlavor localObjectFlavor = new ActivationDataFlavor(Integer.class, DataFlavor.javaJVMLocalObjectMimeType, "Integer Row Index");

    private JTable table = null;

    public TableTransferHandler(JTable table) {
        this.table = table;
    }

    @Override
    protected Transferable createTransferable(JComponent component) {
        return new DataHandler(new Integer(table.getSelectedRow()), localObjectFlavor.getMimeType());
    }

    @Override
    public boolean canImport(TransferHandler.TransferSupport support) {
        boolean b = support.getComponent() == table
                && support.isDrop()
                && support.isDataFlavorSupported(localObjectFlavor);

        table.setCursor(b ? DragSource.DefaultMoveDrop : DragSource.DefaultMoveNoDrop);

        return b;
    }

    @Override
    public int getSourceActions(JComponent component) {
        return TransferHandler.COPY_OR_MOVE;
    }

    @Override
    public boolean importData(TransferHandler.TransferSupport support) {
        JTable target = (JTable) support.getComponent();

        JTable.DropLocation dropLocation = (JTable.DropLocation) support.getDropLocation();

        int index = dropLocation.getRow();

        int max = table.getModel().getRowCount();

        if (index < 0 || index > max) {
            index = max;
        }

        target.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

        try {
            Integer rowFrom = (Integer) support.getTransferable().getTransferData(localObjectFlavor);

            if (rowFrom != -1 && rowFrom != index) {
                ((Reorderable) table.getModel()).reorder(rowFrom, index);

                if (index > rowFrom) {
                    index--;
                }

                target.getSelectionModel().addSelectionInterval(index, index);

                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    protected void exportDone(JComponent component, Transferable transferable, int action) {
        if (action == TransferHandler.MOVE) {
            table.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
    }
}  // end TableTransferHandler

//http://stackoverflow.com/questions/21659357/how-do-you-disallow-drag-and-drop-between-two-jtables