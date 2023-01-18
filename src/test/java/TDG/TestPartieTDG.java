package TDG;

import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;


import Persistable.Equipe;
import Persistable.Joueur;
import Persistable.Partie;
import TDG.JoueurTDG;
import TDG.TDGRegistry;
import TDG.PartieTDG;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestPartieTDG {
	private PartieTDG ptdg;
	private EquipeTDG etdg; 
	private JoueurTDG jtdg;
	private Equipe e1 = new Equipe("LesRedDevils", 0, 0);
	private Joueur j1 = new Joueur("ZoraneTheGreat", 0, "espion");
	private Joueur j2 = new Joueur("ZoraneTheFake", 1, "decodeur");
	
	private Equipe e2 = new Equipe("Equipe2", 1, 1);
	private Joueur j3 = new Joueur("joueur3", 2, "espion");
	private Joueur j4 = new Joueur("joueur4", 3, "decodeur");
	
	private Partie p1 = new Partie(); 
	
	@BeforeAll
	public static void createTable() throws SQLException {
		TDGRegistry.findTDG(Joueur.class).createTable();
		TDGRegistry.findTDG(Equipe.class).createTable();
		TDGRegistry.findTDG(Partie.class).createTable();
	}

	@AfterAll
	public static void deleteTable() throws SQLException {
		TDGRegistry.findTDG(Partie.class).deleteTable();
		TDGRegistry.findTDG(Equipe.class).createTable();
		TDGRegistry.findTDG(Joueur.class).createTable();
		
	}


	@BeforeEach
	public void setUp() {
		ptdg = TDGRegistry.findTDG(Partie.class);
	}

	@Test
	public void EmptyBase() throws SQLException {
		assertNull(ptdg.findById(0));
	}

	@Test
	public void InsertingElement() throws SQLException {
		jtdg.insert(j1);
		jtdg.insert(j2);
		jtdg.insert(j3);
		jtdg.insert(j4);
		e1.setListeJoueurs(j1);
		e1.setListeJoueurs(j2);
		e2.setListeJoueurs(j3);
		e2.setListeJoueurs(j4);
		e1 = etdg.insert(e1);
		e2 = etdg.insert(e2);
		p1.setEquipe1(e1);
		p1.setEquipe2(e2);
		
		
		p1 = ptdg.insert(p1); 
		assertNotEquals(0, p1.getId());
		assertNotNull(ptdg.findById(p1.getId())); 
		assertSame(ptdg.findById(p1.getId()), p1);
		
	}
	
	@Test
	public void UpdatingElement() throws SQLException {
		p1 = ptdg.findById(p1.getId()); 
		p1.setEquipe1(e2);
		p1.setEquipe2(e1);
		p1 = ptdg.update(p1);
		assertSame(ptdg.findById(p1.getId()).getEquipe1(), e2);
		assertSame(ptdg.findById(p1.getId()).getEquipe2(), e1);
		
	}
	
	@Test
	public void DeletingElement() throws SQLException {
		ptdg.delete(p1);
		assertNull(ptdg.findById(p1.getId()));

	}
	


}