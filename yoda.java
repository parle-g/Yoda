package yoda;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.sql.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class yoda {
	public TreeMap<Integer,Integer> custVisit = new TreeMap<Integer,Integer>();
	//    public TreeMap<Integer,Integer> Highesttransaction = new TreeMap<Integer,Integer>();
	public TreeMap<Integer,Integer> HighestSellingBook = new TreeMap<Integer,Integer>();
	public HashMap<String,Integer> bookPrice = new HashMap<String, Integer>();

	public static Map sortByValue(Map unsortedMap) {
		Map sortedMap = new TreeMap(new ValueComparator(unsortedMap));
		sortedMap.putAll(unsortedMap);
		return sortedMap;
	}


	public static void FrequentCustomerVisit(TreeMap<String, Integer> custVisit, int N) {
		Map<String, Integer> cust = sortByValue(custVisit);
		Iterator<String> iterator =  cust.keySet().iterator();

		int i = 0;
		System.out.println("Top "+N+" frequent customers are:");
		String CustID = null;
		while (iterator.hasNext()) {
			CustID = iterator.next();
			System.out.println("CustomerID: " + CustID + " Visits: "+ cust.get(CustID));
			i++;
			if(i == N ){
				break;
			}

		}


	}

	public static void highestTransactionCustomers(TreeMap<String, Integer> highTrans, int N) {
		Map<String, Integer> cust = sortByValue(highTrans);
		Iterator<String> iterator =  cust.keySet().iterator();

		int i = 0;
		System.out.println("Top "+N+" customers having maximum transaction amounts are: ");
		String CustID = null;
		while (iterator.hasNext()) {
			CustID = iterator.next();
			System.out.println("CustomerID: " + CustID + " Transaction Amount: "+ cust.get(CustID));
			i++;
			if(i == N ){
				break;
			}

		}
	}

	public static void MaximumSellingBooks(TreeMap<String, Integer> maxSellingBooks, int N) {
		Map<String, Integer> books = sortByValue(maxSellingBooks);
		Iterator<String> iterator =  books.keySet().iterator();

		int i = 0;
		System.out.println("Top "+N+" customers having maximum transaction amounts are: ");
		String bookID = null;
		while (iterator.hasNext()) {
			bookID = iterator.next();
			System.out.println("Book ID: " + bookID + " Copies Sold: "+ books.get(bookID));
			i++;
			if(i == N ){
				break;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		TreeMap<String,Integer> custVisit = new TreeMap<String,Integer>();
		TreeMap<String,Integer> Highesttransaction = new TreeMap<String,Integer>();
		TreeMap<String,Integer> HighestSellingBook = new TreeMap<String,Integer>();
		HashMap<String,Integer> bookPrice = new HashMap<String, Integer>();
		int num = 0;
		int price = 0;

		BufferedReader br = null;

		int i = 0;

		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader("price.list.txt"));

			while ((sCurrentLine = br.readLine()) != null) {

				String[] arr = sCurrentLine.split(",");
				bookPrice.put(arr[0], Integer.valueOf(arr[1]));

			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader("transaction.list.txt"));

			while ((sCurrentLine = br.readLine()) != null) {

				String[] arr = sCurrentLine.split(",");
				//				System.out.println(arr[1]);
				if(custVisit.containsKey(arr[0])){
					num = custVisit.get(arr[0]);
					num ++;
					custVisit.put(arr[0], num);
					num = 0;
				}
				else {
					custVisit.put(arr[0], 1);
				}
				price = 0;
				for(i = 1 ; i < arr.length; i++) {
					price = price + bookPrice.get(arr[i]);
					num = 0;
					if(HighestSellingBook.containsKey(arr[i])){
						num = HighestSellingBook.get(arr[i]);
						num ++;
						HighestSellingBook.put(arr[i], num);
					}
					else {
						HighestSellingBook.put(arr[i], 1);
					}
				}
				num = 0;
				if(Highesttransaction.containsKey(arr[0])){
					num = Highesttransaction.get(arr[0]);
					num = num + price;
					Highesttransaction.put(arr[0], num);
				}
				else {
					Highesttransaction.put(arr[0], price);
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		while(true){
			System.out.println("Please select one of the option:");
			System.out.println("========");
			System.out.println("Press 1: frequent customers and the number of visits they made");
			System.out.println("Press 2: highest transactions and the corresponding customerid");
			System.out.println("Press 3: highest selling books and quantity sold");
			System.out.println("========");


			Scanner sc=new Scanner(System.in);
			int option = sc.nextInt();
			System.out.println(option);
			System.out.println();
			System.out.println("How many number of top counts you want to see for the selected option ?");
			sc=new Scanner(System.in);
			int N = sc.nextInt();
			System.out.println(N);
			System.out.println();

			if(option == 1)
				FrequentCustomerVisit(custVisit, N);
			else if(option == 2)
				highestTransactionCustomers(Highesttransaction,N);
			else if (option == 3)
				MaximumSellingBooks(HighestSellingBook, N);
			else
				System.out.println("Please select a valid option again");
			System.out.println();
			System.out.println();
			System.out.println("***********Try New Query************");
		}
	}

}

