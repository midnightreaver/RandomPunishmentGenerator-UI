package load;

import create.CreateStart;
import h2.H2Handler;
import main.MainMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/** Parses a colon-separated text file and adds punishments to list
 * @author MidnightReaver
 * @version 1.0
 * @since 1.0
 */
public class ListImport extends JFrame implements ActionListener {

    JTextField tfFilePath;
    JButton btnBrowse;
    JButton btnLoad;
    JButton btnBack;

    JFileChooser fileChooser;
    File file;

    // Dialog box
    JDialog dlgNotice;
    JButton btnNotice;

    private boolean listNameLoaded;

    public ListImport() {
        ImageIcon icon = new ImageIcon("trisk.png");

        listNameLoaded = false;

        tfFilePath = new JTextField();
        tfFilePath.setBounds(250, 200, 200, 26);

        btnBrowse = new JButton("Browse...");
        btnBrowse.setBounds(450, 200, 100, 25);
        btnBrowse.addActionListener(this);

        btnLoad = new JButton("Load List");
        btnLoad.setBounds(250, 230, 300, 25);
        btnLoad.addActionListener(this);

        btnBack = new JButton("Back");
        btnBack.setBounds(250, 260, 200, 50);
        btnBack.addActionListener(this);

        // frame window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setResizable(false);
        this.setVisible(true);
        this.setLayout(null);
        this.add(tfFilePath);
        this.add(btnBrowse);
        this.add(btnLoad);
        this.setTitle("Random Punishment Generator");
        this.setIconImage(icon.getImage());
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnBrowse) {
            fileChooser = new JFileChooser();

            int response = fileChooser.showOpenDialog(null);

            if (response == JFileChooser.APPROVE_OPTION) {
                file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                tfFilePath.setText(file.toString());
                setListNameLoaded();
            }
        } else if (e.getSource() == btnLoad) {
            if (!getListNameLoaded()) { // if listNameLoaded is false
                dlgNotice = new JDialog(this, "Notice");
                JLabel lblNotice = new JLabel("No list loaded!");
                btnNotice = new JButton("OK");
                btnNotice.addActionListener(this);
                JPanel noticePanel = new JPanel();
                noticePanel.add(lblNotice);
                noticePanel.add(btnNotice);
                dlgNotice.add(noticePanel);
                dlgNotice.setSize(200, 200);
                dlgNotice.setVisible(true);
                dlgNotice.setLocationRelativeTo(null);

            } else {
                fileToDatabase(file);
                new MainMenu();
                this.dispose();
            }
        } else if (e.getSource() == btnNotice) {
            dlgNotice.dispose();
        } else if (e.getSource() == btnBack) {
            new CreateStart();
            this.dispose();
        }
    }

    /** Performs the file conversion and database entry
     *
     * @param file the colon-separated file to be converted
     */
    private void fileToDatabase(File file) {

        BufferedReader bufferedReader;
        H2Handler h2 = new H2Handler();
        int sLevel = h2.getSavedLevel();
        int maxLevel = sLevel;

        try {
            bufferedReader = new BufferedReader(new FileReader(file.toString()));
            String line = bufferedReader.readLine();

            while (line != null) {
                String[] elements = line.split(":");
                int level = Integer.parseInt(elements[0]);
                String text = elements[1];
                h2.addRecord(level, text);

                if (level > maxLevel) {
                    maxLevel = level;
                }

                line = bufferedReader.readLine();
            }
            bufferedReader.close();

            if (maxLevel > sLevel) {
                h2.saveLevel(maxLevel);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Enables indicator that the JTextField is not empty
     *
     */
    private void setListNameLoaded() {
        listNameLoaded = true;
    }

    /** Returns status of JTextField containing a value
     *
     * @return boolean value of true if JTextField contains
     *         a value, false otherwise
     */
    private boolean getListNameLoaded() {
        return listNameLoaded;
    }
}
