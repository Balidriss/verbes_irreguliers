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
	public void askQtyQuestions(int qty) {
		// TODO: VALIDATOR
		getPartie().setNbQuestionsSouhaitees(qty);

	}

	private void displayLogedQuestions() {

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
