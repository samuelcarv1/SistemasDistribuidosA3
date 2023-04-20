package server;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {

    public static void main(String[] args) throws IOException {
        
        ServerSocket serverSocket = null;
        boolean listening = true;

        try {
            serverSocket = new ServerSocket(4444);
        } catch (IOException e) {
            System.err.println("Não foi possível abrir a porta 4444.");
            System.exit(-1);
        }

        System.out.println("Servidor iniciado na porta 4444.");

        while (listening) {
            new ServerThread(serverSocket.accept()).start();;
        }

        serverSocket.close();

    }
}