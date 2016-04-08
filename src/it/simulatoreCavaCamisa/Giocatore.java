package it.simulatoreCavaCamisa;

import java.util.ArrayList;

public class Giocatore {
	private ArrayList<Integer> carte;
	private int carteGiocate;
	private int maniPrese;
	private int stato;
	
	/**
	 * Costruttore della classe Giocatore
	 * 
	 * @param carte ArreyList<Integer> carte ricevute.
	 */
	public Giocatore(ArrayList<Integer> carte){
		this.carte = carte;
		this.carteGiocate = 0;
		this.maniPrese = 0;
		this.stato =0;
	}
	
	/**
	 * Restituisce lo stato del giocatore
	 * 
	 * @return int
	 */
	public int getStato(){
		return this.stato;
	}
	
	/**
	 * Imposta lo stato del giocatore
	 * 
	 * @param stato int
	 */
	public void setStato(int stato){
		this.stato = stato;
	}
	
	/**
	 * Funzione che restituisce il numero di carte in mano al giocatore
	 * 
	 * @return
	 */
	public int size(){
		return this.carte.size();
	}
	
	/**
	 * Funzione che restituisce il numero di carte giocate
	 * 
	 * @return int
	 */
	public int getCarteGiocate (){
		return this.carteGiocate;
	}
	
	/**
	 * Funzione che restituisce il numero di mani prese
	 * 
	 * @return int
	 */
	public int getManiGiocate(){
		return this.maniPrese;
	}
	
	/**
	 * Funzione per giocare una carta
	 * toglie dall'array di carte la prima carta e 
	 * restituisce il valore della carta giocata
	 * 
	 * @return int valore della carta
	 */
	public int giocaCarta(){
		int cartaGiocata = this.carte.get(0);
		this.carte.remove(0);
		this.carteGiocate++;
		return cartaGiocata;
	}
	
	/**
	 * Funzione per prendere le carte
	 * Le carte ricevute, come previsto da cavacamisa, vengono aggiunte
	 * alle carte del giocatore l'ultima carta sia la prima.
	 * 
	 * @param cartePrese ArrayList<Integer>
	 */
	public void prendiCarte(ArrayList<Integer> cartePrese){
		int indexFine;
		if(this.carte.size() > 0){
			indexFine = this.carte.size()-1;
		} else{
			indexFine = 0;
		}
			
		for(int i = cartePrese.size()-1; i >= 0; i--){
			this.carte.add(indexFine, cartePrese.get(i));
			indexFine++;
		}
		this.maniPrese++;
	}
	

} // End classe Giocatore
