package br.com.senai;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ValidacaoCPFTest {

	@Test
	@DisplayName("Deve passar na validação de cpf com 11 numeros")
	void test01() {
		boolean validaCpf = Principal.validaCPF("05119675190");
		assertEquals(true, validaCpf);
	}
	
	@Test
	@DisplayName("Deve falhar na validação de cpf quantidade de digitos diferente de 11")
	void test02() {
		boolean validaCpf = Principal.validaCPF("123456789000");
		boolean validaCpf1 = Principal.validaCPF("1234567890");
		boolean validaCpf2 = Principal.validaCPF("123456");
		
		assertEquals(false, validaCpf);
		assertEquals(false, validaCpf1);
		assertEquals(false, validaCpf2);
	}
	
	@Test
	@DisplayName("Deve falhar na validação de cpf com caracteres diferentes de numeros")
	void test03() {
		boolean validaCpf = Principal.validaCPF("teste");
		assertEquals(false, validaCpf);
	}
	
	@Test
	@DisplayName("Deve passar na validação de cpf com espaços em branco")
	void test04() {
		boolean validaCpf = Principal.validaCPF(" ");
		assertEquals(false, validaCpf);
	}
	
	@Test
	@DisplayName("Deve passar na validação de cpf com caracteres especiais")
	void test06() {
		boolean validaCpf = Principal.validaCPF("051.196.751-90");
		assertEquals(false, validaCpf);
	}
}
