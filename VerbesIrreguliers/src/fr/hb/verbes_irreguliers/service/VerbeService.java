package fr.hb.verbes_irreguliers.service;

import java.util.List;
import java.util.Scanner;

import fr.hb.verbes_irreguliers.business.Verbe;

public interface VerbeService {

	List<Verbe> getVerbes();

	void setServices(PartieService partieService, QuestionService questionService, Scanner sc);

	void prepareRandomVerbes();

	Verbe getRandomVerbe(int i);

	boolean compareResponse(Verbe verbe, String userInputPreterit, String userInputParticipePasse);

}
