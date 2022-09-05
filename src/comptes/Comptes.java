package comptes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import clients.Clients;
import database.Jdbcmanager;

public class Comptes {

//	Lï¿½id du client, le numï¿½ro du compte, le type de compte (ï¿½pargne ou courant), date de crï¿½ation 
//	du compte, le solde minimal dï¿½ouverture, la devise du compte . 
	private int idclient	;
	private long numCompte;
	private String typecompte;  //Epargne ou courant;
	private String dateCreated; //genere;
	private double solde;
	private String devise; //(Gdes ou dollar);
	private String statut;
	
	public Comptes() {
		
	}
	
	public Comptes(int idclient, long numCompte, String typecompte, String dateCreated, double solde, String devise,
			String statut) {
		this.idclient = idclient;
		this.numCompte = numCompte;
		this.typecompte = typecompte;
		this.dateCreated = dateCreated;
		this.solde = solde;
		this.devise = devise;
		this.statut = statut;
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
	public String getTypecompte() {
		return typecompte;
	}
	public void setTypecompte(String typecompte) {
		this.typecompte = typecompte;
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	public double getSolde() {
		return solde;
	}
	public void setSolde(double solde) {
		this.solde = solde;
	}
	public String getDevise() {
		return devise;
	}
	public void setDevise(String devise) {
		this.devise = devise;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}

	
	
	
	@Override
	public String toString() {
		return "Comptes [idclient=" + idclient + ", numCompte=" + numCompte
				+ ", typecompte=" + typecompte + ", dateCreated=" + dateCreated
				+ ", solde=" + solde + ", devise=" + devise + ", statut="
				+ statut + "]";
	}

	
	
	public int findLastAccountNumber() {
		int max=0;
		try {
			Statement st=null;
			st = Jdbcmanager.ConnexionDb().createStatement();
			ResultSet res=st.executeQuery("SELECT MAX(noCompte) as maxno from comptes");
			if(res.next()) {
				max=res.getInt("maxno");
			}
			System.out.println("le max bro: "+max);
			res.close();
			st.close();
		} catch (SQLException e) {
			System.out.println("Denier compte inconnu..."+e.getMessage());
//			e.printStackTrace();
		}
		return max;
	}
	
	
	public void readAllComptes() {
		  ResultSet rstab = null;
	        Statement st=null;
		try {
//			File file = new File("datas/comptes.txt");
//			FileReader fr = new FileReader(file);
//			BufferedReader br = new BufferedReader(fr);
			StringBuffer sb = new StringBuffer();
//
//			String line;
//			while ((line = br.readLine()) != null) {
//				if(!line.split("&&")[5].equalsIgnoreCase("ferme")){
//				String client = "    " + line.split("&&")[0] + "  "
//						+ line.split("&&")[1] + "   " + line.split("&&")[2]
//						+ "   " + line.split("&&")[3] + "     "
//						+ line.split("&&")[4] + "      " + line.split("&&")[5]
//						+ "       (" + line.split("&&")[6]+" "+line.split("&&")[7]+")";
//				sb.append(client);
//				sb.append("\n\n");
//				}
//			}
//			fr.close();
//			1097273832&&Courant&&13/06/2022&&800.0&&actif&&14
			st = Jdbcmanager.ConnexionDb().createStatement();
			rstab= st.executeQuery("select * from comptes inner join clients on clients.id=comptes.id;");
			while(rstab.next()){
				String client = "    " + rstab.getInt("noCompte") + "  "
						+ rstab.getString("type") + "   " + rstab.getString("creele")
						+ "   " + rstab.getFloat("solde") + "     "
						+ rstab.getString("monnaie") + "      " + rstab.getString("etat")
						+ "       (" + rstab.getInt("idclient")+" "+rstab.getString("Client")+")";
				sb.append(client);
				sb.append("\n\n");
			}
			System.out
					.println("+------+---------+----------+-----------+------------+-------------+-------------------+");
			System.out
					.println("| NoCompte   | Type  |  Cree le |  Solde  | Monnaie  |    Etat    |       Client       |");
			System.out
					.println("+------+---------+----------+-----------+------------+-------------+-------------------+");
			System.out.println(sb.toString());
			System.out
					.println("+------+---------+----------+-----------+------------+-------------+-------------------+");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	
	public String readComptesByNoCompte(long numCompte,boolean show) {
		try {
//			File file = new File("datas/comptes.txt");
//			FileReader fr = new FileReader(file);
//			BufferedReader br = new BufferedReader(fr);
			StringBuffer sb = new StringBuffer();

			String line;
			boolean found = false;
			Statement st=null;
			ResultSet rstab=null;
			st = Jdbcmanager.ConnexionDb().createStatement();
			rstab= st.executeQuery("select * from comptes inner join clients on clients.id=comptes.id and comptes.noCompte="+numCompte+";");
			while(rstab.next()){
				String client = "    " + rstab.getInt("noCompte") + "  "
						+ rstab.getString("type") + "   " + rstab.getString("creele")
						+ "   " + rstab.getFloat("solde") + "     "
						+ rstab.getString("monnaie") + "      " + rstab.getString("etat")
						+ "       (" + rstab.getInt("idclient")+" "+rstab.getString("Client")+")";
				sb.append(client);
				sb.append("\n\n");
				found=true;
			}
			if(show){
				System.out
					.println("+------+---------+----------+-----------+------------+-------------+-------------------+");
			System.out
					.println("| NoCompte   | Type  |  Cree le |  Solde  | Monnaie  |  Etat | Client  |");
			System.out
					.println("+------+---------+----------+-----------+------------+-------------+-------------------+");
			System.out.println(sb.toString());
			System.out.println("+------+---------+----------+-----------+------------+-------------+-------------------+");
			}
			if (!found) {
				if(show){
				System.err.println("Aucun compte ayant l'id " + numCompte);
				}
				
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "no account";
	}
	
	
	public String VerifEtatCompte(long numCompte) {
		try {
			File file = new File("datas/comptes.txt");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line;
			while ((line = br.readLine()) != null) {
				if (Long.parseLong(line.split("&&")[0]) == numCompte) {
					br.close();
					return line.split("&&")[5];
				}
			}
			fr.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "no account";
	}
	
	
//	Rendre un compte inactif/Actif
	public String setEtatCompte(int numCompte,String tmpStatusCompte) {
		ArrayList<String> arComptes=new ArrayList<String>();
		try {
//			File file = new File("datas/comptes.txt");
//			FileReader fr = new FileReader(file);
//			BufferedReader br = new BufferedReader(fr);

			String line;
			boolean found = false;
//			while ((line = br.readLine()) != null) {
//				if (Integer.parseInt(line.split("&&")[0]) == numCompte) {
////					10273834&&Courant&&13/06/2022&&3000.0&&HTG&&actif&&4&&Grady McCarle
//					String compte = line.split("&&")[0] + "&&"+ line.split("&&")[1] + "&&" + line.split("&&")[2] + "&&"+ line.split("&&")[3] + "&&" + line.split("&&")[4]+ "&&" + tmpStatusCompte + "&&"+ line.split("&&")[6] + "&&" + line.split("&&")[7];
//					 arComptes.add(compte);
//					 found=true;
//				}else{
//					String comptenormal = line.split("&&")[0] + "&&"+ line.split("&&")[1] + "&&" + line.split("&&")[2] + "&&"+ line.split("&&")[3] + "&&" + line.split("&&")[4]+ "&&" + line.split("&&")[5] + "&&"+ line.split("&&")[6] + "&&" + line.split("&&")[7];
//					 arComptes.add(comptenormal);
//				}
//				
//			}
//			br.close();
//			fr.close();
			
//			if(!found){
//				System.out.println("Ce compte n'existe pas!!!");
//				return "no account";
//			}
//			//effacer les anciennes donnees 
//			FileWriter fw3= new FileWriter(file);
//			BufferedWriter bw3=new BufferedWriter(fw3);
//			bw3.write("");bw3.close();fw3.close();
////			--------reecriture dans le vrai fichier....
//			BufferedWriter bw4=new BufferedWriter(new FileWriter(file,true));
//				for(String compte : arComptes){
//					bw4.append(compte);
//					bw4.newLine();
//				}
//				bw4.close();
				
			Statement st=null;
			st = Jdbcmanager.ConnexionDb().createStatement();
			String req = String.join(" ","update COMPTES set etat='",tmpStatusCompte+"'",
					" where noCompte=",numCompte+";");
//			System.out.println(req);
			st.executeUpdate(req);
			System.out.printf("\nCe compte client est passe à l'etat %s...\n",tmpStatusCompte);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "modified";
	}
	
	

	
	
	public String setArgent(long numCompte,double montant) {
		ArrayList<String> arComptes=new ArrayList<String>();
		try {
//			File file = new File("datas/comptes.txt");
//			FileReader fr = new FileReader(file);
//			BufferedReader br = new BufferedReader(fr);

			String line;
			boolean found = false;
			System.out.println("montant enter:"+ montant);
//			while ((line = br.readLine()) != null) {
//				if (Long.parseLong(line.split("&&")[0]) == numCompte) {
////					10273834&&Courant&&13/06/2022&&3000.0&&HTG&&actif&&4&&Grady McCarle
//					String compte = line.split("&&")[0] + "&&"+ line.split("&&")[1] + "&&" + line.split("&&")[2] + "&&"+ montant + "&&" + line.split("&&")[4]+ "&&" + line.split("&&")[5] + "&&"+ line.split("&&")[6] + "&&" + line.split("&&")[7];
//					 arComptes.add(compte);
//					 found=true;
//				}else{
//					String comptenormal = line.split("&&")[0] + "&&"+ line.split("&&")[1] + "&&" + line.split("&&")[2] + "&&"+ line.split("&&")[3] + "&&" + line.split("&&")[4]+ "&&" + line.split("&&")[5] + "&&"+ line.split("&&")[6] + "&&" + line.split("&&")[7];
//					 arComptes.add(comptenormal);
//				}
//				
//			}
//			br.close();
//			fr.close();
			
//			if(!found){
//				System.out.println("Ce compte n'existe pas!!!");
//				return "no account";
//			}
			//effacer les anciennes donnees 
//			FileWriter fw3= new FileWriter(file);
//			BufferedWriter bw3=new BufferedWriter(fw3);
//			bw3.write("");bw3.close();fw3.close();
////			--------reecriture dans le vrai fichier....
//			BufferedWriter bw4=new BufferedWriter(new FileWriter(file,true));
//				for(String compte : arComptes){
//					bw4.append(compte);
//					bw4.newLine();
//				}
//				bw4.close();
				Statement st=null;
				st = Jdbcmanager.ConnexionDb().createStatement();
				String req = String.join(" ","update COMPTES set solde='",montant+"",
						" where noCompte=",numCompte+";");
				System.out.println(req);
				st.executeUpdate(req);
				System.out.printf("\nMontant du compte modifie avec succes...\n");
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "modified";
	}
	

	public void createCompte(){
		Comptes thisaccount=new Comptes();
		Scanner scanString=new Scanner(System.in);
		Scanner scanint=new Scanner(System.in);
		

//		L�id du client, le num�ro du compte, le type de compte (�pargne ou courant), date de cr�ation 
//		du compte, le solde minimal d�ouverture, la devise du compte . 
		
		System.out.print("\n Entrer l'id du client pour la creation de son compte:");
		int choosedID=scanint.nextInt();
		String lineClient=new Clients().searchClientByID(choosedID);
		if(lineClient=="no client"){
			System.out.println("Ce client n'existe pas,le compte ne peut etre cree...");
			return;
		}else{
//			client 
			thisaccount.setIdclient(choosedID);
//			numCompte
			long tmpnoCompte=10273832;
			long noCompte=findLastAccountNumber();
			if(noCompte==0){
				noCompte=tmpnoCompte;
			}else{
				noCompte=noCompte+1;
			}
			thisaccount.setNumCompte(noCompte);
			
//			date creation compte 
			Date date = new Date();
			SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd");
			String stringDate= DateFor.format(date);
			thisaccount.setDateCreated(stringDate);
			
//			type de compte
			System.out.println("Choisir le type de compte du client:");
			System.out.println("1- Epargne");
			System.out.println("2- Courant");
			int choix=scanint.nextInt();
			String typeCompte="";
				if(choix == 1){
					typeCompte="Epargne";
				}else if(choix == 2){
					typeCompte="Courant";
				}
			while(choix<1 || choix>2){
				System.out.println("---------------------------   Le type de compte du client est incorrect...  ----------------");
				System.out.println("\n Choisir le type de compte du client:");
				System.out.println("1 Epargne");
				System.out.println("2- Courant");
				choix=scanint.nextInt();
				if(choix ==1 || choix ==2){
					if(choix == 1){
						typeCompte="Epargne";
					}else{
						typeCompte="Courant";
					}
					break;
				}
				
			}
			thisaccount.setTypecompte(typeCompte);
			
//			devise du compte
			System.out.println("Choisir la devise du compte:");
			System.out.println("1- HTG");
			System.out.println("2- USD");
			int choix2=scanint.nextInt();
			String devise="";
				if(choix2 == 1){
					devise="HTG";
				}else if(choix2 == 2){
					devise="USD";
				}
			while(choix2<1 || choix2>2){
				System.out.println("--------------------------- La devise du compte est incorrect...  ----------------");
				System.out.println("\n Choisir la devise du compte:");
				System.out.println("1- HTG");
				System.out.println("2- USD");
				choix2=scanint.nextInt();
				if(choix2 ==1 || choix2 ==2){
					if(choix2 == 1){
						devise="HTG";
					}else if(choix2 == 2){
						devise="USD";
					}
					break;
				}
				
			}
			thisaccount.setDevise(devise);	
			
			
			
//			solde
			System.out.print("Entrer le solde  du client:");
			double solde=scanint.nextDouble();
			while((solde < 500 && thisaccount.getDevise()=="HTG") || (solde<10 && thisaccount.getDevise()=="USD")){
				System.out.println("---------------------  Le solde doit etre superieur a 500HTG ou 10USD  ------------------------");
				System.out.print("\n Entrer le solde du client:");
				solde=scanint.nextDouble();
				if(solde > 500 && thisaccount.getDevise()=="HTG"){
					break;
				}else if(solde>10 && thisaccount.getDevise()=="USD"){
					break;
				}
			}
			thisaccount.setSolde(solde);

			//statut
			thisaccount.setStatut("actif");
			
//			File file = new File("datas/comptes.txt");
//			FileWriter fw3;
//			try {
//				fw3 = new FileWriter(file,true);
//				BufferedWriter bw=new BufferedWriter(fw3);
//			    String account = thisaccount.getNumCompte() + "&&"+ thisaccount.getTypecompte() + "&&" + thisaccount.getDateCreated() + "&&"+ thisaccount.getSolde() + "&&" + thisaccount.getDevise()+ "&&" + thisaccount.getStatut() + "&&" + thisaccount.getIdclient()+"&&"+lineClient.split("&&")[1];
//				bw.append(account);
//				bw.newLine();bw.close();fw3.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			Statement st=null;
			try{
				st = Jdbcmanager.ConnexionDb().createStatement();
//				ID	NoCompte	type creele	solde	monnaie	etat	idclient
				String req = String.join(" ","insert into COMPTES(noCompte,type,creele,solde,monnaie,etat,idclient) VALUES(",
				"'"+thisaccount.getNumCompte()+"',","'"+thisaccount.getTypecompte()+"',","'"+thisaccount.getDateCreated()+"',","'"+thisaccount.getSolde()+"',","'"+thisaccount.getDevise()+"',","'"+thisaccount.getStatut()+"',",thisaccount.getIdclient()+");");
//				System.out.println(req);
				st.executeUpdate(req);
				System.out.printf("---------------   Compte cree avec succes pour le client %s... ---------------------",lineClient);
				
			}catch(SQLException e){
				System.out.println("Erreur le compte n'a pas ete cree...");
				e.printStackTrace();
			}
		}
		
	}
}
