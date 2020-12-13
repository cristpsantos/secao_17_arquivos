package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.entities.Product;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		List<Product> list = new ArrayList<>();
		
		System.out.println("Digite o caminho do arquivo: ");
		String strPath = sc.nextLine();
		sc.close();
		
		File path = new File(strPath);
		String parent = path.getParent();
		boolean success = new File(parent + "\\out").mkdir();
		String newFile = parent + "\\out\\summary.csv";
		System.out.println(newFile);
				
		try(BufferedReader br = new BufferedReader(new FileReader(strPath))) {
			String line = br.readLine();
			
			while(line != null) {
				String[] vect = line.split(",");
				String name = vect[0];
				Double price = Double.parseDouble(vect[1]);
				Integer quantity = Integer.parseInt(vect[2]);
				
				list.add(new Product(name, price, quantity));		
				
				line = br.readLine();
			}
		}
		catch(IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(newFile,true))) {
			
			
			for(Product lista : list ) {
				bw.write(lista.getName() + ", " + String.format("%.2f", lista.total()));
				bw.newLine();
			}
			
		}
		catch(IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		
	}

}
