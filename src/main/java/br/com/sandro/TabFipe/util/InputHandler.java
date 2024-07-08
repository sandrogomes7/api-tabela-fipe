package br.com.sandro.TabFipe.util;

import java.util.List;
import java.util.Scanner;

import br.com.sandro.TabFipe.model.DadosVeiculo;

public class InputHandler {

    public static String lerCodigoMarca(Scanner sc, List<DadosVeiculo> marcas) {
        String codigoMarca;
        do {
            System.out.print("\nDigite o código da marca: ");
            String inputMarca = sc.nextLine();
            if (!(marcas.stream().anyMatch(m -> m.codigo().contains(inputMarca)))) {
                System.out.print("Código inválido! Digite novamente: ");
            } else {
                codigoMarca = inputMarca;
                break;
            }
        } while (true);
        return codigoMarca;
    }

    public static String lerCodigoModelo(Scanner sc, List<DadosVeiculo> modelos) {
        String codigoModelo;
        do {
            System.out.print("\nDigite o código do modelo: ");
            String inputModelo = sc.nextLine();
            if (!(modelos.stream().anyMatch(m -> m.codigo().contains(inputModelo)))) {
                System.out.print("Código inválido! Digite novamente: ");
            } else {
                codigoModelo = inputModelo;
                break;
            }
        } while (true);
        return codigoModelo;
    }
}
