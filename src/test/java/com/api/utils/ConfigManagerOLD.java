package com.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

public class ConfigManagerOLD {

	
	//read from src test resource
	private static Properties prop=new Properties();//Create the object of preoperties file


	static {//Operation of loading the property file in memeory----executed once ---during class loading time	
		File configfile=new File(System.getProperty("user.dir")+File.separator+"src"+File.separator+"test"+File.separator+"resources"+File.separator+"com"+File.separator+"config"+File.separator+"config.properties");
		try {
			FileReader filereader=null;
			filereader=new FileReader(configfile);
			prop.load(filereader);
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	public static  String getProperty(String Key)  {

		//load the properties file using load()--in the form of reader--file amangement---reader--

		
		return prop.getProperty("BASE_URI");

	}



}
