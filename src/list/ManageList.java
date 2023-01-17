package list;

import main.MainMenu;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Provides access to listing, adding and deleting windows
 * @author MidnightReaver
 * @version 1.0
 * @since 1.0
 */
public class ManageList extends JFrame implements ActionListener {

    JButton btnListP;
    JButton btnAddP;
    JButton btnDeleteP;
    JButton btnMainMenu;

    public ManageList() {

        ImageIcon icon = new ImageIcon("trisk.png");

        btnListP = new JButton("List Punishments");
        btnListP.setBounds(300, 200, 200, 50);
        btnListP.addActionListener(this);

        btnAddP = new JButton("Add Punishment");
        btnAddP.setBounds(300, 255, 200, 50);
        btnAddP.addActionListener(this);

        btnDeleteP = new JButton("Delete Punishment");
        btnDeleteP.setBounds(300, 310, 200, 50);
        btnDeleteP.addActionListener(this);

        btnMainMenu = new JButton("Main Menu");
        btnMainMenu.setBounds(300, 365, 200, 50);
        btnMainMenu.addActionListener(this);

        // frame window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setResizable(false);
        this.setVisible(true);
        this.setLayout(null);
        this.add(btnListP);
        this.add(btnAddP);
        this.add(btnDeleteP);
        this.add(btnMainMenu);
        this.setTitle("Random Punishment Generator");
        this.setIconImage(icon.getImage());
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnListP) {
            new DisplayList();
            this.dispose();
        } else if (e.getSource() == btnAddP) {
            new ListAdd();
            this.dispose();
        } else if (e.getSource() == btnDeleteP) {
            new ListDelete();
            this.dispose();
        } else if (e.getSource() == btnMainMenu) {
            new MainMenu();
            this.dispose();
        }
    }
}
