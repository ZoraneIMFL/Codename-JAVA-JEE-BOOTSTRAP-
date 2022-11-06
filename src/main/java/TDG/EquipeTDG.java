package TDG;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Persistable.Equipe;

public class EquipeTDG extends AbstractTDG<Equipe> {

	private static final String CREATE_EQUIPE_TABLE = "CREATE TABLE Equipe (ID BIGINT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY, NOM_EQUIPE VARCHAR(20) NOT NULL, SCORE BIGINT)";
	private static final String CREATE_JOUEUR_EQUIPE_TABLE = "CREATE TABLE EquipeJoueurs (ID BIGINT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY, IDEQUIPE BIGINT REFERENCES Equipe(ID), IDJOUEUR BIGINT REFERENCES Joueur(ID))";
	
	
	private static final String DROP_EQUIPE_TABLE = "DROP TABLE Equipe";
	private static final String DROP_JOUEUR_EQUIPE_TABLE = "DROP TABLE EquipeJoueurs";
	
	
	private static final String FIND_BY_ID_EQUIPE = "SELECT ID,NOM_EQUIPE, SCORE FROM Equipe e WHERE e.ID=?";
	private static final String FIND_BY_ID_JOUEUR_EQUIPE = "SELECT IDJOUEUR FROM EquipeJoueurs e WHERE e.IDEQUIPE=?";

	private static final String INSERT = "INSERT INTO Equipe (NOM_EQUIPE,SCORE) VALUES(?,?)";
	private static final String INSERT_INTO_JOUEUR_EQUIPE = "INSERT INTO EquipeJoueurs(IDEQUIPE, IDJOUEUR) VALUES(?,?)";
	
	private static final String UPDATE = "UPDATE Equipe e SET e.NOM_EQUIPE = ?, e.SCORE = ? WHERE e.ID = ?";
	private static final String WHERE = "SELECT ID FROM Equipe e WHERE ";
	
	private static final String DELETE_EQUIPE = "DELETE FROM Equipe e WHERE e.ID = ?";
	private static final String DELETE_JOUEUR_EQUIPE = "DELETE FROM EquipeJoueurs e WHERE e.IDEQUIPE = ?";

	@Override
	public void createTable() throws SQLException {
		try (Statement stm = TDGRegistry.getConnection().createStatement()) {
			stm.executeUpdate(CREATE_EQUIPE_TABLE);
			

		}
		try (Statement stm2 = TDGRegistry.getConnection().createStatement()){
			stm2.executeUpdate(CREATE_JOUEUR_EQUIPE_TABLE);
		}

	}

	@Override
	public void deleteTable() throws SQLException {
		
		try (Statement stm = TDGRegistry.getConnection().createStatement()) {
			stm.executeUpdate(DROP_JOUEUR_EQUIPE_TABLE);
		}
		try (Statement stm2 = TDGRegistry.getConnection().createStatement()) {
			stm2.executeUpdate(DROP_EQUIPE_TABLE);
			
		}
		

	}

	@Override
	protected Equipe retrieveFromDB(long id) throws SQLException {
		Equipe t = null;
		try (PreparedStatement pst = TDGRegistry.getConnection().prepareStatement(FIND_BY_ID_EQUIPE)) {
			pst.setLong(1, id);
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					t = new Equipe();
					t.setId(rs.getLong(1));
					t.setNomEquipe(rs.getString(3));
					t.setScore(rs.getInt(2));
				}
			}
		}
		return t;
	}

	@Override
	protected Equipe insertIntoDB(Equipe t) throws SQLException {
		try (PreparedStatement pst = TDGRegistry.getConnection().prepareStatement(INSERT,
				Statement.RETURN_GENERATED_KEYS)) {
			pst.setString(1, t.getNomEquipe());
			pst.setInt(2, t.getScore());
			int result = pst.executeUpdate();
			assert result == 1;
			try (ResultSet keys = pst.getGeneratedKeys()) {
				if (keys.next()) {
					((Equipe) t).setId(keys.getLong(1));
				}
			}
			for (int i = 0; i < t.getListeJoeurs().size(); i++) {

				try (PreparedStatement pst2 = TDGRegistry.getConnection().prepareStatement(INSERT_INTO_JOUEUR_EQUIPE,
						Statement.RETURN_GENERATED_KEYS)) {
					pst2.setLong(1, t.getId());
					pst2.setLong(2, t.getListeJoeurs().get(i));
					int result2 = pst2.executeUpdate();
					assert result2 == 1;
				}
			}
			return t;
		}
	}

	@Override
	protected Equipe updateIntoDB(Equipe t) throws SQLException {
		try (PreparedStatement pst = TDGRegistry.getConnection().prepareStatement(UPDATE)) {
            assert findById(t.getId()) == t;
            pst.setString(1, t.getNomEquipe());
            pst.setInt(2, t.getScore());
            pst.setLong(3, t.getId());
            int result = pst.executeUpdate();
            assert result == 1;
            return t;
        }
	}

	@Override
	protected Equipe refreshIntoDB(Equipe t) throws SQLException {
		try (PreparedStatement pst = TDGRegistry.getConnection().prepareStatement(FIND_BY_ID_EQUIPE)) {
            pst.setLong(1, t.getId());
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    t.setId(rs.getLong(1));
                    t.setNomEquipe(rs.getString(2));
                    t.setScore(rs.getInt(3));
                    try(PreparedStatement pst2 = TDGRegistry.getConnection().prepareStatement(FIND_BY_ID_JOUEUR_EQUIPE)){
                    	pst.setLong(1, t.getId());
                    	while (rs.next()) {
                    		t.setListeJoeurs(rs.getLong(1));
                    	}
                    	
                    }
                    
                }
            }
        }
        return t;
	}

	@Override
	protected Equipe deleteFromDB(Equipe t) throws SQLException {
		try (PreparedStatement pst = TDGRegistry.getConnection().prepareStatement(DELETE_JOUEUR_EQUIPE)) {
            assert findById(t.getId()) == t;
            pst.setLong(1, t.getId());
            int result = pst.executeUpdate();
            System.out.println(result);
            assert result != 1;
            
            try (PreparedStatement pst2 = TDGRegistry.getConnection().prepareStatement(DELETE_EQUIPE)) {
                assert findById(t.getId()) == t;
                pst2.setLong(1, t.getId());
                result = pst2.executeUpdate();
                System.out.println(result);
                assert result == 1;
            }
            return t;
        }

	}

	@Override
	protected String getWherePrefix() {
		return WHERE;
	}

}