package com.occ.RuntimeExec;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RuntimeExec {
	/*public static void main(String args[]) {

		String s;
		Process p = null;
		try {
			p = Runtime.getRuntime().exec("gcc -o out out.c");
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((s = br.readLine()) != null) {
				System.out.println("line: " + s);
			}
			BufferedReader brr = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			while ((s = brr.readLine()) != null) {
				System.out.println("line error: " + s);
			}

			p.waitFor();
			System.out.println("exit: " + p.exitValue());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			p.destroy();

		}
	}
*/
	
	public StringBuffer execute(String cmd) {
		StringBuffer message = new StringBuffer();
		String s;
		Process p = null;
		try {
			p = Runtime.getRuntime().exec(new String[]{"bash","-c",cmd});
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((s = br.readLine()) != null) {
				System.out.println("line: " + s);
				message.append(s);
				
			}
			BufferedReader brr = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			while ((s = brr.readLine()) != null) {
				System.out.println("line error: " + s);
				message.append(s);
			}
			p.waitFor();
			System.out.println("exitvalue from executor: " + p.exitValue());
			br.close();
			} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			p.destroy();

		}
		return message;
	}
}
