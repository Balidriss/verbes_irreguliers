package fr.hb.verbes_irreguliers.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import fr.hb.verbes_irreguliers.business.Question;
import fr.hb.verbes_irreguliers.business.Verbe;
import fr.hb.verbes_irreguliers.service.PartieService;
import fr.hb.verbes_irreguliers.service.QuestionService;
import fr.hb.verbes_irreguliers.service.VerbeService;

public class QuestionServiceImpl implements QuestionService {
	private QuestionService questionService;
	private PartieService partieService;
	private VerbeService verbeService;
	private Scanner sc;
	private List<Integer> ids;
	private int currentId;

	public QuestionServiceImpl() {

	}

	public void shuffleIds() {
		Collections.shuffle(getIds());
	}

	@Override
	public void nextQuestion() {
		Question question;
		int currentId = getCurrentId();

		Verbe currentVerbe = verbeService.getId(getIds().get(currentId));
		question = new Question(currentVerbe);// , currentVerbe.getParticipePasse());
		System.out.println("Question " + (currentId + 1) + ": Donnez le prétérit et le participe passé du verbe "
				+ currentVerbe.getBaseVerbale() + " :");
		String userInput = sc.nextLine();
		String[] parts = userInput.split(",\\s*"); // regex pour espaces après la virgule

		if (parts.length == 2) {
			boolean correctPreterit = question.getVerbe().getPreterit().equals("\"" + parts[0] + "\"");
			boolean correctParticipePasse = question.getVerbe().getPreterit().equals("\"" + parts[1] + "\"");

			if (correctPreterit && correctParticipePasse) {
				if (partieService.getPartie()
						.getNbQuestionsSouhaitees() > partieService.getPartie().getQuestions().size() + 1) {
					System.out.println("Bravo ! Score : " + (partieService.getPartie().getScore() + 1) + "/"
							+ (partieService.getPartie().getQuestions().size() + 1));
					partieService.getPartie().setScore(partieService.getPartie().getScore() + 1);
				} else {
					System.out.println("Bravo ! Score final : " + (partieService.getPartie().getScore() + 1) + "/"
							+ (partieService.getPartie().getQuestions().size() + 1));
					partieService.getPartie().setScore(partieService.getPartie().getScore() + 1);
				}

			} else {
				if (partieService.getPartie()
						.getNbQuestionsSouhaitees() > partieService.getPartie().getQuestions().size() + 1) {
					System.out.println("Ce n’est pas la bonne réponse. Score : " + partieService.getPartie().getScore()
							+ "/" + (partieService.getPartie().getQuestions().size() + 1));
				} else {
					System.out.println(
							"Ce n’est pas la bonne réponse. Score final : " + partieService.getPartie().getScore() + "/"
									+ (partieService.getPartie().getQuestions().size() + 1));
				}
			}

		} else {

			System.out.println("Suivez la consigne !");
			if (partieService.getPartie().getNbQuestionsSouhaitees() > partieService.getPartie().getQuestions().size()
					+ 1) {
				System.out.println("Ce n’est pas la bonne réponse. Score : " + partieService.getPartie().getScore()
						+ "/" + (partieService.getPartie().getQuestions().size() + 1));
			} else {
				System.out
						.println("Ce n’est pas la bonne réponse. Score final : " + partieService.getPartie().getScore()
								+ "/" + (partieService.getPartie().getQuestions().size() + 1));
			}
		}
		question.setDateHeureReponse(LocalDateTime.now());
		partieService.getPartie().getQuestions().add(question);
		setCurrentId(getCurrentId() + 1);

	}

	@Override
	public void setServices(VerbeService verbeService, PartieService partieService, Scanner sc) {
		this.partieService = partieService;
		this.verbeService = verbeService;
		this.sc = sc;

	}

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}

	public void generateRandomIds(int qty) {
		setIds(new ArrayList<Integer>());
		List<Integer> temp = new ArrayList<Integer>();

		for (int i = 0; i < verbeService.getVerbes().size(); i++) {
			temp.add(i);
		}
		Collections.shuffle(temp);
		setIds(new ArrayList<Integer>(temp.subList(0, qty)));
	}

	public int getCurrentId() {
		return currentId;
	}

	public void setCurrentId(int currentId) {
		this.currentId = currentId;
	}

	@Override
	public void prepareRandomIds() {
		generateRandomIds(partieService.getPartie().getNbQuestionsSouhaitees());
		setCurrentId(0);
	}

}
