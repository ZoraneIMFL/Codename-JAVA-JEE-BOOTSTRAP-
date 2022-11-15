package TDG;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Persistable.Joueur;
import Persistable.Partie;

public class PartieTDG extends AbstractTDG<Partie>{
	
	private static final String CREATE = "CREATE TABLE Partie (ID BIGINT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY, ID_EQUIPE1 REFERENCES Equipe(ID), ID_EQUIPE2 REFERENCES Equipe(ID), EQUIPE_GAGNANTE Varchar(20)";
	private static final String DROP = "DROP TABLE Partie";
	private static final String INSERT = "INSERT INTO Partie (ID_EQUIPE1,ID_EQUIPE2, EQUIPE_GAGNANTE) VALUES(?,?,?)";
	private static final String UPDATE = "UPDATE Partie p SET p.ID_EQUIPE1 = ?, p.ID_EQUIPE2 = ? , p.EQUIPE_GAGNANTE = ? WHERE p.ID = ?";
	private static final String DELETE = "DELETE FROM Partie p WHERE p.ID = ?";
	private static final String FIND_BY_ID = "SELECT ID,ID_EQUIPE1, ID_EQUIPE2, EQUIPE_GAGNANTE FROM Partie p WHERE p.ID=?";
	private static final String WHERE = "SELECT ID FROM Partie p WHERE ";
	
	
	@Override
	public void createTable() throws SQLException {
		try (Statement stm = TDGRegistry.getConnection().createStatement()) {
            stm.executeUpdate(CREATE);
            
        }
		
	}

	@Override
	public void deleteTable() throws SQLException {
		try (Statement stm = TDGRegistry.getConnection().createStatement()) {
            stm.executeUpdate(DROP);
        }
		
	}

	@Override
	protected Partie retrieveFromDB(long id) throws SQLException {
		Partie t = null;
        try (PreparedStatement pst = TDGRegistry.getConnection().prepareStatement(FIND_BY_ID)) {
            pst.setLong(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    t = new Partie();
                    t.setId(rs.getLong(1));
                    t.setIdEquipe2(rs.getLong(3));
                    t.setIdEquipe1(rs.getLong(2));
                    t.setEquipeGagnante(CREATE);
                }
            }
        }
        return t;
	}

	@Override
	protected Partie insertIntoDB(Partie t) throws SQLException {
		try (PreparedStatement pst = TDGRegistry.getConnection().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) { 
            pst.setLong(1, t.getIdEquipe1());
            pst.setLong(2, t.getIdEquipe2());
            pst.setString(3, t.getEquipeGagnante());
            int result = pst.executeUpdate();
            assert result == 1;
            try (ResultSet keys = pst.getGeneratedKeys()) {
                if (keys.next()) {
                	t.setId(keys.getLong(1));
                }
            }
            return t;
        }
	}

	@Override
	protected Partie updateIntoDB(Partie t) throws SQLException {
		try (PreparedStatement pst = TDGRegistry.getConnection().prepareStatement(UPDATE)) {
            assert findById(t.getId()) == t;
            pst.setLong(1, t.getIdEquipe1());
            pst.setLong(2,  t.getIdEquipe1());
            pst.setString(3, t.getEquipeGagnante());
            
            pst.setLong(4, t.getId());
            int result = pst.executeUpdate();
            assert result == 1;
            return t;
        }
	}

	@Override
	protected Partie refreshIntoDB(Partie t) throws SQLException {
		try (PreparedStatement pst = TDGRegistry.getConnection().prepareStatement(FIND_BY_ID)) {
            pst.setLong(1, t.getId());
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    t.setId(rs.getLong(1));
                    t.setIdEquipe1(rs.getLong(2));
                    t.setIdEquipe2(rs.getLong(3));
                }
            }
        }
        return t;
	}

	@Override
	protected Partie deleteFromDB(Partie t) throws SQLException {
		try (PreparedStatement pst = TDGRegistry.getConnection().prepareStatement(DELETE)) {
            assert findById(t.getId()) == t;
            pst.setLong(1, t.getId());
            int result = pst.executeUpdate();
            assert result == 1;
            return t;
        }

	}

	@Override
	protected String getWherePrefix() {
		return WHERE;
	}
	
}