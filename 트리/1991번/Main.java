import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Tree tree = new Tree();
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			tree.insert(str.charAt(0), str.charAt(2), str.charAt(4));
		}
		
		tree.PreOrder(tree.root);
		System.out.println();
		tree.InOrder(tree.root);
		System.out.println();
		tree.PostOrder(tree.root);
	}
	
	
}

class Node {
	char data;
	Node left, right;
	
	Node(char c){
		data = c;
		left = null;
		right = null;
	}
}

class Tree{
	Node root;
	
	Tree(){
		root = null;
	}
	
	// 주어진 입력을 바탕으로 노드를 추가함
	void insert(char data, char left_data, char right_data) {
		if(root == null) {// root가 null인 초기상태에서는 data를 갖는 새로운 노드 생성 
			root = new Node(data);
			if(left_data != '.') root.left = new Node(left_data);
			if(right_data != '.') root.right = new Node(right_data);
		}
		// 초기상태가 아닐 경우에는 data를 갖는 해당 노드를 찾아야 함
		else search(root, data, left_data, right_data);
	}
	
	void search(Node x, char data, char left_data, char right_data) {
		if(x == null) return;
		
		else if(x.data == data) { // data를 갖는 노드를 찾은 경우, 왼쪽 오른쪽 자식 노드 생성 후 지정
			if(left_data != '.') x.left = new Node(left_data);
			if(right_data !='.') x.right = new Node(right_data);
		}
		
		else { // data를 갖는 노드가 아닌 경우, 왼쪽 오른쪽 자식으로 이동해 다시 탐색
			search(x.left, data, left_data, right_data);
			search(x.right, data, left_data, right_data);
		}
	}
	
	void PreOrder(Node root) {
		System.out.print(root.data);
		if(root.left != null) PreOrder(root.left);
		if(root.right != null) PreOrder(root.right);
	}
	
	void InOrder(Node root) {
		if(root.left != null) InOrder(root.left);
		System.out.print(root.data);
		if(root.right != null) InOrder(root.right);
	}
	
	void PostOrder(Node root) {
		if(root.left != null) PostOrder(root.left);
		if(root.right != null) PostOrder(root.right);
		System.out.print(root.data);
	}
}
