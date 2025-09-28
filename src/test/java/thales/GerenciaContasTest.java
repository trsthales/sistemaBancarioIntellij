package thales;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import negocio.ContaCorrente;
import negocio.GerenciadoraContas;
/**
 * Classe que valida as principais funcionalidades da classe {@link GerenciadoraContas}
 * @author Thales Ramalho de Souza
 * @date 28/09/2025
 */
public class GerenciaContasTest {

    private GerenciadoraContas gerenciadoraContas;
    private ContaCorrente contaCorrente1;
    private ContaCorrente contaCorrente2;
    private List<ContaCorrente> listaContas;
    private int idCliente1 = 1;
    private int idCliente2 = 2;
    
    /**
     * Instancia 2 contas e adiciona elas em um lista
     * Instancia um GerenciadoraContas com a lista de contas criada acima
     * @author Thales Ramalho de Souza
     * @date 28/09/2025
     */
    @Before
    public void setUp(){
        contaCorrente1 = new ContaCorrente(idCliente1,250,true);
        contaCorrente2 = new ContaCorrente(idCliente2,350,true);

        listaContas = new ArrayList<ContaCorrente>();

        listaContas.add(contaCorrente1);
        listaContas.add(contaCorrente2);

        gerenciadoraContas = new GerenciadoraContas(listaContas);
    }

    /**
     * Teste que valida a transferencia de um valor de uma conta pra outra
     * @author Thales Ramalho de Souza
     * @date 28/09/2025
     */
    @Test
    public void testTransfereValor(){
        boolean deuCerto = gerenciadoraContas.transfereValor(idCliente1,100,idCliente2);

        assertTrue(deuCerto);
        assertThat(contaCorrente1.getSaldo(),is(150.0));
        assertThat(contaCorrente2.getSaldo(),is(450.0));
    }

    /**
     * Teste que valida a transferencia de um valor maior que o saldo da conta de onde o valor vai sair
     * @author Thales Ramalho de Souza
     * @date 28/09/2025
     */
    @Test
    public void testTransfereValorSaldoInsuficiente(){
        boolean deuCerto = gerenciadoraContas.transfereValor(idCliente1,300,idCliente2);

        assertTrue(deuCerto);
        assertThat(contaCorrente1.getSaldo(),is(-50.0));
        assertThat(contaCorrente2.getSaldo(),is(650.0));
    }

    /**
     * Limpa a lista de contas
     * @author Thales Ramalho de Souza
     * @date 28/09/2025 
     */
    @After
    public void tearDown(){
        listaContas.clear();
    }
}
