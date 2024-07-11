package com.bptn.project.kickoff;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {

	static Scanner scanner =  new Scanner (System.in); //take user input from the console
	static ObjectMapper objectMapper = new ObjectMapper(); //initializes an objectmapper to read or write a json file/url
	static Team team;

	public static void main(String[] args) {
		createTeam(); 
		displayMenu();
	}


	static void displayMenu() {
		
		int menuOption = 0;
		//flag to monitor if user has selected the exit option. 
		boolean running = true;
		System.out.println(Color.BLUE_BOLD + Color.YELLOW_BACKGROUND + "Welcome " + team.getTeamName() + " to the 🚀KickOff App!" + Color.RESET);

		do {
			System.out.println(Color.WHITE_BOLD_BRIGHT + Color.BLACK_BACKGROUND + "➖➖➖➖➖➖➖➖➖➖➖➖➖➖" + Color.RESET);
			System.out.println(Color.GREEN_BOLD_BRIGHT + "🚀KICKOFF               🔋🛜📶" + Color.RESET);
			System.out.println(Color.WHITE_BOLD_BRIGHT + Color.BLACK_BACKGROUND + "➖➖➖➖➖➖➖➖➖➖➖➖➖➖" + Color.RESET);

			System.out.println(Color.CYAN_BACKGROUND_BRIGHT + Color.BLACK_BOLD + "📝Select an option from below!" + Color.RESET);
			System.out.println(Color.BLUE_BOLD + Color.WHITE_BACKGROUND + "~~~~ [1️⃣] UPDATE TEAM NAME ~~~" + Color.RESET);
			System.out.println(Color.BLACK_BACKGROUND + Color.WHITE_BOLD_BRIGHT + "~~~~ [2️⃣] FILL YOUR ROSTER ~~~" + Color.RESET);
			System.out.println(Color.BLACK_BACKGROUND + Color.WHITE_BOLD_BRIGHT + "~~~~ [3️⃣] VIEW YOUR TEAM   ~~~" + Color.RESET);
			System.out.println(Color.BLACK_BACKGROUND + Color.WHITE_BOLD_BRIGHT + "~~~~ [4️⃣] CREATE A MATCH   ~~~" + Color.RESET);
			System.out.println(Color.BLACK_BACKGROUND + Color.WHITE_BOLD_BRIGHT + "~~~~ [5️⃣] UPDATE RESULT    ~~~" + Color.RESET);
			System.out.println(Color.BLACK_BACKGROUND + Color.WHITE_BOLD_BRIGHT + "~~~~ [6️⃣] UPDATE ROSTER    ~~~" + Color.RESET);
			System.out.println(Color.BLUE_BOLD + Color.YELLOW_BACKGROUND + "~~~~ [7️⃣] GENERATE REPORT  ~~~" + Color.RESET);
			System.out.println(Color.WHITE_BOLD + Color.RED_BACKGROUND + "~~~~ [8️⃣] EXIT PROGRAM 	   ~~~" + Color.RESET);


			try {
				menuOption = Integer.valueOf(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println(Color.BLACK_BACKGROUND + Color.RED_BOLD_BRIGHT + "📕: Please enter a whole number." + Color.RESET);
				//e.printStackTrace();
			}

			if(menuOption == 1) {
				updateTeamName(); 
			}
			else if(menuOption == 2) {
				addPlayers();
			}
			else if (menuOption == 3) {
				viewTeam();
			}
			else if (menuOption == 4) {
				try {
					scheduleMatch();
				} catch (MalformedURLException e) {
					System.out.println(Color.BLACK_BACKGROUND + Color.RED_BOLD_BRIGHT + "📕: Error with the provided URL. Please double check the url and try again." + Color.RESET);
					//e.printStackTrace();
				} catch (IOException e) {
					System.out.println(Color.BLACK_BACKGROUND + Color.RED_BOLD_BRIGHT + "📕: The url provided is not a valid json file." + Color.RESET);
					//e.printStackTrace();
				}
			}
			else if (menuOption == 5) {
				matchResult();
			}
			else if (menuOption == 6) {
				manageRoster();
			}
			else if (menuOption == 7) {
				generateReport();
			}
			else if (menuOption == 8) {
				running = false; //if selected, program terminates 
			}
			else {
				System.out.println(Color.BLACK_BACKGROUND + Color.YELLOW_BOLD_BRIGHT + "📙: Incorrect input, please try again" + Color.RESET);
			}


		} while (running);
		System.out.println(Color.CYAN_BACKGROUND_BRIGHT + Color.BLACK_BOLD +"Thank you for using 🚀KickOff™️" + Color.RESET);
		scanner.close();
	}

	//Welcome screen obtains input from user and creates an instance of a team object. 
	static void createTeam() {
		System.out.println(Color.CYAN_BOLD_BRIGHT + "Team Name: " + Color.RESET);
		String teamName = scanner.nextLine();
		//create an instance of a team. 
		team = new Team(teamName);


	}
	//option for user to rename the instance of the team created. 
	static void updateTeamName() {
		System.out.println(Color.CYAN_BOLD_BRIGHT + "Updated Team Name: " + Color.RESET);
		String newName = scanner.nextLine();
		team.setTeamName(newName);
	}

	//method to add players. Either through a CSV formatted .txt file or manually through console input. 
	static void addPlayers() {

		System.out.println(Color.CYAN_BACKGROUND_BRIGHT + Color.BLACK_BOLD +  "How would you like to fill the roster?" + Color.RESET);
		System.out.println(Color.BLACK_BACKGROUND + Color.WHITE_BOLD_BRIGHT + "[1️⃣] Fill from a formated txt file." + Color.RESET);
		System.out.println(Color.BLACK_BACKGROUND + Color.WHITE_BOLD_BRIGHT + "[2️⃣] Manually add players." + Color.RESET);
		int choice = 0;
		try {
			choice = Integer.valueOf(scanner.nextLine());
		} catch (NumberFormatException e) {
			System.out.println(Color.BLACK_BACKGROUND + Color.RED_BOLD_BRIGHT + "📕: Please enter a number." + Color.RESET);
			//e.printStackTrace();
		}


		if (choice == 1) {
			System.out.println(Color.CYAN_BOLD_BRIGHT + "Name of the file: " + Color.RESET);
			String fileName = scanner.nextLine();
			if (!(fileName.endsWith(".txt"))) { // concatenates .txt to the file if the input does not already include it. 
				fileName += ".txt";
			}

			/*
			 * catch the exception in case the file is not found or does not exist. 
			 */
			try {
				BufferedReader reader = new BufferedReader(new FileReader(fileName));
				String line;
				while ((line = reader.readLine()) != null) {
					playerAddition(line); //calls the player addition method based on the input provided on each line of the file. 

				}
				System.out.println(Color.BLACK_BOLD + Color.GREEN_BACKGROUND + "💯: additions completed." + Color.RESET);
				reader.close();
			} catch (IOException e) {
				System.out.println(Color.BLACK_BACKGROUND + Color.RED_BOLD_BRIGHT + "📕: Failed to load the file provided. Please double check and try again." + Color.RESET);
				//e.printStackTrace();
			} 
		}
		else if (choice == 2) {
			System.out.println(Color.CYAN_BOLD_BRIGHT + "Enter each player's information line by line using the following format." + Color.RESET);
			System.out.println(Color.CYAN_BOLD_BRIGHT + "	(firstname lastname, jersey number, position) - e.g.: Kylian Mbappe, 10, ST" + Color.RESET);
			System.out.println(Color.CYAN_BOLD_BRIGHT + "enter 'exit' to exit" + Color.RESET);
			System.out.print("Entry: ");
			while(true) {
				String input = scanner.nextLine();
				if(input.equals("exit")) { //keyword entered breaks out of the entry loop. 
					break;
				}
				playerAddition(input); //calls the playerAddition method based on the input provided on each line user console input.
				System.out.print("Entry: ");

			}

		}
		else {
			System.out.println(Color.BLACK_BACKGROUND + Color.YELLOW_BOLD_BRIGHT + "📙: Incorrect input. Please try again." + Color.RESET);
		}
	}

	//method used to add players to the roster based on a particular format. 
	static void playerAddition(String line) {
		String[] separated = line.split(", ");

		try {
			if (!(team.containsPlayer(separated[0], Integer.parseInt(separated[1]), separated[2]))) {
				team.addPlayer(new Player(separated[0], Integer.parseInt(separated[1]), separated[2]));
				System.out.println("✅: " + Color.PURPLE_BOLD_BRIGHT + separated[0] + Color.RESET + " has been added to the roster.");
			} 
			else {
				System.out.println(Color.WHITE_BACKGROUND_BRIGHT + Color.RED_BOLD_BRIGHT + "❌: Unable to add " + separated[0]
						+ " to the roster due to the record already existing." + Color.RESET);
			}

		} catch (NumberFormatException e) {
			System.out.println(Color.BLACK_BACKGROUND + Color.RED_BOLD_BRIGHT + "📕: Failed to add " + separated[0] + ". Please provide the jersey number in numerical form." + Color.RESET);
			//e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			System.out.println(Color.BLACK_BACKGROUND + Color.RED_BOLD_BRIGHT + "📕: incorrect format provided. Please try again." + Color.RESET);
			//e.printStackTrace();
		}
	}
	
	//calls the method that prints out the full formated roster. 
	static void viewTeam() {
		System.out.println(Color.CYAN_BACKGROUND_BRIGHT + Color.BLACK_BOLD + "👋Welcome, " + team.getTeamName() + ". This is your roster." + Color.RESET);
		team.displayRoster(); 

	}

	//schedule match(es) either based on a json url or manually through console input
	static void scheduleMatch() throws MalformedURLException,IOException {
		System.out.println(Color.CYAN_BACKGROUND_BRIGHT + Color.BLACK_BOLD + "How would you like to fill the matches?" + Color.RESET);
		System.out.println(Color.BLACK_BACKGROUND + Color.WHITE_BOLD_BRIGHT + "[1️⃣] Fill from an api." + Color.RESET);
		System.out.println(Color.BLACK_BACKGROUND + Color.WHITE_BOLD_BRIGHT + "[2️⃣] Manually add matches." + Color.RESET);

		int choice = 0;
		//catch the exception when user does not provide a valid number format. 
		try {
			choice = Integer.valueOf(scanner.nextLine());
		} catch (NumberFormatException e) {
			System.out.println(Color.BLACK_BACKGROUND + Color.RED_BOLD_BRIGHT + "📕: Please enter a number." + Color.RESET);
			//e.printStackTrace();
		}

		URL jsonUrl; //allocates memory space for URL object


		if(choice == 1) {
			System.out.println(Color.CYAN_BOLD_BRIGHT + "Enter the url 🔗" + Color.RESET);
			String url = scanner.nextLine();

			System.out.println(Color.CYAN_BOLD_BRIGHT + "⏳checking url..." + Color.RESET);

			//initialize URL object
			jsonUrl = new URL(url); //throw MalformedURL if the url passed is not valid
			//URL is passed to the JsonNode which will traverse all the json trees present in the file provided to find 
			JsonNode json = objectMapper.readTree(jsonUrl); //throw IOException if the file is not json formatted
			
			//matching value pairs based on the predetermined keys provided and then storing the associated values in variables
			try {
				for (JsonNode node : json) {
					String location = node.get("ArenaName").textValue();
					String awayTeamName = "";

					if(node.get("AwayTeamName").textValue().indexOf("(") == -1){
						awayTeamName = node.get("AwayTeamName").textValue();
					}
					else {
						int index = node.get("AwayTeamName").textValue().indexOf("(");
						awayTeamName = node.get("AwayTeamName").textValue().substring(0, index-1);
					}

					String homeTeamName = "";

					if(node.get("HomeTeamName").textValue().indexOf("(") == -1){
						homeTeamName = node.get("HomeTeamName").textValue();
					}
					else {
						int index = node.get("HomeTeamName").textValue().indexOf("(");
						homeTeamName = node.get("HomeTeamName").textValue().substring(0, index);
					}

					String date = node.get("sDateString").textValue();
					String title = awayTeamName + " VS " + homeTeamName;
					String description = "Division Game: " + title;

					Match match = new Match(title, description, date, location, awayTeamName, homeTeamName);
					team.setMatch(match);

					team.setTotalMatches(1);

				}
			} catch (NullPointerException e) { //catch exception if file does not contain the predetermined keys. 
				System.out.println(Color.BLACK_BACKGROUND + Color.RED_BOLD_BRIGHT + "📕: Mismatched return values in file provided." + Color.RESET);
				//e.printStackTrace();
			}
			System.out.println(Color.BLACK_BOLD + Color.GREEN_BACKGROUND + "✅: Match(es) has been created!" + Color.RESET);


		}
		else if(choice == 2) { //option to manually add match from user console input.
			System.out.println(Color.CYAN_BOLD_BRIGHT + "Enter the title of the event: " + Color.RESET);
			String title = scanner.nextLine();

			System.out.println(Color.CYAN_BOLD_BRIGHT + "Enter the description of the event: " + Color.RESET);
			String description = scanner.nextLine();

			System.out.println(Color.CYAN_BOLD_BRIGHT + "Enter the date of the event (e.g. Mon, June 24, 2024 at 7:30pm): " + Color.RESET);
			String date = scanner.nextLine();

			System.out.println(Color.CYAN_BOLD_BRIGHT + "Enter the Location: " + Color.RESET);
			String location = scanner.nextLine();

			System.out.println(Color.CYAN_BOLD_BRIGHT + "Enter home team's name: " + Color.RESET);
			String homeTeamName = scanner.nextLine();

			System.out.println(Color.CYAN_BOLD_BRIGHT + "Enter away team's name: " + Color.RESET);
			String awayTeamName = scanner.nextLine();


			Match match = new Match(title, description, date, location, awayTeamName, homeTeamName);
			team.setMatch(match);

			System.out.println(Color.BLACK_BOLD + Color.GREEN_BACKGROUND + "✅: Match has been created!" + Color.RESET);
			team.setTotalMatches(1);
		}
		else {
			System.out.println(Color.BLACK_BACKGROUND + Color.YELLOW_BOLD_BRIGHT + "📙: Incorrect input. Please try again." + Color.RESET);
		}

	}

	static void matchResult() {
		//use eventID to find the correct match
		if (team.getTotalMatches() > 0 ) { //Exit if there are no current matches. 
			System.out.println(Color.CYAN_BOLD_BRIGHT + "Below are all your matches." + Color.RESET);

			team.displayMatches();//display all the matches

			System.out.println(Color.CYAN_BACKGROUND_BRIGHT + Color.BLACK_BOLD + " 🔍 Enter the MatchID: " + Color.RESET);
			String matchID =  "";
			try {
				matchID = scanner.nextLine();
			} catch (NumberFormatException e) { //catch exception if input provided is not a number
				System.out.println(Color.BLACK_BACKGROUND + Color.RED_BOLD_BRIGHT + "📕: Please enter a numerical matchID." + Color.RESET);
				//e.printStackTrace();
			}
			
			//set an instance of match based on the match ID provided which will pull a specific match information from the map of all matches
			Match selectedMatch = team.getMatch(matchID);

			if (selectedMatch != null) { //make sure this match exists and has a value
				System.out.println(Color.BLUE_BOLD + Color.WHITE_BACKGROUND + "⚔️Match Selected: " + team.getMatch(matchID).getTitle() + Color.RESET);
				
				//In the json file, sometimes the "selected" team is not the home team in a match. This is to confirm if they are in this instance.
				System.out.println(Color.CYAN_BACKGROUND_BRIGHT + Color.BLACK_BOLD + "Enter 'yes' if " +selectedMatch.getHomeTeamName() + " is the home team" + Color.RESET);
				String confirm = scanner.nextLine();
				
				//variables that will be used for the displayed team names depending if the user's team is selected as home team 
				String homeTeamName;
				String awayTeamName;
				
				if(confirm.equalsIgnoreCase("yes")) { // confirmation from user if their "selected" team is the home team for the match. 
					homeTeamName = selectedMatch.getHomeTeamName();
					awayTeamName = selectedMatch.getAwayTeamName();
				}
				else { //if the "selected" team is not the home team in the match, we swap the names as homeTeam score is recorded first to update the user's team total score. 
					homeTeamName = selectedMatch.getAwayTeamName();
					awayTeamName = selectedMatch.getHomeTeamName();
					selectedMatch.setAwayTeamName(awayTeamName);
					selectedMatch.setHomeTeamName(homeTeamName);
				}
				
				System.out.println(Color.CYAN_BOLD_BRIGHT + "[HomeTeam] Enter " + homeTeamName + "'s score: " + Color.RESET);
				int homeTeamScore = 0;
				try {
					homeTeamScore = scanner.nextInt();
				} catch (NumberFormatException e) {
					System.out.println(Color.BLACK_BACKGROUND + Color.RED_BOLD_BRIGHT + "📕: Please enter a numerical score." + Color.RESET);
					//e.printStackTrace();
				}
				scanner.nextLine();
				System.out.println(Color.CYAN_BOLD_BRIGHT + "[AwayTeam] Enter " + awayTeamName + "'s score: " + Color.RESET);
				int awayTeamScore =0;
				try {
					awayTeamScore = scanner.nextInt();
				} catch (NumberFormatException e) {
					System.out.println(Color.BLACK_BACKGROUND + Color.RED_BOLD_BRIGHT + "📕: Please enter a numerical score." + Color.RESET);
					//e.printStackTrace();
				}
				scanner.nextLine();
				
				//pass instance of the home team as a parameter to the method. 
				selectedMatch.setHomeTeam(team);
				//record match scores
				selectedMatch.recordResult(homeTeamScore, awayTeamScore);

				System.out.println(Color.BLACK_BOLD + Color.GREEN_BACKGROUND + "✅: Scores recorded." + Color.RESET);

				if (homeTeamScore > 0) { //Skip this part if no goals were scored in this match
					System.out.println(Color.CYAN_BOLD_BRIGHT + "Now lets record the player statistic for " + selectedMatch.getHomeTeamName() + " in this match" + Color.RESET);
					updateScoresheet(selectedMatch, homeTeamScore);
				}
				
				//update cautions received in the match if there are any. 
				System.out.println(Color.CYAN_BOLD_BRIGHT + "Are there any cautions to record? enter 'yes': " + Color.RESET);
				if(scanner.nextLine().equalsIgnoreCase("yes")) {
					upadateCautions();
					System.out.println(Color.BLACK_BOLD + Color.GREEN_BACKGROUND + "✅: Cautions recorded." + Color.RESET);
				}
				else {
					System.out.println(Color.BLACK_BOLD + Color.GREEN_BACKGROUND + "✅: Match details fully stored!" + Color.RESET);
				}
			} 
			else {
				System.out.println(Color.WHITE_BACKGROUND_BRIGHT + Color.RED_BOLD_BRIGHT + "❌: Match not found. Please try again" + Color.RESET);
			} 
		}
		else {
			System.out.println(Color.WHITE_BACKGROUND_BRIGHT + Color.RED_BOLD_BRIGHT + "⛔: No matches created." + Color.RESET);
		}

	}

	static void updateScoresheet(Match selectedMatch, int homeTeamScore) {
		boolean notValid; //flag to ensure the calculation is correct

		System.out.println(Color.CYAN_BOLD_BRIGHT + "Below is your roster for reference." + Color.RESET);
		team.displayRoster(); 
		System.out.println(Color.CYAN_BOLD_BRIGHT + "----------------------" + Color.RESET);

		Map<Integer, Integer> scoreValid = new HashMap<>();
		do {
			int totalGoalsRecorded = 0;
			notValid = false;
			scoreValid.clear(); //when this resets, we want to clear any previous entries in the map.
			outerloop: // used so we can break out of the entire flow and restart once the condition is not met
				for (int j = 0; j < 1; j++) {

					System.out.println(Color.CYAN_BOLD_BRIGHT + "How many different players scored for " + selectedMatch.getHomeTeamName() + " in this match?" + Color.RESET);
					int playerCount = scanner.nextInt();
					scanner.nextLine();
					if (playerCount <= homeTeamScore) {
						for (int i = 0; i < playerCount; i++) {

							System.out.println(Color.CYAN_BOLD_BRIGHT + "🔍 Enter PlayedID: " + Color.RESET);
							int playerID = scanner.nextInt();
							scanner.nextLine();
							System.out.println(Color.CYAN_BOLD_BRIGHT + "How many goal(s) did " + team.getPlayer(playerID).getName() + " score?: " + Color.RESET);
							int numGoals = scanner.nextInt();
							scanner.nextLine();

							if (numGoals <= homeTeamScore) {
								scoreValid.put(playerID, numGoals);
								//selectedMatch.recordPlayerGoal(playerID, numGoals);
								System.out.println(Color.BLACK_BOLD + Color.GREEN_BACKGROUND + "✅: Statistic for " + team.getPlayer(playerID).getName() + " has been recorded." + Color.RESET);
								totalGoalsRecorded += numGoals;
							} else {
								System.out.println(Color.WHITE_BACKGROUND_BRIGHT + Color.RED_BOLD_BRIGHT + 
										"❌: Player goal(s) cannot be greater than score. Please try again." + Color.RESET);
								notValid = true;
								//break; <--breaks out of the first for-loop but continues executing the subsequent line. Want to break out of the outer for loop instead. 
								break outerloop; //this fixes the above issue
							}
							System.out.println(Color.CYAN_BOLD_BRIGHT + "There are " + (homeTeamScore - totalGoalsRecorded) + " goals left to account for." + Color.RESET);
						}
					} else {
						System.out.println(Color.WHITE_BACKGROUND_BRIGHT + Color.RED_BOLD_BRIGHT + 
								"❌: # of scorers cannot be greater than score. Please try again." + Color.RESET);
						notValid = true;
						break outerloop;
					}
					if (totalGoalsRecorded < homeTeamScore) {
						System.out.println(Color.CYAN_BOLD_BRIGHT + "There are " + (homeTeamScore - totalGoalsRecorded)
								+ " goals not accounted for. How many would you like to record as " + selectedMatch.getAwayTeamName() + "'s own goals?" + Color.RESET);
						int ownGoals = scanner.nextInt();
						scanner.nextLine();
						totalGoalsRecorded += ownGoals;
					}
					if (totalGoalsRecorded != homeTeamScore) {
						notValid = true;
						System.out.println(Color.WHITE_BACKGROUND_BRIGHT + Color.RED_BOLD_BRIGHT + "❌: Team score does not match sum of goal(s) recorded. Please try again." + Color.RESET);
						break outerloop;
					} 
				}


		} while (notValid);

		//once the scores have been validated, they will now be used to updated the player statistic. 
		for (Map.Entry<Integer, Integer> scores : scoreValid.entrySet()) {
			selectedMatch.recordPlayerGoal(scores.getKey(), scores.getValue());
		}
	}

	static void upadateCautions() {
		System.out.println(Color.CYAN_BOLD_BRIGHT + "Below is your roster for reference." + Color.RESET);
		team.displayRoster();

		System.out.println(Color.YELLOW_BRIGHT + "Enter the playerID of each player that recieved a yellow card 🟨 in the follow format." + Color.RESET);
		System.out.println(Color.YELLOW_BRIGHT + "If only 1 🟨: (playerID). If 2 🟨: (playerID, 2)." + Color.YELLOW_BOLD_BRIGHT + " e.g. 9, 2" + Color.RESET);
		System.out.println(Color.CYAN_BOLD_BRIGHT + "enter 'exit' to exit" + Color.RESET);
		System.out.print("Entry: ");
		String input = scanner.nextLine();
		while(true) {
			if(input.equals("exit")) {
				break;
			}
			if(input.contains(",")) {
				String[] separate = input.split(", ");

				try {
					team.getPlayer(Integer.valueOf(separate[0])).setYellowCard(Integer.valueOf(separate[1]));
					team.getPlayer(Integer.valueOf(separate[0])).setRedCard(1);
				} catch (NumberFormatException e) {
					System.out.println(Color.BLACK_BACKGROUND + Color.RED_BOLD_BRIGHT + "📕: Incorrect format. Please try again." + Color.RESET);
					//e.printStackTrace();
				}


			}
			else {
				try {
					team.getPlayer(Integer.valueOf(input)).setYellowCard(1);
				} catch (NumberFormatException e) {
					System.out.println(Color.BLACK_BACKGROUND + Color.RED_BOLD_BRIGHT + "📕: Incorrect format. Please try again." + Color.RESET);
					//e.printStackTrace();
				}
			}
			System.out.print("Entry: ");
			input = scanner.nextLine();
		}

		System.out.println(Color.RED_BRIGHT + "Enter the playerID of each player that recieved a red card 🟥 in the follow format." + Color.RESET);
		System.out.println(Color.PURPLE_BRIGHT + "Exclude a player if 2 yellow cards were previously recorded." + Color.RESET);
		System.out.println(Color.CYAN_BOLD_BRIGHT + "enter 'exit' to exit" + Color.RESET);

		System.out.print("Entry: ");
		input = scanner.nextLine();
		while(true) {
			if(input.equals("exit")) {
				break;
			}
			try {
				team.getPlayer(Integer.valueOf(input)).setRedCard(1);
			} catch (NumberFormatException e) {
				System.out.println(Color.BLACK_BACKGROUND + Color.RED_BOLD_BRIGHT + "📕: Incorrect format. Please try again." + Color.RESET);
				//e.printStackTrace();
			}

			System.out.print("Entry: ");
			input = scanner.nextLine();

		}

	}

	static void manageRoster() {
		System.out.println(Color.CYAN_BACKGROUND_BRIGHT + Color.BLACK_BOLD + "Please select one of the options below!" + Color.RESET);
		System.out.println(Color.BLACK_BACKGROUND + Color.WHITE_BOLD_BRIGHT + "~~~~ [1️⃣] ADD A PLAYER    ~~~" + Color.RESET);
		System.out.println(Color.BLACK_BACKGROUND + Color.WHITE_BOLD_BRIGHT + "~~~~ [2️⃣] REMOVE A PLAYER ~~~" + Color.RESET);

		int choice = 0;
		try {
			choice = Integer.valueOf(scanner.nextLine());
		} catch (NumberFormatException e) {
			System.out.println(Color.BLACK_BACKGROUND + Color.RED_BOLD_BRIGHT + "📕: Please enter a number." + Color.RESET);
			//e.printStackTrace();
		}

		if (choice == 1 ) {
			System.out.println(Color.CYAN_BOLD_BRIGHT + "Use the following format - (firstname lastname, jersey number, position) - e.g.: Kylian Mbappe, 10, ST" + Color.RESET);
			String input = scanner.nextLine();
			try {
				playerAddition(input);
			} catch (Exception e) {
				System.out.println(Color.BLACK_BACKGROUND + Color.RED_BOLD_BRIGHT + "📕: failed to add player. Double check your formating." + Color.RESET);
				//e.printStackTrace();
			}

		}
		else if (choice == 2) {
			System.out.println(Color.CYAN_BOLD_BRIGHT + "Below is your team roster." + Color.RESET);
			team.displayRoster(); 
			System.out.println(Color.CYAN_BOLD_BRIGHT + "----------------------" + Color.RESET);
			System.out.println(Color.CYAN_BACKGROUND_BRIGHT + Color.BLACK_BOLD + "🔍Enter the PlayedID of the player you want to remove: " + Color.RESET);
			int playerID = scanner.nextInt();
			scanner.nextLine();

			if(team.getPlayer(playerID) != null) { //check if the provided player information exists in the roster. 
				System.out.println(Color.YELLOW_BOLD_BRIGHT + "❕: You are about to remove " + team.getPlayer(playerID).getName() + " from the roster. Enter yes to proceed." + Color.RESET);
				String confirm = scanner.nextLine();
				if (confirm.equalsIgnoreCase("yes")){
					String playerRemovedName = team.getPlayer(playerID).getName(); //when a player is removed their name variable is not available anymore, so storing it here will allow us to reference it in the line below.
					try {
						team.removePlayer(playerID); 
					} catch (NullPointerException e) {
						// TODO Auto-generated catch block
						System.out.println(Color.BLACK_BACKGROUND + Color.RED_BOLD_BRIGHT + "📕: Unexpected error. Please try again." + Color.RESET);
						//e.printStackTrace();
					}
					System.out.println(Color.BLACK_BOLD + Color.GREEN_BACKGROUND + "✅: " + playerRemovedName + " has been removed from the roster." + Color.RESET);
				}
				else {
					System.out.println(Color.WHITE_BACKGROUND_BRIGHT + Color.RED_BOLD_BRIGHT + "❌: Removal cancelled." + Color.RESET);
				}

			}
			else {
				System.out.println(Color.WHITE_BACKGROUND_BRIGHT + Color.RED_BOLD_BRIGHT + "⛔: " + team.getPlayer(playerID).getName() + "'s record was not found in the roster." + Color.RESET);
			}

		}
		else {
			System.out.println(Color.BLACK_BACKGROUND + Color.YELLOW_BOLD_BRIGHT + "📙: Incorrect input. Please try again." + Color.RESET);
		}

	}
	static void generateReport() {
		Report report = new Report(team);
		report.generateReports();
	}

}
