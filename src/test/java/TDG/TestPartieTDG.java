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
import Persistable.Partie;
import TDG.JoueurTDG;
import TDG.TDGRegistry;

public class TestPartieTDG {
	private PartieTDG ptdg;
	
	@BeforeClass
	public static void createTable() throws SQLException {
		TDGRegistry.findTDG(Partie.class).createTable();
	}

	@AfterClass
	public static void deleteTable() throws SQLException {
		TDGRegistry.findTDG(Partie.class).deleteTable();
	}


	@Before
	public void setUp() {
		ptdg = TDGRegistry.findTDG(Partie.class);
	}

	@Test
	public void EmptyBase() throws SQLException {
		assertNull(ptdg.findById(0));
	}

	@Test
	public void InsertingElement() throws SQLException {
	}
	
	@Test
	public void DeletingElement() throws SQLException {
		

	}
	@Test
	public void UpdatingElement() throws SQLException {
		
		
	}


}