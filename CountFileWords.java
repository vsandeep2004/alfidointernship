/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package countWords;

import java.io.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.*;

public class CountFileWords {
    public static void main(String[] args) throws IOException {
        try {
            Map<String,Integer> MapPalabras = new HashMap<String, Integer>();
            BufferedWriter bw = new BufferedWriter(new FileWriter("test-files/counted-words.txt"));
            List<String>  lista = Files.readAllLines(Paths.get("test-files/test.txt"));
            lista = lista.stream()
                    .map(w -> w.split(" ")).flatMap(Arrays::stream)
                    .collect(Collectors.toList());
            //lista.forEach(System.out::println);
            for (String palabra : lista) {
                if (MapPalabras.containsKey(palabra)) {
                    MapPalabras.put(palabra, MapPalabras.get(palabra)+1);
                } else {
                    MapPalabras.put(palabra, 1);
                }
            }
            //ordenar alfabeticamente
            MapPalabras = new TreeMap<String,Integer>(MapPalabras);
            //escribir en un fichero
            for (String p : MapPalabras.keySet()) {
                System.out.println(p + " : " + MapPalabras.get(p));
                
                bw.write(p + " : " + MapPalabras.get(p));
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CountFileWords.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
