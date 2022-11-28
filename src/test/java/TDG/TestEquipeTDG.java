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

@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestEquipeTDG {

	private EquipeTDG etdg;
	private JoueurTDG jtdg;
	private Equipe e1 = new Equipe("LesRedDevils", 0, 0);
	private Joueur j1 = new Joueur("ZoraneTheGreat", 0, "espion");
	private Joueur j2 = new Joueur("ZoraneTheFake", 1, "decodeur");

	@BeforeAll
	public static void createTable() throws SQLException {
		TDGRegistry.findTDG(Joueur.class).createTable();
		TDGRegistry.findTDG(Equipe.class).createTable();
		
	}

	@AfterAll
	public static void deleteTable() throws SQLException {
		TDGRegistry.findTDG(Equipe.class).deleteTable();
		TDGRegistry.findTDG(Joueur.class).deleteTable();

	}

	@BeforeEach
	public void setUp() {
		etdg = TDGRegistry.findTDG(Equipe.class);
		jtdg = TDGRegistry.findTDG(Joueur.class);
	}

	@Test
	public void EmptyBase() throws SQLException {
		assertNull(etdg.findById(0));
	}

	@Test
	public void InsertingElement() throws SQLException {
		j1 = jtdg.insert(j1);
		j2 = jtdg.insert(j2);

		e1.setListeJoueurs(j1);
		e1.setListeJoueurs(j2);

		e1 = etdg.insert(e1);
		assertNotEquals(0, e1.getId());
		assertSame(etdg.findById(e1.getId()), e1);
	}
	
	@Test
	public void UpdatingElement() throws SQLException {
		e1 = etdg.findById(e1.getId()); 
		assertEquals(e1.getScore(), 0);
		e1.setNomEquipe("NouveauNom");
		e1.setScore(3);
		etdg.update(e1);
		assertEquals(etdg.findById(e1.getId()).getNomEquipe(), "NouveauNom");
		assertNotEquals(etdg.findById(e1.getId()).getScore(), 0);

	}


	@Test
	public void DeletingElement() throws SQLException {
		etdg.delete(e1);
		assertNull(etdg.findById(e1.getId()));

	}

	
}