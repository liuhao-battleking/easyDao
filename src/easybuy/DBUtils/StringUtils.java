package easybuy.DBUtils;

public class StringUtils {
	
	public static boolean ifNull(String str){
		if(str!=null&&!str.equals(""))
			return false;
		else
			return true;
	}

}
