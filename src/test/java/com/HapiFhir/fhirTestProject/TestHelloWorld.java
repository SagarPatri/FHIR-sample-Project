package com.HapiFhir.fhirTestProject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Collectors;

import org.hl7.fhir.r4.model.Address.AddressType;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.CanonicalType;
import org.hl7.fhir.r4.model.CodeType;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Extension;
import org.hl7.fhir.r4.model.HumanName;
import org.hl7.fhir.r4.model.HumanName.NameUse;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.StringType;
import org.testng.annotations.Test;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;

public class TestHelloWorld {
	
	@Test
	public void test() {
		
	Bundle bundle = new Bundle();
	List <CanonicalType>  theProfile = Arrays.asList(new CanonicalType("https://dhamani.om/dhamani-fs/StructureDefinition/bundle|1.0.0"));
			bundle.getMeta().setProfile(theProfile);
			
			
			// Create patient resource.
	        Patient patientResource = createPatientResource();
	        
	       // Add Patient resource to the bundle 
	        bundle.addEntry()
            .setFullUrl("http://muscat-hospital.com/Patient/" +
                    patientResource.getId())
            .setResource(patientResource);
			
		IParser iParser  =	 FhirContext.forR4().newJsonParser();
		iParser.setPrettyPrint(true);
		System.out.println(iParser.encodeResourceToString(bundle));
	}

	private Patient createPatientResource() {
		
		  Patient patient = new Patient();
		  

	        patient.setId(UUID.randomUUID().toString());
	        patient.getMeta()
	                .addProfile("https://dhamani.om/dhamani-fs/StructureDefinition/patient|1.0.0");

	        Identifier identifier = patient.addIdentifier();
	        PatientDaoImpl pdao = new PatientDaoImpl();
	        PatientDetails pd= pdao.getPatientDetails();

	        Coding coding = identifier.getType()
	                .addCoding();
	        coding.setSystem("urn:iso:std:iso:3166");
	        coding.setCode("EGY");
	        identifier.getSystemElement().setValue("http://Dhamani.om/identifier/nationalid");
	        identifier.setValue(pd.getPatientInsuranceId());

	        List<StringType> prefixList = new ArrayList<StringType>();
	        
	      
	            StringType prefix = new StringType("B.Tech");
	            Extension extension = new Extension("http://hl7.org/fhir/StructureDefinition/iso21090-EN-qualifier", new CodeType("AC"));
	            prefix.addExtension(extension);
	            prefixList.add(prefix);
	   

	        HumanName humanName = patient.addName();

	        humanName
	                .setUse(NameUse.OFFICIAL)
	                .setPrefix(prefixList)
	                .addGiven(pd.getPatientFirstName()); 
	        
	        StringType familyElement = humanName.getFamilyElement();
	        List<String> nameParts = new ArrayList<>();

			/*
			 * Extension extension2 = new
			 * Extension("http://hl7.org/fhir/StructureDefinition/humanname-own-name", new
			 * StringType(pd.getPatientLastName())); familyElement.addExtension(extension2);
			 */   nameParts.add(pd.getPatientLastName());
	        
	        familyElement.setValue(nameParts.stream().collect(Collectors.joining(" ")));

	        String patientBirthDate = pd.getPatientDateOfBirth();

	        try {
	            patient.setBirthDate(new SimpleDateFormat("dd.MM.yy", Locale.GERMANY)
	                    .parse(patientBirthDate));
	        } catch (ParseException e) {
	            System.out.println("Could not parse this birthdate when creating the bundle:" + patientBirthDate);
	        }

	        patient.addAddress()
	                .setType(AddressType.BOTH)
	                .setCountry("Oman")
	                .setCity(pd.getPatientCity())
	                .setPostalCode(pd.getPatientZipCode())
	                .addLine(pd.getPatientStreetName() + " " +
	                        pd.getPatientStreetNumber());

	        StringType line = patient.getAddress().get(0).getLine().get(0);
	        line.addExtension("http://hl7.org/fhir/StructureDefinition/iso21090-ADXP-streetName", new StringType(pd.getPatientStreetName()));
	        line.addExtension("http://hl7.org/fhir/StructureDefinition/iso21090-ADXP-houseNumber", new StringType(pd.getPatientStreetNumber()));
	        return patient;
	  
	}

}
