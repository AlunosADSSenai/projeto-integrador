package br.com.senai;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Principal {

	static int poltronasInicial[] = { 25, 100, 30, 50, 50 };
	
	static  double valoresIngresso[] = { 40.00, 60.00, 80.00, 120.00, 250.00 };
	
	static String areasTeatro[] = { "Plateia A", "Plateia B", "Frisa", "Camarote", "Balcão Nobre" };
	
	static String infoEspetaculos[][] = { { "A Pequena Sereia",  "Categoria: INFANTIL",  "Sessões: MANHÃ / TARDE / NOITE" },
										  { "O Auto da Compadecida",  "Categoria: COMÉDIA",  "Sessões: MANHÃ / TARDE / NOITE" },
										  { "Romeu e Julieta",  "Categoria: DRAMA",  "Sessões: MANHÃ / TARDE / NOITE" } };
	
	static DateTimeFormatter patternData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	static DateTimeFormatter patternDataHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	
	public static void main(String[] args) {

		// DECLARAÇÃO DE VARIÁVEIS
		Scanner input = new Scanner(System.in);
		
		String nome, sobrenome, cpf;
				
		int poltronasManha[][] = { { 25, 100, 30, 50, 50 }, 
								   { 25, 100, 30, 50, 50 }, 
								   { 25, 100, 30, 50, 50 } };
		
		int poltronasTarde[][] = { { 25, 100, 30, 50, 50 }, 
								   { 25, 100, 30, 50, 50 }, 
								   { 25, 100, 30, 50, 50 } };
		
		int poltronasNoite[][] = { { 25, 100, 30, 50, 50 }, 
								   { 25, 100, 30, 50, 50 }, 
								   { 25, 100, 30, 50, 50 } };
		
		int opcaoSair = 0, cont = 0, contIngresso = 1;

		// INÍCIO DO PROGRAMA
		do {
			if (cont > 0) {
				input.nextLine();
			}				
			
			//IMPRESSÃO DO BANNER
			imprimeBanner();
			if(cont == 0) {
				System.out.println(" 7 ART TICKET     (v.1.0.0)");
			}			
			System.out.println();
			
			// CADASTRO USUÁRIO
			System.out.println("CADASTRO: ");
			System.out.println();

			// INSERE NOME
			System.out.println("Informe o primeiro nome: ");
			nome = input.nextLine();
			nome = vailidacaoNome(input, nome);

			// INSERE SOBRENOME
			System.out.println("Informe o sobrenome: ");
			sobrenome = input.nextLine();
			sobrenome = vailidacaoSobrenome(input, sobrenome);

			// INSERE CPF
			System.out.println("Informe o CPF apenas com números: ");
			cpf = input.nextLine();
			cpf = validacaoCPF(input, cpf);

			// APRESENTAÇÃO DOS ESPETÁCULOS
			System.out.println("ESPETÁCULOS: ");
			System.out.println();
			imprimeMatrizString(infoEspetaculos);

			// SELEÇÃO DE ESPETÁCULO
			System.out.println("Informe o espetáculo: ");
			for (int i = 0; i < infoEspetaculos.length; i++) {
				System.out.print("["+(i + 1)+"] - " + infoEspetaculos[i][0] + "     ");
			}
			System.out.println();

			// VALIDAÇÃO OPÇÃO DO ESPETÁCULO
			int opcaoEspetaculo = 0;
			opcaoEspetaculo = validacaoOpcao(input, opcaoEspetaculo, infoEspetaculos.length);

			// SELEÇÃO ÁREA DA POLTRONA
			System.out.println("POLTRONAS: ");
			imprimeValores(areasTeatro, valoresIngresso);

			System.out.println("Informe a área da poltrona: ");
			for (int i = 0; i < areasTeatro.length; i++) {
				System.out.print("[" + (i + 1) + "] - " + areasTeatro[i] + "     ");
			}
			System.out.println();

			// VALIDAÇÃO OPÇÃO DA ÁREA DA POLTRONA
			int opcaoArea = 0;
			opcaoArea = validacaoOpcao(input, opcaoArea, areasTeatro.length);

			System.out.println();

			// ESCOLHENDO SESSÃO
			System.out.println("Informe o horário da sessão: ");
			System.out.println("[1] - Manhã     [2] - Tarde     [3] - Noite");

			// VALIDAÇÃO OPÇÃO DA SESSÃO
			int opcaoSessao = 0;
			opcaoSessao = validacaoOpcao(input, opcaoSessao, 3);

			// IMPRESSÃO DOS DADOS
			System.out.println("DETALHES DO INGRESSO: ");
			System.out.println();
			System.out.println("Nome: "+formatarPadraoNome(nome) + " " + formatarPadraoSobrenome(sobrenome));
			System.out.println("CPF: "+cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9)+ "-" + cpf.substring(9, 11));
			System.out.println("Espetáculo: " + infoEspetaculos[opcaoEspetaculo - 1][0]);
			switch (opcaoSessao) {
			case 1:
				System.out.println("Sessão: Manhã");
				break;
			case 2:
				System.out.println("Sessão: Tarde");
				break;
			case 3:
				System.out.println("Sessão: Noite");
				break;
			default:
				break;
			}
			System.out.println("Local da Poltrona: " + areasTeatro[opcaoArea - 1]);
			System.out.println("VALOR TOTAL: R$ " + formatarMoeda(valoresIngresso[opcaoArea - 1]));

			System.out.println();

			// CONFIRMAÇÃO DOS DADOS
			System.out.println("Confirmar os dados acima ?");
			System.out.println("[1] - Sim     [2] - Não");

			// VALIDAÇÃO DA OPÇÃO DE CONFIRMAÇÃO
			int opcaoConfirmar = 0;
			opcaoConfirmar = validacaoOpcao(input, opcaoConfirmar, 2);

			// RESERVANDO POLTRONA
			if (opcaoConfirmar == 1) {
				int opcaoPoltronasDisponiveis;
				switch (opcaoSessao) {
				case 1:
					// APRESENTAÇÃO DAS POLTRONAS DISPONÍVEIS
					if (poltronasManha[opcaoEspetaculo - 1][opcaoArea - 1] <= 0) {
						System.out.println("Não há mais poltronas disponíveis nesta área.");
						System.out.println();
						System.out.println("Gostaria de ver as poltronas disponíveis ? ");
						System.out.println("[1] - Sim     [2]- Não");

						// VALIDAÇÃO OPÇÃO POLTRONAS DISPONÍVEIS
						opcaoPoltronasDisponiveis = 0;
						opcaoPoltronasDisponiveis = validacaoOpcao(input, opcaoPoltronasDisponiveis, 2);
						
						if (opcaoPoltronasDisponiveis == 1) {
							System.out.println("POLTRONAS DISPONÍVEIS:");
							imprimeLugaresDisponiveis(areasTeatro, poltronasManha, opcaoEspetaculo);
						}
						break;
					}
					// RESERVA DA POLTRONA
					poltronasManha[opcaoEspetaculo - 1][opcaoArea - 1]--;
					System.out.println("Compra Bem-sucedida !");
					imprimeIngresso(nome, sobrenome, cpf, areasTeatro, valoresIngresso, infoEspetaculos, contIngresso, opcaoEspetaculo, opcaoArea, opcaoSessao);
					contIngresso++;
					break;
				case 2:
					// APRESENTAÇÃO DAS POLTRONAS DISPONÍVEIS
					if (poltronasTarde[opcaoEspetaculo - 1][opcaoArea - 1] <= 0) {
						System.out.println("Não há mais poltronas disponíveis nesta área.");
						System.out.println();
						System.out.println("Gostaria de ver as poltronas disponíveis ? ");
						System.out.println("[1] - Sim     [2]- Não");

						// VALIDAÇÃO OPÇÃO POLTRONAS DISPONÍVEIS
						opcaoPoltronasDisponiveis = 0;
						opcaoPoltronasDisponiveis = validacaoOpcao(input, opcaoPoltronasDisponiveis, 2);
						
						if (opcaoPoltronasDisponiveis == 1) {
							System.out.println("POLTRONAS DISPONÍVEIS:");
							imprimeLugaresDisponiveis(areasTeatro, poltronasTarde, opcaoEspetaculo);
						}
						break;
					}
					// RESERVA DA POLTRONA
					poltronasTarde[opcaoEspetaculo - 1][opcaoArea - 1]--;
					System.out.println("Compra Bem-sucedida !");
					imprimeIngresso(nome, sobrenome, cpf, areasTeatro, valoresIngresso, infoEspetaculos, contIngresso, opcaoEspetaculo, opcaoArea, opcaoSessao);
					contIngresso++;
					break;
				case 3:
					// APRESENTAÇÃO DAS POLTRONAS DISPONÍVEIS
					if (poltronasNoite[opcaoEspetaculo - 1][opcaoArea - 1] <= 0) {
						System.out.println("Não há mais poltronas disponíveis nesta área.");
						System.out.println();
						System.out.println("Gostaria de ver as poltronas disponíveis ? ");
						System.out.println("[1] - Sim     [2]- Não");

						// VALIDAÇÃO OPÇÃO POLTRONAS DISPONÍVEIS
						opcaoPoltronasDisponiveis = 0;
						opcaoPoltronasDisponiveis = validacaoOpcao(input, opcaoPoltronasDisponiveis, 2);
						if (opcaoPoltronasDisponiveis == 1) {
							System.out.println("POLTRONAS DISPONÍVEIS:");
							imprimeLugaresDisponiveis(areasTeatro, poltronasNoite, opcaoEspetaculo);
						}
						break;
					}
					// RESERVA DA POLTRONA
					poltronasNoite[opcaoEspetaculo - 1][opcaoArea - 1]--;
					System.out.println("Compra Bem-sucedida !");
					imprimeIngresso(nome, sobrenome, cpf, areasTeatro, valoresIngresso, infoEspetaculos, contIngresso, opcaoEspetaculo, opcaoArea, opcaoSessao);
					contIngresso++;
					break;
				default:
					break;
				}
			} else {
				System.out.println("Operação cancelada !");
			}

			// APRESENTAÇÃO OPÇÕES FINAIS
			do {
				input.nextLine();
				System.out.println("\nInforme a opção desejada: ");
				System.out.println("[1] - Comprar Novamente     [2] - Relatórios     [3] - Encerrar Programa");

				// VALIDAÇÃO DE OPÇÃO FINAL
				opcaoSair = 0;
				opcaoSair = validacaoOpcao(input, opcaoSair, 3);

				// REALTÓRIOS
				if (opcaoSair == 2) {
					System.out.println("Informe o relatório desejado: ");
					System.out.println("[1] - Relatório de Total Ingressos Vendidos     [2] - Relatório de Vendas por Sessão");
					System.out.println("[3] - Relatório de Lucro por Sessão             [4] - Relatório de Lucro do Teatro");

					// VALIDAÇÃO DA OPÇÃO DE RELATÓRIOS
					int opcaoRelatorio = 0;
					opcaoRelatorio = validacaoOpcao(input, opcaoRelatorio, 4);

					switch (opcaoRelatorio) {
					case 1:
						System.out.println("RELATÓRIO TOTAL DE INGRESSOS VENDIDOS ("+LocalDate.now().format(patternData)+")");
						System.out.println();
						System.out.println(infoEspetaculos[0][0].toUpperCase());
						System.out.println("Total de ingressos vendidos: " + (calculaQtdeTotalIngressos(poltronasManha, 0)
																		   + calculaQtdeTotalIngressos(poltronasTarde, 0)
																		   + calculaQtdeTotalIngressos(poltronasNoite, 0)));
						System.out.println();
						System.out.println(infoEspetaculos[0][0].toUpperCase());
						System.out.println("Total de ingressos vendidos: " + (calculaQtdeTotalIngressos(poltronasManha, 1)
																		   + calculaQtdeTotalIngressos(poltronasTarde, 1)
																		   + calculaQtdeTotalIngressos(poltronasNoite, 1)));
						System.out.println();
						System.out.println(infoEspetaculos[2][0].toUpperCase());
						System.out.println("Total de ingressos vendidos: " + (calculaQtdeTotalIngressos(poltronasManha, 2)
																		   + calculaQtdeTotalIngressos(poltronasTarde, 2)
																		   + calculaQtdeTotalIngressos(poltronasNoite, 2)));
						System.out.println();
						break;
					case 2:
						System.out.println("RELATÓRIO DE VENDAS POR SESSÃO ("+LocalDate.now().format(patternData)+")");
						System.out.println();
						
						System.out.println(infoEspetaculos[0][0].toUpperCase());
						System.out.println("PERÍODO      PLATEIA A       PLATEIA B       FRISA           CAMAROTE        BALCÃO NOBRE");
						System.out.print("Manhã");
						imprimeQtdePoltronaSessaoArea(poltronasManha, 0);
						System.out.print("Tarde");
						imprimeQtdePoltronaSessaoArea(poltronasTarde, 0);
						System.out.print("Noite");
						imprimeQtdePoltronaSessaoArea(poltronasNoite, 0);
						System.out.println();
						
						System.out.println(infoEspetaculos[1][0].toUpperCase());
						System.out.println("PERÍODO      PLATEIA A       PLATEIA B       FRISA           CAMAROTE        BALCÃO NOBRE");
						System.out.print("Manhã");
						imprimeQtdePoltronaSessaoArea(poltronasManha, 1);
						System.out.print("Tarde");
						imprimeQtdePoltronaSessaoArea(poltronasTarde, 1);
						System.out.print("Noite");
						imprimeQtdePoltronaSessaoArea(poltronasNoite, 1);
						System.out.println();
						
						System.out.println(infoEspetaculos[2][0].toUpperCase());
						System.out.println("PERÍODO      PLATEIA A       PLATEIA B       FRISA           CAMAROTE        BALCÃO NOBRE");
						System.out.print("Manhã");
						imprimeQtdePoltronaSessaoArea(poltronasManha, 2);
						System.out.print("Tarde");
						imprimeQtdePoltronaSessaoArea(poltronasTarde, 2);
						System.out.print("Noite");
						imprimeQtdePoltronaSessaoArea(poltronasNoite, 2);
						System.out.println();
						
						break;
					case 3:
						System.out.println("RELATÓRIO DE LUCRO POR SESSÃO ("+LocalDate.now().format(patternData)+")");
						System.out.println();
						System.out.println(infoEspetaculos[0][0].toUpperCase());
						System.out.println("PERÍODO      PLATEIA A        PLATEIA B        FRISA            CAMAROTE         BALCÃO NOBRE");
						System.out.print("Manhã");
						imprimeValorPoltronaSessaoArea(poltronasManha, 0);
						System.out.print("Tarde");
						imprimeValorPoltronaSessaoArea(poltronasTarde, 0);
						System.out.print("Noite");
						imprimeValorPoltronaSessaoArea(poltronasNoite, 0);
						System.out.println();
						
						System.out.println(infoEspetaculos[1][0].toUpperCase());
						System.out.println("PERÍODO      PLATEIA A        PLATEIA B        FRISA            CAMAROTE         BALCÃO NOBRE");
						System.out.print("Manhã");
						imprimeValorPoltronaSessaoArea(poltronasManha, 1);
						System.out.print("Tarde");
						imprimeValorPoltronaSessaoArea(poltronasTarde, 1);
						System.out.print("Noite");
						imprimeValorPoltronaSessaoArea(poltronasNoite, 1);
						System.out.println();
						
						System.out.println(infoEspetaculos[2][0].toUpperCase());
						System.out.println("PERÍODO      PLATEIA A        PLATEIA B        FRISA            CAMAROTE         BALCÃO NOBRE");
						System.out.print("Manhã");
						imprimeValorPoltronaSessaoArea(poltronasManha, 2);
						System.out.print("Tarde");
						imprimeValorPoltronaSessaoArea(poltronasTarde, 2);
						System.out.print("Noite");
						imprimeValorPoltronaSessaoArea(poltronasNoite, 2);
						System.out.println();
						
						break;
					case 4:
						System.out.println("RELATÓRIO LUCRO TOTAL DO TEATRO ("+LocalDate.now().format(patternData)+")");
						System.out.println();
						System.out.println(infoEspetaculos[0][0].toUpperCase());
						System.out.println("Lucro: R$ " + formatarMoeda(calculaValorTotalIngressos(poltronasManha, 0)
														+ calculaValorTotalIngressos(poltronasTarde, 0)
														+ calculaValorTotalIngressos(poltronasNoite, 0)));
						System.out.println();
						System.out.println(infoEspetaculos[1][0].toUpperCase());
						System.out.println("Lucro: R$ " + formatarMoeda(calculaValorTotalIngressos(poltronasManha, 1)
														+ calculaValorTotalIngressos(poltronasTarde, 1)
														+ calculaValorTotalIngressos(poltronasNoite, 1)));
						System.out.println();
						System.out.println(infoEspetaculos[2][0].toUpperCase());
						System.out.println("Lucro: R$ " + formatarMoeda(calculaValorTotalIngressos(poltronasManha, 2)
														+ calculaValorTotalIngressos(poltronasTarde, 2)
														+ calculaValorTotalIngressos(poltronasNoite, 2)));
						System.out.println();
						System.out.println("Lucro Total do Teatro: R$ " + formatarMoeda(calculaLucroTotal(poltronasManha)
																		+ calculaLucroTotal(poltronasTarde) 
																		+ calculaLucroTotal(poltronasNoite)));
						break;
					default:
						break;
					}
				}

			} while (opcaoSair == 2);
			cont++;
		} while (opcaoSair != 3);
		imprimeBanner();
		System.out.println(" Obrigado por utilizar nossos serviços.");
		input.close();
	}

	// MÉTODOS DO RELATÓRIO
	private static int calculaQtdeTotalIngressos(int[][] poltronas, int indiceEspetaculo) {
		int totalIngresso = 0;
		for (int i = 0; i < poltronasInicial.length; i++) {
			totalIngresso += (poltronasInicial[i] - poltronas[indiceEspetaculo][i]);			
		}
		return totalIngresso;
	}

	private static double calculaValorTotalIngressos(int[][] poltronas, int indiceEspetaculo) {
		double valorTotalIngresso = 0.0;
		for (int i = 0; i < poltronasInicial.length; i++) {
			valorTotalIngresso += (poltronasInicial[i] - poltronas[indiceEspetaculo][i]) * valoresIngresso[i];			
		}
		return valorTotalIngresso;
	}

	private static double calculaLucroTotal(int[][] poltronas) {
		double valorTotalTeatro = 0.0;
		for (int i = 0; i < poltronas.length; i++) {
			valorTotalTeatro += (25 - poltronas[i][0]) * 40;
			valorTotalTeatro += (100 - poltronas[i][1]) * 60;
			valorTotalTeatro += (30 - poltronas[i][2]) * 80;
			valorTotalTeatro += (50 - poltronas[i][3]) * 120;
			valorTotalTeatro += (50 - poltronas[i][4]) * 250;
		}
		return valorTotalTeatro;
	}

	// MÉTODOS DE IMPRESSÃO
	
	private static void imprimeBanner() {
		  System.out.println(" _____        _             _       _____   _          _             _    ");   
		  System.out.println("|___  |      / \\     _ __  | |_    |_   _| (_)   ___  | | __   ___  | |_  ");
		  System.out.println("   / /      / _ \\   | '__| | __|     | |   | |  / __| | |/ /  / _ \\ | __| ");
		  System.out.println("  / /      / ___ \\  | |    | |_      | |   | | | (__  |   <  |  __/ | |_  ");
		  System.out.println(" /_/      /_/   \\_\\ |_|     \\__|     |_|   |_|  \\___| |_|\\_\\  \\___|  \\__| ");
	}
	
	private static void imprimeQtdePoltronaSessaoArea(int[][] poltronas, int indiceEspetaculo) {
		System.out.print("        ");
		for (int i = 0; i < poltronasInicial.length; i++) {
			int quantidade = poltronasInicial[i] - poltronas[indiceEspetaculo][i];
			if(quantidade < 10) {
				System.out.print(quantidade+"               ");
			}
			else if(quantidade < 100){
				System.out.print(quantidade+"              ");				
			}
			else {
				System.out.print(quantidade+"             ");				
			}
		}
		System.out.println();
	}
	
	private static void imprimeValorPoltronaSessaoArea(int[][] poltronas, int indiceEspetaculo) {
		System.out.print("        ");
		for (int i = 0; i < poltronasInicial.length; i++) {
			double valor = (poltronasInicial[i] - poltronas[indiceEspetaculo][i]) * valoresIngresso[i];
			String valorFormatado = formatarMoeda(valor);
			switch (valorFormatado.length()) {
			case 4:
				System.out.print("R$ "+valorFormatado+"          ");				
				break;
			case 5:
				System.out.print("R$ "+valorFormatado+"         ");				
				break;
			case 6:
				System.out.print("R$ "+valorFormatado+"        ");				
				break;
			case 7:
				System.out.print("R$ "+valorFormatado+"       ");				
				break;
			case 8:
				System.out.print("R$ "+valorFormatado+"      ");				
				break;
			default:
				break;
			}
		}
		System.out.println();
	}
	
	private static void imprimeIngresso(String nome, String sobrenome, String cpf, String[] areasTeatro,
			double[] valoresIngresso, String[][] infoEspetaculos, int contIngresso, int opcaoEspetaculo, int opcaoArea,
			int opcaoSessao) {
		System.out.println();
		System.out.println("#=============================================#");
		System.out.println("|                 7 ART TICKET                |");
		System.out.println("#=============================================#");
		System.out.println("                 INGRESSO #"+contIngresso);
		System.out.println("#=============================================#");
		System.out.println("");
		System.out.println("     NOME: " + nome.toUpperCase() + " " + sobrenome.toUpperCase());
		System.out.println("     CPF: " + cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9)
				+ "-" + cpf.substring(9, 11));
		System.out.println("     ESPETÁCULO: " + infoEspetaculos[opcaoEspetaculo - 1][0].toUpperCase());
		switch (opcaoSessao) {
		case 1:
			System.out.println("     SESSÃO: MANHÃ");
			break;
		case 2:
			System.out.println("     SESSÃO: TARDE");
			break;
		case 3:
			System.out.println("     SESSÃO: NOITE");
			break;
		default:
			break;
		}
		
		System.out.println("     LOCAL DA POLTRONA: " + areasTeatro[opcaoArea - 1].toUpperCase());
		System.out.println("");
		System.out.println("#=============================================#");
		System.out.println("   DATA DE PAGAMENTO: "+LocalDateTime.now().format(patternDataHora));
		System.out.println("   VALOR DO PAGAMENTO: R$ " + formatarMoeda(valoresIngresso[opcaoArea - 1]));
		System.out.println("#=============================================#");
	}
	
	private static void imprimeValores(String[] areasTeatro, double[] valoresIngresso) {
		System.out.println();
		System.out.println("LOCAL DA POLTRONA         VALOR");
		System.out.println(areasTeatro[0] + "                 R$ " + formatarMoeda(valoresIngresso[0]));
		System.out.println(areasTeatro[1] + "                 R$ " + formatarMoeda(valoresIngresso[1]));
		System.out.println(areasTeatro[2] + "                     R$ " + formatarMoeda(valoresIngresso[2]));
		System.out.println(areasTeatro[3] + "                  R$ " + formatarMoeda(valoresIngresso[3]));
		System.out.println(areasTeatro[4] + "              R$ " + formatarMoeda(valoresIngresso[4]));
		System.out.println();
	}

	private static void imprimeLugaresDisponiveis(String[] areasTeatro, int[][] poltronasManha, int opcaoEspetaculo) {
		for (int i = 0; i < areasTeatro.length; i++) {
			System.out.print(areasTeatro[i] + ": ");
			if (poltronasManha[opcaoEspetaculo - 1][i] == 1) {
				System.out.print(poltronasManha[opcaoEspetaculo - 1][i] + " poltrona");
			} else {
				System.out.print(poltronasManha[opcaoEspetaculo - 1][i] + " poltronas");
			}
			System.out.println();
		}
	}

	private static void imprimeMatrizString(String[][] matriz) {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				System.out.println(matriz[i][j]);
			}
			System.out.println();
		}
	}

	// MÉTODOS DE FORMATAÇÃO 
	private static String formatarMoeda(double valor) {
		return String.format("%.2f", valor);
	}
	
	private static String formatarPadraoNome(String nome) {
		if(!nome.isEmpty()) {
			return nome.substring(0, 1).toUpperCase().concat(nome.substring(1, nome.length()).toLowerCase());
		}
		return nome;
	}
	
	private static String formatarPadraoSobrenome(String sobrenome) {
		if(!sobrenome.isEmpty()) {
			if(!sobrenome.contains(" ") || sobrenome.indexOf(" ") == sobrenome.length() - 1) {
				return formatarPadraoNome(sobrenome);				
			}
			else {
				int indiceEspaco = sobrenome.indexOf(" ");
				String sub1 = sobrenome.substring(0, 1).toUpperCase().concat(sobrenome.substring(1, indiceEspaco+1));
				String sub2 = sobrenome.substring(indiceEspaco+1, indiceEspaco+2).toUpperCase().concat(sobrenome.substring(indiceEspaco+2, sobrenome.length()));
				return sub1.concat(sub2);				
			}
		}
		return sobrenome;
	}

	// MÉTODOS DE VALIDAÇÃO
	public static int validacaoOpcao(Scanner input, int opcao, int limite) {
		boolean valido = false;
		while (!valido) {
			try {
				opcao = input.nextInt();
				while (opcao <= 0 || opcao > limite) {
					System.out.println("Campo Inválido: Deve conter apenas o número da opção, no intervalo de 1 e " + limite + ".");
					System.out.println("Digite novamente: ");
					opcao = input.nextInt();
				}
				valido = true;
			} catch (InputMismatchException e) {
				System.out.println("Campo Inválido: Deve conter apenas o número da opção, no intervalo de 1 e " + limite + ".");
				System.out.println("Digite novamente: ");
				input.nextLine();
			}
		}
		return opcao;
	}

	private static String validacaoCPF(Scanner input, String cpf) {
		while (!validaCPF(cpf)) {
			System.out.println("Campo inválido: Deve conter o CPF com 11 números. (Sem pontos ou traço)");
			System.out.println("Digite novamente: ");
			cpf = input.nextLine();
		}
		return cpf;
	}

	private static String vailidacaoNome(Scanner input, String nome) {
		while (!validaNome(nome)) {
			System.out.println("Campo inválido: Deve conter apenas o primeiro nome.(Sem espaços, acentos ou caracteres especiais)");
			System.out.println("Digite novamente: ");
			nome = input.nextLine();
		}
		return nome;
	}

	private static String vailidacaoSobrenome(Scanner input, String sobrenome) {
		while (!validaSobrenome(sobrenome)) {
			System.out.println("Campo inválido: Deve conter sobrenome simples ou composto.(Sem acentos ou caracteres especiais)");
			System.out.println("Digite novamente: \n");
			sobrenome = input.nextLine();
		}
		return sobrenome;
	}

	public static boolean validaNome(String string) {
		boolean valido = false;
		Pattern pattern = Pattern.compile("[^a-zA-Z]");
		Matcher matcher = pattern.matcher(string);
		boolean validacao = matcher.find();
		if (!validacao && !string.isEmpty()) {
			valido = true;
		}
		return valido;
	}
	
	public static boolean validaSobrenome(String string) {
		boolean valido = false;
		Pattern pattern = Pattern.compile("[^a-zA-Z]");
		Matcher matcher = pattern.matcher(string.replace(" ", ""));
		boolean validacao = matcher.find();
		if (!validacao && !string.trim().isEmpty()) {
			valido = true;
		}
		return valido;
	}

	public static boolean validaCPF(String cpf) {
		boolean valido = true;
		if (cpf.length() != 11 || cpf.isEmpty() || !cpf.substring(0, cpf.length()).matches("[0-9]*")) {
			valido = false;
		}
		return valido;
	}
}
