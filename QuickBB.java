import java.util.*;
import java.io.*;

class QuickBB{
	public int treewidth,ub;
	QuickBB(){
		treewidth = 0;
		ub = 0;
	}
	Graph elim(Graph G,int v){
		ArrayList<ArrayList<Integer>>ed = G.edges;
		ArrayList<ArrayList<Integer>>temp = new ArrayList<ArrayList<Integer>>();
		for(int i=0;i<ed.size();i++){
			int x = ed.get(i).get(0), y = ed.get(i).get(0);
			if(x == v || y==v){
				temp.add(ed.get(i));
			}
		}
		for(int i=0;i<temp.size();i++)
			ed.remove(temp.get(i));
		Set<Integer>count = new HashSet<Integer>();
		for(int i=0;i<ed.size();i++){
			count.add(ed.get(i).get(0));
			count.add(ed.get(i).get(1));
		}

		Graph Gs = new Graph(count.size(),ed.size(),ed);
		return Gs;

	}
	int MMW(Graph temp){
		Graph G = temp;
		int lb = 0;
		while((G.allVertices()==1)){
			int v = G.minVertex();
			int u = G.getMinN(v);
			lb = Math.max(lb,G.deg[v]); // See this again
			G.contractEdge(u,v);
		}
		return lb;
	}

	void BB(State s){
		ArrayList<Integer> vs = s.G.getVertices();
		if(vs.size() < 2){
			ub = Math.min(ub,s.f);
		}
		else{
			for(int v: vs){
				Graph temp = elim(s.G,v);
				State d = new State(temp);
				d.x = s.x;
				d.x.add(v);
				d.g = Math.max(s.g,s.G.deg[v]);
				Graph tempo = new Graph(temp.n,temp.m,temp.edges);
				d.h = MMW(tempo);
				d.f = Math.max(d.g,d.h);
				if(d.f<ub){
					BB(d);
				}
			}
		}
	}
	int tw(Graph G){
		State s = new State(G);
		s.g = 0;
		Graph temp = new Graph(G.n,G.m,G.edges);
		s.h = MMW(temp);
		s.f = s.h;
		ub = G.n;
		if(s.f<ub){
			BB(s);
		}
		return ub;
	}


}