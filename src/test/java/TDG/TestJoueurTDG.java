package TDG;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;

import Persistable.Joueur;
import TDG.JoueurTDG;
import TDG.TDGRegistry;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestJoueurTDG {
	private JoueurTDG jtdg;
	private Joueur j1 = new Joueur("ZoraneTheGreat", 0, "espion");

	
	@BeforeClass
	public static void createTable() throws SQLException {
		TDGRegistry.findTDG(Joueur.class).createTable();
	}

	@AfterClass
	public static void deleteTable() throws SQLException {
		TDGRegistry.findTDG(Joueur.class).deleteTable();
	}


	@Before
	public void setUp() {
		jtdg = TDGRegistry.findTDG(Joueur.class);
	}

	@Test
	public void EmptyBase() throws SQLException {
		assertNull(jtdg.findById(0));
	}

	@Test
	public void InsertingElement() throws SQLException {
		j1 = jtdg.insert(j1);
		assertNotEquals(0, j1.getId());
		
		assertEquals(j1.getId(), jtdg.findById(j1.getId()).getId());
		assertSame(j1, jtdg.findById(j1.getId()));
	}
	
	
	@Test
	public void UpdatingElement() throws SQLException {
		assertEquals("ZoraneTheGreat", j1.getPseudo());
		j1.setPseudo("NouveauPseudo"); 
		jtdg.update(j1); 
		assertEquals(jtdg.findById(j1.getId()).getPseudo(), "NouveauPseudo"); 
		
	}
	
	@Test
	public void DeletingElement() throws SQLException {

		Joueur j2 = jtdg.findById(j1.getId());
		assertEquals("ZoraneTheGreat", j2.getPseudo());
		jtdg.delete(j2);
		assertNull(jtdg.findById(j1.getId()));

	}


}