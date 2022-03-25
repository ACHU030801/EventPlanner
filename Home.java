package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;



import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;


public class Home implements ActionListener {

	public static Connection connection=null;
	public static PreparedStatement preparedStatement=null;
	public static ResultSet resultSet=null;
	
	JFrame hf;
	
	
	JFrame f = new JFrame("EVENT MANAGEMENT SYSTEM");
	
	JPasswordField ps;
	
	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();
	JPanel p4 = new JPanel();
	JPanel p5 = new JPanel();
	JPanel tablePanel = new JPanel();
	
	
	JTextField u;
    JTextField p;
    JTextField email;
    JTextField decoration;
    JTextField catering;
    JTextField function;
    JTextField add;
    JTextField mobile;
    
    
    
    
    
    JTextField bi;
    JTextField ui;
    JComboBox<?> et;
    JComboBox<?> lo;
    JTextField dt;
    JTextField ng;
    JTextField d;
    JTextField fo;
    JRadioButton r2;
    JRadioButton r1;
    
    
    JComboBox<?> pt;
    JTextField cn;
    JTextField cv;
    JTextField ed;
    JTextField amt;
    
    
    int x;
    
    JTextField us;
	
    JFrame f1;
    JFrame f2;
    JFrame f3;
    JFrame f4;
    JFrame f5;
	JTextField em;
	JTextField dob;
	JComboBox gr;
	JTextField ad;
	JTextField mob;
    JButton b;
    JLabel l1,l2,l3,l4,l5,l6,l7,l8;
    
    
	JTextField bl;
	JButton gob;

	
 	JButton b1;
 	JButton b2;
 	JButton b3;
 	JButton b4;
 	JButton b5;
 	JButton b6;
 

	public void homepage() {
		hf = new JFrame("Event Management System");
			    
	    hf.setSize(400,400);
        hf.setLocationRelativeTo(null);
        hf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hf.setVisible(true);
        
        hf.setLayout(new BorderLayout());
        JLabel background=new JLabel(new ImageIcon("C:\\Users\\hp\\Desktop\\img.jpg"));
        hf.add(background);
        background.setLayout(new FlowLayout());
        
        
        b1= new JButton("Admin");
        b1.setBackground(Color.pink);
        b1.setToolTipText("Click here to add event");
        background.add(b1);
        
        b2= new JButton("Planner");
        b2.setBackground(Color.pink);
        b2.setToolTipText("Click here to add event");
        background.add(b2);
        
        b3= new JButton("User");
        b3.setBackground(Color.pink);
        b3.setToolTipText("Click here to logout");
        background.add(b3);
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        
        
        hf.setSize(580,580);
        hf.setLocationRelativeTo(null);
        
        
//        b1.addActionListener(this);
//        b3.addActionListener(this);
        
	 	        

	}

	

	public void database()
	{
		
		try {
	        Class.forName("org.postgresql.Driver");
	        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/semproject", "postgres", "postgres");
	     } catch (Exception e) {
	        e.printStackTrace();
	        System.err.println(e.getClass().getName()+": "+e.getMessage());
	     }
	     System.out.println("Opened database successfully"); 
	}
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command=e.getActionCommand();
		String title;
		switch(command)
		{
		case "Admin" :
			        System.out.println("Hy Admin");
			        hf.setVisible(false);
			        title="ADMIN LOGIN";
			        adminfunc(title);
			        break;
		case "User" :
			 hf.setVisible(false);
			System.out.println("Hy User");
			title="USER LOGIN";
			getlogin(title);
			break;
		case "Planner" :
			 hf.setVisible(false);
			System.out.println("Hy Planner");
			title="PLANNER LOGIN";
	        plannerfunc(title);
			break;
			
		case "Login":
			System.out.println("Account created");
			logevent();
			break;
		case "User Request":
			System.out.println("Hy User");
			try {
				makeTable(getUserRequests());
				p4display();

			} catch (Exception e2) {
				System.out.println(e2.getMessage());
				System.out.println("Eroor in accepting!");
			}
			f.repaint();
			break;
		case "Planner Response":
			System.out.println("Hy Planner");
			p5.removeAll();
			try {
				makeTable(getAll());
				f.repaint();
				f.setVisible(true);
			} catch (Exception e2) {
				// TODO: handle exception
				System.out.println("Planner Print error!");
			}

			f.repaint();

			break;
		case "Logout":
			System.out.println("Logout success");
			if(f4!=null)
			{
				f4.setVisible(false);
				f4.dispose();
				
				
			}
			
			System.out.println("Logout done");
			
			p5.removeAll();
			p2.removeAll();
			p4.removeAll();
			f.dispose();
			f.setVisible(false);
			homepage();

			break;

		case "Go":
			System.out.println("Approving all requests!");
			String str = bl.getText();

			System.out.println(str);
			int result = approveAll(Integer.parseInt(str));
			try {
				makeTable(getUserRequests());
			} catch (Exception e1) {
				System.err.println("Table Printing Error!");
			}

			p4display();
			JLabel approvedText = new JLabel(Integer.parseInt(str) + " request approved!");
			if (result > 0) {
				approvedText.setForeground(Color.green);
				p4.add(approvedText, BorderLayout.SOUTH);
				approvedText.setFont(new Font("Arial", Font.BOLD, 16));
			}

			else {
				approvedText.setText("Invalid/Already Approved");
				approvedText.setForeground(Color.red);
				p4.add(approvedText, BorderLayout.SOUTH);
				approvedText.setFont(new Font("Arial", Font.BOLD, 16));
			}

			int delay = 2000;
			ActionListener taskPerformer = new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					approvedText.setVisible(false);
				}
			};
			Timer tick = new Timer(delay, taskPerformer);
			tick.start();

			f.repaint();
			f.setVisible(true);
			break;
			
		case "User Details" :
			 System.out.println("user details displaying");
			 p5.removeAll();
				try {
					makeTable(getuserdet());
					f.repaint();
					f.setVisible(true);
				} catch (Exception e2) {
					// TODO: handle exception
					System.out.println("Planner Print error!");
				}

				f.repaint();
				break;
				
		case "Add Planner" :
			   System.out.println("adding planner");
			   p5.removeAll();
			   getadd();
			   System.out.println("abc");
			   break;
			   
			   
		case "ADD PLANNER" :
			System.out.println(" Addding successfully");
			addpla();
			break;
			
		case "Sign in" :
			System.out.println("signing in");
			loget();
			break;
			
		case "Register Now" :
			System.out.println("registering");
			getregister();
		    break;
		    
		case "Register" :
			System.out.println("register in progress");
			regevent();
			break;
			
		case "Login Now" :
			title="USER LOGIN";
			getlogin(title);
			break;
			
		case "Add Event" :
	        System.out.println("add your event");
	        getaddevent();
	        break;
	        
	    	
		case "ADD" : 
			System.out.println("ADDing");
		    addsuccess();
		    break;
		    
		case "Payment" :
			System.out.println("paying");
			getpayment();
			break;
			
		case  "Pay" :
			System.out.println("payment on progress");
			eventcomplete();
			break;
			
		case "LOGIN" :
			planlog();
			break;
			
		case "View Payment" :
			System.out.println("payment view");
			 p5.removeAll();
				try {
					makeTable(getpayview());
					f.setVisible(true);
				} catch (Exception e2) {
					// TODO: handle exception
					System.out.println("Planner Print error!");
				}

				break;
		
		case "BACK" :
			f4.setVisible(false);
			eventadd();
			break;
			
		}

	 }
	
	
	
	
	
	
	
	
	public void adminfunc(String t)
	{
		System.out.println("hy admin");
	
		f4 = new JFrame("Event Management System");

		JLabel l12 = new JLabel(t);
		l12.setFont(new Font("Times New Roman", Font.BOLD, 30));
		l12.setBounds(220, 30, 420, 35);
		f4.add(l12);

		JLabel l14 = new JLabel("User Name");
		l14.setFont(new Font("Tahoma", Font.PLAIN, 15));
		l14.setBounds(80, 120, 220, 30);
		f4.add(l14);

		JLabel l15 = new JLabel("Password");
		l15.setFont(new Font("Tahoma", Font.PLAIN, 15));
		l15.setBounds(80, 160, 220, 30);
		f4.add(l15);
        x= 4;
		ad = new JTextField();
		ad.setFont(new Font("Tahoma", Font.PLAIN, 13));
		ad.setBounds(200, 125, 200, 25);
		f4.add(ad);
		ad.setColumns(10);

		ps = new JPasswordField(10);
		ps.setFont(new Font("Tahoma", Font.PLAIN, 32));
		ps.setBounds(200, 165, 200, 25);
		f4.add(ps);

		b4 = new JButton("Login");
		b4.setFont(new Font("Tahoma", Font.PLAIN, 22));
		b4.setBackground(Color.pink);
		b4.setBounds(210, 240, 150, 30);
		f4.add(b4);


		f4.setSize(580, 580);
		f4.getContentPane().setBackground(Color.white);
		f4.setLayout(new BorderLayout());
		f4.setLocationRelativeTo(null);
		f4.setVisible(true);
		f4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		b4.addActionListener(this);
	}
	
	
	public void plannerfunc(String t)
	{
		System.out.println("hy planner");
		f4 = new JFrame("Event Management System");
	
		
		
		JLabel l12 = new JLabel(t);
		l12.setFont(new Font("Times New Roman", Font.BOLD, 30));
		l12.setBounds(220, 30, 420, 35);
		f4.add(l12);
	
		JLabel l14 = new JLabel("User Name");
		l14.setFont(new Font("Tahoma", Font.PLAIN, 15));
		l14.setBounds(80, 120, 220, 30);
		f4.add(l14);
	
		JLabel l15 = new JLabel("Password");
		l15.setFont(new Font("Tahoma", Font.PLAIN, 15));
		l15.setBounds(80, 160, 220, 30);
		f4.add(l15);
	
		ad = new JTextField();
		ad.setFont(new Font("Tahoma", Font.PLAIN, 13));
		ad.setBounds(200, 125, 200, 25);
		f4.add(ad);
		ad.setColumns(10);
	
		ps = new JPasswordField(10);
		ps.setFont(new Font("Tahoma", Font.PLAIN, 32));
		ps.setBounds(200, 165, 200, 25);
		f4.add(ps);
	
		b4 = new JButton("LOGIN");
		b4.setFont(new Font("Tahoma", Font.PLAIN, 22));
		b4.setBackground(Color.pink);
		b4.setBounds(210, 240, 150, 30);
		f4.add(b4);
	
	
		f4.setSize(580,580);
		f4.getContentPane().setBackground(Color.white);
		f4.setLayout(new BorderLayout());
		f4.setLocationRelativeTo(null);
		f4.setVisible(true);
		f4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		b4.addActionListener(this);
	}



	public void planlog() {
		System.out.println("loger");

		String name = ad.getText();
		String pwdd = ps.getText();

		try {

			PreparedStatement st = connection
					.prepareStatement("Select user_name,user_password from plannerlogin where user_name=? and user_password=?");

			st.setString(1, name);
			st.setString(2, pwdd);
			ResultSet rs = st.executeQuery();

			System.out.println(rs);

			if (rs.next()) {
				JOptionPane.showMessageDialog(b4, "You have successfully logged in");
				viewpay();
			} else {
				JOptionPane.showMessageDialog(b4, "Wrong Username & Password");
			}
		} catch (SQLException sqlException) {

			sqlException.printStackTrace();
		}
	}
	
	public void viewpay()
	{
		  

		JLabel toplabel = new JLabel("EVENT MANAGEMENT SYSTEM");
        JButton b4 = new JButton("View Payment");
		JButton b3 = new JButton("Logout");
		

		
		
		toplabel.setFont(new Font("Chiller", Font.BOLD, 45));
		toplabel.setForeground(Color.white);

		p1.setBackground(Color.white);
		p2.setBackground(Color.blue);
		p3.setBackground(Color.green);
		p4.setBackground(Color.yellow);

		JLabel background = new JLabel(new ImageIcon("C:\\Users\\hp\\Desktop\\img.jpg"));
		p5.setBackground(Color.white);
		background.setLayout(new FlowLayout());
		background.setBounds(-75, 10, 800, 400);
		p5.add(background);

		p1.setPreferredSize(new Dimension(100, 100));
		p2.setPreferredSize(new Dimension(150, 150));
		p3.setPreferredSize(new Dimension(100, 100));
		p4.setPreferredSize(new Dimension(100, 100));
		p5.setPreferredSize(new Dimension(100, 100));

		p5.setLayout(new BorderLayout());

		f.add(p1, BorderLayout.NORTH);
		f.add(p2, BorderLayout.WEST);
		f.add(p3, BorderLayout.EAST);
		f.add(p4, BorderLayout.SOUTH);
		f.add(p5, BorderLayout.CENTER);
        
		
		Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\hp\\Desktop\\frameIcon.png");
		f.setIconImage(icon);
		
		
		
		f.pack();
		f.setSize(1050, 580);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		p1.add(toplabel, BorderLayout.CENTER);
		
		
		b4.setBounds(10, 100, 140, 30);
		b4.setBackground(Color.cyan);
		p2.add(b4);
	
		b3.setBounds(10, 260, 140, 30);
		b3.setBackground(Color.cyan);
		p2.add(b3);

	
		b3.addActionListener(this);
		b4.addActionListener(this);        
	}
	
	
	
	public void logevent() {
		System.out.println("loger");

		String name = ad.getText();
		String pwdd = ps.getText();

		try {

			PreparedStatement st = connection
					.prepareStatement("Select user_name,user_password from adminlogin where user_name=? and user_password=?");

			st.setString(1, name);
			st.setString(2, pwdd);
			ResultSet rs = st.executeQuery();

			System.out.println(rs);

			if (rs.next()) {
				JOptionPane.showMessageDialog(b4, "You have successfully logged in");
				driver();
			} else {
				JOptionPane.showMessageDialog(b4, "Wrong Username & Password");
			}
		} catch (SQLException sqlException) {

			sqlException.printStackTrace();
		}
	}
	
	public void driver()
	{

	
		JLabel toplabel = new JLabel("EVENT MANAGEMENT SYSTEM");
        JButton b4 = new JButton("User Details");
        JButton b5 = new JButton("Add Planner");
		JButton b1 = new JButton("User Request");
		JButton b2 = new JButton("Planner Response");
		JButton b3 = new JButton("Logout");
		

		
		
		toplabel.setFont(new Font("Chiller", Font.BOLD, 45));
		toplabel.setForeground(Color.white);

		p1.setBackground(Color.white);
		p2.setBackground(Color.white);
		p3.setBackground(Color.white);
		p4.setBackground(Color.white);

		JLabel background = new JLabel(new ImageIcon("C:\\Users\\hp\\Desktop\\img.jpg"));
		p5.setBackground(Color.white);
		background.setLayout(new FlowLayout());
		background.setBounds(-75, 10, 800, 400);
		p5.add(background);

		p1.setPreferredSize(new Dimension(100, 100));
		p2.setPreferredSize(new Dimension(150, 150));
		p3.setPreferredSize(new Dimension(100, 100));
		p4.setPreferredSize(new Dimension(100, 100));
		p5.setPreferredSize(new Dimension(100, 100));

		p5.setLayout(new BorderLayout());

		f.add(p1, BorderLayout.NORTH);
		f.add(p2, BorderLayout.WEST);
		f.add(p3, BorderLayout.EAST);
		f.add(p4, BorderLayout.SOUTH);
		f.add(p5, BorderLayout.CENTER);
        
		
		Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\hp\\Desktop\\frameIcon.png");
		f.setIconImage(icon);
		
		
		
		f.pack();
		f.setSize(1050, 580);
		f.setLocationRelativeTo(null);
        
		f4.setVisible(false);
		f.setVisible(true);
		f4.dispose();

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		p1.add(toplabel, BorderLayout.CENTER);
		
		
		b4.setBounds(10, 100, 140, 30);
		b4.setBackground(Color.cyan);
		p2.add(b4);
		
		b5.setBounds(10, 140, 140, 30);
		b5.setBackground(Color.cyan);
		p2.add(b5);

		b1.setBounds(10, 180, 140, 30);
		b1.setBackground(Color.cyan);
		p2.add(b1);
		b2.setBounds(10, 220, 140, 30);
		b2.setBackground(Color.cyan);
		p2.add(b2);
		b3.setBounds(10, 260, 140, 30);
		b3.setBackground(Color.cyan);
		p2.add(b3);
		
		//f4.dispose();
		f4.setVisible(false);

		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
	}

	public void makeTable(DefaultTableModel dt) {

		
		tablePanel.removeAll();
		p4.removeAll();
		f.repaint();
		try {
			JTable table = new JTable(dt);

			tablePanel.add(new JScrollPane(table));
			table.setPreferredScrollableViewportSize(table.getPreferredSize());
			table.setFillsViewportHeight(true);
			table.setBackground(Color.cyan);
			tablePanel.setBackground(Color.white);

			p5.removeAll();
			p5.add(tablePanel, BorderLayout.CENTER);
			f.remove(p5);
			f.add(p5, BorderLayout.CENTER);
			f.repaint();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Table printing error!");
		}
	}



	public void p4display() {

		JLabel blabel = new JLabel("Enter Booking ID :");
		blabel.setForeground(Color.white);
		p4.add(blabel, BorderLayout.CENTER);

		bl = new JTextField(10);
		bl.setFont(new Font("Tahoma", Font.PLAIN, 13));

		p4.add(bl);

		gob = new JButton("Go");
		p4.add(gob);
		gob.addActionListener(this);

		f.repaint();
		f.setVisible(true);

	}

	
	public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {

		ResultSetMetaData metaData = rs.getMetaData();
		// ColumnNames
		Vector<String> columnNames = new Vector<String>();
		int columnCount = metaData.getColumnCount();
		for (int column = 1; column <= columnCount; column++) {
			columnNames.add(metaData.getColumnName(column));
		}
		// Rows of the table
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		while (rs.next()) {
			Vector<Object> vector = new Vector<Object>();
			for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
				vector.add(rs.getObject(columnIndex));
			}
			data.add(vector);
		}
		return new DefaultTableModel(data, columnNames);
	}

	public static DefaultTableModel getUserRequests() throws SQLException {

		try {
			preparedStatement = null;
			resultSet = null;
			preparedStatement = connection.prepareStatement(
					"select bid,username,userid,type,location,event_date,nog,food,forward from add_events;");
			resultSet = preparedStatement.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return buildTableModel(resultSet);
	}

	public int approveAll(int s) {
		int rs;
		try {
			preparedStatement = connection
					.prepareStatement("update add_events set forward='ACCEPTED' where bid=? and forward='REJECTED';");
			preparedStatement.setInt(1, s);
			rs = preparedStatement.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			rs = -1;
			System.out.println(e.getMessage());
			System.err.println("Approve all error!");
		}
		return rs;
	}

	public static DefaultTableModel getAll() throws SQLException {

		try {
			preparedStatement = null;
			resultSet = null;
			preparedStatement = connection.prepareStatement(
					"select username,userid,type,location,event_date,nog,decor,food,planner_response from plannerdetails;");
			resultSet = preparedStatement.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return buildTableModel(resultSet);
	}
	
	public static DefaultTableModel getuserdet() throws SQLException {

		try {
			preparedStatement = null;
			resultSet = null;
			preparedStatement = connection.prepareStatement(
					"select * from account4;");
			resultSet = preparedStatement.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return buildTableModel(resultSet);
	}
	
	public static DefaultTableModel getpayview() throws SQLException {

		try {
			preparedStatement = null;
			resultSet = null;
			preparedStatement = connection.prepareStatement(
					"select * from view_payment;");
			resultSet = preparedStatement.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return buildTableModel(resultSet);
	}
	
	
	public void getadd() {
		
		 
	        
	     
	    	 u=new JTextField();
	    	 u.setBounds(140,20,150,20);
	    	 l1=new JLabel("Username");
	    	 l1.setBounds(20,20,100,30);
	    	 p=new JTextField();
	    	 p.setBounds(140,60,150,20);
	    	 l2=new JLabel("Password");
	    	 l2.setBounds(20,60,100,30);
	         email=new JTextField();
	         email.setBounds(140,100,150,20);
	         l3=new JLabel("Email");
	         l3.setBounds(20,100,100,30);
	         decoration=new JTextField();
	         decoration.setBounds(140,140,150,20);
	         l4=new JLabel("Decoration");
	         l4.setBounds(20,140,100,30);
	         catering=new JTextField();
	         catering.setBounds(140,180,150,20);
	         l5=new JLabel("Catering");
	         l5.setBounds(20,180,100,30);
	         function=new JTextField();
	         function.setBounds(140,220,150,20);
	         l6=new JLabel("Type of function");
	         l6.setBounds(20,220,100,30);
	         add=new JTextField();
	         add.setBounds(140,260,150,20);
	         l7=new JLabel("Address");
	         l7.setBounds(50,260,100,30);
	         b=new JButton("ADD PLANNER");
	         b.setBounds(400,330,95,30);
	         b.addActionListener(this);
	         p5.add(u);
	         p5.add(p);
	         p5.add(email);
	         p5.add(decoration);
	        p5.add(catering);
	        p5.add(function);
	         p5.add(add);
	  
	         p5.add(l1);
	         p5.add(l2);
	         p5.add(l3);
	       p5.add(l4);
	       p5.add(l5);
	      p5.add(l6);
	         p5.add(l7);
	    
        p4.add(b, BorderLayout.CENTER);
	         
	         f.repaint();
	 		f.setVisible(true);
	         
	}
	
	public void addpla()
	{
		try
		   {
			   String user=u.getText();
			   String pass=p.getText();
			   String email1=email.getText();
			   String de=decoration.getText();
			   String c=catering.getText();
			   String f=function.getText();
			   String a=add.getText();
			   try
			   {
				   
				   PreparedStatement pst=connection.prepareStatement("insert into add_planner11(user_name,user_password,email_id,decoration_cost,catering_cost,type_of_function,address)values(?,?,?,?,?,?,?)");
				   pst.setString(1, user);
				   pst.setString(2, pass);
				   pst.setString(3, email1);
				   pst.setString(4,de);
				   pst.setString(5, c);
				   pst.setString(6, f);
				   pst.setString(7, a);
				   int i=pst.executeUpdate();
				   if(i!=0){
				        System.out.println("added");
				      }
				      else{
				        System.out.println("failed to add");
				      }
			   }
			   catch(SQLException e1)
			   {
				   e1.printStackTrace();
			   }
		   }
		   catch(Exception e1)
		   {
			   e1.printStackTrace();
		   }
		}

	
	
	public void getlogin(String t) {
		System.out.println("log");
		f4 = new JFrame("Event Management System");

		JLabel l12 = new JLabel(t);
		l12.setFont(new Font("Times New Roman", Font.BOLD, 30));
		l12.setBounds(220, 30, 420, 35);
		f4.add(l12);
		
		

		JLabel l14 = new JLabel("User Name");
		l14.setFont(new Font("Tahoma", Font.PLAIN, 15));
		l14.setBounds(80, 120, 220, 30);
		f4.add(l14);

		JLabel l15 = new JLabel("Password");
		l15.setFont(new Font("Tahoma", Font.PLAIN, 15));
		l15.setBounds(80, 160, 220, 30);
		f4.add(l15);

		ad = new JTextField();
		ad.setFont(new Font("Tahoma", Font.PLAIN, 13));
		ad.setBounds(200, 125, 200, 25);
		f4.add(ad);
		ad.setColumns(10);

		ps = new JPasswordField(10);
		ps.setFont(new Font("Tahoma", Font.PLAIN, 32));
		ps.setBounds(200, 165, 200, 25);
		f4.add(ps);

		b4 = new JButton("Sign in");
		b4.setFont(new Font("Tahoma", Font.PLAIN, 22));
		b4.setBackground(Color.pink);
		b4.setBounds(210, 240, 150, 30);
		f4.add(b4);

		JLabel l18 = new JLabel("Don't have an account ?");
		l18.setFont(new Font("Tahoma", Font.PLAIN, 15));
		l18.setBounds(80, 300, 220, 30);
		f4.add(l18);

		b1 = new JButton("Register Now");
		f4.add(b1);
		b1.setBackground(Color.pink);
		b1.setBounds(250, 300, 180, 25);

		b1.addActionListener(this);

		f4.setSize(580, 580);
		f4.getContentPane().setBackground(Color.white);
		f4.setLayout(new BorderLayout());
		f4.setLocationRelativeTo(null);
		f4.setVisible(true);
		f4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		b4.addActionListener(this);
	}

	public void loget() {
		System.out.println("loger");

		String name = ad.getText();
		String pwdd = ps.getText();

		try {

			PreparedStatement st = connection
					.prepareStatement("Select username,password from account4 where username=? and password=?");

			st.setString(1, name);
			st.setString(2, pwdd);
			ResultSet rs = st.executeQuery();

			System.out.println(rs);

			if (rs.next()) {
				JOptionPane.showMessageDialog(b4, "You have successfully logged in");
				eventadd();
			} else {
				JOptionPane.showMessageDialog(b4, "Wrong Username & Password");
			}
		} catch (SQLException sqlException) {

			sqlException.printStackTrace();
		}
	}

	public void getregister() {
		System.out.println("reg");
		JFrame f2 = new JFrame("Event Management System");

		JLabel l2 = new JLabel("New User Register");
		l2.setFont(new Font("Times New Roman", Font.BOLD, 30));
		l2.setBounds(175, 0, 420, 35);
		f2.add(l2);

		JLabel l3 = new JLabel("User Name");
		l3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		l3.setBounds(80, 80, 220, 30);
		f2.add(l3);

		JLabel l4 = new JLabel("Password");
		l4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		l4.setBounds(80, 120, 220, 30);
		f2.add(l4);

		JLabel l5 = new JLabel("Email");
		l5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		l5.setBounds(80, 160, 220, 30);
		f2.add(l5);

		JLabel l6 = new JLabel("Date of Birth (YYYY-MM-DD)");
		l6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		l6.setBounds(80, 200, 220, 30);
		f2.add(l6);

		JLabel l7 = new JLabel("Select Gender");
		l7.setFont(new Font("Tahoma", Font.PLAIN, 15));
		l7.setBounds(80, 240, 220, 30);
		f2.add(l7);

		JLabel l8 = new JLabel("Address");
		l8.setFont(new Font("Tahoma", Font.PLAIN, 15));
		l8.setBounds(80, 280, 220, 30);
		f2.add(l8);

		JLabel l9 = new JLabel("Mobile");
		l9.setFont(new Font("Tahoma", Font.PLAIN, 15));
		l9.setBounds(80, 360, 220, 30);
		f2.add(l9);

		String gen[] = { "Male", "Female", "Others" };

		us = new JTextField();
		us.setFont(new Font("Tahoma", Font.PLAIN, 13));
		us.setBounds(200, 80, 200, 25);
		f2.add(us);
		us.setColumns(10);

		ps = new JPasswordField(10);
		ps.setFont(new Font("Tahoma", Font.PLAIN, 32));
		ps.setBounds(200, 120, 200, 25);
		f2.add(ps);

		em = new JTextField();
		em.setFont(new Font("Tahoma", Font.PLAIN, 13));
		em.setBounds(200, 160, 200, 25);
		f2.add(em);
		em.setColumns(10);

		dob = new JTextField();
		dob.setFont(new Font("Tahoma", Font.PLAIN, 13));
		dob.setBounds(300, 200, 200, 25);
		f2.add(dob);
		dob.setColumns(10);

		gr = new JComboBox(gen);
		gr.setFont(new Font("Tahoma", Font.PLAIN, 13));
		gr.setBounds(200, 240, 200, 25);
		f2.add(gr);

		ad = new JTextField();
		ad.setFont(new Font("Tahoma", Font.PLAIN, 13));
		ad.setBounds(200, 280, 200, 70);
		f2.add(ad);
		ad.setColumns(10);

		mob = new JTextField();
		mob.setFont(new Font("Tahoma", Font.PLAIN, 13));
		mob.setBounds(200, 370, 200, 25);
		f2.add(mob);
		mob.setColumns(10);

		b3 = new JButton("Register");
		b3.setFont(new Font("Tahoma", Font.PLAIN, 22));
		b3.setBackground(Color.cyan);
		b3.setBounds(210, 440, 150, 30);
		f2.add(b3);

		JLabel l17 = new JLabel("Already have an account ?");
		l17.setFont(new Font("Tahoma", Font.PLAIN, 15));
		l17.setBounds(80, 500, 220, 30);
		f2.add(l17);

		b2 = new JButton("Login Now");
		f2.add(b2);
		b2.setBounds(280, 500, 150, 30);
		b2.setBackground(Color.cyan);

		f2.setSize(580, 580);
		f2.getContentPane().setBackground(Color.white);
		f2.setLayout(null);
		f2.setLocationRelativeTo(null);
		f2.setVisible(true);
		f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		b2.addActionListener(this);
		b3.addActionListener(this);
	}

	public void regevent() {
		System.out.println("msg");
		String pwd = ps.getText();
		System.out.println(pwd);
		String msg = "" + us.getText();
		msg += " \n";

		System.out.println(msg);

		try {
			String query = "INSERT INTO account4 values('" + us.getText() + "', '" + pwd + "','" + em.getText() + "','"
					+ dob.getText() + "','" + (String) gr.getSelectedItem() + "','" + ad.getText() + "','"
					+ mob.getText() + "');";

			Statement sta = connection.createStatement();
			sta.executeUpdate(query);

			JOptionPane.showMessageDialog(b3, "Welcome, " + msg + "Your account is sucessfully created");

			JFrame f3 = new JFrame("Event Management System");

			JLabel l10 = new JLabel("Welcome, " + msg + "Please Login to your account To add your Event");
			l10.setFont(new Font("Tahoma", Font.PLAIN, 15));
			l10.setBounds(80, 100, 800, 30);
			f3.add(l10);

			b2 = new JButton("Login Now");
			f3.add(b2);
			b2.setBackground(Color.cyan);
			b2.setBounds(200, 150, 150, 30);

			b2.addActionListener(this);

			f3.setSize(580, 580);
			f3.getContentPane().setBackground(Color.white);
			f3.setLayout(null);
			f3.setLocationRelativeTo(null);
			f3.setVisible(true);
			f3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	
	
	public void eventadd() {
	    f1 = new JFrame("EVENT MANAGEMENT SYSTEM");
	    
	    f1.setSize(400,400);
        f1.setLocationRelativeTo(null);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setVisible(true);
	    
        f1.setLayout(new BorderLayout());
        JLabel background=new JLabel(new ImageIcon("C:\\Users\\hp\\Desktop\\img.jpg"));
        f1.add(background);
        background.setLayout(new FlowLayout());
        
        
        b1= new JButton("Add Event");
        b1.setBackground(Color.white);
        b1.setToolTipText("Click here to add event");
        background.add(b1);
        
        
        b3= new JButton("Logout");
        b3.setBackground(Color.white);
        b3.setToolTipText("Click here to logout");
        background.add(b3);
        
        
        
        f1.setSize(580,580);
        f1.setLocationRelativeTo(null);
        
        
        b1.addActionListener(this);
        b3.addActionListener(this);
        
	 	        

}

	
	
	public void getaddevent()
	{
		f2=new JFrame("EVENT MANAGEMENT SYSTEM");
		
		
		JLabel l1 = new JLabel("Add Event");
		l1.setForeground(Color.white);
		l1.setFont(new Font("Times New Roman", Font.BOLD, 30));
		l1.setBounds(210,20,420, 35);
		f2.add(l1);
		
		
		
	    JLabel l2=new JLabel("User Name");
		l2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		l2.setBounds(80,80,220, 30);
		f2.add(l2);
		
		JLabel l11=new JLabel("User ID");
		l11.setFont(new Font("Tahoma", Font.PLAIN, 15));
		l11.setBounds(80,120,220, 30);
		f2.add(l11);
		
		JLabel l4=new JLabel("Event Type");
		l4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		l4.setBounds(80,160,220, 30);
		f2.add(l4);
		
		JLabel l5=new JLabel("Location");
		l5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		l5.setBounds(80,200,220, 30);
		f2.add(l5);
		
		JLabel l6=new JLabel("Date");
		l6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		l6.setBounds(80,240,220, 30);
		f2.add(l6);
		
		JLabel l7=new JLabel("No of Guest");
		l7.setFont(new Font("Tahoma", Font.PLAIN, 15));
		l7.setBounds(80,280,220, 30);
		f2.add(l7);
		
		JLabel l8=new JLabel("Decoration");
		l8.setFont(new Font("Tahoma", Font.PLAIN, 15));
		l8.setBounds(80,320,220, 30);
		f2.add(l8);
		
		JLabel l9=new JLabel("Lighting");
		l9.setFont(new Font("Tahoma", Font.PLAIN, 15));
		l9.setBounds(80,360,220, 30);
		f2.add(l9);
		
		JLabel l10=new JLabel("Food");
		l10.setFont(new Font("Tahoma", Font.PLAIN, 15));
		l10.setBounds(80,400,220, 30);
		f2.add(l10);
	
		
		
		
	    
	    us = new JTextField();
	    us.setFont(new Font("Tahoma", Font.PLAIN, 13));
	    us.setBounds(200, 80, 200, 25);
	    f2.add(us);
	    us.setColumns(10);
	    
	    ui = new JTextField();
	    ui.setFont(new Font("Tahoma", Font.PLAIN, 13));
	    ui.setBounds(200, 120, 200, 25);
	    f2.add(ui);
	    ui.setColumns(10);
	    
	    String event_type[]={"-----Select-----","Marriage","Reception","Birthday Party","Gettogether","Surprise Event"};
	    
	    et=new JComboBox<Object>(event_type);    
	    et.setFont(new Font("Tahoma", Font.PLAIN, 13));
	    et.setBackground(Color.cyan);
	    et.setBounds(200, 160,200,25);    
	    f2.add(et);  
		
        String Loc[]={"-----Select-----","Thiruvananthapuram","Kollam","Alappuzha","Pathanamthitta","Thrissur","Ernakulam"};
	    
	    lo=new JComboBox<Object>(Loc);    
	    lo.setFont(new Font("Tahoma", Font.PLAIN, 13));
	    lo.setBackground(Color.cyan);
	    lo.setBounds(200, 200,200,25);    
	    f2.add(lo);  
	    
	    dt = new JTextField();
	    dt.setFont(new Font("Tahoma", Font.PLAIN, 13));
	    dt.setBounds(200, 240, 200, 25);
	    f2.add(dt);
	    dt.setColumns(10);
	
	    
	    
	    JLabel date = new JLabel("(YYYY-MM-DD)");
	    date.setFont(new Font("Tahoma", Font.PLAIN, 10));
	    date.setForeground(Color.red);
		date.setBounds(200,260,220, 30);
		f2.add(date);
		
		 ng= new JTextField();
		 ng.setFont(new Font("Tahoma", Font.PLAIN, 13));
		 ng.setBounds(200, 284, 200, 25);
		 f2.add(ng);
		 ng.setColumns(10);
		 
		 
		 
		 d= new JTextField();
		 d.setFont(new Font("Tahoma", Font.PLAIN, 13));
		 d.setBounds(200, 320, 200, 25);
		 f2.add(d);
		 d.setColumns(10);
		  
	     
		
		 
		  r1=new JRadioButton("Yes");   
		  r1.setBackground(Color.cyan);
		 r2=new JRadioButton("No"); 
		 r2.setBackground(Color.cyan);
		 r1.setBounds(200,360,100,30);    
 		 r2.setBounds(320,360,100,30);    
		 ButtonGroup bg=new ButtonGroup();    
		 bg.add(r1);
		 bg.add(r2);    
		 f2.add(r1);
         f2.add(r2);   
		 
		 fo= new JTextField();
		 fo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		 fo.setBounds(200, 400, 200, 25);
		 f2.add(fo);
		 fo.setColumns(10);
		  
		 
		 
		 b4=new JButton("ADD");
		 b4.setBounds(220, 470, 100, 25);
		 b4.setToolTipText("Click here to add your event");
		 b4.setBackground(Color.cyan);
		 f2.add(b4);
		
	
		
		
		f2.setSize(580,580); 
		f2.getContentPane().setBackground(Color.white);
	    f2.setLayout(null);  
	    f2.setLocationRelativeTo(null);
	    f1.setVisible(false);
	    f2.setVisible(true); 
	    f1.dispose();
	    f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    
	    b4.addActionListener(this);
	}
	
	
	
	public void addsuccess()
	{
		f3=new JFrame("EVENT MANAGEMENT SYSTEM");
		String usnm = "" + us.getText();
	    usnm += " \n";
	    
	    
		JLabel l12 = new JLabel("Dear "+usnm+",  ");
		l12.setFont(new Font("Tahoma", Font.PLAIN, 15));
		l12.setBounds(30, 50, 400, 30);
		f3.add(l12);
		
		JLabel l13 = new JLabel("To Successfully Register your event");
		l13.setFont(new Font("Tahoma", Font.PLAIN, 15));
		l13.setBounds(30, 70, 400, 30);
		f3.add(l13);
		
		JLabel l14 = new JLabel("please complete the payment process...");
		l14.setFont(new Font("Tahoma", Font.PLAIN, 15));
		l14.setBounds(30, 90, 400, 30);
		f3.add(l14);
		
		b5=new JButton("Payment");
		b5.setBounds(90, 130, 100, 25);
	    b5.setToolTipText("Click here to go to payment screen");
		b5.setBackground(Color.cyan);
	    f3.add(b5);
	    
	    
	    
	    x=3;
	    
	    
	    
	    
	    f3.setSize(580,580); 
		f3.getContentPane().setBackground(Color.white);
	    f3.setLayout(null);  
	    f3.setLocationRelativeTo(null);
	    f2.setVisible(false);
	    f3.setVisible(true); 
	    f2.dispose();
	    f3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    
	    b5.addActionListener(this);
	}
	
	
	public void getpayment() {
		f4=new JFrame("EVENT MANAGEMENT SYSTEM");
		
		JLabel l15 = new JLabel("Payment Details");
		l15.setFont(new Font("Tahoma", Font.PLAIN, 30));
		l15.setForeground(Color.white);
		l15.setBounds(170,20,420, 35);
		f4.add(l15);
		
		JLabel l16 = new JLabel("Payment Type");
		l16.setFont(new Font("Tahoma", Font.PLAIN, 15));
		l16.setBounds(80,80,220, 30);
		f4.add(l16);
		
		JLabel l17 = new JLabel("Card No");
		l17.setFont(new Font("Tahoma", Font.PLAIN, 15));
		l17.setBounds(80,120,220, 30);
		f4.add(l17);
		
		JLabel l18 = new JLabel("CVV");
		l18.setFont(new Font("Tahoma", Font.PLAIN, 15));
		l18.setBounds(80,160,220, 30);
		f4.add(l18);
		
		JLabel l19 = new JLabel("Expiry date");
		l19.setFont(new Font("Tahoma", Font.PLAIN, 15));
		l19.setBounds(80,200,220, 30);
		f4.add(l19);
		
		JLabel l20 = new JLabel("Amount");
		l20.setFont(new Font("Tahoma", Font.PLAIN, 15));
		l20.setBounds(80,240,220, 30);
		f4.add(l20);
		
		
		
		
		 String paytyp[]={"-----Select-----","Debit Card","Credit Card"};
		    
		    pt=new JComboBox<Object>(paytyp);    
		    pt.setFont(new Font("Tahoma", Font.PLAIN, 13));
		    pt.setBackground(Color.cyan);
		    pt.setBounds(200, 80,200,25);    
		    f4.add(pt); 
		    
		    cn= new JTextField();
			 cn.setFont(new Font("Tahoma", Font.PLAIN, 13));
			 cn.setBounds(200, 120, 200, 25);
			 f4.add(cn);
			 cn.setColumns(15);
			 
			 
			 cv= new JTextField();
			 cv.setFont(new Font("Tahoma", Font.PLAIN, 13));
			 cv.setBounds(200, 160, 200, 25);
			 f4.add(cv);
			 cv.setColumns(3);
			 
			 
			 ed= new JTextField();
			 ed.setFont(new Font("Tahoma", Font.PLAIN, 13));
			 ed.setBounds(200, 200, 200, 25);
			 f4.add(ed);
			 ed.setColumns(5);
			 
			 amt= new JTextField();
			 amt.setFont(new Font("Tahoma", Font.PLAIN, 13));
			 amt.setBounds(200, 240, 200, 25);
			 f4.add(amt);
			 amt.setColumns(10);
			 
			 
			 
			 
			 b6= new JButton("Pay");
			 b6.setBounds(240,280, 100, 25);
			 b6.setToolTipText("Click here to pay");
	         b6.setBackground(Color.cyan);
			 f4.add(b6);
			 
			 JButton b7= new JButton("BACK");
			    b7.setBounds(450, 20, 100, 25);
			    b7.setToolTipText("Click here to go back");
				b7.setBackground(Color.cyan);
			    f4.add(b7);
		 
			    
			    
			    x=4;
			 
			 
			 f4.setSize(580,580); 
				f4.getContentPane().setBackground(Color.white);
			    f4.setLayout(null);  
			    f4.setLocationRelativeTo(null);
			    f3.setVisible(false);
			    f4.setVisible(true); 
			    f3.dispose();
			    f4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			    
			    
			    b6.addActionListener(this);
			    			    b7.addActionListener(this);

	}
	
	
	public void eventcomplete()
	{
		String light="";
		System.out.println("event complete");
	    String nm = "" + us.getText();
	    nm += " \n";
	    
	    if(r1.isSelected()){
	    	light= "Yes";
	    	}
	    	else if(r2.isSelected()){
	    		light= "No";
	    	}
	    
	   System.out.println(nm);

	    try {
	        String query = "INSERT INTO add_events (username,userid,type,location ,event_date,nog,decor,light,food,pt,cn,cv,ed,amt) values('" +us.getText()+"', '"+ui.getText()+"','"+(String)et.getSelectedItem()+"','"+(String)lo.getSelectedItem()+"','"+dt.getText()+"','"+ng.getText()+"','"+d.getText()+"','"+light+"','"+fo.getText()+"','"+(String)pt.getSelectedItem()+"','"+cn.getText()+"','"+cv.getText()+"','"+ed.getText()+"','"+amt.getText()+"');"; 

	        Statement sta = connection.createStatement();
	        sta.executeUpdate(query);

	        
	        JOptionPane.showMessageDialog(b6,"Hy, " + nm + "Your event is successfully registered");    

	    } catch (Exception exception) {
	        exception.printStackTrace();
	    }
	}
	
	
	
	
	
	public static void main(String[] args)
	{
		Home home = new Home();
		home.database();
		home.homepage();
		
	}
	
}
