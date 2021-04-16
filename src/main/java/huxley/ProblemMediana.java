package huxley;

import java.util.Scanner;

public class ProblemMediana {

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
        int max = vector[0];
        int min = vector[0];

        for (int i = 0; i < vector.length; i++) {
            if(vector[i] > max) max = vector[i];
            if(vector[i] < min) min = vector[i];
        }

        int medianExpected = (max + min) / 2;
        int pivotIndex = 0;
        int currentABSMedian = Integer.MAX_VALUE;

        for (int i = 0; i < vector.length; i++) {
            int absMedian = Math.abs(vector[i] - medianExpected);
            if(absMedian < currentABSMedian) {
                currentABSMedian = absMedian;
                pivotIndex = i;
            }
        }
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
