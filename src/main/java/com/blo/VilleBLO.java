package com.blo;

import java.util.ArrayList;

import com.dto.Ville;

public interface VilleBLO {

	public ArrayList<Ville> getAll() throws VilleException;
	public ArrayList<Ville> getVille(int codeInsee) throws VilleException;
	public ArrayList<Ville> getVilleByNom(String nom) throws VilleException;
	public ArrayList<Ville> getVilleByCodePostal(int codePostal) throws VilleException;
	public void createVille(Ville ville) throws VilleException;
	public void updateVilleNom(int codeInsee, String nom) throws VilleException;
	public void updateVilleCodePostal(int codeInsee, int codePostal)throws VilleException;
	public void updateVilleLongitude(int codeInsee, String longitude)throws VilleException;
	public void updateVilleLatitude(int codeInsee, String latitude)throws VilleException;
	public void updateVilleLibelleAcheminement(int codeInsee, String libelle) throws VilleException;
	public void deleteVille(int codeInsee)throws VilleException;
}
