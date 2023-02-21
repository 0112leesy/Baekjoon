import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	
	static int houseNumber;
	static int roadNumber;
	static int[] parents;
	static ArrayList<Road> roads;
	static int totalDistance;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			houseNumber = Integer.parseInt(st.nextToken());
			roadNumber = Integer.parseInt(st.nextToken());
		
			if(houseNumber == 0 && roadNumber == 0) {
				break;
			}
			
			parents = new int[houseNumber];
			for(int i=0; i<houseNumber; i++) {
				parents[i] = i;
			}
			
			roads = new ArrayList<Road>();
			totalDistance = 0;
			for(int i=0; i<roadNumber; i++) {
				st = new StringTokenizer(br.readLine());
				int house1 = Integer.parseInt(st.nextToken());
				int house2 = Integer.parseInt(st.nextToken());
				int distance = Integer.parseInt(st.nextToken());
				
				Road road = new Road(house1, house2, distance);
				roads.add(road);
				totalDistance += distance;
			}
			sb.append(getDiscount() + "\n");
		}
		System.out.println(sb.toString());
		
		
	}
	
	static class Road {
		private int house1;
		private int house2;
		private int distance;
		
		public Road(int x, int y, int z) {
			this.house1 = x;
			this.house2 = y;
			this.distance = z;
		}
	}
	
	static int getDiscount() {
		Collections.sort(roads, new Comparator<Road>() {

			@Override
			public int compare(Road o1, Road o2) {
				// TODO Auto-generated method stub
				return o1.distance - o2.distance;
			}
			
		});
		
		int sumDistance = 0;
		for(Road road : roads) {
			if(find(road.house1) != find(road.house2)) {
				union(road.house1, road.house2);
				sumDistance += road.distance;
				// System.out.println(road.house1 + " " + road.house2 + " " + road.distance);
			}
		}
		
		return (totalDistance - sumDistance);
	}
	
	static void union(int A, int B) {
		A = find(A);
		B = find(B);
		
		if(A <= B) {
			parents[B] = A;
			return;
		}
		parents[A] = B;
	}
	
	static int find(int A) {
		if(parents[A] == A) {
			return A;
		}
		return find(parents[A]);
	}
	
	
}