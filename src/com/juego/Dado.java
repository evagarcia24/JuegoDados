package com.juego;

public class Dado {
	private int caras;

	public Dado(int caras) {
		this.caras = caras;
	}

	public int tirar() {
		return (int) (Math.random() * caras) + 1;
	}

	public int getCaras() {
		return caras;
	}

}
