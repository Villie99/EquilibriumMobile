import java.util.*;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.equilibriumMobile();
    }
    int depth = 0;
    int nodes = 0;
    long numberFrequency = 0;
    long topFrequency = 0;
    String parse;
    public void equilibriumMobile() {
        Scanner scanner = new Scanner(System.in);
        int testCases = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < testCases; i++) {
            parse = scanner.nextLine();
            HashMap<Long, Long> similarNumbers = new HashMap<>();
            StringBuilder number = new StringBuilder();
            depth = 0;
            nodes = 0;
            numberFrequency = 0;
            topFrequency = 0;

            for (int j = 0; j < parse.length(); j++) {
                char character = parse.charAt(j);
                if (Character.isDigit(character)) {
                    number.append(character);
                } else if (number.length() > 0) {
                    long temp = Long.parseLong(number.toString());
                    long key = temp * (long) Math.pow(2, depth);
                    similarNumbers.put(key, similarNumbers.getOrDefault(key, 0L) + 1);
                    nodes++;
                    number = new StringBuilder();
                }
                if (character == '[') {
                    depth++;
                } else if (character == ']') {
                    depth--;
                }
            }
            if (number.length() > 0) {
                long temp = Long.parseLong(number.toString());
                long key = temp * (long) Math.pow(2, depth);
                similarNumbers.put(key, similarNumbers.getOrDefault(key, 0L) + 1);
                nodes++;
            }
            List<Map.Entry<Long,Long>> similarNumbersList = new ArrayList<>(similarNumbers.entrySet());
            for (int j = 0; j < similarNumbers.size(); j++) {
                if (similarNumbersList.get(j).getValue() > numberFrequency) {
                    numberFrequency = similarNumbersList.get(j).getValue();
                    topFrequency = similarNumbersList.get(j).getKey();
                }
            }
            System.out.println(nodes - numberFrequency);
        }
    }

    class Node{
        private int weight;
        private boolean isJunction = true;
        private Node parent;
        public List<Node> children = new ArrayList<>();

        private int costOfChange = 1;
        public boolean isRoot = false;
        public int level;

        public Node(){
        }

        public Node setWeight(int weight){
            this.weight = weight;
            isJunction = false;
            return this;
        }

        public Node setParent(Node parent) {
            this.parent = parent;
            return this;
        }

        public void addChild(Node child) {
            children.add(child);
        }

        public String toString(){
            return isJunction ? "─┐" : "" + weight;
        }
    }
}