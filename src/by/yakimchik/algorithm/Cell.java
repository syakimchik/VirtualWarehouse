package by.yakimchik.algorithm;

public class Cell {
	private int x = -1;
	private int y = -1;
	private boolean blocked = false;
	private boolean finish = false;
	private boolean start = false;
	
	public Cell(int x, int y, boolean blocked){
		this.x = x;
		this.y = y;
		this.blocked = blocked;
	}
	
	public int mandist(Cell finish){
		return 10*(Math.abs(this.x - finish.x)+Math.abs(this.y-finish.x));
	}
	
	public int price(Cell finish){
		if(this.x==finish.x || this.y == finish.y){
			return 10;
		}
		else{
			return 14;
		}
	}
	
	public void setAsStart(){
		this.start = true;
	}
	
	public void setAsFinish(){
		this.finish = true;
	}
	
	public boolean equals(Cell second){
		return (this.x==second.x) && (this.y==second.y);
	}
	
	
}
