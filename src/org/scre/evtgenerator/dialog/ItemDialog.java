/**
 * Created on 02-Sep-2005
 * @author FTYK9
 * @version $Revision: 1.3 $
 */
package org.scre.evtgenerator.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ItemEvent;
import java.io.IOException;
import java.text.Collator;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import org.activemath.content.Book;
import org.activemath.content.ContentManager;
import org.activemath.content.Page;
import org.activemath.content.items.Item;
import org.activemath.content.items.Metadata;
import org.activemath.xlm.model.XlmModelImpl;
import org.scre.evtgenerator.utils.BrowserLauncher;
import org.scre.evtgenerator.utils.LeAMContent;
import org.scre.evtgenerator.utils.TableSorter;

import com.l2fprod.common.swing.BaseDialog;


/**
 * @author vanlabeke
 * @version $Revision: 1.3 $
 */
public class ItemDialog extends BaseDialog
{
    /**
     * Simple String-to-String comparator used to sort itmes in the table
     */
    private static Comparator OMDOCTYPE_COMP = new Comparator()
    {
        public int compare(Object o1, Object o2)
        {
            String str1 = o1.toString();
            String str2 = o2.toString();
            Collator collator = Collator.getInstance();
            int result = collator.compare(str1, str2);

            return result;
        }
    };

    private JPanel      jBookPanel = null; //  @jve:decl-index=0:visual-constraint="36,33"
    private JComboBox   jBookCombo = null;
    private JScrollPane jScrollPane = null; //  @jve:decl-index=0:visual-constraint="35,112"
    private JTable      jTable = null; //  @jve:decl-index=0:visual-constraint="599,10"
    private JComboBox   jFilterCombo = null;
    private JLabel      jBookLabel = null;
    private JLabel      jFilterLabel = null;

    private DefaultTableModel tModel;
    private JLabel jCountLabel = null;

    /**
     * Default constructor
     * @throws HeadlessException
     */
    public ItemDialog() throws HeadlessException
    {
        super();

        // TODO Auto-generated constructor stub
        initialize();
    }

    /**
     * @param collection    The name of the OMDOC collection to display
     */
    private void initListFromCollection(String collection)
    {
        LeAMContent.types.add("<none>");

        Object[] toto = 
                        {
                            "LeAMContent ID", "Type", "Difficulty",
                            "Conmpetencies", "Comp. Level", "Topics"
                        };

        tModel = new DefaultTableModel()
            {
                public boolean isCellEditable(int rowIndex, int mColIndex)
                {
                    return false;
                }
            };
        tModel.setColumnIdentifiers(toto);

        for (int i = 0; i < LeAMContent.items.size(); i++)
        {
            if (i == 0)
            {
                System.out.println("*** Get previously loaded content");
            }

            Vector vec = (Vector) LeAMContent.items.get(i);
            tModel.addRow(vec);
        }

        TableSorter sorter = new TableSorter(tModel); //ADDED THIS
        int nb = tModel.getRowCount();
        jCountLabel.setText("(" + nb + "items)");
        jTable.setModel(sorter);
        sorter.setTableHeader(jTable.getTableHeader()); //ADDED THIS            

        TreeSet sortedset = new TreeSet(ItemDialog.OMDOCTYPE_COMP);
        sortedset.addAll(LeAMContent.types);
        getJFilterCombo().setModel(new DefaultComboBoxModel(sortedset.toArray()));
    }

    /**
     * @param book
     * @deprecated Use the initListFromCollection instead
     */
    private void initListFromBook(Book book)
    {
        XlmModelImpl impl = XlmModelImpl.getInstance();
        ContentManager contentManager = ContentManager.getInstance();

        Object[] toto = 
                        {
                            "LeAMContent ID", "Difficulty", "Conmpetencies",
                            "Comp. Level"
                        };

        DefaultTableModel tmodel = new DefaultTableModel();
        tmodel.setColumnIdentifiers(toto);

        List pages = book.getPages();

        for (int i = 0; i < pages.size(); i++)
        {
            Page page = (Page) pages.get(i);
            List items = page.getItemIds();

            for (int j = 0; j < items.size(); j++)
            {
                Vector vec = new Vector();
                Object item = items.get(j);
                Item content = contentManager.getContentItem(item.toString());
                Set topics = impl.getMapDomain().getTopContentIds(item.toString());

                //String temp = item.toString() + " - " + topics.toString();
                vec.add(item);

                Metadata meta = null; //;

                if ((content != null) &&
                        ((meta = content.getMetadata()) != null))
                {
                    vec.add(meta.getDifficulty());
                    vec.add(meta.getCompetencies());
                    vec.add(meta.getCompetencyLevel());
                }
                else
                {
                    vec.add("");
                    vec.add("");
                    vec.add("");
                }

                vec.add(topics);
                tmodel.addRow(vec);
            }
        }

        TableSorter sorter = new TableSorter(tmodel); //ADDED THIS
        jTable.setModel(sorter);
        sorter.setTableHeader(jTable.getTableHeader()); //ADDED THIS            
    }

    /**
     * This method initializes this
     *
     * @return void
     */
    private void initialize()
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        this.setSize(500, 380);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(getJScrollPane(), java.awt.BorderLayout.CENTER);
        //getContentPane().add(getJBookPanel(), java.awt.BorderLayout.NORTH);
        getBanner().add(getJBookPanel(), java.awt.BorderLayout.CENTER);
        setDialogMode(BaseDialog.OK_CANCEL_DIALOG);
        getBanner().setVisible(true);
        setModal(true);
        this.setTitle("Select an item ID");

        //ContentManager contentManager = ContentManager.getInstance();
        //List books = contentManager.getRecBooks();
        //getJBookComboBox().setModel(new DefaultComboBoxModel(books.toArray()));
        //initListFromBook((Book)getJBookComboBox().getSelectedItem());
        getJBookComboBox().setModel(new DefaultComboBoxModel(LeAMContent.collections.toArray()));
        initListFromCollection((String) getJBookComboBox().getSelectedItem());
    }

    /**
     * This method initializes jPanel
     *
     * @return javax.swing.JPanel
     */
    private JPanel getJBookPanel()
    {
        if (jBookPanel == null)
        {
            GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.gridx = 2;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
            gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.gridy = 1;
            jCountLabel = new JLabel();
            jCountLabel.setText("(0 items)");

            GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
            gridBagConstraints2.gridx = 0;
            gridBagConstraints2.anchor = java.awt.GridBagConstraints.EAST;
            gridBagConstraints2.insets = new java.awt.Insets(0, 5, 0, 5);
            gridBagConstraints2.gridy = 1;
            jFilterLabel = new JLabel();
            jFilterLabel.setText("<HTML>Filter <B>Type</B> by:</HTML>");

            GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
            gridBagConstraints1.fill = java.awt.GridBagConstraints.NONE;
            gridBagConstraints1.gridy = 1;
            gridBagConstraints1.weightx = 0.0;
            gridBagConstraints1.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints1.insets = new java.awt.Insets(0, 0, 0, 0);
            gridBagConstraints1.gridx = 1;

            GridBagConstraints gridBagConstraints16 = new GridBagConstraints();
            gridBagConstraints16.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints16.gridx = 1;
            gridBagConstraints16.gridy = 0;
            gridBagConstraints16.weightx = 1.0;
            gridBagConstraints16.gridwidth = 2;
            gridBagConstraints16.insets = new java.awt.Insets(5, 0, 5, 5);

            GridBagConstraints gridBagConstraints15 = new GridBagConstraints();
            gridBagConstraints15.gridx = 0;
            gridBagConstraints15.gridy = 0;
            gridBagConstraints15.anchor = java.awt.GridBagConstraints.EAST;
            gridBagConstraints15.insets = new java.awt.Insets(0, 5, 0, 5);
            jBookLabel = new JLabel();
            jBookLabel.setText("Collection:");
            jBookPanel = new JPanel();
            jBookPanel.setLayout(new GridBagLayout());
            jBookPanel.setBackground(Color.WHITE);
            jBookPanel.setSize(479, 74);
            jBookPanel.add(jBookLabel, gridBagConstraints15);
            jBookPanel.add(getJBookComboBox(), gridBagConstraints16);
            jBookPanel.add(getJFilterCombo(), gridBagConstraints1);
            jBookPanel.add(jFilterLabel, gridBagConstraints2);
            jBookPanel.add(jCountLabel, gridBagConstraints);
        }

        return jBookPanel;
    }

    /**
     * This method initializes jComboBox
     *
     * @return javax.swing.JComboBox
     */
    private JComboBox getJBookComboBox()
    {
        if (jBookCombo == null)
        {
            jBookCombo = new JComboBox();
            jBookCombo.setRenderer(new DefaultListCellRenderer()
            {
                public Component getListCellRendererComponent(JList list,
                                                              Object value, // value to display
                                                              int index, // cell index
                                                              boolean iss, // is the cell selected
                                                              boolean chf) // the list and the cell have the focus
                {
                    super.getListCellRendererComponent(list,value,index,iss,chf);
                    String text = null;
                    if (value instanceof Book)
                    {
                        Book amevt = (Book) value;
                        text = amevt.getId();
                    }
                    else
                    {
                        text = value.toString();
                    }
                    setText(text);
                    return this;
                }
            });

            jBookCombo.addItemListener(new java.awt.event.ItemListener()
            {
                public void itemStateChanged(java.awt.event.ItemEvent e)
                {
                    if (e.getStateChange() == ItemEvent.SELECTED)
                    {
                        // Item was just selected
                        //initListFromBook((Book) e.getItem());
                        initListFromCollection((String) e.getItem());
                    }
                    else if (e.getStateChange() == ItemEvent.DESELECTED)
                    {
                        // Item is no longer selected
                    }
                }
            });
        }

        return jBookCombo;
    }

    /**
     * @return Returns the selected item, or null if none.
     */
    public String getSelectedItem()
    {
        int rowIndex = getJTable().getSelectedRow();
        if (rowIndex == -1)
        {
            return null;
        }
        String value = (String) getJTable().getValueAt(rowIndex, 0);
        String selItem = "mbase://LeAM_calculus/" + value;
        return selItem;
    }

    /**
     * This method initializes jScrollPane1
     *
     * @return javax.swing.JScrollPane
     */
    private JScrollPane getJScrollPane()
    {
        if (jScrollPane == null)
        {
            jScrollPane = new JScrollPane();
            jScrollPane.setViewportView(getJTable());
            jScrollPane.setSize(new java.awt.Dimension(481, 223));
        }

        return jScrollPane;
    }

    /**
     * This method initializes jTable
     *
     * @return javax.swing.JTable
     */
    private JTable getJTable()
    {
        if (jTable == null)
        {
            TableSorter sorter = new TableSorter(new DefaultTableModel()); //ADDED THIS
            jTable = new JTable();
            jTable.setModel(sorter);
            jTable.setSize(new java.awt.Dimension(74, 106));
            sorter.setTableHeader(jTable.getTableHeader()); //ADDED THIS            
            jTable.setColumnSelectionAllowed(false);
            jTable.setRowSelectionAllowed(true);
            jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            jTable.addMouseListener(new java.awt.event.MouseAdapter()
            {
                // double-click on an item opens it in the browser
                public void mouseClicked(java.awt.event.MouseEvent e)
                {
                    if (e.getClickCount() != 2) return;

                    String itemId = getSelectedItem();
                    if (itemId == null) return;

                    String URL =
                            "http://localhost:8080/ActiveMath2/search/show.cmd?id=" +
                            itemId;

                    try
                    {
                        BrowserLauncher.openURL(URL);
                    }
                    catch (IOException e1)
                    {
                        e1.printStackTrace();
                    }
                }
            });
        }
        return jTable;
    }

    private void filterItems(String filter)
    {
        if (filter==null)
        {
            TableSorter sorter = new TableSorter(tModel); //ADDED THIS
            int nb = tModel.getRowCount();
            jCountLabel.setText("(" + nb + "items)");
            jTable.setModel(sorter);
            sorter.setTableHeader(jTable.getTableHeader()); //ADDED THIS            
            return;
        }
        Object[] toto = 
        {
            "LeAMContent ID", "Type",
            "Difficulty",
            "Conmpetencies",
            "Comp. Level", "Topics"
        };

        DefaultTableModel filterModel = new DefaultTableModel()
            {
                public boolean isCellEditable(int rowIndex,int mColIndex)
                {
                    return false;
                }
            };
        
        filterModel.setColumnIdentifiers(toto);
        Vector vec = tModel.getDataVector();
        int n = vec.size();
        
        for (int i = 0; i < n; i++)
        {
            Vector rr = (Vector) vec.get(i);
            String type = (String) rr.get(1);
        
            if (type.startsWith(filter))
            {
                filterModel.addRow(rr);
                System.out.println(rr);
            }
        }
        
        int nb = filterModel.getRowCount();
        jCountLabel.setText("(" + nb + " items)");
        
        TableSorter sorter = new TableSorter(filterModel); //ADDED THIS
        jTable.setModel(sorter);
        sorter.setTableHeader(jTable.getTableHeader()); //ADDED THIS        
    }
    
    /**
     * This method initializes jComboBox1
     *
     * @return javax.swing.JComboBox
     */
    private JComboBox getJFilterCombo()
    {
        if (jFilterCombo == null)
        {
            jFilterCombo = new JComboBox();
            jFilterCombo.addItemListener(new java.awt.event.ItemListener()
            {
                //This method is called only if a new item has been selected.
                public void itemStateChanged(ItemEvent evt)
                {
                    JComboBox cb = (JComboBox) evt.getSource();
                    if (evt.getStateChange() == ItemEvent.SELECTED)
                    {
                        // Get the affected item
                        Object item = evt.getItem();
                        System.out.println("selected: " + item);

                        // Item was just selected
                        if (cb.getSelectedIndex() == 0)
                            filterItems(null);
                        else
                            filterItems(item.toString());
                    }
                    else if (evt.getStateChange() == ItemEvent.DESELECTED)
                    {
                        // Item is no longer selected
                    }
                }
            });
        }

        return jFilterCombo;
    }
} //  @jve:decl-index=0:visual-constraint="25,3"
