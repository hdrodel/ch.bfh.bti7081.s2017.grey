package ch.bfh.bti7081.s2017.grey.database.dao.impl;

import ch.bfh.bti7081.s2017.grey.database.dao.GenericDao;
import ch.bfh.bti7081.s2017.grey.database.entity.*;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

/**
 * Created by gabor on 29/05/17.
 */
public class PatientDao extends GenericDaoImpl<Patient> implements GenericDao<Patient> {

    public Patient getPatientByName(String firstName, String lastName) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Patient> criteriaQuery = criteriaBuilder.createQuery(Patient.class);
        Root<Patient> patient = criteriaQuery.from(Patient.class);
        criteriaQuery.select(patient).where(criteriaBuilder.equal(patient.get("firstname"), firstName),
                criteriaBuilder.equal(patient.get("lastname"), lastName));

        TypedQuery<Patient> query = em.createQuery(criteriaQuery);
        Patient result = query.getSingleResult();
        return result;
    }


    public void addDrugsToPatient(Patient patient, List<Drug> drugs) {
        em.getTransaction().begin();
        for (Drug drug : drugs) {
            PatientDrugAssociation association = new PatientDrugAssociation();
            association.setPatient(patient);
            association.setDrug(drug);
            association.setPatientId(patient.getId());
            association.setDrugId(drug.getId());
            Instant instant = Instant.now();
            association.setCreated(new Timestamp(instant.toEpochMilli()));
            association.setChanged(new Timestamp(instant.toEpochMilli()));
            em.persist(association);
            patient.getDrugs().add(association);
            drug.getPatients().add(association);
        }
        em.getTransaction().commit();
    }

    public void addHabitsToPatient(Patient patient, List<Habit> habits) {
        em.getTransaction().begin();
        for (Habit habit : habits) {
            PatientHabitAssociation association = new PatientHabitAssociation();
            association.setPatient(patient);
            association.setHabit(habit);
            association.setPatientId(patient.getId());
            association.setHabitId(habit.getId());
            Instant instant = Instant.now();
            association.setCreated(new Timestamp(instant.toEpochMilli()));
            association.setChanged(new Timestamp(instant.toEpochMilli()));
            em.persist(association);
            patient.getHabits().add(association);
            habit.getPatients().add(association);
        }
        em.getTransaction().commit();
    }
}
