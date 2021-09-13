package view;

import java.util.concurrent.Semaphore;

import controller.ThreadPessoa;

public class Porta {
	
	public static void main(String[] args) {
		
		int permissao = 1;
		Semaphore mutex = new Semaphore(permissao);
		
		for (int idPessoa = 1; idPessoa < 5 ; idPessoa++) {
			ThreadPessoa tPessoa = new ThreadPessoa(idPessoa, mutex);
			tPessoa.start();
		}
	}

}
