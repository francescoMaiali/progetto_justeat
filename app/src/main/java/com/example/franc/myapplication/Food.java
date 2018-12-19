package com.example.franc.myapplication;

import org.json.JSONException;
import org.json.JSONObject;

public class Food  {
    private String nome;
    private int quantita = 0;
    private float prezzo;


    public Food(String name, float price) {

        nome = name;
        prezzo = price;
    }

    public Food(JSONObject jsonFood)throws JSONException{
        nome = jsonFood.getString("name");
        prezzo= Float.parseFloat(jsonFood.getString("price"));
    }

    public void setName(String nome) {
        this.nome = nome;
    }

    public String getName() {
        return nome;
    }

    public void setPrezzo(float price) {
        this.prezzo = prezzo;
    }

    public float getPrezzo() {
        return prezzo;
    }



    public int getQuantita() {
        return quantita;
    }
    public void increaseQuantita() {
        this.quantita++;
    }

    public void decreaseQuantita() {
        if(quantita == 0) return;
        this.quantita--;
    }

}
