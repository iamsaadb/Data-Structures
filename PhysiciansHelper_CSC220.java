package pj4;
import java.util.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.io.File;
import java.io.File;
import java.io.FileNotFoundException;




public class PhysiciansHelper
{
	private Map<String, List<String>> symptomChecker;
        Scanner type = new Scanner(System.in);
        
	public PhysiciansHelper()
	{ 
		symptomChecker = new TreeMap<String,List<String>>();
	} 

        
	public void processDatafile()
	{
            System.out.println("Enter your text filename without format (for example for t1.txt, enter t1) :");
            String fileName = type.nextLine();
            Scanner symptomsFile;
            System.out.println("___________________//PART 1\\_______________________");
            try {
                symptomsFile = new Scanner(new File(fileName+".txt"));
                    while (symptomsFile.hasNextLine()) {
                    String line = symptomsFile.nextLine();
                    String[] values = line.trim().toLowerCase().split(":");
                    String disease = values[0].trim();
                    ArrayList<String> symptoms = new ArrayList<String>();
                    symptoms.addAll(Arrays.asList(values[1].trim().toLowerCase().split(",")));
                        for (String symptom : symptoms) {
                            if (symptomChecker.containsKey(symptom.trim())) {
                            symptomChecker.get(symptom.trim()).add(disease);
                            } 
                            else {
                            ArrayList<String> diseases = new ArrayList<String>();
                            diseases.add(disease);
                            symptomChecker.put(symptom.trim(), diseases);
                                 }
                        }
                    }
                }
                catch (FileNotFoundException e) {
                e.printStackTrace();
                }
                System.out.println("From your file, we processed the data below: ");
                for (Entry<String, List<String>> entry : symptomChecker.entrySet()) {
                    String key = entry.getKey().toString();
                    List<String> values = entry.getValue();
                    System.out.println(key + " = " + values);

                }


	} 

	public void processSymptoms()
	{
            System.out.println("****************************************************");
            System.out.println("Possible symptoms :");
            for (Entry<String, List<String>> entry : symptomChecker.entrySet()) {
                String key = entry.getKey().toString();
                System.out.println("-->    " + key);
            }
            System.out.println("___________________//PART 2\\_______________________");
            System.out.println("Enter symptoms: ");
            Set<String> pSymptoms = symptomChecker.keySet();
            ArrayList<String> vSymptoms = new ArrayList<String>();
            String[] symptomsEntered = type.nextLine().trim().toLowerCase().split(",");
            for (String symptom : symptomsEntered) {
            if (vSymptoms.contains(symptom)) {
            System.out.println("Duplicate: " + symptom);
            } else if (!pSymptoms.contains(symptom)) {
            System.out.println("Invalid: " + symptom);
            } else {
            vSymptoms.add(symptom);
            }
            }
            System.out.println("======================================================");
            System.out.println("Patient Symptoms list: " + vSymptoms);
            System.out.println("Possible diseases that match symptoms are:");
            List<String> diseases = new ArrayList<String>();
            for (Entry<String, List<String>> entry : symptomChecker.entrySet()) {
            String key = entry.getKey().toString();
            List<String> values = entry.getValue();
            for (String symptom : vSymptoms) {
            if (key.equals(symptom)) {
            diseases.addAll(values);
            }
            }
            }

            Map<Integer, String> dFreq =new TreeMap<Integer, String>();
            for (String symptom : diseases) {
            int freq = Collections.frequency(diseases, symptom);
            dFreq.put(freq, symptom);
            }
            for (Entry<Integer, String> entry : dFreq.entrySet()) {
            int key = entry.getKey();
            String value = entry.getValue();
            System.out.print(" **** Disease(s) match "+key+" symptom(s) :  ");
            System.out.println(value);
            }
            System.out.println("***** BYE, Thank you *******");
            type.close();

            } 

	public static void main(String[] args)
	{

		PhysiciansHelper lookup = new PhysiciansHelper();
		lookup.processDatafile();
		lookup.processSymptoms();
	} 
} 
