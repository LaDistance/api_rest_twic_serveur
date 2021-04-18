package com.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.blo.VilleBLO;
import com.dto.Ville;

@RestController
//@RequestMapping("/path")
class VilleController {

	@Autowired
	VilleBLO villeService;

	// Methode GET
	@RequestMapping(value = "/villes", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Ville> getAllVilles() {
		System.out.println("Appel GET : get all Villes");
		
		ArrayList<Ville> villes = villeService.getAll();
		
		return villes;
	}
	
	@RequestMapping(value = "/ville/insee/{codeInsee}", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Ville> getVille(@PathVariable int codeInsee){
		System.out.println("Appel GET : get Ville, code INSEE : "+ codeInsee);
		
		return villeService.getVille(codeInsee);
	}
	
	@RequestMapping(value = "/ville/nom/{nom}", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Ville> getVilleByNom(@PathVariable String nom){
		System.out.println("Appel GET : get Ville, nom: "+ nom);
		return villeService.getVilleByNom(nom);
	}
	
	@RequestMapping(value = "/ville/postal/{codePostal}", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Ville> getVilleByCodePostal(@PathVariable int codePostal){
		System.out.println("Appel GET : get Ville, code postal: "+ codePostal);
		return villeService.getVilleByCodePostal(codePostal);
	}
	// TODO : create, updates, delete
}
