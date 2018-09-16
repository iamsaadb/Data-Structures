package PJ3;
import java.util.*;

/*
 * Ref: http://en.wikipedia.org/wiki/Video_poker
 *      http://www.freeslots.com/poker.htm
 *
 *
 * Short Description and Poker rules:
 *
 * Video poker is also known as draw poker. 
 * The dealer uses a 52-card deck, which is played fresh after each playerHand. 
 * The player is dealt one five-card poker playerHand. 
 * After the first draw, which is automatic, you may hold any of the cards and draw 
 * again to replace the cards that you haven't chosen to hold. 
 * Your cards are compared to a table of winning combinations. 
 * The object is to get the best possible combination so that you earn the highest 
 * payout on the bet you placed. 
 *
 * Winning Combinations
 *  
 * 1. Jacks or Better: a pair pays out only if the cards in the pair are Jacks, 
 * 	Queens, Kings, or Aces. Lower pairs do not pay out. 
 * 2. Two Pair: two sets of pairs of the same card denomination. 
 * 3. Three of a Kind: three cards of the same denomination. 
 * 4. Straight: five consecutive denomination cards of different suit. 
 * 5. Flush: five non-consecutive denomination cards of the same suit. 
 * 6. Full House: a set of three cards of the same denomination plus 
 * 	a set of two cards of the same denomination. 
 * 7. Four of a kind: four cards of the same denomination. 
 * 8. Straight Flush: five consecutive denomination cards of the same suit. 
 * 9. Royal Flush: five consecutive denomination cards of the same suit, 
 * 	starting from 10 and ending with an ace
 *
 */


/* This is the video poker game class.
 * It uses Decks and Card objects to implement video poker game.
 * Please do not modify any data fields or defined methods
 * You may add new data fields and methods
 * Note: You must implement defined methods
 */



public class VideoPoker {

    // default constant values
    private static final int startingBalance=100;
    private static final int numberOfCards=5;

    // default constant payout value and playerHand types
    private static final int[] multipliers={1,2,3,5,6,9,25,50,250};
    private static final String[] goodHandTypes={ 
	  "Royal Pair" , "Two Pairs" , "Three of a Kind", "Straight", "Flush	", 
	  "Full House", "Four of a Kind", "Straight Flush", "Royal Flush" };

    // must use only one deck
    private final Decks oneDeck;

    // holding current poker 5-card hand, balance, bet    
    private List<Card> playerHand;
    private int playerBalance;
    private int playerBet;

    /** default constructor, set balance = startingBalance */
    public VideoPoker() 
    {
	this(startingBalance);
    }

    /** constructor, set given balance
     * @param balance */
    public VideoPoker(int balance) 
    {
	this.playerBalance= balance;
        oneDeck = new Decks(1);
    }

    /** This displays the payout table based on multipliers and goodHandTypes arrays */
    private void showPayoutTable()
    { 
	System.out.println("\n\n");
	System.out.println("Payout Table   	      Multiplier   ");
	System.out.println("=======================================");
	int size = multipliers.length;
	for (int i=size-1; i >= 0; i--) {
		System.out.println(goodHandTypes[i]+"\t|\t"+multipliers[i]);
	}
	System.out.println("\n\n");
    }

    /** Check current playerHand using multipliers and goodHandTypes arrays
     *  Must print yourHandType (default is "Sorry, you lost") at the end of function.
     *  This can be checked by testCheckHands() and main() method.
     */
    private void checkHands() throws PlayingCardException 
    {   
        if (isRoyalFlush()) {
        System.out.println(goodHandTypes[8] + "!!");
        }
        else if (isStraightFlush()){
        System.out.println(goodHandTypes[7]+ "!!");

        }
        else if (is4OfKind()) {
        System.out.println(goodHandTypes[6]+ "!!");
        }
        else if (isFullHouse()){
        System.out.println(goodHandTypes[5]+ "!!");
        }
        else if (isFlush()){
        System.out.println(goodHandTypes[4]+ "!!");
        }
        else if (isStraight()){
        System.out.println(goodHandTypes[3]+ "!!");
        }
        else if (is3OfKind()){
        System.out.println(goodHandTypes[2]+ "!!");
        }
        else if (is2Pairs()){
        System.out.println(goodHandTypes[1]+ "!!");
        }
        else if (isRoyalPair()){
        System.out.println(goodHandTypes[0]+ "!!");
        }
        else {
         System.out.println("Sorry you lost!");
        }
        }
        
    
    
   /* private void sortHand() throws PlayingCardException
    {
        
        for (int i=0; i<4; i++){
            for (int j=i+1; j<5; j++){
                if (l1.get(i).getRank() > l1.get(j).getRank()){
                Card tmp = new Card(l1.get(i).getRank(),l1.get(i).getSuit());
                l1.set(i, l1.get(j));
                l1.set(j,tmp);
                } 
            }
        }
    }*/
    
    private List<Card> sortit (List <Card> inputlist ) throws PlayingCardException{
   
        List <Card> l1 = new ArrayList <> (inputlist);
        int n = 0;
        for (int i=0; i<5; i++){
            for (int j=i+1; j<5; j++){
                if (l1.get(i).getRank() > l1.get(j).getRank()){
                Card tmp = new Card(l1.get(i).getRank(),l1.get(i).getSuit());
                l1.set(i, l1.get(j));
                l1.set(j,tmp);
                } 
            }
        }
        return l1;
    }
        
       
    
    
  
     private boolean containsAce() throws PlayingCardException{
         List <Card> l = playerHand;
        if (l.get(0).getRank()==1){ 
            return true;
        } 
        else {
        return false;
        }    
    }
     
     private boolean isRoyalFlush() throws PlayingCardException{
        
        List<Card> l = sortit(playerHand);
        if (l.get(0).getRank()==1 &&
            l.get(1).getRank()<l.get(2).getRank() &&   
            l.get(2).getRank()<l.get(3).getRank() && 
            l.get(3).getRank()<l.get(4).getRank() &&
            l.get(4).getRank()-l.get(1).getRank()==3)
        {return true;}
        else {return false;}
     }
         
    private boolean isFlush() throws PlayingCardException{
        List<Card> l = sortit(playerHand);
        
          int count = 0;
        for (int i=1; i<5; i++){
        if (l.get(0).getSuit()==l.get(i).getSuit()){
        count++;
        }
        else count --;
        }
        if (count == 4) {return true;}
        else {return false;}
    }
     
    private boolean isStraight() throws PlayingCardException{
        List<Card> l = sortit(playerHand);
        if (l.get(0).getRank()<l.get(1).getRank() &&
            l.get(1).getRank()<l.get(2).getRank() &&   
            l.get(2).getRank()<l.get(3).getRank() && 
            l.get(3).getRank()<l.get(4).getRank() &&
            l.get(4).getRank()-l.get(0).getRank()==4)
        {return true;}
        else {return false;}
    }
    
    private boolean isStraightFlush() throws PlayingCardException{
        if (isFlush()==true && isStraight()==true){
            return true;
        }
        else return false;
    }
   
    private boolean is4OfKind() throws PlayingCardException{
        List<Card> l = sortit(playerHand);
        if ((l.get(0).getRank()==l.get(1).getRank() &&
             l.get(1).getRank()==l.get(2).getRank() &&
             l.get(2).getRank()==l.get(3).getRank()) ||
            (l.get(1).getRank()==l.get(2).getRank() &&
             l.get(2).getRank()==l.get(3).getRank() &&
             l.get(3).getRank()==l.get(4).getRank())           
                ){
        return true;}
        else {
        return false;
        }
    }
    
    private boolean isFullHouse() throws PlayingCardException {
        List<Card> l = sortit(playerHand);
        if ((l.get(0).getRank()==l.get(1).getRank() &&
             l.get(1).getRank()==l.get(2).getRank() &&
             l.get(3).getRank()==l.get(4).getRank())
             ||
            (l.get(2).getRank()==l.get(3).getRank() &&
             l.get(3).getRank()==l.get(4).getRank() &&
             l.get(0).getRank()==l.get(1).getRank())
            ){
            return true;
             }
        else {
        return false;
        }
    }
    
    private boolean is3OfKind() throws PlayingCardException{
        List<Card> l = sortit(playerHand);
        if ((l.get(0).getRank()==l.get(1).getRank() &&
             l.get(1).getRank()==l.get(2).getRank()) ||
             (l.get(1).getRank()==l.get(2).getRank() &&
             l.get(2).getRank()==l.get(3).getRank()) ||
             (l.get(2).getRank()==l.get(3).getRank() &&
             l.get(3).getRank()==l.get(4).getRank()))           
        {
        return true;
        }
        else {
        return false;
        }
    }
    
    private boolean is2Pairs() throws PlayingCardException{
        List<Card> l = sortit(playerHand);

        if ((l.get(0).getRank()==l.get(1).getRank() &&
             l.get(2).getRank()==l.get(3).getRank()) ||
             (l.get(1).getRank()==l.get(2).getRank() &&
             l.get(3).getRank()==l.get(4).getRank()) ||
             (l.get(0).getRank()==l.get(1).getRank() &&
             l.get(3).getRank()==l.get(4).getRank()))           
        {
        return true;
        }
        else {
        return false;
        }
    }
    
    
    private boolean isRoyalPair() throws PlayingCardException{
        List<Card> l = sortit(playerHand);

    if ((l.get(3).getRank()== 1 || l.get(3).getRank()>10) &&
        (l.get(4).getRank()== 1 || l.get(4).getRank()>10)){
        return true;
    }
    else {
    return false;
    }
    }
    
    public void play() {
     showPayoutTable();

     boolean play = true;

     while (play) {
     showBalance();
     getBetAndVerify();
     updateBalance();
     oneDeck.reset();
     oneDeck.shuffle();
     try{
     playerHand = oneDeck.deal(numberOfCards);
     }catch (PlayingCardException e) {
     System.out.println("Exception" + e.getMessage());
     }
     displayHand();
     updateCards();
     try{
     checkHands();
     }catch (PlayingCardException e) {
     System.out.println("Exception" + e.getMessage());
     }
     play=retry(); 
     }
     }

    
    
    private void showBalance(){
    System.out.printf("Your balance is %d  ", playerBalance);
    }
    
    private void getBetAndVerify(){
    System.out.printf("Please enter your bet: ", playerBalance);
    Scanner bet = new Scanner(System.in);
    do{
    this.playerBet = bet.nextInt();
    } 
    while(!(this.playerBet > 0) || !(this.playerBet <= this.playerBalance));
    }
    
    private void updateBalance(){
    this.playerBalance -= this.playerBet;
    }

    private void displayHand(){
    int currSize = playerHand.size();
    for (int i = 0; i < currSize ; i++){
    System.out.print(playerHand.get(i).toString() + " || ");
    }
    }
    
    private void header(int l){
    for (int i = 0; i < l; i++){
    System.out.print("-");
    }}
    
    private void updateCards(){
    List<String> keepList;
    String userInput; 
    List<Card> replaceHand = new ArrayList<>(); 
    int remainSize;
    int test;
    boolean num = false; 
    do{
    System.out.print("Enter positions of cards to keep (e.g. 1, 4, 5): ");
    Scanner keepScan = new Scanner( System.in);
    userInput = keepScan.nextLine();
    keepList = Arrays.asList(userInput.split(", *"));
    remainSize = keepList.size();
  
    
    for (int i = 0; i <= remainSize -1 ; i++){
    try{
    test = Integer.parseInt(keepList.get(i));
    if ( test > 0 && test <= numberOfCards)
    {
    num = false;
    }
    else{
    num = true;
    System.out.println("invalid input");
    break;
    }

    } catch(NumberFormatException e){
    num = true;
    System.out.println("not a number");
    }
    }
    } while(num); 
    int replaceCards = numberOfCards-keepList.size(); 
    try{
    replaceHand = oneDeck.deal(replaceCards); 
    } catch (PlayingCardException e){
    System.out.println("Exception dealing a new hand" + e.getMessage());
    }

    
    for(int i = 0;i<keepList.size();i++){
    replaceHand.add( playerHand.get(Integer.parseInt(keepList.get(i))));
    }
    
    playerHand = replaceHand;

    System.out.println("" + playerHand.toString());
    }

    private boolean retry(){
    boolean newGame = false;
    Scanner choiceScan = new Scanner(System.in);
    String choice = "n";

    if (playerBalance <= 0) {
    System.out.printf("Your balance is %d\n Bye!\n", playerBalance);
    newGame = false;
    return newGame;
    }

    System.out.printf("Your balance is:$%d, Do you want to play again (y or n)?\n", playerBalance);
    Scanner playAgain = new Scanner(System.in);
    if (playAgain.hasNext() && (playAgain.nextLine().equalsIgnoreCase("y"))) {
    System.out.printf("Do you want to see the payout table? (y or n) \n" );
    Scanner showTable = new Scanner(System.in);
    if (showTable.hasNext() && (showTable.nextLine().equalsIgnoreCase("y"))) {showPayoutTable();}
    oneDeck.reset();
    newGame = true;
    }
    else {
    System.out.println("Bye, see you next time!");
    return false;
    }
    return newGame;
    }

    

    /*************************************************
     *   Do not modify methods below
    /*************************************************

    /** testCheckHands() is used to test checkHands() method 
     *  checkHands() should print your current hand type
     */ 
    public void testCheckHands()
    {
      	try {
    		playerHand = new ArrayList<Card>();

		// set Royal Flush
		playerHand.add(new Card(1,3));
		playerHand.add(new Card(10,3));
		playerHand.add(new Card(12,3));
		playerHand.add(new Card(11,3));
		playerHand.add(new Card(13,3));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Straight Flush
		playerHand.set(0,new Card(9,3));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Straight
		playerHand.set(4, new Card(8,1));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Flush 
		playerHand.set(4, new Card(5,3));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// "Royal Pair" , "Two Pairs" , "Three of a Kind", "Straight", "Flush	", 
	 	// "Full House", "Four of a Kind", "Straight Flush", "Royal Flush" };

		// set Four of a Kind
		playerHand.clear();
		playerHand.add(new Card(8,3));
		playerHand.add(new Card(8,0));
		playerHand.add(new Card(12,3));
		playerHand.add(new Card(8,1));
		playerHand.add(new Card(8,2));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Three of a Kind
		playerHand.set(4, new Card(11,3));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Full House
		playerHand.set(2, new Card(11,1));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Two Pairs
		playerHand.set(1, new Card(9,1));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Royal Pair
		playerHand.set(0, new Card(3,1));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// non Royal Pair
		playerHand.set(2, new Card(3,3));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");
      	}
      	catch (PlayingCardException e)
      	{
		System.out.println(e.getMessage());
      	}
    }

    /* Quick testCheckHands() */
    public static void main(String args[]) throws PlayingCardException  
    {
	VideoPoker pokergame = new VideoPoker();
	pokergame.testCheckHands();
    }

    
}
