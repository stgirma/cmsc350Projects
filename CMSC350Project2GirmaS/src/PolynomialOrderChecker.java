import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

public class PolynomialOrderChecker {
	static ArrayList<String> inputPolynomialStringList;//read from file into arrayList
	//static ArrayList<Polynomial> polynomialList;//arraylist of Polynomial objects
	

	
	public static void main(String[] args) throws IOException {
		Polynomial polynomial;
		ArrayList<Polynomial> polynomialList = new ArrayList<Polynomial>();
		Scanner scanner;
		boolean strongOrder, weakOrder;
		File inputFile=null;

		

	       //JFileChooser fileSelector = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
	       JFileChooser fileSelector = new JFileChooser("C:\\Users\\senay\\Documents\\umgc\\CMIS 350 Data Structures and Analysis\\docs\\programExamples\\Project2 Collab\\test");
	       int result = fileSelector.showOpenDialog(null);       
	       if (result == JFileChooser.APPROVE_OPTION)
	    	   inputFile = fileSelector.getSelectedFile();
	       else
	       {
	           JOptionPane.showMessageDialog(null, "No file selected. Please select a file to read Polynomials from");
	           System.exit(1);
	       }
		
	     
	       scanner = new Scanner(inputFile);
	       while(scanner.hasNextLine()) {
				try {
					
					polynomial = new Polynomial(scanner.nextLine());
					if(!polynomial.areExponentSorted(polynomial.leadingTerm))
						throw new InvalidPolynomialSyntax("The polynomial's exponents are not sorted in ascending order");
					polynomialList.add(polynomial);
					System.out.println(polynomial.toString());		
					
				}catch(InvalidPolynomialSyntax e1) {
					JOptionPane.showMessageDialog(new JFrame(), e1.toString(), "Dialog", JOptionPane.ERROR_MESSAGE);
					System.exit(1);
				}catch(NumberFormatException e2) {
					JOptionPane.showMessageDialog(new JFrame(), e2.toString(), "Dialog", JOptionPane.ERROR_MESSAGE);
					System.exit(1);
				}
				
			}
	       
	      System.out.println(OrderedList.checkSorted(polynomialList));
	      System.out.println(OrderedList.checkSorted(polynomialList, compareByExponent));
	      

		}
	
	
	public static Comparator<Polynomial.PolynomialTerm> compareByExponent = new Comparator<Polynomial.PolynomialTerm>() {

		@Override
		public int compare(Polynomial.PolynomialTerm pt1, Polynomial.PolynomialTerm pt2) {
			return pt1.getExponent() - pt2.getExponent();
		}
    	  
      };
	
	
	}		

	
	/**
	 * selectFile launches a GUI file chooser to select user file selection. The method runs a while loop to ensure a file is selected.
	 * if no valid selection is provided, an error dialog shows and prompts the user to select file again until valid file selection is done
	 * selected file is returned to caller method.
	 * 
	 */

//	private static Scanner selectFile() {
//		Scanner scanner;
//		File inputFile = null;
//		try {
//		JFileChooser fileSelector = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
//		inputFile = fileSelector.getSelectedFile();
//		scanner = new Scanner(inputFile);
//		}catch(FileNotFoundException e1) {
//			throw new FileNotFoundException();
//		}
//		
//		return scanner;
//		
//	}
	
	/*private static File selectFile() {
		
		
		
		//Initialize input file to null and run loop until valid file replaces inputFile. 
		File inputFile = null;

		try {
			JFileChooser fileSelector = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			inputFile = fileSelector.getSelectedFile();
		}catch(FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Take 2: You have not selected a file.");
		}
		
		
			JFileChooser fileSelector = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			
			int selectionStatus = fileSelector.showOpenDialog(null);
			
			if(selectionStatus == 0) {
				//file is selected so assign inputFile to the selected file
				inputFile = fileSelector.getSelectedFile();
				
			}else if(selectionStatus ==1) {
				//file is not selected so show error dialog
				JOptionPane.showMessageDialog(null, "Take 2: You have not selected a file.");
			}

		
		return inputFile;
	}
	*/
	
	/**
	 * buildArrayListOfPolynomial reads the input file and builds the arryList of polynomials from the file content. Values are first
	 * validated to ensure polynomial syntax is correct, if not exception is thrown. Returns the arryList of Polynomials.
	 * 
	 */
	/*

	private static ArrayList<Polynomial> buildArrayListOfPolynomial() {
		//define the individual polynomial object
		Polynomial polynomial;
		//define and instantiate arrayList to hold the polynomials read from file
		ArrayList<Polynomial> polynomialList = new ArrayList<Polynomial>();
		 
		File inputFile = selectFile();		
		
		try {
			scanner = new Scanner(inputFile);
			while(scanner.hasNextLine()) {
				//read 1 line (polynomial) at a time. Coefficient should be double and exponent int 
				try {
					polynomial = new Polynomial(scanner.nextLine());
					polynomialList.add(polynomial);
					System.out.println(polynomial.toString());
				}catch(InvalidPolynomialSyntax e1) {
					JOptionPane.showMessageDialog(new JFrame(), e1.toString(), "Dialog", JOptionPane.ERROR_MESSAGE);
					return null;
				}catch(NumberFormatException e2) {
					JOptionPane.showMessageDialog(new JFrame(), e2.toString(), "Dialog", JOptionPane.ERROR_MESSAGE);
					return null;
				}
				
				
			}
			
		}catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "File you selected was not fount. Please try again");
		}

		

		return polynomialList;
	}
	
*/	
	

