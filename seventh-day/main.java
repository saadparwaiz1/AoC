import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.HashMap;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Arrays;
import java.util.PriorityQueue;

class Bag {
    String Color;
    HashSet < String > childs;

    public Bag(String Color, HashSet < String > childs) {
        this.Color = Color;
        this.childs = childs;
    }

    public String toString() {
        return "" + childs.isEmpty();
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
                bag = new Bag(color, new HashSet < String > ());
            } else {
                HashSet < String > h = new HashSet < > ();
                String[] childs = child.split(",");

                for (String s: childs) {
                    String str = s.replaceAll("[^A-Za-z ]", "").replace("bags", "").replace("bag", "").replaceFirst("^\\s*", "").replaceAll("\\s+$", "");
                    h.add(str);
                }

                bag = new Bag(color, h);
            }
        }
        return bag;
    }
}

class Main {
    public static boolean canComputeGold(HashMap < String, Bag > map, HashMap < String, Boolean > h, Bag bag) {

        HashSet < String > hset = new HashSet < > ();

        hset.add(bag.Color);
        PriorityQueue < String > pq = new PriorityQueue < > (bag.childs);

        while (!pq.isEmpty()) {
            if (pq.contains("shiny gold")) {
                return true;
            }

            String head = pq.poll();

            if (h.containsKey(head) && h.get(head)) {
                return true;
            }

            if (!hset.contains(head)) {
                pq.addAll(map.get(head).childs);
            }
        }

        return false;
    }

    public static void count_gold_children() throws IOException {
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

    public static void main(String[] args) throws IOException {
        count_gold_children();
    }
}
