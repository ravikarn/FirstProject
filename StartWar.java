package com;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class StartWar {
	static Map<String,Integer> decodingMap=new HashMap<>();

	static void startGame(int[][] playerA, int[][] playerB, int strengthOfPlayerA, int strengthOfPlayerB, Map<String, String> attackPlan) {
		String[] playerAPlan=attackPlan.get("A").split(" ");
		String[] playerBPlan=attackPlan.get("B").split(" ");

		boolean trunOfA=true;

		int playerACounter=0;
		int playerBCounter=0;
		int strikeLengthOfA=playerAPlan.length;
		int strikeLengthOfB=playerBPlan.length;
		boolean exhaustA=false;
		boolean exhaustB=false;
		boolean gameEnd=false;

		while(strengthOfPlayerA>0 && strengthOfPlayerB>0 && !gameEnd) {

			if(!(exhaustA && exhaustB)) {



				if(trunOfA) {

					if(playerACounter<strikeLengthOfA) {




						String attackPoint=playerAPlan[playerACounter];
						String[] attackPointRowColumn=attackPoint.split("");

						int row=(decodingMap.get(attackPointRowColumn[0]))-1;
						int column=(Integer.parseInt(attackPointRowColumn[1]))-1;

						int strikeValueForA=playerB[row][column];

						if(0==strikeValueForA) {
							System.out.println("Player-1 fires a missile with target "+attackPoint+" which got miss");

							trunOfA=false;
						}else {
							playerB[row][column]--;
							System.out.println("Player-1 fires a missile with target "+attackPoint+" which got hit");
							strengthOfPlayerB--;
							if(0==strengthOfPlayerB) {
								System.out.println("Player-1 won the battle");
							}
							
							trunOfA=true;
						}



						playerACounter++;

					}else {
						exhaustA=true;
						trunOfA=false;
						System.out.println("Player-1 has no missiles left to launch");
					}
				}else {


					if(playerBCounter<strikeLengthOfB) {



						String attackPoint=playerBPlan[playerBCounter];
						String[] attackPointRowColumn=attackPoint.split("");

						int row=(decodingMap.get(attackPointRowColumn[0]))-1;
						int column=(Integer.parseInt(attackPointRowColumn[1]))-1;

						int strikeValueForB=playerA[row][column];

						if(0==strikeValueForB) {
							System.out.println("Player-2 fires a missile with target "+attackPoint+" which got miss");

							trunOfA=true;
						}else {
							playerA[row][column]--;
							System.out.println("Player-2 fires a missile with target "+attackPoint+" which got hit");
							strengthOfPlayerA--;
							
							if(0==strengthOfPlayerA) {
								System.out.println("Player-2 won the battle");
							}
							
							trunOfA=false;
						}

						playerBCounter++;
					}else {
						exhaustB=true;
						trunOfA=true;
						System.out.println("Player-2 has no missiles left to launch");
					}


				}
			}else {
				gameEnd=true;
			}

		}
	}

	public static void main(String[] args) throws FileNotFoundException {

		String filePath="input.txt";
		File file = new File(filePath);
		Scanner sc = new Scanner(file);
		int input=0;
		int totalNumberOfShips=0;


		int strengthOfPlayerA=0;
		int strengthOfPlayerB=0;





		for(int i=1;i<=26;i++) {
			decodingMap.put(Character.toString((char)(i+64)), i);
		}
		int column=0;
		int row=0;
		Map<String,String> attackPlan=new HashMap<>(); 

		int[][] playerA = null;
		int[][] playerB = null;

		boolean flagOfA=true;
		while(sc.hasNextLine()){
			String line = sc.nextLine();
			if(0==input) {
				String[] inputArray=line.split(" ");
				column=Integer.parseInt(inputArray[0]);
				row=decodingMap.get(inputArray[1]);
				playerA=new int[row][column];
				playerB=new int[row][column];

			}else if(1==input) {
				totalNumberOfShips=Integer.parseInt(line);
			}else{
				String[] inputArray=line.split(" ");

				String typeOfShip=inputArray[0];

				if("P".equalsIgnoreCase(typeOfShip) || "Q".equalsIgnoreCase(typeOfShip)) {
					int lengthOfInputArray=inputArray.length;
					int columnDimensionOfShip=0;
					int rowDimensionOfShip=0;

					int costOfShip=("P".equalsIgnoreCase(typeOfShip))?1:2;

					columnDimensionOfShip=Integer.parseInt(inputArray[1]);
					rowDimensionOfShip=Integer.parseInt(inputArray[2]);
					boolean shipB=false;

					for(int i=3;i<lengthOfInputArray;i++) {
						String[] positionOfShip=inputArray[i].split("");
						int shipRowStart=(decodingMap.get(positionOfShip[0]))-1;
						int shipColumnStart=(Integer.parseInt(positionOfShip[1]))-1;
						if(shipB) {
							for(int j=shipRowStart;j<(shipRowStart+rowDimensionOfShip);j++) {
								for(int k=shipColumnStart;k<(shipColumnStart+columnDimensionOfShip);k++) {
									playerB[j][k]=costOfShip;
									strengthOfPlayerB=strengthOfPlayerB+costOfShip;
								}
							}
							shipB=false;
						}else {

							for(int j=shipRowStart;j<(shipRowStart+rowDimensionOfShip);j++) {
								for(int k=shipColumnStart;k<(shipColumnStart+columnDimensionOfShip);k++) {
									playerA[j][k]=costOfShip;
									strengthOfPlayerA=strengthOfPlayerA+costOfShip;
								}
							}
							shipB=true;
						}
					}
				}else {
					if(flagOfA) {

						attackPlan.put("A", line);

						flagOfA=false;
					}else {
						attackPlan.put("B", line);
						flagOfA=true;
					}
				}
			}
			input++;
		}


		startGame(playerA,playerB,strengthOfPlayerA,strengthOfPlayerB,attackPlan);

	}
}
