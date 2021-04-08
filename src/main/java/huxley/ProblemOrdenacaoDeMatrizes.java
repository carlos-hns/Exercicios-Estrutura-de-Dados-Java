package huxley;

import java.util.Scanner;

public class ProblemOrdenacaoDeMatrizes {
    public static void main(String[] args){

        Scanner entryPoint = new Scanner(System.in);

        int rows = entryPoint.nextInt();
        int columns = entryPoint.nextInt();
        int[] numbers = new int[rows * columns];

        int nextEmptySpaceIndex = 0;
     /*   while(entryPoint.hasNext()) {
            numbers[nextEmptySpaceIndex] = entryPoint.nextInt();
            nextEmptySpaceIndex++;
        }
*/

        numbers[0] = entryPoint.nextInt();
        numbers[1] = entryPoint.nextInt();
        numbers[2] = entryPoint.nextInt();
        numbers[3] = entryPoint.nextInt();

        sortInIncreasingOrder(numbers);
        printMatrix(numbers, columns);
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

    public static void printMatrix(int[] vector, int columns) {
        int itemPosition = 1;
        for(int i=0; i < vector.length; i++){
            if(itemPosition == columns) {
                System.out.println(vector[i]);
                itemPosition = 0;
            } else {
                System.out.printf("%d ", vector[i]);
            }
            itemPosition++;
        }
    }
}
