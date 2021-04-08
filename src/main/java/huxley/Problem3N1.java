package huxley;

import java.util.Scanner;

public class Problem3N1 {

    private static final int firstNumber = 0;
    private static final int secondNumber = 1;

    public static void main(String[] args){
        Scanner entryPoint = new Scanner(System.in);
        while(entryPoint.hasNext()){
            int initialCycleNumber = entryPoint.nextInt();
            int finalCycleNumber = entryPoint.nextInt();

            if (initialCycleNumber < 1 || finalCycleNumber < 1 || initialCycleNumber > 1000000 || finalCycleNumber > 1000000) break;

            int biggestNumber = 0;
            int smallestNumber = 0;

            if (initialCycleNumber > finalCycleNumber){
                biggestNumber = initialCycleNumber;
                smallestNumber = finalCycleNumber;
            } else {
                biggestNumber = finalCycleNumber;
                smallestNumber = initialCycleNumber;
            }

            int finalCycleSize = 0;
            for (int currentCycle=smallestNumber; currentCycle <= biggestNumber; currentCycle++){
                int currentCycleValue = 0;
                int initialNumber = currentCycle;

                while(initialNumber != 1){
                    if(initialNumber % 2 == 0) initialNumber /= 2;
                    else initialNumber = (3 * initialNumber) + 1;
                    currentCycleValue++;
                }
                if(finalCycleSize < currentCycleValue) finalCycleSize = currentCycleValue;
            }
            System.out.printf("%d %d %d\n", initialCycleNumber, finalCycleNumber, finalCycleSize + 1);
        }
    }
}
