package fr.hb.verbes_irreguliers.business;

import java.util.List;

public class Partie {
	private static long compteur;
	
	private long id;
	private int nbQuestionsSouhaitees;
	private int score;
	
	private List<Question> questions;

	public Partie() {
		super();
		this.id = compteur++;
		// TODO Auto-generated constructor stub
	}
	public Partie(List<Question> questions) {
		this();
		this.questions = questions;
	}
	public Partie(List<Question> questions, int score) {
		this(questions);
		this.score = score;
	}
	public Partie(List<Question> questions, int score, int nbQuestionsSouhaitees) {
		this(questions);
		this.nbQuestionsSouhaitees = nbQuestionsSouhaitees;
	}
	
	public int getNbQuestionsSouhaitees() {
		return nbQuestionsSouhaitees;
	}
	public void setNbQuestionsSouhaitees(int nbQuestionsSouhaitees) {
		this.nbQuestionsSouhaitees = nbQuestionsSouhaitees;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public List<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	public long getId() {
		return id;
	}
	@Override
	public String toString() {
		return "Partie [Nombre =" + getNbQuestionsSouhaitees() + ", Score()=" + getScore()
				+ ", Question =" + getQuestions() + ",  Id =" + getId() + "]";
	}
}
