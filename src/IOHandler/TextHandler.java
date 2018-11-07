package IOHandler;

/**
 * This class handles the majority of our
 * text functions so that when a GUI is implemented
 * there will not be a lot of hunting the code to find
 * System.out.print calls - any text that appears once or twice
 * can just be referenced with the print or printLine function
 */

public class TextHandler {
    
    public void printWelcome() {
        System.out.println();
        System.out.println("╱▔╲╱▔▔▔▔╲╱▔╲");
        System.out.println("▏ ╱  ▂ ▂ ╲ ▕");
        System.out.println("╲╳▏ ┏▅┐ ┏▅┐╳╱ ");
        System.out.println("  ▏╭╰━╯╰━┻━━╮");
        System.out.println("╱▔▏▕     ▕▔▔▔▏");
        System.out.println("▏ ▏▕      ╲▂╱▏");
        System.out.println("▏ ▏ ╲   ▕╲ ┃▕╱▏");
        System.out.println("▏ ╲  ▔▔▔▔▔▔▔▔▔▏   Welcome to MuttLab!");
        System.out.println("▏ ▏╲▂▂▂▂▂▂▂▂▂╱");
        System.out.println("▏ ▏        ▕");
        System.out.println("▏ ▏(textart4u.blogspot.com)");
        System.out.println();
        System.out.println("MuttLab is an amazing new, matrix calculator.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
    }
    
     public void printQuitMessage()
       {     
            System.out.println("Thank you for using MuttLab");
            System.out.println();
            System.out.println("╱▔╲╱▔▔▔▔╲╱▔╲");
            System.out.println("▏ ╱  ▂ ▂ ╲ ▕");
            System.out.println("╲╳▏ ┏▅┐ ┏▅┐╳╱");
            System.out.println("  ▏╭╰━╯╰━┻━━╮");
            System.out.println("╱▔▏▕     ▕▔▔▔▏");
            System.out.println("▏ ▏▕      ╲▂╱▏");
            System.out.println("▏ ▏ ╲   ▕╲ ┃▕╱▏");
            System.out.println("▏ ╲  ▔▔▔▔▔▔▔▔▔▏     hee, hee, hee!");
            System.out.println("▏ ▏╲▂▂▂▂▂▂▂▂▂╱");
            System.out.println("▏ ▏        ▕");
            System.out.println("▏ ▏        ▕");
        }
     
     public void printInvalidInput()
     {
         System.out.println("Invalid Matrix or Scalar");
     }
     
     public void printInvalidOp()
     {
         System.out.println("Matrices not compatible");
     }
     
     public void printInvalidAmount()
     {
         System.out.println("Not enough matrices for operation");
     }
     
     public void printInvalidArg()
     {
         System.out.println("Invalid argument(s) for function");
     }
          
     public void print(Object obj)
     {
         System.out.print(obj);
     }
     
     public void printLine(Object obj)
     {
         System.out.println(obj);
     }
     
}
