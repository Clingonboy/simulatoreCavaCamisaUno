package it.simulatoreCavaCamisa;

public class SimCavaCamisaDati {
	
	// Attributi privati
	private int carteGiocateGiocatoreUno;
	private int carteGiocateGiocatoreDue;
	private int maniPreseGiocatoreUno;
	private int maniPreseGiocatoreDue;
	
	// Metodi pubblici
	public int getCarteGiocateGiocatoreUno() {
		return carteGiocateGiocatoreUno;
	}
	public void setCarteGiocateGiocatoreUno(int carteGiocateGiocatoreUno) {
		this.carteGiocateGiocatoreUno = carteGiocateGiocatoreUno;
	}
	public int getCarteGiocateGiocatoreDue() {
		return carteGiocateGiocatoreDue;
	}
	public void setCarteGiocateGiocatoreDue(int carteGiocateGiocatoreDue) {
		this.carteGiocateGiocatoreDue = carteGiocateGiocatoreDue;
	}
	public int getManiPreseGiocatoreUno() {
		return maniPreseGiocatoreUno;
	}
	public void setManiPreseGiocatoreUno(int maniPreseGiocatoreUno) {
		this.maniPreseGiocatoreUno = maniPreseGiocatoreUno;
	}
	public int getManiPreseGiocatoreDue() {
		return maniPreseGiocatoreDue;
	}
	public void setManiPreseGiocatoreDue(int maniPreseGiocatoreDue) {
		this.maniPreseGiocatoreDue = maniPreseGiocatoreDue;
	}
	
	public SimCavaCamisaDati(){
		this.carteGiocateGiocatoreUno = 0;
		this.carteGiocateGiocatoreDue = 0;
		this.maniPreseGiocatoreUno = 0;
		this.maniPreseGiocatoreDue = 0;
	}
}
