package FonctionsJeu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;

public class NomProject{
	
	
	public NomProject() {
		
	}
	public String getNom()  {
		InputStream is = getClass().getClassLoader().getResourceAsStream("./settings.gradle");
		ArrayList<String> liste2 = new ArrayList<String>();
		var br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
		String st;
		int v=0;
		try{
			while ((st = br.readLine()) != null) {

				Collections.addAll(liste2, st.split("= "));

				v++;
			}
			br.close();
		}
		catch (IOException e){

		}




		//return "slip";
		return liste2.get(v).replace("'", "");
	}
}


