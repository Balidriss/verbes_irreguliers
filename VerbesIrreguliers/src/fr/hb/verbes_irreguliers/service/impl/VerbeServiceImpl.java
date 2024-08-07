package fr.hb.verbes_irreguliers.service.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.hb.verbes_irreguliers.business.Verbe;
import fr.hb.verbes_irreguliers.service.VerbeService;

public class VerbeServiceImpl implements VerbeService {

	final String PATH = "src/resource/verbes.csv";
	List<Verbe> verbes = createVerbes();
	
	public VerbeServiceImpl() {
		super();
		this.verbes = createVerbes();
	}

	
	
	

	private List<Verbe> createVerbes() {
		
		return loadCSV(PATH);
	}
	
	public List<Verbe> loadCSV(String path) {
        List<Verbe> verbs = new ArrayList<>();
        String line;
        String csvSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while ((line = br.readLine()) != null) {
                String[] values = line.split(csvSplitBy);
                    String baseVerbale = values[1];
                    String participePasse = values[2];
                    String preterit = values[3];
                    String traduction = values[4];
                    Verbe verbe = new Verbe(baseVerbale, participePasse, preterit, traduction);
                    verbs.add(verbe);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return verbs;
    }


	public void displayVerbes(List<Verbe> verbes) {
		verbes.forEach(System.out::println);
		
	}

	@Override
	public List<Verbe> getVerbes() {
		return verbes;
	}
	
	
}
