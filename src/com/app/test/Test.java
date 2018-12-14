package com.app.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import com.app.model.file;

public class Test {

	public static void main(String[] args) {
		SessionFactory sf=new AnnotationConfiguration ().configure().buildSessionFactory();
		Session s=sf.openSession();
		BufferedReader br = null;
	    Transaction trans = null;
		try {
			String line  = null;
			String arr[] = null;
			file f = null;
			String name = "C:"+File.separator+"Users"+File.separator+"raghav"+File.separator+"Desktop"+
					       File.separator+"access.log";
			br = new BufferedReader(new FileReader(new File(name)));
			trans = s.beginTransaction();
			 while((line = br.readLine()) != null){
				 f = new file();
				 arr = line.split("\\|");
				 f.setTimeStamp(arr[0].trim());
				 f.setIpAddress(arr[1].trim());
				 f.setHttpMethod(arr[2].trim());
				 f.setThreshold(arr[3].trim());
				 f.setDescription(arr[4].trim());
				 
				 s.save(f);
			 }
			 trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
	        s.flush();
	        s.close();
	    try {
	        if(br != null) {
	            br.close();
	            br = null;
	        }
	    } catch(Exception e) {
	        e.printStackTrace();
	    	}
		}
	}
}
