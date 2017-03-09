package tools;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpUtils {
	public static String Post(String data,String path){
		String temp=null;
		URL url = null;
		try {
			url=new URL(path);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpURLConnection conn=null;
		try {
			conn=(HttpURLConnection) url.openConnection();
			int len=data.length();
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Content-Length", len+"");
			conn.setRequestMethod("POST");
			conn.setConnectTimeout(5000);
			conn.setDoOutput(true);
			OutputStream os=conn.getOutputStream();
			os.write(data.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int code;
		try {
			code = conn.getResponseCode();
			if(code==200)
			{
				InputStream is=conn.getInputStream();
				temp=StreamTools(is);
				return temp;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return temp;
		
	}

	private static String StreamTools(InputStream is) {
		String temp=null;
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		byte[] buffer=new byte[1024];
		int len=0;
		try {
			while((len=is.read(buffer))!=-1){
				baos.write(buffer,0,len);
				byte[] result=baos.toByteArray();
				temp=new String(result);
			}
			return temp;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}
}
