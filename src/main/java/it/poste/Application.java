package it.poste;

import it.poste.generafile.FileBuilder;

public class Application {
	
	public static void main(String args[]) {
		
		System.out.println("GENERA-FILE-EU");
		System.out.println("");
		
		FileBuilder service = new FileBuilder();
		service.createFile();
		
		System.out.println("");
		System.out.println("FINE LAVORAZIONE");
		
	}
		
}
