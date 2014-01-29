/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tornipuolustus.tornipuolustus;


public class Sijainti {

    private int x;
    private int y;
    
    public Sijainti(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public double etaisyys(Sijainti verrattava) {
        double etaisyys = Math.pow(verrattava.getX() - this.getX(), 2) + Math.pow(verrattava.getY() - this.getY(), 2);
        etaisyys = Math.sqrt(etaisyys);
        return etaisyys;
    }
}
