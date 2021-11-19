import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class BingoController {

    private final String[] mainMenuItems = {"Exit",
            "Play bingo",
            "Set number separator",
            "Create a bingo card",
            "List existing cards",
            "Set bingo card size"};

    /* TODO
          complete constants attached to mainMenuItems
     */
    private final String OPTION_EXIT = "0";
    private final String OPTION_PLAY = "1";
    private final String OPTION_SEPARATOR = "2";
    private final String OPTION_CREATE_CARD = "3";
    private final String OPTION_LIST_CARDS = "4";
    private final String OPTION_SIZE = "5";

    /* TODO
          complete default size of rows / columns as specified in the Defaults class
          create an arraylist of BingoCard cards
          include getters and setters for row / column sizes
     */
    private int currentRowSize = Defaults.DEFAULT_NUMBER_OF_ROWS;
    private int currentColumnSize = Defaults.DEFAULT_NUMBER_OF_COLUMNS;


    /* TODO
          create an ArrayList of BingoCard cards
     */

    //implement code here
    ArrayList<BingoCard> bingoCards = new ArrayList<>();

    /* TODO
          implement getters and setters for currentRowSize / currentColumnSize
     */
    public int getCurrentRowSize() {
        /* TODO
              change the return from 0 to the appropriate return
     */
        return currentRowSize;
    }

    public void setCurrentRowSize(int currentRowSize) {
        /* TODO
             implement code here
     */
        if (currentRowSize > 0)
            this.currentRowSize = currentRowSize;
    }

    public int getCurrentColumnSize() {
        /* TODO
              change the return from 0 to the appropriate return
     */
        return currentColumnSize;
    }

    public void setCurrentColumnSize(int currentColumnSize) {
        /* TODO
             implement code here
     */
        if (currentColumnSize > 0)
            this.currentColumnSize = currentColumnSize;
    }

    /* TODO
          add a new BingoCard card
     */
    public void addNewCard(BingoCard card) {
        bingoCards.add(card);
    }

    /* TODO
          include an appropriate message to the the number of rows as well as the number of columns
     */
    public void setSize() {
        setCurrentRowSize(parseInt(Toolkit.getInputForMessage(
                "Enter the number of rows for the card").trim()));
        setCurrentColumnSize(parseInt(Toolkit.getInputForMessage(
                "Enter the number of columns for the card").trim()));
        System.out.printf("The bingo card size is set to %d rows X %d columns%n",
                getCurrentRowSize(),
                getCurrentColumnSize());
    }

    /* TODO
           ensure that the correct amount of numbers are entered
           print a message that shows the numbers entered using Toolkit.printArray(numbers)
           create, setCardNumbers and add the card as a BingoCard
     */
    public void createCard() {
        /* TODO
              calculate how many numbers are required to be entered based on the number or rows / columns
         */
        int numbersRequired = getCurrentRowSize() * getCurrentColumnSize();

        String[] numbers;
        String input;

        boolean correctAmountOfNumbersEntered;

        do {
            input = Toolkit.getInputForMessage(
                            String.format("Enter %d numbers for your card (separated by " +
                                    "'%s')", numbersRequired, Defaults.getNumberSeparator()))
                    .trim();
            input = input.replace(Defaults.getNumberSeparator(), ",");
            input = input.replace(" ", "");
            numbers = input.split(",");
            /* TODO
                    verify if the correctAmountOfNumbersEntered is true or false dependant on the numbersRequired calculation
            */  //changes according to calculation inserted here
            correctAmountOfNumbersEntered = false;
            /* TODO
                verify whether the numbers entered is not correct by printing an appropriate message
                verify against the expected output files
            */
            if (numbers.length == numbersRequired) {
                correctAmountOfNumbersEntered = true;
            } else
                System.out.printf("Try again: you entered %d numbers instead of %d%n", numbers.length, numbersRequired);
        } while (!correctAmountOfNumbersEntered);

        /* TODO
              print an appropriate message using ToolKit.printArray() to show the numbers entered
         */
        System.out.println("You entered");
        System.out.print(Toolkit.printArray(numbers));
        System.out.println(); //insert code here
        /* TODO
              create new BingoCard
         */
        BingoCard Card = new BingoCard(getCurrentRowSize(), getCurrentColumnSize());
        /* TODO
              setCardNumbers for the new card
         */
        Card.setCardNumbers(numbers);
        /* TODO
              add the card to the ArrayList
         */
        addNewCard(Card);
    }

    /* TODO
         this method goes with printCardAsGrid() seen below
         when option 4 is chosen to list existing cards it prints each card accordingly
         for example, it should show the following
         Card 0 numbers:
         1  2
         3  4 (check with expected output files)
    */
    public void listCards() {
        /* TODO
              insert code here to find all cards to be printed accordingly
         */
        /* TODO
              call printCardAsGrid() method here, Hint: use getCardNumbers() when getting cards
         */
        for (int i = 0; i < bingoCards.size(); i++) {
            System.out.printf("Card %2d numbers:%n", i);
            printCardAsGrid(bingoCards.get(i).getCardNumbers());
        }
    }

    /* TODO
          this is for option 4, list existing cards where all the cards are printed as a grid
          of rows / columns, so numbers 3 4 5 6 will be printed as follows:
          3  4
          5  6
          it is a follow on method from listCards() and ensures that the grid structure is printed
     */
    public void printCardAsGrid(String numbers) {
        //insert code here to print numbers as a grid
        numbers = numbers.replace(Defaults.getNumberSeparator(), ",");
        String[] numberArray = numbers.trim().split(",");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberArray.length; i++) {
            sb.append(String.format("%2s", numberArray[i]));
            if ((i % getCurrentColumnSize() == getCurrentColumnSize() - 1) || i == numberArray.length - 1) {
                sb.append("%n");
            } else
                sb.append(Defaults.getNumberSeparator());
        }
        System.out.printf(sb.toString());
    }

    /* TODO
          use Toolkit.getInputForMessage to enter a new separator
          print a message what the new separator is
     */
    public void setSeparator() {
        String sep = Toolkit.getInputForMessage("Enter the new separator");
        /* TODO
              make use of setNumberSeparator() and getNumberSeparator()
         */
        Defaults.setNumberSeparator(sep);
        System.out.printf("Separator is '%s'%n", Defaults.getNumberSeparator());
    }

    /* TODO
         reset all BingoCards using resetMarked (to false)
     */
    public void resetAllCards() {
        for (int i = 0; i < bingoCards.size(); i++)
            bingoCards.get(i).resetMarked();
    }

    /* TODO
          mark off a number that was called when it equals one of the numbers on the BingoCard
     */
    public void markNumbers(int number) {
        //insert code here
        for (int i = 0; i < bingoCards.size(); i++) {
            System.out.printf("Checking card %d for %d%n", i, number);
            bingoCards.get(i).markNumber(number);
        }
    }

    /* TODO
         make use of isWinner() to determine who the winner is
         the method should return the index of who the winner is
    */
    public int getWinnerId() {
        //insert code here
        for (int i = 0; i < bingoCards.size(); i++)
            if (bingoCards.get(i).isWinner())
                return i;
        return Defaults.NO_WINNER;
    }

    /* TODO
          please take note that the game will not end until there is a winning sequence
     */
    public void play() {
        System.out.println("Eyes down, look in!");
        resetAllCards();

        boolean weHaveAWinner;
        do {
            markNumbers(parseInt(
                    Toolkit.getInputForMessage("Enter the next number")
                            .trim()));

            int winnerID = getWinnerId();
            weHaveAWinner = winnerID != Defaults.NO_WINNER;
            if (weHaveAWinner)
                System.out.printf("And the winner is card %d%n", winnerID);
        } while (!weHaveAWinner);
    }

    public String getMenu(String[] menuItems) {
        /* TODO
            change this method so it prints a proper numbered menu
            analyse the correct format from the ExpectedOutput files
            menuText is returned
        */
        StringBuilder menuText = new StringBuilder();
        for (int i = 0; i < menuItems.length; i++) {
            menuText.append(String.format("%2d: %s%n", i, menuItems[i]));
        }
        //insert code here
        return menuText.toString();
    }

    /* TODO
          complete the menu using switch to call the appropriate method calls
     */
    public void run() {
        boolean finished = false;
        do {
            switch (Toolkit.getInputForMessage(getMenu(mainMenuItems)).trim()) {
                //insert code here
                case (OPTION_EXIT): {
                    finished = true;
                    break;
                }
                case (OPTION_PLAY): {
                    play();
                    break;
                }
                case (OPTION_SEPARATOR): {
                    setSeparator();
                    break;
                }
                case (OPTION_CREATE_CARD): {
                    createCard();
                    break;
                }
                case (OPTION_LIST_CARDS): {
                    listCards();
                    break;
                }
                case (OPTION_SIZE): {
                    setSize();
                    break;
                }
                default:
                    break;
            }
        } while (!finished);
    }
}
