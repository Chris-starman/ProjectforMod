
import javax.swing.*;
import java.io.BufferedReader;
 import java.io.DataInputStream;
import java.io.FileInputStream;
 import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel{
    /**The terminal's menu options, always called before and after an option*/
    static void basicMenu() {
        System.out.println("---------------------------------\n");
        System.out.println("1. Enter in a new movie\n");
        System.out.println("2. Reserve a ticket + seat\n");
        System.out.println("3. Get a ticket (random seat)\n");
        System.out.println("4. Add movies/customers via .txt file\n");
        System.out.println("5. Remove movies\n");
        System.out.println("6. Remove a customer\n");
        System.out.println("7. Print all movies and customer info\n");
        System.out.println("-1. Quit\n");
    }



    static void main(String[] args) {
        //The various scanners for all of the terminal options
        Scanner myObj = new Scanner(System.in);
        Scanner myObj2 = new Scanner(System.in);
        Scanner myObj3 = new Scanner(System.in);
        Scanner myObj4 = new Scanner(System.in);
        Scanner myObj5 = new Scanner(System.in);
        Scanner myObj6 = new Scanner(System.in);
        Scanner myObj7 = new Scanner(System.in);

        /**<p>This arraylist is used throughout the project and stores movie and customer info</p>*/
        ArrayList<Movie> list = new ArrayList();
        int menuOption = 777;
        int Idee=0;
        int Iddd=0;
        int zeekeeper=0;
        int rando=0;
        final int[] movieCounter = {-1};
        int answer=0;
        //This is called to store the user's position in the arraylist
        final int[] Yunaka = { 0 };
        //Various variables used to store the user's options for the terminal
        double taxx = 0;
        String DX=" ",DK,daMovie=" ", firstAnswer = "yes", secondAnswer = "yes",zeanswer=" ";
        boolean seatChecker = true, validEntry=false, emptyChecker=false;


//Defines the pop-up menu

        Frame peace= new Frame("DMS Project");
        //peace.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MenuBar menuBar = new MenuBar();
        peace.setMenuBar(menuBar);

        // Create a "File" menu and its options
        Menu fileMenu = new Menu("Options");

        MenuItem newItem = new MenuItem("Put movie in the system");
        MenuItem ApplyItem = new MenuItem("Register for a movie seat of your choice");
        MenuItem randomItem = new MenuItem("Register for a random seat for a movie");
        MenuItem TxtItem = new MenuItem("Add Movies/Seats FROM a database");
        MenuItem ToItem = new MenuItem("Add Movies/Seats TO a database");
        MenuItem DelItem = new MenuItem("Delete a movie");
        MenuItem LeetItem = new MenuItem("Remove a seat");
        MenuItem PrintItem = new MenuItem("Print everything in the system");
        Label instructions = new Label("Welcome. Please use the menu above to get started. Minimize and maximize the page to load in the option when selected");

        /// //////////////////////////////////////////////////
        //First Menu Option, adding a movie in
        /**
         *
         * <p>Asks the user to enter in a movie, adding it to the movie arraylist</p>
         * */

            newItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //Define everything that'll be use panel-wise
                    JPanel panel1 = new JPanel();

                    JLabel Bylabel = new JLabel("Please enter name of the movie");
                    JTextField MovieEnter = new JTextField(10);
                    MovieEnter.setToolTipText("Type in the movie's name here. Press enter whenever you're done.");
                    JTextField SeatEnter = new JTextField(10);
                    JLabel Byseat = new JLabel("Please Enter in how many seats the theater houses");
                    SeatEnter.setToolTipText("Type in how many seats the theater has here. Must be more than 0");
                    JTextField TaxEnter = new JTextField(10);
                    JLabel ByTax = new JLabel("\nPlease Enter in how much the tax rate is");
                    JButton wonder =new JButton("Enter movie in");
                    JLabel FYI = new JLabel("");
                    JLabel FYI2 = new JLabel("");

                    String movieman = "";
                    double lifecost = 0;
                    final int[] Pyracushion = {0};
                    //MovieEnter.setEnabled(true);
                    //SeatEnter.setEnabled(true);
                    //TaxEnter.setEnabled(true);


                    //Shows off the text field and labels, being entering in the movie
                    peace.removeAll();
                    peace.setVisible(true);
                    //peace.removeAll();
                    peace.add(panel1);
                    //Asks for the movies/seats/tax rate before asking for a button press
                    panel1.add(Bylabel);
                    panel1.add(MovieEnter);
                    panel1.add(Byseat);
                    panel1.add(SeatEnter);
                    panel1.add(ByTax);
                    panel1.add(TaxEnter);
                    panel1.add(wonder);
                    panel1.add(FYI);
                    panel1.add(FYI2);

                    /**
                     * <p>When you press this button it'll add your info to the arraylist, barring any errors</p>*/


                    //When you press the button do...
                    wonder.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            String movieman =MovieEnter.getText().trim();

                            // Checks if the parameters are empty, error occurs if it is
                            if(movieman.isEmpty()){
                                JOptionPane.showMessageDialog(null, "Hey dude, put something here", "You didn't even try", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                            //Makes sure the user enters a number
                                String input=SeatEnter.getText().trim();
                                if(input.isEmpty()){
                                    JOptionPane.showMessageDialog(null, "Hey dude, put something here", "You didn't even try", JOptionPane.ERROR_MESSAGE);
                                    return;
                                }

                                try{
                                    int Pyracushion = Integer.parseInt(input);
                                    //A number more than 0 at that
                                    if(Pyracushion>0){
                                        String input2=TaxEnter.getText().trim();
                                        if (input2.isEmpty()){
                                            JOptionPane.showMessageDialog(null, "Hey dude, put something here", "You didn't even try", JOptionPane.ERROR_MESSAGE);
                                            return;
                                        }
                                        try{
                                            double lifecost=Double.parseDouble(input2);
                                            //Stores and makes sure the tax rate is a positive, or else an error message occurs
                                            if(lifecost>0){
                                                //If you do, put it into the movie arraylist (making sure all of the seats are available and names are blank by default)
                                                int thingforthestuff=list.size();
                                                list.add(new Movie("", new int[Pyracushion], lifecost, new String[Pyracushion], new boolean[Pyracushion], new String[Pyracushion]));
                                                Arrays.fill(list.get(thingforthestuff).isFilled, true);
                                                Arrays.fill(list.get(thingforthestuff).CustomerName, " ");
                                                Arrays.fill(list.get(thingforthestuff).CustomerAge, " ");
                                                list.get(thingforthestuff).title = movieman;
                                                //Afirmation message
                                                FYI.setText("Ok, " + movieman + ", seating  " + Pyracushion + ", at  " + lifecost + " tax rate has been entered.");
                                                //Resets text so user can enter values infinitely
                                                MovieEnter.setText("");
                                                SeatEnter.setText("");
                                                TaxEnter.setText("");
                                                FYI2.setText("If you would like to add more movies just enter your text and press the button again");
                                            }
                                            //All of the various error messages in case the user enters something in wrong.
                                            // Negative tax rate inputted, non-number tax rate, negative and non-number seat numbers respectively
                                            else{
                                                JOptionPane.showMessageDialog(null, "No Government would ever give negative taxes, put a positive",
                                                        "Bruh", JOptionPane.ERROR_MESSAGE);

                                            }
                                        }catch(NumberFormatException k){
                                            JOptionPane.showMessageDialog(null, "Not a number", "C'mon man", JOptionPane.ERROR_MESSAGE);
                                        }
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(null, "You want to watch a movie in a theater with nonexistent seats? Try again",
                                                "Bruh", JOptionPane.ERROR_MESSAGE);
                                    }

                                }catch(NumberFormatException op){
                                    JOptionPane.showMessageDialog(null, "Not a number", "C'mon man", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                    });
                }
            });
        /**
         * <p>When you press this button it'll ask you what seats for available movies you want, adding them to the movie arraylist</p>*/

        /// /////////////////////////////////////////////////
        //Second option in the menu (Getting a seat choice)
        ApplyItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Defines panel things to be used
                JPanel panel2 = new JPanel();

                JLabel Yourmovie = new JLabel("Please enter name of the movie you'd like to watch");
                JTextField MovieEnter = new JTextField(10);
                MovieEnter.setToolTipText("Type in the movie's name here. Press enter whenever you're done.");
                JTextField SeatEnter = new JTextField(10);
                SeatEnter.setToolTipText("Type in the movie's name here. Press enter whenever you're done.");
                JLabel Yourseat = new JLabel("Please enter what seat number you'd like");
                JTextField AgeEnter = new JTextField(10);
                AgeEnter.setToolTipText("Type in the movie's name here. Press enter whenever you're done.");
                JLabel Yourage = new JLabel("Please enter in how old you are (C/A/S)");
                JTextField NameEnter = new JTextField(10);
                NameEnter.setToolTipText("Type in the movie's name here. Press enter whenever you're done.");
                JLabel Yourname = new JLabel("Please enter in your name");
                JButton fates= new JButton("Enter");
                JLabel availaset= new JLabel("");
                String movieman = "";
                double lifecost = 0;

                Yourmovie.setEnabled(true);
                Yourage.setEnabled(true);
                Yourseat.setEnabled(true);


                //Shows off the first text field and label, being entering in the movie
                peace.setVisible(true);
                peace.removeAll();
                peace.add(panel2);

                //Asks for the movie name/seat number/age respectively
                panel2.add(Yourmovie);
                panel2.add(MovieEnter);
                panel2.add(Yourseat);
                panel2.add(SeatEnter);
                panel2.add(Yourname);
                panel2.add(NameEnter);
                panel2.add(Yourage);
                panel2.add(AgeEnter);
                //Messages that occur when a seat is entered (has no text til something is entered)

                panel2.add(fates);
                panel2.add(availaset);


                //When you press the button do...
                /**
                 * <p>When you press this button it'll go through the arraylist and make sure your seat is available,
                 * if it is it'll add your info to the arraylist and mark your new seat as unavailable</p>*/

                fates.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //Makes sure the user enters in a number more than 0, or anything at all for that matter
                        String ginput=SeatEnter.getText().trim();
                        if(ginput.isEmpty()){
                            JOptionPane.showMessageDialog(null, "Hey dude, put something here", "You didn't even try", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        try {
                            //Makes sure the seat number is positive
                            boolean pizzapizza=true;
                           int Euniecushion=Integer.parseInt(ginput);
                           if(Euniecushion>0){
                               //Makes sure the strings are filled, error messages if not
                               String N=MovieEnter.getText().trim();
                               if(N.isEmpty()){
                                   JOptionPane.showMessageDialog(null, "Don't leave me hanging, enter something", "Hey! Listen!", JOptionPane.ERROR_MESSAGE);
                               }
                               String M=AgeEnter.getText().trim();
                               if(M.isEmpty()){
                                   JOptionPane.showMessageDialog(null, "Don't leave me hanging, enter something", "Hey! Listen!", JOptionPane.ERROR_MESSAGE);
                               }
                               //Makes sure user enters their age in c/a/s format or else error message
                               if (M.equals("A")==true || M.equals("a")==true  || M.equals("c")==true || M.equals("C")==true  || M.equals("s")==true || M.equals("S")==true) {

                                   String sena = NameEnter.getText().trim();
                                   //Checks if there's available seats/movies or not
                                   boolean fillchecker = false, anyChecker=false;
                                   Euniecushion--;
                                   //Goes through entire Movie arraylist to try and find the movie name you entered
                                   for (int g = list.size() - 1; g >= 0; --g) {
                                       if (((Movie) list.get(g)).noSeats(pizzapizza) == true) {
                                           if (((Movie) list.get(g)).title.equals(N)) {
                                               anyChecker=true;
                                               //If there is, put it in movie arraylist and display affirmation message
                                               if (list.get(g).isFilled[Euniecushion] == true) {
                                                   list.get(g).isFilled[Euniecushion] = false;
                                                   list.get(g).CustomerAge[Euniecushion] = M;
                                                   list.get(g).CustomerName[Euniecushion] = sena;
                                                   if (M.equals("c") || M.equals("C")) {
                                                       availaset.setText("Ok " + sena + ", your seat at " + N + " is seat number " + (Euniecushion + 1) +
                                                               ", you have been registered under a child ticket. Your total is $" + (list.get(g).taxRate * 8));
                                                   }
                                                   if (M.equals("a") || M.equals("A")) {
                                                       availaset.setText("Ok " + sena + ", your seat at " + N + " is seat number " + (Euniecushion + 1) +
                                                               ", you have been registered under an adult ticket. Your total is $" + (list.get(g).taxRate * 12));
                                                   }
                                                   if (M.equals("s") || M.equals("S")) {
                                                       availaset.setText("Ok " + sena + ", your seat at " + N + " is seat number " + (Euniecushion + 1) +
                                                               ", you have been registered under a senior ticket. Your total is $" + (list.get(g).taxRate * 10));
                                                   }
                                                   //Also sets the movie registration checker to true to giving the error message
                                                   fillchecker = true;
                                               }
                                           }
                                           //Various error messages in case user enters wrong info
                                       } else if (list.get(g).noSeats(pizzapizza) == false) {
                                           JOptionPane.showMessageDialog(null, "Sorry, man. That movie is full.", "Womp womp", JOptionPane.ERROR_MESSAGE);
                                       } else if (list.size() <= 0) {
                                           JOptionPane.showMessageDialog(null, "There's nothing in the system, go enter a movie in first", "Womp womp", JOptionPane.ERROR_MESSAGE);
                                       } else {
                                           JOptionPane.showMessageDialog(null, "Seems you didn't enter something in the system, go try again.", "Womp womp", JOptionPane.ERROR_MESSAGE);
                                       }
                                   }
                                   if (fillchecker == false) {
                                       JOptionPane.showMessageDialog(null, "Either that seat isn't in the system or is already taken, enter another", "You're too slow!", JOptionPane.ERROR_MESSAGE);
                                   }
                                   if (anyChecker == false) {
                                       JOptionPane.showMessageDialog(null, "That movie isn't in the system, try again", "Hey, c'mon c'mon", JOptionPane.ERROR_MESSAGE);
                                   }
                                   //sets the seat registration checker to false to loop for next time

                                   fillchecker = false;
                               }
                               else{
                                   JOptionPane.showMessageDialog(null, "You're not a fetus. Enter whether (C)hild,(A)dult,or (S)enior", "Ok, Tiki", JOptionPane.ERROR_MESSAGE);
                               }
                           }
                           else{
                               JOptionPane.showMessageDialog(null, "You can't be in a negative or nonexistent seat, it must be a whole number above 0", "You know who ELSE makes mistakes?", JOptionPane.ERROR_MESSAGE);
                           }
                        }catch(NumberFormatException Mp){
                            JOptionPane.showMessageDialog(null, "Input a number, man", "Hey! Listen!", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
            }
        });
/// //////////////////////////////////////////////////////////////////////////////////////////
        /**
         * <p>When you press this button it'll ask you for a movie and your age and if there's any available, it'll give you a random seat</p>*/
        //Option 3 Random ticket
        randomItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Defines the options given in the popup
                JPanel panel4 = new JPanel();
                JLabel Burlabel=new JLabel("Please enter what movie you want to watch here:");
                JLabel Allabel=new JLabel("Please enter what age you are (C/A/S):");
                JTextField comeonecomeall = new JTextField(10);
                comeonecomeall.setToolTipText("If there's a seat available, we will give you a random seat.");
                JTextField baby = new JTextField(10);
                baby.setToolTipText("Note: Only C/A/S will be accepted");
                JLabel Result =new JLabel("");
                JLabel cost =new JLabel("");
                JButton jackpot= new JButton("Get an assigned seat");

                //Displays them

                peace.setVisible(true);
                peace.removeAll();
                peace.add(panel4);
                //Asks user to input the movie, age and the button needed to accept both
                panel4.add(Burlabel);
                panel4.add(comeonecomeall);
                panel4.add(Allabel);
                panel4.add(baby);
                panel4.add(jackpot);
                panel4.add(Result);
                panel4.add(cost);

                //When user presses button do...
                /**
                 * <p>This is the part that goes through and checks the movie arraylist for availability, generating the random seat and
                 * adding your spot in there's any left</p>*/

                jackpot.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        boolean idk=false;
                       String chips = comeonecomeall.getText();
                       int rando=0;
                       //Makes sure user enters something
                        String DK = baby.getText();
                        if (comeonecomeall.getText().equals("") || baby.getText().equals("")) {
                            JOptionPane.showMessageDialog(null,
                                    "Enter something please", "Triforce", JOptionPane.ERROR_MESSAGE);
                        }

                        //Goes through entire Movie arraylist to try and find the movie name you entered
                        if(!(comeonecomeall.getText().equals("")) || !(baby.getText().equals(""))) {
                            for (int l = list.size() - 1; l >= 0; --l) {
                                //if it finds it then....
                                if (((Movie) list.get(l)).title.equals(chips)) {
                                    idk=true;
                                    //First makes sure that there's seats available
                                    for (int o = 0; o < list.get(l).seatNum.length; o++) {
                                        if (list.get(l).noSeats(seatChecker) == true) {
                                            //If there is then..

                                            //Loop to calculate your random ticket number, going til you get one unavailable
                                            for (int f = 0; f < 100; f--) {
                                                int index = (int) (Math.random() * list.get(l).seatNum.length);
                                                if (list.get(l).isFilled[index] == true) {
                                                    rando = index;
                                                    list.get(l).isFilled[index] = false;
                                                    f = 200;
                                                }
                                            }

                                            //Enter your age, checks if it's c/a/s
                                            for (int a = 1; a < 100; a--) {
                                                if (DK.equals("c") == true || DK.equals("C") == true
                                                        || DK.equals("a") == true || DK.equals("A") == true
                                                        || DK.equals("s") == true || DK.equals("S") == true) {
                                                    a = 200;
                                                    list.get(l).CustomerAge[rando] = DK;
                                                }
                                                //Else gives this error message and makes you try again
                                                else if (DK.equals("c") == false || DK.equals("C") == false
                                                        || DK.equals("a") == false || DK.equals("A") == false
                                                        || DK.equals("s") == false || DK.equals("S") == false || DK.equals("")) {
                                                    JOptionPane.showMessageDialog(null, "You're not a fetus, enter whether you're a (C)hild,(A)dult, or (S)enior", "Ok, Tiki", JOptionPane.ERROR_MESSAGE);
                                                    l = -10;
                                                    a=200;
                                                }
                                            }
                                            //Determines your ticket cost and what age you're registered as
                                            if (DK.equals("c") || DK.equals("C")) {
                                                cost.setText("\n That will be $" + (((1 + list.get(l).taxRate)) * 8));
                                                Result.setText("Your seat is number " + (rando + 1));
                                                list.get(l).CustomerAge[l] = "C";
                                                o = list.get(l).seatNum.length + 10;

                                            }
                                            if (DK.equals("a") || DK.equals("A")) {
                                                cost.setText("\n That will be $" + (((1 + list.get(l).taxRate)) * 12));
                                                Result.setText("Your seat is number " + (rando + 1));
                                                list.get(l).CustomerAge[l] = "A";
                                                o = list.get(l).seatNum.length + 10;

                                            }
                                            if (DK.equals("s") || DK.equals("S")) {
                                                cost.setText("\n That will be $" + (((1 + list.get(l).taxRate)) * 10));
                                                Result.setText("Your seat is number " + (rando + 1));
                                                list.get(l).CustomerAge[l] = "S";
                                                o = list.get(l).seatNum.length + 10;
                                            }

                                        }
                                        //Otherwise, error message
                                        if (list.get(l).noSeats(seatChecker) == false) {
                                            JOptionPane.showMessageDialog(null, "That movie has no more seats available, try another one", "You're too slow!", JOptionPane.ERROR_MESSAGE);
                                            l = -10;
                                            comeonecomeall.setText("");
                                            baby.setText("");
                                        }
                                        //If there's no such movie in the system produces this message
                                        if (!list.get(l).title.equals(chips) && baby.getText().equals("")) {
                                            JOptionPane.showMessageDialog(null, "Not a registered movie, try again", "Dude", JOptionPane.ERROR_MESSAGE);
                                            l = -10;
                                            comeonecomeall.setText("");
                                            baby.setText("");
                                        }
                                    }

                                }
                            }
                            if(idk==false) {
                                JOptionPane.showMessageDialog(null, "Not a registered movie, try again", "Dude", JOptionPane.ERROR_MESSAGE);
                                comeonecomeall.setText("");
                                baby.setText("");
                            }
                        }
                        idk=false;
                    }
                });
            }
        });

///    ///////////////////////////////////////////////////////////////////////////////////////////////
        /**
         * <p>When you press this button it'll ask you for database info and will add the movie arraylist info to the registered database</p>*/
        //Option 4, Adding in Movies/seats via database
        TxtItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Defines the popup options
                JPanel panel4 = new JPanel();
                JLabel Warning=new JLabel("WARNING! Please make sure your columns have:  name VARCHAR(255) default '',\n" +
                        "    tax_rate DOUBLE,\n" +
                        "    seat_number INT,\n" +
                        "    is_filled BOOLEAN,\n" +
                        "    seat_limit INT,\n" +
                        "    customer_name VARCHAR(255),\n" +
                        "    customer_age varchar(1) or it will not register");
                JLabel txtentry=new JLabel("Enter the JDBC Connection string you want to use ");
                JTextField enterslop = new JTextField(10);
                enterslop.setToolTipText("Enter your JDBC connection string here and press enter when done");
                JLabel unameentry = new JLabel("Enter the database's user name");
                JTextField unameenter = new JTextField(10);
                unameenter.setToolTipText("Put 'root' if you don't have one");
                JLabel pwentry = new JLabel("Enter the database's password");
                JTextField pwenter = new JTextField(10);
                pwenter.setToolTipText("Makes sure it's spelled correctly and case sensitive");
                JLabel schemaentry = new JLabel("Enter the name of the Schema:");
                JTextField schemanter = new JTextField(10);
                JLabel tableentry = new JLabel("Enter the name of the table that'll be used:");
                JTextField tableenter = new JTextField(10);

                JLabel Done =new JLabel("");
                JLabel sucker =new JLabel("");

                JButton enterhere = new JButton("Enter");
                //Displays the options
                peace.setVisible(true);
                peace.removeAll();
                peace.add(panel4);
                //Asks user for their database info and the comfirm button
                panel4.add(Warning);
                panel4.add(txtentry);
                panel4.add(enterslop);
                panel4.add(unameentry);
                panel4.add(unameenter);
                panel4.add(pwentry);
                panel4.add(pwenter);
                panel4.add(enterhere);
                panel4.add(pwenter);
                panel4.add(schemaentry);
                panel4.add(schemanter);
                panel4.add(tableentry);
                panel4.add(tableenter);

//Affirmation message info, blank by default
                panel4.add(enterhere);
                panel4.add(Done);
                panel4.add(sucker);

//When user presses the button do...
                /**
                 * <p>Connect to the database and transfer movie arraylist data to the linked database</p>*/
                enterhere.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //Stores the user inputs, by default it uses my personal database
                        String txt="jdbc:mysql://127.0.0.1:3306/?user=root", next="root",last="Meyou777!",almost="dmstable",there="movies";
                         txt = enterslop.getText();
                         next = unameenter.getText();
                         last = pwenter.getText();
                         almost = schemanter.getText();
                         there = tableenter.getText();
                         String table="Movie_Name";
                         int frogsyum=0;
                         String booksdelicous="";

                         //Gets all values from the data

                        String query = ("select * from "+almost+"."+there);

//Connects to the database
                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            Connection con = DriverManager.getConnection(txt, next, last);


                            // Create a statement
                            Statement st = con.createStatement();
                            st= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                            // Execute the query
                            ResultSet rs = st.executeQuery(query);
                            //sets variables to the values gotten
                            String mname="", cname="", age="";
                            int seatnum=0,limit=0,fill=1,something=0;
                            double tax=0;
                            boolean nobody=true;
                            ArrayList<Integer>filled=new ArrayList<Integer>();

                            //Base connection affirmation message

                            Done.setText("You have at least connected :)");



                            // Process the results
                            while (rs.next()) {
                                //and put into variables (putting defaults if they're blank/null to prevent crashes)
                                boolean imtired = true;
                                mname = rs.getString("name");
                                if (rs.wasNull()) {
                                    mname = "";
                                }

                                cname = rs.getString("customer_name");
                                if (rs.wasNull()) {
                                    cname = "";
                                }
                                seatnum = rs.getInt("seat_number");
                                if (rs.wasNull()) {
                                    seatnum = -1;
                                }
                                age = rs.getString("customer_age");
                                if (rs.wasNull()) {
                                    age = "";
                                }
                                //Makes sure age is in c/a/s form
                                if (age.equals("c") || age.equals("C") || age.equals("a") || age.equals("A") || age.equals("s") || age.equals("S") || age.equals("") || age.equals(" ")) {
                                    if (age.equals("") || age.equals(" ")) {
                                        age = "";
                                    }
                                } else {
                                    age = "A";
                                }
                                tax = rs.getDouble("tax_rate");
                                if (rs.wasNull()) {
                                    tax = 1;
                                }
                                limit = rs.getInt("seat_limit");
                                if (rs.wasNull()) {
                                    limit = -1;
                                }
                                fill = rs.getInt("is_filled");
                                if (fill == 0) {
                                    imtired = false;
                                } else {
                                    imtired = true;
                                }

                                //Registers the first movie
                                if (limit >= 0) {
                                    if (frogsyum == 0) {
                                        list.add(new Movie(mname, new int[limit], tax, new String[limit], new boolean[limit], new String[limit]));
                                        Arrays.fill(list.get(0).isFilled, true);
                                        Arrays.fill(list.get(0).CustomerName, " ");
                                        Arrays.fill(list.get(0).CustomerAge, " ");
                                        if (seatnum < limit && seatnum >= 0) {
                                            list.get(0).CustomerAge[seatnum] = age;
                                            list.get(0).CustomerName[seatnum] = cname;
                                            list.get(0).isFilled[seatnum] = false;
                                        }
                                        //Increments a variable to make sure it doesn't do the "first movie process" more than once
                                        frogsyum++;
                                    }
                                }

                                if (frogsyum != 0) {
                                    //Checks ahead to make sure there's no duplicate movies, if there is makes a new movie, if not, registers the info to an existing one

                                    if (rs.next()) {
                                        String warioftilt = rs.getString("name");
                                        int wariosideb = rs.getInt("seat_limit");
                                        if (rs.wasNull()) {
                                            wariosideb = 1;
                                        }
                                        double wariokicks = rs.getDouble("tax_rate");
                                        rs.previous();

                                        if (warioftilt.equals(mname) == false) {
                                            //If it isn't a duplicate then adds new movie entry

                                            list.add(new Movie(warioftilt, new int[wariosideb], wariokicks, new String[wariosideb], new boolean[wariosideb], new String[wariosideb]));
                                            for (int u = 0; u < list.size(); u++) {
                                                if (list.get(u).title.equals(warioftilt)) {
                                                    Arrays.fill(list.get(u).isFilled, true);
                                                    Arrays.fill(list.get(u).CustomerName, " ");
                                                    Arrays.fill(list.get(u).CustomerAge, " ");
                                                }
                                            }
                                        }
                                        //Otherwise adds the seat info

                                        if (warioftilt.equals(mname)) {
                                            for (int u = 0; u < list.size(); u++) {
                                                if (list.get(u).title.equals(mname)) {
                                                    if (seatnum < limit && seatnum >= 0) {

                                                        list.get(u).CustomerAge[seatnum] = age;
                                                        list.get(u).CustomerName[seatnum] = cname;
                                                        list.get(u).isFilled[seatnum] = imtired;
                                                    }
                                                }
                                            }
                                        }

                                    }
                                }
                                nobody = true;
                            }
                            //Entry affirmation message

                            sucker.setText("Your database values have been added to the local program,  negative or 'bonus' seats are ignored");

                            st.close();
                            rs.close();
                            //Errors incase the movie/database can't be found
                        } catch (ClassNotFoundException ex) {
                            throw new RuntimeException(ex);
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, "We couldn't find that directory, try again", "Fail whale", JOptionPane.ERROR_MESSAGE);
                            throw new RuntimeException(ex);
                        }
                       }
                });
            }
        });

/// //////////////////////////////////////////////////////////////////////////////////////////
        /**
         * <p>When you press this button it'll ask for database info, this time taking info from the database and adding it to the arraylist</p>*/
       // Option 5, Adding to a database
        ToItem.addActionListener(new ActionListener() {
                    @Override
                   public void actionPerformed(ActionEvent e) {
                        //Gives users fields on where to enter their table info
                        JPanel panel9 = new JPanel();
                        JLabel Warning=new JLabel("WARNING! Please make sure your columns have:  name VARCHAR(255) default '',\n" +
                                "    tax_rate DOUBLE,\n" +
                                "    seat_number INT,\n" +
                                "    is_filled BOOLEAN,\n" +
                                "    seat_limit INT,\n" +
                                "    customer_name VARCHAR(255),\n" +
                                "    customer_age varchar(1) or it will not register");
                        JLabel txtentry=new JLabel("Enter the JDBC Connection string you want to use ");
                        JTextField enterslop = new JTextField(10);
                        enterslop.setToolTipText("Enter your JDBC connection string here and press enter when done");
                        JLabel unameentry = new JLabel("Enter the database's user name");
                        JTextField unameenter = new JTextField(10);
                        unameenter.setToolTipText("Put 'root' if you don't have one");
                        JLabel pwentry = new JLabel("Enter the database's password");
                        JTextField pwenter = new JTextField(10);
                        pwenter.setToolTipText("Makes sure it's spelled correctly and case sensitive");
                        JLabel schemaentry = new JLabel("Enter the name of the Schema:");
                        JTextField schemanter = new JTextField(10);
                        JLabel tableentry = new JLabel("Enter the name of the Movie table that'll be used:");
                        JTextField tableenter = new JTextField(10);
                        JLabel seatentry = new JLabel("Enter the name of the Seat table that'll be used:");
                        JTextField seatenter = new JTextField(10);
                        JLabel custentry = new JLabel("Enter the name of the Customer table that'll be used:");
                        JTextField custenter = new JTextField(10);

                        //Finished results labels

                        JLabel Done =new JLabel("");
                        JLabel ornot =new JLabel("");

                        //Comfirm button


                        JButton enterhere = new JButton("Enter");

                        //Puts it in the GUI
                        peace.setVisible(true);
                        peace.removeAll();
                        peace.add(panel9);
                        panel9.add(Warning);
                        panel9.add(txtentry);
                        panel9.add(enterslop);
                        panel9.add(unameentry);
                        panel9.add(unameenter);
                        panel9.add(pwentry);
                        panel9.add(pwenter);
                        panel9.add(enterhere);
                        panel9.add(pwenter);
                        panel9.add(schemaentry);
                        panel9.add(schemanter);
                        panel9.add(tableentry);
                        panel9.add(tableenter);

                        //Affirmation message info, blank by default

                        panel9.add(enterhere);
                        panel9.add(Done);
                        panel9.add(ornot);

/**
 * <p>When you press this button it'll connect to the database and go through and add the info to the arraylist</p>*/
                        enterhere.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                //Gets user database info, uses my personal one by default
                                String txt="mysqlstring", next="root",last="Password",almost="dmstable",there="movies";
                                 txt = enterslop.getText();
                                 next = unameenter.getText();
                                 last = pwenter.getText();
                                 almost = schemanter.getText();
                                 there = tableenter.getText();
                                 String table="Movie_Name";
                                 String seats=seatenter.getText(),customers=custenter.getText();
                                 int praccounter=0;

                                try {
                                    // Connects
                                    Class.forName("com.mysql.cj.jdbc.Driver");
                                    Connection con = DriverManager.getConnection(txt, next, last);
                                    con.setCatalog(almost);


                                    // Create a statement
                                    Statement st = con.createStatement();
                                    Done.setText("You have at least connected :)");

                                    //Puts values in database

                                    for(int v=0;v<list.size();v++){

                                        for(int u=0;u<list.get(v).seatNum.length;u++){
                                            //Puts the values into a statement, columns must be named a certain way. This determines where it puts the val into
                                            String Value1="INSERT INTO "+almost+"."+there+
                                                    "(name,tax_rate,seat_number,is_filled,customer_name,customer_age,seat_limit) VALUES (?,?,?,?,?,?,?)";

                                            // Prepare the statement
                                            PreparedStatement myStmt = con.prepareStatement(Value1);

                                            // Set the parameter values
                                            myStmt.setString(1, list.get(v).title);
                                            myStmt.setDouble(2, list.get(v).taxRate);
                                            myStmt.setInt(3,u);
                                            myStmt.setBoolean(4,list.get(v).isFilled[u]);
                                            myStmt.setString(5,list.get(v).CustomerName[u]);
                                            myStmt.setString(6,list.get(v).CustomerAge[u]);
                                            myStmt.setInt(7,list.get(v).seatNum.length);

                                        }
                                    }
                                    ornot.setText("All values have been added to your table");
                                    st.close();
                                } catch (ClassNotFoundException ex) {
                                    throw new RuntimeException(ex);
                                } catch (SQLException ex) {
                                    JOptionPane.showMessageDialog(null, "We couldn't find that directory, try again", "Fail whale", JOptionPane.ERROR_MESSAGE);
                                    throw new RuntimeException(ex);
                                }
                               }
                        });
                    }
                });


 /////////////////////////////////////////////////////////////////////////////////////////////
        /**
         * <p>When you press this button it'll ask you for a movie to delete, deleting it when entered</p>*/
 //Option 6 delete a movie
        DelItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Info to display
                JPanel panel5 = new JPanel();
                JLabel Welcome=new JLabel("Enter the name of the movie you want to delete from the system\n");
                //JLabel del=new JLabel("Enter the directory path of the .txt file you want to use ");
                JTextField delentry = new JTextField(10);
                delentry.setToolTipText("Enter the movie name here and press enter when done");
                JLabel DDDone =new JLabel("");
              //Displays it
                peace.setVisible(true);
                peace.removeAll();
                peace.add(panel5);
                //Asks for the movie
                panel5.add(Welcome);
                //deletes it
                panel5.add(delentry);
                //Affirmation. Blank by default
                panel5.add(DDDone);


                //On press do...
/**
 * <p>Go through the arraylist and try to find the movie registered and delete it</p>*/
                delentry.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        boolean emptyChecker=false;
                        //Assuming there's anything to delete that is or else error
                        if(list.size()>0) {

                            //Asks what to delete
                            String dee= delentry.getText();

                            //Checks if it's in the system
                            // if(list.size()>=0) {
                            for (int k = list.size() - 1; k >= 0; --k) {
                                //If it is then delete
                                if (((Movie) list.get(k)).title.equals(dee)) {
                                    list.remove(k);
                                    DDDone.setText(dee+" has been removed");
                                    delentry.setText("");
                                    emptyChecker=true;
                                    movieCounter[0]--;
                                    break;
                                }
                            }
                            //If not then error message
                            if (emptyChecker=false){
                                JOptionPane.showMessageDialog(null, "There is not a movie in the system. Try again", "Whoops", JOptionPane.ERROR_MESSAGE);
                            }
                            emptyChecker=false;
                        }
                        //Error if no movies are in
                        else if(list.size()<=0){
                            JOptionPane.showMessageDialog(null, "There are no movies in the system. There's nothing to delete but the time you just wasted", "Whoops", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
            }
        });
/// //////////////////////////////////////////////////////////////////////////////////////
        /**
         * <p>When you press this button it'll ask for a movie and customer to delete, clearing their info from the system when entered</p>*/
        //Option 7, Removing an individual customer from the system
        LeetItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Defines parameters
                JPanel panel4 = new JPanel();
                JLabel Moviehere=new JLabel("Please put what movie you'd like to remove a customer from here:");
                JLabel Seathere=new JLabel("Enter the seat number you'd like to clear any customers of");
                JTextField hereMovie = new JTextField(10);
                hereMovie.setToolTipText("Enter the movie here");
                JTextField hereSeat = new JTextField(10);
                hereSeat.setToolTipText("Enter the seat here");
                JButton Enter= new JButton("Enter");
                JLabel Doubledopdone =new JLabel("");

                //Displays it
                peace.setVisible(true);
                peace.removeAll();
                peace.add(panel4);

                //Asks users for the info
                panel4.add(Moviehere);
                panel4.add(hereMovie);
                panel4.add(Seathere);
                panel4.add(hereSeat);
                //Comfirmation button
                panel4.add(Enter);
                //Affirmation message, blank by default
                panel4.add(Doubledopdone);

                //On press do...
                /**
                 * <p>Go through the movie arraylist and find the customer (if available) and reset all of the values. "Clearing" them from the system</p>*/
                Enter.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //Makes sure user enters in something/ it's a number
                        String lyn= hereSeat.getText().trim();
                        if(lyn.isEmpty()){
                            JOptionPane.showMessageDialog(null, "Please enter something", "Whoops", JOptionPane.ERROR_MESSAGE);

                        }
                        try{
                            boolean Darkness=false;
                            int celica=Integer.parseInt(lyn);
                            String miu=hereMovie.getText().trim();
                            if(miu.isEmpty()){
                                JOptionPane.showMessageDialog(null, "Please enter something", "Whoops", JOptionPane.ERROR_MESSAGE);
                            }
                            //If the number is more than 0 then...
                            celica--;
                            if(celica>=0) {
                                //check if the movie is in the system, if yes "erase" the seat
                                for (int g = list.size() - 1; g >= 0; g--) {
                                    if (list.get(g).title.equals(miu)) {
                                        if(celica<list.get(g).seatNum.length&& celica>=0){
                                            list.get(g).isFilled[celica]=true;
                                            list.get(g).CustomerAge[celica]="";
                                            list.get(g).CustomerName[celica]="";
                                            Doubledopdone.setText("Ok, seat number "+(celica+1)+" in "+miu+" is now available");
                                            Darkness=true;
                                        }
                                        //Errors in case seat is out of bounds, if it can't be found, or if it's not a number entered
                                        else{
                                            JOptionPane.showMessageDialog(null, "That number is out of bounds for this theater, enter another seat number", "Whoops", JOptionPane.ERROR_MESSAGE);
                                        }
                                    }
                                }
                                if(Darkness==false){
                                    JOptionPane.showMessageDialog(null, "Seems that movie isn't in the system, please enter one first", "Whoa", JOptionPane.ERROR_MESSAGE);

                                }
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "We don't except negative numbers, please try again", "Whoops", JOptionPane.ERROR_MESSAGE);
                            }


                        }catch(NumberFormatException BP){
                            JOptionPane.showMessageDialog(null, "Please enter a number", "Whoops", JOptionPane.ERROR_MESSAGE);

                        }
                    }
                });

            }
        });

/// //////////////////////////////////////////////////////////////////////////////////////////
        /**
         * <p>It'll go through and print everything added to the movie arraylist</p>*/
        //Option 8, Printing everything in the system
       PrintItem.addActionListener(new ActionListener() {
        @Override
         public void actionPerformed(ActionEvent e) {
            //Defines displays values
            StringBuilder starterstorage= new StringBuilder();
            JPanel panel7 = new JPanel();
            JLabel Currentmovie = new JLabel("");
            JLabel CurrentmovieCalc = new JLabel("");
            JLabel ReservedMovie = new JLabel("");
            JLabel ReservedMovieCalc = new JLabel("");
            JTextArea jack = new JTextArea(80,40);
            JScrollPane bob = new JScrollPane(jack,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                    JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            jack.setLineWrap(true);
            bob.add(jack);
            bob.setViewportView(jack);

            //Saves movie arraylist to JTextarea

           for(int i=0;i<list.size();i++) {
               //Goes through arraylist and displays available customers per movie
                jack.append("\n"+(i+1)+"."+(" Movie name: ")+list.get(i).title+("\n Tax rate: ")+(list.get(i).taxRate)+("\n Available seat(s): "));
                for (int r = 0; r < list.get(i).seatNum.length; r++) {
                    //If a seat is filled
                    if (list.get(i).isFilled[r]) {
                        jack.append(String.valueOf((r+1)) + (", "));
                    }
                }
                //and the reserved ones
                jack.append("\nReserved seat(s): ");
                for (int p = 0; p < list.get(i).seatNum.length; p++) {
                    //If it's not
                    if (list.get(i).isFilled[p] == false) {
                        jack.append((" seat number: ")+(p+1)+(" By customer: ")+(list.get(i).CustomerName[p])+(" Age: ")+(list.get(i).CustomerAge[p]));
                    }
                }
            }
            CurrentmovieCalc.setText(String.valueOf(starterstorage));

            //and prints it


            peace.setVisible(true);
            peace.removeAll();


            peace.add(panel7);
            panel7.add(jack);



            bob.setVisible(true);
         }
        });
/**
 * <p>When pressed exits the system</p>*/
        // Create an "Exit" menu item with an action listener
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        /**
         * <p>Sets up the above options into the GUI</p>*/
//Adds all of the options for the menu
        peace.setVisible(true);
        peace.add(instructions);
        fileMenu.add(newItem);
        fileMenu.add(ApplyItem);
        fileMenu.add(randomItem);
        fileMenu.add(TxtItem);
        fileMenu.add(ToItem);
        fileMenu.add(DelItem);
        fileMenu.add(LeetItem);
        fileMenu.add(PrintItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
//Adds the menu itself
        menuBar.add(fileMenu);

        peace.setSize(1800, 500);
        //peace.setVisible(true);



//This is everything for the terminal program
/// /////////////////////////////////////////////////////////////////
        //----------------------------------------------------------------------
        //Loops to ask for everything related to the info for the first movie
        while (!validEntry) {
            System.out.println("Enter in the first movie's name: ");
            if (myObj.hasNextLine()) {
                daMovie = myObj.nextLine();
                validEntry=true;
            } else {
                System.out.println("That's not an answer, try again. (Enter letters/characters, and spaces should be separated with _s) ");
                myObj.nextLine();
            }
        }
        validEntry=false;
        while (!validEntry) {
            System.out.println("Enter in the first movie's tax rate: ");
            if (myObj.hasNextDouble()) {
                taxx = myObj.nextDouble();
              //  myObj.nextLine();
                validEntry = true;
            } else {
                System.out.println("Not an answer, please enter numbers: ");
                myObj.next();
            }
        }
        validEntry = false;
        while (!validEntry) {
            System.out.println("Enter in how many seats the first movie has: ");
            if (myObj.hasNextInt()) {
                Idee= myObj.nextInt();
                myObj.nextLine();
                if(Idee>0) {
                    validEntry = true;
                    list.add(0, new Movie("", new int[Idee], taxx, new String[Idee], new boolean[Idee], new String[Idee]));
                    //Makes sure all seats are available(true)
                    Arrays.fill(list.get(0).isFilled, true);
                    Arrays.fill(list.get(0).CustomerName, " ");
                    Arrays.fill(list.get(0).CustomerAge, " ");
                    list.get(0).title = daMovie;
                    list.get(movieCounter[0]).setTitle(daMovie);
                }
                else{
                    System.out.println("Dude, enter a positive.\n");
                    Idee=0;
                }
            }

            else {
                System.out.println("Not an answer, please enter whole positive numbers: ");
                myObj.next();
                myObj.nextLine();
            }
        }
        validEntry=false;


/// ///////////////////////////////////////////////////////////////////////////////
        basicMenu();
        System.out.println("Enter an numbered option or -1 to quit:");
        menuOption = Integer.parseInt(myObj.next());


        //Coding for all of the options
        while (menuOption != -1) {
            switch (menuOption) {
                //first option
                case 1:
                    int x = 0;
                    //Infinite loop of asking for movies to enter

                    for (; x != -1; ++x) {
                        //counter for moving up in the movie arraylist for later
                        movieCounter[0]++;
                        //System.out.println("Enter the name of the movie into the program: ");
                        // DX = myObj.next();
                        validEntry=false;
                        //Infinitely ask for movies to enter, catching any non-answers

                        while (!validEntry) {
                            System.out.println("Enter in the name of the movie into the program: ");
                            if (myObj2.hasNextLine()) {
                                DX=myObj2.nextLine();
                                validEntry=true;
                            } else {
                                System.out.println("That's not an answer, try again. (Enter letters/characters, and spaces should be seperated with _s) ");
                                myObj2.nextLine();
                            }
                        }
                        validEntry=false;

                        /*while (!validEntry) {
                            System.out.println("Enter the name of the movie into the program: ");
                            // Display message
                            if (myObj.hasNextLine()) {
                                DX = myObj.nextLine();
                                validEntry = true;
                            } else {
                                System.out.println("Not an answer, please enter letters and numbers, and separate spaces with _s: ");
                                myObj.next();
                            }
                        }*/
                            // Try block to check if any exception occurs
                           /* try {
                                // Parsing user input to double
                                // Get off from this loop
                                break;
                            }
                            // Catch block to handle NumberFormatException
                            catch (NumberFormatException e) {
                                // Print the message if exception occurred
                                System.out.println(
                                        "\nNumberFormatException occurred (enter a number)\n");
                            }*/

                        validEntry=false;
                        //System.out.println("Enter in how much is the tax rate of first movie: ");
                        // taxx = Double.parseDouble(myObj.next());
                        //Infinitely ask for tax rates, catching any non-answers
                        while (!validEntry) {
                            System.out.println("Enter in how much is the tax rate of first movie: ");
                            if (myObj2.hasNextDouble()) {
                                taxx = myObj2.nextDouble();
                                myObj2.nextLine();
                                validEntry = true;
                            } else {
                                System.out.println("Not an answer, please enter numbers: ");
                                myObj2.next();
                            }
                        }
                            // Try block to check if any exception occurs
                            /*try {
                                // Parsing user input to integer
                                // using the parseInt() method
                                // Get off from this loop
                                break;
                            }
                            // Catch block to handle NumberFormatException
                            catch (NumberFormatException e) {
                                // Print the message if exception occurred
                                System.out.println(
                                        "\nNumberFormatException occurred (enter a number)\n");
                            }*/


                        validEntry=false;
                        //Infinitely asks for numbers of seats in the theater, catch any non-answers
                        while (!validEntry) {
                            System.out.println("Enter in how many seats the movie's theater has: ");
                            if(myObj2.hasNextInt()){
                                Iddd = myObj2.nextInt();
                                myObj2.nextLine();
                              //  myObj2.nextLine();
                                if(Iddd>0){
                                    validEntry=true;
                                }
                                else{
                                    System.out.println("You want to watch a movie for a nonexistent theater? Try again.\n ");
                                }
                            }
                            else{
                                System.out.println("Not an answer, please enter numbers: ");
                                myObj2.next();
                               // myObj.nextLine();
                            }
                            // Try block to check if any exception occurs
                            /*try {
                                // Parsing user input to integer
                                // using the parseInt() method
                                // Get off from this loop
                                break;
                            }
                            // Catch block to handle NumberFormatException
                            catch (NumberFormatException e) {
                                // Print the message if exception occurred
                                System.out.println(
                                        "\nNumberFormatException occurred (enter a number)\n");
                            }*/
                        }
                        validEntry=false;
                        //System.out.println("Enter in how many seats the movie's theater has: ");
                        // Iddd = Integer.parseInt(myObj.next());

                        //Makes a new entry in the movie arraylist, all bits of logic depend on the number of seats
                        list.add(movieCounter[0],new Movie(DX+"", new int[Iddd], taxx, new String[Iddd], new boolean[Iddd], new String[Iddd]));

                        // list.add(new Movie(new String, new int[Iddd], taxx, new String[Iddd], new boolean[Iddd], new String[Iddd]));

                        // list.get(movieCounter).setTitle(DX);

                        //Makes sure that the new movie's seats are all available by default
                        Arrays.fill(list.get(movieCounter[0]).getIsFilled(),true);
                        //Makes sure names are blank by default
                        Arrays.fill(list.get(movieCounter[0]).CustomerName," ");
                        //And all the customer's ages
                        Arrays.fill(list.get(movieCounter[0]).CustomerAge," ");

                        // list.get(movieCounter).setTitle(DX);

                        // list.get(movieCounter).setTitle(DX);
                        // list.get(0).setTitle(daMovie);

                        //Asks if user wants to keep entering in movies, catches any non-answers
                        while (!validEntry) {
                            System.out.println("Do you want to continue entering in movies? (enter -1 if not, enter any other number if yes): ");
                            if (myObj.hasNextInt()) {
                                answer = myObj.nextInt();
                                validEntry = true;
                            } else {
                                System.out.println("Not an answer, please enter numbers: ");
                                myObj.next();
                            }
                        }
                        // System.out.println("Do you want to continue entering in movies? (enter -1 if not, enter any other number if yes): ");
                        // int answer = myObj.nextInt();

                        if (answer == -1) {
                            //If yes, breaks loop
                            x = -2;
                            System.out.println("------------------------------------- ");
                            basicMenu();
                            System.out.println("Enter an numbered option or -1 to quit:");
                            menuOption = Integer.parseInt(myObj.next());
                        }
                    }

                    //Prints everything
                    for (int y = 0; y < list.size(); ++y) {
                        list.get(y).starterPrint();
                    }
                    break;
/////////////////////////////////////////////////////////////////////////////////////////////////////
                //Option 2
                case 2:
                    boolean leerror=false;

                    // System.out.println("Enter the name of the movie you wish to reserve for: ");
                    // String zeanswer = myObj.next();



                  //Makes sure you entered a valid input for what Movie you want to reserve a spot, stores input if it is
                    validEntry=false;
                        while (!validEntry) {
                            System.out.println("Enter the name of the movie you wish to reserve for: ");
                            if (myObj3.hasNextLine()) {
                               zeanswer = myObj3.nextLine();
                                validEntry = true;
                            } else {
                                System.out.println("That's not an answer, try again. (Enter letters/characters) ");
                                myObj3.nextLine();
                            }
                        }
                        validEntry=false;
                        //Checks if that Movie is available or not
                        for ( int g = list.size() - 1; g >= 0; --g) {
                            //Makes full error message only pop up once

                            //If it finds it and there's seats available then...
                            if (((Movie) list.get(g)).noSeats(seatChecker) == true) {
                                if (((Movie) list.get(g)).title.equals(zeanswer)) {
                                    list.get(g).idkMan();

                                    //Asks for what seat number you'd like, looping til valid entry is permitted
                                    while (!validEntry) {
                                        System.out.println("\nEnter what number seat you'd like to reserve (must be more than 0 and whole number and must be a number available in the theater): ");
                                        if (myObj3.hasNextInt()) {
                                            zeekeeper = myObj3.nextInt() - 1;
                                            myObj3.nextLine();

                                            //Makes sure the seat number isn't a negative or taken seat or else loops back around

                                            if (zeekeeper >= 0) {
                                                if(list.get(g).seatNum.length>zeekeeper && list.get(g).isFilled[zeekeeper]==true) {
                                                    leerror=true;
                                                    validEntry = true;
                                                }
                                                else if(list.get(g).seatNum.length<zeekeeper){
                                                    System.out.println("\nSorry, we don't have that number of seat available. Try again");
                                                }
                                            } else if (zeekeeper < 0 || list.get(g).isFilled[zeekeeper]==false) {
                                                System.out.println("\nPlease enter a whole number and make sure it isn't taken!\n ");
                                            }
                                            //If user enters non answer then outputs this and asks again
                                        } else {
                                            System.out.println("Not an answer, please enter numbers: ");
                                            myObj3.next();
                                        }
                                    }
                                    validEntry = false;
                                    //Infinitely ask for whole numbers
                                /*while (true) {
                                    // Display message
                                    //Whatever seat number you're entered under will dictate later logic for the Movie arraylist
                                    System.out.println("\nEnter what number seat you'd like to reserve: ");
                                    zeekeeper = Integer.parseInt(myObj.next()) - 1;
                                    if (zeekeeper < 0) {
                                        System.out.println("\nPlease enter a whole number!\n ");
                                        System.out.println("\nEnter what number seat you'd like to reserve: ");
                                        zeekeeper = Integer.parseInt(myObj.next());
                                    }

                                    // Try block to check if any exception occurs
                                    try {*/
                                    // Parsing user input to double
                                    // Get off from this loop

                                    //Saves name user inputs
                                    ((Movie) list.get(g)).isFilled[zeekeeper] = false;
                                    System.out.println("\nWhat name would you like to be reserved under?");
                                    String urName = myObj3.nextLine();
                                    ((Movie) list.get(g)).CustomerName[zeekeeper] = urName;
                                    System.out.println("\n Then you will be reserved under " + urName + "!");

                                    //Asks for acceptable age til user enters obliges
                                    System.out.println("\nWhat age are you? (C,A,S)");
                                    DK = myObj3.next();
                                    myObj3.nextLine();

                                    //Enter your age,checks if it's c/a/s
                                    for (int a = 1; a < 100; a--) {
                                        if (DK.equals("c") == true || DK.equals("C") == true
                                                || DK.equals("a") == true || DK.equals("A") == true
                                                || DK.equals("s") == true || DK.equals("S") == true) {
                                            a = 200;
                                            list.get(g).CustomerAge[zeekeeper] = DK;
                                        }
                                        //Else gives this error message and makes you try again
                                        else if (DK.equals("c") == false || DK.equals("C") == false
                                                || DK.equals("a") == false || DK.equals("A") == false
                                                || DK.equals("s") == false || DK.equals("S") == false) {
                                            System.out.println("That is not an accepted answer, please enter your age:(C,A,S)");
                                            DK = myObj3.next();
                                            myObj3.nextLine();
                                        }
                                    }
                                    //Determines your ticket cost and what age you're registered as
                                    if (DK.equals("c") || DK.equals("C")) {
                                        System.out.println("\n That will be $" + (((1 + (list.get(g).taxRate)) * 8)));
                                        list.get(g).CustomerAge[zeekeeper] = "C";
                                        leerror=true;
                                    }
                                    if (DK.equals("a") || DK.equals("A")) {
                                        System.out.println("\n That will be $" + (((1 + list.get(g).taxRate)) * 12));
                                        list.get(g).CustomerAge[zeekeeper] = "A";
                                        leerror=true;
                                    }
                                    if (DK.equals("s") || DK.equals("S")) {
                                        System.out.println("\n That will be $" + (((1 + list.get(g).taxRate)) * 10));
                                        list.get(g).CustomerAge[zeekeeper] = "S";
                                        leerror=true;
                                    }
                                    System.out.println("--------------------------------------------------\n");

                                   // break;
                                }
                                // Catch block to handle NumberFormatException
                                   /* catch (NumberFormatException e) {
                                        // Print the message if exception occurred
                                        System.out.println(
                                                "NumberFormatException occurred (enter a whole number)\n");
                                    }*/


                            }

                            //Otherwise just prints a generic message
                            else {
                                System.out.println("\nThat's not a movie registered in the system!");
                            }
                       // }

                    }
                    //Checks if there's no seats left, if so....
                            if (leerror == false) {
                    System.out.println("Sorry, that movie is full, please try another movie.");
                }

                    //Calls back the menu as part of the infinite loop
                    basicMenu();
                    System.out.println("Enter an numbered option or -1 to quit:");
                    menuOption = Integer.parseInt(myObj.next());
                    break;

/// ////////////////////////////////////////////////////////////////////////////////////////////////
                //Third option
                case 3:
                    //Random ticket = new Random();
                    System.out.println("Enter the name of the movie you wish to buy your ticket for: ");
                    zeanswer = myObj4.nextLine();

                    //Goes through entire Movie arraylist to try and find the movie name you entered
                    for (int l = list.size() - 1; l >= 0; --l)  {
                        //if it finds it then....
                        if (((Movie) list.get(l)).title.equals(zeanswer)) {
                            //First makes sure that there's seats available
                            for (int o = 0; o < list.get(l).seatNum.length; o++) {
                                if (list.get(l).noSeats(seatChecker) == true) {
                                    //If there is then...
                                    list.get(l).idkMan();

                                    //Loop to calculate your random ticket number, going til you get one unavailable
                                    for (int f = 0; f < 100; f--) {
                                        int index = (int) (Math.random() * list.get(l).seatNum.length);
                                        if (list.get(l).isFilled[index] == true) {
                                            rando = index;
                                            list.get(l).isFilled[index] = false;
                                            f = 200;
                                        }
                                    }
                                    System.out.println("Your seat is number " + (rando + 1));
                                    //listt(l).isFilled[rando+1]=false;

                                    System.out.println("\nWhat age are you? (C,A,S)");
                                    DK = myObj4.next();
                                    myObj4.nextLine();
                                    //Enter your age, checks if it's c/a/s
                                    for (int a = 1; a < 100; a--) {
                                        if (DK.equals("c") == true || DK.equals("C") == true
                                                || DK.equals("a") == true || DK.equals("A") == true
                                                || DK.equals("s") == true || DK.equals("S") == true) {
                                            a = 200;
                                            list.get(l).CustomerAge[rando] = DK;
                                        }
                                        //Else gives this error message and makes you try again
                                        else if (DK.equals("c") == false || DK.equals("C") == false
                                                || DK.equals("a") == false || DK.equals("A") == false
                                                || DK.equals("s") == false || DK.equals("S") == false) {
                                            System.out.println("That is not an accepted answer, please enter your age:(C,A,S)");
                                            DK = myObj4.next();
                                            myObj4.nextLine();
                                        }
                                    }
                                    //Determines your ticket cost and what age you're registered as
                                    if (DK.equals("c") || DK.equals("C")) {
                                        System.out.println("\n That will be $" + (((1 + list.get(l).taxRate)) * 8));
                                        System.out.println("------------------------------------- ");
                                        list.get(l).CustomerAge[l] = "C";
                                        o= list.get(l).seatNum.length+10;

                                    }
                                    if (DK.equals("a") || DK.equals("A")) {
                                        System.out.println("\n That will be $" + (((1 + list.get(l).taxRate)) * 12));
                                        System.out.println("------------------------------------- ");
                                        list.get(l).CustomerAge[l] = "A";
                                        o=list.get(l).seatNum.length+10;

                                    }
                                    if (DK.equals("s") || DK.equals("S")) {
                                        System.out.println("\n That will be $" + (((1 + list.get(l).taxRate)) * 10));
                                        System.out.println("------------------------------------- ");
                                        list.get(l).CustomerAge[l] = "S";
                                        o=list.get(l).seatNum.length+10;
                                    }

                                }
                                //Otherwise, error message
                                else{
                                    System.out.println(" Sorry, there's no more seats available\n");
                                }
                                //If there's no such movie in the system produces this message
                                if (!list.get(l).title.equals(zeanswer)) {
                                    System.out.println("Sorry that's not in our system\n");
                                }
                            }

                        }
                    }

                    System.out.println("------------------------------------- ");
                    basicMenu();
                    System.out.println("Enter an numbered option or -1 to quit:");
                    menuOption = Integer.parseInt(myObj.next());

                    break;

                    /// ///////////////////////////////////////////////////////////////////////////////////////
                //Option 4
                case 4:
                    //User enters their .txt file directory
                    System.out.println("WARNING! Please make sure your .txt file is in name-number of seats-tax rate-customer name/customer age format first\n");
                    System.out.println("Enter the directory path of the .txt file you want to use ");
                    String txt = myObj5.next();

                    try {
                        FileInputStream fstream = new FileInputStream(txt);
                        DataInputStream in = new DataInputStream(fstream);
                        BufferedReader br = new BufferedReader(new InputStreamReader(in));

                        String strLine;
                        //Stores info into strings to break apart til end of file
                        while((strLine = br.readLine()) != null) {
                            //Stores into...
                            int size=-1;
                            String[] goodStuff= new String[]{("")};
                            double daTax=0;

                            //Ignores negatives
                            if(!strLine.contains("--")) {

                                //Part that ignores the dashes

                                 goodStuff = strLine.split("-");

                           /* for (int e = 0; e < goodStuff.length; e++) {
                                System.out.println(goodStuff[e] + ", ");
                            }*/
                                 size = 0;
                                String leName = goodStuff[0];

                                //An entry is just ignored if there's no seats
                                if (Integer.parseInt(goodStuff[1]) > 0) {
                                    size = Integer.parseInt(goodStuff[1]);
                                }
                                 daTax = Double.parseDouble(goodStuff[2]);
                            }

                            if (size > 0) {
                                //Adds to movie arraylist if it passed the checks
                                list.add(new Movie(goodStuff[0], new int[size], daTax, new String[size], new boolean[size], new String[size]));
                                movieCounter[0]++;
                                //Fills the info with blanks by default
                                Arrays.fill(list.get(movieCounter[0]).isFilled, true);
                                Arrays.fill(list.get(movieCounter[0]).CustomerName, " ");

                                int Custcounter = 0;

                                //Separates the /s into readable info, and puts it into the arraylist
                                for (int m = 3; m < goodStuff.length; ++m) {
                                    String[] slopslop = goodStuff[m].split("/");
                                    String Nozomi = slopslop[0], Eito = slopslop[1];
                                    //for(int p=0;p< goodStuff.length;p++){
                                    if (Custcounter < list.get(movieCounter[0]).seatNum.length) {

                                        list.get(movieCounter[0]).isFilled[Custcounter] = false;
                                        list.get(movieCounter[0]).CustomerName[Custcounter] = Nozomi;
                                        if (Eito.equals("c") || Eito.equals("C") || Eito.equals("a")
                                                || Eito.equals("A") || Eito.equals("s") || Eito.equals("S")) {
                                            list.get(movieCounter[0]).CustomerAge[Custcounter] = Eito;
                                        } else {
                                            list.get(movieCounter[0]).CustomerAge[Custcounter] = "A";
                                        }
                                        //list.get(movieCounter).setCustomerName(Nozomi);
                                        // list.get(movieCounter).setCustomerAge(Eito);
                                        // }
                                        Custcounter++;
                                    }
                                }
                            }
                        }

                        in.close();
                        System.out.println("Entered. Please note any movies with 0 or less and bonus customers with full houses were also excluded.\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    for(int y = 0; y < list.size(); ++y) {
                        ((Movie)list.get(y)).starterPrint();
                    }

                    // System.out.println("Do you want to continue or go back to main menu? (-1 for main menu, any other number for continue):");
                    // int response = myObj.nextInt();
                    // if (response == -1) {
                    basicMenu();
                    System.out.println("Enter an numbered option or -1 to quit:");
                    menuOption = myObj.nextInt();
                    System.out.println("------------------------------------------");
                    //  break;
                    //  }
                    break;
/// //////////////////////////////////////////////////////////////////////////////////////////////////////////////
                //Option 5
                case 5:
                    //Infinitely asks for movies to delete
                    for(int b=0;b<100;b--){
                        //Assuming there's anything to delete that is or else
                        if(list.size()>0) {

                            //Asks what to delete
                            System.out.println("What movie do you want to erase?: ");
                            String dee = myObj6.nextLine();

                            //Checks if it's in the system
                            // if(list.size()>=0) {
                            for (int k = list.size() - 1; k >= 0; --k) {
                                //If it is then delete
                                if (((Movie) list.get(k)).title.equals(dee)) {
                                    list.remove(k);
                                    System.out.println(dee+ " has been removed\n ");
                                    emptyChecker=true;
                                    movieCounter[0]--;
                                }
                            }
                            //If not then error message
                            if (emptyChecker=false){
                                System.out.println("That's not a movie in the system!\n ");
                            }
                            emptyChecker=false;
                        }
                        //Error if no movies are in
                        else{
                            System.out.println("There are currently no movies in the system, please enter one in first.\n ");
                        }

                        //Asks if user wants to continue entering movies, breaks loop if no
                        System.out.println("Do you want to stop entering Movies?(yes/no)");
                        String daanswer = myObj6.nextLine();
                        // daanswercheck = Double.parseDouble(daanswer);
                        if (daanswer.equals("yes") || daanswer.equals("Yes")){
                            b=200;
                        }
                        else if (daanswer.equals("no") || daanswer.equals("No")){
                        }
                        else{
                            System.out.println("Not an answer! Now do you want to stop entering Movies?(yes/no)");
                            daanswer = myObj.next();
                            myObj.nextLine();
                        }
                    }

                    for (int y = 0; y < list.size(); ++y) {
                        ((Movie) list.get(y)).starterPrint();
                    }
                    basicMenu();
                    System.out.println("Enter an numbered option or -1 to quit:");
                    menuOption = Integer.parseInt(myObj.next());
                    break;
//////////////////////////////////////////////////////////////////////////////////////////
                //Option 6
                case 6:
                    int leet=0;

                    for(int b=0;b<100;b--){
                        boolean error=true;

                        //Asks for Movie to delete customer from
                        System.out.println("What movie do you want to erase a customer from?: ");
                        String dee = myObj7.nextLine();
                        int availacounter=0;

                        //Scans database to see if user input is in or not
                        if(list.size()>=0) {
                            for (int k = list.size() - 1; k >= 0; --k) {
                                if (((Movie) list.get(k)).title.equals(dee)) {
                                    for(int u=0;u<list.get(k).seatNum.length;u++) {
                                        if(list.get(k).isFilled[u]==false){
                                            availacounter++;
                                        }
                                    }

                                    ((Movie) list.get(k)).starterPrint();
                                    validEntry=false;

                                    //Makes sure a seat number to remove is entered. Gives error if not.
                                    while (!validEntry) {
                                        System.out.println("Enter the seat number of the customer you'd like to remove (must be within the specified range): ");

                                        if(myObj7.hasNextInt()){
                                            leet = myObj7.nextInt();
                                            myObj7.nextLine();
                                           leet --;

                                           //Checks if there's any customers to be deleted or not, otherwise produces a message and gets you out of loop
                                           if(availacounter>1) {

                                               //Makes sure user enters seat numbers in range, if so deletes customer
                                               if (list.get(k).seatNum.length > leet && leet>=0) {
                                                       list.get(k).isFilled[leet] = true;
                                                       list.get(k).CustomerName[leet] = "";
                                                       list.get(k).CustomerAge[leet] = "";
                                                       System.out.println("seat number: " + (leet + 1) + " has been removed\n ");
                                                       validEntry = true;
                                                       error=false;
                                               } else {
                                                   System.out.println("That seat number doesn't seem to be in the system\n");
                                               }
                                           }
                                           //No customers message
                                           else{
                                               System.out.println("There are no customers registered, please register some first then come back\n");
                                               validEntry=true;
                                           }

                                        }
                                        //If user enters something not allowed loops back around
                                        else{
                                            System.out.println("Not an answer, please enter whole numbers numbers: ");
                                            myObj7.next();
                                            myObj7.nextLine();
                                        }
                                    }
                                    validEntry=false;

                                }
                            }
                             if(error==true){
                                System.out.println("That's not a movie in the system!\n ");
                                error=false;
                            }
                        }
                        //No Movies to delete anything from message
                        else{
                            System.out.println("There are currently no movies in the system, please enter one in first.\n ");
                        }

                        //Breaks loop if you don't want to continue, asks for customers infinitely if you do.
                        System.out.println("Do you want to stop entering Movies?(yes/no)");
                        String daanswer = myObj7.next();
                        myObj7.nextLine();
                        // daanswercheck = Double.parseDouble(daanswer);
                        if (daanswer.equals("yes") || daanswer.equals("Yes")){
                            b=200;
                        }
                        else if (daanswer.equals("no") || daanswer.equals("No")){
                        }
                        else{
                            System.out.println("Not an answer! Now do you want to stop entering Movies?(yes/no)");
                            daanswer = myObj7.next();
                            myObj7.nextLine();
                        }
                    }

                    basicMenu();
                    System.out.println("Enter an numbered option or -1 to quit:");
                    menuOption = Integer.parseInt(myObj.next());
                    break;

/// //////////////////////////////////////////////////////////////
            //Option 7
                case 7:
                    for (int y = 0; y < list.size(); ++y) {
                        ((Movie) list.get(y)).starterPrint();
                    }
                    basicMenu();
                    System.out.println("Enter an numbered option or -1 to quit:");
                    menuOption = Integer.parseInt(myObj.next());

                    break;


                case -1:
                    break;

                default:
                    basicMenu();
                    System.out.println("\nThat's not an option! Enter an numbered option or -1 to quit:");
                    menuOption = myObj.nextInt();

            }

        }
    }
}
