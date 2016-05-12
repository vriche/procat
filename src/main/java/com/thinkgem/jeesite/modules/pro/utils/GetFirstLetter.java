package com.thinkgem.jeesite.modules.pro.utils;




/****************************************************************************     
 * Created on 2007-11-21                                      *    
 *                                                               			*
 * @author <a href="mailto:hongxilin@yahoo.com.cn">hongxi</a>               *
 *         Updated by hongxi (hongxilin@yahoo.com.cn)                       *
 *                                                                          *
 * @version  Vision:1.0														*
 * 																			*
 ***************************************************************************/

/**
 * Title:获得汉字的拼音首字母
 * Description: GB 2312-80 把收录的汉字分成两级。第一级汉字是常用汉字，计 3755 个，
 * 置于 16～55 区，按汉语拼音字母／笔形顺序排列；第二级汉字是次常用汉字，
 * 计 3008 个，置于 56～87 区，按部首／笔画顺序排列，所以本程序只能查到
 * 对一级汉字的声母。同时对符合声母（zh，ch，sh）只能取首字母（z，c，s） 
 * Copyright: Copyright (c) 2004
 * Company: 
 * @author not attributable
 * @version 1.0
 */


import java.io.UnsupportedEncodingException;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class GetFirstLetter {
//	 国标码和区位码转换常量
	  private static final int GB_SP_DIFF = 160;

//	存放国标一级汉字不同读音的起始区位码
	  private static final int[] secPosvalueList = {
	      1601, 1637, 1833, 2078, 2274, 2302, 2433, 2594, 2787,
	      3106, 3212, 3472, 3635, 3722, 3730, 3858, 4027, 4086,
	      4390, 4558, 4684, 4925, 5249, 5600};

//	存放国标一级汉字不同读音的起始区位码对应读音
	  private static final char[] firstLetter = {
	      'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j',
	      'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
	      't', 'w', 'x', 'y', 'z'};
	  

	  
	  

//	获取一个字符串的拼音码
	  public static String getFirstLetter(String oriStr) {
		    String str = SBCchange(oriStr).toLowerCase();
		    str = StringFilter(str);
	    
	    if (str == null || str.trim().length() == 0) {  
            return "";  
        }  
  	    
	    
	      //特殊字符：_ & @  
        //数字   48-57  
        //字母 65-90    97-122  
        //汉字  
        //空格 
//	    boolean isGBK = false;
//	    try {
//			isGBK = StringUtil.isGBK(str);
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	    
	    StringBuffer buffer = new StringBuffer();
	    char ch;
	    char[] temp;
	    for (int i = 0; i < str.length(); i++) { //依次处理str中每个字符
	      ch = str.charAt(i);
	      temp = new char[] {ch};
	      try {
		      byte[] uniCode = new String(temp).getBytes("GBK");
		      if (uniCode[0] < 128 && uniCode[0] > 0) { // 非汉字
		        buffer.append(temp);
		        
		      }else{
		    	  char hanzi=convert(uniCode);
		    	  if(hanzi!='-'){
		    		  buffer.append(hanzi);
		    	  }else{
		    		  buffer.append(toPinYin(str.substring(i,i+1)));
		    	  }
		      }  
	      } catch (UnsupportedEncodingException e) {
	    	  e.printStackTrace();
	      }

	    }
	    return buffer.toString();
	  }
	  
	  
	 

	  /** 获取一个汉字的拼音首字母。
	   * GB码两个字节分别减去160，转换成10进制码组合就可以得到区位码
	   * 例如汉字"你"的GB码是0xC4/0xE3，分别减去0xA0（160）就是0x24/0x43
	   * 0x24转成10进制就是36，0x43是67，那么它的区位码就是3667，在对照表中读音为‘n'
	   */

	  private static char convert(byte[] bytes) {

	    char result = '-';
	    int secPosvalue = 0;
	    int i;
	    for (i = 0; i < bytes.length; i++) {
	      bytes[i] -= GB_SP_DIFF;
	    }
	    secPosvalue = bytes[0] * 100 + bytes[1];
	    for (i = 0; i < 23; i++) {
	      if (secPosvalue >= secPosvalueList[i] &&
	          secPosvalue < secPosvalueList[i + 1]) {
	        result = firstLetter[i];
	        break;
	      }
	    }
	    return result;
	  }
	  
	  public static String remove(String source, String key) {
			int i = source.indexOf(key);
			int l = key.length();
			if (i != -1)
				return String.valueOf(source.substring(0, i))
					+ String.valueOf(source.substring(i + l));
			else
				return source;
		}
	  
	  private static String toPinYin(String hanzhis){
//		  hanzhis = remove(hanzhis," ");
		  CharSequence s= hanzhis;

		  char [] hanzhi=new char[s.length()];
		  for(int i=0;i<s.length();i++){
			  hanzhi[i]=s.charAt(i);
		  }

		  char [] t1 =hanzhi;
		  String[] t2 = new String[s.length()];
		  
		  /** *//**
		  * 设置输出格式
		  */
		  
		  net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
		  
		  t3.setCaseType(HanyuPinyinCaseType.UPPERCASE);
		  t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		  t3.setVCharType(HanyuPinyinVCharType.WITH_V);

		  int t0=t1.length;
		  String py = "";
		  
		  try {
			  for (int i=0;i<t0;i++){
				  t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
				  py=py+t2[0].toString();
			  }
		  }
		  catch (BadHanyuPinyinOutputFormatCombination e1) {
			  e1.printStackTrace();
		  }
		  
		return py.trim().toLowerCase().substring(0,1);
		  
	}
	  
	  
	  /** 
	  * 提取每个汉字的首字母 
	  * @param str 
	  * @return String 
	  */ 
	  public static String getPinYinHeadChar(String str)  
	  {   
		  
		    str = SBCchange(str).toLowerCase();
		    str = StringFilter(str);
	    
		    if (str == null || str.trim().length() == 0) {  
	            return "";  
	        } 
	         String convert = "";  
	         for (int j = 0; j < str.length(); j++) {   
	               char word = str.charAt(j);  
	               // 提取汉字的首字母  
	               String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);  
	               if (pinyinArray != null) {  
	                       convert += pinyinArray[0].charAt(0);   
	               } else {  
	                       convert += word;  
	               }  
	          }   
	          return convert;  
	   }    
	  /** 
	  * 将字符串转换成ASCII码 
	  * @param cnStr 
	  * @return String 
	  */ 
	  public static String getCnASCII(String cnStr)  
	  {   
	        StringBuffer strBuf = new StringBuffer();   
	        // 将字符串转换成字节序列   
	        byte[] bGBK = cnStr.getBytes();  
	        for (int i = 0; i < bGBK.length; i++) {  
	             // 将每个字符转换成ASCII码  
	             strBuf.append(Integer.toHexString(bGBK[i] & 0xff));  
	        }  
	        return strBuf.toString();  
	  }      	  
	  
	  
//    全角转半角
	 public static final String SBCchange(String QJstr)
	  {
	      String outStr="";
	      String Tstr="";
	      byte[] b=null;

	      for(int i=0;i<QJstr.length();i++)
	      {     
	       try
	       {
	        Tstr=QJstr.substring(i,i+1);
	        b=Tstr.getBytes("unicode");
	       }
	       catch(java.io.UnsupportedEncodingException e)
	       {
	        e.printStackTrace();
	       }     
	    
	       if (b[3]==-1)
	       {
	        b[2]=(byte)(b[2]+32);
	        b[3]=0;      
	         
	        try
	        {       
	         outStr=outStr+new String(b,"unicode");
	        }
	        catch(java.io.UnsupportedEncodingException e)
	        {
	         e.printStackTrace();
	        }      
	       }else outStr=outStr+Tstr;
	      }
	     
	      return outStr; 
	   }	
	 
//	 半角转全角
	 public static final String BQchange(String QJstr) {
		 String outStr = "";
		 String Tstr = "";
		 byte[] b = null;

		 for (int i = 0; i< QJstr.length(); i++) {
		 try {
		 Tstr = QJstr.substring(i, i + 1);
		 b = Tstr.getBytes("unicode");
		 } catch (java.io.UnsupportedEncodingException e) {
		 e.printStackTrace();
		 }
		 if (b[3] != -1) {
		 b[2] = (byte) (b[2] - 32);
		 b[3] = -1;
		 try {
		 outStr = outStr + new String(b, "unicode");
		 } catch (java.io.UnsupportedEncodingException e) {
		 e.printStackTrace();
		 }
		 } else
		 outStr = outStr + Tstr;
		 }
		 return outStr;
	 }	
	  
	  
	 public   static   final String StringFilter(String    str)   throws    PatternSyntaxException    {     
         // 只允许字母和数字       
         // String    regEx   =   "[^a-zA-Z0-9]";                     
        // 清除掉所有特殊字符  
		 String rep ="\n\r\t"; 
		 str = String2kenizer(str,rep);	
		 
        String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？×《 》Ⅲ]";  
        Pattern    p    =    Pattern.compile(regEx);     
        Matcher    m    =    p.matcher(str);     
       return    m.replaceAll("").trim();     
	 }	  
	 
	 public static String String2kenizer(String s,String rep) {
//		  String rep ="\n\r\t" +"/.*|/?\"\\";
		  StringBuffer   temp = new   StringBuffer();  ;
		  StringTokenizer   token = new   StringTokenizer(s,rep);
		  for(;token.hasMoreTokens();)   temp.append(token.nextToken());  
		  return temp.toString();
	}
	  
}