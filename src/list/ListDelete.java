package list;

import h2.H2Handler;
import list.data.Record;
import main.MainMenu;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

/** Removes a punishment from the punishments table
 * @author MidnightReaver
 * @version 1.0
 * @since 1.0
 */
public class ListDelete extends JFrame implements ActionListener, ItemListener {

    JComboBox cbDeleteLevels;
    JComboBox cbDeletePunishments;

    ArrayList<Record> records;

    JButton btnDelete;
    JButton btnBack;

    DefaultListCellRenderer listRenderer;

    public ListDelete() {

        ImageIcon icon = new ImageIcon("trisk.png");


        H2Handler h2 = new H2Handler();
        int max_level = h2.getSavedLevel();

        cbDeleteLevels = new JComboBox<>();

        for (int i = 1; i <= max_level; i++) {
            cbDeleteLevels.addItem(String.valueOf(i));
        }

        cbDeleteLevels.setBounds(250, 200, 300, 25);
        cbDeleteLevels.addItemListener(this);
        listRenderer = new DefaultListCellRenderer();
        listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER); // center-align items
        cbDeleteLevels.setRenderer(listRenderer);

        cbDeletePunishments = new JComboBox<>();
        cbDeletePunishments.setBounds(250, 230, 300, 25);

        // Initialize list with Level 1
        records = h2.getRecordsByLevel(1);

        for (Record record : records) {
            String text = record.getText();
            cbDeletePunishments.addItem(String.valueOf(text));
        }

        btnDelete = new JButton("Delete");
        btnDelete.setBounds(300, 260, 200, 50);
        btnDelete.addActionListener(this);

        btnBack = new JButton("Back");
        btnBack.setBounds(300, 320, 200, 50);
        btnBack.addActionListener(this);

        // frame window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setResizable(false);
        this.setVisible(true);
        this.setLayout(null);
        this.add(cbDeleteLevels);
        this.add(cbDeletePunishments);
        this.add(btnDelete);
        this.add(btnBack);
        this.setTitle("Random Punishment Generator");
        this.setIconImage(icon.getImage());
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnDelete) {

            H2Handler h2 = new H2Handler();
            int index = cbDeletePunishments.getSelectedIndex();
            int level = Integer.parseInt(String.valueOf(cbDeleteLevels.getSelectedItem()));
            h2.deleteRecord(records.get(index).getId());
            new ListDelete();
            this.dispose();
        } else if (e.getSource() == btnBack) {
            new MainMenu();
            this.dispose();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

        if (e.getStateChange() == ItemEvent.SELECTED) {
            cbDeletePunishments.removeAllItems();
            int level = Integer.parseInt(String.valueOf(cbDeleteLevels.getSelectedItem()));
            H2Handler h2 = new H2Handler();
            ArrayList<Record> tmpRecords = h2.getRecordsByLevel(level);

            for (Record record : tmpRecords) {
                String text = record.getText();
                cbDeletePunishments.addItem(String.valueOf(text));
            }
        }
    }
}
