
package tornipuolustus.ruudut;


public class Rakennettava extends Ruutu {

    private boolean varaus;
    
    public Rakennettava(int x, int y) {
        super(x,y);
        varaus = false;
    }
    
    public void varaa(){
        varaus = true;
    }
    
    public void vapauta() {
        varaus = false;
    }
    
    public boolean getVaraus() {
        return varaus;
    }

    @Override
    public String toString() {
        if (varaus) {
            return "T";
        }
        return "O";
    }
}
