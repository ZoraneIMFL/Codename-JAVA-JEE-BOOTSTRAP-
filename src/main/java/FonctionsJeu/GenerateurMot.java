package FonctionsJeu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class GenerateurMot {

	public GenerateurMot() {

	}

	public ArrayList<Carte> genere() throws FileNotFoundException {
		ArrayList<String> listeMots = new ArrayList<String>();
		ArrayList<Carte> listeCartes = new ArrayList<Carte>();
		Scanner scanner = new Scanner(new File("C:\\Users\\Admin\\git\\codename\\src\\main\\resources\\mots.txt"));
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			for (String mot : line.split("\\s")) {
				if (!mot.isEmpty()) {
					listeMots.add(mot);
				}
			}
		}
		scanner.close();
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