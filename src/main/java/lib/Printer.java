package lib;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface Printer {

    static void printMessage(Object s) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss:SSS");
        System.out.println("[" + LocalDateTime.now().format(format) + "] - CURRENT THREAD: " + Thread.currentThread() + " => " + s);
    }
}