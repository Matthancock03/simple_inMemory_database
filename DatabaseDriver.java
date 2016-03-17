import java.util.*;

class DatabaseDriver {
  public static void main(String[] args) {
    SimpleDatabase database = new SimpleDatabase();
    System.out.println("\nPlease enter a command. You may enter '-HELP' at any time\nfor a list of available commands. Type 'END' to leave system.\n");
    Scanner in = new Scanner(System.in);
    while(in.hasNext()){
      String temp = in.next();
      switch (temp) {
        case "SET":
          database.set(in.next(), Integer.parseInt(in.next()));
          break;
        case "GET":
          database.get(in.next());
          break;
        case "UNSET":
          database.unSet(in.next());
          break;
        case "NUMEQUALTO":
          database.numEqualTo(in.nextInt());
          break;
        case "BEGIN":
          database.beginTransaction();
          break;
        case "ROLLBACK":
          database.rollbackTransaction();
          break;
        case "COMMIT":
          database.commitTransaction();
          break;
        case "-HELP":
          database.printHelp();
          break;
        case "END":
          System.out.println("Thank you for using my database system. Good bye.");
          System.exit(0);
        default:
          System.out.println("\nInvalid Command");
      }
    }
  }
}
