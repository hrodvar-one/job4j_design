package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 9);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 8);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Tetrahedron");
    }

    @Test
    void whenNumberOfVerticesIsGreaterThan0() {
        Box box = new Box(4, 8);
        int result = box.getNumberOfVertices();
        assertThat(result).isGreaterThan(0);
    }

    @Test
    void whenNumberOfVerticesIsEqualTo8() {
        Box box = new Box(8, 9);
        int result = box.getNumberOfVertices();
        assertThat(result).isEqualTo(8);
    }

    @Test
    void whenBoxIsExist() {
        Box box = new Box(0, 10);
        boolean result = box.isExist();
        assertThat(result).isTrue();
    }

    @Test
    void whenBoxIsNotExist() {
        Box box = new Box(-2, 7);
        boolean result = box.isExist();
        assertThat(result).isFalse();
    }

    @Test
    void whenBoxAreaEquals43Dot30() {
        Box box = new Box(4, 5);
        double result = box.getArea();
        assertThat(result).isEqualTo(43.30d, withPrecision(0.01d));
    }

    @Test
    void whenBoxAreaEquals294() {
        Box box = new Box(8, 7);
        double result = box.getArea();
        assertThat(result).isEqualTo(294d, withPrecision(0.1d));
    }
}