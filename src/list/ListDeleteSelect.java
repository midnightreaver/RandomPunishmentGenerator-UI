package list;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import h2.H2Handler;
import list.data.Record;

public class ListDeleteSelect extends JFrame implements ActionListener {
	
	JComboBox<Object> cbDeletePunishments;
	
	ArrayList<Record> records;
	
	JLabel lblDeleteNotice;
	
	JButton btnDelete;
	JButton btnBack;
	
	int currentLevel;
	
	public ListDeleteSelect(int selectedLevel) {
		ImageIcon icon = new ImageIcon("trisk.png");
		
		currentLevel = selectedLevel;
		
		cbDeletePunishments = new JComboBox<>();
		cbDeletePunishments.setBounds(250, 200, 300, 25);
		
		H2Handler h2 = new H2Handler();
		
		// Initialize list with Level 1
		records = h2.getRecordsByLevel(selectedLevel);
		
		for (Record record : records) {
		    String text = record.getText();
		    cbDeletePunishments.addItem(String.valueOf(text));
		}
		
		lblDeleteNotice = new JLabel();
		lblDeleteNotice.setBounds(100, 230, 600, 25);
		lblDeleteNotice.setHorizontalTextPosition(JLabel.CENTER);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(300, 260, 200, 50);
		btnDelete.addActionListener(this);
		
		btnBack = new JButton("Back");
        btnBack.setBounds(300, 315, 200, 50);
        btnBack.addActionListener(this);
		
		// frame window
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 600);
		this.setResizable(false);
		this.setVisible(true);
		this.setLayout(null);
		this.add(cbDeletePunishments);
		this.add(lblDeleteNotice);
		this.add(btnDelete);
		this.add(btnBack);
		this.setTitle("Random Punishment Generator");
		this.setIconImage(icon.getImage());
		this.setLocationRelativeTo(null);
	}
	
	public ListDeleteSelect(int selectedLevel, String deletedText) {
		ImageIcon icon = new ImageIcon("trisk.png");
		
		currentLevel = selectedLevel;
		
		cbDeletePunishments = new JComboBox<>();
		cbDeletePunishments.setBounds(250, 200, 300, 25);
		
		H2Handler h2 = new H2Handler();
		
		// Initialize list with Level 1
		records = h2.getRecordsByLevel(selectedLevel);
		
		for (Record record : records) {
		    String text = record.getText();
		    cbDeletePunishments.addItem(String.valueOf(text));
		}
		
		//System.out.println("Item count: " + cbDeletePunishments.getItemCount());
		
		lblDeleteNotice = new JLabel("'" + deletedText + "' has been deleted.", SwingConstants.CENTER);
		lblDeleteNotice.setBounds(100, 230, 600, 25);
		lblDeleteNotice.setHorizontalTextPosition(JLabel.CENTER);
		lblDeleteNotice.setForeground(Color.RED);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(300, 260, 200, 50);
		btnDelete.addActionListener(this);
		
		if (cbDeletePunishments.getItemCount() == 0) {
			btnDelete.setEnabled(false);
		}
		
		btnBack = new JButton("Back");
        btnBack.setBounds(300, 315, 200, 50);
        btnBack.addActionListener(this);
		
		// frame window
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 600);
		this.setResizable(false);
		this.setVisible(true);
		this.setLayout(null);
		this.add(cbDeletePunishments);
		this.add(lblDeleteNotice);
		this.add(btnDelete);
		this.add(btnBack);
		this.setTitle("Random Punishment Generator");
		this.setIconImage(icon.getImage());
		this.setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == btnDelete) {
			if (cbDeletePunishments.getItemCount() > 0) {
				H2Handler h2 = new H2Handler();
				int index = cbDeletePunishments.getSelectedIndex();
				int recordId = records.get(index).getId();
				String text = records.get(index).getText();
	            h2.deleteRecord(recordId);
	            this.dispose();
	            new ListDeleteSelect(currentLevel, text);
			}
		} else if (e.getSource() == btnBack) {
			this.dispose();
			new ListDelete();
		}
	}
	

}
