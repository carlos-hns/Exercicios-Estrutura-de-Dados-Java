package huxley;

import java.util.Arrays;
import java.util.Scanner;

public class ProblemOrdenacaoSelecao {

    public static void main(String[] args) {
        Scanner entryPoint = new Scanner(System.in);

        int numbersQuantity = entryPoint.nextInt();
        int numberOfImpressions = entryPoint.nextInt();
        int[] numbers = new int[numbersQuantity];

        getNumbers(numbers, entryPoint);
        sortInIncreasingOrderSelection(numbers, numberOfImpressions);
    }

    public static void getNumbers(int[] numbers, Scanner scanner) {
        for(int i = 0; i < numbers.length; i++) {
            numbers[i] = scanner.nextInt();
        }
    }

    public static void sortInIncreasingOrderSelection(int[] vector, int numberOfImpressions) {
        int currentImpressionNumber = 0;
        int vectorSize = vector.length;

        for (int i=0; i < vectorSize; i++) {
            int currentNumberToOrder = vector[i];
            int lowestNumberIndex = i;
            int nextNumberOfSearch = i + 1;
            for (int j = nextNumberOfSearch; j < vectorSize; j++) {
                if(vector[j] < vector[lowestNumberIndex]) {
                    lowestNumberIndex = j;
                }
            }
            vector[i] = vector[lowestNumberIndex];
            vector[lowestNumberIndex] = currentNumberToOrder;
            if (currentImpressionNumber < numberOfImpressions) {
                System.out.println(Arrays.toString(vector));
                currentImpressionNumber++;
            }
        }
    }
}
