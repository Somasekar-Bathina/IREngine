package com.irs.irengine.textparser;

import java.io.File;

public class TextParser {
	
	public TextParser() {
		super();
		readFiles();
		// TODO Auto-generated constructor stub
	}

	public void readFiles() {
		File directoryPath = new File("ft911");
		
	    //List of all files and directories
	    File filesList[] = directoryPath.listFiles();
	    
	    System.out.println("List of files and directories in the specified directory:");
	    
	    for(File file : filesList) {
	       System.out.println("File name: "+file.getName());
	       System.out.println("File path: "+file.getAbsolutePath());
	       System.out.println("Size :"+file.getTotalSpace());
	       System.out.println(" ");
	    }
	}

}
