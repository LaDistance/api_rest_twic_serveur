package com.dao;

import java.util.ArrayList;

import com.dto.Ville;

public interface VilleDAO {
	
	public ArrayList<Ville> getAll();
	public ArrayList<Ville> getVille(int codeInsee);
	public ArrayList<Ville> getVilleByNom(String nom);
	public ArrayList<Ville> getVilleByCodePostal(int codePostal);
	public void createVille(Ville ville);
	public void updateVilleNom(int codeInsee, String nom);
	public void updateVilleCodePostal(int codeInsee, int codePostal);
	public void updateVilleLongitude(int codeInsee, String longitude);
	public void updateVilleLatitude(int codeInsee, String latitude);
	public void deleteVille(int codeInsee);
}
