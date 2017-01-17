package slapCompetition;

import java.util.Random;

public class BattleField {

	int firstApplicantAttacks;
	int secondApplicantAttacks;
	int firstApplicantHealth;
	int secondApplicantHealth;

	public BattleField(Applicant attacker, Applicant defender) {
		int number = generateRandomNumber();		
		
		getHealthAndAttacks(attacker, defender);
		
		printApplicantsInfo(attacker, defender);		
		printTheFirstAttacker(number);
		
		startSlapping(attacker, defender, number);
		winner(attacker, defender);

		setHealthAndAttacks(attacker, defender);
	}

	public int generateRandomNumber() {
		Random randomNumber = new Random();
		int number = randomNumber.nextInt(2) + 1;
		return number;
	}
	
	public void getHealthAndAttacks(Applicant attacker, Applicant defender) {
		firstApplicantHealth = attacker.getHealth();
		secondApplicantHealth = defender.getHealth();

		firstApplicantAttacks = attacker.getAttacks();
		secondApplicantAttacks = defender.getAttacks();
	}
	
	public void printApplicantsInfo(Applicant attacker, Applicant defender) {
		System.out.println("");
		
		System.out.println("Applicant 1: " + attacker.getName());
		System.out.println("\t Health: " + firstApplicantHealth);
		System.out.println("\t Damage: " + attacker.getDamage());
		System.out.println("\t Attacks: " + firstApplicantAttacks);
		
		System.out.println("");
		
		System.out.println("Applicant 2: " + defender.getName());
		System.out.println("\t Health: " + secondApplicantHealth);
		System.out.println("\t Damage: " + defender.getDamage());
		System.out.println("\t Attacks: " + secondApplicantAttacks);
		
		System.out.println("");
	}

	public void printTheFirstAttacker(int number) {
		System.out.println("Applicant " + number + " is chosen randomly to start slapping ");
		System.out.println("");
	}

	private void startSlapping(Applicant attacker, Applicant defender, int number) {
		// TODO Auto-generated method stub
		System.out.println("");
		System.out.println("ROUND STARTS");
		System.out.println("");
		
		int totalAttacks = attacker.getAttacks() + defender.getAttacks();
		
		boolean randomApplicantSelection = true;
		boolean AlternateSelection = false;
		
		while (attacker.getHealth() > 0 && defender.getHealth() > 0 && totalAttacks > 0) {

			if (attacker.getAttacks() > 0 && randomApplicantSelection == true && number == 1) {
				int healthBeforeAttack = defender.getHealth();
				
				slap(attacker, defender);
				
				totalAttacks = attacker.getAttacks() + defender.getAttacks();
				
				System.out.println(attacker.getName() + " hits " + defender.getName() + " for " + attacker.getDamage() + 
						" damage (" + healthBeforeAttack + " -> " + defender.getHealth() + ")");
						
				randomApplicantSelection = false;
				AlternateSelection = true;
			} else if (number == 2 && defender.getAttacks() > 0 && randomApplicantSelection == true) {
				int healthBeforeAttack = attacker.getHealth();
				
				slap(defender, attacker);
				
				totalAttacks = attacker.getAttacks() + defender.getAttacks();

				System.out.println(defender.getName() + " hits " + attacker.getName() + " for " + defender.getDamage() + 
						" damage (" + healthBeforeAttack + " -> " + attacker.getHealth() + ")");
				
				randomApplicantSelection = false;
			}

			if (defender.getAttacks() > 0 && AlternateSelection == true) {
				int healthBeforeAttack = attacker.getHealth();
				
				slap(defender, attacker);
				
				totalAttacks = attacker.getAttacks() + defender.getAttacks();

				System.out.println(defender.getName() + " hits " + attacker.getName() + " for " + defender.getDamage() + 
						" damage (" + healthBeforeAttack + " -> " + attacker.getHealth() + ")");
				
				AlternateSelection = false;
			} else if (attacker.getAttacks() > 0 && AlternateSelection == false) {
				int healthBeforeAttack = defender.getHealth();

				slap(attacker, defender);
				
				totalAttacks = attacker.getAttacks() + defender.getAttacks();
				
				System.out.println(attacker.getName() + " hits " + defender.getName() + " for " + attacker.getDamage() + 
						" damage (" + healthBeforeAttack + " -> " + defender.getHealth() + ")");
				
				AlternateSelection = true;
			}

			if (defender.getAttacks() > 0 && attacker.getAttacks() <= 0) {
				int healthBeforeAttack = attacker.getHealth();

				slap(defender, attacker);
				totalAttacks = attacker.getAttacks() + defender.getAttacks();


				System.out.println(defender.getName() + " hits " + attacker.getName() + " for " + defender.getDamage() + 
						" damage (" + healthBeforeAttack + " -> " + attacker.getHealth() + ")");
			}
			if (attacker.getAttacks() > 0 && defender.getAttacks() <= 0) {
				int healthBeforeAttack = defender.getHealth();

				slap(attacker, defender);
				totalAttacks = attacker.getAttacks() + defender.getAttacks();


				System.out.println(attacker.getName() + " hits " + defender.getName() + " for " + attacker.getDamage() + 
						" damage (" + healthBeforeAttack + " -> " + defender.getHealth() + ")");
			}

		}

		if (attacker.getHealth() > 0 && defender.getHealth() > 0) {
			int randomNumber = generateRandomNumber();
			
			attacker.setAttacks(firstApplicantAttacks);
			defender.setAttacks(secondApplicantAttacks);
			
			startSlapping(attacker, defender, randomNumber);
		}
	}

	private void slap(Applicant attacker, Applicant defender) {
		// TODO Auto-generated method stub
		int damage = attacker.getDamage();
		
		int healthRemaining = defender.getHealth() - damage;
		defender.setHealth(healthRemaining);

		int attacksLeft = attacker.getAttacks() - 1;
		attacker.setAttacks(attacksLeft);
	}

	private void winner(Applicant attacker, Applicant defender) {
		// TODO Auto-generated method stub
		if (attacker.getHealth() <= 0) {
			System.out.println("");
			System.out.println(defender.getName() + " WINS!");
			System.out.println("");
			
			defender.setNumberOfWins(defender.getNumberOfWins() + 1);
		} else if (defender.getHealth() <= 0) {
			System.out.println("");
			System.out.println(attacker.getName() + " WINS!");
			System.out.println("");
			
			attacker.setNumberOfWins(attacker.getNumberOfWins() + 1);
		}
	}
	
	public void setHealthAndAttacks(Applicant attacker, Applicant defender) {
		attacker.setHealth(firstApplicantHealth);
		defender.setHealth(secondApplicantHealth);
		attacker.setAttacks(firstApplicantAttacks);
		defender.setAttacks(secondApplicantAttacks);
	}
}
