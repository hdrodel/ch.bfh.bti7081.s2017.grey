package ch.bfh.bti7081.s2017.grey.ui.appointment;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Nic on 15.05.17.
 */
public class AppointmentPresenter implements AppointmentView.AppointmentViewListener {

  public interface AppointmentPresenterListener {

    void appointmentSelected(Appointment appointment);
  }

  private List<AppointmentPresenterListener> listeners = new ArrayList<>();

  public void addListener(AppointmentPresenterListener listener) {
    listeners.add(listener);
  }

  private AppointmentModel appointmentModel;
  private AppointmentView appointmentView;

  public AppointmentPresenter(AppointmentView appointmentView, AppointmentModel appointmentModel) {
    this.appointmentModel = appointmentModel;
    this.appointmentView = appointmentView;

    this.appointmentModel.setNewAppointment();

    this.appointmentView.addListener(this);
    this.appointmentView.setPatients(this.appointmentModel.getPatients());
    this.appointmentView
        .setAppointment(this.appointmentModel.getAppointment(), this.appointmentModel.isEditMode());
  }

  /**
   * @see AppointmentView.AppointmentViewListener#startClick()
   */
  @Override
  public void startClick() {
    if (this.appointmentModel.isEditMode()) {
      for (AppointmentPresenterListener listener : listeners) {
        listener.appointmentSelected(this.appointmentModel.getAppointment());
      }
    }
  }

  /**
   * @see AppointmentView.AppointmentViewListener#newClick()
   */
  @Override
  public void newClick() {
    this.appointmentModel.setNewAppointment();
    this.appointmentView
        .setAppointment(this.appointmentModel.getAppointment(), this.appointmentModel.isEditMode());
  }

  /**
   * @see AppointmentView.AppointmentViewListener#saveClick()
   */
  @Override
  public void saveClick() {
    this.appointmentModel.saveAppointment();
    this.appointmentView.setAppointmentList(this.appointmentModel.getAppointmentList());
  }

  /**
   * @see AppointmentView.AppointmentViewListener#deleteClick()
   */
  @Override
  public void deleteClick() {
    this.appointmentModel.deleteAppointment();
    this.appointmentView
        .setAppointment(this.appointmentModel.getAppointment(), this.appointmentModel.isEditMode());
    this.appointmentView.setAppointmentList(this.appointmentModel.getAppointmentList());
  }

  /**
   * @see AppointmentView.AppointmentViewListener#appointmentMove(Date)
   */
  @Override
  public void appointmentMove(Date start) {
    this.appointmentModel.moveAppointment(start);
    this.appointmentView
        .setAppointment(this.appointmentModel.getAppointment(), this.appointmentModel.isEditMode());
  }

  /**
   * @see AppointmentView.AppointmentViewListener#appointmentReisize(Date, Date)
   */
  @Override
  public void appointmentReisize(Date start, Date end) {
    this.appointmentModel.resizeAppointment(start, end);
    this.appointmentView
        .setAppointment(this.appointmentModel.getAppointment(), this.appointmentModel.isEditMode());
  }

  /**
   * @see AppointmentView.AppointmentViewListener#monthViewSelect()
   */
  @Override
  public void monthViewSelect() {
    this.appointmentModel.setMonthRange();
    this.updateDate();
  }

  /**
   * @see AppointmentView.AppointmentViewListener#weekViewSelect()
   */
  @Override
  public void weekViewSelect() {
    this.appointmentModel.setWeekRange();
    this.updateDate();
  }

  /**
   * @see AppointmentView.AppointmentViewListener#dayViewSelect()
   */
  @Override
  public void dayViewSelect() {
    this.appointmentModel.setDayRange();
    this.updateDate();
  }

  /**
   * @see AppointmentView.AppointmentViewListener#dateSelect(Date)
   */
  @Override
  public void dateSelect(Date date) {
    this.appointmentModel.setDate(date);
    this.updateDate();
  }

  /**
   * @see AppointmentView.AppointmentViewListener#dateRangeSelect(Date, Date)
   */
  @Override
  public void dateRangeSelect(Date start, Date end) {
    this.appointmentModel.setStart(start);
    this.appointmentModel.setEnd(end);
    this.updateDate();
  }

  private void updateDate() {
    this.appointmentView.setStartDate(this.appointmentModel.getStart());
    this.appointmentView.setEndDate(this.appointmentModel.getEnd());
    this.appointmentView.setAppointmentList(this.appointmentModel.getAppointmentList());
  }

  /**
   * @see AppointmentView.AppointmentViewListener#appointmentSelect(Appointment)
   */
  @Override
  public void appointmentSelect(Appointment appointment) {
    this.appointmentModel.setAppointment(appointment);
    this.appointmentView.setAppointment(appointment, this.appointmentModel.isEditMode());
  }

  /**
   * @see AppointmentView.AppointmentViewListener#viewEntered(String)
   */
  @Override
  public void viewEntered(String username) {
    this.weekViewSelect();
    this.appointmentModel.setUser(username);
    this.appointmentModel.setNewAppointment();
    this.appointmentView
        .setAppointment(this.appointmentModel.getAppointment(), this.appointmentModel.isEditMode());
    this.appointmentView.setAppointmentList(this.appointmentModel.getAppointmentList());
  }
}
