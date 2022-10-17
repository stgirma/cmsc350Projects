import java.util.Iterator;

public class Polynomial implements Iterable<Polynomial.PolynomialTerm>, Comparable<Polynomial> {

	private String inputPolynomialString;// string read from file
	public PolynomialTerm leadingTerm;// represents the head node of the linked list

	/**
	 * Polynomial constructor instantiates a polynomial object from string that represents coefficients 
	 * and exponents read from file
	 * 
	 */
	public Polynomial(String inputPolynomialString) {
		//split the string parameter and iterate through the array and create PolynomialTerm from each pair
		this.inputPolynomialString = inputPolynomialString;
		String[] polynomialStringList = inputPolynomialString.split("\\s+");
		
		//validate we have coefficient - exponent pairs (length is even)
		if(polynomialStringList.length % 2==0) {
			try {
				for(int i = 0; i<polynomialStringList.length; i+=2) {
				double coefficient = Double.parseDouble(polynomialStringList[i]);
				int exponent = Integer.parseInt(polynomialStringList[i+1]);
				
				apendPolynomialTerm(coefficient, exponent);
				}	
				}catch(NumberFormatException e) {
					throw new InvalidPolynomialSyntax("A non-numerical character is suplied");
					//throw e;
				}
			}else {
			throw new InvalidPolynomialSyntax("A coefficient or an exponent is missing");
			}
			
	}
	

	/**
	 * PolynomialTerm constructor creates a term with parameter coefficient and
	 * exponent. nextTerm is set as null during instantiation.
	 * 
	 */
	public static class PolynomialTerm {
		private double coefficient;
		private int exponent;
		private PolynomialTerm nextTerm;

		PolynomialTerm(double coefficient, int exponent) {
			this.coefficient = coefficient;
			this.exponent = exponent;
			this.nextTerm = null;
		}

		public double getCoefficient() {
			return coefficient;
		}

		public int getExponent() {
			return exponent;
		}

		public PolynomialTerm getNextTerm() {
			return nextTerm;
		}

	}

	/**
	 * apendPolynomialTerm adds a term to a polynomial object. receives double and
	 * int for coefficient and exponent and adds to this.
	 * 
	 * @param double coefficient
	 * @param int    exponent
	 * 
	 */
	public void apendPolynomialTerm(double coefficient, int exponent) {
		PolynomialTerm newPolynomialTerm = new PolynomialTerm(coefficient, exponent);

		if (leadingTerm == null) {
			// leading term has not been instantiated: this is the leading tem
			leadingTerm = newPolynomialTerm;
		} else {
			// if leading term already exists, find the last polynomial term. Start from
			// leading term
			// iterate through until nextTerm is null

			PolynomialTerm lastPolynomialTerm = leadingTerm;
			while (lastPolynomialTerm.nextTerm != null) {
				// currentTerm has nextTerm, so go to the next
				lastPolynomialTerm = lastPolynomialTerm.nextTerm;
			}

			// Exited loop, now point lastPolynomialTerm's nextTerm property to the new
			// polynomialTetm
			lastPolynomialTerm.nextTerm = newPolynomialTerm;
		}

		// if leading term already exists

	}
	public boolean areExponentSorted(PolynomialTerm leadingTerm) {
		
		if(leadingTerm == null)
			return true;
		for(PolynomialTerm term = leadingTerm; term.nextTerm != null; term = term.nextTerm) {
			if(term.exponent < term.nextTerm.exponent)
				return false;
		}
		
		return true;
	}

	/**
	 * compareTo Overridden method to compare the exponents of this object and
	 * another Polynomial object provided in parameter
	 * 
	 * @param Polynomial object to compare to this object
	 * 
	 */
	@Override
	public int compareTo(Polynomial comparedPolynomial) {
		/*
		 * Iterate through each polynomial object comparing exponent to exponent and
		 * coefficient to coefficient of a given term If both exponent and coefficient
		 * of both polynomial's terms are equal, go to the next term. First Define
		 * iterators for both
		 */
		Iterator iterator1 = this.iterator();
		Iterator iterator2 = comparedPolynomial.iterator();
		int comparison;// comparison result (negative = this < comparedPolynomial, 0
					   // this==comparedPolynomial, positive this>comparedPolynomia
		int thisExponent, comparedToExponent;
		double thisCoefficient, comparedToCoefficient;
		boolean areEqual = true;// house keeping

		do {

			if (iterator1.hasNext()) {
				if (iterator2.hasNext()) {
					// Both iterator1 and iterator2 have terms left: Compare exponents
					// since generic Iterator is used, next item need to be type casted

					String[] thisPolynomialnextTermArray = (String[]) iterator1.next();
					String[] comparedPolynomialnextTermArray = (String[]) iterator2.next();

					thisExponent = Integer.parseInt(thisPolynomialnextTermArray[0]);
					comparedToExponent = Integer.parseInt(comparedPolynomialnextTermArray[0]);

					thisCoefficient = Double.parseDouble(thisPolynomialnextTermArray[1]);
					comparedToCoefficient = Double.parseDouble(comparedPolynomialnextTermArray[1]);

					comparison = Integer.compare(thisExponent, comparedToExponent);

					if (comparison == 0) {
						// exponents are equal, compare coefficient
						comparison = Double.compare(thisCoefficient, comparedToCoefficient);
						if(comparison!=0) {
							areEqual = false;
						}
					} else {
						areEqual = false;

					}

				} else {
					// iterator1 has next but iterator2 does not have next
					areEqual = false;
					comparison = 1;
				}
			} else {
				// iterator1 doesn't have next but iterator2 has next
				comparison = -1;
				areEqual = false;
			}

		} while (areEqual);

		return comparison;

	}

	/**
	 * iterator implementation of the Iterable interface. It returns an Iterator
	 * object. the Iterator object comes a custom class defined below
	 * 
	 */
	@Override
	public Iterator iterator() {
		return new polynomialTermIterator(this);
	}

	/**
	 * polynomialIterator custom class to that performs the iterations defined in
	 * the Iterable interface. This class implements the methods hasNext() and
	 * next()
	 * 
	 */
	public static class polynomialTermIterator implements Iterator {
		// define a term that we'll be used at each iteration
		private PolynomialTerm currentTerm;
		// Polynomial object we're iterating over
		private Polynomial polynomial;

		public polynomialTermIterator(Polynomial polynomial) {
			// assign the polynomial that we're iterating over to the parameter provided
			// object
			this.polynomial = polynomial;

			// at instantiation, current term is the head node (leadingTerm)
			this.currentTerm = polynomial.leadingTerm;

		}

		/**
		 * hasNext overridden method to implement the method called for in the iterable
		 * interface. it returns true if polynomial term has next term. It returns false
		 * if next term is null.
		 * 
		 */
		@Override
		public boolean hasNext() {
			// return currentTerm.nextTerm != null;
			return currentTerm != null;
		}

		/**
		 * next overridden method to return an object that contains only exponent and
		 * coefficient (an Array object with 2 elements).
		 * 
		 */
		@Override
		public String[] next() {
			// Iterator.next need to return just the exponent(int) and coefficient(double)
			// so String array used
			String[] nextTermValues = new String[2];

			// check if the polynomial has any next term if not return null
			if (!this.hasNext()) {
				// throw exception
			} else {
				// copy currentTerm to return to caller before advancing currentTerm to nextTerm
				PolynomialTerm tempPolynomialTerm = currentTerm; //
				currentTerm = currentTerm.nextTerm;
				nextTermValues[0] = Integer.toString(tempPolynomialTerm.exponent);
				nextTermValues[1] = Double.toString(tempPolynomialTerm.coefficient);

				return nextTermValues;
			}
			return null;
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Iterator iterator = this.iterator();
		// int nextExponent;
		// double nextCoefficient;

		while (iterator.hasNext()) {
			String[] thisPolynomialnextTermArray = (String[]) iterator.next();
			String nextExponent = thisPolynomialnextTermArray[0];
			String nextCoefficient = thisPolynomialnextTermArray[1];
			//System.out.println(nextExponent.equals("0"));
			if (nextCoefficient.equals("0.0")) {
				// skip terms with 0 coefficient
			} else {
				// non-zero coefficient
				if (nextExponent.equals("0")) {
					
					// if exponent is 0, don't show any variable
					sb.append(nextCoefficient + " + ");
				} else if (nextExponent.equals("1")) {
					// if exponent is 1, don't show any exponent
					sb.append(nextCoefficient + "x + ");
				} else {
					// for all other exponent value
					sb.append(nextCoefficient + "x^" + nextExponent + " + ");
				}

			}
		}
		// polynomial has no more term, remove the last " + " from sb (3 characters)
		sb.deleteCharAt(sb.length() - 1);
		sb.deleteCharAt(sb.length() - 1);
		sb.deleteCharAt(sb.length() - 1);

		return sb.toString();
	}

}
