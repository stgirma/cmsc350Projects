/**
 * OrderList.java - this class performs two methods checkSorted (overloaded methods). 
 * 
 * @author  Girma, Senay
 * @version 1.0 10/24/2022
 * Project 2
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;



/**
 * OrderedList a utility class with two overloaded implementations of checkSorted method to check if the list 
 * read from the input file is in proper format (terms are ordered in correct order)
 * 
 */
public class OrderedList{
	
	/**
	 * checkSorted a utility class with two overloaded implementations of checkSorted method to check if the list 
	 * read from the input file is in proper format (terms are ordered in correct order)
	 * 
	 * @param List<T> list of string read from the file that represent the polynomial
	 */
	public static <T extends Comparable<? super T>> boolean checkSorted(List<T> pList) {
		//base case
		if(pList.size() == 1) {
			System.out.println("There is only one term left " + pList.get(0).toString() );
			return true;
		}
		
		if(pList.get(0).compareTo(pList.get(1)) > 0) {
			System.out.println("Now comparing " + pList.get(0).toString() + " and " + pList.get(1).toString() );
			return false;
		}
		
		System.out.println("Comparing " + pList.get(0).toString() + " and " + pList.get(1).toString() + " returned true." ); 
		System.out.println("recursing ...");
		return checkSorted(pList.subList(1, pList.size()));

	}
	
	/**
	 * checkSorted a utility class with two overloaded implementations of checkSorted method to check if the list 
	 * read from the input file is in proper format (terms are ordered in correct order)
	 * 
	 * @param polynomialList List<T> list of string read from the file that represent the polynomial
	 * @param compareByExponent comparator<Polynomial>
	 */
	public static <T> boolean checkSorted(ArrayList<Polynomial> polynomialList, Comparator<Polynomial.PolynomialTerm> compareByExponent) {
		if(polynomialList.size() == 1) {
			System.out.println("There is only one term left " + polynomialList.get(0).toString() );
			return true;
		}
		Polynomial.PolynomialTerm p1, p2;
		
		int index=0;
		int comparisonInt = 0;
		
		
		for(index=0; index < polynomialList.size()-1;index++) {
			p1 = polynomialList.get(index).leadingTerm;
			p2 = polynomialList.get(index + 1).leadingTerm;
			
			do {
				
				if(p1.getNextTerm()==null) {//p1 is null
					if(p2.getNextTerm() ==null) {//p1 and p2 are null
						comparisonInt = 0;
						break;
					}else {//p1 is null p2 is not null
						comparisonInt =-1;
						break;
					}
				}else if(p2.getNextTerm() == null) {//p1 is not null p2 is null
					comparisonInt = 1;
					break;
				}
				
				comparisonInt = compareByExponent.compare(p1, p2);
				if(comparisonInt > 0) {
					return false;
				}else if(comparisonInt < 0) {
					break ;
				}
				
				p1=p1.getNextTerm();
				p2=p2.getNextTerm();
				
			}while(p1.getNextTerm()!=null || p2.getNextTerm() != null);
			
			if(comparisonInt > 0) {
				return false;
			}else if(comparisonInt < 0) {
				
			}
			
		}
		//by now comparisonInt should be negative
		if(comparisonInt <=0) {
			return true;
		}else {
			return false;
		}
		
	}
}
