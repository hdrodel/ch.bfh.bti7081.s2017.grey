package ch.bfh.bti7081.s2017.grey;

import javax.servlet.annotation.WebServlet;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.model.AppointmentModel;
import ch.bfh.bti7081.s2017.grey.presenter.AppointmentPresenter;
import ch.bfh.bti7081.s2017.grey.presenter.PatientTabsPresenter;
import ch.bfh.bti7081.s2017.grey.view.AppointmentViewImpl;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@SuppressWarnings("serial")
@Theme("mytheme")
@Widgetset("com.vaadin.v7.Vaadin7WidgetSet")
public class MyUI extends UI implements AppointmentPresenter.AppointmentPresenterListener  {
	@WebServlet(value = "/*", asyncSupported = true)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		new Navigator(this, this);

		AppointmentViewImpl appointmentView = new AppointmentViewImpl();
		AppointmentModel appointmentModel = new AppointmentModel();
		new AppointmentPresenter(appointmentView, appointmentModel).addListener(this);

		getNavigator().addView(LoginScreen.NAME, LoginScreen.class);
		getNavigator().addView(AppointmentViewImpl.NAME, appointmentView);
		getNavigator().addView(FinishAppointmentViewImpl.NAME, finishAppointmentView);
		getNavigator().addView(PatientTabsPresenter.NAME, PatientTabsPresenter.class);
		getNavigator().setErrorView(LoginScreen.class);

		router();
	}

	@Override
	public void appointmentSelected(Appointment appointment) {
		getNavigator().navigateTo(PatientTabsPresenter.NAME);
	}

	private void router(){
		if(VaadinSession.getCurrent().getAttribute("user") != null){
			getNavigator().navigateTo(AppointmentViewImpl.NAME);
		} else {
			getNavigator().navigateTo(LoginScreen.NAME);
		}
	}
	
	public static void loggedIn(){
		if(VaadinSession.getCurrent().getAttribute("user") == null){
			Page.getCurrent().setUriFragment("!"+LoginScreen.NAME);
		}
	}
}
