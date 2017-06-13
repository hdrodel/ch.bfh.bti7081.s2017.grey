package ch.bfh.bti7081.s2017.grey.ui.patient;

import ch.bfh.bti7081.s2017.grey.database.entity.Drug;
import ch.bfh.bti7081.s2017.grey.database.entity.EmergencyContact;
import ch.bfh.bti7081.s2017.grey.database.entity.Patient;
import ch.bfh.bti7081.s2017.grey.database.entity.PatientDrugAssociation;
import ch.bfh.bti7081.s2017.grey.service.impl.EmergencyContactServiceImpl;
import ch.bfh.bti7081.s2017.grey.service.impl.PatientServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hannes on 5/17/17.
 */
public class PatientModel {
   PatientServiceImpl patientService = new PatientServiceImpl();
   Patient patient = new Patient();
   List emContact = new ArrayList <>();
   List drugList = new ArrayList<>();

   public void setPatient(Patient newpatient) {
      this.patient = newpatient;
   }

   public void addPatient(Patient newpatient) {
      patientService.createPatient(newpatient.getFirstname(), newpatient.getLastname());
   }
  public Patient getPatient(){
  return patient;
  }
  public void editPatient(Patient changedpatient, int patientId){
      this.patient = patient;
      patientService.updatePatient(patient);
  }

  public List<Drug> getDrugList(){
      return this.drugList;
  }

  public void setDrugList(List <PatientDrugAssociation> drugList){
      this.drugList=drugList;
  }

  public List<EmergencyContact> getEmContact(){
      return this.emContact;
  }

    public void setEmContact(List <EmergencyContact> emContact) {
        this.emContact = emContact;
    }
}
