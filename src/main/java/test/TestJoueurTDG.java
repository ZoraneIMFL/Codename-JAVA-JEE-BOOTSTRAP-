package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import Persistable.Joueur;
import TDG.JoueurTDG;
import TDG.TDGRegistry;

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
		Joueur a2 = jtdg.findById(j1.getId());
		assertEquals(j1, a2);
		assertSame(j1, a2);
	}
	
	@Test
	public void DeletingElement() throws SQLException {
		j1 = jtdg.insert(j1);
		assertNotEquals(0, j1.getId());
		Joueur j2 = jtdg.findById(j1.getId());
		assertEquals("ZoraneTheGreat", j2.getPseudo());
		jtdg.delete(j2);
		assertNull(jtdg.findById(1));

	}


}