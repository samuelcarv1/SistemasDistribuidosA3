package server;

import java.util.Random;

public class JogoAdivinhacao {
    
    private int numeroAleatorio;
    private int tentativas;
    private boolean acabou;

    public JogoAdivinhacao() {
        Random random = new Random();
        this.numeroAleatorio = random.nextInt(100) + 1;
        this.tentativas = 0;
        this.acabou = false;
    }

    public String iniciarJogo(String tentativa) {
        if (tentativa == null) {
            return "Tentativa #" + (++tentativas) + ". Tente adivinhar um número entre 1 e 100.";
        }

        if (acabou) {
            return "O jogo já acabou. Reinicie o servidor para jogar novamente.";
        }

        int palpite;
        try {
            palpite = Integer.parseInt(tentativa);
        } catch (NumberFormatException e) {
            return "Entrada inválida. Por favor, digite um número entre 1 e 100.";
        }

        if (palpite < 1 || palpite > 100) {
            return "Entrada inválida. Por favor, digite um número entre 1 e 100.";
        }

        if (palpite < numeroAleatorio) {
            tentativas++;
            return "Tentativa #" + tentativas + ". Seu palpite de " + palpite + " é muito baixo. Tente novamente.";
        } else if (palpite > numeroAleatorio) {
            tentativas++;
            return "Tentativa #" + tentativas + ". Seu palpite de " + palpite + " é muito alto. Tente novamente.";
        } else {
            acabou = true;
            return "Parabéns! Você acertou em " + tentativas + " tentativas. Digite 'quit' para sair.";
        }
    }
}
