package thales;


import negocio.Cliente;
import negocio.GerenciadoraClientes;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class GerenciaClientesTest {

    private List<Cliente> clientes;
    private GerenciadoraClientes gerenciadoraClientes;

    @Before
    public void setUp() {
        clientes = new ArrayList<Cliente>();
        Cliente cliente1 = new Cliente(1,"Thales Ramalho", 38, "thales87@gmail.com",1,true);
        Cliente cliente2 = new Cliente(2,"Murillo Ramalho", 37, "murillinho@gmail.com",1,true);

        clientes.add(cliente1);
        clientes.add(cliente2);

        gerenciadoraClientes = new GerenciadoraClientes(clientes);
    }

    @Test
    public void testPesquisaCliente(){
        Cliente clientePesquisado = gerenciadoraClientes.pesquisaCliente(1);

        assertThat(clientePesquisado.getId(),is(1));
        assertThat(clientePesquisado.getEmail(),is("thales87@gmail.com"));
    }

    @Test
    public void testRemoveCliente(){
        boolean clienteRemovido = gerenciadoraClientes.removeCliente(1);

        assertTrue(clienteRemovido);
        assertThat(gerenciadoraClientes.getClientesDoBanco().size(), is(1));
        assertNull(gerenciadoraClientes.pesquisaCliente(1));
    }

    @Test
    public void testPesquisaClienteInexistente(){
        Cliente clienteInexistente = gerenciadoraClientes.pesquisaCliente(3);
        assertNull(clienteInexistente);
    }

    @After
    public void tearDown() {
        gerenciadoraClientes.limpa();
    }
}