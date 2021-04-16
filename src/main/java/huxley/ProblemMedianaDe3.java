package huxley;

import java.util.Scanner;

public class ProblemMedianaDe3 {
    public static void main(String[] args) {
        Scanner entryPoint = new Scanner(System.in);
        int[] vector = convertVectorOfStringToVectorOfInt(convertStringInArrayOfNumbersInString(entryPoint.nextLine()));
        int pivotIndex = findPivotIndex(vector);
        System.out.println(vector[pivotIndex]);
        exchangeNumbers(vector, pivotIndex, vector.length - 1);
        particiona(vector, 0, vector.length - 1);
        System.out.println(getArrayStringToPrint(vector, 0, vector.length - 1));
    }

    public static int findPivotIndex(int[] vector) {
        int startIndex = 0;
        int endIndex = vector.length -  1;
        int middleIndex = 0;

        if (vector.length % 2 == 0) middleIndex = (vector.length / 2) - 1;
        else middleIndex  = vector.length / 2;

        int start = vector[startIndex];
        int end = vector[endIndex];
        int middle = vector[middleIndex];
        int pivotIndex = 0;

        if (start > end) {
            if (start > middle) {
                if (end > middle) {
                    pivotIndex = endIndex;
                } else {
                    pivotIndex = middleIndex;
                }
            } else {
                pivotIndex = startIndex;
            }
        } else {
            if(end > middle) {
                if(start > middle) {
                    pivotIndex = startIndex;
                } else{
                    pivotIndex = middleIndex;
                }
            } else {
                pivotIndex = endIndex;
            }
        }
        // Middle element after the exchange
        return pivotIndex;
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


    public static void exchangeNumbers(int[] vector, int sourceIndex, int destinationIndex) {
        int itemHoldedFromSource = vector[sourceIndex];
        vector[sourceIndex] = vector[destinationIndex];
        vector[destinationIndex] = itemHoldedFromSource;
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
