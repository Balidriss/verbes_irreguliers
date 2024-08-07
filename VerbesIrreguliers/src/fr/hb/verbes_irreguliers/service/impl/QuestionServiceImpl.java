package fr.hb.verbes_irreguliers.service.impl;

import java.util.List;

import fr.hb.verbes_irreguliers.business.Question;
import fr.hb.verbes_irreguliers.service.QuestionService;

public class QuestionServiceImpl implements QuestionService {

	List<Question> questions;
	
	public QuestionServiceImpl() {
		this.questions = createQuestions();
	}


	public List<Question> createQuestions() {
	
		return null;
	}


	@Override
	public List<Question> getQuestions() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
