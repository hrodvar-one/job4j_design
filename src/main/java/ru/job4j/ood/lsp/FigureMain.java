package ru.job4j.ood.lsp;

public class FigureMain {
    public static void main(String[] args) {
        Rectangle rect = new Square();
        rect.setWidth(5);
        rect.setHeight(10);
        System.out.println("Area: " + rect.getArea());
    }
}
