package com.whoknows.tools;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class main {
	
	public static void main(String[] args) {
		
		try (BufferedReader br
				= new BufferedReader(new FileReader("src/main/resources/user"))) {
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] strs = line.split("\t");
				if (strs.length == 3) {
					System.out.println(strs[1]);
				}
			}
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
