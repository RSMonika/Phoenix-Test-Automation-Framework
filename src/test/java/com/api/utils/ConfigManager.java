package com.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Properties;

public class ConfigManager {

	// read from src test resource
	private static Properties prop = new Properties();// Create the object of preoperties file
	private static String path = "com/config/config.properties";
	private static String env;

	private ConfigManager() {
		// Private configmanager
	}

	static {
		
		env=System.getProperty("env","qa");
		env=env.toLowerCase().trim();
		//System.out.println("Running test in which environment   "+ env );
       switch (env) {
       case "dev"-> path="com/config/configdev1.properties";
    	   
       case "qa"->  path="com/config/configqa.properties";
    	   
    	   
       case "uat" -> path="com/config/configuat.properties";

       default -> path= "com/config/configqa.properties";
    	  
       }
		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
		if (input==null) {
			throw new RuntimeException("Cannot find the file at path"+input);
		}
		
		try {

			prop.load(input);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getProperty(String Key) {

		// load the properties file using load()--in the form of reader--file
		// amangement---reader--

		return prop.getProperty("BASE_URI");

	}

}
