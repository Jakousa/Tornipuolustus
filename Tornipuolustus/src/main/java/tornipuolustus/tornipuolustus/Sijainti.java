package tornipuolustus.tornipuolustus;

/**
 * Luokkaa lähinnä säilyttää piirtämisessä ja etäisyyksien 
 * vertailuun tarvittavia x ja y koordinaatteja
 */

public class Sijainti implements Comparable<Sijainti> {

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
    
    /**
     * Kahden pisteen välisen etäisyyden kaava
     * 
     * @param verrattava Sijainti johon tätä Sijaintia verrataan
     * @return palauttaa pisteiden välisen etäisyyden doublena
     */

    public double etaisyys(Sijainti verrattava) {
        double etaisyys = Math.pow(verrattava.getX() - this.getX(), 2) + Math.pow(verrattava.getY() - this.getY(), 2);
        etaisyys = Math.sqrt(etaisyys);
        return etaisyys;
    }

    /**
     * Kaksi sijaintia ovat samat mikäli ne ovat samassa paikassa
     * 
     * @param s Sijainti johon tätä Sijaintia verrataan
     * @return palauttaa tosi, mikäli x ja y ovat samat. Muulloin false.
     */
    public boolean equals(Sijainti s) {
        if (this.x == s.getX() && this.y == s.getY()) {
            return true;
        }
        return false;
    }

    /**
     * Pisteiden järjestämistä varten tarkoitettu metodi
     * 
     * @param s Sijainti johon tätä Sijaintia verrataan
     * @return Palauttaa 1 tai -1; Pienempi x ja y johtaa siihen, että kyseinen
     *          sijainti on ennen suurempaa x ja ytä.
     */
    @Override
    public int compareTo(Sijainti s) {
        if (s.getY() < this.getY()) {
            return 1;
        }
        if (s.getY() > this.getY()) {
            return -1;
        }
        if (s.getX() < this.getX()) {
            return 1;
        }
        return -1;
    }
}
