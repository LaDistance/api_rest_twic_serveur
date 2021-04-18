package com.blo;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.VilleDAO;
import com.dto.Ville;

@Service
public class VilleBLOImpl implements VilleBLO {

	@Autowired
	private VilleDAO villeDAO;

	@Override
	public ArrayList<Ville> getAll() throws VilleException {
		ArrayList<Ville> listVille;

		listVille = villeDAO.getAll();
		System.out.println("Nombre de ville récupérée(s) : " + listVille.size());

		return listVille;
	}

	@Override
	public ArrayList<Ville> getVille(int codeInsee) throws VilleException {
		ArrayList<Ville> listVille;

		listVille = villeDAO.getVille(codeInsee);
		System.out.println("Ville récupérée : " + listVille.get(0));

		return listVille;
	}

	@Override
	public ArrayList<Ville> getVilleByNom(String nom) throws VilleException {
		ArrayList<Ville> listVille;

		listVille = villeDAO.getVilleByNom(nom);
		System.out.println("Ville récupérée : " + listVille.get(0));

		return listVille;
	}

	@Override
	public ArrayList<Ville> getVilleByCodePostal(int codePostal) throws VilleException {
		ArrayList<Ville> listVille;

		listVille = villeDAO.getVilleByCodePostal(codePostal);
		System.out.println("Ville récupérée : " + listVille.get(0));

		return listVille;
	}

	@Override
	public void createVille(Ville ville) throws VilleException {
		villeDAO.createVille(ville);
		System.out.println("Ville created : "+ ville);
	}

	@Override
	public void updateVilleNom(int codeInsee, String nom) throws VilleException {
		villeDAO.updateVilleNom(codeInsee, nom);
		System.out.println("Ville updated, code INSEE : " + codeInsee + ", nouveau nom : " + nom);
	}

	@Override
	public void updateVilleCodePostal(int codeInsee, int codePostal) throws VilleException {
		villeDAO.updateVilleCodePostal(codeInsee, codePostal);
		System.out.println("Ville updated, code INSEE : " + codeInsee + ", nouveau code postal : " + codePostal);
	}

	@Override
	public void updateVilleLongitude(int codeInsee, String longitude) throws VilleException {
		villeDAO.updateVilleLongitude(codeInsee, longitude);
		System.out.println("Ville updated, code INSEE : " + codeInsee + ", nouvelle longitude : " + longitude);
	}

	@Override
	public void updateVilleLatitude(int codeInsee, String latitude) throws VilleException {
		villeDAO.updateVilleLongitude(codeInsee, latitude);
		System.out.println("Ville updated, code INSEE : " + codeInsee + ", nouvelle latitude : " + latitude);
	}

	@Override
	public void deleteVille(int codeInsee) throws VilleException {
		villeDAO.deleteVille(codeInsee);
		System.out.println("Ville deleted, code INSEE : " + codeInsee);
	}

}
