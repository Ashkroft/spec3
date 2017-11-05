package util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;


public class Util {
    public static void printRelease(Date date, int resource, FileWriter log) {
        try {
            log.write(date.toString() + " " + Integer.valueOf(resource).toString() + " Release\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printGetSuccessful(Date date, int resource, FileWriter log) {
        try {
            log.write(date.toString() + " " + Integer.valueOf(resource).toString() + " Get successful\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void printGetFailed(Date date, int resource, FileWriter log) {
        try {
            log.write(date.toString() + " " + Integer.valueOf(resource).toString()+ " Get Failed\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
