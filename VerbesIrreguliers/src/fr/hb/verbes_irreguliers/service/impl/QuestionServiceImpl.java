package fr.hb.verbes_irreguliers.service.impl;

import java.util.List;
import java.util.Scanner;

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

	public int getCurrentId() {
		return currentId;
	}

	public void setCurrentId(int currentId) {
		this.currentId = currentId;
	}

}
