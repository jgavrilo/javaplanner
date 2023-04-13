package src.main.java.schedule_maker;
/**
 * The Picker class contains a method for executing different operations
 * based on the user's selection. These operations include adding, dropping,
 * editing classes, printing schedules, managing appointments, and quitting the program.
 */
import java.io.*;

import src.main.java.schedule_maker.model.Appointment;
import src.main.java.schedule_maker.model.Classes;
public class Picker {

    /**
     * Executes the operation based on the user's selection.
     *
     * @param selection The character representing the user's choice.
     * @param f         The file object.
     * @param count     The number of classes or appointments.
     * @param name      The user's name.
     * @param year      The year of the schedule.
     * @param sem       The semester of the schedule.
     * @throws IOException If an input or output exception occurs.
     */
    public static void pick(char selection, File f, int count, String name, String year, String sem) throws IOException {
        Appointment[] app;
        Classes[] classes = Classes.readClasses(f);
        count = Classes.counterClass(f);

        switch (selection) {
            case 'a':
            case 'A':
                Classes.addAClass(classes, count);
                Classes.updateClasses(classes, count, name, sem, year);
                break;

            case 'b':
            case 'B':
                classes = Classes.dropAClass(f, count, classes);
                Classes.updateClasses(classes, --count, name, sem, year);
                break;

            case 'c':
            case 'C':
                classes = Classes.editClass(classes, count);
                Classes.updateClasses(classes, count, name, sem, year);
                break;

            case 'd':
            case 'D':
                Printers.printSchedule(classes, count);
                break;

            case 'e': //Fix bugs in make appointment
            case 'E':
                f = new File(name + "Appointments.txt");
                f.createNewFile();
                count = Appointment.counterApp(f);
                app = Appointment.makeAppointment(Appointment.appointments(name, count), count);
                Printers.appPrint(app, count, f);
                break;

            case 'f':
            case 'F':
                f = new File(name + "Appointments.txt");
                if (!(f.exists())) {
                    System.out.println("It looks like you dont have any appointments, please make one to view them");
                } else {
                    count = Appointment.counterApp(f);
                    app = Appointment.appointments(name, count);
                    Printers.viewApps(app, count, f);
                }
                break;

            case 'g':
            case 'G':
                Quitters.quit();
                break;
        }//End of switch
    }//End of pick

}
