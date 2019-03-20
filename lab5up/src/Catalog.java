import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.util.*;

public class Catalog extends JFrame {

    public static MobileN addResult = null;
    public static String path = null;
    JTable infoPanel = new JTable();
    JTree notebooksTree = new JTree();
    myTableModel tableModel = null;
    myTreeModel treeModel = null;
    public Catalog() throws HeadlessException {
        JButton addButton = new JButton("Добавить устройство в каталог");
        addButton.addActionListener(e -> SwingUtilities.invokeLater(() -> openAddDialog()));

        JButton removeButton = new JButton("Удалить устройство из каталога");
        removeButton.addActionListener(e -> removeItem());

        tableModel = new myTableModel();
        infoPanel = new JTable(tableModel);
        treeModel = new myTreeModel(new treeNode("Каталог устройств"));
        notebooksTree = new JTree(treeModel);
        notebooksTree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                treeNode node = (treeNode) notebooksTree.getLastSelectedPathComponent();
                if (node == null) {
                    return;
                }
                ArrayList<MobileN> array = node.getAllNodes();
                tableModel = new myTableModel(array);
                infoPanel.setModel(tableModel);
            }
        });



        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true, new JScrollPane(notebooksTree), new JScrollPane(infoPanel));
        splitPane.setDividerLocation(300);


        getContentPane().add(splitPane);
        getContentPane().add("North", addButton);
        getContentPane().add("South", removeButton);
        setBounds(100, 100, 600, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        Catalog mainClass = new Catalog();
        mainClass.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainClass.setVisible(true);
    }

    public void openAddDialog() {
        Add addForm = new Add(this);
        addForm.setVisible(true);
    }

    public void addNewItem() {
        treeNode temp, where, insert, root = treeModel.getRoot();

        try {
            insert = new treeNode(addResult.serial, addResult);
            if ((where = findNode(root, addResult.mod)) != null) {
                treeModel.insertNodeInto(insert, where, where.getChildCount(), false);
            } else if (findNode(root, addResult.manufacturer) != null) {
                treeModel.insertNodeInto(new treeNode(addResult.mod), (temp = findNode(root, addResult.manufacturer)), temp.getChildCount(), false);
                where = findNode(root, addResult.mod);
                treeModel.insertNodeInto(insert, where, where.getChildCount(), false);
            } else if (findNode(root, addResult.type) != null) {
                treeModel.insertNodeInto(new treeNode(addResult.manufacturer), (temp = findNode(root, addResult.type)), temp.getChildCount(), false);
                treeModel.insertNodeInto(new treeNode(addResult.mod), (temp = findNode(root, addResult.manufacturer)), temp.getChildCount(), false);
                where = findNode(root, addResult.mod);
                treeModel.insertNodeInto(insert, where, where.getChildCount(), false);
            } else {
                treeModel.insertNodeInto(new treeNode(addResult.type), root, root.getChildCount(), false);
                treeModel.insertNodeInto(new treeNode(addResult.manufacturer), (temp = findNode(root, addResult.type)), temp.getChildCount(), false);
                treeModel.insertNodeInto(new treeNode(addResult.mod), (temp = findNode(root, addResult.manufacturer)), temp.getChildCount(), false);
                where = findNode(root, addResult.mod);
                treeModel.insertNodeInto(insert, where, where.getChildCount(), false);
            }
        } catch (Exception e) {
            path = null;
            addResult = null;
            return;
        }

        path = null;
        addResult = null;
    }

    public void removeItem() {
        TreePath currentSelection = notebooksTree.getSelectionPath();
        if (currentSelection != null) {
            treeNode currentNode = (treeNode) (currentSelection.getLastPathComponent());
            treeNode parent = (treeNode) (currentNode.getParent());
            if (parent != null) {
                treeModel.removeNodeFromParent(currentNode);
                parent.deleteNode(currentNode);
                ArrayList<MobileN> array = parent.getAllNodes();
                tableModel = new myTableModel(array);
                infoPanel.setModel(tableModel);
            }
        }
    }

    public treeNode findNode(treeNode root, String s) {
        Enumeration<treeNode> e = root.depthFirstEnumeration();
        while (e.hasMoreElements()) {
            treeNode node = e.nextElement();
            if (node.toString().equalsIgnoreCase(s)) {
                return node;
            }
        }
        return null;
    }
}

class myTreeModel extends DefaultTreeModel {

    private treeNode root;

    public myTreeModel(treeNode r) {
        super(r);
        root = r;
    }

    @Override
    public treeNode getRoot() {
        return root;
    }

    public void insertNodeInto(treeNode child, treeNode parent, int i, boolean flag) {
        this.insertNodeInto(child, parent, i);
        parent.addNode(child);
    }
}

class myTableModel implements TableModel {

    static final String[] columnNames = new String[]{"Серийный номер", "Тип устройства", "Производитель", "Модель"};
    static final Class[] columnTypes = new Class[]{String.class, String.class, String.class, String.class, String.class, Integer.class, Integer.class};
    private Set<TableModelListener> listeners = new HashSet<TableModelListener>();
    private ArrayList<MobileN> infoNodes;

    public myTableModel() {
        infoNodes = new ArrayList<MobileN>();
    }

    public myTableModel(ArrayList<MobileN> al) {
        this.infoNodes = al;
    }

    public void setInfoArray(ArrayList<MobileN> al) {
        infoNodes = al;
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return infoNodes.size();
    }

    public Class getColumnClass(int columnIndex) {
        return columnTypes[columnIndex];
    }

    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        MobileN nb = infoNodes.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return nb.getserial();
            case 1:
                return nb.getType();
            case 2:
                return nb.getmanufacturer();
            case 3:
                return nb.getMod();
        }
        return "";
    }

    @Override
    public void addTableModelListener(TableModelListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeTableModelListener(TableModelListener listener) {
        listeners.remove(listener);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
    }
}

class treeNode extends DefaultMutableTreeNode {
    String name;
    MobileN ifNode = null;
    ArrayList<treeNode> nodes;
    boolean isThisTheEnd = false;

    public treeNode() {
        name = "-";
        nodes = new ArrayList<treeNode>();
        ifNode = null;
        isThisTheEnd = false;
    }

    public treeNode(String str) {
        name = str;
        nodes = new ArrayList<treeNode>();
        ifNode = null;
        isThisTheEnd = false;
    }

    public treeNode(String str, MobileN nbNode) {
        name = str;
        nodes = new ArrayList<treeNode>();
        ifNode = nbNode;
        isThisTheEnd = true;
    }

    public ArrayList<MobileN> getAllNodes() {
        ArrayList<MobileN> ret = new ArrayList<MobileN>();
        Deque<treeNode> deque = new ArrayDeque<treeNode>();

        treeNode temp;
        deque.push(this);
        while (!deque.isEmpty()) {
            temp = deque.removeFirst();
            if (temp.isThisTheEnd) {
                ret.add(temp.getIfNode());
            } else {
                for (int i = 0; i < temp.nodes.size(); i++) {
                    deque.push(temp.nodes.get(i));
                }
            }
        }
        return ret;
    }

    public void addNode(treeNode tn) {
        nodes.add(tn);
    }

    public void deleteNode(treeNode tn) {
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).toString().compareToIgnoreCase(tn.toString()) == 0) {
                nodes.remove(i);
            }
        }
    }

    public MobileN getIfNode() {
        return ifNode;
    }

    public ArrayList<treeNode> getNodes() {
        return nodes;
    }

    public String toString() {
        return name;
    }
}

class MobileN {
    String serial, type, manufacturer, mod;


    MobileN() {
    }

    MobileN(String name, String manufacturer, String mod, String serial) {
        this.type = name;
        this.manufacturer = manufacturer;
        this.mod = mod;
        this.serial = serial;
    }


    public String getserial() { return serial; }

    public String getType() {
        return type;
    }

    public String getMod() {
        return mod;
    }

    public String getmanufacturer() {
        return manufacturer;
    }
}