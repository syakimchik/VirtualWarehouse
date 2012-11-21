package by.yakimchik.algorithm;

import java.util.ArrayList;
import java.util.Stack;

import by.yakimchik.data.Point;

public class SearchAllPointsOfMap {

	private Stack<Point> stack;
	
	private ArrayList<Point> allPointsOfMap = new ArrayList<Point>();
	
	public SearchAllPointsOfMap(ArrayList<Point> allPoints){
		stack = new Stack<Point>();
		
		stack.push(new Point(allPoints.get(0).getX()+25, allPoints.get(0).getX()+25));
		
		while(!stack.empty()){
			Point cur = stack.pop();
		}
	}
	
}
