package ch.bfh.bti7081.s2017.grey;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.Task;
import ch.bfh.bti7081.s2017.grey.database.util.EntityManagerSingleton;
import ch.bfh.bti7081.s2017.grey.database.entity.Patient;
import ch.bfh.bti7081.s2017.grey.database.entity.PatientDrugAssociation;
import ch.bfh.bti7081.s2017.grey.service.PatientService;
import ch.bfh.bti7081.s2017.grey.service.impl.PatientServiceImpl;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class PatientTabsPresenter extends HorizontalLayout implements View {
	private static final long serialVersionUID = 1L;
	public static final String NAME = "PatientTabs";
	
	private PatientTabs patientTab = new PatientTabs();
	
	public PatientTabsPresenter(){
		PatientService patientService = new PatientServiceImpl();
		// TODO fetch patient dynamicly
		Patient patient = patientService.getPatientById(4);
		patientTab.setSizeFull();
		
		Label clientTempLabel = new Label("Personeninformationen sind hier", ContentMode.HTML);
		VerticalLayout client = new VerticalLayout(clientTempLabel);
		patientTab.addTab(client, "Client");


		DrugListView drugs = new DrugListView();
		for (PatientDrugAssociation patientDrugAssociation: patient.getDrugs()) {
			drugs.addDrug(patientDrugAssociation.getDrug());
		}
		patientTab.addTab(drugs, "Drugs");

		

		TaskListView toDo = new TaskListView();
		// TODO load selected appointment
		Appointment appointment = EntityManagerSingleton.getInstance().find(Appointment.class, (long) 1);
		toDo.setSizeFull();
		for (Task task: appointment.getTasks()) {
            toDo.addTask(task);
        }
		toDo.addNewTaskButton();
		patientTab.addTab(toDo, "ToDo");
				
		Design design = new Design();
		addComponent(design.insertContent(patientTab));
	}

	@Override
	public void enter(ViewChangeEvent event) {
		MyUI.loggedIn();
	}
}
