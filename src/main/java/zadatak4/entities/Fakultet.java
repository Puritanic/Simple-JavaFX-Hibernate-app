package zadatak4.entities;

import javax.persistence.*;

// fakultet (id, naziv, grad, adresa, kontakt telefon)
@Entity
@Table(name = "fakultet")
public class Fakultet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "naziv")
    private String naziv;

    @Column(name = "grad")
    private String grad;

    @Column(name = "adresa")
    private String adresa;

    @Column(name = "telefon")
    private String telefon;

    public Fakultet() {
    }

    public Fakultet(int id, String naziv, String grad, String adresa, String telefon) {
        this.id = id;
        this.naziv = naziv;
        this.grad = grad;
        this.adresa = adresa;
        this.telefon = telefon;
    }

    public Fakultet(String naziv, String grad, String adresa, String telefon) {
        this.naziv = naziv;
        this.grad = grad;
        this.adresa = adresa;
        this.telefon = telefon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    @Override
    public String toString() {
        return "Fakultet{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                ", grad='" + grad + '\'' +
                ", adresa='" + adresa + '\'' +
                ", telefon='" + telefon + '\'' +
                '}';
    }
}
