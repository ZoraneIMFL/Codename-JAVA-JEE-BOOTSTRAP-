package FonctionsJeu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class GenerateurMot {

	public GenerateurMot() {

	}

	public ArrayList<Carte> genere() throws FileNotFoundException, IOException {
		ArrayList<String> listeMots = new ArrayList<String>();
		ArrayList<Carte> listeCartes = new ArrayList<Carte>();
		
		InputStream is = getClass().getClassLoader()
                .getResourceAsStream("./mots.txt");

        	var br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        	String st;  
		while ((st = br.readLine()) != null){
            		Collections.addAll(listeMots, st.split("\\s"));
            	}

        
		br.close(); 
		Collections.shuffle(listeMots);

		for (int i = 0; i < 25; i++) {
			if (i < 9) {
				
				listeCartes.add(new Carte("rouge", listeMots.get(i), String.valueOf(i), false));
			} else if (i >= 9 && i < 17) {

				listeCartes.add(new Carte("bleu", listeMots.get(i), String.valueOf(i), false));
			} else if (i >= 17 && i < 24) {

				listeCartes.add(new Carte("blanc", listeMots.get(i), String.valueOf(i), false));
			} else {

				listeCartes.add(new Carte("noir", listeMots.get(i), String.valueOf(i), false));
			}

		}
		Collections.shuffle(listeCartes);
		return listeCartes;

	}

}
