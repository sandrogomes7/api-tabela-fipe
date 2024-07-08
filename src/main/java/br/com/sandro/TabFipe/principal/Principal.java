package br.com.sandro.TabFipe.principal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.sandro.TabFipe.model.DadosVeiculo;
import br.com.sandro.TabFipe.model.Modelos;
import br.com.sandro.TabFipe.model.Veiculo;
import br.com.sandro.TabFipe.service.ConsumoApi;
import br.com.sandro.TabFipe.service.ConverteDados;
import br.com.sandro.TabFipe.util.InputHandler;

public class Principal {
    private Scanner sc = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";

    public void exibeMenu() {
        var menu = """
                \n--- OPÇÕES ---
                1 - Carro
                2 - Moto
                3 - Caminhão
                """;

        String endereco;

        while(true) {
            System.out.print(menu);
            System.out.print("Escolha uma opção: ");
            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    endereco = URL_BASE + "carros/marcas";
                    break;
                case 2:
                    endereco = URL_BASE + "motos/marcas";
                    break;
                case 3:
                    endereco = URL_BASE + "caminhoes/marcas";
                    break;
                default:
                    System.out.println("Opção inválida!");
                    continue;
            }
            break;
        }
        
        var json = consumoApi.obterDados(endereco);
        
        var marcas = conversor.obterLista(json, DadosVeiculo.class);

        System.out.println("\n--- MARCAS ---");
        marcas.forEach(m -> System.out.printf("Marca: %s - Código: %s\n", m.nome(), m.codigo()));
        
        String codigoMarca = InputHandler.lerCodigoMarca(sc, marcas);
        endereco = endereco + "/" + codigoMarca + "/modelos";
        json = consumoApi.obterDados(endereco);

        var listaModelos = conversor.obterDados(json, Modelos.class);

        System.out.print("Digite um trecho do nome do modelo: ");
        var trecho = sc.nextLine();

        System.out.println("\n--- MODELOS ---");
        listaModelos.modelos().stream().filter(m -> m.nome().toLowerCase().contains(trecho.toLowerCase()))
                    .forEach(m -> System.out.printf("Modelo: %s - Código: %s\n", m.nome(), m.codigo()));
        
        System.out.print("\nDigite o código do modelo que você quer: ");

        String codigoModelo = InputHandler.lerCodigoModelo(sc, listaModelos.modelos());
        endereco = endereco + "/" + codigoModelo + "/anos";
        json = consumoApi.obterDados(endereco);

        List<DadosVeiculo> anos = conversor.obterLista(json, DadosVeiculo.class);

        List<Veiculo> veiculos = new ArrayList<>();

        for(var ano : anos) {
            var enderecoAno = endereco + "/" + ano.codigo();
            json = consumoApi.obterDados(enderecoAno);
            var veiculo = conversor.obterDados(json, Veiculo.class);
            veiculos.add(veiculo);
        }

        System.out.println("\n--- VEÍCULOS ---");
        veiculos.forEach(v -> System.out.printf("Modelo: %s - Ano: %d - Valor: %s - Combustível: %s - Mês de referência: %s\n",
                                                v.modelo(), v.ano(), v.valor(), v.tipoCombustivel(), v.mesReferencia()));

    }

}
