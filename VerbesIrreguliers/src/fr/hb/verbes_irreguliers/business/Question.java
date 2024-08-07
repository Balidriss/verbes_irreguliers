package fr.hb.verbes_irreguliers.business;

import java.time.LocalDateTime;

public class Question {

	private static long compteur;

	private long id;

	private String reponsePreterit;
	private String reponseParticipePasse;
	private LocalDateTime dateHeureEnvoi;
	private LocalDateTime dateHeureReponse;

	private Verbe verbe;

	public Question() {
		super();
		this.id = compteur++;
		this.dateHeureEnvoi = LocalDateTime.now();
	}

	public Question(Verbe verbe) {
		this();
		this.verbe = verbe;
	}

	public String getReponsePreterit() {
		return reponsePreterit;
	}

	public void setReponsePreterit(String reponsePreterit) {
		this.reponsePreterit = reponsePreterit;
	}

	public String getReponseParticipePasse() {
		return reponseParticipePasse;
	}

	public void setReponseParticipePasse(String reponseParticipePasse) {
		this.reponseParticipePasse = reponseParticipePasse;
	}

	public LocalDateTime getDateHeureEnvoi() {
		return dateHeureEnvoi;
	}

	public void setDateHeureEnvoi(LocalDateTime dateHeureEnvoi) {
		this.dateHeureEnvoi = dateHeureEnvoi;
	}

	public LocalDateTime getDateHeureReponse() {
		return dateHeureReponse;
	}

	public void setDateHeureReponse(LocalDateTime dateHeureReponse) {
		this.dateHeureReponse = dateHeureReponse;
	}

	public long getId() {
		return id;
	}

	public Verbe getVerbe() {
		return verbe;
	}

	public void setVerbe(Verbe verbe) {
		this.verbe = verbe;
	}

	@Override
	public String toString() {
		return "- Base : " + getVerbe().getBaseVerbale() + "  Preterit : " + getVerbe().getPreterit()
				+ " Participe Passe : " + getVerbe().getParticipePasse();
	}

}
