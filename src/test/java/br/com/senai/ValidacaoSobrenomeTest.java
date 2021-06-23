package br.com.senai;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ValidacaoSobrenomeTest {

	@Test
	@DisplayName("Deve passar na valida��o de sobrenome somente com letras")
	void test01() {		
		boolean validaString = Principal.validaSobrenome("Teste");
		assertEquals(true, validaString);
	}
	
	@Test
	@DisplayName("Deve passar na valida��o de sobrenome com espa�os")
	void test04() {		
		boolean validaString = Principal.validaSobrenome("Teste Teste");
		assertEquals(true, validaString);
	}
	
	@Test
	@DisplayName("Deve falhar na valida��o de sobrenome com n�meros")
	void test02() {		
		boolean validaString = Principal.validaSobrenome("Teste123");
		assertEquals(false, validaString);
	}
	
	@Test
	@DisplayName("Deve falhar na valida��o de sobrenome com caracteres especiais")
	void test03() {		
		boolean validaString = Principal.validaSobrenome("Teste /,.;�~][�\'=+");
		assertEquals(false, validaString);
	}
	
	@Test
	@DisplayName("Deve falhar na valida��o de sobrenome com acentos")
	void test05() {		
		boolean validaString = Principal.validaSobrenome("Teste ����");
		assertEquals(false, validaString);
	}

	@Test
	@DisplayName("Deve falhar na valida��o de sobrenome vazio")
	void test06() {		
		boolean validaString = Principal.validaSobrenome(" ");
		
		assertEquals(false, validaString);
	}
	
	@Test
	@DisplayName("Deve passar na valida��o de sobrenome com espa�o")
	void test07() {		
		boolean validaString = Principal.validaSobrenome("Teste ");
		
		assertEquals(true, validaString);
	}
}
