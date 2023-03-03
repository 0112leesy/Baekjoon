import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Trie trie = new Trie();
		
		for(int i=0; i<N; i++ ) {
			String name = br.readLine();
			trie.insert(name);
		}
		
		for(String nickname : answer) {
			System.out.println(nickname);
		}
		
	}
	
	static class Node {
		boolean isLeaf;
		Map<Character, Node> childMap;
		int count;
		
		Node() {
			count = 0;
			isLeaf = false;
			childMap = new HashMap<>();
		}
	}
	
	static ArrayList<String> answer = new ArrayList<>();
	
	static class Trie {
		Node rootNode;
		
		Trie() {
			this.rootNode = new Node();
		}
		
		void insert(String str) {
			Node node = rootNode;
			String nickname = "";
			boolean flag = true;
			
			for(int i=0; i<str.length(); i++) {
				char c = str.charAt(i);
				if(!node.childMap.containsKey(c)) {
					node.childMap.put(c, new Node());
					if(flag) {
						answer.add(nickname + c);
						flag = false;
					}
				}
				nickname += c;
				node = node.childMap.get(c);
			}
			if(node.childMap.isEmpty()) {
				node.isLeaf = true;
				if(node.count == 0) node.count = 1;
			}

			if(flag) {
				node.count += 1;
				if(node.count == 1) {
					answer.add(str);
				}
				else {
					answer.add(str + node.count);
				}
			}
		}
	}
}