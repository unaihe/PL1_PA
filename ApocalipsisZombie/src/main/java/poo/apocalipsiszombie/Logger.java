/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.apocalipsiszombie;

/**
 *
 * @author unaih
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Logger {

    private final BlockingQueue<String> logQueue = new LinkedBlockingQueue<>();
    private final String logFile = "apocalipsis.txt";
    private volatile boolean running = true;
    private Thread writerThread;

    public Logger() {
        startWriterThread();
    }

    // Método que usarán los hilos para escribir mensajes
    public void escribir(String mensaje) {
        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        try {
            logQueue.put(timestamp + " - " + mensaje);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Hilo que consume la cola y escribe en el fichero
    private void startWriterThread() {
        writerThread = new Thread(() -> {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(logFile, true))) {
                while (running || !logQueue.isEmpty()) {
                    String logMsg = logQueue.take();
                    bw.write(logMsg);
                    bw.newLine();
                    bw.flush();
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        writerThread.setDaemon(true);
        writerThread.start();
    }

    // Método para cerrar el logger correctamente (opcional)
    public void cerrar() {
        running = false;
        writerThread.interrupt();
    }
}
