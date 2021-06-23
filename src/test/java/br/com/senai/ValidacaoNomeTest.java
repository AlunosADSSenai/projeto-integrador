package br.com.senai;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ValidacaoNomeTest {

	@Test
	@DisplayName("Deve passar na valida��o de nome somente com letras")
	void test01() {		
		boolean validaString = Principal.validaNome("Teste");
		assertEquals(true, validaString);
	}
	
	@Test
	@DisplayName("Deve falhar na valida��o de nome com n�meros")
	void test02() {		
		boolean validaString = Principal.validaNome("Teste123");
		assertEquals(false, validaString);
	}
	
	@Test
	@DisplayName("Deve falhar na valida��o de nome com caracteres especiais")
	void test03() {		
		boolean validaString = Principal.validaNome("Teste /,.;�~][�\'=+");
		assertEquals(false, validaString);
	}
	
	@Test
	@DisplayName("Deve falhar na valida��o de nome com espa�os em branco")
	void test04() {		
		boolean validaString = Principal.validaNome("Teste Teste");
		assertEquals(false, validaString);
	}
	
	@Test
	@DisplayName("Deve falhar na valida��o de nome com acentos")
	void test05() {		
		boolean validaString = Principal.validaNome("Teste ����");
		assertEquals(false, validaString);
	}

	@Test
	@DisplayName("Deve falhar na valida��o de nome vazio")
	void test06() {		
		boolean validaString = Principal.validaNome(" ");
		assertEquals(false, validaString);
	}

}
