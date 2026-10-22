package gui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import business.Haushaltsroboter;
import business.HaushaltsroboterModel;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HaushaltsroboterControl {
	private HaushaltsroboterView view;
	private HaushaltsroboterModel model;
	
	public HaushaltsroboterControl(Stage primaryStage) {
		this.model = new HaushaltsroboterModel();
		this.view = new HaushaltsroboterView(this, model, primaryStage);
	}
	
	   public void nehmeHaushaltsroboterAuf(){
	    	try{ 
	    		Haushaltsroboter roboter = new Haushaltsroboter(
	    			view.getTxtName().getText(), 
	    			Float.parseFloat(view.getTxtPreis().getText()),
	   	            view.getTxtModell().getText(),
	   	        	view.getTxtSensortyp().getText(),
	    		    view.getTxtFarben().getText().split(";"));
	    		view.zeigeInformationsfensterAn("Der Haushaltsroboter wurde aufgenommen!");
		    	model.setHaushaltsroboter(roboter);

	    	}
	       	catch(Exception exc){
	       		view.zeigeFehlermeldungsfensterAn(exc.getMessage());
	     	}
	    }
	   
	    public void zeigeHaushaltsroboterAn(){
	    	if(model.getHaushaltsroboter() != null){
	    		view.getTxtAnzeige().setText(
	    			model.getHaushaltsroboter().gibHaushaltsroboterZurueck(' '));
	    	}
	    	else{
	    		view.zeigeInformationsfensterAn("Bisher wurde kein Haushaltsroboter aufgenommen!");
	    	}
	    }    
	   
	    public void leseAusDatei(String typ){
	    	try {
	    		model.leseAusDatei(typ);
	      		if("csv".equals(typ)){
	      	  			view.zeigeInformationsfensterAn(
	      	  	   			"Der Haushaltsroboter wurde gelesen!");
	      		}
	       		else{
		   			view.zeigeInformationsfensterAn(
		   				"Noch nicht implementiert!");
		   		}
			}
			catch(IOException exc){
				view.zeigeFehlermeldungsfensterAn(
					"IOException beim Lesen!");
			}
			catch(Exception exc){
				view.zeigeFehlermeldungsfensterAn(
					"Unbekannter Fehler beim Lesen!");
			}
		}
	    
		public void schreibeHaushaltsroboterInCsvDatei() {
			try {
				model.schreibeHaushaltsroboterInCsvDatei();
	   			view.zeigeInformationsfensterAn(
		   			"Die Haushaltsroboter wurden gespeichert!");
			}	
			catch(IOException exc){
				view.zeigeFehlermeldungsfensterAn(
					"IOException beim Speichern!");
			}
			catch(Exception exc){
				view.zeigeFehlermeldungsfensterAn(
					"Unbekannter Fehler beim Speichern!");
			}
		}
}
