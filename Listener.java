import java.util.Scanner; 

public class Listener {
    private LinkedChain<Object> newQueue;
    public void printMenu(){
        System.out.println("===== Menu ======");
        System.out.println("1. New Queue");
        System.out.println("2. Add Song");
        System.out.println("3. Add Podcast");
        System.out.println("4. Insertat Position");
        System.out.println("5. Remove Item");
        System.out.println("6. View Queue");
        System.out.println("7. Play Next");
        System.out.println("8. Quit");
}
public LinkedChain<Object> queue(){
    LinkedChain<Object> queue = new LinkedChain<>();
    return queue;
}

public void menu(){
    printMenu();

    }
}
public static void main(String[] args) {
    Listener listener = new Listener();
    listener.printMenu();
}
}
