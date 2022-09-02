package rapports;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Rapports {
//	Cette rubrique contiendra les fonctionnalités suivantes
//	Liste des comptes fermés
//	Cette rubrique contiendra tous les comptes fermés.
//	Liste des comptes inactifs
//	Cette rubrique permettra de voir tous les comptes inactifs
//	Montant total dépôt de fonds
//	Cette rubrique permettra de savoir le montant total des dépôts effectués
//	Montant total retrait de fonds

	public void readAllTransactionsDepot() {
		double totalCash=0;
		String devise="";
		try {
			File file = new File("datas/transactions.txt");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			StringBuffer sb = new StringBuffer();

			String line;
			while ((line = br.readLine()) != null) {
				if(line.split("&&")[7].equalsIgnoreCase("depot")){
					String transc = "" + line.split("&&")[0] + "     (" +line.split("&&")[1]+")"+ line.split("&&")[2]
						+ "  " + line.split("&&")[3] + "     "
						+ line.split("&&")[4] + "            " + line.split("&&")[5]
						+ "        " + line.split("&&")[6] + "       "+line.split("&&")[7]
						;
				sb.append(transc);
				sb.append("\n\n");
				devise=line.split("&&")[6];
				totalCash+=Double.parseDouble(line.split("&&")[5]);
				}
			}
			fr.close();
//			4&&10273834&&14/06/2022&&129.0&&HTG&&depot
			System.err.println("\n\n Rapport total des depots ---------------------------------------\n\n");
			System.out
					.println("+------+-------------+----------+------------------------+-------------+-----------------+");
			System.out
					.println("| ID   |    Client   |   Compte |  Transaction fait le   |  Montant  | Devise  |  Type   |");
			System.out
					.println("+------+-------------+----------+------------------------+-------------+-----------------+");
			System.out.println(sb.toString().length()==0?" \t \t \t \t \t Aucun(s) depots":sb.toString());
			System.err.println("\t\t\t-----   Total depots: " +totalCash+""+devise+" --------");
			System.out
					.println("+------+-------------+----------+------------------------+-------------+-----------------+");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void readAllTransactionsRetrait() {
		double totalCash=0;
		String devise="";
		try {
			File file = new File("datas/transactions.txt");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			StringBuffer sb = new StringBuffer();

			String line;
			while ((line = br.readLine()) != null) {
				if(line.split("&&")[7].equalsIgnoreCase("retrait")){
					String transc = "" + line.split("&&")[0] + "     (" +line.split("&&")[1]+")"+ line.split("&&")[2]
						+ "  " + line.split("&&")[3] + "     "
						+ line.split("&&")[4] + "            " + line.split("&&")[5]
						+ "        " + line.split("&&")[6] + "       "+line.split("&&")[7]
						;
				sb.append(transc);
				sb.append("\n\n");
				totalCash+=Double.parseDouble(line.split("&&")[5]);
				}
			}
			fr.close();
//			4&&10273834&&14/06/2022&&129.0&&HTG&&depot
			System.err.println("\n\n---------------  Rapport total des retraits ---------------------------------------\n\n");
			System.out
					.println("+------+-------------+----------+------------------------+-------------+-----------------+");
			System.out
					.println("| ID   |    Client   |   Compte |  Transaction fait le   |  Montant  | Devise  |  Type   |");
			System.out
					.println("+------+-------------+----------+------------------------+-------------+-----------------+");
			System.out.println(sb.toString().length()==0?" \t \t \t \t \t Aucun(s) retraits":sb.toString());
			System.err.println("\t\t\t-----   Total retraits : " +totalCash+""+devise+" --------");
			System.out
					.println("+------+-------------+----------+------------------------+-------------+-----------------+");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void help(){
		System.err.println("Cette application permet de faire la gestion des comptes bancaires des clients"
				+ "\n  à travers les differents menus. Entrer les chiffres correspondant à votre choix.");
	}
	
	
	public void fermer(){
		System.err.println("Merci d'utiliser SysSgbanking ,au revoir...");
		System.exit(1);
	}

	public void readAllComptesFerme() {
		try {
			File file = new File("datas/comptes.txt");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			StringBuffer sb = new StringBuffer();

			String line;
			while ((line = br.readLine()) != null) {
				if(line.split("&&")[5].equalsIgnoreCase("ferme")){
				String client = "    " + line.split("&&")[0] + "  "
						+ line.split("&&")[1] + "   " + line.split("&&")[2]
						+ "   " + line.split("&&")[3] + "     "
						+ line.split("&&")[4] + "      " + line.split("&&")[5]
						+ "       (" + line.split("&&")[6]+" "+line.split("&&")[7]+")";
				sb.append(client);
				sb.append("\n\n");
				}
			}
			fr.close();
//			1097273832&&Courant&&13/06/2022&&800.0&&actif&&14
			System.out
					.println("+------+---------+----------+-----------+------------+-------------+-------------------+");
			System.out
					.println("| NoCompte   | Type  |  Cree le |  Solde  | Monnaie  |    Etat    |       Client       |");
			System.out
					.println("+------+---------+----------+-----------+------------+-------------+-------------------+");
			System.out.println(sb.toString());
			System.out
					.println("+------+---------+----------+-----------+------------+-------------+-------------------+");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void readAllComptesInactif() {
		try {
			File file = new File("datas/comptes.txt");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			StringBuffer sb = new StringBuffer();

			String line;
			while ((line = br.readLine()) != null) {
				if(line.split("&&")[5].equalsIgnoreCase("inactif")){
				String client = "    " + line.split("&&")[0] + "  "
						+ line.split("&&")[1] + "   " + line.split("&&")[2]
						+ "   " + line.split("&&")[3] + "     "
						+ line.split("&&")[4] + "      " + line.split("&&")[5]
						+ "     (" + line.split("&&")[6]+" "+line.split("&&")[7]+")";
				sb.append(client);
				sb.append("\n\n");
				}
			}
			fr.close();
//			1097273832&&Courant&&13/06/2022&&800.0&&actif&&14
			System.out
					.println("+------+---------+----------+-----------+------------+-------------+-------------------+");
			System.out
					.println("| NoCompte   | Type  |  Cree le |  Solde  | Monnaie  |    Etat     |      Client       |");
			System.out
					.println("+------+---------+----------+-----------+------------+-------------+-------------------+");
			System.out.println(sb.toString());
			System.out
					.println("+------+---------+----------+-----------+------------+-------------+-------------------+");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
