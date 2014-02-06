package tornipuolustus.tornipuolustus;

import java.util.ArrayList;
import java.util.Scanner;
import tornipuolustus.ruudut.Kuljettava;
import tornipuolustus.ruudut.Rakennettava;
import tornipuolustus.toimijat.Hirvio;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Peli tornipuolustus = new Peli(new Scanner(System.in));
        tornipuolustus.start();
    }
}
