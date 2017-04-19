package io_serializacja;

import java.io.Closeable;
import java.io.IOException;

import kolekcje_i_algorytmy.Logger;
import kolekcje_i_algorytmy.Student;

public class SerializedLogger implements Logger, Closeable {
	
	
	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void log(String status, Student student) {
		// TODO Auto-generated method stub
		
	}

}
