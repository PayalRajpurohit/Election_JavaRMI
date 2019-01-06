# Election_JavaRMI
Java RMI based program that simulates an election process
Java RMI based program that simulates an election process. 
This program have remote interface have methods: register voter, verify voter, vote, tally results, and announce winner. 

1. The register voter methods should take as parameters a name, and assign a voter ID from a list of valid voter IDs (Each of you decides the criteria of a valid voter ID, it needs to be a format that your system generates and accepts, be innovative here). 
2. The verify voter method takes a voter ID as input and verifies if the voter with the voter ID is registered and under what name? However it should not allow the voter to vote if they have voted before.
3. The vote method should allow a voter to cast a single vote for a single candidate. 
4. The tally results, should return the votes each of the candidates received to be displayed by the client.
5. Announce winner should just return the election winner's name.

We implemented a client/server RMI application that takes handles this system, so we needed both the client and the server in addition to the remote custom interface. 

This program start by loading a file with candidate names (Supply as input), it should handle up to 10 candidates. Then this program should generate valid voter IDs (we defined own criteria for what a valid voter ID is). They system should allow for up to 1000 voters. 

Then once the system starts, a menu should be displayed to ask the user to either input one of the 
5 choices listed above or 0 to quit. 

1. Register to Vote
2. Verify Voter
3. Vote
4. Tally Results
5. Announce Winner
0. Quit

Please Enter your Choice: 

The menu continues to be displayed as long as the voter has not selected the Quit option. So, each of the
Operations can be done as many times as the user of the program choses. 
This website from Oracle can be helpful: https://docs.oracle.com/javase/7/docs/technotes/guides/rmi/hello/hello-world.html
