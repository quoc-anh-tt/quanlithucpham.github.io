package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

import model.food;

public class fileIO {
	static Scanner sc = new Scanner(System.in);

	private static final String file_name = "D:\\SourceCode_Eclip\\ASM1_LAB\\";

	public static boolean readFlie(ArrayList<food> f) {
		try {
			System.out.print("Enter the file name you want to save: ");
			String str = sc.nextLine();
			File file = new File(file_name + str);
			if (file.createNewFile()) {
				System.err.println("File creation successful !");
			} else {
				System.err.println("File creation failed !");
			}
			FileOutputStream fos = new FileOutputStream(str + ".txt");
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			BufferedWriter bfw = new BufferedWriter(osw);
			for (food food1 : f) {
				String line = food1.getId() + " | " + food1.getName() + " | " + food1.getWeight() + " | "
						+ food1.getType() + " | " + food1.getPlace() + " | " + food1.getExp();
				bfw.write(line);
				bfw.newLine();
			}
			bfw.close();
			osw.close();
			fos.close();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
