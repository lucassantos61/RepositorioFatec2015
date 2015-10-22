package br.sceweb.teste;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.sceweb.modelo.Empresa;
import br.sceweb.modelo.EmpresaDAO;

public class UC01CadastraEmpresa {
	static EmpresaDAO empresaDAO;
	static Empresa empresa;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		empresaDAO = new EmpresaDAO();
		empresa = new Empresa();
		empresa.setNomeDaEmpresa("empresa x");
		empresa.setCnpj("89424232000180");
		empresa.setNomeFantasia("empresa x");
		empresa.setEndereco("Rua taquari");
		empresa.setTelefone("2222");
	}

	@Test
	public void CT01UC01CadastraEmpresa_com_sucesso() {
		empresaDAO.exclui("89424232000180");
		assertEquals(1, empresaDAO.adiciona(empresa));
		empresaDAO.exclui("89424232000180");
	}

	@Test(expected = RuntimeException.class)
	public void CT02UC01A2CadastraEmpresa_ja_cadastrada() {
		empresaDAO.adiciona(empresa);
		assertEquals(0, empresaDAO.adiciona(empresa));
	}
	
	@Test
	public void CT03UC01A3CadastraEmpresa_cnpj_invalido() {
		Empresa empresa2 = new Empresa();
		try{
			
			empresa2.setCnpj("89424232000180");
			fail("Deveria ter um excption");
		}catch(Exception e){
			assertEquals("CNPJ inválido", e.getMessage());
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

}
