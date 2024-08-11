package fr.hb.verbes_irreguliers.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import fr.hb.verbes_irreguliers.business.Verbe;
import fr.hb.verbes_irreguliers.service.PartieService;
import fr.hb.verbes_irreguliers.service.QuestionService;
import fr.hb.verbes_irreguliers.service.VerbeService;

public class VerbeServiceImpl implements VerbeService {
	private QuestionService questionService;
	private PartieService partieService;
	private VerbeService verbeService;
	private Scanner sc;
	final String PATH = "verbes.csv";
	List<Verbe> verbes;
	List<Verbe> randomVerbes;

	public VerbeServiceImpl() {
		super();
		this.verbes = createVerbes();
	}

	private List<Verbe> createVerbes() {

		return loadCSV(PATH);
	}

	public List<Verbe> loadCSV(String path) {
		List<Verbe> verbs = new ArrayList<>();
		String csvSplitBy = ",";

		try (Scanner scanner = new Scanner(new File(path))) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] values = line.split(csvSplitBy);
				if (values.length >= 5) {
					String baseVerbale = values[1];
					String participePasse = values[2];
					String preterit = values[3];
					String traduction = values[4];
					Verbe verbe = new Verbe(baseVerbale, participePasse, preterit, traduction);
					verbs.add(verbe);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return verbs;
	}

	@Override
	public boolean compareResponse(Verbe verbe, String userInputPreterit, String userInputParticipePasse) {

		boolean correctPreterit = verbe.getPreterit().equals("\"" + userInputPreterit + "\"");
		boolean correctParticipePasse = verbe.getParticipePasse().equals("\"" + userInputParticipePasse + "\"");
		return correctPreterit && correctParticipePasse;

	}

	public List<Verbe> getRandomVerbes() {
		return randomVerbes;
	}

	public void setRandomVerbes(List<Verbe> randomVerbes) {
		this.randomVerbes = randomVerbes;
	}

	@Override
	public List<Verbe> getVerbes() {

		return verbes;
	}

	@Override
	public void setServices(PartieService partieService, QuestionService questionService, Scanner sc) {
		this.partieService = partieService;
		this.questionService = questionService;
		this.sc = sc;
	}

	@Override
	public Verbe getRandomVerbe(int i) {
		return getRandomVerbes().get(i);
	}

	public List<Verbe> getRandomVerbes(int qty) {

		List<Verbe> shuffledVerbes = getVerbes();
		Collections.shuffle(shuffledVerbes);

		return shuffledVerbes.stream().limit(qty).collect(Collectors.toList());

	}

	@Override
	public void prepareRandomVerbes() {
		setRandomVerbes(getRandomVerbes(partieService.getPartie().getNbQuestionsSouhaitees()));

	}

}
