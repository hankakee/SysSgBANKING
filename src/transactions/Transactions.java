package transactions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import comptes.Comptes;

public class Transactions {

//	L’id du client, le numéro du compte, la date de la transaction, le montant à déposer. 
	private int idclient;
	private long numCompte;
	 private String dateTransaction;
	private double montant;
	private String typeTransaction;
	
	public Transactions() {
	}

	public Transactions(int idclient, long numCompte, String dateTransaction,
			double montant, String typeTransaction) {
		super();
		this.idclient = idclient;
		this.numCompte = numCompte;
		this.dateTransaction = dateTransaction;
		this.montant = montant;
		this.typeTransaction = typeTransaction;
	}

	public int getIdclient() {
		return idclient;
	}

	public void setIdclient(int idclient) {
		this.idclient = idclient;
	}

	public long getNumCompte() {
		return numCompte;
	}

	public void setNumCompte(long numCompte) {
		this.numCompte = numCompte;
	}

	public String getDateTransaction() {
		return dateTransaction;
	}

	public void setDateTransaction(String dateTransaction) {
		this.dateTransaction = dateTransaction;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public String getTypeTransaction() {
		return typeTransaction;
	}

	public void setTypeTransaction(String typeTransaction) {
		this.typeTransaction = typeTransaction;
	}

	public int findLastIdTransaction() {
		int lastid = 0;
		int max=150;
		try {
			File file = new File("datas/transactions.txt");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line;
			while ((line = br.readLine()) != null) {
				lastid = Integer.parseInt(line.split("&&")[0]);
				if(lastid>max){
					max=lastid;
				}
			}
			fr.close();
		} catch (IOException e) {
			System.out.println("Deniere transaction inconnue..."+e.getMessage());
//			e.printStackTrace();
		}
		return max;
	}

	
	public void readAllTransactions() {
		try {
			File file = new File("datas/transactions.txt");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			StringBuffer sb = new StringBuffer();

			String line;
			while ((line = br.readLine()) != null) {
				
					String transc = "" + line.split("&&")[0] + "     (" +line.split("&&")[1]+")"+ line.split("&&")[2]
						+ "  " + line.split("&&")[3] + "     "
						+ line.split("&&")[4] + "            " + line.split("&&")[5]
						+ "        " + line.split("&&")[6] + "       "+line.split("&&")[7]
						;
				sb.append(transc);
				sb.append("\n\n");
			}
			fr.close();
//			4&&10273834&&14/06/2022&&129.0&&HTG&&depot
			System.out
					.println("+------+-------------+----------+------------------------+-------------+---------------+");
			System.out
					.println("| ID   |    Client   |   Compte |  Transaction fait le   |  Montant  | Devise  |  Type   |");
			System.out
					.println("+------+-------------+----------+------------------------+-------------+---------------+");
			System.out.println(sb.toString());
			System.out
					.println("+------+-------------+----------+------------------------+-------------+---------------+");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void createTransaction(String type){
		Transactions thistransaction=new Transactions();
		Scanner scanint=new Scanner(System.in);
		
//		fullname
//		L’id du client, le numéro du compte, la date de la transaction, le montant à retirer,type retrait ou depot,monnaie
		try {
			System.out.println("Entrer le numero de compte du client:" );
			long tnumcompte=scanint.nextLong();
			//verifier si existe dabord
			String infoCompte=new Comptes().readComptesByNoCompte(tnumcompte,true);
			if(infoCompte.equalsIgnoreCase("no account")){
//				System.err.println("Transaction echouee,numero compte invalide...");
				scanint.close();
				return;
			}
			String etatCompte =infoCompte.split("&&")[5].trim();
			System.out.println("Etat du compte : "+etatCompte);
			if(etatCompte.equalsIgnoreCase("inactif") || etatCompte.equalsIgnoreCase("ferme")){
				System.err.println("Aucune action ne peut etre effectué sur ce compte...");
				scanint.close();
				return;
			}else{
				double montantOnAccount=Double.parseDouble(infoCompte.split("&&")[3]);
				String devise =infoCompte.split("&&")[4];
//				solde
				System.out.println("ETAT:"+type);
				if(type=="retrait"){
					System.out.print("\n Entrer le montant du retrait au solde du client:");
				}else if(type=="depot"){
					System.out.print("\n Entrer le montant du depot au solde du client:");
				}
//				scanint.nextLine();
				double solde=scanint.nextFloat();
				System.out.println("SOLDO:"+solde);
//				thistransaction.setMontant(solde);
				
				int tidclient=Integer.parseInt(infoCompte.split("&&")[6]);
				String fullnameClient=infoCompte.split("&&")[7];
				thistransaction.setIdclient(tidclient);
				thistransaction.setTypeTransaction(type);
				thistransaction.setNumCompte(tnumcompte);
				Date date = new Date();
				SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
			    thistransaction.setDateTransaction(DateFor.format(date));
				
			    
				if(type=="retrait"){
					double newargentretrait=montantOnAccount-solde;
					System.out.println("Solde entré :"+solde);
					if(solde<=0){
						System.err.println("-----------------Le montant entré est incorrect... -------------------");
						scanint.close();
						return;
					}else{
						if((newargentretrait < 500 && devise.equalsIgnoreCase("HTG")) || (newargentretrait <10 && devise.equalsIgnoreCase("USD"))){
							System.err.println("---------------------  Le solde restant doit etre superieur ou  egal a 500HTG ou 10USD  ------------------------");
							scanint.close();
							return;
						}else{
								new Comptes().setArgent(tnumcompte,newargentretrait);
							}
					}
					
				}else if(type=="depot"){
					if(solde<=0){
						System.err.println("-----------------Le montant entré est incorrect... -------------------");
					}else{
						double newargentdepot=montantOnAccount+solde;
					new Comptes().setArgent(tnumcompte,newargentdepot);
					}
					
				}
				//line transaction 
				try {
					BufferedWriter bw;
					int newidtrans=findLastIdTransaction()+1;
//					L’id du client, le numéro du compte, la date de la transaction, le montant à retirer,type retrait ou depot,monnaie
					String trnas = newidtrans+"&&"+thistransaction.getIdclient()+"&&"+fullnameClient+ "&&"+ thistransaction.getNumCompte() + "&&" + thistransaction.getDateTransaction() + "&&"+ solde + "&&" + devise+ "&&" + type;
					File file = new File("datas/transactions.txt");
					bw = new BufferedWriter(new FileWriter(file, true));
					bw.append(trnas);
					bw.newLine();
					bw.close();
					System.out.println("Transaction ajoute avec succes!");
				} catch (IOException e) {
					System.err.println("Erreur la transaction n'a pas ete ajoute...");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Format non respecté lors de la saisie.../n Transaction non enregistree");
		}
	}
	
//	Si le solde<100000 alors le taux d’intérêt est 2.5%
//	Si le solde>=100000 et solde<500000 alors le taux d’intérêt sera de 3.5%
//	Si le solde>=500000 et solde<2500000 alors le taux d’intérêt sera de 5%
//	Si le solde >=2500000 alors le taux d’intérêt sera de 7%

	private double findInteret(double solde){
		double pourcentage=0;
		if( solde<100000){
			pourcentage=2.5f;
		}else if(solde>=100000 && solde<500000){
			pourcentage=3.5f;
		}
		else if(solde>=500000 && solde<2500000){
			pourcentage=5;
		}else if( solde >=2500000){
			pourcentage=7;
		}
		return pourcentage;
	}
	
	private double calculInteret(double solde){
		double interet=findInteret(solde);
		return interet*solde;
	}
	
	
	public void readInteretComptes(long nocompte) {
		if(new Comptes().readComptesByNoCompte(nocompte,false).equalsIgnoreCase("no account")){
			System.err.println("Aucun compte trouvé pour ce numero...");
			return;
		}
		else if(!(new Comptes().VerifEtatCompte(nocompte).equals("actif"))){
			System.err.println("Desole,Ce compte n'est pas autorisé à produire des interets...");
			return;
		}
		else{
			try {
				File file = new File("datas/comptes.txt");
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				StringBuffer sb = new StringBuffer();

				String line;
				while ((line = br.readLine()) != null) {
					if((!line.split("&&")[5].equalsIgnoreCase("ferme")) && (Long.parseLong(line.split("&&")[0])==nocompte)){
					String client = "  " + line.split("&&")[0] + "    "
							+ line.split("&&")[1] + "    " + line.split("&&")[2]
							+ "    " + line.split("&&")[3] + "        "
							+ line.split("&&")[4] + "              " + line.split("&&")[5]
							+ "           (" + line.split("&&")[6]+" "+line.split("&&")[7]+")     "+ findInteret(Double.parseDouble(line.split("&&")[3]))+"%       "+calculInteret(Double.parseDouble(line.split("&&")[3]))+""+line.split("&&")[4];
					sb.append(client);
//					sb.append("\n");
					break;
					}
				}
				fr.close();
//				1097273832&&Courant&&13/06/2022&&800.0&&actif&&14
				System.err.println("\n------------------ Attention les informations presentes ci-dessous ne sont pas enregistrées... -------------------");
				System.out.println("Interets produits par ce compte...\n\n");
				System.out
						.println("+------------+----------+-----------+------------+-------------+-------------------+--------------------+-----------+-------------+");
				System.out
						.println("| NoCompte   | Type     |  Cree le  |  Solde     | Monnaie     |    Etat           |       Client       |    Taux   |   Interet   |");
				System.out
						.println("+------------+----------+-----------+------------+-------------+-------------------+--------------------|-----------+-------------+");
				System.out.println(sb.toString());
				System.out
						.println("+------------+----------+-----------+------------+-------------+-------------------+--------------------|-----------+-------------+");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
}