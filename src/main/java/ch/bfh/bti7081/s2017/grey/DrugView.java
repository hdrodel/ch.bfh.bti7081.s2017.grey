package ch.bfh.bti7081.s2017.grey;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class DrugView extends VerticalLayout {
	private Label name = null;

	public DrugView(){
		super();
		name = new Label("Drug Name");
		addComponent(name);
	}

	public void setName(String labelName) {
		name.setValue(labelName);
	}
}
