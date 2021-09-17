package view;

import java.util.ArrayList;
import java.util.Scanner;

import controller.foodManager;
import model.food;

public class main {
	static ArrayList<food> listFood = new ArrayList<food>();
	static foodManager fm = new foodManager();

	public static void main(String[] args) {
		System.out.println("Welcome to Food Management - @2021 by <SE150627 - Tran Thien Quoc Anh>");
		while (true) {
			menu();
		}
	}

	public static void menu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("---------------MENU-------------");
		System.out.println("1. Add a new food");
		System.out.println("2. Search a food by name");
		System.out.println("3. Remove the food by ID");
		System.out.println("4. Print the food list in the descending order of expired date");
		System.out.println("5. Save File");
		System.out.println("6. Quit");
		System.out.println("----------------------------------");
		System.out.print("** Enter your choice: ** : ");
		int choose = sc.nextInt();
		switch (choose) {
		case 1: // add new food
			fm.add();
			break;
		case 2: // Search a food by name
			fm.timkiem();
			break;
		case 3: // Remove the food by ID
			fm.xoa();
			break;
		case 4:
			fm.xuat();
			break;
		case 5:
			// lưu file
			fm.luuFile();
			break;
		case 6: // Quit
			fm.exit();
			break;
		case 7:
			fm.timKiemByDate();
			break;
		case 8:
			fm.sapXepWeight();
			break;
		default:
			System.err.println("Your choice is not valid !!");
		}
	}

}
