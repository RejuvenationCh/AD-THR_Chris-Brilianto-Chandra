package Question2;
import java.util.*;

public class Question2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    
        System.out.print("Input Stand Owner: ");
        String standOwner = scanner.nextLine();
        
        System.out.print("Input Suspects: ");
        String suspectsInput = scanner.nextLine();

        process(standOwner, suspectsInput);

        scanner.close();
    }

    public static void process(String standOwner, String suspectsInput) {
        Stand currentStand;

        if (standOwner.equalsIgnoreCase("Jotaro")) currentStand = new JotaroStand();
        else if (standOwner.equalsIgnoreCase("Okuyasu")) currentStand = new OkuyasuStand();
        else if (standOwner.equalsIgnoreCase("Koichi")) currentStand = new KoichiStand();
        else if (standOwner.equalsIgnoreCase("Rohan")) currentStand = new RohanStand();
        else currentStand = new JosukeStand();

        Queue<String> suspectQueue = new LinkedList<>();
        String[] suspectList = suspectsInput.split(" ");
        for (int i = 0; i < suspectList.length; i++) {
            suspectQueue.add(suspectList[i]);
        }

        Stack<String> arrestedStack = new Stack<>();
        while (!suspectQueue.isEmpty()) {
            String person = suspectQueue.poll();
            if (currentStand.check(person)) {
                arrestedStack.push(person);
            }
        }

        if (arrestedStack.isEmpty()) {
            System.out.println(currentStand.ownerName + " exposed no one.");
        } else {
            System.out.println(currentStand.ownerName + " exposed someone!");
        }

        String[] finalResult = new String[arrestedStack.size()];
        for (int i = 0; i < finalResult.length; i++) {
            finalResult[i] = arrestedStack.get(i);
        }
        System.out.println("Arrested : " + Arrays.toString(finalResult));
    }

    static abstract class Stand {
        String ownerName;
        public Stand(String ownerName) { this.ownerName = ownerName; }
        public abstract boolean check(String name);
    }

    static class JotaroStand extends Stand {
        public JotaroStand() { super("Jotaro"); }
        @Override
        public boolean check(String name) { return name.length() <= 3; }
    }

    static class OkuyasuStand extends Stand {
        public OkuyasuStand() { super("Okuyasu"); }
        @Override
        public boolean check(String name) {
            String lower = name.toLowerCase();
            for (int i = 0; i < lower.length() - 1; i++) {
                if (lower.charAt(i) == lower.charAt(i + 1)) return true;
            }
            return false;
        }
    }

    static class KoichiStand extends Stand {
        public KoichiStand() { super("Koichi"); }
        @Override
        public boolean check(String name) {
            int count = 0;
            String vowels = "aeiouAEIOU";
            for (int i = 0; i < name.length(); i++) {
                if (vowels.indexOf(name.charAt(i)) != -1) count++;
            }
            return count >= 3;
        }
    }

    static class RohanStand extends Stand {
        public RohanStand() { super("Rohan"); }
        @Override
        public boolean check(String name) {
            String shortened = name.toLowerCase();
            String reversed = new StringBuilder(shortened).reverse().toString();
            return shortened.equals(reversed) && shortened.length() > 1;
        }
    }

    static class JosukeStand extends Stand {
        public JosukeStand() { super("Josuke"); }
        @Override
        public boolean check(String name) { return name.toLowerCase().startsWith("k"); }
    }
}