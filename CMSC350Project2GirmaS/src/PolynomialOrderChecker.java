
/**
 * PolynomialOrderChecker.java - This program accepts a file with a list of polynomials and checks if the polynomials are ordered
 * correctly. This class prompts user to input a file.  
 * 
 * @author  Girma, Senay
 * @version 1.0 10/24/2022
 * Project 2
 */
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

public class PolynomialOrderChecker {
	static ArrayList<String> inputPolynomialStringList;// read from file into arrayList

	public static void main(String[] args) throws IOException {
		Polynomial polynomial;
		ArrayList<Polynomial> polynomialList = new ArrayList<Polynomial>();
		Scanner scanner;
		File inputFile = null;

		JFileChooser fileSelector = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int result = fileSelector.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION)
			inputFile = fileSelector.getSelectedFile();
		else {
			JOptionPane.showMessageDialog(null, "No file selected. Please select a file to read Polynomials from");
			System.exit(1);
		}

		scanner = new Scanner(inputFile);
		while (scanner.hasNextLine()) {
			try {

				polynomial = new Polynomial(scanner.nextLine());
				if (!polynomial.areExponentSorted(polynomial.leadingTerm))
					throw new InvalidPolynomialSyntax("The polynomial's exponents are not sorted in ascending order");
				polynomialList.add(polynomial);
				System.out.println(polynomial.toString());

			} catch (InvalidPolynomialSyntax e1) {
				JOptionPane.showMessageDialog(new JFrame(), e1.toString(), "Dialog", JOptionPane.ERROR_MESSAGE);
				System.exit(1);
			} catch (NumberFormatException e2) {
				JOptionPane.showMessageDialog(new JFrame(), e2.toString(), "Dialog", JOptionPane.ERROR_MESSAGE);
				System.exit(1);
			}

		}

		System.out.println(OrderedList.checkSorted(polynomialList));
		System.out.println("comparing with comarator: " + OrderedList.checkSorted(polynomialList, compareByExponent));

	}

	public static Comparator<Polynomial.PolynomialTerm> compareByExponent = 
			(Polynomial.PolynomialTerm pt1, Polynomial.PolynomialTerm pt2) -> pt1.getExponent() - pt2.getExponent();

}
