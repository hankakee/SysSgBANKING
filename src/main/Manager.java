package main;

import java.util.Scanner;

import rapports.Rapports;
import transactions.Transactions;
import clients.Clients;
import comptes.Comptes;

//import transactions.Transactions;

public class Manager {
	// nusuccursale =45
	// separator &&

	public void sous_menu_client() {

		Clients cl = new Clients();
		System.out.println("\n--------------------------CLIENTS---------------------------------");
		System.out.println("1) Enregistrer un client");
		System.out.println("2) Rechercher un client");
		System.out.println("3) Lister tous les clients");
		System.out.println("4) Modifier info d'un client");
		System.out.println("5) Retour");

		System.out.println("\n");
		System.out.print("Faire un choix: ");
		@SuppressWarnings("resource")
		int choixSecondaire = new Scanner(System.in).nextInt();
		if (choixSecondaire == 1) {
			cl.createClient();
			sous_menu_client();
		} else if (choixSecondaire == 2) {
			System.out.print("\n\n Entrer l'id du client que vous voulez rechercher: ");
			@SuppressWarnings("resource")
			int id = new Scanner(System.in).nextInt();
			cl.searchClientByID(id);
			 sous_menu_client();
		} else if (choixSecondaire == 3) {
			cl.readAllClients();
			 sous_menu_client();
		} else if (choixSecondaire == 4) {
			cl.modifyClient();
			 sous_menu_client();
		} else if (choixSecondaire == 5) {
			String[] args={};
			Manager.main(args);
		} else {
			System.err.println("Mauvais choix...");
			 sous_menu_client();
		}
	}

	
	public void sous_menu_compte() {
		Comptes co=new Comptes();
		System.out.println("\n--------------------------COMPTES---------------------------------");
		System.out.println("1) Ouvrir un compte");
		System.out.println("2) Rechercher un compte");
		System.out.println("3) Lister tous les comptes");
		System.out.println("4) Activer un compte");
		System.out.println("5) Desactiver un compte");
		System.out.println("6) Retour");
		System.out.println("\n");
		System.out.print("Faire un choix: ");
		@SuppressWarnings("resource")
		int choixSecondaire = new Scanner(System.in).nextInt();
		if (choixSecondaire == 1) {
co.createCompte();
			sous_menu_compte();
		}else if(choixSecondaire==2){
			System.out.print("Entrer le numero de compte à rechercher : ");
			@SuppressWarnings("resource")
			long numCompte = new Scanner(System.in).nextLong();
			co.readComptesByNoCompte(numCompte, true);
			sous_menu_compte();
		}else if(choixSecondaire==3){
			co.readAllComptes();
			sous_menu_compte();
		}else if(choixSecondaire==4){
			System.out.print("Entrer le numero de compte à rendre actif : ");
			@SuppressWarnings("resource")
			int numComptse = new Scanner(System.in).nextInt();
			co.setEtatCompte(numComptse, "actif");
			sous_menu_compte();
		}else if(choixSecondaire==5){
			System.out.print("Entrer le numero de compte à rendre inactif : ");
			@SuppressWarnings("resource")
			int numCompte2 = new Scanner(System.in).nextInt();
			co.setEtatCompte(numCompte2, "inactif");
			sous_menu_compte();
		}else if (choixSecondaire == 6) {
			String[] args={};
			Manager.main(args);
		} else {
			System.err.println("Mauvais choix...");
			sous_menu_compte();
		}
	}
	
	public void sous_menu_transactions() {
		System.out.println("\n--------------------------TRANSACTIONS---------------------------------");
		System.out.println("1) Enregistrer un depot");
		System.out.println("2) Enregistrer un retrait");
		System.out.println("3) Lister toutes les transactions");
		System.out.println("4) Fermer un compte");
		System.out.println("5) Produire interet compte");
		System.out.println("6) Retour");
		System.out.println("\n");
		System.out.print("Faire un choix: ");
//		new Scanner(System.in).close();
		@SuppressWarnings("resource")
		int choixSecondaire = new Scanner(System.in).nextInt();
		if (choixSecondaire == 1) {
			new Transactions().createTransaction("depot");
			try {
				Thread.sleep(3000);
				sous_menu_transactions();
			} catch (InterruptedException e) {
			}
		}else if(choixSecondaire==2){
			new Transactions().createTransaction("retrait");
			try {
				Thread.sleep(3000);
				sous_menu_transactions();
			} catch (InterruptedException e) {
			}
		}else if(choixSecondaire==3){
			new Transactions().readAllTransactions();
			sous_menu_transactions();
		}else if(choixSecondaire==4){
			System.out.print("Entrer le numero de compte à fermer : ");
		
			Scanner scnumComptse=new Scanner(System.in);
			int numComptse = scnumComptse.nextInt();
			new Comptes().setEtatCompte(numComptse, "ferme");
			scnumComptse.close();
			sous_menu_transactions();
		}else if(choixSecondaire==5){
			System.out.print("Entrer le numero de compte à produire l'interet : ");
			@SuppressWarnings("resource")
			long numCompte3 = new Scanner(System.in).nextLong();
			new Transactions().readInteretComptes(numCompte3);
			sous_menu_transactions();
		}else if (choixSecondaire == 6) {
			String[] args={};
			Manager.main(args);
		} else {
			System.err.println("Mauvais choix...");
			sous_menu_transactions();
		}
	}
	
	public void sous_menu_rapports(){
		System.out.println("\n-------------------------- RAPPORTS ---------------------------------");
		
		System.out.println("1) Liste des comptes fermés ");
		System.out.println("2) Liste des comptes inactifs");
		System.out.println("3) Montant total dépôt de fonds");
		System.out.println("4) Montant total retrait de fonds");
		System.out.println("5) Retour");
		System.out.println("\n");
		
		@SuppressWarnings("resource")
		int choixSecondairerapports = new Scanner(System.in).nextInt();
		if (choixSecondairerapports == 1) {
			new Rapports().readAllComptesFerme();
			try {
				Thread.sleep(2000);
				sous_menu_rapports();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}else if (choixSecondairerapports == 2) {
			new Rapports().readAllComptesInactif();
			try {
				Thread.sleep(2000);
				sous_menu_rapports();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}else if (choixSecondairerapports == 3) {
			new Rapports().readAllTransactionsDepot();
			try {
				Thread.sleep(2000);
				sous_menu_rapports();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}else if (choixSecondairerapports == 4) {
			new Rapports().readAllTransactionsRetrait();
			try {
				Thread.sleep(2000);
				sous_menu_rapports();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else if (choixSecondairerapports == 5) {
			String[] args={};
			Manager.main(args);
		} else {
			System.err.println("Mauvais choix...");
			sous_menu_transactions();
		}
	}
	
	public static void main(String[] args) {

		// ----------MENU PRINCIPAL---------

		try {

			System.out
					.println("*****************************************************************************************");
			System.out
					.println("************************************   SyS SG Banking ***********************************");
			System.out
					.println("*****************************************************************************************");
			System.out.println("\n\n");

			System.out.println("1) Gestion des clients");
			System.out.println("2) Gestion des comptes bancaires");
			System.out.println("3) Gestion des transactions bancaires");
			System.out.println("4) Gestion des rapports bancaires");
			System.out.println("5) Sortie du programme");
			System.out.println("6) Aide");

			System.out.println("\n");
			System.out.print("Faire un choix: ");
			 Scanner tmpscintchoixprincipal = new Scanner(System.in);
//			@SuppressWarnings("resource")
			int choicemenuPrincipal = tmpscintchoixprincipal.nextInt();
		
			// CLIENTS
			if (choicemenuPrincipal == 1) {
				new Manager().sous_menu_client();
			}
			// COMPTES
			else if (choicemenuPrincipal == 2) {
				new Manager().sous_menu_compte();
			}
			// TRANSACTIONS
			else if (choicemenuPrincipal == 3) {
				new Manager().sous_menu_transactions();
			}
			// RAPPORTS
			else if (choicemenuPrincipal == 4) {
				new Manager().sous_menu_rapports();
			} else if (choicemenuPrincipal == 5) {
				new Rapports().fermer();

			} else if (choicemenuPrincipal == 6) {
				new Rapports().help();
				String[] argsa={};
				Manager.main(argsa);
			} else {
				System.err.println("Mauvais choix...");
			}
			 tmpscintchoixprincipal.close();
		} catch (Exception e) {
			e.printStackTrace();
//			System.err.println(e.getMessage());
//			System.err.println("Vous avez fait un mauvais choix , sortie de l'application");
		}
		// choicemenuPrincipal==1 || choicemenuPrincipal==2 ||
		// choicemenuPrincipal==3 || choicemenuPrincipal==4 ||
		// choicemenuPrincipal==5 || choicemenuPrincipal==6

		// TODO Auto-generated method stub
		// Date date = new Date();
		// SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
		// String stringDate= DateFor.format(date);
		// System.out.println(new Clients().searchClientByID(14));
		// new Comptes().readComptesByNoCompte(10273833);
		// new Transactions().createTransaction("depot");
		// new Transactions().createTransaction("retrait");
		// new Transactions().createTransaction("ferme");
		// new Transactions().readAllTransactions();

		// fermer un compte
		// new Comptes().setEtatCompte(10273833,"ferme");

		// Interet compte
		// new Transactions().readInteretComptes(10273833);

		// new Rapports().fermer();

		// comptes ferme
		// new Rapports().readAllComptesFerme();

		// compte inactif
		// new Rapports().readAllComptesInactif();

		// rapports total depots
		// new Rapports().readAllTransactionsDepot();

		// rapports total retrait
		// new Rapports().readAllTransactionsRetrait();

		// new Comptes().readAllComptes();
	}

}
