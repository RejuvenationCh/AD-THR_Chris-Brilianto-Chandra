package Question3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Question3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Food> menuList = new ArrayList<>();
        
        while (true) {
            System.out.println("--- Fiery Wok Menu System ---");
            System.out.println("1. Add Food");
            System.out.println("2. Add Regular Menu");
            System.out.println("3. Add Special Menu");
            System.out.println("4. View All Menus");
            System.out.println("5. Exit");
            System.out.print("Choice: ");
            
            int choice = sc.nextInt();
            sc.nextLine(); 

            if (choice == 5) break;
            if (choice == 4) {
                for (Food f : menuList) f.getInfo();
                continue;
            }

            System.out.print("Enter Name: ");
            String name = sc.nextLine();
            
            System.out.print("Enter Base Price: ");
            int price = sc.nextInt();

            if (choice == 1) {
                menuList.add(new Food(name, price));
            } else if (choice == 2) {
                menuList.add(new RegularMenu(name, price));
            } else if (choice == 3) {
                menuList.add(new SpecialMenu(name, price));
            }
        }
        sc.close();
    }
}

class Food {
    protected String name;
    protected int basePrice;
    protected final int labourCost = 5000;

    public Food(String name, int basePrice) {
        this.name = name;
        this.basePrice = (basePrice < 1000000) ? basePrice : 1000000;
    }

    public int calcPrice() {
        return this.basePrice + labourCost;
    }

    public void getInfo() {
        System.out.println("Name: " + this.name);
        System.out.println("Price: " + this.calcPrice());
    }
}

class RegularMenu extends Food {
    public RegularMenu(String name, int basePrice) {
        super(name, basePrice);
    }

    @Override
    public int calcPrice() {
        return super.calcPrice() + 10000;
    }
}

class SpecialMenu extends Food {
    public SpecialMenu(String name, int basePrice) {
        super(name, basePrice);
    }

    @Override
    public int calcPrice() {
        return super.calcPrice() + 20000;
    }
}

