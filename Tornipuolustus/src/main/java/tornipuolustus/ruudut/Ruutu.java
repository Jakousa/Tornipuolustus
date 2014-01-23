/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tornipuolustus.ruudut;

public abstract class Ruutu implements Comparable<Ruutu> {

    private int x;
    private int y;

    public Ruutu(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public int compareTo(Ruutu r) {
        if (r.getY() < this.getY()) {
            return 1;
        }
        if (r.getY() > this.getY()) {
            return -1;
        }
        if (r.getX() < this.getX()) {
            return 1;
        }
        return -1;
    }
}
