package Question5;
import java.util.Scanner;
import java.util.Stack;

public class Question5 {
  public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Stack<Book> library = new Stack<>();
        library.push(new Book("Why Black Moves First", "Wesley So", 2025));
        library.push(new GeneralBook("Inside Black Mesa", "Dr. Isaac Kleiner", 1997, "Documentary"));
        library.push(new ChildrenBook("Got Science?", "Rachel Dawes", 2015, 5, true));
        library.push(new GeneralBook("Dune", "Frank Herbert", 1965, "Sci-Fi"));
        library.push(new ChildrenBook("The Cat in the Hat", "Dr. Seuss", 1957, 6, true));

        boolean running = true;
        while (running) {
            System.out.println("--- Alexandria Library CLI ---");
            System.out.println("1. Add New Book");
            System.out.println("2. View All Books");
            System.out.println("3. Delete Specific Book");
            System.out.println("4. Exit");
            System.out.print("Choice: ");
            int menu = sc.nextInt();
            sc.nextLine(); 

            switch (menu) {
                case 1:
                    System.out.println("Type (1. General, 2. Children, 3. Basic): ");
                    int type = sc.nextInt(); sc.nextLine();
                    
                    System.out.print("Title: "); String t = sc.nextLine();
                    System.out.print("Author: "); String a = sc.nextLine();
                    System.out.print("Year: "); int y = sc.nextInt(); sc.nextLine();

                    if (type == 1) {
                        System.out.print("Genre: "); String g = sc.nextLine();
                        library.push(new GeneralBook(t, a, y, g));
                    } else if (type == 2) {
                        System.out.print("Min Age: "); int age = sc.nextInt();
                        System.out.print("Has Visual (true/false): "); boolean vis = sc.nextBoolean();
                        library.push(new ChildrenBook(t, a, y, age, vis));
                    } else {
                        library.push(new Book(t, a, y));
                    }
                    System.out.println("Book added successfully.");
                    break;

                case 2:
                    System.out.println("\n--- Current Book List ---");
                    for (int i = 0; i < library.size(); i++) {
                        System.out.println("Index [" + i + "]");
                        library.get(i).getInfo();
                        System.out.println("-------------------------");
                    }
                    break;

                case 3:
                    System.out.print("Enter book index to delete: ");
                    int deleteIdx = sc.nextInt();
                    if (deleteIdx >= 0 && deleteIdx < library.size()) {
                        library.remove(deleteIdx);
                        System.out.println("Book deleted.");
                    } else {
                        System.out.println("Invalid index.");
                    }
                    break;

                case 4:
                    System.out.println("Closing system...");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
        sc.close();
    }
}

class Book {
    protected String title;
    protected String author;
    protected int year;

    public Book(String title, String author, int year) {
        if (title.length() < 255) { 
            this.title = title; 
        } else { 
            this.title = "Default Title"; 
        }
        if (author.length() < 50) { 
            this.author = author; } else { 
                this.author = "Unknown Author"; 
            }
        if (year > 1800 && year < 2026) { 
            this.year = year; } 
            else {  
            this.year = 0; }
    }

    public void getInfo() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        if (year == 0) { 
            System.out.println("Year of Publication: Invalid Year"); 
        }
        else { 
            System.out.println("Year of Publication: " + year); 
        }
    }
}

class GeneralBook extends Book {
    private String genre;
    public GeneralBook(String title, String author, int year, String genre) {
        super(title, author, year);
        if (genre.length() <= 30) { 
            this.genre = genre; 
        } else { 
            this.genre = "General"; 
        }

    }
    @Override
    public void getInfo() {
        super.getInfo();
        System.out.println("Genre: " + genre);
    }
}

class ChildrenBook extends Book {
    private int minAge;
    private boolean hasVisualisation;
    public ChildrenBook(String title, String author, int year, int minAge, boolean hasVisualisation) {
        super(title, author, year);
        if (minAge > 3 && minAge < 12) { this.minAge = minAge; } else { this.minAge = 0; }
        this.hasVisualisation = hasVisualisation;
    }
    @Override
    public void getInfo() {
        super.getInfo();
        if (minAge == 0) { 
            System.out.println("Minimum Age: Invalid"); 
        } 
        else { 
            System.out.println("Minimum Age: " + minAge); 
        }
        if (hasVisualisation) { 
            System.out.println("Has Visualisation: Yes"); 
        } 
        else { 
            System.out.println("Has Visualisation: No"); 
        }
    }
}
