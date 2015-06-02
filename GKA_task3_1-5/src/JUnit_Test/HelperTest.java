package JUnit_Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import task_3.Service.Helper;

public class HelperTest
{
    Helper helper = new Helper();

    // positive Tests
    String variante1 = "Hamburg,Berlin";
    ArrayList<String> arrayVar1 = new ArrayList<String>();
    String variante2 = "Hamburg:9,Stuttgart:60";
    ArrayList<String> arrayVar2 = new ArrayList<String>();
    String variante3 = "Frankfurt:0,Stockholm:9999999::-20.5";
    ArrayList<String> arrayVar3 = new ArrayList<String>();
    String variante4 = "a,z::1.0";
    ArrayList<String> arrayVar4 = new ArrayList<String>();
    String variante5 = "Hallo";
    ArrayList<String> arrayVar5 = new ArrayList<String>();

    // Jede Zeile aus der Datei wird als regulärer Ausdruck überprüft
    // Hier werden alle Varianten mit positiven Ergebnissen getestet
    // Auch negative und Gleitkomma Gewichtung werden überprüft
    @Test
    public void testgibInfosAusArrayPos()
    {
        arrayVar1.add("Hamburg");
        arrayVar1.add(null);
        arrayVar1.add("Berlin");
        arrayVar1.add(null);
        arrayVar1.add(null);
        assertEquals(arrayVar1, helper.gibInfoAusArray(variante1));
        arrayVar2.add("Hamburg");
        arrayVar2.add("9");
        arrayVar2.add("Stuttgart");
        arrayVar2.add("60");
        arrayVar2.add(null);
        assertEquals(arrayVar2, helper.gibInfoAusArray(variante2));
        arrayVar3.add("Frankfurt");
        arrayVar3.add("0");
        arrayVar3.add("Stockholm");
        arrayVar3.add("9999999");
        arrayVar3.add("-20.5");
        assertEquals(arrayVar3, helper.gibInfoAusArray(variante3));
        arrayVar4.add("a");
        arrayVar4.add(null);
        arrayVar4.add("z");
        arrayVar4.add(null);
        arrayVar4.add("1.0");
        assertEquals(arrayVar4, helper.gibInfoAusArray(variante4));
        arrayVar5.add("Hallo");
        arrayVar5.add(null);
        arrayVar5.add(null);
        arrayVar5.add(null);
        arrayVar5.add(null);
        assertEquals(arrayVar5, helper.gibInfoAusArray(variante5));
    }

    // negative Tests
    String neg1 = "";
    ArrayList<String> arrayNeg1 = new ArrayList<String>();
    String neg2 = null;
    ArrayList<String> arrayNeg2 = new ArrayList<String>();
    String neg3 = "Bey3rn";
    ArrayList<String> arrayNeg3 = new ArrayList<String>();
       
    // Hier werden Fälle getestet wo eine Exception geworfen wird
    // Beispielsweise bei null Elementen oder laut Definition ungültige Zeichenkette (Sonderzeichen und Zahlen im Knotenname)
    @Test(expected = IllegalArgumentException.class)
    public void testgibInfosAusArrayNeg()
    {
        helper.gibInfoAusArray(neg1);
        helper.gibInfoAusArray(neg2);
        helper.gibInfoAusArray(neg3);
    }
    
    // positive Tests
    ArrayList<String> arrayClean1 = new ArrayList<String>();
    ArrayList<String> arrayDirty1 = new ArrayList<String>();
    ArrayList<String> arrayClean2 = new ArrayList<String>();
    ArrayList<String> arrayDirty2 = new ArrayList<String>();
    
    // Hier wird überprüft ob die Methode alle Leerzeichen und nulls aus einem String gelöscht hat
    @Test
    public void testdateiBereinigen()
    {
        arrayClean1.add("T");
        arrayClean1.add("e");
        arrayClean1.add("s");
        arrayClean1.add("t");
        arrayDirty1.add(" T  ");
        arrayDirty1.add("e ");
        arrayDirty1.add("   s");
        arrayDirty1.add("t");
        assertEquals(arrayClean1, helper.dateiBereinigen(arrayDirty1));
        arrayClean2.add("Hamburg");
        arrayClean2.add("Bremen");
        arrayDirty2.add("Hamburg");
        arrayDirty2.add(null);
        arrayDirty2.add("Bremen");
        assertEquals(arrayClean2, helper.dateiBereinigen(arrayClean2));
    }
    
//    // Testet ob ein Graph in eine Datei gespeichert wird
//    // und diese anschließend ohne Fehler gelesen werden kann
//    @SuppressWarnings({ "static-access", "unused" })
//    @Test
//    public void testGraphSpeichern()
//    {
//        String path = "./src/bspGraphen/bsp1.graph";
//        List<CustomVertex> temp = new ArrayList<CustomVertex>();
//        StartUpMain main = new StartUpMain();
//        CustomVertex v1 = new CustomVertex("");
//        CustomVertex v2 = new CustomVertex("");
//        temp = main.programmStarten(path, v1, v2, "astar");
//        
//        String path2 = "./src/bspGraphen/gespeicherterGraph.graph";
//        Scanner scanner = new Scanner();
//        scanner.dateiLesen(path2);
//    }
}
