package tornipuolustus.tornipuolustus;

import java.util.Scanner;

public class Main 
{
    public static void main( String[] args )
    {
        Peli tornipuolustus = new Peli(new Scanner(System.in));
        tornipuolustus.start();
    }
}
