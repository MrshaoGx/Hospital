package func;

import java.util.ArrayList;
import java.util.List;

import tools.Doc;
import tools.HttpUtils;

public class ViewMsg {
	public static List<Doc> ViewDocs(String msg){
		List<Doc> lists=new ArrayList<Doc>();
		String temp="";
		String data="msg="+msg;
		String path="http://192.168.1.126:8082/Hospital/ViewDoc";
		temp=HttpUtils.Post(data, path);
		System.out.println(temp);
		String[] temps=temp.split(";");
		for(int i=0;i<temps.length;i++){
				String[] per=temps[i].split(",");
					Doc doc=new Doc("±àÂë£º"+per[0].toString(),"ÐÕÃû£º"+per[1].toString(),"¿ÆÊÒ"+per[2].toString());
					lists.add(doc);
		}
		return lists;
	}
}
