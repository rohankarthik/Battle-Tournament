package slapCompetition;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BeginBattle {
	private static final String filePath = BeginBattle.class.getClassLoader().getResource("candidates.csv").getPath();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] applicantsImported = extractContents();
		
		Applicant[] applicant = setValuesForApplicants(applicantsImported);

		initializeMatches(applicant);

		getTheWinner(applicant);
	}

	public static String[] extractContents() {
		FileReader file = null;
		BufferedReader buffer = null;
		String[] applicantsImported = new String[11];

		try {
			file = new FileReader(filePath);
			buffer = new BufferedReader(file);
			String line;

			int i = 0;
			while ((line = buffer.readLine()) != null) {
				applicantsImported[i] = line;
				i++;
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (buffer != null) {
					buffer.close();
					file.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return applicantsImported;
	}
	
	public static Applicant[] setValuesForApplicants(String[] applicantsImported) {
		String[][] candidates = new String[10][10];
		for (int i = 1; i < applicantsImported.length; i++) {
			candidates[i - 1] = applicantsImported[i].split(",");
		}

		Applicant applicant[] = new Applicant[10];

		for (int i = 0; i < candidates.length; i++) {
			applicant[i] = new Applicant();
			applicant[i].setName(candidates[i][0]);
			applicant[i].setHealth(Integer.parseInt(candidates[i][1]));
			applicant[i].setDamage(Integer.parseInt(candidates[i][2]));
			applicant[i].setAttacks(Integer.parseInt(candidates[i][3]));
			applicant[i].setNumberOfWins(0);

		}
		return applicant;
	}

	public static void initializeMatches(Applicant[] applicant) {
		BattleField battles[] = new BattleField[45];
		int matchCount = 0;

		for (int x = 0; x < 10; x++) {
			for (int y = x + 1; y < 10; y++) {
				matchCount = matchCount + 1;
				System.out.println("######################## MATCH NUMBER " + matchCount + " ###########################");
				battles[x] = new BattleField(applicant[x], applicant[y]);

			}
		}
	}
	
	public static void getTheWinner(Applicant[] applicant) {
		long maxValueOfWinPercentage = 0;
		int winnersIndex = 0;
		
		System.out.println("************************************************");
		System.out.println("");
		
		for (int i = 0; i < 10; i++) {
			long val = getWinPercentage(applicant[i]);
			
			if (maxValueOfWinPercentage < val) {
				maxValueOfWinPercentage = val;
				winnersIndex = i;
			}
		}
		
		System.out.println("");
		System.out.println("************************************************");
		System.out.println("");
		System.out.println("The Finalist of Applicants is "+ applicant[winnersIndex].getName());
		System.out.println("");
		System.out.println("************************************************");
	}

	private static long getWinPercentage(Applicant applicant) {
		// TODO Auto-generated method stub
		long winPercentage = applicant.getNumberOfWins() * 100 / 9;
		System.out.println("Number of wins for " + applicant.getName() + " are " +  applicant.getNumberOfWins() + "  -->  Win Percentage is : " + winPercentage);
		return winPercentage;
	}

}
