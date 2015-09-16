package com.sakram.SwingWrite;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class WindowForm implements ActionListener {

	private JFrame frame;
	private JPanel mainPanel;
	private JPanel textPanel;
	private JPanel buttonPanel;
	private JTextArea wordField;
	private JScrollPane wordScroll;
	private JButton saveButton;
	private JButton loadButton;
	private JButton exitButton;

	public WindowForm() {
		createFrame();
	}

	private void createFrame() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(createPanel());								// As most Swing forms do, this app starts simply,
		frame.setBounds(200, 200, 800, 500);					// making a frame large enough to form an editable
		frame.setTitle("SwingWrite");							// text window.
		frame.setVisible(true);
	}

	private JPanel createPanel() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(createTextPanel(), BorderLayout.CENTER);	// The word processing area will be centered in the
		mainPanel.add(createButtonPanel(), BorderLayout.SOUTH);	// main panel, with the button bar beneath.
		return mainPanel;
	}

	private JPanel createTextPanel() {
		textPanel = new JPanel();
		textPanel.setLayout(new FlowLayout());
		wordField = new JTextArea(26, 60);						// Here, I specify that the area for text editing will be
		wordScroll = new JScrollPane(wordField,					// 26 rows by 60 columns in size, and add a JScrollPane
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,		// only in the event that there are more lines of text
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);		// present than you can see in the window.
		textPanel.add(wordScroll);
		return textPanel;
	}

	private JPanel createButtonPanel() {						// There will be 3 buttons beneath the text area, one
		buttonPanel = new JPanel();								// each to save and load the given file and another to
		buttonPanel.setLayout(new FlowLayout());				// quit the program.
		saveButton = new JButton("Save");
		saveButton.addActionListener(new SaveButtonListener());
		loadButton = new JButton("Load");
		loadButton.addActionListener(new LoadButtonListener());
		exitButton = new JButton("Exit");
		exitButton.addActionListener(new ExitButtonListener());
		buttonPanel.add(saveButton);
		buttonPanel.add(loadButton);
		buttonPanel.add(exitButton);
		return buttonPanel;
	}

	private class SaveButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			File f = new File("/Users/seankram/Desktop/swingWritten.txt");
			if (!f.isFile()) {									// Here, I check to see if the user has saved a file
				WriteToFile(true);								// of the designated name before, and if not, I'll bypass
				return;											// the dialog that asks if they want to overwrite the existing doc.
			}

			Object[] options = { "Add to File", "Overwrite" };	// But otherwise, a JOptionPane dialog shows up, letting the user
			int save = JOptionPane								// add the text they've entered to the swingWritten.txt file or
					.showOptionDialog(							// overwrite it entirely.
							frame,
							"Would you like to overwrite the existing file, or append the written text?",
							"Save File", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, options,
							options[1]);
			if (save == JOptionPane.YES_OPTION) {				// Whichever option the user decides on, I pass a boolean value
				WriteToFile(true);								// to the file-writing method, that concerns whether text is
			}													// appended to a file or whether it replaces what was already
			if (save == JOptionPane.NO_OPTION) {				// there.
				WriteToFile(false);
			}
		}
	}

	private void WriteToFile(boolean addToFile) {
		Writer write;
		try {
			write = new Writer("/Users/seankram/Desktop/swingWritten.txt",
					addToFile);									// Here, I call in the Writer class, take the user's above
			String content = wordField.getText();				// preference into consideration, then store all of the
			write.addText(content);								// entered text in a String and add it to the file
			write.closeFile();									// designated here, before closing the file to cap off the
		} catch (IOException e) {								// method.
			e.printStackTrace();
		}
	}

	private class LoadButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Reader read = new Reader("/Users/seankram/Desktop/swingWritten.txt");
			try {
				String content = read.getText();				// When the user clicks Load, the reverse of the above
				wordField.setText(content);						// method goes into action, as the contents of the given
			} catch (IOException e) {							// file are stored in a String and then added to the
				e.printStackTrace();							// editable field of text.
			}
		}
	}

	private class ExitButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.exit(0);										// Finally, Exit quits the program, no further hassle.
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
	
}
