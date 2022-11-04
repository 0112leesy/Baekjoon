import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	
	static Node rootNode;

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		
		int antNumber = Integer.parseInt(br.readLine());
		rootNode = new Node("");
		
		for(int i=0; i<antNumber; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int nodeNumber = Integer.parseInt(st.nextToken());
			Node currentNode = rootNode;
			
			for(int j=0; j<nodeNumber; j++) {
				String nodeContent = st.nextToken();
				if(!currentNode.isChild(nodeContent)) {
					currentNode.setChild(nodeContent);
				}
				currentNode = currentNode.childMap.get(nodeContent);
			}
		}
		
		DFS(rootNode, "");
		System.out.println(sb);


	}
	
	static StringBuilder sb = new StringBuilder();
	
	static void DFS(Node currentNode, String depth) {
		if(currentNode.isLeaf()) {
			return;
		}
		
		Map<String, Node> currentChildMap = currentNode.childMap;
		for(String childContent : currentChildMap.keySet()) {
			sb.append(depth + childContent).append('\n');
			Node childNode = currentChildMap.get(childContent);
			DFS(childNode, depth+"--");
		}
	}
	
	static class Node{
		
		private String content;
		private Map<String, Node> childMap;
		
		Node(String content){
			this.content = content;
			childMap = new TreeMap<>();
		}
		
		boolean isChild(String childContent) {
			if(childMap.containsKey(childContent)) {
				return true;
			}
			return false;
		}
		
		void setChild(String childContent) {
			childMap.put(childContent, new Node(childContent));
		}
		
		boolean isLeaf() {
			if(childMap.size() == 0) {
				return true;
			}
			return false;
		}
		
	}
	
	
}