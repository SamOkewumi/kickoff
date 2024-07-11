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
	static ObjectMapper objectMapper = new ObjectMapper(); //initialize an objectmapper to read json file/url
	static Team team;

	public static void main(String[] args) {
		createTeam(); 
		displayMenu();
	}


	static void displayMenu() {

		int menuOption = 0;
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
				updateTeamName(); //replaces the default team name ; would like to make this a pre-menu task before they enter the full menu option
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
				running = false;
			}
			else {
				System.out.println(Color.BLACK_BACKGROUND + Color.YELLOW_BOLD_BRIGHT + "📙: Incorrect input, please try again" + Color.RESET);
			}


		} while (running);
		System.out.println(Color.CYAN_BACKGROUND_BRIGHT + Color.BLACK_BOLD +"Thank you for using 🚀KickOff™️" + Color.RESET);
		scanner.close();
	}

	static void createTeam() {
		System.out.println(Color.CYAN_BOLD_BRIGHT + "Team Name: " + Color.RESET);
		String teamName = scanner.nextLine();

		team = new Team(teamName);


	}

	static void updateTeamName() {
		System.out.println(Color.CYAN_BOLD_BRIGHT + "Updated Team Name: " + Color.RESET);
		String newName = scanner.nextLine();
		team.setTeamName(newName);
	}

	//ADD JUNIT TEST
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
			if (!(fileName.endsWith(".txt"))) {
				fileName += ".txt";
			}

			/*
			 * catch the exception in case the file is not found. 
			 */
			try {
				BufferedReader reader = new BufferedReader(new FileReader(fileName));
				String line;
				while ((line = reader.readLine()) != null) {
					playerAddition(line);

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
				if(input.equals("exit")) {
					break;
				}
				playerAddition(input);
				System.out.print("Entry: ");

			}

		}
		else {
			System.out.println(Color.BLACK_BACKGROUND + Color.YELLOW_BOLD_BRIGHT + "📙: Incorrect input. Please try again." + Color.RESET);
		}
	}

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

	static void viewTeam() {
		System.out.println(Color.CYAN_BACKGROUND_BRIGHT + Color.BLACK_BOLD + "👋Welcome, " + team.getTeamName() + ". This is your roster." + Color.RESET);
		team.displayRoster(); 

	}

	static void scheduleMatch() throws MalformedURLException,IOException {
		System.out.println(Color.CYAN_BACKGROUND_BRIGHT + Color.BLACK_BOLD + "How would you like to fill the matches?" + Color.RESET);
		System.out.println(Color.BLACK_BACKGROUND + Color.WHITE_BOLD_BRIGHT + "[1️⃣] Fill from an api." + Color.RESET);
		System.out.println(Color.BLACK_BACKGROUND + Color.WHITE_BOLD_BRIGHT + "[2️⃣] Manually add matches." + Color.RESET);

		int choice = 0;
		try {
			choice = Integer.valueOf(scanner.nextLine());
		} catch (NumberFormatException e) {
			System.out.println(Color.BLACK_BACKGROUND + Color.RED_BOLD_BRIGHT + "📕: Please enter a number." + Color.RESET);
			//e.printStackTrace();
		}

		//scanner.nextLine();
		URL jsonUrl;


		if(choice == 1) {
			System.out.println(Color.CYAN_BOLD_BRIGHT + "Enter the url 🔗" + Color.RESET);
			String url = scanner.nextLine();

			System.out.println(Color.CYAN_BOLD_BRIGHT + "⏳checking url..." + Color.RESET);

			jsonUrl = new URL(url); //throw MalformedURL
			JsonNode json = objectMapper.readTree(jsonUrl); //throw IOException

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
			} catch (NullPointerException e) {
				System.out.println(Color.BLACK_BACKGROUND + Color.RED_BOLD_BRIGHT + "📕: Mismatched return values in file provided." + Color.RESET);
				//e.printStackTrace();
			}
			System.out.println(Color.BLACK_BOLD + Color.GREEN_BACKGROUND + "✅: Match(s) has been created!" + Color.RESET);


		}
		else if(choice == 2) {
			//Store match event in Map and use eventID as the key to locate the matches
			System.out.println(Color.CYAN_BOLD_BRIGHT + "Enter the title of the event: " + Color.RESET);
			String title = scanner.nextLine();

			System.out.println(Color.CYAN_BOLD_BRIGHT + "Enter the description of the event: " + Color.RESET);
			String description = scanner.nextLine();

			System.out.println(Color.CYAN_BOLD_BRIGHT + "Enter the date of the event (DD/MM/YYYY): " + Color.RESET);
			String date = scanner.nextLine();

			System.out.println(Color.CYAN_BOLD_BRIGHT + "Enter the Location: " + Color.RESET);
			String location = scanner.nextLine();

			System.out.println(Color.CYAN_BOLD_BRIGHT + "Enter your team's name: " + Color.RESET);
			String homeTeamName = scanner.nextLine();

			System.out.println(Color.CYAN_BOLD_BRIGHT + "Enter the name of the opponent: " + Color.RESET);
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
		if (team.getTotalMatches() > 0 ) { //check if there are matches
			System.out.println(Color.CYAN_BOLD_BRIGHT + "Below are all your matches." + Color.RESET);

			team.displayMatches();//print out all the matches

			System.out.println(Color.CYAN_BACKGROUND_BRIGHT + Color.BLACK_BOLD + " 🔍 Enter the MatchID: " + Color.RESET);
			String matchID =  "";
			try {
				matchID = scanner.nextLine();
			} catch (NumberFormatException e) {
				System.out.println(Color.BLACK_BACKGROUND + Color.RED_BOLD_BRIGHT + "📕: Please enter a numerical matchID." + Color.RESET);
				//e.printStackTrace();
			}

			Match selectedMatch = team.getMatch(matchID);

			if (selectedMatch != null) { //make sure this event exists and has a value
				System.out.println(Color.CYAN_BOLD_BRIGHT + "Enter your team's score: " + Color.RESET);
				int homeTeamScore = 0;
				try {
					homeTeamScore = scanner.nextInt();
				} catch (NumberFormatException e) {
					System.out.println(Color.BLACK_BACKGROUND + Color.RED_BOLD_BRIGHT + "📕: Please enter a numerical score." + Color.RESET);
					//e.printStackTrace();
				}
				scanner.nextLine();
				System.out.println(Color.CYAN_BOLD_BRIGHT + "Enter the opponent's score: " + Color.RESET);
				int awayTeamScore =0;
				try {
					awayTeamScore = scanner.nextInt();
				} catch (NumberFormatException e) {
					System.out.println(Color.BLACK_BACKGROUND + Color.RED_BOLD_BRIGHT + "📕: Please enter a numerical score." + Color.RESET);
					//e.printStackTrace();
				}
				scanner.nextLine();

				selectedMatch.setHomeTeam(team);

				selectedMatch.recordResult(homeTeamScore, awayTeamScore);

				System.out.println(Color.BLACK_BOLD + Color.GREEN_BACKGROUND + "✅: Scores recorded." + Color.RESET);

				if (homeTeamScore > 0) { //if it was a no score event, we can skip this part
					System.out.println(Color.CYAN_BOLD_BRIGHT + "Now lets record the player statistic for this match" + Color.RESET);
					updateScoresheet(selectedMatch, homeTeamScore);

				}
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

	//ADD JUNIT TEST
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
			outerloop: // used so we can break out of the entire flow and restart when a condition is not met
				for (int j = 0; j < 1; j++) {

					System.out.println(Color.CYAN_BOLD_BRIGHT + "How many different players scored for your team in this match?" + Color.RESET);
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
								//break; //breaks out of the for loop but continues with the following line, we want it to start again.
								break outerloop; //fixes the above issue
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
								+ " goals not accounted for. How many would you like to record as own goals?" + Color.RESET);
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

		System.out.println("Enter the playerID of each player that recieved a yellow card 🟨 in the follow format.");
		System.out.println("If only 1 🟨: (playerID). If 2 🟨: (playerID, 2). e.g. 9, 2");
		System.out.println("enter 'exit' to exit");
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

		System.out.println("Enter the playerID of each player that recieved a red card 🟥 in the follow format.");
		System.out.println("If you have already recorded 2 yellows for a player you do not need to include them again.");
		System.out.println("enter 'exit' to exit");

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

			if(team.getPlayer(playerID) != null) {
				System.out.println(Color.YELLOW_BOLD_BRIGHT + "❕: You are about to remove " + team.getPlayer(playerID).getName() + " from the roster. Enter yes to proceed." + Color.RESET);
				String confirm = scanner.nextLine();
				if (confirm.equalsIgnoreCase("yes")){
					String playerRemovedName = team.getPlayer(playerID).getName(); //when removed, we won't have access to the name anympre, so storing it here will allow us to reference it in the following line.
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
		//System.out.println("Generation of reports complete.");
	}

}
