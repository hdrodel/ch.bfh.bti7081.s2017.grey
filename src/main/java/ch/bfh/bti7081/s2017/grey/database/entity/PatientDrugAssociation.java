package ch.bfh.bti7081.s2017.grey.database.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Quentin
 * @version 21.05.2017
 */
@Entity
@Table(name = "patient_drug")
@IdClass(PatientDrugAssociationId.class)
public class PatientDrugAssociation {

  @Id
  @Column(name = "patient_id")
  private long patientId;
  @Id
  @Column(name = "drug_id")
  private long drugId;
  private Timestamp created;
  private Timestamp changed;
  @ManyToOne
  @JoinColumn(name = "patient_id", updatable = false, insertable = false, referencedColumnName = "id")
  private Patient patient;
  @ManyToOne
  @JoinColumn(name = "drug_id", updatable = false, insertable = false, referencedColumnName = "id")
  private Drug drug;

  public long getPatientId() {
    return patientId;
  }

  public void setPatientId(long patientId) {
    this.patientId = patientId;
  }

  public long getDrugId() {
    return drugId;
  }

  public void setDrugId(long drugId) {
    this.drugId = drugId;
  }

  public Timestamp getCreated() {
    return created;
  }

  public void setCreated(Timestamp created) {
    this.created = created;
  }

  public Timestamp getChanged() {
    return changed;
  }

  public void setChanged(Timestamp changed) {
    this.changed = changed;
  }

  public Patient getPatient() {
    return patient;
  }

  public void setPatient(Patient patient) {
    this.patient = patient;
  }

  public Drug getDrug() {
    return drug;
  }

  public void setDrug(Drug drug) {
    this.drug = drug;
  }
}
