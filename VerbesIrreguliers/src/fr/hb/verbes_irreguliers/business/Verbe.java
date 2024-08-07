package fr.hb.verbes_irreguliers.business;

public class Verbe {

	private static long compteur;
	
	private long id;
	private String baseVerbale;
	private String participePasse;
	private String preterit;
	private String traduction;
	
	public Verbe() {
		super();
		this.id = compteur++;
	}
	
	public Verbe(String baseVerbale,String participePasse, String preterit, String traduction) {
		this();
		this.baseVerbale = baseVerbale;
		this.preterit = preterit;
		this.participePasse = participePasse;
		this.traduction = traduction;
	}

	public String getBaseVerbale() {
		return baseVerbale;
	}

	public void setBaseVerbale(String baseVerbale) {
		this.baseVerbale = baseVerbale;
	}

	public String getPreterit() {
		return preterit;
	}

	public void setPreterit(String preterit) {
		this.preterit = preterit;
	}

	public String getParticipePasse() {
		return participePasse;
	}

	public void setParticipePasse(String participePasse) {
		this.participePasse = participePasse;
	}

	public String getTraduction() {
		return traduction;
	}

	public void setTraduction(String traduction) {
		this.traduction = traduction;
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Verbe [id=" + id + ", baseVerbale=" + baseVerbale + ", participePasse=" + participePasse + ", preterit="
				+ preterit + ", traduction=" + traduction + "]";
	}
	
	
}
