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
		
		env=System.getProperty("env","qa");//it reads the system properties which stored --mvn test -Denv=qa,D -DEFINE SYSTEM PROPERTY
		env=env.toLowerCase().trim();//-Denv=qa,env= defines property name ,qa =values assigned to it
		//System.out.println("Running test in which environment   "+ env );
       switch (env) {
       case "dev"-> path="com/config/configdev1.properties";
    	   
       case "qa"->  path="com/config/configqa.properties";
    	   
    	   
       case "uat" -> path="com/config/configuat.properties";

       default -> path= "com/config/configqa.properties";
    	  
       }//Class laoder---used as it works anywhere,
		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
		if (input==null) {
			throw new RuntimeException("Cannot find the file at path"+input);
		}
		
		try {

			prop.load(input);//read the file content!!
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getProperty(String Key) {

		// load the properties file using load()--in the form of reader--file
		// amangement---reader--

		return prop.getProperty("BASE_URI");//gets the proeprty of file having key mentioned!!returns it va;ues

	}

}
