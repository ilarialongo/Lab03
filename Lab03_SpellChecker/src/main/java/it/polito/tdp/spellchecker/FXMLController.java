/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

/*package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;

public class FXMLController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

    }
}
*/

package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class FXMLController {
	
	private Dictionary model;
	List<String> input= new ArrayList<>();
	long start;
	long stop;
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> bxChoice;

    @FXML
    private TextArea txtTesto;

    @FXML
    private Button btnOrtografia;

    @FXML
    private TextArea txtErrate;

    @FXML
    private Text txtNumeroErrori;

    @FXML
    private Button btnClear;

    @FXML
    private Text txtVelocita;

    @FXML
    void doClearText(ActionEvent event) {
    	txtErrate.clear();
    	txtTesto.clear();
    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	this.model.loadDictionary(bxChoice.getValue());
    	String testo=txtTesto.getText();
    	testo.toLowerCase();
   	testo.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_'~()\\[\\]\"]", "");
   	String[] sTemp= testo.split(" ");
   	input=Arrays.asList(sTemp);
   	//this.model.spellCheckTest(input);
   	List<RichWord> lista= new ArrayList<>();
   	start= System.nanoTime();
   	
   	try {
   		//lista=this.model.spellCheckTest(input);
   		lista=this.model.spellCheckTestDichotomic(input);
   	} catch (IllegalStateException se) {
   		txtErrate.appendText(se.getMessage());
		return;
   	}
   	stop=System.nanoTime();
   	txtErrate.setText(this.model.stampaLista(lista));
   	txtNumeroErrori.setText("The text contains "+Integer.toString(this.model.getNumeroParoleErrate())+" errors");
   	txtVelocita.setText("Spell check completed in "+(stop-start)/1e9+" seconds");
 
    }

    @FXML
    void initialize() {
        assert bxChoice != null : "fx:id=\"bxChoice\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTesto != null : "fx:id=\"txtTesto\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnOrtografia != null : "fx:id=\"btnOrtografia\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtErrate != null : "fx:id=\"txtErrate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNumeroErrori != null : "fx:id=\"txtNumeroErrori\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtVelocita != null : "fx:id=\"txtVelocita\" was not injected: check your FXML file 'Scene.fxml'.";
        bxChoice.getItems().addAll("Italian", "English");
        bxChoice.setValue("Italian");
    }

	public void setModel(Dictionary model) {
		this.model=model;
	}
}
