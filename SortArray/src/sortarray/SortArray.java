/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sortarray;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author alenk
 */
public class SortArray {

    private static final int[] arrayFirst = new int[10];

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        initArray();
        try ( FileWriter fw = new FileWriter("arraySort.txt")) {
            fw.write(Arrays.toString(arrayFirst));
            // скопируем массив, результат сортировок
            int[] arraySort = new int[arrayFirst.length];
            arraySort = bubbleSort(arrayFirst);
            fw.write("\nArray bubble sort\n");
            fw.write(Arrays.toString(arraySort));

            arraySort = selectionSort(arrayFirst);
            fw.write("\nSelectionSort\n");
            fw.write(Arrays.toString(arraySort));

            arraySort = insertSort(arrayFirst);
            fw.write("\nInsertSort\n");
            fw.write(Arrays.toString(arraySort));

            arraySort = shuttleSort(arrayFirst);
            fw.write("\nShuttleSort\n");
            fw.write(Arrays.toString(arraySort));
            
            arraySort = shellSort(arrayFirst);
            fw.write("\nShell Sort\n");
            fw.write(Arrays.toString(arraySort));
            

        } catch (IOException ex) {
            System.out.println("File wrong!");
        }
    }

    private static void initArray() {
        Random rand = new Random();
        for (int i = 0; i < arrayFirst.length; i++) {
            arrayFirst[i] = rand.nextInt(20) + 1;
        }
    }

    private static int[] bubbleSort(int[] arraySort) {
        int temp = 0;
        for (int i = arraySort.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arraySort[j] > arraySort[j + 1]) {
                    temp = arraySort[j + 1];
                    arraySort[j + 1] = arraySort[j];
                    arraySort[j] = temp;
                }
            }
        }
        return arraySort;
    }

    private static int[] selectionSort(int[] arraySort) {
        int temp = 0;
        for (int i = 0; i < arraySort.length; i++) {
            for (int j = i; j < arraySort.length; j++) {
                if (arraySort[j] < arraySort[i]) {
                    temp = arraySort[i];
                    arraySort[i] = arraySort[j];
                    arraySort[j] = temp;
                }
            }
        }
        return arraySort;
    }

    private static int[] insertSort(int[] arraySort) {
        int temp = 0, j = 0;
        for (int i = 1; i < arraySort.length; i++) {
            temp = arraySort[i];
            j = i - 1;
            while (j >= 0 && temp < arraySort[j]) {
                arraySort[j + 1] = arraySort[j];
                j--;
            }
            arraySort[j + 1] = temp;
        }
        return arraySort;
    }

    private static int[] shuttleSort(int[] arraySort) {
        int temp = 0;
        for (int i = 1; i < arraySort.length; i++) {
            if (arraySort[i] < arraySort[i - 1]) {
                temp = arraySort[i];
                arraySort[i] = arraySort[i - 1];
                arraySort[i - 1] = temp;
                for (int j = i - 1; (j - 1) >= 0; j--) {
                    if (arraySort[j] < arraySort[j - 1]) {
                        temp = arraySort[j];
                        arraySort[j] = arraySort[j - 1];
                        arraySort[j- 1] = temp;
                    } else {
                        break;
                    }
                }
            }
        }
        return arraySort;
    }

    private static int[] shellSort(int[] arraySort) {
        for (int step = arraySort.length / 2; step > 0; step /= 2) {
            for (int i = step; i < arraySort.length; i++) {
                for (int j = i - step; j >= 0 && arraySort[j] > arraySort[j + step] ; j -= step) {
                    int x = arraySort[j];
                    arraySort[j] = arraySort[j + step];
                    arraySort[j + step] = x;
                }
            }
        }
        return arraySort;
    }

}
