package com.occ.filewriter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FWriter {
	public void write(String content,String filename) {

		BufferedWriter bw = null;
		FileWriter fw = null;

		try {

			// String content = "This is the content to write into file\n";

			fw = new FileWriter(filename);
			bw = new BufferedWriter(fw);
			bw.write(content);

			System.out.println("written "+content+ "into "+filename);

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}

	}

}
