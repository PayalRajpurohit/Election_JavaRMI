import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import java.util.Random; 
import java.lang.*; 
import java.util.ArrayList;
import java.util.List;
import java.util.*;
      
public class Server implements Election 
{
	
	final static String VoterId_Prefix = "2018";
	private static int count = 0;  
	static int n =5;
    ArrayList<Voterdata> list=new ArrayList<>();
    public Server() {}
	
	public String Generate_id(String first_name, String last_name, String city_name)
	{
		String sub_fname, sub_lname, sub_cname, Voter_Id;
	
		sub_fname = first_name.substring(0, 2);
		sub_lname = last_name.substring(0, 2);
		sub_cname = city_name.substring(0, 2);
		
		Random rand = new Random(); 
		count = rand.nextInt(100); 
		
		Voter_Id = VoterId_Prefix+sub_fname.toUpperCase()+sub_lname.toUpperCase()+sub_cname.toUpperCase()+count;
		return Voter_Id;
		
	} 
	
    public String register_voter(String f_name, String l_name, String c_name) 
	{
    	
		String Details;
		String Full_Name = f_name+" "+l_name;
		String id = Generate_id(f_name,l_name, c_name);
		System.out.println(id);
		boolean isVoted = false;
		int Vote = 0;
		list.add(new Voterdata(Full_Name,id, isVoted, Vote));
		display();
		Details = Full_Name+"\t"+id;
		return Details;
	}
	
	
	public void display(){
		
		 for (int i = 0; i < list.size(); i++) 
        { 
           Voterdata data = list.get(i); 
           System.out.println(data.Name+" "+data.voterid+" "+data.isVoted+" "+data.Vote); 
        } 
	 
 }
	public String verify_voter(String voter_id)
	{
		for (int i = 0; i < list.size(); i++) 
        { 	
			Voterdata data = list.get(i); 
			if(voter_id.equals(data.voterid)== true)
			{
				return data.Name;
			}
		}
		return "Not available";	
	}
	
	public boolean check_vote(String voter_id)
	{
			for (int i = 0; i < list.size(); i++) 
			{
				Voterdata data = list.get(i); 
				if(voter_id.equals(data.voterid)== true)
				{
					return data.isVoted;
				}
			}	
			return true;
	}
	
	public void update_votestatus(String voter_id)
	{
		
		for (int i = 0; i < list.size(); i++) 
			{
				Voterdata data = list.get(i); 
				if(voter_id.equals(data.voterid)== true)
				{
					
					int x = data.Vote;
					String NewName = data.Name;
					String NewVid = data.voterid;
					boolean NewisVoted = true;
					list.remove(i);
					list.add(new Voterdata(NewName,NewVid,NewisVoted,x));
					System.out.println("---------------------------------------------------------");
					display();
					System.out.println("---------------------------------------------------------");
				
				}
			}
		
	}
	public String vote(String candidate_id, String voter_id)
	{	
		for (int i = 0; i < list.size(); i++) 
			{
				Voterdata data = list.get(i); 
				if(candidate_id.equals(data.voterid)== true)
				{
					int x = data.Vote;
					x=x+1;
					String NewName = data.Name;
					String NewVid = data.voterid;
					boolean NewisVoted = data.isVoted;
					list.remove(i);
					list.add(new Voterdata(NewName,NewVid,NewisVoted,x));
					update_votestatus(voter_id);
					display();
					return "voted";
				}	
			}
			
		return "Cannot vote";	
	}
	
	public ArrayList<Voterdata> tally_results()
	{
		return list;
	}
	public String announce_winner()
	{
		Voterdata data = list.get(0); 
		int max = data.Vote;
		
		for (int i = 1; i < list.size(); i++) 
        { 
           data = list.get(i); 
		   if (data.Vote >  max)
		   {
			   max = data.Vote;
		   }      
        } 
		for(int i=0;i<list.size();i++)
		{
			 data = list.get(i);
			if(max==data.Vote)
			{
				return data.Name;
			}
		}
		
		return "NO WINNER";
	}
	      
    public static void main(String args[])
	{
        
        try
		{
            Server obj = new Server();
            Election stub = (Election) UnicastRemoteObject.exportObject(obj, 0);
            
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("Election", stub);
            System.err.println("Server ready");
        }
		catch (Exception e) 
		{
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}