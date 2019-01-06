import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.*;


public interface Election extends Remote
 {
    
	public String register_voter(String f_name,String l_name,String c_name) throws RemoteException;
	public String verify_voter(String voter_id) throws RemoteException;
	public boolean check_vote(String voter_id) throws RemoteException;
	public String vote(String candidate_id, String voter_id) throws RemoteException;
	public ArrayList<Voterdata> tally_results() throws RemoteException;
	public String announce_winner() throws RemoteException;
}