package main;

import list.ManageList;
import list.SelectRandom;
import load.ListExport;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/** Provides access to randomized punishment, punishment
 *  management and exporting to list features
 * @author MidnightReaver
 * @version 1.0
 * @since 1.0
 */
public class MainMenu extends JFrame implements ActionListener {

    JButton btnSelectP;
    JButton btnManageP;
    JButton btnExportList;

    public MainMenu() {
        ImageIcon icon = new ImageIcon("trisk.png");

        btnSelectP = new JButton("Select Punishment");
        btnSelectP.setBounds(300, 200, 200, 50);
        btnSelectP.addActionListener(this);

        btnManageP = new JButton("Manage Punishments");
        btnManageP.setBounds(300, 255, 200, 50);
        btnManageP.addActionListener(this);

        btnExportList = new JButton("Export List");
        btnExportList.setBounds(300, 310, 200, 50);
        btnExportList.addActionListener(this);

        // frame window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setResizable(false);
        this.setVisible(true);
        this.setLayout(null);
        this.add(btnSelectP);
        this.add(btnManageP);
        this.add(btnExportList);
        this.setTitle("Random Punishment Generator");
        this.setIconImage(icon.getImage());
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO - MainMenu button actions (manage, export)
        if (e.getSource() == btnSelectP) {
            new SelectRandom();
            this.dispose();
        } else if (e.getSource() == btnManageP) {
            new ManageList();
            this.dispose();
        } else if (e.getSource() == btnExportList) {
            new ListExport();
            this.dispose();
        }
    }
}
