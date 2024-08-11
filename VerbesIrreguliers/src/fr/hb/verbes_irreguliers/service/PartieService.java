package fr.hb.verbes_irreguliers.service;

import java.util.Scanner;

import fr.hb.verbes_irreguliers.business.Partie;

public interface PartieService {

	Partie getPartie();

	void askQtyQuestions(int qty);

	void setServices(VerbeService verbeService, QuestionService questionService, Scanner sc);

	void newPartie();

}
