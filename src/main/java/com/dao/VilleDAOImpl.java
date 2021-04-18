package com.dao;

import static com.dao.DAOUtilitaire.fermeturesSilencieuses;
import static com.dao.DAOUtilitaire.fermeturesSilencieuses;
import static com.dao.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.dto.Ville;
import com.dao.DAOException;
import com.dao.DAOFactory;

@Repository
public class VilleDAOImpl implements VilleDAO {
	private static final String SQL_SELECT = "SELECT * FROM Ville ORDER BY id";
	private static final String SQL_SELECT_BY_INSEE = "SELECT * FROM Ville WHERE Code_commune_INSEE = ?";
	private static final String SQL_SELECT_BY_NOM = "SELECT * FROM Ville WHERE Nom_commune = ?";
	private static final String SQL_SELECT_BY_CODE_POSTAL = "SELECT * FROM Ville WHERE Code_postal = ?";
	private static final String SQL_INSERT        = "INSERT INTO Ville (Code_commune_INSEE, Nom_commune, Code_postal, Libelle_acheminement, Ligne_5, Latitude, Longitude) VALUES (?, ?, ?, ?, '', ?, ?)";
    private static final String SQL_DELETE		  = "DELETE FROM Ville WHERE Code_commune_INSEE = ?";
    private static final String SQL_SET_NOM = "UPDATE Ville SET Nom_commune = ? WHERE Code_commune_INSEE = ?";
    private static final String SQL_SET_CODE_POSTAL = "UPDATE Ville SET Code_postal = ? WHERE Code_commune_INSEE = ?";
    private static final String SQL_SET_LONGITUDE = "UPDATE Ville SET Longitude = ? WHERE Code_commune_INSEE = ?";
    private static final String SQL_SET_LATITUDE = "UPDATE Ville SET Latitude = ? WHERE Code_commune_INSEE = ?";
	private DAOFactory daoFactory;
	
	VilleDAOImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }

	@Override
	public ArrayList<Ville> getAll() {
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Ville> villes = new ArrayList<Ville>();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement( SQL_SELECT );
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) {
            	villes.add( map( resultSet ) );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connection );
        }

        return villes;
	}
	
	 private static Ville map( ResultSet resultSet ) throws SQLException {
		 Ville ville = new Ville();
		 ville.setCodeInsee(resultSet.getInt("Code_commune_INSEE"));
		 ville.setNom(resultSet.getString("Nom_commune"));
		 ville.setCodePostal(resultSet.getInt("Code_postal"));
		 ville.setLatitude(resultSet.getString("Latitude"));
		 ville.setLongitude(resultSet.getString("Longitude"));
		 return ville;
	 }

	@Override
	// On récupère un ArrayList de villes pour standardiser le code. 
	// En réalité on en récupérera toujours 1 seule vu que c'est la clé primaire.
	public ArrayList<Ville> getVille(int codeInsee) {
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Ville> villes = new ArrayList<Ville>();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement( SQL_SELECT_BY_INSEE );
            preparedStatement.setInt(1, codeInsee);
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) {
            	villes.add( map( resultSet ) );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connection );
        }
		return villes;
	}
	
	@Override
	public ArrayList<Ville> getVilleByNom(String nom) {
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Ville> villes = new ArrayList<Ville>();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement( SQL_SELECT_BY_NOM );
            preparedStatement.setString(1, nom);
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) {
            	villes.add( map( resultSet ) );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connection );
        }
		return villes;
	}
	@Override
	public ArrayList<Ville> getVilleByCodePostal(int codePostal) {
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Ville> villes = new ArrayList<Ville>();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement( SQL_SELECT_BY_CODE_POSTAL );
            preparedStatement.setInt(1, codePostal);
            resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ) {
            	villes.add( map( resultSet ) );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connection );
        }
		return villes;
	}

	@Override
	public void createVille(Ville ville) {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true,
            		ville.getCodeInsee(), ville.getNom(),
            		ville.getCodePostal(), ville.getLibelleAcheminement(),
            		ville.getLatitude(), ville.getLongitude());
            int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la création de la ville, aucune ligne ajoutée dans la table." );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( preparedStatement, connexion );
        }
	}

	@Override
	public void updateVilleNom(int codeInsee, String nom) {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SET_NOM, true,
            		codeInsee,
            		nom);
            int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DAOException( "Échec de l'update de la ville, aucune ligne modifiée dans la table." );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( preparedStatement, connexion );
        }
	}

	@Override
	public void deleteVille(int codeInsee) {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_DELETE, true,
            		codeInsee);
            int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DAOException( "Échec du delete de la ville." );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( preparedStatement, connexion );
        }
	}

	@Override
	public void updateVilleCodePostal(int codeInsee, int codePostal) {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SET_CODE_POSTAL, true,
            		codeInsee,
            		codePostal);
            int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DAOException( "Échec de l'update de la ville, aucune ligne modifiée dans la table." );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( preparedStatement, connexion );
        }
	}

	@Override
	public void updateVilleLongitude(int codeInsee, String longitude) {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SET_LONGITUDE, true,
            		codeInsee,
            		longitude);
            int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DAOException( "Échec de l'update de la ville, aucune ligne modifiée dans la table." );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( preparedStatement, connexion );
        }
	}

	@Override
	public void updateVilleLatitude(int codeInsee, String latitude) {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SET_LATITUDE, true,
            		codeInsee,
            		latitude);
            int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DAOException( "Échec de l'update de la ville, aucune ligne modifiée dans la table." );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( preparedStatement, connexion );
        }
	}



}
