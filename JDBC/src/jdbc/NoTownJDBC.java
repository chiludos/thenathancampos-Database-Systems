package jdbc;

import java.sql.*;
import java.util.Scanner;

public class NoTownJDBC {
	
   static final String db_url = "jdbc:postgresql://cs1.calstatela.edu/cs4222s74";
   static final String username = "cs4222s74";
   static final String password = "PgNjishI";
   static final String QUERY = "SELECT SSN, Name, Address, Phone_number FROM Musician";
   

   public static void main(String[] args) {
	   // Set Up User Interface
	   Scanner user_input = new Scanner(System.in);
	   Scanner user_input2 = new Scanner(System.in);
	   Scanner user_input3 = new Scanner(System.in);
	   Scanner user_input4 = new Scanner(System.in);
	   Scanner user_input5 = new Scanner(System.in);
	   Scanner user_input6 = new Scanner(System.in);
	   Scanner user_input7 = new Scanner(System.in);
	   Scanner user_input8 = new Scanner(System.in);
	   
	   /*System.out.println("Please select from our 6 choices: \n0 to exit, \n1 to add musician, \n2"
	   		+ " to remove musician, \n3 to add album and song, \n4 to remove album and song,"
	   		+ "\n5 to display all info from tables");
	   int choice = user_input.nextInt();*/
	   int choice;
	   
	   do {
		   System.out.println("Please select from our 6 choices: \n0 to exit, \n1 to add musician, \n2"
			   		+ " to remove musician, \n3 to add album and song, \n4 to remove album and song,"
			   		+ "\n5 to display all info from tables");
		   choice = user_input.nextInt();
		   
		   // Create Switch Menu
		   switch (choice) {
		   case 0:
			   System.exit(0);
		   case 1:
			   System.out.print("Please Enter a Musician SSN: ");
			   int musician_SSN = user_input2.nextInt();
			   System.out.print("Please Enter a Musician name: ");
			   String musician_Name = user_input3.nextLine();
			   System.out.print("Please Enter a Musician address: ");
			   String musician_Address = user_input4.nextLine();
			   System.out.print("Please Enter a Musician phone number: ");
			   String musician_PhoneNumber = user_input5.nextLine();
			   //String add_Musician = "Insert Into Musician Values (?);";
			   String add_Musician = "Insert Into Musician Values('" + musician_SSN + "', '" + musician_Name + "',"
			   		+ " '" + musician_Address + "', '" + musician_PhoneNumber + "')";
				// Prepared Statements: prevent SQL injection attack
				//String sql = "insert into ecst_faculty (department_name) values (?);";
				//PreparedStatement stmt = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				//stmt.setString(1, name);
				//stmt.setString(2, comment);
				//stmt.executeUpdate();
			   // Open a connection
			   try {
			    Connection conn = DriverManager.getConnection(db_url, username, password);
			    Statement stmt = conn.createStatement();
			    stmt.executeUpdate(add_Musician);
				/*PreparedStatement stmt = conn.prepareStatement(add_Musician, Statement.RETURN_GENERATED_KEYS);
				stmt.setInt(1, musician_ID);
				stmt.setString(2, musician_Name);
				stmt.setString(3, musician_Address);
				stmt.setString(4, musician_PhoneNumber);
	            stmt.executeUpdate(); */
			   } catch (SQLException e) {
		         e.printStackTrace();
		      }
			   
			  System.out.println();
			  break;
		   case 2:
			   System.out.print("Please Enter a Musician SSN to delete: ");
			   int musician_IDDelete = user_input2.nextInt();
			   String delete_Musician = "Delete From Musician Where SSN = '" + musician_IDDelete + "'";
			   // Open a connection
			   try {
			    Connection conn = DriverManager.getConnection(db_url, username, password);
			    Statement stmt = conn.createStatement();
			    stmt.executeUpdate(delete_Musician);
			   } catch (SQLException e) {
			         e.printStackTrace();
			      }
			   System.out.println();
			   break;
		   case 3:
			   System.out.print("Please Enter an Album ID: ");
			   int album_ID = user_input2.nextInt();
			   System.out.print("Please Enter an Album title: ");
			   String album_title = user_input3.nextLine();
			   System.out.print("Please Enter Album Format (CD or MC): ");
			   String album_Format = user_input4.nextLine();
			   System.out.print("Please Enter Album copyright date: ");
			   String album_CopyRight = user_input5.nextLine();
			   System.out.print("Please Enter Album identifier: ");
			   int album_Identifier = user_input6.nextInt();
			   String add_Album = "Insert Into Album Values('" + album_ID + "', '" + album_title + 
					   "', '" + album_Format + "', '" + album_CopyRight + "', '" + album_Identifier + "')";
			   // Open a connection
			   try {
			    Connection conn = DriverManager.getConnection(db_url, username, password);
			    Statement stmt = conn.createStatement();
			    stmt.executeUpdate(add_Album);
			   } catch (SQLException e) {
			         e.printStackTrace();
			      }
			   System.out.println();
			   System.out.print("Please Enter a Song title: ");
			   String song_Title = user_input7.nextLine();
			   System.out.print("Please Enter an Author(ID): ");
			   int author_ID = user_input8.nextInt();
			   String add_Song = "Insert Into Songs Values('" + song_Title + "', '" + author_ID + "')";
			   // Open a connection
			   try {
			    Connection conn = DriverManager.getConnection(db_url, username, password);
			    Statement stmt = conn.createStatement();
			    stmt.executeUpdate(add_Song);
			   } catch (SQLException e) {
			         e.printStackTrace();
			      }
			   System.out.println();
			   break;
		   case 4:
			   System.out.print("Please Enter an Album ID to delete: ");
			   int album_IDDelete = user_input2.nextInt();
			   String delete_Album = "Delete From Album Where Album_id = '" + album_IDDelete + "'";
			   try {
				    Connection conn = DriverManager.getConnection(db_url, username, password);
				    Statement stmt = conn.createStatement();
				    stmt.executeUpdate(delete_Album);
				   } catch (SQLException e) {
				         e.printStackTrace();
				      }
			   System.out.print("Please Enter a Song title to delete: ");
			   String song_TitleDelete = user_input3.nextLine();
			   String delete_Song = "Delete From Songs Where Song_title = '" + song_TitleDelete + "'";
			   try {
				    Connection conn = DriverManager.getConnection(db_url, username, password);
				    Statement stmt = conn.createStatement();
				    stmt.executeUpdate(delete_Song);
				   } catch (SQLException e) {
				         e.printStackTrace();
				      }
			   System.out.println();
			   break;
		   case 5:
			   // Open a connection
			   try(Connection conn = DriverManager.getConnection(db_url, username, password);
				Statement stmt = conn.createStatement();
	            ResultSet rs = stmt.executeQuery(QUERY);) {
	           // Extract data from result set
	           while (rs.next()) {
	            // Retrieve by column name
	            System.out.print("SSN: " + rs.getInt("SSN"));
	            System.out.print(", Name: " + rs.getString("Name"));
	            System.out.print(", Address: " + rs.getString("Address"));
	            System.out.println(", Phone Number: " + rs.getString("Phone_number"));
	           }

		      } catch (SQLException e) {
		         e.printStackTrace();
		      }
			   String query2 = "SELECT Instrument_id, Name, Musical_key, SSN FROM Instrument";
			   // Open a connection
			   try(Connection conn = DriverManager.getConnection(db_url, username, password);
				Statement stmt = conn.createStatement();
	            ResultSet rs = stmt.executeQuery(query2);) {
	           // Extract data from result set
	           while (rs.next()) {
	            // Retrieve by column name
	            System.out.print("Instrument ID: " + rs.getInt("Instrument_id"));
	            System.out.print(", Name: " + rs.getString("Name"));
	            System.out.print(", Musical Key: " + rs.getString("Musical_key"));
	            System.out.println(", SSN: " + rs.getInt("SSN"));
	           }

		      } catch (SQLException e) {
		         e.printStackTrace();
		      }
			   String query3 = "SELECT Song_title, Author FROM Songs";
			   // Open a connection
			   try(Connection conn = DriverManager.getConnection(db_url, username, password);
				Statement stmt = conn.createStatement();
	            ResultSet rs = stmt.executeQuery(query3);) {
	           // Extract data from result set
	           while (rs.next()) {
	            // Retrieve by column name
	            System.out.print("Song Title: " + rs.getString("Song_title"));
	            System.out.println(", Author: " + rs.getInt("Author"));
	           }

		      } catch (SQLException e) {
		         e.printStackTrace();
		      }
			   String query4 = "SELECT Album_id, Album_title, Format, Copyright_date, Album_identifier FROM Album";
			   // Open a connection
			   try(Connection conn = DriverManager.getConnection(db_url, username, password);
				Statement stmt = conn.createStatement();
	            ResultSet rs = stmt.executeQuery(query4);) {
	           // Extract data from result set
	           while (rs.next()) {
	            // Retrieve by column name
	            System.out.print("Album ID: " + rs.getInt("Album_id"));
	            System.out.print(", Album Title: " + rs.getString("Album_title"));
	            System.out.print(", Format Type: " + rs.getString("Format"));
	            System.out.print(", Copyright Date: " + rs.getString("Copyright_date"));
	            System.out.println(", Album Indentifier: " + rs.getInt("Album_identifier"));
	           }

		      } catch (SQLException e) {
		         e.printStackTrace();
		      }
			   System.out.println();
			   break;
		   }
	   } while (choice != 0);
		user_input.close();   
 
   }
}
