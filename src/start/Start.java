package start;

import create.CreateStart;
import h2.H2Handler;
import load.ListImport;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Provides access to create a fresh list (Begin) or import
 *  from text file (Load)
 * @author MidnightReaver
 * @version 1.0
 * @since 1.0
 */
public class Start extends JFrame implements ActionListener {

    JLabel logoLabel;
    JButton btnBegin;
    JButton btnLoad;

    public Start() {

        H2Handler h2 = new H2Handler();
        h2.init();

        ImageIcon icon = new ImageIcon("trisk.png");

        logoLabel = new JLabel();
        logoLabel.setIcon(icon);
        logoLabel.setBounds(336, 100, 128, 128);

        btnBegin = new JButton("Begin");
        btnBegin.setBounds(300, 300, 200, 50);
        btnBegin.addActionListener(this);

        btnLoad = new JButton("Load");
        btnLoad.setBounds(300, 360, 200, 50);
        btnLoad.addActionListener(this);

        // frame window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setResizable(false);
        this.setVisible(true);
        this.setLayout(null);
        this.add(logoLabel);
        this.add(btnBegin);
        this.add(btnLoad);
        this.setTitle("Random Punishment Generator");
        this.setIconImage(icon.getImage());
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnBegin) {
            new CreateStart();
            this.dispose();
        } else if (e.getSource() == btnLoad) {
            new ListImport();
            this.dispose();
        }

    }
}
