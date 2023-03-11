package com.irs.irengine.main;

import com.irs.irengine.textparser.TextParser;

public class IREngineMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
		TextParser initializeParse = new TextParser();
		}catch(Exception e) {
			System.out.println("Exception throwed from Parser:"+e);
		}
		
	}

}
