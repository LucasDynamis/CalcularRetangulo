package com.example.calcularretangulo;

public class Retangulo {
    public static double calcularArea(double largura, double altura) {
        return largura * altura;
    }

    public static double calcularPerimetro(double largura, double altura) {
        return 2 * (largura + altura);
    }

    public static double calcularDiagonal(double largura, double altura) {
        return Math.sqrt(largura * largura + altura * altura);
    }
}
