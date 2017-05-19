package ch.bfh.bti7081.s2017.grey.service.impl;

import ch.bfh.bti7081.s2017.grey.database.entity.Appointment;
import ch.bfh.bti7081.s2017.grey.database.entity.AppointmentStatus;
import ch.bfh.bti7081.s2017.grey.service.AppointmentStatusOperations;

/**
 * Created by gabor on 17/05/17.
 */
public class DelayedAso implements AppointmentStatusOperations {
    @Override
    public AppointmentStatus create(Appointment appointment) throws IllegalStateException {
        throw new IllegalStateException("Cant use operation create with status " + appointment.getStatus().toString());
    }

    @Override
    public AppointmentStatus delay(Appointment appointment) throws IllegalStateException {
        throw new IllegalStateException("Cant use operation delay with status " + appointment.getStatus().toString());
    }

    @Override
    public AppointmentStatus cancel(Appointment appointment) {
        return null;
    }

    @Override
    public AppointmentStatus finish(Appointment appointment) {
        return null;
    }
}
