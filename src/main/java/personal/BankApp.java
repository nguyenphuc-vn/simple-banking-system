package personal;

import java.util.Scanner;
import java.util.logging.Logger;

public class BankApp {
    private static final Logger logger = Logger.getLogger(BankApp.class.getName());
    private final static Scanner scanner = new Scanner(System.in);
    private static Engine engine;

    public static void main(String[] args) {
        engine = new EngineSystem();
        printUI();
    }

    private static void printUI() {
        System.out.println("1. Create an account" +
                "\n2. Log into account " +
                "\n0. Exit ");
        int choose = letThemChoose();
        mainAction(choose);
    }
    private static int letThemChoose(){
        try{
            return scanner.nextInt();
        }catch (NumberFormatException ex){
            logger.info("Error from user input wrong type");
        }
        return 0;
    }
    private static void mainAction(int choose) {
        switch (choose){
            case 1: engine.createCard();
            printUI();
            break;
            case 2: accInfo();
            break;
            case 0: exit();
        }
    }

    private static void exit() {
        System.out.println("Bye!");
        System.exit(0);
    }

    private static void accInfo() {
        System.out.println("Enter your card number:");
        scanner.nextLine();
        String numberInput = scanner.nextLine();
        System.out.println("Enter your PIN:");
        String PINInput = scanner.nextLine();
        User user = engine.login(numberInput,PINInput);
        if(user == null){
            System.out.println("Wrong card number or PIN!");
            printUI();
        }
        printInfoUI(user);
    }

    private static void printInfoUI(User user) {
        System.out.println("You have successfully logged in!");
        System.out.println("1. Balance" +
                "\n2. Log out" +
                "\n0. Exit");
        int choose = letThemChoose();
        infoAction(choose,user);
    }

    private static void infoAction(int choose,User user) {

        switch (choose){
            case 1: engine.getInfo(user);
            printInfoUI(user);
            break;
            case 2: logout();
            break;
            case 0: exit();
        }
    }

    private static void logout() {
        System.out.println("You have successfully logged out!");
        printUI();
    }


}
