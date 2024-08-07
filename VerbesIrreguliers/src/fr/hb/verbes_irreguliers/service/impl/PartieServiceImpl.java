package fr.hb.verbes_irreguliers.service.impl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import fr.hb.verbes_irreguliers.business.Partie;
import fr.hb.verbes_irreguliers.business.Question;
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

		System.out.println("Temps moyen de réponse : " + calculateAverageTime(getPartie().getQuestions()).getSeconds()
				+ " secondes.");

		System.out.println("Historique des verbes demandés : ");
		displayLogedQuestions();

	}

	private void displayLogedQuestions() {
		getPartie().getQuestions().forEach(System.out::println);

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

	@Override
	public void newPartie() {
		this.partie = new Partie();

	}

	public Duration calculateAverageTime(List<Question> questions) {
		if (questions.isEmpty()) {
			return Duration.ZERO;
		}

		Duration totalDuration = Duration.ZERO;
		int count = 0;

		for (Question question : questions) {
			LocalDateTime dateHeureEnvoi = question.getDateHeureEnvoi();
			LocalDateTime dateHeureReponse = question.getDateHeureReponse();

			if (dateHeureEnvoi != null && dateHeureReponse != null && !dateHeureReponse.isBefore(dateHeureEnvoi)) {
				Duration duration = Duration.between(dateHeureEnvoi, dateHeureReponse);
				totalDuration = totalDuration.plus(duration);
				count++;
			}
		}

		return count == 0 ? Duration.ZERO : totalDuration.dividedBy(count);
	}

}
