package com.irs.irengine.textparser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TextParser {

	private Map<Integer, File> fileDictionary = new HashMap<>();
	private Map<Integer, String> wordDictionary = new HashMap<>();
	private List<String> stopWordsList = new ArrayList<>();
	private int tokenCounter = 0;

	public TextParser() throws IOException {
		super();
		flushOutputFile();
		readFiles();
		loadStopWords();
		tokenziation();
	}

	private void flushOutputFile() {
		FileWriter fwObject = null;
		try {
			fwObject = new FileWriter("parser_output.txt", false);
			PrintWriter pwObject = new PrintWriter(fwObject, false);
			pwObject.flush();
			pwObject.close();
			pwObject.close();
		} catch (IOException e) {
			System.out.println("Exception occured while Flushing the output file:" + e.getMessage());
		}
	}

	public void readFiles() {
		/*
		 * This method used to load the folder contents 
		 * it also creates a File dictionary with a unique ID
		 *  */
		File directoryPath = new File("ft911");

		// List of all files and directories
		try {
			File filesList[] = directoryPath.listFiles();

			System.out.println("List of files and directories in the specified directory:");
			int counter = 0;
			for (File file : filesList) {
				counter += 1;
				fileDictionary.put(counter, file);

				System.out.println("File name: " + file.getName());
				System.out.println("File path: " + file.getAbsolutePath());
				System.out.println("Unique Doc Id: " + counter);
				System.out.println(" ");
			}
			System.out.print("Total Number of Files in the Folder " + fileDictionary.size());
		} catch (Exception e) {
			System.out.println("Exception Occured while loading files: " + e);
		}
	}

	private void loadStopWords() {
		/*
		 * This method is used to load the stop words into the memory
		 * */
		try {
			Scanner sc = new Scanner(new FileInputStream("stopwordlist.txt"));
			while (sc.hasNextLine()) {
				String word = sc.nextLine();
				stopWordsList.add(word);
			}
		} catch (Exception e) {
			System.out.println("Exception occured while reading Stopwords list file" + e);
		}
	}

	public void tokenziation() throws IOException {

		/*
		 * This is used to tokenize each term
		 * To create a word dictionary
		 * To write the word dictionary into a output file 'parser_output'
		 * To write the file dictionary into the output file 'parser_output'
		 * */
		for (Map.Entry<Integer, File> docEntry : fileDictionary.entrySet()) {
			File currentFile = docEntry.getValue();
			FileReader reader = null;
			try {
				reader = new FileReader(currentFile);
				BufferedReader bufferread = new BufferedReader(reader);
				StreamTokenizer token = new StreamTokenizer(bufferread);
				token.lowerCaseMode(true);

				int t;
				while ((t = token.nextToken()) != StreamTokenizer.TT_EOF) {
					switch (t) {
					case StreamTokenizer.TT_WORD:
						if (stopWordsList.contains(token.sval)) {
							break;
						}
						String outputToken = stemming(token.sval);
						tokenCounter += 1;
						wordDictionary.put(tokenCounter, outputToken);
						writeOutputToken(tokenCounter, outputToken);
						break;
					}

				}
			} catch (FileNotFoundException e) {
				System.out.println("Exception Occured while Reading File data:" + e);
			} finally {
				reader.close();
			}
		}
		System.out.println("Final Size:"+wordDictionary.size());
		for (Map.Entry<Integer, File> docEntry : fileDictionary.entrySet()) {
			writeOutputToken(docEntry.getKey(), docEntry.getValue().getName());
		}
	}

	private void writeOutputToken(int tokenCounter, String outputToken) {

		/*
		 * This mehtod performs write operation to the file
		 * */
		try {

			BufferedWriter f_writer = new BufferedWriter(new FileWriter("parser_output.txt", true));
			f_writer.write(tokenCounter + " " + outputToken + "\n");

			f_writer.close();
		} catch (IOException e) {
			System.out.print("Exception Occured While writing into the file:" + e.getMessage());
		}

	}

	private String stemming(String stemWord) {
		/*
		 * This method utilizes the stemming algorithm to reduce the term 
		 * */
		Porter stemmerAlgo = new Porter();
		String result = stemmerAlgo.stripAffixes(stemWord);
		return result;

	}
}
