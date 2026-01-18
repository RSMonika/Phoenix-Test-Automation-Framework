package com.api.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager1 {

	public static void main(String[] args) throws IOException {
  Properties prop=new Properties();
  
  File file=new File(System.getProperty("user.dir")+"\\src\\test\\resources\\com\\config\\config.properties");
  FileReader reader=new FileReader(file);

	prop.load(reader)	;
	System.out.println(prop.getProperty("BASE_URI"));
		
  
		
		
	}

}
