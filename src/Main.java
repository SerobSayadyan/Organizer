import organizer.Organizer;

public class Main {
    public static void main(String[] args) {
        long start = System.nanoTime();
        Organizer.organize();
        long end = System.nanoTime();
//        System.out.println("\n\nruning time" + (end - start));
    }
}