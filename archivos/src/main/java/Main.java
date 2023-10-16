import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        /*
        Crear un metodo que lea un archivo de texto que se le pasa por parametros y elimine todas las vocales.
        El texto resultante se debera almacenar en otro archivo diferencte que se pase por parametro.
         */

        try (var br = new BufferedReader( new FileReader("carpetita/texto.txt"));
             var bw = new BufferedWriter(new FileWriter("carpetita/texto2.txt"))){
            int s;
            while((s = br.read()) != -1){
                char character = (char) s;
                if (!isVowel(character)) {
                    bw.write(character);
                }
            }
        } catch (FileNotFoundException e){
            throw new RuntimeException(e);
        } catch (IOException e){
            throw new RuntimeException(e);
        }

    }
    private static boolean isVowel(char c) {
        return "AEIOUaeiou".indexOf(c) != -1;
    }

    public static void main6(String[] args) {

        Long palabras = 0L;
        try (Scanner sc = new Scanner(new File("carpetita"+File.separator+"texto.txt"))){
            while(sc.hasNext()){
                System.out.println(sc.next());
                palabras++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Total palabras: " + palabras);
    }
    public static void main5(String[] args) {

        List<String> todo;
        try (var br = new BufferedReader( new FileReader("pom.xml"))){
            todo = br.lines().toList();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        todo.forEach((s)->{
            System.out.println(s.toUpperCase());
        });
    }
    public static void main4(String[] args) {

        try (var br = new BufferedReader( new FileReader("pom.xml"));
             var bw = new BufferedWriter( new FileWriter("pom2.xml"))){
            String s;
            while((s=br.readLine())!=null){
                System.out.println(s);
                bw.write(s.toUpperCase());
                bw.newLine();
            }
        } catch (FileNotFoundException e){
            throw new RuntimeException(e);
        } catch (IOException e){
            throw new RuntimeException(e);
        }

    }
    public static void main3(String[] args) {

        try (var fr = new FileReader("pom.xml")) {
            Integer tamaño = 0;
            int c;
            while ((c = fr.read()) != -1) {
                System.out.print((char) c);
                tamaño++;
            }
            fr.close();
            System.out.println("\n Cantidad de caracteres: " + tamaño);
        } catch (IOException ex) {
            System.out.println("Error de excepcion.");
        }
    }
    public static void main2(String[] args) {

        File f = new File("./carpetita");
        f.mkdir();

        File f2 = new File("archivito.txt");
        try {
            f2.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /*
        Iterator it = Arrays.stream(f.list()).iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
        */

        f2.delete();
        f = new File(".");

        for(File ff: f.listFiles()){
            if(ff.isDirectory()) System.out.print("D ");
            else System.out.print("F ");
            System.out.print( ff.length() );
            System.out.print("  ");
            System.out.print( new Date(ff.lastModified()) );
            System.out.print("  ");
            System.out.println(ff.getName());
        }
    }
}
