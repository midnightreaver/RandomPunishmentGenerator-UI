package list;

import h2.H2Handler;
import list.data.Record;
import main.MainMenu;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Randomly selects a punishment based on a specified level
 * @author MidnightReaver
 * @version 1.0
 * @since 1.0
 */
public class SelectRandom extends JFrame implements ActionListener {

    JLabel lblRandom;
    JComboBox cbRandom;
    JButton btnRandom;
    JButton btnMainMenu;

    JLabel lblSelectedIs;
    JTextArea taSelectedRandom;

    public SelectRandom() {
        ImageIcon icon = new ImageIcon("trisk.png");

        lblRandom = new JLabel("Punishment Level:");
        lblRandom.setBounds(260, 50, 200, 25);

        H2Handler h2 = new H2Handler();
        int max_level = h2.getSavedLevel();

        cbRandom = new JComboBox<>();

        for (int i = 1; i <= max_level; i++) {
            cbRandom.addItem(String.valueOf(i));
        }

        cbRandom.setBounds(370, 50, 50, 25);

        btnRandom = new JButton("Punish!");
        btnRandom.setBounds(425, 50, 80, 25);
        btnRandom.addActionListener(this);

        lblSelectedIs = new JLabel("Selected Punishment is:");
        lblSelectedIs.setBounds(330, 80, 200, 50);

        taSelectedRandom = new JTextArea();
        taSelectedRandom.setBounds(200, 120, 400, 200);
        taSelectedRandom.setLineWrap(true);
        taSelectedRandom.setWrapStyleWord(true);
        taSelectedRandom.setEditable(false);

        btnMainMenu = new JButton("Main Menu");
        btnMainMenu.setBounds(300, 340, 200, 50);
        btnMainMenu.addActionListener(this);

        // frame window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setResizable(false);
        this.setVisible(true);
        this.setLayout(null);
        this.add(lblRandom);
        this.add(cbRandom);
        this.add(btnRandom);
        this.add(lblSelectedIs);
        this.add(taSelectedRandom);
        this.add(btnMainMenu);
        this.setTitle("Random Punishment Generator");
        this.setIconImage(icon.getImage());
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnRandom) {
            H2Handler h2  = new H2Handler();
            String level = String.valueOf(cbRandom.getSelectedItem());
            Record randomRecord = h2.getRandomRecordByLevel(Integer.parseInt(level));
            taSelectedRandom.setEditable(true);
            taSelectedRandom.selectAll();
            taSelectedRandom.replaceSelection("");
            taSelectedRandom.setText(randomRecord.getText());
            taSelectedRandom.setEditable(false);
        } else if (e.getSource() == btnMainMenu) {
            new MainMenu();
            this.dispose();
        }
    }
}
