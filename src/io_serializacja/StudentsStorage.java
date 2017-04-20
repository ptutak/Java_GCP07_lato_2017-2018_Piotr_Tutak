package io_serializacja;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public class StudentsStorage {
	
	String fileName;
	File file;
	FileInputStream fileInputStream;
	FileOutputStream fileOutputStream;
	DataInputStream dis;
	DataOutputStream dos;
	RandomAccessFile raf;
	
	
	public StudentsStorage(String fileName) throws IOException{
		this.fileName=fileName;	
		file=new File(fileName);
		if (!file.isFile()){
			file.createNewFile();
			fileOutputStream=new FileOutputStream(file);
			dos=new DataOutputStream(fileOutputStream);
			for (int i=0;i<20;++i){
				dos.writeInt(12*20);
				dos.writeInt(0);
				dos.writeInt(0);
			}
			dos.flush();
			dos.close();
			fileOutputStream.close();
			dos=null;
			fileOutputStream=null;
		}
	}
	
	public byte[] getImage(int index) throws IOException{
		if (index<0 || index>20)
			throw new IOException("wrong index");
		fileInputStream=new FileInputStream(file);
		dis=new DataInputStream(fileInputStream);
		dis.skip(index*12);
		int offset=dis.readInt();
		int size=dis.readInt();
		offset-=12*index+8;
		dis.skip(offset);
		byte[] image=new byte[size];
		int readBytes=dis.read(image);
		fileInputStream.close();
		dis.close();
		if (readBytes!=size)
			throw new IOException("wrong image size");
		return image;
	}
	
	public void putImage(byte[] image, int index) throws IOException{
		if (index<0 || index>20)
			throw new IOException("wrong index");
		raf=new RandomAccessFile(file,"rw");

		fileInputStream=new FileInputStream(file);
		dis=new DataInputStream(fileInputStream);
		
		dis.skip(index*12);
		int offset=dis.readInt();
		int size=dis.readInt();
		
		raf.seek(index*12+4);
		raf.writeInt(image.length);
		for (int i=index; i<20;++i){
			raf.seek(i*12);
			int off=raf.readInt();
			raf.seek(i*12);
			raf.writeInt(off+image.length-size);
		}

		raf.seek(offset);
		offset-=12*index+8;
		dis.skip(offset);
		dis.skip(size);
		byte[] b=new byte[dis.available()];
		dis.read(b);
		raf.write(image);
		raf.write(b);
		raf.close();
		dis.close();
		fileInputStream.close();
	}
	
	public void close() throws IOException{
		if (fileInputStream!=null)
			fileInputStream.close();
		if (dis!=null)
			dis.close();
		if (dos!=null)
			dos.close();
		if (fileOutputStream!=null)
			fileOutputStream.close();
		fileInputStream=null;
		dis=null;
		dos=null;
		fileOutputStream=null;
		file=null;
	}
	
	public void open() throws IOException{
		if (file==null)
			file=new File(fileName);
		if (!file.isFile()){
			file.createNewFile();
			fileOutputStream=new FileOutputStream(file);
			dos=new DataOutputStream(fileOutputStream);
			for (int i=0;i<20;++i){
				dos.writeInt(12*20);
				dos.writeInt(0);
				dos.writeInt(0);
			}
			dos.flush();
			dos.close();
			fileOutputStream.close();
			dos=null;
			fileOutputStream=null;
		}
	}
	
	public static void main(String[] args){
		try {
			StudentsStorage s=new StudentsStorage("images");
			FileInputStream f= new FileInputStream("image0.jpg");
			FileInputStream f2= new FileInputStream("image1.jpg");
			
			byte[] image=new byte[f.available()];
			byte[] image2=new byte[f2.available()];
			f.read(image);
			f2.read(image2);
			s.putImage(image, 0);
			s.putImage(image2, 1);
			s.close();
			f.close();
			f2.close();
			
			s.open();
			
			byte[] image3=s.getImage(0);
			byte[] image4=s.getImage(1);
			if (image.equals(image3) && image2.equals(image4))
				System.out.println("SUCCESS");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
