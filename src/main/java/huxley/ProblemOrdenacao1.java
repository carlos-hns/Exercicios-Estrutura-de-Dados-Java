package huxley;

import java.util.Scanner;

public class ProblemOrdenacao1 {
    public static void main(String[] args) {
        Scanner entryPoint = new Scanner(System.in);
        int quantityOfArrays = entryPoint.nextInt();
        entryPoint.nextLine();
        for(int currentQuantity = 0; currentQuantity < quantityOfArrays; currentQuantity++) {
            String line = entryPoint.nextLine();
            String[] numbersInString = convertStringInArrayOfNumbersInString(line);
            int[] numbers = convertVectorOfStringToVectorOfInt(numbersInString);

            mergeSort(numbers, 0, numbers.length - 1);
            System.out.println(getArrayStringToPrint(numbers, 0, numbers.length - 1));
        }
    }

    public static void mergeSort(int[] vector, int start, int end) {
        if(hasMoreElements(start, end)) {
            int middle = (int) Math.floor((start + end)/ 2);
            mergeSort(vector, start, middle);
            mergeSort(vector, middle + 1, end);
            intercala(vector, start, middle, end);
        }
    }

    public static void intercala(int[] vector, int start, int middle, int end) {
        int sizeStartToMiddle = middle - start + 1;
        int sizeMiddleToEnd = end - middle;

        int[] vectorStartToMid = new int[sizeStartToMiddle + 1];
        int[] vectorMidToEnd = new int[sizeMiddleToEnd + 1];

        for (int currentIndex = 0; currentIndex < sizeStartToMiddle; currentIndex++) {
            vectorStartToMid[currentIndex] = vector[start + currentIndex];
        }

        for(int currentIndex = 0; currentIndex < sizeMiddleToEnd; currentIndex++) {
            vectorMidToEnd[currentIndex] = vector[middle + currentIndex + 1];
        }

        vectorStartToMid[sizeStartToMiddle] = Integer.MAX_VALUE;
        vectorMidToEnd[sizeMiddleToEnd] = Integer.MAX_VALUE;

        int currentStartToMiddleIndex = 0;
        int currentMiddleToEndIndex = 0;

        for (int currentVectorIndex = start; currentVectorIndex <= end; currentVectorIndex++) {
            if(vectorStartToMid[currentStartToMiddleIndex] <= vectorMidToEnd[currentMiddleToEndIndex]) {
                vector[currentVectorIndex] = vectorStartToMid[currentStartToMiddleIndex];
                currentStartToMiddleIndex++;
            } else {
                vector[currentVectorIndex] = vectorMidToEnd[currentMiddleToEndIndex];
                currentMiddleToEndIndex++;
            }
        }
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
