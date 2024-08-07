package fr.hb.verbes_irreguliers.service;

import java.util.Scanner;

public interface QuestionService {

	public void nextQuestion();

	public void setServices(VerbeService verbeService, PartieService partieService, Scanner sc);

	public void prepareRandomIds();

}
