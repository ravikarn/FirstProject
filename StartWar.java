package com;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StartWar {
	public static void main(String[] args) throws FileNotFoundException {

		String filePath="input.txt";
		File file = new File(filePath);
		Scanner sc = new Scanner(file);
		int input=0;
		Map<String,Integer> decodingMap=new HashMap<>();

		for(int i=1;i<=26;i++) {
			decodingMap.put(Character.toString((char)(i+64)), i);
		}




		int column=0;
		int row=0;
		int totalNumberOfShips=0;
		int numberOfPShips=0;
		int numberOfQShips=0;

		int[][] playerA=new int[row][];
		int[][] playerB=new int[row][];

		boolean flagOfA=true;
		while(sc.hasNextLine()){
			String line = sc.nextLine();
			if(0==input) {
				String[] inputArray=line.split(" ");
				column=Integer.parseInt(inputArray[0]);
				row=decodingMap.get(inputArray[1]);


				playerA=new int[row][];
				playerB=new int[row][];

			}else if(1==input) {
				totalNumberOfShips=Integer.parseInt(line);

			}else{


				String[] inputArray=line.split(" ");
				int lengthOfInputArray=inputArray.length;

				String typeOfShip=inputArray[0];

				int columnDimensionOfShip=0;
				int rowDimensionOfShip=0;

				if("P".equalsIgnoreCase(typeOfShip) || "Q".equalsIgnoreCase(typeOfShip)) {
					numberOfPShips=((lengthOfInputArray)-3)/2;
					columnDimensionOfShip=Integer.parseInt(inputArray[1]);
					rowDimensionOfShip=Integer.parseInt(inputArray[2]);
					
					
					boolean shipB=false;
					
					for(int i=3;i<lengthOfInputArray;i++) {
						String[] positionOfShip=inputArray[i].split("");
						
						if(shipB) {
							System.out.println("B postion---"+positionOfShip[0]+""+positionOfShip[1]);
							shipB=false;
						}else {
							System.out.println("A postion---"+positionOfShip[0]+""+positionOfShip[1]);
							shipB=true;
						}
					}
					
					System.out.println("number of "+typeOfShip+" is -"+numberOfPShips+" having row "+rowDimensionOfShip+" and column "+columnDimensionOfShip);
					


				}else {

					if(flagOfA) {
						System.out.println("A attack plan- "+line);
						flagOfA=false;
					}else {
						System.out.println("B attack plan- "+line);
						flagOfA=true;
					}
				}

			}

			input++;

		}




	}

}
