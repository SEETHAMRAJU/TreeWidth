import java.util.*;
import java.io.*;


class Driver{
	public static void main(String []args){
		Scanner s = new Scanner(System.in);
        
        int n = s.nextInt();
        int m = s.nextInt();

        ArrayList<ArrayList<Integer>> ad = new ArrayList<ArrayList<Integer>>();
        while(s.hasNextInt())
        {
        	ArrayList<Integer> temp = new ArrayList<Integer>();
            int u,v;
            u=s.nextInt();
            v=s.nextInt();
            u--;v--;
            temp.add(u);temp.add(v);
            ad.add(temp);
        }

		Graph G = new Graph(n,m,ad);
		QuickBB algo = new QuickBB();
		long start = System.currentTimeMillis();
		int h = algo.tw(G);

		long end = System.currentTimeMillis();
		System.out.println("Treewidth :"+h);
		System.out.println("Time taken :"+(end-start));

	}
}