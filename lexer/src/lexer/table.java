package lexer;


import java.util.ArrayList;

public class table {

	// 32个
	public static String keywords[] = { "abstract", "Boolean", "reak", "byte",
			"case", "catch", "char", "class", "continue", "default", "do",
			"double", "else", "extends", "false", "final", "finally", "float",
			"for", "if", "implements", "import", "instanceof", "int",
			"interface", "long", "native", "new", "null", "package", "private",
			"protected", "public", "return", "short", "static", "super",
			"switch", "synchronized", "this", "throw", "throws", "transient",
			"true", "try", "void", "volatile", "while", "String" };

	 public static char oper[] = { '+', '-', '*', '=', '<', '>', '&', '|', '~',  
         '^', '!', '(', ')', '[', ']', '{', '}', '%', ';', ',', '#', '.' };
	 
	   public static Boolean isPlusEqu(char ch) // 运算符后可加等于  
	    {  
	        return ch == '+' || ch == '-' || ch == '*'  || ch == '='  
	                || ch == '>' || ch == '<' || ch == '&' || ch == '!'  || ch == '|' 
	                || ch == '^';  
	    }
	   public static Boolean isDelimiter(char ch)
	   {
		   return ch == '(' || ch == ')' || ch == '[' || ch == ']' ||
				   ch == '{' || ch == '}' || ch == ';' || ch == ','||ch=='.';  
	   }
	   
	 
	public static ArrayList<String> symbol = new ArrayList();

	public boolean IsKeyWords(String str) {

		for (int i = 0; i < keywords.length; i++) {

			if (str.equals(keywords[i]))
				return true;

		}

		return false;
	}
	public boolean Isop(char ch) {

		for (int i = 0; i < oper.length; i++) {

			if (ch==oper[i])
				return true;

		}
		return false;
	}
	
	public static Boolean isEsSt(char ch) {  
        return  ch == '\b' || ch == '\f' || ch == '\n' || ch == '\r'  
                || ch == '\t'  || ch == '\0';  
    }  

}
