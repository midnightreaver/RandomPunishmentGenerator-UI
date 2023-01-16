package list;

import h2.H2Handler;
import main.MainMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Adds a punishment to the punishments table
 * @author MidnightReaver
 * @version 1.0
 * @since 1.0
 */
public class ListAdd extends JFrame implements ActionListener {

    private boolean entryAdded;

    JComboBox cbAdd;
    JTextArea taAdd;

    DefaultListCellRenderer listRenderer;

    JButton btnAdd;
    JButton btnBack;

    // Dialog box
    JDialog dlgNotice;
    JButton btnNotice;

    public ListAdd() {

        ImageIcon icon = new ImageIcon("trisk.png");

        entryAdded = false;

        String[] levels = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        cbAdd = new JComboBox<>(levels);
        cbAdd.setBounds(250, 200, 300, 25);
        listRenderer = new DefaultListCellRenderer();
        listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER); // center-align items
        cbAdd.setRenderer(listRenderer);

        taAdd = new JTextArea();
        taAdd.setBounds(250, 230, 300, 100);
        taAdd.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));

        btnAdd = new JButton("Add");
        btnAdd.setBounds(300, 340, 200, 50);
        btnAdd.addActionListener(this);

        btnBack = new JButton("Back");
        btnBack.setBounds(300, 400, 200, 50);
        btnBack.addActionListener(this);

        // frame window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setResizable(false);
        this.setVisible(true);
        this.setLayout(null);
        this.add(cbAdd);
        this.add(taAdd);
        this.add(btnAdd);
        this.add(btnBack);
        this.setTitle("Random Punishment Generator");
        this.setIconImage(icon.getImage());
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnAdd) {

            String data = taAdd.getText().trim();
            if (!data.equals("")) { // if not empty
                taAdd.setEditable(true);
                String lvl = String.valueOf(cbAdd.getSelectedItem());
                int level = Integer.parseInt(lvl);
                String entry = taAdd.getText();

                H2Handler h2 = new H2Handler();
                h2.addRecord(level, entry);

                taAdd.selectAll();
                taAdd.replaceSelection("");

                taAdd.setEditable(false);
            } else {
                dlgNotice = new JDialog(this, "Notice");
                JLabel lblNotice = new JLabel("Entry cannot be blank!");
                btnNotice = new JButton("OK");
                btnNotice.addActionListener(this);
                JPanel noticePanel = new JPanel();
                noticePanel.add(lblNotice);
                noticePanel.add(btnNotice);
                dlgNotice.add(noticePanel);
                dlgNotice.setSize(200, 200);
                dlgNotice.setVisible(true);
                dlgNotice.setLocationRelativeTo(null);
            }
        } else if (e.getSource() == btnBack) {
            new MainMenu();
            this.dispose();
        }
    }
}
