package com.example.franc.myapplication;

public class Food {
    private String name;
    private String prezzo;
    private String quantita;

     public Food(String name, String prezzo, String quantita)  {
         this.name = name;
         this.prezzo = prezzo;
         this.quantita = quantita;
     }

    public String getQuantita() {
        return quantita;
    }

    public String getName() {
        return name;
    }

    public String getPrezzo() {
        return prezzo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrezzo(String prezzo) {
        this.prezzo = prezzo;
    }

    public void increaseQuantita(String quantita) {
        this.quantita = quantita;
    }
}
