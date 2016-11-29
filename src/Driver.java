
import java.util.*;

/**
 * Created by pavan on 28/11/16.
 */
public class Driver {
    static String plainString = "";
    static String str = new String();
    public static HashMap<String,String> charCodeMap = new HashMap<>();
    public static List<Node> charFreqMap = new ArrayList<>();
    public static void init(){
        charFreqMap.add(0,new Node('e',1));
        charFreqMap.add(1,new Node('h',1));
        charFreqMap.add(2,new Node('o',1));
        charFreqMap.add(3,new Node('l',2));

        /*charFreqMap.add(0,new Node('a',5));
        charFreqMap.add(1,new Node('b',9));
        charFreqMap.add(2,new Node('c',12));
        charFreqMap.add(3,new Node('d',13));
        charFreqMap.add(4,new Node('e',16));
        charFreqMap.add(5,new Node('f',45));*/
    }
    public static void main(String[] args) {
        //init();

        //System.out.println("Final root is " + root.frequency);
        System.out.println("Enter the string to be encoded");
        Scanner input = new Scanner(System.in);
        plainString = input.nextLine();
        //make a charFreqMap from input
        makeCharFreqMap(plainString);
        Collections.sort(charFreqMap);

        for (Node entry : charFreqMap) {
            System.out.println("Key : " + entry.leafChar+" Value :" +entry.frequency);
        }
        //get the final root
        Node root = getFinalRootNode();
        //traverse the root to get a bible of char and their codes
        traverse(root,"");
        //encode the string using the bible
        encodeString("hello");
    }
    public static void encodeString(String plainString){
        String encodedString = "";
        for(int i = 0; i<plainString.length() ; i++){
            encodedString += charCodeMap.get((String.valueOf(plainString.charAt(i))));
        }
        System.out.println("Encoded String is " + encodedString);
        /*for (Map.Entry entry : charCodeMap.entrySet()) {
            System.out.println(entry.getKey() + ", " + entry.getValue());
        }*/
    }
    private static void traverse(Node node,String code) {

        if(node.leftChild == null && node.rightChild == null){
            System.out.println("Leaf " +node.leafChar+ " " + code);
            charCodeMap.put(String.valueOf(node.leafChar),code);
        }
        if(node.leftChild != null) {
            traverse(node.leftChild, code + "0");
        }
        if(node.rightChild != null){
            traverse(node.rightChild,code +"1");
        }
    }


    public static Node getFinalRootNode(){
        while(charFreqMap.size() > 1) {
            Node rootNode = new Node();
            List<Node> twoWorkingNodes = getFirstTwoNodes();
            rootNode = makeARootAndTwoChildren(rootNode, twoWorkingNodes);
            System.out.println("Intermediate root is " + rootNode.frequency + rootNode.leafChar);
            charFreqMap.add(rootNode);
            Collections.sort(charFreqMap);
        }
        return charFreqMap.get(0);
    }

    public static List<Node> getFirstTwoNodes() {
        List<Node> twoNodes = new ArrayList<>();
        if (charFreqMap.size() >= 2)
            for (int i = 0; i < 2; i++) {
                twoNodes.add(charFreqMap.get(0));
                System.out.println("two childs " + twoNodes.get(i).frequency);
                charFreqMap.remove(0);
            }
            return twoNodes;
    }

    public static Node makeARootAndTwoChildren(Node root,List<Node> twoWorkingNodes){
        root.frequency = twoWorkingNodes.get(0).frequency + twoWorkingNodes.get(1).frequency;
        System.out.println("Node 1  " + twoWorkingNodes.get(0).leafChar);
        System.out.println("Node 2  " + twoWorkingNodes.get(1).leafChar);
        root.addLeftChild(twoWorkingNodes.get(0));
        root.addRightChild(twoWorkingNodes.get(1));
        return root;
    }

    public static void makeCharFreqMap(String plainString) {
        charFreqMap.clear();
        HashMap<Character, Integer> charFreqDynamicMap = new HashMap<>();
        for (int i = 0; i < plainString.length(); i++) {
            int prevCountValue = charFreqDynamicMap.containsKey(plainString.charAt(i))?charFreqDynamicMap.get(plainString.charAt(i)):0;
            charFreqDynamicMap.put((plainString.charAt(i)), prevCountValue + 1);
        }
        for (Map.Entry entry : charFreqDynamicMap.entrySet()) {
            charFreqMap.add(new Node((Character)entry.getKey(), (Integer)entry.getValue()));
            //System.out.println("Key : " + entry.getKey()+" Value :" +entry.getValue());
        }
    }
}

