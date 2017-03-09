package func;

import tools.HttpUtils;

public class LoginAndReg {
		public static String Login(String username,String password){
			String x="";
			String data="username="+username+"&password="+password;
			String path="http://192.168.1.126:8082/Hospital/LoginServlet";
			x=HttpUtils.Post(data, path);
			return x;
			
		}
		public static String Reg(String username,String password){
			String x="";
			String data="username="+username+"&password="+password;
			String path="http://192.168.1.126:8082/Hospital/RegServlet";
			x=HttpUtils.Post(data, path);
			return x;
			
		}
}
