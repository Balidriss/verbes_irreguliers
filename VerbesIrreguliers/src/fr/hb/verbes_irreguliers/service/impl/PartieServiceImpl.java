package fr.hb.verbes_irreguliers.service.impl;

import java.util.Scanner;

import fr.hb.verbes_irreguliers.business.Partie;
import fr.hb.verbes_irreguliers.service.PartieService;
import fr.hb.verbes_irreguliers.service.QuestionService;
import fr.hb.verbes_irreguliers.service.VerbeService;

public class PartieServiceImpl implements PartieService {
	private QuestionService questionService;
	private PartieService partieService;
	private VerbeService verbeService;
	private Scanner sc;
	private Partie partie;

	public PartieServiceImpl() {
		super();
		this.partie = new Partie();
	}

	@Override
	public Partie getPartie() {
		return this.partie;
	}

	@Override
	public void askQtyQuestions() {
		System.out.println("Combien de verbes pour cette partie ?");

		// TODO: VALIDATOR
		getPartie().setNbQuestionsSouhaitees(sc.nextInt());
		sc.nextLine();

	}

	@Override
	public void recap() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean askNew() {
		System.out.println("Souhaitez-vous faire une nouvelle partie (o/n) ?");
		String userInput = sc.nextLine();
		return userInput.equals("o");

	}

	@Override
	public boolean hasNewPartie() {
		// TODO Auto-generated method stub
		return false;
	}

	// question()/// boucle // resultat score/countScore
	@Override
	public void askQuestions() {
		questionService.prepareRandomIds();
		for (int i = 0; i < partie.getNbQuestionsSouhaitees(); i++) {

			questionService.nextQuestion();
		}
		System.out.println("fin questions");
	}

	@Override
	public void setServices(VerbeService verbeService, QuestionService questionService, Scanner sc) {

		this.verbeService = verbeService;
		this.questionService = questionService;
		this.sc = sc;

	}

}
