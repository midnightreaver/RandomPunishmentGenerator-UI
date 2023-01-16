package create;

import h2.H2Handler;
import main.MainMenu;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Sets the initial highest level of punishments
 * @author MidnightReaver
 * @version 1.0
 * @since 1.0
 */
public class CreateStart extends JFrame implements ActionListener {

    JLabel lblNumLvl;
    JComboBox cbNumLvl;
    DefaultListCellRenderer listRender;
    JButton btnContinue;

    public CreateStart() {
        ImageIcon icon = new ImageIcon("trisk.png");

        lblNumLvl = new JLabel("Number of Levels:");
        lblNumLvl.setBounds(188, 205, 150, 50);

        String[] levels = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

        cbNumLvl = new JComboBox<>(levels);
        cbNumLvl.addActionListener(this);
        cbNumLvl.setBounds(300, 220, 200, 25);

        listRender = new DefaultListCellRenderer();
        listRender.setHorizontalAlignment(DefaultListCellRenderer.CENTER); // center-aligned items
        cbNumLvl.setRenderer(listRender);

        btnContinue = new JButton("Continue");
        btnContinue.setBounds(300, 253, 200, 25);
        btnContinue.addActionListener(this);

        // frame window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setResizable(false);
        this.setVisible(true);
        this.setLayout(null);
        this.add(lblNumLvl);
        this.add(cbNumLvl);
        this.add(btnContinue);
        this.setTitle("Random Punishment Generator");
        this.setIconImage(icon.getImage());
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnContinue) {
            H2Handler h2  = new H2Handler();
            h2.init();
            String level = String.valueOf(cbNumLvl.getSelectedItem());
            h2.saveLevel(Integer.parseInt(level));
            new MainMenu();
            this.dispose();
        }
    }
}
