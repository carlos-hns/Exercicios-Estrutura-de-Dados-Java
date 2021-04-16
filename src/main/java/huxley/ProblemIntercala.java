package huxley;

import java.util.Scanner;

public class ProblemIntercala {

    public static void main(String[] args) {
        Scanner entryPoint = new Scanner(System.in);
        int[] firstVector = convertVectorOfStringToVectorOfInt(convertStringInArrayOfNumbersInString(entryPoint.nextLine()));
        int[] secondVector = convertVectorOfStringToVectorOfInt(convertStringInArrayOfNumbersInString(entryPoint.nextLine()));

        if(!(verificarSeArrayEstaOrdenado(firstVector) && verificarSeArrayEstaOrdenado(secondVector))) System.out.println(-1);
        else {
            int[] interleavedArray = interleave(firstVector, secondVector);
            System.out.println(getArrayStringToPrint(interleavedArray, 0, interleavedArray.length - 1));
        }
    }

    private static boolean verificarSeArrayEstaOrdenado(int[] vector) {
        int numerosMenoresQueOPosterior = 0;
        for (int i = 0; i < vector.length - 1; i ++) {
            if(vector[i] <= vector[i+1]) numerosMenoresQueOPosterior++;
        }
        return numerosMenoresQueOPosterior == vector.length - 1;
    }

    public static int[] interleave(int[] vector1, int[] vector2) {
        int[] interleavedArray = new int[vector1.length + vector2.length];

        int j = 0, k = 0;
        for (int i = 0; i < interleavedArray.length; i++) {

            if(j == vector1.length) {
                interleavedArray[i] = vector2[k++];
            } else if (k == vector2.length) {
                interleavedArray[i] = vector1[j++];
            } else {
                if(vector1[j] <= vector2[k]) {
                    interleavedArray[i] = vector1[j++];
                } else {
                    interleavedArray[i] = vector2[k++];
                }
            }
        }
        return interleavedArray;
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
