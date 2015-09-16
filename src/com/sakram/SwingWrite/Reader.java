package com.sakram.SwingWrite;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Reader {

	private String path;

	public Reader(String path) {
		this.path = path;
	}

	@SuppressWarnings("resource")
	public String getText() throws IOException {
		String content = "";
		FileInputStream fstream = new FileInputStream(path);
		DataInputStream fileIn = new DataInputStream(fstream);
		BufferedReader buff = new BufferedReader(new InputStreamReader(fileIn));
		String lineIn;
		while ((lineIn = buff.readLine()) != null) {
			content += (lineIn + "\n");
		}
		return content;
	}

}
