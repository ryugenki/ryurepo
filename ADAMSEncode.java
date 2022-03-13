import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.HashMap;
import java.util.Map;
import java.io.*;
/*
 * 下記3点の対応を加えました。
 * ① サンプルソースconvertTable中の「Aｱあ亜高」はx-JIS0208またはJIS_X0201に直接に変換できるので、
 *   convertTableに入れなくてもよいかなと思います。convertTableに変換したい文字（髙→高など）だけ入れればよいかと。
 *   ソースから直接に変換できる文字をconvertTableから削除しました。
 * 
 * ② 6桁のUTF-8のバイナリデータを経由してなくても、入力文字（例：亜）から直接にx-JIS0208またはJIS_X0201の
 * 　　バイナリデータ（例：亜→3021）に変換できるので、convertTableのキーはUTF-8のバイナリデータから入力文字に変更しました。
 *   
 * ③ JIS_X0201とx-JIS0208の文字一覧を調べたところ、JIS_X0201にある文字はすべて半角文字で、
 * 　　x-JIS0208にある文字がすべて全角なので、インプットの文字が来たら、JIS_X0201でエンコードするか、それとも
 *   x-JIS0208でエンコードするかと振り分けるためのメソッドisHankakuを用意しました。
 *
 */

public class ADAMSEncode {
	
	public static void main(String[] args) throws Exception {
		CharsetEncoder xjis0208 = Charset.forName("x-JIS0208").newEncoder();
		CharsetEncoder jisx0201 = Charset.forName("JIS_X0201").newEncoder();
		
        Map<String, String> convertTable = new HashMap<String, String>();
        // x-JIS0208文字一覧にない文字だったら、下記①②③のやり方で対応するかなと思います。
        // ①似ている文字に変換（髙→高のように）
        // ②例外を発生させないで、？のような文字に変換
        // ③例外を発生させる
        convertTable.put("Ⅰ", "？");    // Ⅰ(外字)
        convertTable.put("㎡", "■");    // ㎡(外字)
        convertTable.put("髙", "高");    // 髙→高
        
        String inputString = "Aｱあ亜Ⅰ㎡高髙②～";
        String[] inputChars = inputString.split("");
       
        byte[] inputBytes;
        byte[] outputBytes;
        for(int i = 0; i < inputChars.length; i++) {
            System.out.println("■入力文字：" + inputChars[i]);
            inputBytes = inputChars[i].getBytes("utf-8");
            System.out.println("・変換前(UTF-8)：" + ADAMSEncode.encodeHex(inputBytes));
            
            if(isHankaku(inputChars[i]) == true && jisx0201.canEncode(inputChars[i].charAt(0)) == true) {
            	// 半角文字
            	outputBytes = inputChars[i].getBytes("JIS_X0201");
            	System.out.println("・変換後(ADAMS)：" + ADAMSEncode.encodeHex(outputBytes));
            	System.out.println("・出力文字列：" + new String(outputBytes, "JIS_X0201"));
            } else if(isHankaku(inputChars[i]) == false && xjis0208.canEncode(inputChars[i].charAt(0)) == true) {
            	// 全角文字
            	outputBytes = inputChars[i].getBytes("x-JIS0208");
            	System.out.println("・変換後(ADAMS)：" + ADAMSEncode.encodeHex(outputBytes));
            	System.out.println("・出力文字列：" + new String(outputBytes, "x-JIS0208"));
            } else {
            	//変換テーブルから変換
            	if(convertTable.containsKey(inputChars[i])) {
	            	outputBytes = convertTable.get(inputChars[i]).getBytes("x-JIS0208");
	            	System.out.println("・変換後(ADAMS)：" + ADAMSEncode.encodeHex(outputBytes));
	            	System.out.println("・出力文字列：" + convertTable.get(inputChars[i]));
            	}else {
            		//ここで、例外を発生させるか、例外を発生させないで別の文字に変換したいんだったら、convertTableに入れるとかで対応が必要
            		System.out.println("・出力文字列：" + "変換不可！！！");
            	}
            }
            System.out.println("");
        }
    }
	
	// Apache Commons Codec jarを持っていないので、バイナリデータを16進数に変換するロジックを実現
    public static String encodeHex(byte[] b) {
		String rtn="";
        for(int i=0;i<b.length;i++) {	
		    String hex = Integer.toHexString(b[i] & 0xFF);
		       rtn = rtn + hex;
		    }
        return rtn;
	}
    
    // 半角文字だったら、JIS_X0201でのエンコードの処理に入れるように判定する。
    // 戻り値true:JIS_X0201でエンコード
    // 戻り値false:x-JIS0208でエンコード
    public static boolean isHankaku(String s) throws Exception{
    	char c = s.charAt(0);
    	if(s.getBytes("utf-8").length == 1 ||  // 半角英数字、!"#$%&'()*+,-./:;<=>?@[\]^_`{|}~
    	   c >= 0xFF65 && c <= 0xFF9f ||       // ･ ｱ～ﾝ ﾞ ﾟ
    	   c == 'ｰ' || c == '｡' || c == '｢' || c == '｣' || c == '､') {
    		return true;
    	}else {
    		return false;
    	}
    }
}
