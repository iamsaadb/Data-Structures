/**
 *********************************************************************************
 *
 * This class represents a mixed number which consist of sign (+ or -), integer 
 * and fraction parts of a number. Example: -10 3/5, 0 1/2, -0 3/4, 4 5/6
 * 
 * Requirements:
 * 1. Must use the given Fraction and FractionInterface to store fraction part
 * 2. Implement interfaces: MixedNumberInterface and Comparable (i.e. compareTo())
 * 3. Implement methods equals() and toString() from class Object
 * 4. Must work for both positive and negative mixed numbers
 * 5. Must reduce to mixed number to lowest term, e.g. 3 22/4 --> 8 1/2 
 * 6. Must reduce result mixed number to lowest term for operations 
 *    add(), subtract(), multiply() and divide(), e.g. see test cases
 * 7. For input 2 3/-10 & 2 -3/-10, must convert them to 
 *    -2 3/10 & 2 3/10 respectively (see Hint 2. below)
 * 8. Must throw only Project1Exception in case of errors
 * 9. Must not add new or modify existing data fields
 * 10.Must not add new or modify existing public methods
 * 11.May add private methods
 *
 * More info:
 *
 * 1. You need to downcast reference parameter MixedNumberInterface to 
 *    MixedNumber if you want to use it as MixedNumber. 
 *    See add, subtract, multiply and divide methods
 *
 * 2. The following private methods will be useful
 *
 *    // Private method: reduce a MixedNumber object to lowest term 
 *    // E.g. -3 50/7 --> -10 1/7;  3 25/10 --> 5 1/2
 *    private void reduceToLowestForm()
 *
 *    // Private method: convert a MixedNumber to new Fraction object
 *    // object. E.g. -7 1/7 --> -50/7;  3 1/8 --> 25/8
 *    private FractionInterface getFractionalEquivalent()
 *           
 * 3. Use "this" to access this object if it is needed
 *
 * 4. You need to downcast reference parameter FractionInterface to Fraction if  
 *    you want to use it as Fraction. 
 *
 * 5. More Fraction class info
 *
 *    It works for both positive and negative fractions
 *    For input 3/-10 & -3/-10, they are converted to -3/10 & 3/10 respectively 
 *    All fraction objects are reduced to lowest term, e.g. 10/20 --> 1/2
 *    Additional methods in Fraction:
 *
 *		public Fraction()  // default value: 0/1
 *		public Fraction(int num, int den)
 *		public boolean equals(Object other)
 *		public int compareTo(Fraction other)
 *		public String toString() // e.g. "3/5" or "-10/11"
 *
 ************************************************************************************/


package PJ1;


public class MixedNumber implements MixedNumberInterface, Comparable<MixedNumber>
{

        // Fields:

	// both integer and fraction parts are forced to >= 0
	// sign of a mixed number is stored as '+' or '-'

	private char              sign;	    // '+' or '-'
	private int               integer;  // whole number portion >= 0
	private FractionInterface fraction; // fractional portion in lowest terms;


	// Methods:

	public MixedNumber()
	{
		setMixedNumber(0, 0, 1);
	}	// end default constructor

	public MixedNumber(int integerPart, int fractionNumerator, 
				int fractionDenominator)
	{
                setMixedNumber(integerPart, fractionNumerator, fractionDenominator);
	}	// end constructor

	public MixedNumber(int integerPart, FractionInterface fractionPart)
	{
		setMixedNumber(integerPart, fractionPart);
	}	// end constructor


	public void setMixedNumber(int integerPart, FractionInterface fractionPart)
	{
		int n,a,b;
                n = integerPart;
                a = fractionPart.getNumerator();
                b = fractionPart.getDenominator();
                Fraction N = new Fraction ();
                N = (Fraction) fractionPart;
                if (n<0 && fractionPart.getSign()=='-' || n>=0 && fractionPart.getSign()=='+'){
                    this.sign='+';
                }
                else{
                    this.sign='-';
                }
                this.integer = Math.abs(n);
                N.setFraction(Math.abs(a), Math.abs(b));
                this.fraction = N;
  
         
               
                
                if (fractionPart.getDenominator()==0){ throw new ArithmeticException("Denominator is 0, try again");
	}	// end setMixedNumber
        }
	public void setMixedNumber(int integerPart, 
		int fractionNumerator, int fractionDenominator)
	{
		if (integerPart*fractionNumerator*fractionDenominator >= 0){
                    this.sign='+';
                }
                else {this.sign='-';}
            
                this.integer = Math.abs(integerPart);
                Fraction F = new Fraction ();
                F.setFraction(Math.abs(fractionNumerator),Math.abs( fractionDenominator));
                this.fraction = F;
               
                
	}	// end setMixedNumber

	public int getIntegerPart()
	{
		if (this.sign=='+'){
                    return integer;
                }
                else return -1*integer;
	  
	}	// end getInteger

	public FractionInterface getFractionPart()
	{
		// retrieve fraction portion, assume + value 
		return fraction;
	}	// end getFraction

	public MixedNumberInterface add(MixedNumberInterface operand)
	{
		MixedNumber Op = (MixedNumber) operand;
                Fraction F1 = (Fraction) this.getFractionalEquivalent();
                Fraction F2 = (Fraction) Op.getFractionalEquivalent();
                Fraction C = (Fraction) F1.add(F2);
                MixedNumber K = new MixedNumber (0,C);
                K.reduceToLowestForm();
               
		return K; // change it
	} // end add

	public MixedNumberInterface subtract(MixedNumberInterface operand)
	{
		MixedNumber Op = (MixedNumber) operand;
                Fraction F1 = (Fraction) this.getFractionalEquivalent();
                Fraction F2 = (Fraction) Op.getFractionalEquivalent();
                Fraction C = (Fraction) F1.subtract(F2);
                MixedNumber K = new MixedNumber (0,C);
                K.reduceToLowestForm();
               
		return K; // change it
	}	// end subtract

	public MixedNumberInterface multiply(MixedNumberInterface multiplier)
	{
		MixedNumber Op = (MixedNumber) multiplier;
                Fraction F1 = (Fraction) this.getFractionalEquivalent();
                Fraction F2 = (Fraction) Op.getFractionalEquivalent();
                Fraction C = (Fraction) F1.multiply(F2);
                MixedNumber K = new MixedNumber (0,C);
                K.reduceToLowestForm();
               
		return K; // change it
	}	// end multiply

	public MixedNumberInterface divide(MixedNumberInterface divisor)
	{
		MixedNumber Op = (MixedNumber) divisor;
                Fraction F1 = (Fraction) this.getFractionalEquivalent();
                Fraction F2 = (Fraction) Op.getFractionalEquivalent();
                Fraction C = (Fraction) F1.divide(F2);
                MixedNumber K = new MixedNumber (0,C);
                K.reduceToLowestForm();

		return K; // change it
	}
        public boolean equals(Object other) { 
        boolean result;
       
        if ((other == null) || (getClass() != other.getClass())) 
            result = false;
        else { 
        MixedNumber otherMN = (MixedNumber) other;
        otherMN.AdjustMixNumSign();
        this.AdjustMixNumSign();
        if (this.sign!=otherMN.sign) { result = false;}
        else {Fraction N = new Fraction ();
        N = (Fraction) otherMN.getFractionPart();
        Fraction N2 = new Fraction ();
        N2 = (Fraction) this.getFractionPart();
        if (N.equals(N2)) result = true;
        else result = false;}}
        return result;}
        

	public int compareTo(MixedNumber other)
	{
                Fraction A = new Fraction();
                Fraction B = new Fraction ();
                A = (Fraction) this.getFractionalEquivalent();
                B = (Fraction) other.getFractionalEquivalent();
                
                if ((A.compareTo(B))>0){return 1; }
                else if (A.compareTo(B) == 0) {return 0;}
                else return -1;
		 // change it
	} // end compareTo


	public String toString()
       /* {return this.sign + " " + this.integer + " " + this.fraction.toString();}*/
	{
         if (this.sign=='-'){
             if (this.integer == 0) {return this.sign + " " + this.fraction.toString();}
             else {return this.sign + " " + this.integer + " " + this.fraction.toString();}
         }
         else {
             if(this.integer==0){return this.fraction.toString();}
             else { return this.integer + " " + this.fraction.toString();}
         
         }
        }
      

	// Private method: reduce a MixedNumber object to lowest term 
        // E.g. -3 50/7 --> -10 1/7;  3 25/10 --> 5 1/2
	
	private void reduceToLowestForm()
	{
              int resid = (int) this.fraction.getNumerator()/this.fraction.getDenominator();
              
              int a = this.getIntegerPart() + resid;
              int b = this.fraction.getNumerator()-resid*this.fraction.getDenominator();
              int c = this.fraction.getDenominator();
              this.setMixedNumber(a,b,c);
              
        }	


	// Private method: convert a MixedNumber to new Fraction object
        // object. E.g. -7 1/7 --> -50/7;  3 1/8 --> 25/8
	//DONE
        private FractionInterface getFractionalEquivalent()
	{
		
                Fraction N = new Fraction();
                N.setSign(this.sign);
                N.setFraction((this.integer*this.fraction.getDenominator()+ this.fraction.getNumerator()), this.fraction.getDenominator());
                 return N ;}
               
	

        
        private MixedNumberInterface AdjustMixNumSign(){
        if (this.integer*this.getFractionPart().getNumerator()*this.getFractionPart().getDenominator()<0){
        this.sign = '-';
        this.integer = Math.abs(this.integer);
        this.fraction.setSign('+');
        this.fraction.setFraction(Math.abs(this.fraction.getNumerator()), Math.abs(this.fraction.getDenominator()));
        }
        else 
        this.sign = '+';
        this.integer = Math.abs(this.integer);
        this.fraction.setSign('+');
        this.fraction.setFraction(Math.abs(this.fraction.getNumerator()), Math.abs(this.fraction.getDenominator()));
        return this;
        }
	// You may add more private methods

} // end MixedNumber

