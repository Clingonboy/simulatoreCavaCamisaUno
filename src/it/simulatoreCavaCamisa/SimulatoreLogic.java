package it.simulatoreCavaCamisa;

import java.util.ArrayList;
import java.util.Collections;

public class SimulatoreLogic {
	

	private ArrayList<Integer> giocatoreUno;
	private ArrayList<Integer> giocatoreDue;
	private String stringaInIngresso;
	public ArrayList<Integer> tavolo;
	private String stringaUno;
	private String stringaDue;
	private String message = "Tutto ok";
	private int controller = 1;
	private SimCavaCamisaDati dati;
	public Giocatore g1;
	public Giocatore g2;

	public SimulatoreLogic(String stringa){
		this.stringaInIngresso = stringa;
		this.giocatoreUno = new ArrayList<Integer>();
		this.giocatoreDue = new ArrayList<Integer>();
		this.tavolo = new ArrayList<Integer>();	
		this.dati = new SimCavaCamisaDati();
		
		if (this.stringaInIngresso.equals("RANDOM")){
			initRandom();
		}else{
		init();
		}
	}
	
	// Metodi privati.
	
	/**
	 * Metoto initRandom che genera una sequenza casuale di carte
	 * per i 2 giocatori
	 */
	private void initRandom() {
		
		// Creazione di un mazzo ordinato di carte 1 2 3 0 0 0 0 0 0 0 per i 4 semi
		ArrayList<Integer> carteMazzo = new ArrayList<Integer>();
		for(int i = 0; i < 4; i++) {
			carteMazzo.add(1);
			carteMazzo.add(2);
			carteMazzo.add(3);
			for(int j = 0; j < 7; j++){
				carteMazzo.add(0);
			}
		}
		/// Mescolamento delle carte
		Collections.shuffle(carteMazzo);

		// Distribuzione delle carte a giocatoreUno e giocatoreDue.
		for(int i = 0; i < 20; i++){
			this.giocatoreUno.add(carteMazzo.get(i));
		}
		for(int i = 20; i<40; i++){
			this.giocatoreDue.add(carteMazzo.get(i));
		}
		
		this.message = "La sequeza casuale di carte è stata creata";
		this.message += "\nIn tutto sono : " + carteMazzo.size()+ "\n";
		
		//test controllo________________________________________
		if(controller > 0){
			message += "Giocatore1 :\n";
			for(int i = 0; i < this.giocatoreUno.size(); i++){
				message = message + String.valueOf(this.giocatoreUno.get(i)) + "\n";
			}
			message = message + "Giocatore2 :\n";
			for(int i = 0; i < this.giocatoreDue.size(); i++){
				message = message + String.valueOf(this.giocatoreDue.get(i)) + "\n";
			}
		}
		//test controllo _______________________________________
		
		// Creazione degli oggetti Giocatore
		this.g1 = new Giocatore(this.giocatoreUno);
		this.g2 = new Giocatore(this.giocatoreDue);
		
	} // end initRandom()

	/**
	 * Metodo per inizializzare tutti i membri della classe init()
	 * legge la scringa inserita dal costruttore e la elabora
	 * per ottenere le carte dei 2 giocatori
	 */
	private void init(){
		
		// Lettura delle 2 stringhe di imput
		this.stringaUno = this.stringaInIngresso.substring(0, this.stringaInIngresso.indexOf("+"));
		this.stringaDue = this.stringaInIngresso.substring((this.stringaInIngresso.indexOf("+")+1), this.stringaInIngresso.length());
		
		//Definizione dei possibili separatori
		String[] separatori = new String[] {";", ":", "|", "," ,"-" , " "};
		int posizioneSeparatore = -1;
		String separatore = " "; //Separatore di default.
		
		// Ciclo per travare il separatore
		try{
			for(int i = 0; i < separatori.length; i++){
				if(this.stringaUno.contains(separatori[i])){
					posizioneSeparatore = i;
				}
			}
			if(posizioneSeparatore > -1){
			separatore = separatori[posizioneSeparatore];
			}else {
				this.controller = 0;
				this.message = "Non è stato trovato un separatore corretto\n";
				this.message += "Riprova e stà tento";
			}
		} catch (Exception exc) {
			this.controller = 0;
			this.message = "Non è stato trovato un separatore corretto\n";
			this.message += exc;
		}//end Try
		
		
		// Ciclo per assegnarer i valori delle carte al giocatore 1
		if(this.controller > 0){
			try{
				for(int i = 0; i<this.stringaUno.length(); i++){
					if(this.stringaUno.substring(i, i+1).equals(separatore)){
						if(!this.giocatoreUno.add(Integer.parseInt(this.stringaUno.substring(i-1, i)))){
							controller = 0;
						}
					}
				}
				if(!this.giocatoreUno.add(Integer.parseInt(this.stringaUno.substring(this.stringaUno.length()-1, this.stringaUno.length())))){
					controller = 0;
				}
			} catch (Exception exc) {
				this.controller = 0;
				this.message = "Stringa errata, controlla e riprova\n";
				this.message += exc;
			}//end Try
			
			// Ciclo per assegnarer i valori delle carte al giocatore 2
			try{
				for(int i = 0; i<this.stringaDue.length(); i++){
					if(this.stringaDue.substring(i, i+1).equals(separatore)){
						if(!this.giocatoreDue.add(Integer.parseInt(this.stringaDue.substring(i-1, i)))){
							controller = 0;
						}
					}
				}
				if(!this.giocatoreDue.add(Integer.parseInt(this.stringaDue.substring(this.stringaDue.length()-1, this.stringaDue.length())))){
					controller = 0;
				}
			} catch (Exception exc) {
				this.controller = 0;
				this.message = "Stringa errata, controlla e riprova\n";
				this.message += exc;
			}//end Try
		} // end if controllo 
		
		// Controllo che il numero delle carte sia giusto
		if(this.controller > 0){
			if (this.giocatoreUno.size() == 20){
				this.controller = 1;
			}else {
				this.controller = 0;
				this.message = "Il numero di carte dei giocatori devono essere 20 !!!\n";
				this.message += "Ricontrolla il giocatore 1";
			}
			if(this.controller > 0){
				if(this.giocatoreDue.size() == 20){
					this.controller = 1;
				}else {
					this.controller = 0;
					this.message = "Il numero di carte dei giocatori devono essere 20 !!!\n";
					this.message += "Ricontrolla il giocatore 2";
				}
			}
		}
		
		// Controllo del tipo di carte assegnate
		// Controllo che ci siano 4 Assi, 4 Due e 4 Tre.
		if(this.controller > 0){
			int nAssi = 0;
			int nDue = 0;
			int nTre = 0;
			int nZero = 0;
			for(int carta: this.giocatoreUno){
				if(carta == 0){
					nZero++;
				}
				if(carta == 1){
					nAssi++;
				}
				if(carta == 2){
					nDue++;
				}
				if(carta == 3){
					nTre++;
				}
			} // end for
			
			for(int carta: this.giocatoreDue){
				if(carta == 0){
					nZero++;
				}
				if(carta == 1){
					nAssi++;
				}
				if(carta == 2){
					nDue++;
				}
				if(carta == 3){
					nTre++;
				}
			} // end for
			
			// IF di controllo
			if(nZero == 28 & nAssi == 4 & nDue == 4 & nTre ==4){
				this.controller = 1;
			} else{
				this.controller = 0;
				this.message = "Il numero delle carte per tipo è sbagliato";
				this.message += "Devono essere:\n 4 Assi\n 4 Due\n 4 Tre\n e tutti gli altri zero";
			}
		}
		
		// Fine controllo Assi Due Tre.
		
		//test controllo________________________________________
		// 1,1,1,1,2,2,2,2,3,3,3,3,0,0,0,0,0,0,0,0
		// 1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0
		if(controller > 0){
			message = "Giocatore1 :\n";
			for(int i = 0; i < this.giocatoreUno.size(); i++){
				message = message + String.valueOf(this.giocatoreUno.get(i)) + "\n";
			}
			message = message + "Giocatore2 :\n";
			for(int i = 0; i < this.giocatoreDue.size(); i++){
				message = message + String.valueOf(this.giocatoreDue.get(i)) + "\n";
			}
		}
		//test controllo _______________________________________
		
		// Creazione degli oggetti Giocatore
		this.g1 = new Giocatore(this.giocatoreUno);
		this.g2 = new Giocatore(this.giocatoreDue);

	}//end init()
	
	// Motodi pubblici.
	
	/** Restituisce il messaggio da visualizzare all'utente
	 * 
	 * @return String 'Messaggio per l'utente'
	 */
	public String getMessage(){
		return this.message;
	}
	
	/**
	 * Funzione che realizza la simulazione del gioco,
	 * la funzione è ricorsiva, continua a chiamare se stessa
	 * fino al verificarsi delle condizioni di fine partita.
	 * 
	 * La condizione di fine partita di verifica quanto il giocatore
	 * primo di mano non ha più carte in mano da giocare
	 * 
	 * @param a Giocarore Giocatore primo di mano
	 * @param b Giocatore Giocatore secondo di mano
	 * @param t ArrayList<Integer> Tavolo di gioco
	 * @param partenza int Stato del gioco (rappresenta l'ultima carta iocata dall'avversario altrimenti è = 0
	 */
	public void gioca(Giocatore a, Giocatore b, ArrayList<Integer> t, int partenza){
		System.out.println(a.getCarteGiocate()); /////////////////////////////
		// se l'ultima carta giocata dall'avversario è 0
		if( partenza == 0){
			if(a.size() > 0){
				t.add(0,a.giocaCarta());
				partenza = t.get(0);
				this.gioca(b, a, t, partenza);
				return;
			} else{
				// b.prendiCarte(t);
				b.setStato(1); // Impostato lo stato del giocatore = 1 = VINCITORE
				return;
			}
		} // End if partenza = 0
		
		// se l'ìavversario aveva giocato un asso
		if(partenza == 1){ 
			if(a.size() > 0){
				t.add(0, a.giocaCarta());
				if(t.get(0) > 0){ // se gioca una carta significativa
					partenza = t.get(0);
					this.gioca(b, a, t, partenza);
					return;
				}else{
					b.prendiCarte(t);
					t.clear();
					partenza = 0;
					this.gioca(b, a, t, partenza);
					return;
				}
			}else{
				b.setStato(1);
				return;
			}		
		}
		
		// se l'avversario aveva giocato un due
		if(partenza == 2){ 
			if(a.size() > 0){
				t.add(0, a.giocaCarta());
				if(t.get(0) > 0){ // se gioca una carta significativa
					partenza = t.get(0);
					this.gioca(b, a, t, partenza);
					return;
				} else if(a.size() > 0){
					t.add(0, a.giocaCarta());
					if(t.get(0) > 0){
						partenza = t.get(0);
						this.gioca(b, a, t, partenza);
						return;
					} else{
						b.prendiCarte(t);
						t.clear();
						partenza = 0;
						this.gioca(b, a, t, partenza);
						return;
					}
				} else {
					b.setStato(1);
					return;
				}
			}else{
				b.setStato(1);
				return;
			}	
		}
		
		// se l'avversario aveva giocato un tre
		if(partenza == 3){ 
			if(a.size() > 0){
				t.add(0, a.giocaCarta());
				if(t.get(0) > 0){ // se gioca una carta significativa
					partenza = t.get(0);
					this.gioca(b, a, t, partenza);
					return;
				} else if(a.size() > 0){
					t.add(0, a.giocaCarta());
					if(t.get(0) > 0){
						partenza = t.get(0);
						this.gioca(b, a, t, partenza);
						return;
					} else if (a.size() > 0){
						t.add(0, a.giocaCarta());
						if(t.get(0) > 0){ // se gioca una carta significativa
							partenza = t.get(0);
							this.gioca(b, a, t, partenza);
							return;
						}else{
							b.prendiCarte(t);
							t.clear();
							partenza = 0;
							this.gioca(b, a, t, partenza);
							return;
						}
					} else {
						b.setStato(1);
						return;
					}
				} else {
					b.setStato(1);
					return;
				}
			}else{
				b.setStato(1);
				return;
			}	
		}
		
	}// end metodo gioca.
	
	public String generaMessaggio(){
		String messaggio = "Risultato della simulazione: \n";
		messaggio += "Giocatore 1: \n";
		messaggio += "Carte giocate: " + this.g1.getCarteGiocate() + "\n";
		messaggio += "_________________\n";
		messaggio += "Giocatore 2: \n";
		messaggio += "Carte giocate: " + this.g2.getCarteGiocate() + "\n";
		if(this.g1.getStato() > 0){
			messaggio += "Il vincitore è il giocatore uno";
		} else{
			messaggio += "Il vincitore è il giocatore due";
		}
		
		return messaggio;
	}
	
} // end classe simulatore.
