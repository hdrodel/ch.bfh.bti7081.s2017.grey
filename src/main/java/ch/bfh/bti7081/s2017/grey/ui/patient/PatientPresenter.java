package ch.bfh.bti7081.s2017.grey.ui.patient;

import ch.bfh.bti7081.s2017.grey.database.entity.Patient;
import ch.bfh.bti7081.s2017.grey.database.util.EntityManagerSingleton;
import ch.bfh.bti7081.s2017.grey.service.impl.PatientServiceImpl;
import javax.persistence.EntityManager;

/**
 * Created by hannes on 5/17/17.
 */
public class PatientPresenter implements PatientView.PatientViewListener {

  private PatientModel patientModel;
  private PatientView patientView;
  private EntityManager em = EntityManagerSingleton.getInstance();
  private PatientServiceImpl patientService = new PatientServiceImpl(em);

  public PatientPresenter(PatientViewImpl patientView, PatientModel patientModel) {
    this.patientModel = patientModel;
    this.patientView = patientView;
    this.patientView.addListener(this);
  }

  @Override
  public void editClick() {
  }

  @Override
  public void saveClick() {

  }

  public void cancelClick() {

  }

  @Override
  public void setPatient(Patient patient) {
    this.patientModel.setPatient(patient);
    this.patientView.setPatient(this.patientModel.getPatient());
  }

  public PatientView getView() {
    return patientView;
  }
}