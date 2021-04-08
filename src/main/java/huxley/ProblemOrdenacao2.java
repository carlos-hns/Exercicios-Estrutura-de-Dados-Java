package huxley;

import java.util.Scanner;

public class ProblemOrdenacao2 {
    public static void main(String[] args) {
        Scanner entryPoint = new Scanner(System.in);
        int quantityOfArrays = entryPoint.nextInt();
        entryPoint.nextLine();
        for (int currentQuantity = 0; currentQuantity < quantityOfArrays; currentQuantity++) {
            String line = entryPoint.nextLine();
            String[] numbersInString = convertStringInArrayOfNumbersInString(line);
            int[] numbers = convertVectorOfStringToVectorOfInt(numbersInString);

            quickSort(numbers, 0, numbers.length - 1);
            System.out.println(getArrayStringToPrint(numbers, 0, numbers.length - 1));
        }
    }

    public static void quickSort(int[] vector, int start, int end) {
        if(hasMoreElements(start, end)) {
            int pivotIndex = particiona(vector, start, end);
            quickSort(vector, start, pivotIndex - 1);
            quickSort(vector, pivotIndex + 1, end);
        }
    }

    public static int particiona(int[] vector, int start, int end) {
        int pivot = vector[end];
        int smallerItemsThanPivotIndexController = start - 1;
        for (int largerItemsThanPivotIndexController = start; largerItemsThanPivotIndexController < end; largerItemsThanPivotIndexController++) {
            if(vector[largerItemsThanPivotIndexController] <= pivot) {
                smallerItemsThanPivotIndexController++;
                exchangeNumbers(vector, smallerItemsThanPivotIndexController, largerItemsThanPivotIndexController);
            }
        }
        exchangeNumbers(vector, smallerItemsThanPivotIndexController + 1, end);
        return smallerItemsThanPivotIndexController + 1;
    }

    public static void exchangeNumbers(int[] vector, int source, int destination) {
        int itemHoldedFromSource = vector[source];
        vector[source] = vector[destination];
        vector[destination] = itemHoldedFromSource;
    }

    public static boolean hasMoreElements(int start, int end) {
        return start < end;
    }

    public static int[] convertVectorOfStringToVectorOfInt(String[] vector) {
        int[] newVector = new int[vector.length];
        for(int i = 0; i  < vector.length; i++){
            newVector[i] = Integer.parseInt(vector[i]);
        }
        return newVector;
    }

    public static String[] convertStringInArrayOfNumbersInString(String numbers) {
        return  numbers.split(" ");
    }

    public static String getArrayStringToPrint(int[] vector, int start, int end) {
        String text = "";
        for (int i = start; i < end; i++) {
            text += vector[i] + " ";
        }
        text += vector[end];
        return text;
    }
}
