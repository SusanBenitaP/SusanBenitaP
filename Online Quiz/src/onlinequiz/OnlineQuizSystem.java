package onlinequiz;
import java.util.*;
import java.sql.*;
public class OnlineQuizSystem 
{
	public static void main(String[] args) 
	{
		Scanner input=new Scanner(System.in);
		for(int i=0;i<75;i++)
		{
			System.out.print("> ");
		}
		for(int i=0;i<2;i++)
		{
			System.out.println();
		}
		for(int i=0;i<26;i++)
		{
			System.out.print(" ");
		}
		System.out.println("***************************************** SHOTGUN *****************************************");
		for(int i=0;i<35;i++)
		{
			System.out.print(" ");
		}
		System.out.println("PROJECT DONE BY SHRI VAISHNAVI, SOUPERNIKA, SUSAN BENITA! :)");
		System.out.println();
		for(int i=0;i<75;i++)
		{
			System.out.print("< ");
		}
		System.out.println();
		System.out.println("1.Teacher\n2.Student");
		System.out.print("Who are You :)");
		int num=input.nextInt();
		for(int i=0;i<75;i++)
		{
			System.out.print("* ");
		}
		System.out.println();
		switch(num)
		{
			case 1:
				Teacher t=new Teacher();
				t.TeacherLogin();
				break;
			case 2:
				Student s=new Student();
				s.studentQuiz();
				break;
		}
		input.close();
	}
}
class Teacher
{
	public void TeacherLogin()
	{
		Scanner input=new Scanner(System.in);
		try
		{
			Connection myConnect=DriverManager.getConnection("jdbc:mysql://localhost:3306/TeacherLogin","root","susanroot");
			Statement myStmt=myConnect.createStatement();
			for(int i=0;i<75;i++)
			{
				System.out.print("* ");
			}
			System.out.println();
			System.out.println("1.Already a user");
			System.out.println("2.New User");
			System.out.print("Please,Enter your choice : ");
			int n;
			n=input.nextInt();
			input.nextLine();
			for(int i=0;i<75;i++)
			{
				System.out.print("* ");
			}
			System.out.println();
			if(n==2)
			{
				newUser();
			}
			else if(n==1)
			{
				try
				{
					while(true)
					{
						System.out.print("Enter your name : ");
						String name=input.nextLine();
						System.out.print("Enter your password : ");
						String password=input.nextLine();
						ResultSet rs=myStmt.executeQuery("SELECT password FROM TeacherInfo WHERE name = '"+name+"'");
						if(rs.next())
						{
							String s=rs.getString("password");
							if(s.equals(password))
							{
								System.out.println("You have successfully logged in!!!");
								category();
								break;
							}
							else
							{
								System.out.println("Please check your password");
								for(int i=0;i<75;i++)
								{
									System.out.print("* ");
								}
								System.out.println();
								System.out.println("PLEASE ENTER YOUR INFORMATION AGAIN");
							}
						}
						else
						{
							System.out.println("Check the proper name and password");
							System.out.println("Are you a new user??\n1.Yes\n2.No");
							System.out.print("Please enter your choice : ");
							int num1=input.nextInt();
							input.nextLine();
							input.close();
							if(num1==1)
							{
								newUser();
								break;
							}
						}
					}
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
			}
		}
		catch(Exception esc)
		{
			System.out.println(esc);
		}
	}
	public static void newUser()
	{
		Scanner input=new Scanner(System.in);
		try
		{
			Connection myConnect=DriverManager.getConnection("jdbc:mysql://localhost:3306/TeacherLogin","root","susanroot");
			Statement myStmt=myConnect.createStatement();
			System.out.print("Enter your name: ");
			String name=input.nextLine();
			System.out.print("Enter your password: ");
			String password;
			System.out.println("Enter a strong password that contains a uppercase,lowercase and a digit");
			while(true)
			{
				System.out.print("Enter your password : ");
				password=input.next();
				int flag=0,flag1=0,flag2=0;
				if(password.length()>8)
				{
					for(int i=0;i<password.length();i++)
					{
						if(password.charAt(i)>64 && password.charAt(i)<91)
						{
							flag=1;
						}
						else if(password.charAt(i)>96 && password.charAt(i)<123)
						{
							flag1=1;
						}
						else if(password.charAt(i)>47 && password.charAt(i)<58)
						{
							flag2=1;
						}
					}
					if(flag==1 && flag1==1 && flag2==1)
					{
						break;
					}
					else
					{
						System.out.println("Please enter a strong password");
					}
				}
				else
				{
					System.out.println("Please enter a strong password");
				}
			}
			String str="INSERT INTO TeacherInfo "+" VALUES ('"+name+"', '"+password+"')";
			myStmt.executeUpdate(str);
			category();
			for(int i=0;i<75;i++)
			{
				System.out.print("* ");
			}
			System.out.println();
		
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		input.close();
	}
	static void category()
	{
		Scanner input=new Scanner(System.in);
		System.out.println("1.New quiz \n2.View scores");
		int num=input.nextInt();
		if(num==1)
		{
			newQuiz();
		}
		else if(num==2)
		{
			viewMarks();
		}
		input.close();
	}
	static void viewMarks()
	{
		Scanner input=new Scanner(System.in);
		System.out.print("Enter the id of the test which scores you want to see : ");
		String id=input.next();
		try
		{
			Connection myConnect=DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","susanroot");
			Connection myConnect1=DriverManager.getConnection("jdbc:mysql://localhost:3306/question","root","susanroot");
			Statement myStmt=myConnect.createStatement();
			Statement myStmt1=myConnect1.createStatement();
			ResultSet rs=myStmt.executeQuery("SELECT * FROM studentMark where id='"+id+"'");
			ResultSet rs1=myStmt1.executeQuery("SELECT total FROM questionTable where id='"+id+"'");
			int n=0;
			if(rs1.next())
			{
				n=rs1.getInt("total");
			}
			while(rs.next())
			{
				System.out.println(rs.getString("name")+" scored "+rs.getString("mark")+" out of "+n);
			}
			for(int i=0;i<75;i++)
			{
				System.out.print("* ");
			}
			System.out.println();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	static void newQuiz()
	{
		Scanner input=new Scanner(System.in);
		try
		{
			Connection myConnect=DriverManager.getConnection("jdbc:mysql://localhost:3306/question","root","susanroot");
			Statement myStmt=myConnect.createStatement();
			String id=null;
			while(true)
			{
				System.out.print("Enter a unique id for your test : ");
				id=input.next();
				ResultSet rs=myStmt.executeQuery("SELECT * FROM questionTable where id='"+id+"'");
				if(rs.next())
				{
					System.out.println("Your unique id has already taken .");
				}
				else
				{
					break;
				}
			}
			System.out.print("Enter the number of questions you want to add : ");
			int num=input.nextInt();
			input.nextLine();
			for(int i=1;i<=num;i++)
			{
				System.out.print("Enter the question : ");
				String s=input.nextLine();
				System.out.print("Enter the First option : ");
				String option1=input.nextLine();
				System.out.print("Enter the Second option : ");
				String option2=input.nextLine();
				System.out.print("Enter the Third option : ");
				String option3=input.nextLine();
				System.out.print("Enter the Forth option : ");
				String option4=input.nextLine();
				System.out.print("Enter the answer : ");
				String answer=input.nextLine();
				System.out.print("Enter the answer with the proper explanation : ");
				String explanation=input.nextLine();
				String str="INSERT INTO questionTable "+" VALUES('"+id+"' , '"+i+"' , '"+s+"' , '"+option1+"' , '"+option2+"' , '"+option3+"' , '"+option4+"' , '"+answer+"','"+explanation+"',"+num+")";
				myStmt.executeUpdate(str);
				System.out.print("Question "+i+" added!!");
				System.out.println(); 
			}
			for(int i=0;i<75;i++)
			{
				System.out.print("* ");
			}
			System.out.println();
		}
		catch(Exception e)
		{
			input.close();
		}
	}
}
	
class Student
{
	static void studentQuiz()

	{
		Scanner input=new Scanner(System.in);
		System.out.print("Enter your name : ");
		String name=input.nextLine();
		System.out.print("Enter the id of your test : ");
		String id=input.next();
		input.nextLine();
		try
		{
			Connection myConnect=DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","susanroot");
			Statement myStmt=myConnect.createStatement();
			Connection myConnect1=DriverManager.getConnection("jdbc:mysql://localhost:3306/question","root","susanroot");
			Statement myStmt1=myConnect1.createStatement();
			ResultSet rs=myStmt1.executeQuery("SELECT * from questionTable WHERE id='"+id+"'");
			int count=0;
			while(rs.next())
			{
				System.out.print(rs.getString("qNo")+".");
				System.out.print(rs.getString("question"));
				System.out.println();
				System.out.println("a."+rs.getString("option1"));
				System.out.println("b."+rs.getString("option2"));
				System.out.println("c."+rs.getString("option3"));
				System.out.println("d."+rs.getString("option4"));
				System.out.print("Enter your choice : ");
				String ans=input.nextLine();
				String answer=rs.getString("answer");
				if(ans.equals(answer))
				{
					count++;
				}
			}
			String s="INSERT INTO studentMark "+" VALUES('"+id+"' , '"+name+"' , '"+count+"')";
			myStmt.executeUpdate(s);
			for(int i=0;i<75;i++)
			{
				System.out.print("* ");
			}
			System.out.println();
			ResultSet rs1 = myStmt1.executeQuery("SELECT * from questionTable WHERE id='"+id+"'");
			while(rs1.next())
			{
				System.out.println(rs1.getString("explanation"));
				System.out.println();
			}
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
}