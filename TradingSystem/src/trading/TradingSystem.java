package trading;
import java.sql.*;
import java.util.Scanner;
public class TradingSystem 
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
		System.out.println("WELCOME TO TRADING MANAGEMENT SYSTEM!! PROJECT DONE BY SHRI VAISHNAVI, SOUPERNIKA, SUSAN BENITA! :)");
		System.out.println();
		for(int i=0;i<75;i++)
		{
			System.out.print("< ");
		}
		System.out.println();
		System.out.println("1.Customer\n2.Seller\n3.Admin");
		System.out.print("Please enter your category : ");
		int num=input.nextInt();
		switch(num)
		{
		case 1:
			Customer c=new Customer();
			c.CustomerLogin();
			break;
		case 2:
			Seller s=new Seller();
			s.SellerLogin();
			break;
		case 3:
			Admin a=new Admin();
			a.AdminLogin();
			break;
		}
		
		input.close();
	}
}
class Customer
{
	public void CustomerLogin()
	{
		Scanner input=new Scanner(System.in);
		try
		{
			Connection myConnect=DriverManager.getConnection("jdbc:mysql://localhost:3306/customerLogin","root","susanroot");
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
						ResultSet rs=myStmt.executeQuery("SELECT password FROM CustomerInfo WHERE name = '"+name+"'");
						if(rs.next())
						{
							String s=rs.getString("password");
							if(s.equals(password))
							{
								System.out.println("You have successfully logged in!!!");
								category(name);
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
							int num1=input.nextInt();
							input.nextLine();
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
					System.out.println("User not found"+" "+e);
				}
			}
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public void newUser()
	{
		Scanner input=new Scanner(System.in);
		for(int i=0;i<31;i++)
		{
			System.out.print("* ");
		}
		System.out.print("Please,Enter your details ");
		for(int i=0;i<31;i++)
		{
			System.out.print("* ");
		}
		System.out.println();
		try
		{
			Connection myConnect=DriverManager.getConnection("jdbc:mysql://localhost:3306/customerLogin","root","susanroot");
			Statement myStmt=myConnect.createStatement();
			System.out.print("Enter your name : ");
			String name=input.nextLine();
			String email;
			while(true)
			{
				System.out.print("Enter your email : ");
				email=input.next();
				if(email.contains("@") && email.contains(".com"))
				{
					break;
				}
				else
				{
					System.out.print("Please enter a valid email");
				}
			}	
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
			
			System.out.print("Enter your phone number : ");
			String phNo;
			while(true)
			{
				phNo=input.next();
				if(phNo.length()==10)
				{
					break;
				}
				else
				{
					System.out.print("Your phone number should be of 10 digits");
				}
			}
			String str="INSERT INTO CustomerInfo "+" VALUES ('"+name+"', '"+email+"', '"+password+"', '"+phNo+"')";
			myStmt.executeUpdate(str);
			ResultSet myRs=myStmt.executeQuery("select * from CustomerInfo where name='"+name+"'");
			while(myRs.next())
			{
				for(int i=0;i<75;i++)
				{
					System.out.print("* ");
				}
				System.out.println("\nThe Name is "+myRs.getString("name")+"\nThe Email-id is "+myRs.getString("email")+"\nThe phone number is "+myRs.getString("phNo"));
			}
			category(name);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	static void category(String name)
	{
		Scanner input=new Scanner(System.in);
		for(int i=0;i<31;i++)
		{
			System.out.print("* ");
		}
		System.out.print("Welcome "+name+" !!!");
		for(int i=0;i<31;i++)
		{
			System.out.print("* ");
		}
		System.out.println();
		System.out.println("1.Language \n2.Science and Technology \n3.Magazines");
		System.out.print("Enter your choice : ");
		int num=input.nextInt();
		switch(num)
		{
		case 1:
			{
				language(name,"language");
				break;
			}
		case 2:
			{
				Science_and_Technology(name,"Science and Technology");
				break;
			}
		case 3:
			{
				magazine(name,"magazine");
				break;
			}
		}
	}
	static void language(String name,String category1)
	{
		Scanner input=new Scanner(System.in);
		try
		{
			Connection myConnect=DriverManager.getConnection("jdbc:mysql://localhost:3306/books","root","susanroot");
			Statement myStmt=myConnect.createStatement();
			System.out.println("1.Novels \n2.Grammar \n3.Biographies \n4.Story Books");
			System.out.print("Enter the category which you desire to buy : ");
			int num=input.nextInt();
			switch(num)
			{
			case 1:
				{
					String str="SELECT * FROM bookShelf WHERE subCategory='Novels'";
					ResultSet rs=myStmt.executeQuery(str);
					if(rs.next())
					{
						System.out.println("ID of the book is "+rs.getString("id")+"\nThe name of the book is "+rs.getString("bookName")+"\nThe price of the book is "+rs.getString("price")+"\nThe availability of the book is "+rs.getString("stock")+"\nThe shop name is "+rs.getString("shopName"));
						for(int i=0;i<75;i++)
						{
							System.out.print("- ");
						}
						System.out.println();
						while(rs.next())
						{
							System.out.println("ID of the book is "+rs.getString("id")+"\nThe name of the book is "+rs.getString("bookName")+"\nThe price of the book is "+rs.getString("price")+"\nThe availability of the book is "+rs.getString("stock")+"\nThe shop name is "+rs.getString("shopName"));
							for(int i=0;i<75;i++)
							{
								System.out.print("- ");
							}
							System.out.println();
						}
						order(name);
					}
					else
					{
						System.out.println("No books available");
						for(int i=0;i<75;i++)
						{
							System.out.print("* ");
						}
						System.out.println();
						System.out.println("Want to try some other books?");
						System.out.println("1.Yes\n2.No");
						int n=input.nextInt();
						if(n==1)
						{
							category(name);
						}
						else
						{
							System.out.println("*****Visit again soon*****");
						}
						
					}
					break;
				}
			case 2:
			{
				String str="SELECT * FROM bookShelf WHERE subCategory='Grammar'";
				ResultSet rs=myStmt.executeQuery(str);
				if(rs.next())
				{
					System.out.println("ID of the book is "+rs.getString("id")+"\nThe name of the book is "+rs.getString("bookName")+"\nThe price of the book is "+rs.getString("price")+"\nThe availability of the book is "+rs.getString("stock"));
					for(int i=0;i<75;i++)
					{
						System.out.print("- ");
					}
					System.out.println();
					while(rs.next())
					{
						System.out.println("ID of the book is "+rs.getString("id")+"\nThe name of the book is "+rs.getString("bookName")+"\nThe price of the book is "+rs.getString("price")+"\nThe availability of the book is "+rs.getString("stock"));
						for(int i=0;i<75;i++)
						{
							System.out.print("- ");
						}
						System.out.println();
					}
					order(name);
				}
				else
				{
					System.out.println("No books available");
					for(int i=0;i<75;i++)
					{
						System.out.print("* ");
					}
					System.out.println();
					System.out.println("Want to try some other books???\n1.Yes\n2.No");
					int n=input.nextInt();
					if(n==1)
					{
						String name1=name;
						category(name1);
					}
				}
				break;
			}
			case 3:
			{
				String str="SELECT * FROM bookShelf WHERE subCategory='Biographies'";
				ResultSet rs=myStmt.executeQuery(str);
				if(rs.next())
				{
					System.out.println("ID of the book is "+rs.getString("id")+"\nThe name of the book is "+rs.getString("bookName")+"\nThe price of the book is "+rs.getString("price")+"\nThe availability of the book is "+rs.getString("stock"));
					for(int i=0;i<75;i++)
					{
						System.out.print("- ");
					}
					System.out.println();
					while(rs.next())
					{
						System.out.println("ID of the book is "+rs.getString("id")+"\nThe name of the book is "+rs.getString("bookName")+"\nThe price of the book is "+rs.getString("price")+"\nThe availability of the book is "+rs.getString("stock"));
						for(int i=0;i<75;i++)
						{
							System.out.print("- ");
						}
						System.out.println();
					}
					order(name);
				}
				else
				{
					System.out.println("No books available");
					for(int i=0;i<75;i++)
					{
						System.out.print("* ");
					}
					System.out.println();
					System.out.println("Want to try some other books???\n1.Yes\n2.No");
					int n=input.nextInt();
					if(n==1)
					{
						String name1=name;
						category(name1);
					}
				}
				break;
			}
			case 4:
			{
				String str="SELECT * FROM bookShelf WHERE subCategory='Story Books'";
				ResultSet rs=myStmt.executeQuery(str);
				if(rs.next())
				{
					System.out.println("ID of the book is "+rs.getString("id")+"\nThe name of the book is "+rs.getString("bookName")+"\nThe price of the book is "+rs.getString("price")+"\nThe availability of the book is "+rs.getString("stock"));
					for(int i=0;i<75;i++)
					{
						System.out.print("- ");
					}
					System.out.println();
					while(rs.next())
					{
						System.out.println("ID of the book is "+rs.getString("id")+"\nThe name of the book is "+rs.getString("bookName")+"\nThe price of the book is "+rs.getString("price")+"\nThe availability of the book is "+rs.getString("stock"));
						for(int i=0;i<75;i++)
						{
							System.out.print("- ");
						}
						System.out.println();
					}
					order(name);
				}
				else
				{
					System.out.println("No books available");
					for(int i=0;i<75;i++)
					{
						System.out.print("* ");
					}
					System.out.println();
					System.out.println("Want to try some other books???\n1.Yes\n2.No");
					int n=input.nextInt();
					if(n==1)
					{
						String name1=name;
						category(name1);
					}
				}
				break;
			}
					
			}
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
		
	}
	static void Science_and_Technology(String name,String category1)
	{
		Scanner input=new Scanner(System.in);
		try
		{
			Connection myConnect=DriverManager.getConnection("jdbc:mysql://localhost:3306/books","root","susanroot");
			Statement myStmt=myConnect.createStatement();
			System.out.println("1.AI and AR \n2.Natural Philosophy \n3.Biology \n4.Agriculture");
			System.out.print("Enter the category of book you desire to buy : ");
			int num=input.nextInt();
			switch(num)
			{
			case 1:
				{
					String str="SELECT * FROM bookShelf WHERE subCategory='AI and AR'";
					ResultSet rs=myStmt.executeQuery(str);
					if(rs.next())
					{
						System.out.println("ID of the book is "+rs.getString("id")+"\nThe name of the book is "+rs.getString("bookName")+"\nThe price of the book is "+rs.getString("price")+"\nThe availability of the book is "+rs.getString("stock")+"\nThe shop name is "+rs.getString("shopName"));
						for(int i=0;i<75;i++)
						{
							System.out.print("- ");
						}
						System.out.println();
						while(rs.next())
						{
							System.out.println("ID of the book is "+rs.getString("id")+"\nThe name of the book is "+rs.getString("bookName")+"\nThe price of the book is "+rs.getString("price")+"\nThe availability of the book is "+rs.getString("stock")+"\nThe shop name is "+rs.getString("shopName"));
							for(int i=0;i<75;i++)
							{
								System.out.print("- ");
							}
							System.out.println();
						}
						order(name);
					}
					else
					{
						System.out.println("No books available");
						for(int i=0;i<75;i++)
						{
							System.out.print("* ");
						}
						System.out.println();
						System.out.println("Want to try some other books?");
						System.out.println("1.Yes\n2.No");
						int n=input.nextInt();
						if(n==1)
						{
							category(name);
						}
						else
						{
							System.out.println("*****Visit again soon*****");
						}
					}
					break;
				}
			case 2:
			{
				String str="SELECT * FROM bookShelf WHERE subCategory='Natural Philosophy'";
				ResultSet rs=myStmt.executeQuery(str);
				if(rs.next())
				{
					System.out.println("ID of the book is "+rs.getString("id")+"\nThe name of the book is "+rs.getString("bookName")+"\nThe price of the book is "+rs.getString("price")+"\nThe availability of the book is "+rs.getString("stock"));
					for(int i=0;i<75;i++)
					{
						System.out.print("- ");
					}
					System.out.println();
					while(rs.next())
					{
						System.out.println("ID of the book is "+rs.getString("id")+"\nThe name of the book is "+rs.getString("bookName")+"\nThe price of the book is "+rs.getString("price")+"\nThe availability of the book is "+rs.getString("stock"));
						for(int i=0;i<75;i++)
						{
							System.out.print("- ");
						}
						System.out.println();
					}
					order(name);
				}
				else
				{
					System.out.println("No books available");
					for(int i=0;i<75;i++)
					{
						System.out.print("* ");
					}
					System.out.println();
					System.out.println("Want to try some other books?");
					System.out.println("1.Yes\n2.No");
					int n=input.nextInt();
					if(n==1)
					{
						category(name);
					}
					else
					{
						System.out.println("*****Visit again soon*****");
					}
				}
				break;
			}
			case 3:
			{
				String str="SELECT * FROM bookShelf WHERE subCategory='Biology'";
				ResultSet rs=myStmt.executeQuery(str);
				if(rs.next())
				{
					System.out.println("ID of the book is "+rs.getString("id")+"\nThe name of the book is "+rs.getString("bookName")+"\nThe price of the book is "+rs.getString("price")+"\nThe availability of the book is "+rs.getString("stock"));
					for(int i=0;i<75;i++)
					{
						System.out.print("- ");
					}
					System.out.println();
					while(rs.next())
					{
						System.out.println("ID of the book is "+rs.getString("id")+"\nThe name of the book is "+rs.getString("bookName")+"\nThe price of the book is "+rs.getString("price")+"\nThe availability of the book is "+rs.getString("stock"));
						for(int i=0;i<75;i++)
						{
							System.out.print("- ");
						}
						System.out.println();
					}
					order(name);
				}
				else
				{
					System.out.println("No books available");
					for(int i=0;i<75;i++)
					{
						System.out.print("* ");
					}
					System.out.println();
					System.out.println("Want to try some other books?");
					System.out.println("1.Yes\n2.No");
					int n=input.nextInt();
					if(n==1)
					{
						category(name);
					}
					else
					{
						System.out.println("*****Visit again soon*****");
					}
				}
				break;
			}
			case 4:
			{
				String str="SELECT * FROM bookShelf WHERE subCategory='Agriculture'";
				ResultSet rs=myStmt.executeQuery(str);
				if(rs.next())
				{
					System.out.println("ID of the book is "+rs.getString("id")+"\nThe name of the book is "+rs.getString("bookName")+"\nThe price of the book is "+rs.getString("price")+"\nThe availability of the book is "+rs.getString("stock"));
					for(int i=0;i<75;i++)
					{
						System.out.print("- ");
					}
					System.out.println();
					while(rs.next())
					{
						System.out.println("ID of the book is "+rs.getString("id")+"\nThe name of the book is "+rs.getString("bookName")+"\nThe price of the book is "+rs.getString("price")+"\nThe availability of the book is "+rs.getString("stock"));
						for(int i=0;i<75;i++)
						{
							System.out.print("- ");
						}
						System.out.println();
					}
					order(name);
				}
				else
				{
					System.out.println("No books available");
					for(int i=0;i<75;i++)
					{
						System.out.print("* ");
					}
					System.out.println();
					System.out.println("Want to try some other books?");
					System.out.println("1.Yes\n2.No");
					int n=input.nextInt();
					if(n==1)
					{
						category(name);
					}
					else
					{
						System.out.println("*****Visit again soon*****");
					}
				}
				break;
			}
					
			}
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
		
	}
	static void magazine(String name,String category1)
	{
		Scanner input=new Scanner(System.in);
		try
		{
			Connection myConnect=DriverManager.getConnection("jdbc:mysql://localhost:3306/books","root","susanroot");
			Statement myStmt=myConnect.createStatement();
			System.out.println("1.India Today \n2.Tinkle \n3.Reader's Digest \n4.Forbes India");
			System.out.print("Enter the category of book you desire ti buy : ");
			int num=input.nextInt();
			switch(num)
			{
			case 1:
				{
					String str="SELECT * FROM bookShelf WHERE subCategory='India Today'";
					ResultSet rs=myStmt.executeQuery(str);
					if(rs.next())
					{
						System.out.println("ID of the book is "+rs.getString("id")+"\nThe name of the book is "+rs.getString("bookName")+"\nThe price of the book is "+rs.getString("price")+"\nThe availability of the book is "+rs.getString("stock")+"\nThe shop name is "+rs.getString("shopName"));
						for(int i=0;i<75;i++)
						{
							System.out.print("- ");
						}
						System.out.println();
						while(rs.next())
						{
							System.out.println("ID of the book is "+rs.getString("id")+"\nThe name of the book is "+rs.getString("bookName")+"\nThe price of the book is "+rs.getString("price")+"\nThe availability of the book is "+rs.getString("stock")+"\nThe shop name is "+rs.getString("shopName"));
							for(int i=0;i<75;i++)
							{
								System.out.print("- ");
							}
							System.out.println();
						}
						order(name);
					}
					else
					{
						System.out.println("No books available");
						for(int i=0;i<75;i++)
						{
							System.out.print("* ");
						}
						System.out.println();
						System.out.println("Want to try some other books?");
						System.out.println("1.Yes\n2.No");
						int n=input.nextInt();
						if(n==1)
						{
							category(name);
						}
						else
						{
							System.out.println("*****Visit again soon*****");
						}
					}
					break;
				}
			case 2:
			{
				String str="SELECT * FROM bookShelf WHERE subCategory='Tinkle'";
				ResultSet rs=myStmt.executeQuery(str);
				if(rs.next())
				{
					System.out.println("ID of the book is "+rs.getString("id")+"\nThe name of the book is "+rs.getString("bookName")+"\nThe price of the book is "+rs.getString("price")+"\nThe availability of the book is "+rs.getString("stock"));
					for(int i=0;i<75;i++)
					{
						System.out.print("- ");
					}
					System.out.println();
					while(rs.next())
					{
						System.out.println("ID of the book is "+rs.getString("id")+"\nThe name of the book is "+rs.getString("bookName")+"\nThe price of the book is "+rs.getString("price")+"\nThe availability of the book is "+rs.getString("stock"));
						for(int i=0;i<75;i++)
						{
							System.out.print("- ");
						}
						System.out.println();
					}
					order(name);
				}
				else
				{
					System.out.println("No books available");
					for(int i=0;i<75;i++)
					{
						System.out.print("* ");
					}
					System.out.println();
					System.out.println("Want to try some other books?");
					System.out.println("1.Yes\n2.No");
					int n=input.nextInt();
					if(n==1)
					{
						category(name);
					}
					else
					{
						System.out.println("*****Visit again soon*****");
					}
				}
				break;
			}
			case 3:
			{
				String str="SELECT * FROM bookShelf WHERE subCategory='Reader's Digest'";
				ResultSet rs=myStmt.executeQuery(str);
				if(rs.next())
				{
					System.out.println("ID of the book is "+rs.getString("id")+"\nThe name of the book is "+rs.getString("bookName")+"\nThe price of the book is "+rs.getString("price")+"\nThe availability of the book is "+rs.getString("stock"));
					for(int i=0;i<75;i++)
					{
						System.out.print("- ");
					}
					System.out.println();
					while(rs.next())
					{
						System.out.println("ID of the book is "+rs.getString("id")+"\nThe name of the book is "+rs.getString("bookName")+"\nThe price of the book is "+rs.getString("price")+"\nThe availability of the book is "+rs.getString("stock"));
						for(int i=0;i<75;i++)
						{
							System.out.print("- ");
						}
						System.out.println();
					}
					order(name);
				}
				else
				{
					System.out.println("No books available");
					for(int i=0;i<75;i++)
					{
						System.out.print("* ");
					}
					System.out.println();
					System.out.println("Want to try some other books?");
					System.out.println("1.Yes\n2.No");
					int n=input.nextInt();
					if(n==1)
					{
						category(name);
					}
					else
					{
						System.out.println("*****Visit again soon*****");
					}
				}
				break;
			}
			case 4:
			{
				String str="SELECT * FROM bookShelf WHERE subCategory='Forbes India'";
				ResultSet rs=myStmt.executeQuery(str);
				if(rs.next())
				{
					System.out.println("ID of the book is "+rs.getString("id")+"\nThe name of the book is "+rs.getString("bookName")+"\nThe price of the book is "+rs.getString("price")+"\nThe availability of the book is "+rs.getString("stock"));
					for(int i=0;i<75;i++)
					{
						System.out.print("- ");
					}
					System.out.println();
					while(rs.next())
					{
						System.out.println("ID of the book is "+rs.getString("id")+"\nThe name of the book is "+rs.getString("bookName")+"\nThe price of the book is "+rs.getString("price")+"\nThe availability of the book is "+rs.getString("stock"));
						for(int i=0;i<75;i++)
						{
							System.out.print("- ");
						}
						System.out.println();
					}
					order(name);
				}
				else
				{
					System.out.println("No books available");
					for(int i=0;i<75;i++)
					{
						System.out.print("* ");
					}
					System.out.println();
					System.out.println("Want to try some other books?");
					System.out.println("1.Yes\n2.No");
					int n=input.nextInt();
					if(n==1)
					{
						category(name);
					}
					else
					{
						System.out.println("*****Visit again soon*****");
					}
				}
				break;
			}
					
			}
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
		
	}
	static void order(String name)
	{
		Scanner input=new Scanner(System.in);
		try
		{
			Connection myConnect=DriverManager.getConnection("jdbc:mysql://localhost:3306/books","root","susanroot");
			Statement myStmt=myConnect.createStatement();
			Connection myConnect1=DriverManager.getConnection("jdbc:mysql://localhost:3306/orders","root","susanroot");
			Statement myStmt1=myConnect1.createStatement();
			System.out.print("Enter the id of the book you want to buy : ");
			String id=input.next();			
			String s="SELECT stock,bookName,shopName FROM bookShelf where id='"+id+"'";
			ResultSet rs=myStmt.executeQuery(s);
			while(rs.next())
			{
				String bookName=rs.getString("bookName");
				String shopName=rs.getString("shopName");
				myStmt1.executeUpdate("INSERT INTO orderTable "+" VALUES('"+name+"' , '"+id+"' , '"+bookName+"' , '"+shopName+"')");
				System.out.println("Your Book Has Been Successfully Ordered!!");
				int stock=rs.getInt("stock");
				stock--;
				if(stock==0)
				{
					String s2="DELETE FROM bookShelf WHERE id='"+id+"'";
					myStmt.executeUpdate(s2);
				}
				else
				{
					String s2="UPDATE bookShelf SET stock="+stock+" WHERE id='"+id+"'";
					myStmt.executeUpdate(s2);
				}
				
				
			}
			
		}
		catch(Exception e)
		{
			System.out.println("YOUR ORDER HAS BBEN SUCCESSFULLY PLACED");
			System.out.println("Wanna place some more???\n1.Yes\n2.No");
			int n=input.nextInt();
			if(n==2)
			{
				System.out.println("Thank you for shopping with us!");
			}
			else if(n==1)
			{
				String name1=name;
				category(name1);
			}
		}
	}
}
class Seller
{
	public void SellerLogin()
	{
		Scanner input=new Scanner(System.in);
		try
		{
			Connection myConnect=DriverManager.getConnection("jdbc:mysql://localhost:3306/Seller","root","susanroot");
			Statement myStmt=myConnect.createStatement();
			System.out.println("1.Already a user");
			System.out.println("2.New User");
			int n;
			n=input.nextInt();
			input.nextLine();
			
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
						ResultSet rs=myStmt.executeQuery("SELECT password FROM SellerInfo WHERE name = '"+name+"'");
						if(rs.next())
						{
							String s=rs.getString("password");
							if(s.equals(password))
							{
								System.out.println("You have successfully logged in!!!");
								ResultSet rs1=myStmt.executeQuery("SELECT shopName FROM SellerInfo where name = '"+name+"'");
								String shopName=null;
								if(rs1.next())
								{
									shopName=rs1.getString("shopName");
								}
								category(shopName);
								break;
							}
							else
							{
								System.out.println("Please check your password");
								System.out.println("PLEASE ENTER YOUR INFORMATION");
							}
						}
						else
						{
							System.out.println("Check the name and password");
							System.out.println("Are you a new user??\n1.Yes\n2.No");
							int num1=input.nextInt();
							input.nextLine();
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
					System.out.println("User not found"+" ");
				}
			}
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	static void newUser()
	{
		Scanner input=new Scanner(System.in);
		try
		{
			Connection myConnect=DriverManager.getConnection("jdbc:mysql://localhost:3306/Seller","root","susanroot");
			Statement myStmt=myConnect.createStatement();
			String id;
			while(true)
			{
				System.out.print("Enter a unique id for u r shop : ");
				id=input.next();
				String s="SELECT shopName FROM SellerInfo WHERE id='"+id+"'";
				ResultSet rs=myStmt.executeQuery(s);
				if(rs.next())
				{
					System.out.println("Please enter a proper unique id");
				}
				else
				{
					break;
				}
			}
			input.nextLine();
			System.out.print("Enter your name : ");
			String name=input.nextLine();
			String email;
			while(true)
			{
				System.out.print("Enter your email : ");
				email=input.next();
				if(email.contains("@") && email.contains(".com"))
				{
					break;
				}
				else
				{
					System.out.println("Please enter a valid email");
				}
			}	
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
			
			System.out.print("Enter your phone number : ");
			String phNo;
			while(true)
			{
				phNo=input.next();
				if(phNo.length()==10)
				{
					break;
				}
				else
				{
					System.out.println("Your phone number should be of 10 digits");
				}
			}
			System.out.print("Enter your shop name : ");
			input.nextLine();
			String shopName;
			shopName=input.nextLine();
			String str="INSERT INTO SellerInfo "+" VALUES ('"+id+"', '"+name+"', '"+email+"', '"+password+"', '"+phNo+"','"+shopName+"')";
			myStmt.executeUpdate(str);
			ResultSet myRs=myStmt.executeQuery("select * from SellerInfo where name='"+name+"'");
			while(myRs.next())
			{
				for(int i=0;i<75;i++)
				{
					System.out.print("* ");
				}
				System.out.println("\nYour unique id is "+myRs.getString("id")+"\nThe Name is "+myRs.getString("name")+"\nThe Email-id is "+myRs.getString("email")+"\nThe password is "+"\nThe phone number is "+myRs.getString("phNo")+"\nThe shop name is "+myRs.getString("shopName"));
				
				for(int i=0;i<75;i++)
				{
					System.out.print("* ");
				}
				System.out.println();
			}
			category(shopName);
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	static void category(String shopName)
	{
		Scanner input=new Scanner(System.in);
		System.out.println("1.Add Books \n2.View orders \n3.Update orders");
		System.out.print("Enter the operation that has to be done : ");
		String shop=shopName;
		int n=input.nextInt();
		if(n==1)
		{
			System.out.println("1.Language \n2.Science and Technology \n3.Magazines");
			System.out.print("Please, Enter the category : ");
			int num=input.nextInt();
			switch(num)
			{
			case 1:
				{
					language(shop);
					break;
				}
			case 2:
				{
					Science_and_Technology(shop);
					break;
				}
			case 3:
				{
					magazine(shop);
					break;
				}
			}
		}
		if(n==2)
		{
			try
			{
				Connection myConnect1=DriverManager.getConnection("jdbc:mysql://localhost:3306/orders","root","susanroot");
				Statement myStmt1=myConnect1.createStatement();
				ResultSet rs=myStmt1.executeQuery("SELECT * from orderTable WHERE shopName='"+shopName+"'");
				if(rs.next())
				{
					System.out.println("The id of the book is "+rs.getString("id")+"\nThe customer name is "+rs.getString("customerName"));
					while(rs.next())
					{
						System.out.println("The id of the book is "+rs.getString("id")+"\nThe customer name is "+rs.getString("customerName"));
					}
				}
				else
				{
					System.out.println("So far no orders");
				}
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
		if(n==3)
		{
			try
			{
				Connection myConnect1=DriverManager.getConnection("jdbc:mysql://localhost:3306/orders","root","susanroot");
				Statement myStmt1=myConnect1.createStatement();
				ResultSet rs=myStmt1.executeQuery("SELECT * from orderTable WHERE shopName='"+shopName+"'");
				if(rs.next())
				{
					System.out.println("The id of the book is "+rs.getString("id")+"\nThe customer name is "+rs.getString("customerName"));
					while(rs.next())
					{
						System.out.println("The id of the book is "+rs.getString("id")+"\nThe customer name is "+rs.getString("customerName"));
					}
					System.out.println("Enter the id of the books which has been placed : ");
					String id=input.next();
					myStmt1.executeUpdate("DELETE FROM orderTable WHERE id='"+id+",");
				}
				else
				{
					System.out.println("So far no orders");
				}
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
	}
	static void language(String shopName)
	{
		Scanner input=new Scanner(System.in);
		System.out.println("1.Novels \n2.Grammar \n3.Biographies \n4.Story Books");
		System.out.print("Enter the category of book you desire to buy : ");
		int num=input.nextInt();
		
		switch(num)
		{
		case 1:
			insertion(shopName,"Novels","Language");
			break;
		case 2:
			insertion(shopName,"Grammar","Language");
			break;
		case 3:
			insertion(shopName,"Biographies","Language");
			break;
		case 4:
			insertion(shopName,"Story Books","Language");
			break;
		}
	}
	static void insertion(String shopName,String subCategory,String category)
	{
		Scanner input=new Scanner(System.in);
		try
		{
			Connection myConnect=DriverManager.getConnection("jdbc:mysql://localhost:3306/books","root","susanroot");
			Statement myStmt=myConnect.createStatement();
			System.out.print("Enter your ID : ");
			String id;
			while(true)
			{
				id=input.next();
				ResultSet rs=myStmt.executeQuery("SELECT id FROM bookShelf where id='"+id+"'");
				if(rs.next())
				{
					System.out.println("Your unique id is already taken");
				}
				else
				{
					break;
				}
			}
			input.nextLine();
			System.out.print("Enter your book name : ");
			String bookName=input.nextLine();
			System.out.print("Enter the price of your book : ");
			String price=input.next();
			System.out.print("Enter the stock : ");
			int stock=input.nextInt();
			myStmt.executeUpdate("INSERT INTO BookShelf "+" VALUES('"+id+"' , '"+bookName+"' , '"+price+"' , '"+shopName+"' , '"+stock+"' ,'"+category+"','"+subCategory+"')");
			System.out.println("Do You still want to add books?\n1.Yes\n2.No");
			System.out.print("Enter your choice : ");
			int n=input.nextInt();
			if(n==1)
			{
				category(shopName);
			}
			else
			{
				System.out.println("Your Book Has Been Inserted!!\n Thank You!:)");
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	static void Science_and_Technology(String shopName)
	{
		Scanner input=new Scanner(System.in);
		System.out.println("1.AI and AR \n2.Natural Philosophy \n3.Biology \n4.Agriculture");
		System.out.print("Enter the category of book you desire to buy : ");
		int num=input.nextInt();
		input.nextLine();
		switch(num)
		{
		case 1:
			insertion(shopName,"AI and AR","Sci_and_Tech");
			break;
		case 2:
			insertion(shopName,"Natural Philosophy","Sci_and_Tech");
			break;
		case 3:
			insertion(shopName,"Biology","Sci_and_Tech");
			break;
		case 4:
			insertion(shopName,"Agriculture","Sci_and_Tech");
			break;
		}
	}
	static void magazine(String shopName)
	{
		Scanner input=new Scanner(System.in);
		System.out.println("1.India Today \n2.Tinkle \n3.Reader's Digest \n4.Forbes India");
		System.out.print("Enter the category of book you desire to buy : ");
		int num=input.nextInt();
		input.nextLine();
		switch(num)
		{
		case 1:
			insertion(shopName,"India Today","magazine");
			break;
		case 2:
			insertion(shopName,"Tinkle","magazine");
			break;
		case 3:
			insertion(shopName,"Reader's Digest","magazine");
			break;
		case 4:
			insertion(shopName,"Forbes India","magazine");
			break;
		}
	}
}
class Admin
{
	public void AdminLogin()
	{
		Scanner input=new Scanner(System.in);
		try
		{
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Admin","root","susanroot");
			Statement myStmt=con.createStatement();
			while(true)
			{
				int flag=0;
				System.out.print("Enter your name: ");
				String name=input.nextLine();
				ResultSet rs=myStmt.executeQuery("SELECT password FROM adminInfo where name='"+name+"'");
				if(rs.next())
				{
					while(true)
					{
						System.out.print("Enter your password : ");
						String password=input.next();
						String pass1=rs.getString("password");
						if(password.equals(pass1))
						{
							flag=1;
							System.out.println("You have Successfully logged in!");
							category();
							break;
						}
						else
						{
							System.out.println("Oops!");
						}
					}
					
				}
				else
				{
					System.out.println("Enter the proper name");
				}
				if(flag==1)
				{
					break;
				}
			}
			
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	static void category()
	{
		Scanner input=new Scanner(System.in);
		System.out.println("1.View Orders\n2.Delete Seller");
		System.out.print("Enter your choice : ");
		int n=input.nextInt();
		if(n==1)
		{
			try
			{
				Connection myConnect=DriverManager.getConnection("jdbc:mysql://localhost:3306/orders","root","susanroot");
				Statement myStmt=myConnect.createStatement();
				ResultSet rs=myStmt.executeQuery("select * from orderTable");
				while(rs.next())
				{
					System.out.println("The customer name is :" +rs.getString("customerName")+"The book is is : "+rs.getString("id")+"The bookName is : "+rs.getString("bookName")+"The shop Name is : "+rs.getString("shopName"));
				}
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
		else if(n==2)
		{
			try
			{
				Connection myConnect=DriverManager.getConnection("jdbc:mysql://localhost:3306/seller","root","susanroot");
				Statement myStmt=myConnect.createStatement();
				Connection myConnect1=DriverManager.getConnection("jdbc:mysql://localhost:3306/books","root","susanroot");
				Statement myStmt1=myConnect1.createStatement();
				
				String shopName=null;
				ResultSet rs=myStmt.executeQuery("select * from sellerInfo");
				while(rs.next())
				{
					for(int i=0;i<75;i++)
					{
						System.out.print("- ");
					}
					System.out.println();
					System.out.print("The id of the shop is "+rs.getString("id")+"\nThe name of the shop is "+rs.getString("shopName"));
					shopName=rs.getString("shopName");
				}
				input.nextLine();
				System.out.println("Enter the id of the shop that has to be removed : ");
				String id=input.next();
				myStmt.executeUpdate("DELETE FROM sellerInfo where id='"+id+"'");
				myStmt1.executeUpdate("DELETE FROM bookShelf where shopName='"+shopName+"'");
				System.out.println("Seller's account has been deleted sucessfully!");
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
	}
}
