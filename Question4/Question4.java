package Question4;
public class Question4 {
 public static void main(String[] args) {
        Dvd movie = new Dvd("Baby be Mine", 1982, 50000, 4);
        Magazine mag = new Magazine("Nintendo Power #82", 1997, 25000, "Nintendo", 36);
        Vinyl record = new Vinyl("Song of The Wind", 1967, 350000, 12);

        movie.getDescription();
        System.out.println();
        mag.getDescription();
        System.out.println();
        record.getDescription();
    }
}

class Media {
    protected String title;
    protected int releaseYear;
    protected double price;

    public Media(String title, int releaseYear, double price) {
        if (title.length() < 255) {
            this.title = title;
        } else {
            this.title = "Invalid Title";
        }

        if (releaseYear > 1800 && releaseYear < 2026) {
            this.releaseYear = releaseYear;
        } else {
            this.releaseYear = 0;
        }

        this.price = price;
    }

    public void getDescription() {
        System.out.println("Title: " + title);
        System.out.println("releaseYear: " + releaseYear);
        System.out.println("Price: " + (int)price);
    }
}

class Dvd extends Media {
    private double runtime;
    public Dvd(String title, int releaseYear, double price, double runtime) {
        super(title, releaseYear, price);
        if (runtime < 720) {
            this.runtime = runtime;
        } else {
            this.runtime = 0;
        }
    }

    @Override
    public void getDescription() {
        super.getDescription();
        System.out.println("Runtime: " + (int)runtime + " minutes");
    }
}

class Magazine extends Media {
    private String author;
    private int numPages;

    public Magazine(String title, int releaseYear, double price, String author, int numPages) {
        super(title, releaseYear, price);
        if (author.length() < 50) {
            this.author = author;
        } else {
            this.author = "Unknown";
        }
        this.numPages = numPages;
    }

    @Override
    public void getDescription() {
        super.getDescription();
        System.out.println("Author: " + author);
        System.out.println("Number of Pages: " + numPages);
    }
}

class Vinyl extends Media {
    private int size;

    public Vinyl(String title, int releaseYear, double price, int size) {
        super(title, releaseYear, price);
        if (size <= 12) {
            this.size = size;
        } else {
            this.size = 0;
        }
    }

    @Override
    public void getDescription() {
        super.getDescription();
        System.out.println("Size in inches: " + size);
    }
}
