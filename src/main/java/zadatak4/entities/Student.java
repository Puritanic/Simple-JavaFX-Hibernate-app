package zadatak4.entities;

import javax.persistence.*;

//student (broj indeksa, id fakulteta, ime, prezime, godina upisa, status)
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "broj_indeksa", unique = true, nullable = false)
    private int brojIndeksa;

    @Column(name = "ime")
    private String ime;

    @Column(name = "prezime")
    private String prezime;

    @Column(name = "status")
    private String status;

    @Column(name = "godina_upisa")
    private int godinaUpisa;

    @Column(name = "id_fakulteta")
    private int IDFakulteta;

    public Student() {}

    public Student(String ime, String prezime, String status, int godinaUpisa, int IDFakulteta) {
        this.ime = ime;
        this.prezime = prezime;
        this.status = status;
        this.godinaUpisa = godinaUpisa;
        this.IDFakulteta = IDFakulteta;
    }

    public Student(int brojIndeksa, String ime, String prezime, String status, int godinaUpisa, int IDFakulteta) {
        this.brojIndeksa = brojIndeksa;
        this.ime = ime;
        this.prezime = prezime;
        this.status = status;
        this.godinaUpisa = godinaUpisa;
        this.IDFakulteta = IDFakulteta;
    }

    public int getBrojIndeksa() {
        return brojIndeksa;
    }

    public void setBrojIndeksa(int brojIndeksa) {
        this.brojIndeksa = brojIndeksa;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getGodinaUpisa() {
        return godinaUpisa;
    }

    public void setGodinaUpisa(int godinaUpisa) {
        this.godinaUpisa = godinaUpisa;
    }

    public int getIDFakulteta() {
        return IDFakulteta;
    }

    public void setIDFakulteta(int IDFakulteta) {
        this.IDFakulteta = IDFakulteta;
    }

    @Override
    public String toString() {
        return "Student{" +
                "brojIndeksa=" + brojIndeksa +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", status='" + status + '\'' +
                ", godinaUpisa='" + godinaUpisa + '\'' +
                ", IDFakulteta='" + IDFakulteta + '\'' +
                '}';
    }
}