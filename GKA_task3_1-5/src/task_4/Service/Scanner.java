package task_4.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Scanner
{
    private ArrayList<String> dateiAlsArray = null;
    
    private Boolean directed = false;
    private Boolean undirected = false;
    private Boolean weighted = false;
    private Boolean attributed = false;
    private Boolean standard = false;
    
    public Scanner()
    {
        dateiAlsArray = new ArrayList<String>();
    }
    
    @SuppressWarnings({ "unused", "resource" })
    public ArrayList<String> dateiLesen(String path)
    {
        //Datei lesen
        FileReader fr;
        BufferedReader br;
        
        try
        {
            fr = new FileReader(path);
            br = new BufferedReader(fr);
            
            String zeile = br.readLine();
            while(zeile != null)
            {
                dateiAlsArray.add(zeile);
                zeile = br.readLine();
            }
            br.close();
        } catch(IOException e)
        {
            try
            {
                FileWriter writer = new FileWriter(path, false);
                return null;
            } catch(IOException e2)
            {
                System.out.println("Fehler beim Lesen das Datei" + path);
                e2.printStackTrace();
            }
        }

        graphArtExtrahieren();
        
        if(dateiAlsArray.isEmpty())
        {
            System.out.println("Datei ist leer!");
        }
        return dateiAlsArray;
    }

    public void graphArtExtrahieren()
    {
     // entscheiden ob directed/undirected - attributed - weighted
        String merkmale = dateiAlsArray.get(0).toLowerCase();
        // #directed #weighted
        if (merkmale.contains("#directed") && merkmale.contains("#weighted"))
        {
            System.out.println("Graphart: directed + weighted");
            directed = true;
            weighted = true;
        }
        // #directed
        else if (merkmale.contains("#directed"))
        {
            System.out.println("Graphart: directed");
            directed = true;
        }
        // #undirected #weighted
        else if (merkmale.contains("#undirected")
                && merkmale.contains("weighted"))
        {
            System.out.println("Graphart: undirected + weighted");
            undirected = true;
            weighted = true;
        }
        // #undirected
        else if (merkmale.contains("#undirected"))
        {
            System.out.println("Graphart: undirected");
            undirected = true;
        }
        // Default
        else if (merkmale.contains("#weighted") && merkmale.contains("attributed"))
        {
            System.out.println("Graphart: attributed + weighted");
            attributed = true;
            weighted = true;
        }
    }
       
    public boolean gibDirected()
    {
        return directed;
    }

    public boolean gibUndirected()
    {
        return undirected;
    }
    
    public boolean gibWeighted()
    {
        return weighted;
    }

    public boolean gibAttributed()
    {
        return attributed;
    }
    
    public boolean gibDefault()
    {
        return standard;
    }

}
