package com.ray.framework.util.framework;

public class SqlReplaceUtil {

	 public static String replaceSQL(String  replace) {
			String replace1=replace.replaceAll("[\\s\t\n]{1,}", " ");
			String s="SELECT COUNT(1) FROM ";
			String[] aa = replace1.split("\\s(?i)from\\s",2);
			if(aa.length==2){
				s+=aa[aa.length-1];
			}
			String s1="";
			String[] ab = s.split("\\s(?i)order\\s(?i)by\\s");
			if(ab.length==1){
				return s;
			}else{
				for (int i = 0 ; i<ab.length-1 ; i++ ) {
					if(i==ab.length-2){
						s1+=ab[i];
					}else{
						s1+=ab[i]+" order by ";
					}
				}
				return s1;
			}
			
		}
	
}
