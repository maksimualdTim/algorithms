import app.*;

import java.lang.reflect.*;

public class Main {
    public static void main(String[] args) {
//        lab1();
        lab2();
    }

    private static void lab1() {
        int size = 10;
        LowArray lowArr = new LowArray(size);

        lowArr.fill(size);
        lowArr.push(25);
        lowArr.push(15);
        lowArr.push(39);
        lowArr.print();
        consoleDevider();

        lowArr.setElem(11, 17);
        lowArr.print();
        consoleDevider();

        lowArr.delete(17);
        lowArr.print();
        consoleDevider();

        System.out.println(lowArr.find(6));
        consoleDevider();

        System.out.println(lowArr.getElem(10));
        consoleDevider();

        lowArr.push(90);
        lowArr.insert(97);
        lowArr.push(105);
        lowArr.push(4565);
        lowArr.print();
        consoleDevider();

        System.out.println(lowArr.findBinary(25));
        consoleDevider();

        lowArr.deleteBinary(97);
        lowArr.print();
    }

    private static void lab2() {
        SortArray sortArr = new SortArray(100000);
        sortArr.fill();

        Long start = System.currentTimeMillis();
        sortArr.bubbleSort();
        Long end = System.currentTimeMillis();
        sortArr.restore();
        System.out.println("Bubble sort(time spend): " + (end - start) + " ms");

        start = System.currentTimeMillis();
        sortArr.SelectionSort();
        end = System.currentTimeMillis();
        sortArr.restore();
        System.out.println("Selection sort(time spend): " + (end - start) + " ms");

        start = System.currentTimeMillis();
        sortArr.InsertionSort();
        end = System.currentTimeMillis();
        sortArr.restore();
        System.out.println("Insertion sort(time spend): " + (end - start) + " ms");

        start = System.currentTimeMillis();
        sortArr.shellSort();
        end = System.currentTimeMillis();
        sortArr.restore();
        System.out.println("Shell sort(time spend): " + (end - start) + " ms");

        start = System.currentTimeMillis();
        sortArr.quickSort(0, sortArr.getnElems() - 1);
        end = System.currentTimeMillis();
        sortArr.restore();
        System.out.println("Quick sort(time spend): " + (end - start) + " ms");

    }

    private void lab3() {
    }

    private void lab4() {
    }

    private void lab5() {
    }

    private void lab6() {
    }

    private void lab7() {
    }

    private void lab8() {
    }

    private void lab9() {
    }

    private void lab10() {
    }

    private static void consoleDevider() {
        System.out.println("--------------------------------------------");
    }
}