package com.sakram.SwingWrite;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Writer {
	private String path;
	private PrintWriter writer;
	private boolean addToFile;

	public Writer(String newPath, boolean add) throws IOException {
		this.path = newPath;
		this.addToFile = add;
		createFile();
	}

	private void createFile() throws IOException {
		FileWriter output = new FileWriter(path, addToFile);
		writer = new PrintWriter(output);
	}

	public void addText(String content) {
		writer.println(content);
	}

	public void closeFile() {
		writer.close();
	}

}
