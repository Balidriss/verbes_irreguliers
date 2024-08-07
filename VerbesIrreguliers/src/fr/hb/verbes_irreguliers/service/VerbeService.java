package fr.hb.verbes_irreguliers.service;

import java.util.List;
import java.util.Scanner;

import fr.hb.verbes_irreguliers.business.Verbe;

public interface VerbeService {

	public List<Verbe> getVerbes();

	public void setServices(PartieService partieService, QuestionService questionService, Scanner sc);

	public Verbe getId(int id);
}
