package tornipuolustus.tornipuolustus;


public class Peli {

    private Kentta kentta;
    
    public Peli(){
        kentta = new Kentta(10);
    }

    public Kentta getKentta() {
        return kentta;
    }
    
    public void start() {
        kentta.tayta();
        kentta.piirra();
    }

}
