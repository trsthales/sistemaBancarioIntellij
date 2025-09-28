package thales;


import negocio.Cliente;
import negocio.GerenciadoraClientes;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
/**
 * Classe criada para validar as principais funcionalidades da classe {@link GerenciadoraClientes}
 * @author Thales Ramalho de Souza
 * @date 28/09/2025
 */
public class GerenciaClientesTest {

    private List<Cliente> clientes;
    private GerenciadoraClientes gerenciadoraClientes;
    private int idCliente1 = 1;
    private int idCliente2 = 2;

    /**
     * Criando 2 Clientes e adiconando eles em uma lista
     * Instanciando um GerenciadoraClientes com a lista criada
     */
    @Before
    public void setUp() {
        clientes = new ArrayList<Cliente>();
        Cliente cliente1 = new Cliente(idCliente1,"Thales Ramalho", 38, "thales87@gmail.com",1,true);
        Cliente cliente2 = new Cliente(idCliente2,"Murillo Ramalho", 37, "murillinho@gmail.com",1,true);

        clientes.add(cliente1);
        clientes.add(cliente2);

        gerenciadoraClientes = new GerenciadoraClientes(clientes);
    }

    /**
     * Método que pesquisa um cliente a partir de um id
     */
    @Test
    public void testPesquisaCliente(){
        Cliente clientePesquisado = gerenciadoraClientes.pesquisaCliente(1);

        assertThat(clientePesquisado.getId(),is(idCliente1));
        assertThat(clientePesquisado.getEmail(),is("thales87@gmail.com"));
    }

    /**
     * Teste que remove um cliente a partir de um id
     * @author Thales Ramalho de Souza
     * @dade 28/09/2025
     */
    @Test
    public void testRemoveCliente(){
        boolean clienteRemovido = gerenciadoraClientes.removeCliente(idCliente1);

        assertTrue(clienteRemovido);
        assertThat(gerenciadoraClientes.getClientesDoBanco().size(), is(idCliente1));
        assertNull(gerenciadoraClientes.pesquisaCliente(idCliente1));
    }

    /**
     * Teste que verifica a pesquisa de um cliente inexistente
     * @author Thales Ramalho de Souza
     * @date 28/09/2025
     */
    @Test
    public void testPesquisaClienteInexistente(){
        Cliente clienteInexistente = gerenciadoraClientes.pesquisaCliente(3);
        assertNull(clienteInexistente);
    }

    /**
     * limpa a lista de clientes
     */
    @After
    public void tearDown() {
        gerenciadoraClientes.limpa();
    }
}