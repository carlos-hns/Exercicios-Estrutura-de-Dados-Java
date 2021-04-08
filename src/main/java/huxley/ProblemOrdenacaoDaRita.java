package huxley;

import java.util.Scanner;

public class ProblemOrdenacaoDaRita {

    public static void main(String[] args){
        Scanner entryPoint = new Scanner(System.in);
        while(entryPoint.hasNext()){
            int firstNumber = entryPoint.nextInt();
            int secondNumber = entryPoint.nextInt();
            int thirdNumber = entryPoint.nextInt();
            int[] numbers = {firstNumber, secondNumber, thirdNumber};

            if(firstNumber < 0 || secondNumber < 0 || thirdNumber < 0) {
                System.out.println("Ordenacao cancelada.");
            } else {
                if(firstNumber % 2 != 0) sortInIncreasingOrder(numbers);
                else sortInDecreasingOrder(numbers);
                for(int number : numbers){
                    System.out.println(number);
                }
            }
        }
    }

    public static void sortInIncreasingOrder(int[] vector) {
        for(int unsortedElementIndex=1; unsortedElementIndex < vector.length; unsortedElementIndex++){
            int unsortedElement = vector[unsortedElementIndex];
            int currentSortedElementIndex  = unsortedElementIndex - 1;
            while (currentSortedElementIndex >= 0 && vector[currentSortedElementIndex] > unsortedElement) {
                vector[currentSortedElementIndex + 1] = vector[currentSortedElementIndex];
                currentSortedElementIndex--;
            }
            vector[currentSortedElementIndex + 1] = unsortedElement;
        }
    }

    public static void sortInDecreasingOrder(int[] vector) {
        for(int unsortedElementIndex=1; unsortedElementIndex < vector.length; unsortedElementIndex++){
            int unsortedElement = vector[unsortedElementIndex];
            int currentSortedElementIndex  = unsortedElementIndex - 1;
            while (currentSortedElementIndex >= 0 && unsortedElement > vector[currentSortedElementIndex]) {
                vector[currentSortedElementIndex + 1] = vector[currentSortedElementIndex];
                currentSortedElementIndex--;
            }
            vector[currentSortedElementIndex + 1] = unsortedElement;
        }
    }
}
