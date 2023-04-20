package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread {
    
    private Socket socket = null;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(
                    socket.getInputStream()));
        ) {
            String inputLine, outputLine;

            //Envia uma mensagem de boas-vindas ao cliente
            out.println("Bem-vindo ao servidor. Digite 'quit' para sair.");
            out.flush();

            //Inicia a lógica de comunicação do servidor com o cliente
            JogoAdivinhacao jogo = new JogoAdivinhacao();
            outputLine = jogo.iniciarJogo(null);
            out.println(outputLine);
            out.flush();

            while ((inputLine = in.readLine()) != null) {
                if ("quit".equals(inputLine)) {
                    break;
                }
                outputLine = jogo.iniciarJogo(inputLine);
                out.println(outputLine);
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}


