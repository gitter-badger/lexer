package lexer;


import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JScrollPane;

public class FrameGUI extends JFrame {

	private JPanel contentPane;
	JTextArea textArea, textArea_1;
	private JButton btnNewButton_1;
	private JScrollPane scrollPane, scrollPane_1;
	private JScrollPane scrollPane_2;
	private JScrollPane scrollPane_3;
	private JButton btnNewButton_2;

	String infile = "code.txt";
	String outfile = "out.txt";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameGUI frame = new FrameGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrameGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 857, 589);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("\u8BCD\u6CD5\u5206\u6790");
		btnNewButton.setBounds(10, 502, 93, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String str = textArea.getText();
				table t = new table();

				ArrayList<Node> list = new ArrayList();
				int errorCount = 0;
				// ///
				Deal deal = new Deal();
				list = deal.method(str);

				// /////
				Node node = new Node();
				for (int i = 0; i < list.size(); i++) {

					node = (Node) list.get(i);
					if (node.flag == 1)
						textArea_1.append("(" + node.name + "," + node.values
								+ ")\n");
					else
						textArea_1
								.append(node.name + "->" + node.values + "\n");

				}

				String ni = "char n='\n';";

				System.out.println("³¤¶È" + ni.length());

				// if(ni.charAt(6)=='\n')
				// {
				// System.out.println("\\");
				// }
				// System.out.println(ni.charAt(7));
				// if(ni.charAt(7)=='\n')
				// {
				// System.out.println(ni.charAt(7));
				// }

			}
		});
		contentPane.add(btnNewButton);

		btnNewButton_1 = new JButton("\u4ECE\u6587\u4EF6\u8BFB\u53D6");
		btnNewButton_1.setBounds(223, 502, 93, 23);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				BufferedReader dr = null;

				try {
					FileInputStream f = new FileInputStream(infile);
					dr = new BufferedReader(new InputStreamReader(f));

					BufferedWriter output = new BufferedWriter(new FileWriter(
							outfile));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				String line = "";
				int cnt = 0;
				try {
					while ((line = dr.readLine()) != null) {

						textArea.append(line + "\n");

					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		contentPane.add(btnNewButton_1);

		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(75, 89, 200, 403);
		contentPane.add(scrollPane_2);

		textArea = new JTextArea();
		scrollPane_2.setViewportView(textArea);

		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(420, 89, 400, 403);
		contentPane.add(scrollPane_3);

		textArea_1 = new JTextArea();
		scrollPane_3.setViewportView(textArea_1);
		textArea_1.setLineWrap(true);

		JButton button = new JButton("\u4FDD\u5B58\u5230\u6587\u4EF6");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				BufferedReader dr = null;
				BufferedWriter output=null;

				try {
					FileInputStream f = new FileInputStream(infile);
					dr = new BufferedReader(new InputStreamReader(f));

					output = new BufferedWriter(new FileWriter(
							outfile));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				String line = "";
				int cnt = 0;
				try {
					output.write(textArea_1.getText());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

			}
		});
		button.setBounds(398, 502, 93, 23);
		contentPane.add(button);

		btnNewButton_2 = new JButton("\u91CD\u7F6E");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				textArea.setText("");
				textArea_1.setText("");

			}
		});
		btnNewButton_2.setBounds(615, 502, 93, 23);
		contentPane.add(btnNewButton_2);

	}
}
