package clients;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Clients {
	// Leid du client, le nom complet du client, la date de creation,
	// leadresse du client, le # telephone
	// du client, le numero de la succursale, le statut matrimonial du client
	// et le nombre deenfants.
	private int idclient;
	private String fullname;
	private String dateCreation;
	private String adresse;
	private long tel;
	private int succursale=45;
	private String statuMatrimonial;
	private int nbEnfants;

	public Clients() {
	}

	public Clients(String adresse, long tel) {
		super();
		this.adresse = adresse;
		this.tel = tel;
	}

	public Clients(int idclient, String fullname, String dateCreation,
			String adresse, long tel, String statuMatrimonial,
			int nbEnfants) {
		super();
		this.idclient = idclient;
		this.fullname = fullname;
		this.dateCreation = dateCreation;
		this.adresse = adresse;
		this.tel = tel;
		this.statuMatrimonial = statuMatrimonial;
		this.nbEnfants = nbEnfants;
	}

	public int getIdclient() {
		return idclient;
	}

	public void setIdclient(int idclient) {
		this.idclient = idclient;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public long getTel() {
		return tel;
	}

	public void setTel(long tel) {
		this.tel = tel;
	}

	public int getSuccursale() {
		return succursale;
	}

	public void setSuccursale(int succursale) {
		this.succursale = succursale;
	}

	public String getStatuMatrimonial() {
		return statuMatrimonial;
	}

	public void setStatuMatrimonial(String statuMatrimonial) {
		this.statuMatrimonial = statuMatrimonial;
	}

	public int getNbEnfants() {
		return nbEnfants;
	}

	public void setNbEnfants(int nbEnfants) {
		this.nbEnfants = nbEnfants;
	}


	public void readAllClients() {
		try {
			File file = new File("datas/clients.txt");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			StringBuffer sb = new StringBuffer();

			String line;
			while ((line = br.readLine()) != null) {
				String client = "    " + line.split("&&")[0] + "  "
						+ line.split("&&")[1] + "   " + line.split("&&")[2]
						+ "   " + line.split("&&")[3] + "     "
						+ line.split("&&")[4] + "      " + line.split("&&")[5]
						+ "    " + line.split("&&")[6] + "    "
						+ line.split("&&")[7] + "  ";
				sb.append(client);
				sb.append("\n\n");
			}
			fr.close();
			System.out
					.println("+------+---------+----------+-----------+------------+-------------+-------------------+----------+");
			System.out
					.println("| ID   | Client  |  Cree le |  Adresse  | Telephone  |  Succursale | StatusMatrimonial | Enfants |");
			System.out
					.println("+------+---------+----------+-----------+------------+-------------+-------------------+----------+");
			System.out.println(sb.toString());
			System.out
					.println("+------+---------+----------+-----------+------------+-------------+-------------------+----------+");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public String searchClientByID(int id) {
		try {
			File file = new File("datas/clients.txt");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			StringBuffer sb = new StringBuffer();

			String line;
			boolean found = false;
			while ((line = br.readLine()) != null) {
				if (Integer.parseInt(line.split("&&")[0]) == id) {
					String client = "    " + line.split("&&")[0] + "  "
							+ line.split("&&")[1] + "   " + line.split("&&")[2]
							+ "   " + line.split("&&")[3] + "     "
							+ line.split("&&")[4] + "      "
							+ line.split("&&")[5] + "    "
							+ line.split("&&")[6] + "    "
							+ line.split("&&")[7] + "  ";
					sb.append(client);
					System.out
							.println("+------+---------+----------+-----------+------------+-------------+-------------------+----------+");
					System.out
							.println("| ID   | Client  |  Cree le |  Adresse  | Telephone  |  Succursale | StatusMatrimonial | Enfants |");
					System.out
							.println("+------+---------+----------+-----------+------------+-------------+-------------------+----------+");
					System.out.println(sb.toString());
					System.out
							.println("+------+---------+----------+-----------+------------+-------------+-------------------+----------+");
					sb.append("\n\n");
					found = true;
					br.close();
					return line.toString();
				}
			}

			if (!found) {
				System.out.println("Aucun client ayant l'id " + id);
				
			}
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "no client";
	}

	
	
	public int findLastIdClient() {
		int lastid = 0;
		int max=0;
		try {
			File file = new File("datas/clients.txt");
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
			System.out.println("Denier client inconnu..."+e.getMessage());
//			e.printStackTrace();
		}
		return max;
	}

	public void searchClientByName(String clientname) {
		try {
			File file = new File("datas/clients.txt");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			StringBuffer sb = new StringBuffer();

			String line;
			boolean found = false;
			while ((line = br.readLine()) != null) {
				if (line.split("&&")[1].toLowerCase().contains(
						clientname.toLowerCase())) {
					String client = "    " + line.split("&&")[0] + "  "
							+ line.split("&&")[1] + "   " + line.split("&&")[2]
							+ "   " + line.split("&&")[3] + "     "
							+ line.split("&&")[4] + "      "
							+ line.split("&&")[5] + "    "
							+ line.split("&&")[6] + "    "
							+ line.split("&&")[7] + "  ";
					sb.append(client);
					System.out
							.println("+------+---------+----------+-----------+------------+-------------+-------------------+----------+");
					System.out
							.println("| ID   | Client  |  Cree le |  Adresse  | Telephone  |  Succursale | StatusMatrimonial | Enfants |");
					System.out
							.println("+------+---------+----------+-----------+------------+-------------+-------------------+----------+");
					System.out.println(sb.toString());
					System.out
							.println("+------+---------+----------+-----------+------------+-------------+-------------------+----------+");
					sb.append("\n\n");
					found = true;
					break;
				}
			}

			if (!found) {
				System.out.println("Client " + clientname + " inconnu...");
			}
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void modifyClient() {
		ArrayList<String> arClients=new ArrayList<String>();
		Scanner scanint2=new Scanner(System.in);
		Scanner scanString2=new Scanner(System.in);
		System.out.print("\n Entrer l'id du client à modifier le compte:");
		int choosedID=scanint2.nextInt();
		String lineClient=searchClientByID(choosedID);
		String swapLineClient="";
		
		if(lineClient.length()>2){
			swapLineClient=lineClient;
			
			Clients thisclient=new Clients("",0);
//			adresse
			System.out.print("Entrer la nouvelle adresse du client:");
			String tiadress=scanString2.nextLine();
			while(tiadress.length()<5){
				System.err.println("------------------------ L'adresse du client est incorrect   ex: Delmas 54,rue acacia #1  ---------------------");
				System.out.print("\n Entrer la nouvelle adresse du client:");
				tiadress=scanString2.nextLine();
				if(tiadress.length()>5){
					break;
				}
			 }
			thisclient.setAdresse(tiadress);
			
				
//			tel
			System.out.print("Entrer le nouveau telephone du client:");
			long titel=scanint2.nextLong();
			while(titel < 10000000){
				System.err.println("---------------------  Le numero telephone est incorrect ex:36799090  ------------------------");
				System.out.print("\n Entrer le nouveau telephone du client:");
				titel=scanint2.nextLong();
				if(titel > 1000000){
					break;
				}
			}
			thisclient.setTel(titel);
			
			
			//----------Lire et reecriture dans un tableau en remplacant la ligne concernee----------------
			//			lire
			FileReader fr;
			try {
				File file = new File("datas/clients.txt");
				fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				String line;
				while ((line = br.readLine()) != null) {
					if (Integer.parseInt(line.split("&&")[0]) != choosedID) {
						String client = line.split("&&")[0] + "&&"+ line.split("&&")[1] + "&&" + line.split("&&")[2] + "&&"+ line.split("&&")[3] + "&&" + line.split("&&")[4]+ "&&" + line.split("&&")[5] + "&&"+ line.split("&&")[6] + "&&" + line.split("&&")[7];
						 arClients.add(client);
					}else if(Integer.parseInt(line.split("&&")[0]) == choosedID){
						String clientmodif = line.split("&&")[0] + "&&"+ line.split("&&")[1] + "&&" + line.split("&&")[2] + "&&"+ thisclient.getAdresse() + "&&" + thisclient.getTel()+ "&&" + line.split("&&")[5] + "&&"+ line.split("&&")[6] + "&&" + line.split("&&")[7];
						 arClients.add(clientmodif);
					}
				}
				br.close();
				scanint2.close();
				scanString2.close();
				
				//effacer les anciennes donnees 
				FileWriter fw3= new FileWriter(file);
				BufferedWriter bw3=new BufferedWriter(fw3);
				bw3.write("");bw3.close();fw3.close();
//				--------reecriture dans le vrai fichier....
				BufferedWriter bw4=new BufferedWriter(new FileWriter(file,true));
					for(String client : arClients){
						bw4.append(client);
						bw4.newLine();
					}
					bw4.close();
					System.out.println("L'adresse et le numero de telephone du client a ete modifie avec success...");
			} catch (NumberFormatException | IOException  e) {
				e.printStackTrace();
				System.out.println("Client non modifie..."+e.getMessage());
			}
		}else{
			System.err.println("Aucun client trouve pour cet id de compte...");
		}

	}

//	---------------------------add client----------------------------------------------------------
	public void createClient(){
		Clients thisclient=new Clients();
		Scanner scanString=new Scanner(System.in);
		Scanner scanint=new Scanner(System.in);
		
//		fullname
		System.out.print("Entrer le nom complet du client:");
		String tiname=scanString.nextLine();
		while(tiname.length()<5){
			System.err.println("\n------------------Le nom complet est incorrect: ex: Jean Pierre -----------------\n");
			System.out.print("\n Entrer le nom complet du client:");
			tiname=scanString.nextLine();
			if(tiname.length()>5){
				break;
			}
		}
		thisclient.setFullname(tiname);
		
//		adresse
		System.out.print("Entrer l'adresse du client:");
		String tiadress=scanString.nextLine();
		while(tiadress.length()<5){
			System.err.println("------------------------ L'adresse du client est incorrect   ex: Delmas 54,rue acacia #1  ---------------------");
			System.out.print("\n Entrer l'adresse du client:");
			tiadress=scanString.nextLine();
			if(tiadress.length()>5){
				break;
			}
		 }
		thisclient.setAdresse(tiadress);
		
			
//		tel
		System.out.print("Entrer le telephone du client:");
		long titel=scanint.nextLong();
		while(titel < 10000000){
			System.out.println("---------------------  Le numero telephone est incorrect ex:36799090  ------------------------");
			System.out.print("\n Entrer le telephone du client:");
			titel=scanint.nextLong();
			if(titel > 1000000){
				break;
			}
		}
		thisclient.setTel(titel);
		
//		statuMatrimonial
		System.out.println("Choisir le statut matrimonial du client:");
		System.out.println("1- Marie(e)");
		System.out.println("2- Celibataire");
		int choix=scanint.nextInt();
		String statusM="";
			if(choix == 1){
				statusM="Marie(e)";
			}else if(choix == 2){
				statusM="Celibataire";
			}
		while(choix<1 || choix>2){
			System.out.println("---------------------------   Le statut matrimonial du client est incorrect...  ----------------");
			System.out.println("\n Choisir le statut matrimonial du client:");
			System.out.println("1- Marie(e)");
			System.out.println("2- Celibataire");
			choix=scanint.nextInt();
			if(choix ==1 || choix ==2){
				if(choix == 1){
					statusM="Marie(e)";
				}else{
					statusM="Celibataire";
				}
				break;
			}
			
		}
		thisclient.setStatuMatrimonial(statusM);
		
		//nbEnfants
		System.out.print("Entrer le nombre d'enfants du client:");
		int nbEnfants=scanint.nextInt();
		while(nbEnfants< 0 || nbEnfants > 100){
			System.out.println("------------------   Le nombre d'enfants du client est incorrect  --------------");
			System.out.print("\n Entrer le nombre d'enfants du client:");
			nbEnfants=scanint.nextInt();
			if(nbEnfants > 0 && nbEnfants <100 ){
				break;
			}
		}
		thisclient.setNbEnfants(nbEnfants);
		scanint.close();
		scanString.close();
		
		
		
		
		
		Date date = new Date();
		SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
	    thisclient.setDateCreation(DateFor.format(date));
		BufferedWriter bw;
		int new_id_client=findLastIdClient()+1;
		try {
			String client = new_id_client + "&&"+ thisclient.getFullname() + "&&" + thisclient.getDateCreation() + "&&"+ thisclient.getAdresse() + "&&" + thisclient.getTel()+ "&&" + thisclient.getSuccursale() + "&&"+ thisclient.getStatuMatrimonial() + "&&" + thisclient.getNbEnfants();
			File file = new File("datas/clients.txt");
			bw = new BufferedWriter(new FileWriter(file, true));
			bw.append(client);
			bw.newLine();
			bw.close();
			System.out.println("Client ajoute avec succes!");
			searchClientByID(new_id_client);
		} catch (IOException e) {
			System.out.println("Erreur le client n'a pas ete ajoute...");
			e.printStackTrace();
		}
	}
	
	
	

}
