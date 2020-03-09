package indi.shuolei.mapeditor.fileeditor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class fileEditor {
	public static void writeIntoJava(String pathTemp, String pathTarget,String mark,String inputString) throws IOException {
        codeCopy(pathTemp,pathTarget);
		boolean isMarkExist = false;
    	FileInputStream fis=null;
        InputStreamReader isr=null;
        BufferedReader bReader=null;
        OutputStreamWriter osw=null;
        FileOutputStream fos=null;
        BufferedWriter bWriter=null;
        String line;
    	fis=new FileInputStream(pathTemp);
    	fos=new FileOutputStream(pathTarget);
        isr=new InputStreamReader(fis);
        osw=new OutputStreamWriter(fos);
        bReader=new BufferedReader(isr);
        bWriter=new BufferedWriter(osw);
        while((line=bReader.readLine())!=null){
        	bWriter.write(line);
        	bWriter.newLine();
        	if(line.equals(mark)) {
        		isMarkExist = true;
        		bWriter.write(inputString);
        		bWriter.newLine();
        	}
        }
        if(!isMarkExist) {
        	System.out.println("No mark exist");
        }
        bReader.close();
        isr.close();
        fis.close();
        bWriter.close();
        osw.close();
        fos.close();
	}
	
//	public static void delete(String pathTemp, String pathTarget,String StringTodelete) throws IOException {
//    	FileInputStream fis=null;
//        InputStreamReader isr=null;
//        BufferedReader bReader=null;
//        OutputStreamWriter osw=null;
//        FileOutputStream fos=null;
//        BufferedWriter bWriter=null;
//        String line;
//        if(decideFile == 1) {
//        	fis=new FileInputStream(pathTemp);
//        	fos=new FileOutputStream(pathTarget);
//        	decideFile = 0;
//        }else {
//        	fis=new FileInputStream(pathTarget);
//        	fos=new FileOutputStream(pathTemp);
//        	decideFile = 1;
//        }
//        isr=new InputStreamReader(fis);
//        osw=new OutputStreamWriter(fos);
//        bReader=new BufferedReader(isr);
//        bWriter=new BufferedWriter(osw);
//        while((line=bReader.readLine())!=null){
//        	if(!line.equals(StringTodelete)) {
//            	bWriter.write(line);
//            	bWriter.newLine();
//        	}else {
//        		bWriter.newLine();
//        	}
//        }
//        bReader.close();
//        isr.close();
//        fis.close();
//        bWriter.close();
//        osw.close();
//        fos.close();
//	}
	
	public static void codeCopy (String pathTemp, String pathTarget) throws IOException {
    	FileInputStream fis=null;
        InputStreamReader isr=null;
        BufferedReader bReader=null;
        OutputStreamWriter osw=null;
        FileOutputStream fos=null;
        BufferedWriter bWriter=null;
        String line;        
    	fis=new FileInputStream(pathTarget);
		fos=new FileOutputStream(pathTemp);
        isr=new InputStreamReader(fis);
        osw=new OutputStreamWriter(fos);
        bReader=new BufferedReader(isr);
        bWriter=new BufferedWriter(osw);
        while((line=bReader.readLine())!=null){
        	bWriter.write(line);
        	bWriter.newLine();
        }
        bReader.close();
        isr.close();
        fis.close();
        bWriter.close();
        osw.close();
        fos.close();
	}
}
