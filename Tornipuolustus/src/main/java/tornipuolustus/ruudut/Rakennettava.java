
package tornipuolustus.ruudut;


public class Rakennettava extends Ruutu {

    private boolean varattu;
    
    public Rakennettava(int x, int y) {
        super(x,y);
    }

    @Override
    public void piirra() {
        if (varattu) {
            System.out.print("T");
        }
        System.out.print("O");
    }
}
