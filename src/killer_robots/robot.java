package killer_robots;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;


public class Robot {
	
	static Scanner input= new Scanner(System.in);
	
	String robotName;
	int robotHp;
	float posX;
	float posY;
	int viewRange;
	boolean killerRobot;
	boolean alive;
	
	
	public Robot (String robotName, int robotHp, int posX, int posY, int viewRange){
		this.robotName=robotName;
		this.robotHp=robotHp;
		this.posX=posX;
		this.posY=posY;
		this.viewRange=viewRange;
		this.killerRobot=true;
		this.alive=true;
	}
	
	public Robot (String robotName, int robotHp, int posX, int posY){
		this.robotName=robotName;
		this.robotHp=robotHp;
		this.posX=posX;
		this.posY=posY;
		this.alive=true;
	}
	
	public void setName(String robotName) {
		this.robotName=robotName;
	}
	
	public void setRobotHp(int robotHp) {
		this.robotHp=robotHp;
	}
	
	public void setPosX(float posX) {
		this.posX=posX;
	}
	
	public void setPosY(float posY) {
		this.posY=posY;
	}
	
	public void setViewRange(int viewRange) {
		this.viewRange=viewRange;
	}
	
	public void setKillerRobot(boolean killerRobot) {
		this.killerRobot=killerRobot;
	}
	
	public void setAliveStatus(boolean alive) {
		this.alive=alive;
	}
	
	
	public String getName(){
		return this.robotName;
	}
	
	public int getRobotHp() {
		return this.robotHp;
	}
	
	public float getPosX() {
		return this.posX;
	}
	
	public float getPosY() {
		return this.posY;
	}
	
	public int getViewRange() {
		return this.viewRange;
	}
	
	public boolean getKillerRobot() {
		return this.killerRobot;
	}
	
	public boolean getAliveStatus() {
		return this.alive;
	}
	
	public String toString() {
		return this.robotName + " " + this.robotHp + " " + this.posX + " " + this.posY + " " + this.viewRange;
	}
	
	public static void main(String[] args) {
		
		Robot killerRobot = new Robot(input.next(), input.nextInt(), input.nextInt(), input.nextInt(), input.nextInt());
		List <Robot> robotList= new ArrayList<>();
		int robotNumber=input.nextInt();
		for(int i=0; i<robotNumber; i++) 
			robotList.add(new Robot(input.next(), input.nextInt(), input.nextInt(), input.nextInt()));
		
		boolean seeTarget=true;
		
		List <String> deadRobots = new ArrayList<>();
		while (seeTarget ) {//|| killerRobot.getRobotHp()>0) {
			List <Float> distanceList = new ArrayList<>();
			float min=32000;
			for(int robotIndex=0; robotIndex<robotNumber; robotIndex++) {
				distanceList.add(calculateDistance(killerRobot, robotList.get(robotIndex)));
				if(distanceList.get(robotIndex)<min)
					min=distanceList.get(robotIndex);
			}
			int closestRobot = distanceList.indexOf(min);
			if (min<=killerRobot.getViewRange() && killerRobot.getRobotHp()>=robotList.get(closestRobot).getRobotHp()) {
				attackRobot(killerRobot, robotList.get(closestRobot));
				deadRobots.add(robotList.get(closestRobot).getName());
				robotList.remove(closestRobot);
				robotNumber--;
			}
			else 
				seeTarget=false;
		}
		
		System.out.println("last coordinates of the killer robot " + killerRobot.getName() + " are: X" + killerRobot.getPosX() + "  Y" + killerRobot.getPosY());
		System.out.println(killerRobot.getName() + " has " + killerRobot.getRobotHp() + " hp left");
		if (robotNumber!=0) {
			System.out.println("the following robots survived: ");
		for(int i=0; i<robotNumber; i++)
			System.out.println(robotList.get(i).getName() + " ");
		}
		System.out.println(killerRobot.getName()  + " killed the following robots:");
		for(int i=0; i<deadRobots.size(); i++)
			System.out.print(deadRobots.get(i) + " ");
		
		input.close();
	}
	
	 static float calculateDistance(Robot killerRobot, Robot currentRobot) {
		float distance=(float) Math.sqrt(Math.pow(killerRobot.posX-currentRobot.posX, 2)+Math.pow(killerRobot.posY-currentRobot.posY, 2));
		return distance;
	}
	 
	 static void attackRobot(Robot killerRobot, Robot currentRobot) {
		 killerRobot.setPosX(currentRobot.getPosX());
		 killerRobot.setPosY(currentRobot.getPosY());
		 killerRobot.setRobotHp(killerRobot.getRobotHp()-currentRobot.getRobotHp());
	 }
	
}
