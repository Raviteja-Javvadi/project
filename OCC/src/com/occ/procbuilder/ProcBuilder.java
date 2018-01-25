package com.occ.procbuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcBuilder {

	public static String main(String... strings) throws InterruptedException, IOException {
		BufferedReader br = null;
		ProcessBuilder pb = new ProcessBuilder(strings[0], strings[1]);
		pb.directory(new File(strings[2]));
		String output=null;
		System.out.println("Running " + strings[0] + " " + strings[1]);
		Process process = pb.start();
		int errCode = process.waitFor();
		System.out.println("Command executed, any errors? " + (errCode == 0 ? "No" : "Yes"));
		if (errCode == 0) {
			br = new BufferedReader(new InputStreamReader(process.getInputStream()));
			output=output(br);
			System.out.println("Compilation successful:\n" + output);
			br.close();
			return "Compilation successful..."+output;
		} else {
			br = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			output=output(br);
			System.out.println("Error:\n" + output);
			br.close();
			return output;
		}
	}

	private static String output(BufferedReader br) throws IOException {
		StringBuilder sb = new StringBuilder();
		try {
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line + System.getProperty("line.separator"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();

	}

}
