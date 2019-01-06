import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.util.*;
import java.io.*;

public class Client 
{

    private Client() {}

    public static void main(String[] args)
	{
		int number;

        String host = (args.length < 1) ? null : args[0];
        try 
		{
            Registry registry = LocateRegistry.getRegistry(host);
            Election stub = (Election) registry.lookup("Election");
			boolean loop = true;
			while(loop)
			{
			System.out.println("***********Welcome to Voting System*********");
			System.out.println("1. Register to Vote\n 2. Verify Voter\n 3. Vote\n 4. Tally Results\n 5. Announce Winner\n 0. Quit\n");
				Scanner scan = new Scanner(System.in);
				number = scan.nextInt();
				switch(number) 
				{
				 case 1:
				 
					System.out.println("------- Register your Profile -------");
					System.out.println("Register your First Name");
					String f_name= scan.next();
					System.out.println("Register your Last Name");
					String l_name = scan.next();
					System.out.println("Enter your City");
					String c_name = scan.next();
					String response = stub.register_voter(f_name,l_name,c_name);
					System.out.println("response: " + response);
					
					break;
					
				 case 2 :
	
					System.out.println("------- Please Verify your ID below -------");
					System.out.println("Enter your Voter ID:");
					String voter_id= scan.next();
					response = stub.verify_voter(voter_id);
					System.out.println("response: " + response);
					
					break;
					
				 case 3 :
		
					System.out.println("-------Election Board -------");
					System.out.println("Enter your Voter ID:");
					voter_id= scan.next();
					boolean check_response = stub.check_vote(voter_id);
					if(check_response == false)
					{
						System.out.println("Enter Candidate's Voter ID:");
						String candidate_id = scan.next();
						response = stub.vote(candidate_id,voter_id);
						System.out.println("response: " + response);
					}
					else
					{
						System.out.println("You have already Voted");
					}
					
					break;
					
				 case 4 :
				 
					System.out.println("------- Candidate's Details -------");
					ArrayList<Voterdata> data_list = stub.tally_results();
					for (int i = 0; i < data_list.size(); i++) 
					{ 
					   Voterdata data = data_list.get(i); 
					   System.out.println(data.Name+" "+data.Vote); 
					} 
					break;
					
				 case 5 :
				 
					System.out.println("-------Announcement -------");
					response = stub.announce_winner();
					System.out.println("response: " + response);
					break;
					
				case 0 :
					loop = false;
					break;
				default :
					System.out.println("Invalid Input. Please try again");
				}

            }
        } 
		catch (Exception e) 
		{
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
		
		

    }
}