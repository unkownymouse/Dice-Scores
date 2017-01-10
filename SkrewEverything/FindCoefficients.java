package SkrewEverything;

import java.util.*;
import java.math.*;


class FindCoefficients
{
	//-----------Class Variable/ Static Variables-----------//
	private static ArrayList<BigInteger> f = new ArrayList<>(); // Factorial List
	private static long i; // Keeps track of till where factorial was calculated.
	private static char[] superScripts = {'\u2070','\u00B9','\u00B2','\u00B3','\u2074','\u2075','\u2076','\u2077','\u2078','\u2079'};
	//-----------Instance Variables----------//
	private ArrayList<BigInteger> coefficients = new ArrayList<>(); // All the coefficients of the equation
    private int noOfDice,sides; 
    
    
    
	private static void factorial(int noOfDice, int sides)
	{
		if(f.size() == 0 ) 
		{
			f.add(BigInteger.ONE);
			BigInteger fact = new BigInteger("1");
			/* Total number of factorials needed is noOfDice*sides. We are already insert 0!
			 * so, we iterate upto noOfDice*side-1(last value in factorial series)
			 * 
			 * 
			 * Highest factorial value used will be noOfDice*sides-1 // check it in the coefficient expansion i.e., at c12
			 */
			for( i = 1; i<= noOfDice*sides-1 ; i++)
			{
				fact = fact.multiply(BigInteger.valueOf(i));
				f.add(fact);
			}
		}
		else if(noOfDice*sides > f.size()) //  If there are less terms, then it just adds at the end instead of creating whole list again.
		{
			BigInteger fact = new BigInteger(String.valueOf(f.get(f.size()-1)));
			/*
			 * No need to assign i value because i is incremented as postfix.
			 * So it automatically updated to the required/current intial value(i.e., more than previous value).
			 * For example, First we calculated till 11 (till c12) now to calculate next term i  should be 12.
			 * As it is postfix increment, it broke from the loop with value 12.
			 */
			for( ; i<= noOfDice*sides-1; i++) 
			{
				fact = fact.multiply(BigInteger.valueOf(i));
				f.add(fact);
			}
		}
	}
	
	/**
	 * 
	 * @param sides     :	How many sides are there in a die
	 * @param noOfDice  :	How many number of dice are there
	 */
	
	FindCoefficients(int sides, int noOfDice) 
	{
		this.noOfDice = noOfDice;
		this.sides = sides;
		factorial(noOfDice, sides); // Calculates factorial.
		firstSigma(); // Starts to find the coefficients.
	}
	
	private BigInteger nCr(int n, int r)
	{
		BigInteger denominator = f.get(r).multiply(f.get(n-r));
		BigInteger numerator = f.get(n);
		return numerator.divide(denominator);
	}
	
	private void firstSigma()  
	{
		int a = noOfDice,total = sides*noOfDice;
		/*
		 * We calculate till `total` because after that elements will be equal to 0
		 */
		//TODO Optimize for loop so that it calculates till middle and just reverse it according to even and odd.
		//     Doing so will avoid calculating factorial for all nCr combinations
		for(int i = a; i <= total; i++)
			{
				secondSigma(i);
			}
	}
	
	private void secondSigma(int a)
	{
		BigInteger sum= new BigInteger("0");
		int mi = Math.min(noOfDice, (a-noOfDice)/sides);
		int sign;
		int _b;	
		for(int s = 0 ; s <= mi ; s++)
		{
			_b = a-(s*sides)-1;
			sign = s%2==0 ? 1 : -1; // (-1)^s  -> if s is even power, value will be obviously +1
			sum = sum.add(nCr(noOfDice,s).multiply(nCr(_b,noOfDice-1)).multiply(BigInteger.valueOf(sign)));
		}
		coefficients.add(sum); 
	}
	
	/**
	 * @return : Returns ArrayList of BigIntegers with all the coefficients
	 */
	ArrayList<BigInteger> getCoefficients()
	{
		return coefficients;
	}
	
	/**
	 * 
	 * @param power : Takes the exponent of required coefficient i.e., x^power
	 * @return : Returns the index at which the coefficient of specified power is there in List
	 */
	int indexOfPower(int power)
	{
		if(power >= noOfDice && power <= noOfDice*sides)
		{
			int index =  power - noOfDice;
			return index;
		}
		else
		{
			return -1;
		}	
	}
	
	/**
	 * 
	 * @return Returns the Polynomial Equation in the form of String
	 */
	String getEquation()
	{
		StringBuilder equation = new StringBuilder();
		char[] ch;
		for(int i = noOfDice, j = 0 ; j < coefficients.size() ; i++, j++)
		{
			equation.append(coefficients.get(j));
			equation.append("x"); // Polynomial in terms of 'x'
			if(i >= 10) // For splitting 2 or more digit numbers individually to retrieve from superScript array
			{
				ch = String.valueOf(i).toCharArray();
				for(char c : ch)
				{
					equation.append(superScripts[Character.digit(c, 10)]);
				}
			}
			else
			{
				equation.append(superScripts[i]);
			}
			equation.append("+");
		}
		equation.deleteCharAt(equation.length()-1); // Remove the extra '+' at the end
		
		return equation.toString();
	}
	
}