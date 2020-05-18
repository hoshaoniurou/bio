package xyz.nimeide.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author hoshaoniurou
 */
public class BioServer {

    private static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        ServerSocket socket = null;
        try {
            socket = new ServerSocket(PORT);
            System.out.println("服务启动");
            Socket accept;
            while (true) {
                accept = socket.accept();
                new Thread(new TimeServerHandler(accept)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (socket!=null){
                System.out.println("服务关闭");
                socket.close();
            }
        }
    }
}
