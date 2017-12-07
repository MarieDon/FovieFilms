package utils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Stack;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XMLSerializer implements Serializer
{
	private Stack stack= new Stack();
	private File file;
	
	public XMLSerializer(File file)
	{
		this.file=file;
	}

	@Override
	public void push(Object o)
	{
		stack.push(o);
	}

	@Override
	public Object pop() {
		return stack.pop();
	}

	@Override
	public void write() throws Exception
	{
		ObjectOutputStream os=null;
		try
		{
			 XStream xstream = new XStream(new DomDriver());
		     os= xstream.createObjectOutputStream(new FileWriter(file));
		 os.writeObject(stack);
		}
		finally
		{
			if(os !=null)
			{
				os.close();
			}
		}
	}

	@Override
	public void read() throws Exception 
	{
		ObjectInputStream is=null;
		try
		{
			 XStream xstream = new XStream(new DomDriver());
		     is= xstream.createObjectInputStream(new FileReader(file));
	stack=(Stack) is.readObject();		     
		}
		finally
		{
			if(is !=null)
			{
				is.close();
			}
		}
	}
}
