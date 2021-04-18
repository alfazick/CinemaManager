
import java.util.Scanner;

public class Cinema {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rowNum = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int colNum = scanner.nextInt();
        
        String[][] activeCinema = createCinema(rowNum,colNum);
        showView(activeCinema);
        
        
        boolean main = true;
        
        while (main) {
            int key = showMenu();
            
            switch (key) {
                case 1:
                    showView(activeCinema);
                    break;
                case 2:
                    reserveTicket(activeCinema);
                    break;
                case 3:
                    showStatistics(activeCinema);
                    break;
                case 0:
                    main = false;
                    break;
            }
        }
        
        
        
        
        
        //calculateProfit(rowNum,colNum);
        
    }
    
    public static int showMenu() {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        System.out.println("");
        
        Scanner scanner = new Scanner(System.in);
        int response = scanner.nextInt();
        return response;
        
    }
    
    public static void showStatistics(String[][] cinemaSeats) {
        int numSoldTicket = 0;
        double percentSoldTicket = 0;
        int currentIncome = 0;
        int totalIncomePossible = 0;
        
        int rows = cinemaSeats.length;
        int columns = cinemaSeats[0].length;
        double totalTickets = 0;
        
        
        for (int row = 0; row < rows; row ++ ) {
            for (int column = 0; column < columns; column++) {
                if (cinemaSeats[row][column] == "S" || cinemaSeats[row][column] == "B") {
                    // define price for possible seat 
                    int ticketPrice = 10;
                    if (((cinemaSeats[0].length * cinemaSeats.length) > 60) && row >= (cinemaSeats.length / 2)) {
                        ticketPrice = 8;
                    }
                    
                    totalIncomePossible += ticketPrice;
                    totalTickets += 1;
                    if (cinemaSeats[row][column] == "B") {
                        numSoldTicket += 1;
                        currentIncome += ticketPrice;
                    }
                } 
            }
        }
        
        //Number of purchased tickets: 2
        //Percentage: 5.56%
        //Current income: $20
        //Total income: $360
        
        percentSoldTicket = (numSoldTicket / totalTickets) * 100;
        
        
        System.out.printf("Number of purchased tickets: %d", numSoldTicket);
        System.out.printf("%nPercentage: %3.2f%%", percentSoldTicket);
        System.out.printf("%nCurrent income: $%d", currentIncome);
        System.out.printf("%nTotal income: $%d", totalIncomePossible);
        System.out.println();
        
    }
    
    
    public static void reserveTicket(String[][] cinemaSeats ) {
        
        Scanner scanner = new Scanner(System.in);
        
        boolean picked = false;
        
        while (!picked) {
            System.out.println("Enter a row number:");
            int rowNumseat = scanner.nextInt();
        
            System.out.println("Enter a seat number in that row:");
            int colNumseat = scanner.nextInt();
            
            if (rowNumseat > 0 && rowNumseat < cinemaSeats.length && colNumseat > 0 && colNumseat < cinemaSeats[0].length) {
                if (cinemaSeats[rowNumseat][colNumseat] == "S") {
                    picked = true;
                    int ticketPrice = 10;
                    if (((cinemaSeats[0].length * cinemaSeats.length) > 60) && rowNumseat >= (cinemaSeats.length / 2)) {
                        ticketPrice = 8;
                    }
                    
                    System.out.println("Ticket price: $" + ticketPrice);
                    cinemaSeats[rowNumseat][colNumseat] = "B";
                    return; 
                    
                }
                else {
                    System.out.println("That ticket has already been purchased!");
                }
            }
            
            else {
                System.out.println("Wrong input!");
            }    
                
            
        }
        
        
    }
    
    public static void calculateProfit (int rowNum, int colNum) {
        
        if (rowNum * colNum <= 60) {
            System.out.println("Total income:");
            System.out.println("$" + rowNum*colNum*10);
        }
        else {
            int front = rowNum / 2;
            int back = rowNum - front;
            int total = colNum * front * 10 + colNum * back * 8;
            System.out.println("Total income:");
            System.out.println("$" + total);
            
        }
        
    }

    public static String[][] createCinema(int rows, int columns) {
        // Write your code here
        //int rows = 7;
        //int columns = 8;
        
        String [][] matrix = new String[rows+1][columns+1];
        
        
        for (int row = 0; row <= rows; row ++ ) {
            for (int column = 0; column <= columns; column++) {
                if (row == 0 && column == 0) {
                    matrix[row][column] = " ";
                    continue;
                }
                if (row == 0) {
                    matrix[row][column] = column + "";
                    continue;
                }
                else if (column == 0) {
                    matrix[row][column] = row + "";
                    continue;
                }
                else {
                    matrix[row][column] = "S";
                }   
            }
        }
        return matrix;
    }
    
    public static void showView(String[][] matrix) {
        System.out.println("Cinema:");   
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}