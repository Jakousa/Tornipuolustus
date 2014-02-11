package tornipuolustus.tornipuolustus;

public class Pelaaja {

    private int rahat;
    private int pisteet;
    private int elama;

    public int getRahat() {
        return rahat;
    }

    public void aloitusRahat(int vaikeustaso) {
        this.rahat = (4 - vaikeustaso) * 200;
    }

    public int getPisteet() {
        return pisteet;
    }

    public void setPisteet(int pisteet) {
        this.pisteet = pisteet;
    }

    public int getElama() {
        return elama;
    }

    public void aloitusElama(int vaikeustaso) {
        this.elama = 11 - vaikeustaso;
    }

    public void otaOsumaa() {
        this.elama--;
    }

    public void tienaa(int osumista) {
        this.rahat += osumista * 7;
        this.pisteet += osumista * 17;
    }
    
    public void rakenna(int hinnalla) {
        this.rahat -= hinnalla;
    }
    
    public void tuhoaTorni(int hinta) {
        this.rahat += hinta/2;
    }
}
