package load;

import h2.H2Handler;
import list.data.Record;
import main.MainMenu;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

/** Exports punishment tables as formatted text document
 * @author MidnightReaver
 * @version 1.0
 * @since 1.0
 */
public class ListExport extends JFrame implements ActionListener {

    JTextField tfSaveFilePath;
    JButton btnSaveBrowse;
    JButton btnSave;
    JButton btnBack;

    JFileChooser saveFileChooser;
    File saveFile;

    // Dialog box
    JDialog dlgNotice;
    JButton btnNotice;

    private boolean saveListNameLoaded;

    public ListExport() {

        ImageIcon icon = new ImageIcon("trisk.png");

        saveListNameLoaded = false;

        tfSaveFilePath = new JTextField();
        tfSaveFilePath.setBounds(250, 200, 200, 26);

        btnSaveBrowse = new JButton("Save As...");
        btnSaveBrowse.setBounds(450, 200, 100, 25);
        btnSaveBrowse.addActionListener(this);

        btnSave = new JButton("Save List");
        btnSave.setBounds(250, 230, 300, 25);
        btnSave.addActionListener(this);

        btnBack = new JButton("Back");
        btnBack.setBounds(250, 260, 200, 50);
        btnBack.addActionListener(this);

        // frame window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setResizable(false);
        this.setVisible(true);
        this.setLayout(null);
        this.add(tfSaveFilePath);
        this.add(btnSaveBrowse);
        this.add(btnSave);
        this.setTitle("Random Punishment Generator");
        this.setIconImage(icon.getImage());
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnSaveBrowse) {
            saveFileChooser = new JFileChooser();
            saveFileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
            saveFileChooser.setSelectedFile(new File("punish.txt"));
            saveFileChooser.setFileFilter(new FileNameExtensionFilter("Text file","txt"));

            int response = saveFileChooser.showSaveDialog(null);

            if (response == JFileChooser.APPROVE_OPTION) {
                saveFile = new File(saveFileChooser.getSelectedFile().getAbsolutePath());

                tfSaveFilePath.setText(saveFile.toString());
                saveListNameLoaded();
            }
        } else if (e.getSource() == btnSave) {
            if (!getSaveListNameLoaded()) { // if listNameLoaded is false
                dlgNotice = new JDialog(this, "Notice");
                JLabel lblNotice = new JLabel("No save path selected!");
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
                databaseToFile(saveFile);
                new MainMenu();
                this.dispose();
            }
        } else if (e.getSource() == btnNotice) {
            dlgNotice.dispose();
        } else if (e.getSource() == btnBack) {
            new MainMenu();
            this.dispose();
        }
    }

    private void databaseToFile(File file) {

        H2Handler h2 = new H2Handler();
        ArrayList<Record> allRecords = h2.getAllRecords();

        try {
            FileWriter saveFileStream = new FileWriter(file);
            for (Record allRecord : allRecords) {
                int level = allRecord.getLevel();
                String text = allRecord.getText();
                String record = level + ":" + text;
                System.out.println("databaseToFile record => " + record);
                saveFileStream.write(record + "\n");
            }
            saveFileStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveListNameLoaded() {
        saveListNameLoaded = true;
    }

    private boolean getSaveListNameLoaded() {
        return saveListNameLoaded;
    }
}
