import java.util.*;

class Graph{
	public int deg[];
	public int n,m;

	public ArrayList<ArrayList<Integer>> edges;

	Graph(int n, int m, ArrayList<ArrayList<Integer>> edges){
		this.n = n;
		this.m = m;
		this.edges = edges;
		deg = new int [n];
		for(int i=0;i<n;i++)
			deg[i]=0;
		for(int i=0;i<=m-1;i++){
			int v = edges.get(i).get(0);
			int u = edges.get(i).get(1);
			if(u!=v){
			// System.out.print(u+" ");
			// System.out.println(v);
			deg[v]++;
			deg[u]++;
		}
		}
	}
	void printDeg(){
		for(int i=0;i<n;i++)
			System.out.print(deg[i]+"  ");
		System.out.println("");
	}

	void printEdges(ArrayList<ArrayList<Integer>> ed){
		for(int i=0;i<ed.size();i++){
			for(int j=0;j<ed.get(i).size();j++){
				System.out.print(ed.get(i).get(j)+" ");
			}

			System.out.println("");
		}
	}
	ArrayList<ArrayList<Integer>> checkDistinct(ArrayList<ArrayList<Integer>> ed){
        Set<ArrayList<Integer>> hset = new HashSet<>(ed);
        ed.clear();
        ed.addAll(hset);
		return ed;
	}

	void contractEdge(int u,int v){
		ArrayList<Integer> temp = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> toremove = new ArrayList<ArrayList<Integer>>();
		for(int i=0;i<m;i++){
			int x = edges.get(i).get(0), y = edges.get(i).get(1);
			if(x==u && y!=v){
				toremove.add(edges.get(i));
				temp.add(y);
			}
			else if(y == u && y!=v){
				toremove.add(edges.get(i));
				temp.add(x);
			}
			else if(x==u && y==v || y==u && x==v){
				toremove.add(edges.get(i));
			}
		}

		for(int i=0;i<temp.size();i++){
			ArrayList<Integer> temp1 = new ArrayList<Integer>();
			temp1.add(v);temp1.add(temp.get(i));
			edges.add(temp1);
		}

		for(int i=0;i<toremove.size();i++){
			this.edges.remove(toremove.get(i));
		}


		this.edges = checkDistinct(this.edges);
		for(int i=0;i<n;i++)
			deg[i]=0;

		for(int i=0;i<edges.size();i++){
			int x = edges.get(i).get(0), y = edges.get(i).get(1);
			if(x!=y){
				deg[x]+=1;
				deg[y]+=1;
			}
		}	
		m = this.edges.size();
	}

	int minVertex(){
		int v = 0,mind = 100000000;
		for(int i=0;i<n;i++){
			if(deg[i]<mind && deg[i]!=0){
				mind = deg[i];
				v = i;
			}
		}
		return v;
	}
	int getMinN(int v){
		int u = 0,mind = 10000000;
		for(int i=0;i<m;i++){
			int x = edges.get(i).get(0), y= edges.get(i).get(1);
			if(x==v){
				if(deg[y]<=mind){
					mind = deg[y];
					u = y;
				}
			}
			else if(y==v){
				if(deg[x]<=mind){
					mind = deg[x];
					u = x;
				}
			}
		}
		return u;
	}

	int allVertices(){
		int x = 0;
		for(int i=0;i<n;i++){
			if(deg[i]>=1){
				x = 1;
			}
		}
		return x;
	}
	int getActiveVertices(){
		int count = 0;
		for(int i=0;i<n;i++){
			if(deg[i]>=1)
				count++;
		}
		return count;
	}
	ArrayList<Integer> getVertices(){
		ArrayList<Integer> p = new ArrayList<Integer>();
		for(int i=0;i<n;i++){
			if(deg[i]>=1){
				p.add(i);
			}
		}
		return p;
	}
}