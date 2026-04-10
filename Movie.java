//Represents a movie and all of their info

public class Movie {
    //starts the info movie has: their seat numbers, tax rates, movie names(titles), customer names, and if it's filled or not
    int[] seatNum;
    double taxRate;
    String title;
    String[] CustomerName= new String[]{" "};
    String[] CustomerAge= new String[]{" "};
    boolean[] isFilled= new boolean[]{true};
    /**
     * <p>This defines the Movie arraylist, which is used throughout the project.
     * Every movie has a name, a max number of seats, a tax rate, names of customers to watch it, whether the movie is filled. and the customer names and ages</p>
     * */
    public Movie(String title, int[] seatNum, double taxRate, String[] CustomerName, boolean[] isFilled, String[] CustomerAge){
        this.seatNum=seatNum;
        this.taxRate=taxRate;
        this.title=title;
        this.CustomerName=CustomerName;
        this.isFilled=isFilled;
        this.CustomerAge=CustomerAge;
    }
    /**
     * <p>This defines the Movie arraylist, which is used throughout the project.
     * Every movie has a name, a max number of seats, a tax rate, names of customers to watch it, whether the movie is filled. and the customer names and ages</p>
     * */

    //default stuff in case things go wrong
    public Movie(String idk, int first, double second, String third, String fourth){
        this.seatNum= new int[]{0};
        this.taxRate=0;
        this.title="Not registered";
        this.CustomerName= new String[]{"Not registered"};
        this.isFilled= new boolean[]{true};
        this.CustomerAge = new String[]{"A"};
    }
    //getters and setters
    public String getTitle(){

        return title;
    }
    public void setTitle(String ownTitle){

        this.title=ownTitle;
    }
    public boolean[] getIsFilled(){

        return isFilled;
    }
    public boolean[] setIsFilled(boolean isFilled){
        this.isFilled= new boolean[]{isFilled};
        return new boolean[0];
    }
    public String[] getCustomerName(){

        return CustomerName;
    }

    public void setCustomerName(String CustomerName){

        this.CustomerName= new String[]{CustomerName};
    }
    public int[] getSeatNum(){

        return seatNum;
    }
    public double getTaxRate(){
        return taxRate;
    }
    public double setTaxRate(){
        return taxRate;
    }

    public String[] getCustomerAge(){return CustomerAge;}
    public String[] setCustomerAge(String eito){return CustomerAge;}

    //Print the movie's registered info
    public void starterPrint() {
        System.out.println("Movie name: "+title+"\nTax rate: "+taxRate+"\nAvailable seat(s): ");
        for(int r=0; r<seatNum.length;r++){
            //If a seat is filled
            if (isFilled[r]){
                System.out.println((r+1)+", ");
            }
        }
        System.out.println("\nReserved seat(s): ");
        for(int p=0; p<seatNum.length;p++){
            //If it's not
            if(isFilled[p] == false){
                System.out.println("seat number: "+(p+1)+" By customer: "+CustomerName[p]+" Age: "+CustomerAge[p]);
            }
        }
        System.out.println("\n");
    }
    /**
     * <p>Prints out seat availability, relevant only in the terminal version</p>*/
    //Prints out seat availability
    public void idkMan() {
        System.out.println("Available seat(s): ");
        for (int r = 0; r < seatNum.length; r++) {
            if (isFilled[r]) {
                System.out.println((r+1) + ", ");
            }
        }
    }
    /**Checks if they're seats left in any given movie, returns false if no, and true if so*/

    //returns false if no seats are left
    public boolean noSeats(boolean x){
        int seatcounter=0;
        for (int r = 0; r < seatNum.length; r++) {
            if (isFilled[r]) {
                seatcounter++;
            }
        }
        if (seatcounter==0){
            x=false;
        }
        return x;
    }
    /**Checks if they're seats left in any given movie, returns false if no, and true if so*/

    }






