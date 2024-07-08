package br.com.sandro.TabFipe;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.sandro.TabFipe.principal.Principal;

@SpringBootApplication
public class TabFipeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TabFipeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Principal principal = new Principal();
		Scanner sc = new Scanner(System.in);

		while (true) {
			int opcao;
			principal.exibeMenu();
			System.out.print("\nDeseja pesquisar novamente? 1 - Sim / 2 - Não: ");
			opcao = sc.nextInt();
			if (opcao == 2) {
				break;
			} else if (opcao != 1) {
				System.out.println("Opção inválida!");
			} else {
				continue;
			}
		}
		
		sc.close();
	}

}
