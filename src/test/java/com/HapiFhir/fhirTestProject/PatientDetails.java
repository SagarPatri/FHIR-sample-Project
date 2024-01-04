package com.HapiFhir.fhirTestProject;

public class PatientDetails {

	private String patientInsuranceId;
	private String patientFirstName;
	private String patientLastName;
	private String patientDateOfBirth;
	private String patientCity;
	private String patientZipCode;
	private String patientStreetName;
	private String patientStreetNumber;
	
	
	
	
	public String getPatientCity() {
		return patientCity;
	}

	public void setPatientCity(String patientCity) {
		this.patientCity = patientCity;
	}

	public String getPatientZipCode() {
		return patientZipCode;
	}

	public void setPatientZipCode(String patientZipCode) {
		this.patientZipCode = patientZipCode;
	}

	public String getPatientStreetName() {
		return patientStreetName;
	}

	public void setPatientStreetName(String patientStreetName) {
		this.patientStreetName = patientStreetName;
	}

	public String getPatientStreetNumber() {
		return patientStreetNumber;
	}

	public void setPatientStreetNumber(String patientStreetNumber) {
		this.patientStreetNumber = patientStreetNumber;
	}

	public String getPatientDateOfBirth() {
		return patientDateOfBirth;
	}

	public void setPatientDateOfBirth(String patientDateOfBirth) {
		this.patientDateOfBirth = patientDateOfBirth;
	}

	public String getPatientFirstName() {
		return patientFirstName;
	}

	public String getPatientLastName() {
		return patientLastName;
	}

	public void setPatientLastName(String patientLastName) {
		this.patientLastName = patientLastName;
	}

	public void setPatientFirstName(String patientFirstName) {
		this.patientFirstName = patientFirstName;
	}

	public String getPatientInsuranceId() {
		return patientInsuranceId;
	}

	public void setPatientInsuranceId(String patientInsuranceId) {
		this.patientInsuranceId = patientInsuranceId;
	}
	
	
	
}
