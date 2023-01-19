package TDG;

import java.sql.SQLException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import Persistable.Joueur;


public class JoueurTDG extends AbstractTDG<Joueur> {
	

	
	private static final String CREATE = "CREATE TABLE Joueur (ID BIGINT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY, PSEUDO VARCHAR(20) NOT NULL, ROLE VARCHAR(20))";
    private static final String DROP = "DROP TABLE Joueur";
    private static final String INSERT = "INSERT INTO Joueur (PSEUDO,ROLE) VALUES(?,?)";
    private static final String UPDATE = "UPDATE Joueur j SET j.Pseudo = ?, j.Role = ? WHERE j.ID = ?";
    private static final String DELETE = "DELETE FROM Joueur j WHERE j.ID = ?";
    private static final String FIND_BY_ID = "SELECT ID,PSEUDO, ROLE FROM Joueur j WHERE j.ID=?";
    private static final String WHERE = "SELECT ID FROM Joueur j WHERE ";


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
	protected Joueur retrieveFromDB(long id) throws SQLException {
		Joueur t = null;
        try (PreparedStatement pst = TDGRegistry.getConnection().prepareStatement(FIND_BY_ID)) {
            pst.setLong(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    t = new Joueur();
                    t.setId(rs.getLong(1));
                    t.setRole(rs.getString(3));
                    t.setPseudo(rs.getString(2));
                }
            }
        }
        return t;

	}

	@Override
	protected Joueur insertIntoDB(Joueur t) throws SQLException {
		try (PreparedStatement pst = TDGRegistry.getConnection().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) { 
            pst.setString(1, t.getPseudo());
            pst.setString(2, t.getRole());
            int result = pst.executeUpdate();
            assert result == 1;
            try (ResultSet keys = pst.getGeneratedKeys()) {
                if (keys.next()) {
                    ((Joueur) t).setId(keys.getLong(1));
                }
            }
            return t;
        }

	}

	@Override
	protected Joueur updateIntoDB(Joueur t) throws SQLException {
		try (PreparedStatement pst = TDGRegistry.getConnection().prepareStatement(UPDATE)) {
            assert findById(t.getId()) == t;
            pst.setString(1, ((Joueur) t).getPseudo());
            pst.setString(2, ((Joueur) t).getRole());
            
            pst.setLong(3, t.getId());
            int result = pst.executeUpdate();
            assert result == 1;
            return t;
        }

	}

	@Override
	protected String getWherePrefix() {
		return WHERE;
	}


	@Override
	protected Joueur refreshIntoDB(Joueur t) throws SQLException {
		try (PreparedStatement pst = TDGRegistry.getConnection().prepareStatement(FIND_BY_ID)) {
            pst.setLong(1, t.getId());
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    t.setId(rs.getLong(1));
                    t.setPseudo(rs.getString(2));
                    t.setRole(rs.getString(3));
                }
            }
        }
        return t;

	}

	@Override
	protected Joueur deleteFromDB(Joueur t) throws SQLException {
		try (PreparedStatement pst = TDGRegistry.getConnection().prepareStatement(DELETE)) {
            assert findById(t.getId()) == t;
            pst.setLong(1, t.getId());
            int result = pst.executeUpdate();
            assert result == 1;
            return t;
        }

	}
	
}