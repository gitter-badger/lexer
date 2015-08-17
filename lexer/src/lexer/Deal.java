package lexer;


import java.util.ArrayList;

public class Deal {

	public ArrayList<Node> method(String str) {
		table t = new table();
		ArrayList<Node> list = new ArrayList();
		int errorCount = 0;
		int start = 0;
		for (int cur = 0; cur < str.length(); cur++) {
			System.out.println("长度2" + str.length());
			// start = cur;
			String s = "";
			char ch = str.charAt(cur);
			if (ch == ' ' || ch == '\n' || ch == '\t')
				continue;

			String $;
			// 如果是标示符或者关键字
			if (Character.isLetter(ch) == true | ch == '_' | ch == '$') {

				do {
					s += ch;
					cur++;
					if (cur >= str.length())
						break;
					ch = str.charAt(cur);
				} while (Character.isLetter(ch) | Character.isDigit(ch)
						| ch == '_' | ch == '$');

				if (t.IsKeyWords(s)) {
					Node node = new Node();
					node.flag = 1;
					node.name = "关键字";
					node.values = s;
					list.add(node);

				} else {
					if (!t.symbol.contains(s)) {
						t.symbol.add(s);
						Node node = new Node();
						node.name = "标识符";
						node.values = s;
						node.flag = 1;
						list.add(node);
					} else {
						Node node = new Node();
						node.name = "ERROR:标示符重复";
						node.values = s;
						node.flag = 0;
						errorCount++;
						list.add(node);
					}

				}// end_of_else是关键字
				cur--;// 指针回退
				s = "";
			} // end_of_if是关键字或者标示符

			else if (Character.isDigit(ch))// 判断数字常量
			{
				boolean Isfloat = false;

				do {
					if (ch == '.')
						Isfloat = true;

					s += ch;
					cur++;
					if (cur >= str.length())
						break;
					ch = str.charAt(cur);

				} while (Character.isDigit(ch) | ch == '.');

				Boolean haveMistake = false;

				if (t.Isop(ch) | ch == ' ') {
					if (Isfloat == false) {
						Node node = new Node();
						node.flag = 1;
						node.name = "整数";
						node.values = s;
						list.add(node);
					} else {
						Node node = new Node();
						node.flag = 1;
						node.name = "浮点数";
						node.values = s;
						list.add(node);
					}

				} else {
					while (ch != '\0' && ch != ',' && ch != ';' && ch != ' ') // 一直到“可分割”的字符结束
					{

						s += ch;
						cur++;
						if (cur >= str.length())
							break;
						ch = str.charAt(cur);

					}
					Node node = new Node();
					node.flag = 0;
					node.name = "ERROR：请确保实常数输入正确";
					node.values = s;
					errorCount++;
					list.add(node);

				}
				cur--;// 指针回退
				s = "";
			} else if (ch == '"') // 处理字符串常量的
			{

				do {
					s += ch;
					cur++;
					if (cur >= str.length())
						break;
					ch = str.charAt(cur);

				} while (ch != '"' && ch != ';' && ch != ',');

				if (ch == '"')// 正常结束
				{
					Node node = new Node();
					node.flag = 1;
					node.name = "字符串";
					node.values = node.values = s + "\"";
					list.add(node);
				} else {
					Node node = new Node();
					node.flag = 0;
					node.name = "ERROR:字符串常量引号不封闭";
					node.values = node.values = s;
					list.add(node);
					cur--;
				}

				s = "";

			} else if (ch == '\'') // 处理字符常量的
			{
				cur++;
				char value = str.charAt(cur);
				String va = "字符";
				String na = "";
				va += value;
				if (value == '\\') {
					cur++;
					value = str.charAt(cur);
					va += value;
					if (value == 'n') {
						na = "转义字符";
						va = "\\n";
					} else if (value == 'b') {
						na = "转义字符";
						va = "\\b";
					} else if (value == 'f') {
						na = "转义字符";
						va = "\\f";
					} else if (value == 'r') {
						na = "转义字符";
						va = "\\r";
					} else if (value == 't') {
						na = "转义字符";
						va = "\\t";
					} else if (value == '0') {
						na = "转义字符";
						va = "\\0";
					}
				}

				cur++;
				if (str.charAt(cur) == '\'') // 正常结束
				{
					Node node = new Node();
					node.flag = 1;
					node.name = na;
					node.values = va;
					list.add(node);

				} else {
					Node node = new Node();
					node.flag = 0;
					node.name = "ERROR：字符常量引号不封闭";
					node.values = "\'" + va;
					list.add(node);
					cur--;
				}

			} else if (table.isDelimiter(ch) == true) // 界符
			{
				Node node = new Node();
				node.flag = 1;
				node.name = "界符";
				node.values = "" + ch;
				list.add(node);

			} else if (table.isPlusEqu(ch)) // 运算符
			{
				s += ch;
				char v = str.charAt(++cur);
				if (v == '=') {
					s += v;
				} else if (ch == '+' && v == '+') {
					s += v;
				} else if (ch == '-' && v == '-') {
					s += v;
				} else if (ch == '&' && v == '&') {
					s += v;
				} else if (ch == '|' && v == '|') {
					s += v;
				} else {
					cur--;
				}
				Node node = new Node();
				node.flag = 1;
				node.name = "运算符";
				node.values = s;
				list.add(node);
			} else if (ch == '/') {
				cur++;

				ch = str.charAt(cur);
				if (ch != '/' && ch != '*') // 是除号
				{
					if (ch == '=') // 是 /=
					{
						s = "/=";

					} else {
						s = "/";
						cur--;
					}
					Node node = new Node();
					node.flag = 1;
					node.name = "运算符";
					node.values = s;
					list.add(node);
					s = "";

				} else if (ch == '/') {
					Node node = new Node();
					node.flag = 1;
					node.name = "注释标志";
					node.values = "//";
					list.add(node);

					cur++;
					if (cur >= str.length())
						break;

					while (str.charAt(cur) != '\n') {

						s += str.charAt(cur);
						cur++;
						if (cur >= str.length())
							break;
					}

					Node node2 = new Node();
					node2.flag = 1;
					node2.name = "注释";
					node2.values = s;
					list.add(node2);
					s = "";

				} else if (ch == '*') {
					Node node = new Node();
					node.flag = 1;
					node.name = "注释标志";
					node.values = "/*";
					list.add(node);

					cur++;
					if (cur >= str.length())
						break;
					while (str.charAt(cur) != '*') {

						s += str.charAt(cur);

						cur++;
						if (cur >= str.length())
							break;
					}
					cur++;
					if (cur >= str.length())
						break;

					Node node2 = new Node();
					node2.flag = 1;
					node2.name = "注释";
					node2.values = s;
					list.add(node2);
					s = "";

					if (str.charAt(cur) == '/') // 注释正常结束
					{

						Node node3 = new Node();
						node3.flag = 1;
						node3.name = "注释标志";
						node3.values = "*/";
						list.add(node3);

					} else {

						Node node3 = new Node();
						node3.flag = 0;
						node3.name = "ERROR:注释标志不封闭";
						node3.values = "*/";
						list.add(node3);
						cur--;
					}

				}
			}

		}

		return list;
	}
}
