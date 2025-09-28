package thales;

import negocio.ContaCorrente;
import negocio.GerenciadoraContas;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GerenciaContasTest {

    private GerenciadoraContas gerenciadoraContas;
    private ContaCorrente contaCorrente1;
    private ContaCorrente contaCorrente2;
    private List<ContaCorrente> listaContas;

    @Before
    public void setUp(){
        contaCorrente1 = new ContaCorrente(1,250,true);
        contaCorrente2 = new ContaCorrente(2,350,true);

        listaContas = new ArrayList<ContaCorrente>();

        listaContas.add(contaCorrente1);
        listaContas.add(contaCorrente2);

        gerenciadoraContas = new GerenciadoraContas(listaContas);
    }

    @Test
    public void testTransfereValor(){
        boolean deuCerto = gerenciadoraContas.transfereValor(1,100,2);

        assertTrue(deuCerto);
        assertThat(contaCorrente1.getSaldo(),is(150.0));
        assertThat(contaCorrente2.getSaldo(),is(450.0));
    }

    @Test
    public void testTransfereValorSaldoInsuficiente(){
        boolean deuCerto = gerenciadoraContas.transfereValor(1,300,2);

        assertTrue(deuCerto);
        assertThat(contaCorrente1.getSaldo(),is(-50.0));
        assertThat(contaCorrente2.getSaldo(),is(650.0));
    }

    @After
    public void tearDown(){
        listaContas.clear();
    }
}
