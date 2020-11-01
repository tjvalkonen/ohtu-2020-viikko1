package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;

    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void eiVoiLisätäLiikaa() {
        varasto.lisaaVarastoon(11);
        
        // varastoon on laitettu vain 10 ja mahtuu enään 0
        assertEquals(0, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void eiVoiLisätäVäärää() {
        varasto.lisaaVarastoon(-2);
        
        // varastossa ei mitään
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void eiVoiOttaaLiikaa() {
        varasto.lisaaVarastoon(5);
        
        varasto.otaVarastosta(6);
        
        // varastoon laitettu 5 ja kun otetaan 6 varastoon jää 0
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void eiVoiOttaaVäärää() {
        varasto.lisaaVarastoon(5);
        
        varasto.otaVarastosta(-2);
        
        // varastoon laitettu 5 ja kun otetaan -2 ei saldo muutu
        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void varastoStringToimii() {
       
        String test = "saldo = 0.0, vielä tilaa 10.0";
        
        assertTrue(varasto.toString().equals(test));
    }

    @Test
    public void kayttokelvottomallaVarastollaOikeaTilavuus() {
        Varasto varasto2 = new Varasto(-1);
        assertEquals(0, varasto2.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus2() {
        Varasto varasto2 = new Varasto(10,10);
        assertEquals(10, varasto2.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void kayttokelvottomallaVarastollaOikeaTilavuus2() {
        Varasto varasto2 = new Varasto(-1,-1);
        assertEquals(0, varasto2.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void uudellaVarastollaOikeaTilavuus3() {
        Varasto varasto2 = new Varasto(10,11);
        assertEquals(10, varasto2.getTilavuus(), vertailuTarkkuus);
    } 
}