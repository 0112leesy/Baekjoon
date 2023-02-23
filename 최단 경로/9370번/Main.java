import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	// 입력부를 다른 메서드에서 받으면 IOException 처리를 하나하나 다 해줘야 됨
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static final int INF = 1000000000;

	public static void main(String[] args) throws IOException {
		
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while(testCase-- > 0) { // 테스트 케이스 별 입력부
			StringTokenizer st = new StringTokenizer(br.readLine());
			int nodeSize = Integer.parseInt(st.nextToken());
			int edgeSize = Integer.parseInt(st.nextToken());
			int candidateSize = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			int startNode = Integer.parseInt(st.nextToken());
			int hintNode1 = Integer.parseInt(st.nextToken());
			int hintNode2 = Integer.parseInt(st.nextToken());
			
			String answer = getFinalAnswer(
					nodeSize, edgeSize, candidateSize,
					startNode, hintNode1, hintNode2);
			sb.append(answer).append('\n');
		}
		
		System.out.println(sb.toString());
		
	}
	
	// 최종 답안 리턴 메서드
	static String getFinalAnswer(
			int nodeSize, int edgeSize, int candidateSize,
			int startNode, int hintNode1, int hintNode2) throws IOException {

		ArrayList<ArrayList<Edge>> edges = getEdges(nodeSize, edgeSize, hintNode1, hintNode2);
		int[] candidates = getCandidates(candidateSize);
		
		int[] optimal = dijkstra(nodeSize, edges, startNode, hintNode1, hintNode2);
		
		ArrayList<Integer> finalAnswer = new ArrayList<>();
		for(int candidate : candidates) {
			if(optimal[candidate]%2 == 1) {
				finalAnswer.add(candidate);
			}
		}
		
		Collections.sort(finalAnswer);
		StringBuilder sb = new StringBuilder();
		for(int answer : finalAnswer) {
			sb.append(answer).append(' ');
		}
	
		return sb.toString();
	}
	
	static ArrayList<ArrayList<Edge>> getEdges(int nodeSize, int edgeSize, int hint1, int hint2) throws IOException {
		ArrayList<ArrayList<Edge>> edges = new ArrayList();
		
		for(int i=0; i<=nodeSize; i++) {
			edges.add(new ArrayList<Edge> ());
		}
		
		for(int i=0; i<edgeSize; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int distance = Integer.parseInt(st.nextToken());
			
			// 가중치 저장할 때 힌트 루트 빼고 *2, 힌트 루트는 *2-1
			// 최종 distance가 홀수면 힌트 루트를 거쳐간 것, 아니면 거쳐가지 않은 것
			distance *= 2;
			if((start == hint1 && end == hint2) || (start == hint2 && end == hint1)) {
				distance -= 1;
			}
			
			edges.get(start).add(new Edge(start, end, distance));
			edges.get(end).add(new Edge(end, start, distance));
		}
		
		return edges;
	}
	
	static int[] getCandidates(int candidateSize) throws IOException {
		int[] candidates = new int[candidateSize];
		for(int i=0; i<candidateSize; i++) {
			candidates[i] = Integer.parseInt(br.readLine());
		}
		return candidates;
	}
	
	/*
	 * 다익스트라 알고리즘
	 * 
	 * pq : Edge가 담기는 우선 순위 큐
	 * graph : 연결된 엣지 리스트
	 * opt : 최단 거리를 저장하는 배열
	 *
	 * pq.offer(new Edge(start, 0))
	 * while(!pq.isEmpty)
	 * 		current = pq.poll
	 * 		for(Edge next : graph.get(current.node))
	 * 			if(opt[next.node] > opt[current.node] + current.dist)
	 * 				opt[next.node] = opt[current.node] + current.dist
	 * 				pq.offer(next)
	 * 
	 * return opt
	 * 
	 */
	
	static class Edge implements Comparable<Edge> {
		
		private int start;
		private int end;
		private int distance;
		
		Edge(int start, int end, int distance) {
			this.start = start;
			this.end = end;
			this.distance = distance;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return o.distance - distance;
		}
		
	}
	
	static int[] dijkstra(int nodeSize, ArrayList<ArrayList<Edge>> edges, int start, int hint1, int hint2) {
		
		int[] optimalDistance = new int[nodeSize+1];
		Arrays.fill(optimalDistance, INF);
		optimalDistance[start] = 0;
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(0, start, 0));
		
		while(!pq.isEmpty()) {
			Edge edge = pq.poll();
			int node = edge.end;
			
			for(Edge nextEdge : edges.get(node)) {
				int nextNode = nextEdge.end;
				int nextDistance = nextEdge.distance;
				
				if(optimalDistance[nextNode] > optimalDistance[node] + nextDistance) {
					optimalDistance[nextNode] = optimalDistance[node] + nextDistance;
					pq.offer(nextEdge);
				}
			}
		}
		return optimalDistance;		
	}
}