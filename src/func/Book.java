package func;

import tools.HttpUtils;

public class Book {
	public static String bookView(String msg){
		
		String temp="";
		String data="msg="+msg;
		String path="http://192.168.1.126:8082/Hospital/BookView";
		temp=HttpUtils.Post(data, path);
		System.out.println(temp);
		return temp;
	}
	public static boolean doBook(String msg){
		String temp="";
		boolean flag=false;
		String data="did="+msg+"&uid="+msg;
		String path="http://192.168.1.126:8082/Hospital/doBook";
		temp=HttpUtils.Post(data, path);
		if(temp!=null)
			flag=true;
		return flag;
		
	}
}
