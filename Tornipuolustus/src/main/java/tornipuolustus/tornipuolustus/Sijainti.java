package tornipuolustus.tornipuolustus;

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

    public double etaisyys(Sijainti verrattava) {
        double etaisyys = Math.pow(verrattava.getX() - this.getX(), 2) + Math.pow(verrattava.getY() - this.getY(), 2);
        etaisyys = Math.sqrt(etaisyys);
        return etaisyys;
    }

    public boolean equals(Sijainti s) {
        if (this.x == s.getX() && this.y == s.getY()) {
            return true;
        }
        return false;
    }

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
