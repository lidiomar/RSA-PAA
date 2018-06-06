package br.com.paa.rsa;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileManager {
	
	private File getFileFromResource(String fileName) {
		File file = new File(fileName);
		return file;
	}
	
	
	public Boolean writeOnFile(String fileName, String content) {
		File file = getFileFromResource(fileName);
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		
		try {
			fileWriter = new FileWriter(file.getAbsolutePath());
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(content);
			bufferedWriter.close();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public String readFromFile(String fileName) {
		File file = getFileFromResource(fileName);
		StringBuilder stringBuilder = new StringBuilder("");
		try {
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				stringBuilder.append(scanner.nextLine());
				if(scanner.hasNextLine())
					stringBuilder.append(System.lineSeparator());
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return stringBuilder.toString();
	}
	
}
