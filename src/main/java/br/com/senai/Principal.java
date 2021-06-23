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
	
	static String areasTeatro[] = { "Plateia A", "Plateia B", "Frisa", "Camarote", "Balc�o Nobre" };
	
	static String infoEspetaculos[][] = { { "A Pequena Sereia",  "Categoria: INFANTIL",  "Sess�es: MANH� / TARDE / NOITE" },
										  { "O Auto da Compadecida",  "Categoria: COM�DIA",  "Sess�es: MANH� / TARDE / NOITE" },
										  { "Romeu e Julieta",  "Categoria: DRAMA",  "Sess�es: MANH� / TARDE / NOITE" } };
	
	static DateTimeFormatter patternData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	static DateTimeFormatter patternDataHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	
	public static void main(String[] args) {

		// DECLARA��O DE VARI�VEIS
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

		// IN�CIO DO PROGRAMA
		do {
			if (cont > 0) {
				input.nextLine();
			}				
			
			//IMPRESS�O DO BANNER
			imprimeBanner();
			if(cont == 0) {
				System.out.println(" 7 ART TICKET     (v.1.0.0)");
			}			
			System.out.println();
			
			// CADASTRO USU�RIO
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
			System.out.println("Informe o CPF apenas com n�meros: ");
			cpf = input.nextLine();
			cpf = validacaoCPF(input, cpf);

			// APRESENTA��O DOS ESPET�CULOS
			System.out.println("ESPET�CULOS: ");
			System.out.println();
			imprimeMatrizString(infoEspetaculos);

			// SELE��O DE ESPET�CULO
			System.out.println("Informe o espet�culo: ");
			for (int i = 0; i < infoEspetaculos.length; i++) {
				System.out.print("["+(i + 1)+"] - " + infoEspetaculos[i][0] + "     ");
			}
			System.out.println();

			// VALIDA��O OP��O DO ESPET�CULO
			int opcaoEspetaculo = 0;
			opcaoEspetaculo = validacaoOpcao(input, opcaoEspetaculo, infoEspetaculos.length);

			// SELE��O �REA DA POLTRONA
			System.out.println("POLTRONAS: ");
			imprimeValores(areasTeatro, valoresIngresso);

			System.out.println("Informe a �rea da poltrona: ");
			for (int i = 0; i < areasTeatro.length; i++) {
				System.out.print("[" + (i + 1) + "] - " + areasTeatro[i] + "     ");
			}
			System.out.println();

			// VALIDA��O OP��O DA �REA DA POLTRONA
			int opcaoArea = 0;
			opcaoArea = validacaoOpcao(input, opcaoArea, areasTeatro.length);

			System.out.println();

			// ESCOLHENDO SESS�O
			System.out.println("Informe o hor�rio da sess�o: ");
			System.out.println("[1] - Manh�     [2] - Tarde     [3] - Noite");

			// VALIDA��O OP��O DA SESS�O
			int opcaoSessao = 0;
			opcaoSessao = validacaoOpcao(input, opcaoSessao, 3);

			// IMPRESS�O DOS DADOS
			System.out.println("DETALHES DO INGRESSO: ");
			System.out.println();
			System.out.println("Nome: "+formatarPadraoNome(nome) + " " + formatarPadraoSobrenome(sobrenome));
			System.out.println("CPF: "+cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9)+ "-" + cpf.substring(9, 11));
			System.out.println("Espet�culo: " + infoEspetaculos[opcaoEspetaculo - 1][0]);
			switch (opcaoSessao) {
			case 1:
				System.out.println("Sess�o: Manh�");
				break;
			case 2:
				System.out.println("Sess�o: Tarde");
				break;
			case 3:
				System.out.println("Sess�o: Noite");
				break;
			default:
				break;
			}
			System.out.println("Local da Poltrona: " + areasTeatro[opcaoArea - 1]);
			System.out.println("VALOR TOTAL: R$ " + formatarMoeda(valoresIngresso[opcaoArea - 1]));

			System.out.println();

			// CONFIRMA��O DOS DADOS
			System.out.println("Confirmar os dados acima ?");
			System.out.println("[1] - Sim     [2] - N�o");

			// VALIDA��O DA OP��O DE CONFIRMA��O
			int opcaoConfirmar = 0;
			opcaoConfirmar = validacaoOpcao(input, opcaoConfirmar, 2);

			// RESERVANDO POLTRONA
			if (opcaoConfirmar == 1) {
				int opcaoPoltronasDisponiveis;
				switch (opcaoSessao) {
				case 1:
					// APRESENTA��O DAS POLTRONAS DISPON�VEIS
					if (poltronasManha[opcaoEspetaculo - 1][opcaoArea - 1] <= 0) {
						System.out.println("N�o h� mais poltronas dispon�veis nesta �rea.");
						System.out.println();
						System.out.println("Gostaria de ver as poltronas dispon�veis ? ");
						System.out.println("[1] - Sim     [2]- N�o");

						// VALIDA��O OP��O POLTRONAS DISPON�VEIS
						opcaoPoltronasDisponiveis = 0;
						opcaoPoltronasDisponiveis = validacaoOpcao(input, opcaoPoltronasDisponiveis, 2);
						
						if (opcaoPoltronasDisponiveis == 1) {
							System.out.println("POLTRONAS DISPON�VEIS:");
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
					// APRESENTA��O DAS POLTRONAS DISPON�VEIS
					if (poltronasTarde[opcaoEspetaculo - 1][opcaoArea - 1] <= 0) {
						System.out.println("N�o h� mais poltronas dispon�veis nesta �rea.");
						System.out.println();
						System.out.println("Gostaria de ver as poltronas dispon�veis ? ");
						System.out.println("[1] - Sim     [2]- N�o");

						// VALIDA��O OP��O POLTRONAS DISPON�VEIS
						opcaoPoltronasDisponiveis = 0;
						opcaoPoltronasDisponiveis = validacaoOpcao(input, opcaoPoltronasDisponiveis, 2);
						
						if (opcaoPoltronasDisponiveis == 1) {
							System.out.println("POLTRONAS DISPON�VEIS:");
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
					// APRESENTA��O DAS POLTRONAS DISPON�VEIS
					if (poltronasNoite[opcaoEspetaculo - 1][opcaoArea - 1] <= 0) {
						System.out.println("N�o h� mais poltronas dispon�veis nesta �rea.");
						System.out.println();
						System.out.println("Gostaria de ver as poltronas dispon�veis ? ");
						System.out.println("[1] - Sim     [2]- N�o");

						// VALIDA��O OP��O POLTRONAS DISPON�VEIS
						opcaoPoltronasDisponiveis = 0;
						opcaoPoltronasDisponiveis = validacaoOpcao(input, opcaoPoltronasDisponiveis, 2);
						if (opcaoPoltronasDisponiveis == 1) {
							System.out.println("POLTRONAS DISPON�VEIS:");
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
				System.out.println("Opera��o cancelada !");
			}

			// APRESENTA��O OP��ES FINAIS
			do {
				input.nextLine();
				System.out.println("\nInforme a op��o desejada: ");
				System.out.println("[1] - Comprar Novamente     [2] - Relat�rios     [3] - Encerrar Programa");

				// VALIDA��O DE OP��O FINAL
				opcaoSair = 0;
				opcaoSair = validacaoOpcao(input, opcaoSair, 3);

				// REALT�RIOS
				if (opcaoSair == 2) {
					System.out.println("Informe o relat�rio desejado: ");
					System.out.println("[1] - Relat�rio de Total Ingressos Vendidos     [2] - Relat�rio de Vendas por Sess�o");
					System.out.println("[3] - Relat�rio de Lucro por Sess�o             [4] - Relat�rio de Lucro do Teatro");

					// VALIDA��O DA OP��O DE RELAT�RIOS
					int opcaoRelatorio = 0;
					opcaoRelatorio = validacaoOpcao(input, opcaoRelatorio, 4);

					switch (opcaoRelatorio) {
					case 1:
						System.out.println("RELAT�RIO TOTAL DE INGRESSOS VENDIDOS ("+LocalDate.now().format(patternData)+")");
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
						System.out.println("RELAT�RIO DE VENDAS POR SESS�O ("+LocalDate.now().format(patternData)+")");
						System.out.println();
						
						System.out.println(infoEspetaculos[0][0].toUpperCase());
						System.out.println("PER�ODO      PLATEIA A       PLATEIA B       FRISA           CAMAROTE        BALC�O NOBRE");
						System.out.print("Manh�");
						imprimeQtdePoltronaSessaoArea(poltronasManha, 0);
						System.out.print("Tarde");
						imprimeQtdePoltronaSessaoArea(poltronasTarde, 0);
						System.out.print("Noite");
						imprimeQtdePoltronaSessaoArea(poltronasNoite, 0);
						System.out.println();
						
						System.out.println(infoEspetaculos[1][0].toUpperCase());
						System.out.println("PER�ODO      PLATEIA A       PLATEIA B       FRISA           CAMAROTE        BALC�O NOBRE");
						System.out.print("Manh�");
						imprimeQtdePoltronaSessaoArea(poltronasManha, 1);
						System.out.print("Tarde");
						imprimeQtdePoltronaSessaoArea(poltronasTarde, 1);
						System.out.print("Noite");
						imprimeQtdePoltronaSessaoArea(poltronasNoite, 1);
						System.out.println();
						
						System.out.println(infoEspetaculos[2][0].toUpperCase());
						System.out.println("PER�ODO      PLATEIA A       PLATEIA B       FRISA           CAMAROTE        BALC�O NOBRE");
						System.out.print("Manh�");
						imprimeQtdePoltronaSessaoArea(poltronasManha, 2);
						System.out.print("Tarde");
						imprimeQtdePoltronaSessaoArea(poltronasTarde, 2);
						System.out.print("Noite");
						imprimeQtdePoltronaSessaoArea(poltronasNoite, 2);
						System.out.println();
						
						break;
					case 3:
						System.out.println("RELAT�RIO DE LUCRO POR SESS�O ("+LocalDate.now().format(patternData)+")");
						System.out.println();
						System.out.println(infoEspetaculos[0][0].toUpperCase());
						System.out.println("PER�ODO      PLATEIA A        PLATEIA B        FRISA            CAMAROTE         BALC�O NOBRE");
						System.out.print("Manh�");
						imprimeValorPoltronaSessaoArea(poltronasManha, 0);
						System.out.print("Tarde");
						imprimeValorPoltronaSessaoArea(poltronasTarde, 0);
						System.out.print("Noite");
						imprimeValorPoltronaSessaoArea(poltronasNoite, 0);
						System.out.println();
						
						System.out.println(infoEspetaculos[1][0].toUpperCase());
						System.out.println("PER�ODO      PLATEIA A        PLATEIA B        FRISA            CAMAROTE         BALC�O NOBRE");
						System.out.print("Manh�");
						imprimeValorPoltronaSessaoArea(poltronasManha, 1);
						System.out.print("Tarde");
						imprimeValorPoltronaSessaoArea(poltronasTarde, 1);
						System.out.print("Noite");
						imprimeValorPoltronaSessaoArea(poltronasNoite, 1);
						System.out.println();
						
						System.out.println(infoEspetaculos[2][0].toUpperCase());
						System.out.println("PER�ODO      PLATEIA A        PLATEIA B        FRISA            CAMAROTE         BALC�O NOBRE");
						System.out.print("Manh�");
						imprimeValorPoltronaSessaoArea(poltronasManha, 2);
						System.out.print("Tarde");
						imprimeValorPoltronaSessaoArea(poltronasTarde, 2);
						System.out.print("Noite");
						imprimeValorPoltronaSessaoArea(poltronasNoite, 2);
						System.out.println();
						
						break;
					case 4:
						System.out.println("RELAT�RIO LUCRO TOTAL DO TEATRO ("+LocalDate.now().format(patternData)+")");
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
		System.out.println(" Obrigado por utilizar nossos servi�os.");
		input.close();
	}

	// M�TODOS DO RELAT�RIO
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

	// M�TODOS DE IMPRESS�O
	
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
		System.out.println("     ESPET�CULO: " + infoEspetaculos[opcaoEspetaculo - 1][0].toUpperCase());
		switch (opcaoSessao) {
		case 1:
			System.out.println("     SESS�O: MANH�");
			break;
		case 2:
			System.out.println("     SESS�O: TARDE");
			break;
		case 3:
			System.out.println("     SESS�O: NOITE");
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

	// M�TODOS DE FORMATA��O 
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

	// M�TODOS DE VALIDA��O
	public static int validacaoOpcao(Scanner input, int opcao, int limite) {
		boolean valido = false;
		while (!valido) {
			try {
				opcao = input.nextInt();
				while (opcao <= 0 || opcao > limite) {
					System.out.println("Campo Inv�lido: Deve conter apenas o n�mero da op��o, no intervalo de 1 e " + limite + ".");
					System.out.println("Digite novamente: ");
					opcao = input.nextInt();
				}
				valido = true;
			} catch (InputMismatchException e) {
				System.out.println("Campo Inv�lido: Deve conter apenas o n�mero da op��o, no intervalo de 1 e " + limite + ".");
				System.out.println("Digite novamente: ");
				input.nextLine();
			}
		}
		return opcao;
	}

	private static String validacaoCPF(Scanner input, String cpf) {
		while (!validaCPF(cpf)) {
			System.out.println("Campo inv�lido: Deve conter o CPF com 11 n�meros. (Sem pontos ou tra�o)");
			System.out.println("Digite novamente: ");
			cpf = input.nextLine();
		}
		return cpf;
	}

	private static String vailidacaoNome(Scanner input, String nome) {
		while (!validaNome(nome)) {
			System.out.println("Campo inv�lido: Deve conter apenas o primeiro nome.(Sem espa�os, acentos ou caracteres especiais)");
			System.out.println("Digite novamente: ");
			nome = input.nextLine();
		}
		return nome;
	}

	private static String vailidacaoSobrenome(Scanner input, String sobrenome) {
		while (!validaSobrenome(sobrenome)) {
			System.out.println("Campo inv�lido: Deve conter sobrenome simples ou composto.(Sem acentos ou caracteres especiais)");
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
