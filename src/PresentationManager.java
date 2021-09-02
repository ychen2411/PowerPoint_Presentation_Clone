/**
 * The PresentationManager class tests the Slide class,
 * SlideListNode class, SlideList class and the methods
 * within them.
 * @author Yanhui Chen
 *      e-mail: yanhui.chen@stonybrook.edu
 *
 *
 */
import java.util.Scanner;
public class PresentationManager {
    public static void main(String[] args) {
        SlideList presentation = new SlideList();
        Scanner input = new Scanner(System.in);
        System.out.print("Welcome to Presentation Manager!\n\n");
        String[] menu = {
                "Please select an option:", "F) Move cursor forward",
                "B) Move Cursor backward", "D) Display cursor slide",
                "E) Edit cursor slide", "P) Print presentation summary",
                "A) Append new slide to tail", "I) Insert new slide before cursor",
                "R) Remove slide at cursor", "H) Reset cursor to head",
                "Q) Quit"
        };
        for (int i = 0; i < menu.length; i++) {
            System.out.print(menu[i] + "\n");
        }
        String choice = "";
        String validChoice = "";

        do {
            try {
                System.out.print("\nSelect a menu option: ");
                choice = input.next();
                validChoice = choice.toUpperCase();

                switch (validChoice) {
                    case "F": {
                        presentation.cursorForward();
                        System.out.print("\nThe cursor moved forward to slide \"" +
                                presentation.getCursorSlide().getTitle()+ "\".\n");
                        break;
                    }
                    case "B": {
                        presentation.cursorBackward();
                        System.out.print("\nThe cursor moved backward to slide \"" +
                                presentation.getCursorSlide().getTitle() + "\".\n");
                        break;
                    }
                    case "D": {
                        presentation.getCursorSlide().printSlide();
                        break;
                    }
                    case "E": {
                        System.out.print("Edit title, duration, or bullets? (t/d/b) ");
                        String in = input.next();
                        if (in.equalsIgnoreCase("t")) {
                            System.out.print("Delete or edit? (d/e) ");
                            String dOrETitle = input.next();
                            if (dOrETitle.equalsIgnoreCase("e")) {
                                System.out.print("Enter the new title: ");
                                input.nextLine();
                                String newTitle = input.nextLine();
                                presentation.getCursorSlide().setTitle(newTitle);
                            } else if (dOrETitle.equalsIgnoreCase("d")){
                                presentation.getCursorSlide().setTitle("");
                                System.out.print("\nTitle has been deleted\n");
                            }
                        } else if (in.equalsIgnoreCase("d")) {
                            System.out.print("Delete or edit? (d/e) ");
                            String dOrEDuration = input.next();
                            if (dOrEDuration.equalsIgnoreCase("e")) {
                                System.out.print("Enter the new duration: ");
                                double newDuration = input.nextDouble();
                                presentation.getCursorSlide().setDuration(newDuration);
                            } else if (dOrEDuration.equalsIgnoreCase("d")) {
                                presentation.getCursorSlide().deleteDuration();
                                System.out.print("\nDuration has been deleted\n");
                            }
                        } else if (in.equalsIgnoreCase("b")) {
                            System.out.print("Bullet index: ");
                            int bullIndex = input.nextInt();
                            System.out.print("Delete or edit? (d/e) ");
                            String dOrE = input.next();
                            if (dOrE.equalsIgnoreCase("d")) {
                                presentation.getCursorSlide().deleteBullet(bullIndex);
                            } else if (dOrE.equalsIgnoreCase("e")){
                                System.out.print("\nBullet " + bullIndex + ": ");
                                input.nextLine();
                                String s = input.nextLine();
                                presentation.getCursorSlide().editBullet(bullIndex, s);
                            }
                        }
                        break;
                    }
                    case "P": {
                        presentation.printSlidesSummary();
                        break;
                    }
                    case "A": {
                        Slide newSlide = new Slide();
                        System.out.print("\nEnter the slide title: ");
                        input.nextLine();
                        String title = input.nextLine();
                        newSlide.setTitle(title);
                        System.out.print("Enter the slide duration: ");
                        double duration = input.nextDouble();
                        newSlide.setDuration(duration);
                        int count = 0;
                        String add ="";
                        while (!add.equalsIgnoreCase("n")) {
                            System.out.print("Bullets " + (count+1) + ": ");
                            input.nextLine();
                            String info = input.nextLine();
                            newSlide.setBullet(info,(count+1));
                            count++;
                            if (count == 5) {
                                System.out.print("No more bullets allowed." +
                                        " Slide is full.");
                                break;
                            }
                            System.out.print("Add another bullet point? (y/n) ");
                            add = input.next();

                        }
                        presentation.appendToTail(newSlide);
                        System.out.print("\nSlide \"" + newSlide.getTitle() +
                                "\"" + " added to presentation.\n");
                        break;
                    }
                    case "I": {
                        Slide newSlide = new Slide();
                        System.out.print("\nEnter the slide title: ");
                        input.nextLine();
                        String title = input.nextLine();
                        newSlide.setTitle(title);
                        System.out.print("Enter the slide duration: ");
                        double duration = input.nextDouble();
                        newSlide.setDuration(duration);
                        int count = 0;
                        String add ="";
                        while (!add.equalsIgnoreCase("n")) {
                            System.out.print("Bullets " + (count+1) + ": ");
                            input.nextLine();
                            String info = input.nextLine();
                            newSlide.setBullet(info,(count+1));
                            count++;
                            if (count == 5) {
                                System.out.print("No more bullets allowed." +
                                        " Slide is full.");
                                break;
                            }
                            System.out.print("Add another bullet point? (y/n) ");
                            add = input.next();

                        }
                        presentation.insertBeforeCursor(newSlide);
                        System.out.print("\nSlide \"" + newSlide.getTitle() +
                                "\"" + " added to presentation.\n");
                        break;
                    }
                    case "R": {
                        String title = presentation.getCursorSlide().getTitle();
                        presentation.removeCursor();
                        System.out.print("Slide \"" + title + "\"" +
                                " has been removed\n");
                        break;
                    }
                    case "H": {
                        presentation.resetCursorToHead();
                        System.out.print("Cursor has been reset to head.\n");
                        break;
                    }
                    case "Q": {
                        System.out.print("\nProgram terminates normally.");
                        System.exit(0);
                    }
                }
            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            catch (EndOfListException e) {
                System.out.println(e.getMessage());
            }
        } while (!validChoice.equalsIgnoreCase("Q"));
    }
}
