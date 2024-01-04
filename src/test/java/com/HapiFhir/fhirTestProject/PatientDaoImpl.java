package com.HapiFhir.fhirTestProject;

public class PatientDaoImpl {

	public PatientDetails getPatientDetails() {
		PatientDetails pd= new PatientDetails();		
		
		pd.setPatientFirstName("Sagar");
		pd.setPatientLastName("Patri");
		pd.setPatientDateOfBirth("19091997");
		pd.setPatientCity("Bengaluru");
		pd.setPatientInsuranceId("TestCivilID101");
		pd.setPatientStreetName("ITPL");
		pd.setPatientZipCode("532312");
		pd.setPatientStreetNumber("2-3-5");
		return pd;
	}
	
}
