package list;

import h2.H2Handler;
import list.data.Record;
import main.MainMenu;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/** Lists all punishments with level
 * @author MidnightReaver
 * @version 1.0
 * @since 1.0
 */
public class DisplayList extends JFrame implements ActionListener {

    JTextArea textArea;

    JButton btnBack;

    public DisplayList() {

        ImageIcon icon = new ImageIcon("trisk.png");

        textArea = new JTextArea(30, 40);
        textArea.setEditable(false);
        JScrollPane scrollArea = new JScrollPane(textArea);

        scrollArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        btnBack = new JButton("Back");
        btnBack.setSize(200, 50);
        btnBack.addActionListener(this);

        // frame window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setResizable(false);
        this.setVisible(true);
        this.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
        this.getContentPane().add(btnBack);
        this.getContentPane().add(scrollArea);
        this.setTitle("Random Punishment Generator");
        this.setIconImage(icon.getImage());
        this.setLocationRelativeTo(null);

        populateList();
    }

    /** Queries all records in the punishments table
     *  and appends them to the JTextArea in order of
     *  level then ID number in ascending order
     */
    private void populateList() {
        textArea.setEditable(true);
        textArea.selectAll();
        textArea.replaceSelection("");

        H2Handler h2 = new H2Handler();
        ArrayList<Record> allRecords = h2.getAllRecords();

        for (int i = 0; i < allRecords.size(); i++) {
            int level = allRecords.get(i).getLevel();
            String text = allRecords.get(i).getText();
            String line = "Level " + level + ": " + text + "\n";
            textArea.append(line);
        }
        textArea.setEditable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnBack) {
            new MainMenu();
            this.dispose();
        }
    }
}
