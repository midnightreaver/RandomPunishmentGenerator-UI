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
 *  Step 1 of delete punishment process
 * @author MidnightReaver
 * @version 1.2
 * @since 1.0
 */
public class ListDelete extends JFrame implements ActionListener {

    JComboBox<Object> cbDeleteLevels;
    JComboBox<Object> cbDeletePunishments;

    ArrayList<Record> records;

    JButton btnContinue;
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
        listRenderer = new DefaultListCellRenderer();
        listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER); // center-align items
        cbDeleteLevels.setRenderer(listRenderer);
        
        btnContinue = new JButton("Continue");
        btnContinue.setBounds(300, 260, 200, 50);
        btnContinue.addActionListener(this);

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
        this.add(btnContinue);
        this.add(btnBack);
        this.setTitle("Random Punishment Generator");
        this.setIconImage(icon.getImage());
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	
    	if (e.getSource() == btnBack) {
    		this.dispose();
    		new MainMenu();
    	} else if (e.getSource() == btnContinue) {
    		this.dispose();
    		new ListDeleteSelect(Integer.parseInt(String.valueOf(cbDeleteLevels.getSelectedItem())));
    	}
    }
}
