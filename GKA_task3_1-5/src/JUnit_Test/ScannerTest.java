package JUnit_Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import task_4.Service.Scanner;

public class ScannerTest
{
    Scanner scanner = new Scanner();

    @Test
    public void testDateiAlsArra1()
    {
        String path1 = "./src/bspGraphen/bsp3.graph";
        ArrayList<String> datei = scanner.dateiLesen(path1);
        String erwarteteZeile = "#attributed #weighted";
        assertEquals(erwarteteZeile, datei.get(0));
    }
    
    @Test
    public void testDateiAlsArra2()
    {
        String path1 = "./src/bspGraphen/bsp3.graph";
        ArrayList<String> datei = scanner.dateiLesen(path1);
        String erwarteteZeile = "Paderborn:221,Hamburg:0::228";
        assertEquals(erwarteteZeile, datei.get(1));
    }

    @Test
    public void testDateiAlsArra3()
    {
        String path1 = "./src/bspGraphen/bsp6.graph";
        ArrayList<String> datei = scanner.dateiLesen(path1);
        String erwarteteZeile = "10";
        assertEquals(erwarteteZeile, datei.get(17));
    }    
}
