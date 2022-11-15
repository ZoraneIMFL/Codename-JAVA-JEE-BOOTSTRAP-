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
import Persistable.Equipe;
import Persistable.Joueur;
import TDG.EquipeTDG;
import TDG.JoueurTDG;
import TDG.TDGRegistry;

public class TestEquipeTDG {

	private EquipeTDG etdg;
	private JoueurTDG jtdg;
	private Equipe e1 = new Equipe("LesRedDevils", 0);
	private Joueur j1 = new Joueur("ZoraneTheGreat", 0, "espion");
	private Joueur j2 = new Joueur("ZoraneTheFake", 1, "decodeur");

	@BeforeClass
	public static void createTable() throws SQLException {
		TDGRegistry.findTDG(Joueur.class).createTable();
		TDGRegistry.findTDG(Equipe.class).createTable();
	}

	@AfterClass
	public static void deleteTable() throws SQLException {
		TDGRegistry.findTDG(Equipe.class).deleteTable();
		TDGRegistry.findTDG(Joueur.class).deleteTable();

	}

	@Before
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
		// Phase d'initialisation qu'on ne peut mettre dans un test @beforeEach car les
		// modifications sur nos attributs se sauvegarde pas.
		j1 = jtdg.insert(j1);
		j2 = jtdg.insert(j2);
		assertNotEquals(0, j1.getId());
		assertNotEquals(1, j2.getId());
		e1.setListeJoeurs(j1.getId());
		e1.setListeJoeurs(j2.getId());

		e1 = etdg.insert(e1);
		assertNotEquals(0, e1.getId());
		assertSame(etdg.findById(e1.getId()), e1);
	}

	@Test
	public void DeletingElement() throws SQLException {
		// Phase d'initialisation qu'on ne peut mettre dans un test @beforeEach car les
		// modifications sur nos attributs se sauvegarde pas.
		j1 = jtdg.insert(j1);
		j2 = jtdg.insert(j2);
		e1.setListeJoeurs(j1.getId());
		e1.setListeJoeurs(j2.getId());
		e1 = etdg.insert(e1);

		etdg.delete(e1);
		assertNull(etdg.findById(e1.getId()));

	}

	@Test
	public void UpdatingElement() throws SQLException {
		// Phase d'initialisation qu'on ne peut mettre dans un test @beforeEach car les
		// modifications sur nos attributs se sauvegarde pas.
		j1 = jtdg.insert(j1);
		j2 = jtdg.insert(j2);
		e1.setListeJoeurs(j1.getId());
		e1.setListeJoeurs(j2.getId());
		e1 = etdg.insert(e1);
		assertEquals(etdg.findById(e1.getId()).getScore(), 0);

		e1.setNomEquipe("NouveauNom");
		e1.setScore(3);
		etdg.update(e1);
		assertEquals(etdg.findById(e1.getId()).getNomEquipe(), "NouveauNom");
		assertNotEquals(etdg.findById(e1.getId()).getScore(), 0);

	}

}