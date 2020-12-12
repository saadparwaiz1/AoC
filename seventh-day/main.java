import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Arrays;
import java.util.PriorityQueue;

class Bag implements Comparable < Bag > {
    String Color;
    int count;
    HashSet < Bag > childs;

    public Bag(String Color, HashSet < Bag > childs, int count) {
        this.Color = Color;
        this.count = count;
        this.childs = childs;
    }

    public String toString() {
        return Color;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Bag)) {
            return false;
        }

        Bag other = (Bag) o;

        return this.Color.equals(other.Color);
    }

    @Override
    public int hashCode() {
        return this.Color.hashCode();
    }

    @Override
    public int compareTo(Bag bg) {
        return this.Color.compareTo(bg.Color);
    }

}

class ProcessData {
    Stream < String > lines;
    BufferedReader file;

    public ProcessData(String filename) throws IOException {
        FileReader file_reader = new FileReader(filename);
        file = new BufferedReader(file_reader);
        lines = file.lines();
    }

    public Stream < Bag > getStream() {
        return lines.map(new Mapper());
    }
}

class Mapper implements Function < String, Bag > {
    @Override
    public Bag apply(String t) {
        Pattern pattern = Pattern.compile("(^[a-zA-Z]* [a-zA-Z]* [a-zA-Z]*) contain (.*)");
        Matcher matcher = pattern.matcher(t);
        boolean matchFound = matcher.find();
        Bag bag = null;

        if (matchFound) {
            String color = matcher.group(1).replace("bags", "").replace("bag", "").replaceFirst("^\\s*", "").replaceAll("\\s+$", "");
            String child = matcher.group(2);
            if (child.compareTo("no other bags.") == 0 || child.compareTo("no other bags") == 0) {
                bag = new Bag(color, new HashSet < Bag > (), 0);
            } else {
                HashSet < Bag > h = new HashSet < > ();
                String[] childs = child.split(",");

                for (String s: childs) {
                    String str = s.replaceAll("[^A-Za-z ]", "").replace("bags", "").replace("bag", "").replaceFirst("^\\s*", "").replaceAll("\\s+$", "");
                    String num = s.replaceAll("[A-Za-z .]", "");
                    h.add(new Bag(str, null, Integer.parseInt(num)));
                }

                bag = new Bag(color, h, 0);
            }
        }
        return bag;
    }
}

class Main {
    public static boolean canComputeGold(HashMap < String, Bag > map, HashMap < String, Boolean > h, Bag bag) {

        HashSet < String > hset = new HashSet < > ();

        hset.add(bag.Color);
        PriorityQueue < Bag > pq = new PriorityQueue < > (bag.childs);

        Bag shiny_gold = new Bag("shiny gold", null, -1);

        while (!pq.isEmpty()) {
            if (pq.contains(shiny_gold)) {
                return true;
            }

            Bag head = pq.poll();


            if (h.containsKey(head.Color) && h.get(head.Color)) {
                return true;
            }

            if (!hset.contains(head.Color)) {
                pq.addAll(map.get(head.Color).childs);
                hset.add(head.Color);
            }
        }

        return false;
    }

    public static void count_gold() throws IOException {
        HashMap < String, Bag > h = new HashMap < > ();
        HashMap < String, Boolean > h_bool = new HashMap < > ();
        ProcessData process = new ProcessData("data/input");
        Stream < Bag > bag_stream = process.getStream();
        Bag[] bags = bag_stream.toArray(Bag[]::new);

        for (Bag bag: bags) {
            h.put(bag.Color, bag);
        }

        int count = 0;

        for (Bag bag: bags) {
            Boolean result = canComputeGold(h, h_bool, bag);
            h_bool.put(bag.Color, result);
            if (result) {
                count += 1;
            }
        }

        System.out.println(count);
    }

    private static int count_helper(HashSet < Bag > h, HashMap < String, Bag > map) {
        if (h.size() == 0) {
            return 0;
        }

        int count = 0;

        for (Bag bg: h) {
            count = count + bg.count + bg.count * count_helper(map.get(bg.Color).childs, map);
        }

        return count;
    }

    public static void count_gold_children() throws IOException {
        HashMap < String, Bag > h = new HashMap < > ();
        ProcessData process = new ProcessData("data/input");
        Stream < Bag > bag_stream = process.getStream();
        Bag[] bags = bag_stream.toArray(Bag[]::new);

        for (Bag bag: bags) {
            h.put(bag.Color, bag);
        }

        int count = count_helper(h.get("shiny gold").childs, h);
        System.out.println(count);
    }

    public static void main(String[] args) throws IOException {
        count_gold();
        count_gold_children();
    }
}