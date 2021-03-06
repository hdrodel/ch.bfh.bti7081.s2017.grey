package ch.bfh.bti7081.s2017.grey.ui.finish_appointment;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;

/**
 * Created by gabor on 07/06/17.
 */
public interface FinishAppointmentView {

  interface FinishAppointmentViewListener {

    void addDelayClick(int time);

    void subtractDelayClick(int time);

    void finishClick();

    void viewEntered();
  }

  void addListener(FinishAppointmentViewListener listener);

  void setAppointment(Appointment appointment);

  void setDelay(int delay);
}
