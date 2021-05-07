import java.util.*;
class State{
	public Graph G;
	public int f,h,g;
	public ArrayList<Integer> x;
	State(Graph G){
		x = new ArrayList<Integer> ();
		this.G = G;

	}
}