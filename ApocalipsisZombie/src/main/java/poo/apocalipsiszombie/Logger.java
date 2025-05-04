/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.apocalipsiszombie;


/**
 * Logger concurrente para la simulación del apocalipsis zombi.
 * Esta clase permite que múltiples hilos escriban mensajes de log de forma segura y eficiente.
 * Los mensajes se almacenan en una cola y un hilo dedicado los escribe en un fichero de texto.
 * El archivo de log por defecto es "apocalipsis.txt".
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
    /**
     * Crea un nuevo logger y arranca el hilo de escritura en segundo plano.
     */
    public Logger() {
        startWriterThread();
    }

    /**
     * Añade un mensaje a la cola de log para ser escrito en el archivo.
     * El mensaje se marca automáticamente con la fecha y hora actual.
     * @param mensaje Mensaje a registrar en el log.
     */
    public void escribir(String mensaje) {
        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        try {
            logQueue.put(timestamp + " - " + mensaje);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Arranca el hilo que consume la cola de mensajes y los escribe en el archivo de log.
     * Este método se llama automáticamente en el constructor.
     */
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

}
