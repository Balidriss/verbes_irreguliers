package fr.hb.verbes_irreguliers.service.impl;

import java.util.List;
import java.util.Scanner;

import fr.hb.verbes_irreguliers.business.Partie;
import fr.hb.verbes_irreguliers.business.Verbe;
import fr.hb.verbes_irreguliers.service.PartieService;
import fr.hb.verbes_irreguliers.service.QuestionService;
import fr.hb.verbes_irreguliers.service.VerbeService;

public class PartieServiceImpl implements PartieService {

	
	
	
	
	public PartieServiceImpl() {
		super();
	}

	@Override
	public void start()
	{
		
		Partie partie = new Partie();
		
		
		
	//nouvelle partie //boucle
		//choisir combien de questions
			//question()/// boucle
				//resultat score/countScore
			//resultat final  // AVGdeltaTime
			//log display.
	//ask new game
	//
	//sinon bye
	//
	}

	public void displayNbVerbeTotal(List<Verbe> verbes)
	{
		
		System.out.println("Nombre de verbes irréguliers disponibles : " + verbes.size());
	}
}
