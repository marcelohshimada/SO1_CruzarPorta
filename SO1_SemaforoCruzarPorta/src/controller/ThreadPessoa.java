package controller;

import java.util.concurrent.Semaphore;

public class ThreadPessoa extends Thread {

	private int idPessoa;
	private static int posicaoChegada;
	private Semaphore mutex;

	public ThreadPessoa(int idPessoa, Semaphore mutex) {
		this.idPessoa = idPessoa;
		this.mutex = mutex;
	}

	@Override
	public void run() {
		pessoaAndando(); // Paralelizável (sem restrições)

		// INÍCIO DA SEÇÃO CRÍTICA
		try {
			mutex.acquire();
			cruzandoPorta(); // Apenas 1 pode cruzar por vez
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally { // Esse bloco roda independente do try ou catch
			mutex.release(); // Libera a porta para a próxima pessoa
		}

		// FIM DA SEÇÃO CRÍTICA

	}

	private void pessoaAndando() {
		int distanciaFinal = 200;
		int distanciaPercorrida = 0;
		int tempo = 500;

		while (distanciaPercorrida < distanciaFinal) {
			int deslocamento = (int) ((Math.random() * 3) + 4);
			distanciaPercorrida += deslocamento;

			try {
				sleep(tempo); // Simulação do movimento (andar)
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("A pessoa " + idPessoa + " andou " + deslocamento + "m" + " e já percorreu "
					+ distanciaPercorrida + "m");
		}
		posicaoChegada++;
		System.err.println("A pessoa " + idPessoa + " foi a " + posicaoChegada + "ª" + " a chegar a porta");
		System.err.println("A pessoa " + idPessoa + " está cruzando a porta");
	}

	private void cruzandoPorta() {
		System.err.println("A pessoa " + idPessoa + " cruzou a porta! PORTA LIBERADA");
		// Tempo para cruzar a porta 1 a 2 segundos
		int tempoCruzar = (int) ((Math.random() * 1001) + 1000);

		try {
			sleep(tempoCruzar);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
