package xyz.nimeide.bio;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author hoshaoniurou
 */
public class TimeServerHandler implements Runnable {

    private final Socket socket;

    public TimeServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            String body;
            while ((body = in.readLine()) != null && body.length() != 0) {
                System.out.println("time server msg : " + body);
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:ss:mm");
                out.println(LocalDateTime.now().format(dateTimeFormatter));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            if (out != null) {
                out.close();
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
