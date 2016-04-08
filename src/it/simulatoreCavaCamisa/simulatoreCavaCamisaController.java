package it.simulatoreCavaCamisa;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class simulatoreCavaCamisaController {
	
	private SimulatoreLogic simulazione;
	
    @FXML
    private Button readData;
    
    @FXML
    private Button generaRandom;
    
    @FXML
    private Button startSimulazione;

    @FXML
    private TextArea txtResult;

    @FXML
    private TextField txtGiocatoreUno;

    @FXML
    private TextField txtGiocatoreDue;

    @FXML
    void doReadData(ActionEvent event) {
    	this.simulazione = new SimulatoreLogic(txtGiocatoreUno.getText() + "+" + txtGiocatoreDue.getText());
    	txtResult.setText(simulazione.getMessage());
    }
    
    @FXML
    void doGenerarandom(ActionEvent event) {
    	this.simulazione = new SimulatoreLogic("RANDOM");
    	txtResult.setText(simulazione.getMessage());
    }
    
    @FXML
    void doStartSimulazione(ActionEvent event) {
    	this.simulazione.gioca(this.simulazione.g1, this.simulazione.g2, this.simulazione.tavolo, 0);
    	txtResult.setText(this.simulazione.generaMessaggio());
    }


    @FXML
    void initialize() {
        assert readData != null : "fx:id=\"readData\" was not injected: check your FXML file 'simulatoreCavacamisa.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'simulatoreCavacamisa.fxml'.";
        assert txtGiocatoreUno != null : "fx:id=\"txtGiocatoreUno\" was not injected: check your FXML file 'simulatoreCavacamisa.fxml'.";
        assert txtGiocatoreDue != null : "fx:id=\"txtGiocatoreDue\" was not injected: check your FXML file 'simulatoreCavacamisa.fxml'.";

    }
	
	
}
