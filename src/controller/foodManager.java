package controller;

import java.awt.List;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

import model.food;

public class foodManager {

	Scanner sc = new Scanner(System.in);
	private ArrayList<food> listFood = new ArrayList<food>();

	public boolean checkTrung(int a) {
		for (int i = 0; i < listFood.size(); i++) {
			if (listFood.get(i).getId() == a) {
				return true;
			}
		}
		return false;
	}

	public boolean checkSo(String a) {
		try {
			Integer.parseInt(a);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public boolean checkSoTrongChuoi(String a) {
		for (int i = 0; i < a.length(); i++) {
			if (checkSo(a.charAt(i) + "")) {
				return true;
			}
		}
		return false;
	}

	// check ID
	public int nhapID() {
		while (true) {
			try {
				System.out.print("Enter id: ");
				int id = Integer.parseInt(sc.nextLine());
				return id;
			} catch (NumberFormatException e) {
				System.err.println("ID not valid !");
			}
		}
	}

	// check weight
	public int nhapWeight() {
		while (true) {
			try {
				System.out.println("Enter weight [1-10000] (g):");
				int wei = Integer.parseInt(sc.nextLine());
				while (wei < 1 || wei > 10000) {
					System.err.println("Weight in the range [1..10000]");
					System.out.println("Re-enter weight:");
					wei = Integer.parseInt(sc.nextLine());
				}
				return wei;
			} catch (NumberFormatException e) {
				System.err.println("Weight not valid!");
			}
		}
	}

	// check place
	public int nhapPlace() {
		while (true) {
			try {
				System.out.print("Enter place (1:Freeze, 2: Fresh, 3: Cool): ");// chưa check
				int placeT = Integer.parseInt(sc.nextLine());
				while (placeT < 1 || placeT > 3) {
					System.err.println("Enter 1, 2 or 3");
					System.out.print("Re-enter place (1, 2, 3): ");
					placeT = Integer.parseInt(sc.nextLine());
				}
				return placeT;
			} catch (NumberFormatException e) {
				System.err.println("Place not valid");
			}
		}
	}

	// check EXP
	public boolean checkExp(String date) {
		return date.matches(
				"^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");
		// source :
		// https://stackoverflow.com/questions/15491894/regex-to-validate-date-format-dd-mm-yyyy-with-leap-year-support
	}

	// xử lí EXP
	public int CompareTwoDatesTest(String date) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDateTime now = LocalDateTime.now();
		SimpleDateFormat sdformat = new SimpleDateFormat("dd/MM/yyyy");
		Date d1 = null;
		try {
			d1 = sdformat.parse(date);
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		Date d2 = null;
		try {
			d2 = sdformat.parse(dtf.format(now));
		} catch (ParseException ex) {
			ex.printStackTrace();
		}

		if (d1.compareTo(d2) > 0) {
			return 1; // còn hạn sử dụng
		} else if (d1.compareTo(d2) < 0) {
			return -1; // đã hết hạn
		} else if (d1.compareTo(d2) == 0) {
			return 2; // hạn hôm nay
		}
		return 0;
	}

	// nhap Exp
	public String nhapExp() {
		try {
			System.out.print("Enter expired date (dd/MM/yyyy): ");
			String startDateString = sc.nextLine();
			if (startDateString.isEmpty()) {
				System.err.println("No blank");
				System.out.print("Enter exp: ");
				startDateString = sc.nextLine();
				while (!checkExp(startDateString) || CompareTwoDatesTest(startDateString) == -1) {
					System.err.println("Expired date not valid !");
					System.out.print("Re-enter expired date (dd/MM/yyyy): ");
					startDateString = sc.nextLine();
				}
			}
			while (!checkExp(startDateString) || CompareTwoDatesTest(startDateString) == -1) {
				System.out.print("Re-enter exp: ");
				startDateString = sc.nextLine();
			}
			return startDateString;
		} catch (Exception e) {
			return "00/00/0000"; // null
		}
	}

	// add new
	public void add() {
		while (true) {
			food f = new food();
			System.out.println("Please enter product information... ");
			System.out.println("		- - -		 ");
			// ID
			int id = nhapID();
			while (checkTrung(id)) {
				System.err.println("ID already exists. Please re-enter another ID..");
				System.out.print("Re-enter ID ");
				id = Integer.parseInt(sc.nextLine());
			}
			f.setId(id);
			// name
			System.out.print("Enter name: ");
			String name = sc.nextLine();
			while (name.isEmpty()) {
				System.err.println("No blank..");
				System.out.print("Re-enter name: ");
				name = sc.nextLine();
			}
			while (checkSoTrongChuoi(name)) {
				System.err.println("Wrong name format..");
				System.out.print("Re-enter name: ");
				name = sc.nextLine();
			}
			f.setName(name);
			// weight
			int weight = nhapWeight();
			f.setWeight(weight);
			// type
			System.out.print("Enter type: ");
			String type = sc.nextLine();
			while (type.isEmpty()) {
				System.err.println("No blank !");
				System.out.print("Re-enter type: ");
				type = sc.nextLine();
			}
			while (checkSoTrongChuoi(type)) {
				System.err.println("Enter the wrong format..");
				System.out.print("Re-enter type: ");
				type = sc.nextLine();
			}
			f.setType(type);
			// place
			int place = nhapPlace();
			f.setPlace(place);
			// exp
			String exp = nhapExp();
			f.setExp(exp);
			listFood.add(f);
			System.out.print("Do you want continue ?(y/n): ");
			String s = sc.nextLine();
			if (s.equalsIgnoreCase("n"))
				break;
		}
	}

	// sap xep
	public void sapXepTheoExp() {
		Collections.sort(listFood, new SortByExp());
	}

	// in
	public void xuat() {
		int sum = 0;
		// check empty
		if (listFood.isEmpty()) {
			System.err.println("The list is empty. Please enter product information !");
			return;
		}
		// sap xep
		sapXepTheoExp();
		// in ra danh sach
		System.out.format("%-15s | ", "ID");
		System.out.format("%-15s | ", "Name");
		System.out.format("%-15s | ", "Weight");
		System.out.format("%-15s | ", "Type");
		System.out.format("%-15s | ", "Place");
		System.out.format("%-15s | ", "Expired date");
		System.out.println("");
		for (food food : listFood) {
			food.xuat();
		}
		System.out.println("Total " + listFood.size() + " product(s)");
	}

	// tìm kiếm
	public void timkiem() {
		while (true) {
			boolean flag = false;
			System.out.print("Enter the search name: ");
			String ten_tim = sc.nextLine();
			while (ten_tim.isEmpty()) {
				System.err.println("No blank");
				System.out.print("Re-enter the search name:");
				ten_tim = sc.nextLine();
			}
			if (listFood.isEmpty()) {
				System.err.println("The list is empty. Please enter product information !");
				return;
			}
			boolean bl = false;
			for (int i = 0; i < listFood.size(); i++) {
				if (listFood.get(i).getName().contains(ten_tim)) {
					if (!bl) {
						System.out.format("%-15s | ", "ID");
						System.out.format("%-15s | ", "Name");
						System.out.format("%-15s | ", "Weight");
						System.out.format("%-15s | ", "Type");
						System.out.format("%-15s | ", "Place");
						System.out.format("%-15s | ", "Expired date");
						System.out.println("");
						bl = true;
					}
					listFood.get(i).xuat();
					flag = true;
				}
			}
			if (!flag) {
				System.err.println("No products in the list !");
			}
			System.out.print("Do you want to continue searching? (Y/N) ");
			String b = sc.nextLine();
			if (b.equalsIgnoreCase("n")) {
				return;
			}
		}
	}

	// timkiem theo date
	public void timKiemByDate() {
		while (true) {
			boolean flag = false;
			System.out.print("Enter the search date: ");
			String date_tim = sc.nextLine();
			while (date_tim.isEmpty()) {
				System.err.println("No blank");
				System.out.print("Re-enter the search name:");
				date_tim = sc.nextLine();
			}
			if (listFood.isEmpty()) {
				System.err.println("The list is empty. Please enter product information !");
				return;
			}
			boolean flag3 = false;
			for (int i = 0; i < listFood.size(); i++) {
				if (listFood.get(i).getExp().equalsIgnoreCase(date_tim)) {
					if (!flag3) {
						System.out.format("%-15s | ", "ID");
						System.out.format("%-15s | ", "Name");
						System.out.format("%-15s | ", "Weight");
						System.out.format("%-15s | ", "Type");
						System.out.format("%-15s | ", "Place");
						System.out.format("%-15s | ", "Expired date");
						System.out.println("");
						flag3 = true;
					}
					sapXepTheoExp();
					listFood.get(i).xuat();
					flag = true;
				}
			}
			if (!flag) {
				System.err.println("No products in the list !");
			}
			System.out.print("Do you want to continue searching? (Y/N) ");
			String b = sc.nextLine();
			if (b.equalsIgnoreCase("n")) {
				return;
			}
		}
	}

	// xoa
	public void xoa() {
		boolean flag2 = false;
		System.out.println("Remove the food by ID..");
		int code_xoa = nhapID();
		if (listFood.isEmpty()) {
			System.err.println("The list is empty. Please enter product information !");
			return;
		}
		// xác nhận xoá
		System.out.print("Are you sure you want to delete it? (Y/N) ");
		String y = sc.nextLine();
		if (y.equalsIgnoreCase("n")) {
			System.err.println("Deleted fail !");
			return;
		}
		for (int i = 0; i < listFood.size(); i++) {
			if (listFood.get(i).getId() == code_xoa) {
				listFood.remove(i);
				System.err.println("Deleted successfully !");
				flag2 = true;
			}
		}
		if (!flag2) {
			System.err.println("This product is not in the list !");
			System.err.println("Deleted fail !");
		}
	}

	// lưu file
	public void luuFile() {
		fileIO.readFlie(listFood);
	}

	public void exit() {
		System.out.print("Are you sure to exit the program? (Y/N) ");
		String str = sc.nextLine();
		if (str.equalsIgnoreCase("n")) {
			return;
		}
		System.err.println("GOODBYE !!");
		System.exit(0);
	}

	public void sapXepWeight() {
		Collections.sort(listFood, new SortByWeight());
		for (int i = 0; i < listFood.size(); i++) {
			listFood.get(i).xuat();
		}
	}
}
