import java.util.ArrayList;
import java.io.*;
import java.util.Iterator;

public class Voterdata implements Serializable
{	
        String Name;
		String voterid;
		boolean isVoted;
		int Vote;
		
	 Voterdata(String Name, String voterid,boolean isVoted, int Vote)
        { 
            this.Name = Name; 
            this.voterid = voterid; 
			this.isVoted = isVoted;
            this.Vote = Vote; 
        } 	
}