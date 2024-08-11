package fr.hb.verbes_irreguliers;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import fr.hb.verbes_irreguliers.business.Question;
import fr.hb.verbes_irreguliers.business.Verbe;
import fr.hb.verbes_irreguliers.service.PartieService;
import fr.hb.verbes_irreguliers.service.QuestionService;
import fr.hb.verbes_irreguliers.service.VerbeService;
import fr.hb.verbes_irreguliers.service.impl.PartieServiceImpl;
import fr.hb.verbes_irreguliers.service.impl.QuestionServiceImpl;
import fr.hb.verbes_irreguliers.service.impl.VerbeServiceImpl;

public class App {

	static VerbeService verbeService = new VerbeServiceImpl();
	static QuestionService questionService = new QuestionServiceImpl();
	static PartieService partieService = new PartieServiceImpl();
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		singletonize();

		s("Bienvenue sur verbes irréguliers");

		System.out.println("Nombre de verbes irréguliers disponibles : " + verbeService.getVerbes().size());

		do {

			s("Nouvelle partie !");
			partieService.newPartie();
			s("Combien de verbes pour cette partie ?");
			int qtyQuestions = sc.nextInt();
			sc.nextLine();
			partieService.askQtyQuestions(qtyQuestions);
			s("Merci de séparer le prétérit et le participe passé avec une virgule");
			verbeService.prepareRandomVerbes();

			for (int i = 0; i < partieService.getPartie().getNbQuestionsSouhaitees(); i++) {
				Question question;
				Verbe currentVerbe = verbeService.getRandomVerbe(i);
				question = new Question(currentVerbe);
				System.out.println("Question " + (i + 1) + ": Donnez le prétérit et le participe passé du verbe "
						+ currentVerbe.getBaseVerbale() + " :");
				String userInput = sc.nextLine();
				String[] parts = userInput.split(",\\s*"); // regex pour espaces après la virgule
				String userInputPreterit = "N/A";
				String userInputParticipePasse = "N/A";
				String finalText = "";
				if (parts.length != 2) {
					System.out.println("Suivez la consigne !");
				} else {
					userInputPreterit = parts[0];
					userInputParticipePasse = parts[1];
				}
				question.setReponseParticipePasse(userInputParticipePasse);
				question.setReponsePreterit(userInputPreterit);
				finalText = (partieService.getPartie().getNbQuestionsSouhaitees() - 1 < i + 1) ? " final" : " ";
				if (verbeService.compareResponse(currentVerbe, question.getReponsePreterit(),
						question.getReponseParticipePasse())) {

					System.out.println("Bravo ! Score" + finalText + " : " + (partieService.getPartie().getScore() + 1)
							+ "/" + (partieService.getPartie().getQuestions().size() + 1));
					partieService.getPartie().setScore(partieService.getPartie().getScore() + 1);
				} else {
					System.out.println("Ce n’est pas la bonne réponse. Score" + finalText + " : "
							+ partieService.getPartie().getScore() + "/"
							+ (partieService.getPartie().getQuestions().size() + 1));
				}

				question.setDateHeureReponse(LocalDateTime.now());
				partieService.getPartie().getQuestions().add(question);
			}

			System.out.println("Temps moyen de réponse : "
					+ calculateAverageTime(partieService.getPartie().getQuestions()).getSeconds() + " secondes.");

			System.out.println("Historique des verbes demandés : ");
			displayLogedQuestions();

		} while (askNew());
		s("Au revoir");

	}

	private static Duration calculateAverageTime(List<Question> questions) {
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

	private static void displayLogedQuestions() {
		partieService.getPartie().getQuestions().forEach(System.out::println);

	}

	private static boolean askNew() {
		System.out.print("Souhaitez-vous faire une nouvelle partie (O/N) ? ");
		String response = sc.nextLine().trim().toLowerCase();
		return response.equals("o");
	}

	private static void singletonize() {
		partieService.setServices(verbeService, questionService, sc);
		questionService.setServices(verbeService, partieService, sc);
		verbeService.setServices(partieService, questionService, sc);
	}

	static public void s(String string) {
		System.out.println(string);
	}

}
