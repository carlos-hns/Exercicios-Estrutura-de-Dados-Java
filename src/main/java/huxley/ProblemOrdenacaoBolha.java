package huxley;

import java.util.Arrays;
import java.util.Scanner;

public class ProblemOrdenacaoBolha {

    public static void main(String[] args) {
        Scanner entryPoint = new Scanner(System.in);

        int numbersQuantity = entryPoint.nextInt();
        int numberOfImpressions = entryPoint.nextInt();
        int[] numbers = new int[numbersQuantity];

        getNumbers(numbers, entryPoint);
        sortInIncreasingOrderBubble(numbers, numberOfImpressions);
    }

    public static void getNumbers(int[] numbers, Scanner scanner) {
        for(int i = 0; i < numbers.length; i++) {
            numbers[i] = scanner.nextInt();
        }
    }

    public static void sortInIncreasingOrderBubble(int[] vector, int numberOfImpressions) {
        int vectorSize = vector.length;
        int currentImpressionNumber = 0;
        for (int i = 0; i < vectorSize; i++) {
            int currentOrderedItens = i;
            for (int j = 0; j < vectorSize - 1 - currentOrderedItens; j++) {
                if(vector[j] > vector[j + 1]) {
                    int currentItemCache = vector[j];
                    vector[j] = vector[j + 1];
                    vector[j + 1] = currentItemCache;
                }
            }
            if (currentImpressionNumber < numberOfImpressions) {
                System.out.println(Arrays.toString(vector));
                currentImpressionNumber++;
            }
        }
    }
}
