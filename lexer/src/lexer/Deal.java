package lexer;


import java.util.ArrayList;

public class Deal {

	public ArrayList<Node> method(String str) {
		table t = new table();
		ArrayList<Node> list = new ArrayList();
		int errorCount = 0;
		int start = 0;
		for (int cur = 0; cur < str.length(); cur++) {
			System.out.println("����2" + str.length());
			// start = cur;
			String s = "";
			char ch = str.charAt(cur);
			if (ch == ' ' || ch == '\n' || ch == '\t')
				continue;

			String $;
			// ����Ǳ�ʾ�����߹ؼ���
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
					node.name = "�ؼ���";
					node.values = s;
					list.add(node);

				} else {
					if (!t.symbol.contains(s)) {
						t.symbol.add(s);
						Node node = new Node();
						node.name = "��ʶ��";
						node.values = s;
						node.flag = 1;
						list.add(node);
					} else {
						Node node = new Node();
						node.name = "ERROR:��ʾ���ظ�";
						node.values = s;
						node.flag = 0;
						errorCount++;
						list.add(node);
					}

				}// end_of_else�ǹؼ���
				cur--;// ָ�����
				s = "";
			} // end_of_if�ǹؼ��ֻ��߱�ʾ��

			else if (Character.isDigit(ch))// �ж����ֳ���
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
						node.name = "����";
						node.values = s;
						list.add(node);
					} else {
						Node node = new Node();
						node.flag = 1;
						node.name = "������";
						node.values = s;
						list.add(node);
					}

				} else {
					while (ch != '\0' && ch != ',' && ch != ';' && ch != ' ') // һֱ�����ɷָ���ַ�����
					{

						s += ch;
						cur++;
						if (cur >= str.length())
							break;
						ch = str.charAt(cur);

					}
					Node node = new Node();
					node.flag = 0;
					node.name = "ERROR����ȷ��ʵ����������ȷ";
					node.values = s;
					errorCount++;
					list.add(node);

				}
				cur--;// ָ�����
				s = "";
			} else if (ch == '"') // �����ַ���������
			{

				do {
					s += ch;
					cur++;
					if (cur >= str.length())
						break;
					ch = str.charAt(cur);

				} while (ch != '"' && ch != ';' && ch != ',');

				if (ch == '"')// ��������
				{
					Node node = new Node();
					node.flag = 1;
					node.name = "�ַ���";
					node.values = node.values = s + "\"";
					list.add(node);
				} else {
					Node node = new Node();
					node.flag = 0;
					node.name = "ERROR:�ַ����������Ų����";
					node.values = node.values = s;
					list.add(node);
					cur--;
				}

				s = "";

			} else if (ch == '\'') // �����ַ�������
			{
				cur++;
				char value = str.charAt(cur);
				String va = "�ַ�";
				String na = "";
				va += value;
				if (value == '\\') {
					cur++;
					value = str.charAt(cur);
					va += value;
					if (value == 'n') {
						na = "ת���ַ�";
						va = "\\n";
					} else if (value == 'b') {
						na = "ת���ַ�";
						va = "\\b";
					} else if (value == 'f') {
						na = "ת���ַ�";
						va = "\\f";
					} else if (value == 'r') {
						na = "ת���ַ�";
						va = "\\r";
					} else if (value == 't') {
						na = "ת���ַ�";
						va = "\\t";
					} else if (value == '0') {
						na = "ת���ַ�";
						va = "\\0";
					}
				}

				cur++;
				if (str.charAt(cur) == '\'') // ��������
				{
					Node node = new Node();
					node.flag = 1;
					node.name = na;
					node.values = va;
					list.add(node);

				} else {
					Node node = new Node();
					node.flag = 0;
					node.name = "ERROR���ַ��������Ų����";
					node.values = "\'" + va;
					list.add(node);
					cur--;
				}

			} else if (table.isDelimiter(ch) == true) // ���
			{
				Node node = new Node();
				node.flag = 1;
				node.name = "���";
				node.values = "" + ch;
				list.add(node);

			} else if (table.isPlusEqu(ch)) // �����
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
				node.name = "�����";
				node.values = s;
				list.add(node);
			} else if (ch == '/') {
				cur++;

				ch = str.charAt(cur);
				if (ch != '/' && ch != '*') // �ǳ���
				{
					if (ch == '=') // �� /=
					{
						s = "/=";

					} else {
						s = "/";
						cur--;
					}
					Node node = new Node();
					node.flag = 1;
					node.name = "�����";
					node.values = s;
					list.add(node);
					s = "";

				} else if (ch == '/') {
					Node node = new Node();
					node.flag = 1;
					node.name = "ע�ͱ�־";
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
					node2.name = "ע��";
					node2.values = s;
					list.add(node2);
					s = "";

				} else if (ch == '*') {
					Node node = new Node();
					node.flag = 1;
					node.name = "ע�ͱ�־";
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
					node2.name = "ע��";
					node2.values = s;
					list.add(node2);
					s = "";

					if (str.charAt(cur) == '/') // ע����������
					{

						Node node3 = new Node();
						node3.flag = 1;
						node3.name = "ע�ͱ�־";
						node3.values = "*/";
						list.add(node3);

					} else {

						Node node3 = new Node();
						node3.flag = 0;
						node3.name = "ERROR:ע�ͱ�־�����";
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
