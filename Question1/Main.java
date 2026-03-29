package Question1;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Enter the input string:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] result = solve(input);
        
        System.out.println(Arrays.toString(result));
    }

 public static String[] solve(String input) {
        HashMap<String, Integer> monthMap = new HashMap<>();
        String[] months = {"january", "february", "march", "april", "may", "june", 
                           "july", "august", "september", "october", "november", "december"};
        for (int i = 0; i < months.length; i++) {
            monthMap.put(months[i], i + 1);
        }

        Stack<String> rawStack = new Stack<>();
        String[] words = input.split(" ");
        for (int i = 0; i < words.length; i++) {
            rawStack.push(words[i]);
        }

        int pairCount = words.length / 2;
        String[][] dataMatrix = new String[pairCount][2];
        for (int i = 0; i < pairCount; i++) {
            String month = rawStack.pop();
            String name = rawStack.pop();
            dataMatrix[i][0] = name;
            dataMatrix[i][1] = month;
        }

        for (int i = 0; i < pairCount - 1; i++) {
            for (int j = 0; j < pairCount - i - 1; j++) {
                int currentMonthVal = monthMap.get(dataMatrix[j][1].toLowerCase());
                int nextMonthVal = monthMap.get(dataMatrix[j + 1][1].toLowerCase());

                if (currentMonthVal > nextMonthVal) {
                    String[] temp = dataMatrix[j];
                    dataMatrix[j] = dataMatrix[j + 1];
                    dataMatrix[j + 1] = temp;
                }
            }
        }

        Stack<String> resultStack = new Stack<>();
        for (int i = 0; i < pairCount; i++) {
            resultStack.push(dataMatrix[i][0]);
            resultStack.push(dataMatrix[i][1]);
        }

        String[] output = new String[resultStack.size()];
        for (int i = 0; i < output.length; i++) {
            output[i] = resultStack.get(i);
        }
        return output;
    }
}