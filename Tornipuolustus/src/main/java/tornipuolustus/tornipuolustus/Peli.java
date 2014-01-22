package tornipuolustus.tornipuolustus;


public class Peli {

    private Kentta kentta;
    
    public Peli(){
        kentta = new Kentta(10);
    }
    
    public void start() {
        kentta.luo();
        kentta.piirra();
    }

}
