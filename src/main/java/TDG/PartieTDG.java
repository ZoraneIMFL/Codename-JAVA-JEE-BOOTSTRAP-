package TDG;

import java.sql.SQLException;

import Persistable.Partie;

public class PartieTDG extends AbstractTDG<Partie>{
	
	private static final String CREATE = "CREATE TABLE Partie (ID BIGINT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY, ID_EQUIPE1 REFERENCES Equipe(ID), ID_EQUIPE2 REFERENCES Equipe(ID), EQUIPE_GAGNANTE Varchar(20)             )";

	@Override
	public void createTable() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteTable() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Partie retrieveFromDB(long id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Partie insertIntoDB(Partie t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Partie updateIntoDB(Partie t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Partie refreshIntoDB(Partie t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Partie deleteFromDB(Partie t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getWherePrefix() {
		// TODO Auto-generated method stub
		return null;
	}
	
}