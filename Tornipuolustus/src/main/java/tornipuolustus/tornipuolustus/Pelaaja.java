package tornipuolustus.tornipuolustus;

public class Pelaaja {

    private int rahat;
    private int pisteet;
    private int elama;
    private Sijainti kursori;

    public Pelaaja() {
        kursori = new Sijainti(0, 0);
    }

    public Sijainti getKursori() {
        return kursori;
    }

    public int getRahat() {
        return rahat;
    }

    /**
     * asettaa rahamäärän joka pelaajalla on ennen pelin alkua
     *
     * @param vaikeustaso Integer joka vaikuttaa pelin kulkuun ratkaisevasti
     */
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

    /**
     * asettaa luvun joka kertoo kuinka monta hirviötä voi päästä sokkelon läpi
     * ennen kuin peli päätyy
     *
     * @param vaikeustaso Integer joka vaikuttaa pelin kulkuun ratkaisevasti
     */
    public void aloitusElama(int vaikeustaso) {
        this.elama = 11 - vaikeustaso;
    }

    /**
     * vähentää pelaajan elämiä yhdellä
     */
    public void otaOsumaa() {
        this.elama--;
    }

    /**
     * Antaa epämääräisesti pisteitä ja rahaa pelajaalle.
     *
     * @param osumista Kuinka monta kertaa osuttiin hirviöön
     */
    public void tienaa(int osumista) {
        this.rahat += osumista * 9;
        this.pisteet += osumista * 17;
    }

    /**
     * Vähentää pelaajan rahoja.
     *
     * @param hinnalla vähennettävien rahojen määrä
     */
    public void rakenna(int hinnalla) {
        this.rahat -= hinnalla;
    }

    /**
     * Lisää pelaajan rahoja
     *
     * @param hinta kaksinkertainen määrä lisättäviin rahoihin verrattuna
     */
    public void tuhoaTorni(int hinta) {
        this.rahat += hinta / 2;
    }
}
