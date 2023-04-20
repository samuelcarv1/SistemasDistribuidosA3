package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    
    public static void main(String[] args) {
        
        String hostName = "localhost";
        int portNumber = 4444;

        try (
            Socket socket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(
                new InputStreamReader(System.in))
        ) {
            String userInput;
            String serverResponse;

            //Lê a mensagem de boas-vindas do servidor
            serverResponse = in.readLine();
            System.out.println(serverResponse);

            //Enquanto o usuário não digitar "quit", continua jogando
            while ((userInput = stdIn.readLine()) != null) {
                //Envia a mensagem do usuário para o servidor
                out.println(userInput);

                //Lê a resposta do servidor e imprime na tela
                serverResponse = in.readLine();
                System.out.println(serverResponse);

                //Se o jogo terminou, encerra a conexão com o servidor
                if (serverResponse.contains("O jogo acabou!")) {
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Não foi possível conectar ao servidor " + hostName + " na porta " + portNumber);
            System.exit(-1);
        }
    }
}
