import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class TastySearch {

    static class DocScore
    {
        Document document;
        Float score;

        @Override
        public String toString() {
            return "DocScore{" +
                    "document=" + document +
                    ", score=" + score +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {

        Indexer idx = new Indexer();

        long startTime = System.currentTimeMillis();

        idx.parseFile("/Users/bluestacks/Downloads/finefoods.txt_sampled");

        long endTime = System.currentTimeMillis();

        Searcher searcher = new Searcher(idx);

        System.out.println("time taken = " + (endTime - startTime) / 1000 + "secs");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {

            String queries = bufferedReader.readLine();

            long startTimeQ = System.currentTimeMillis();


            System.out.println("Result = " + searcher.search(queries));


            System.out.println("===============================================");
            long endTimeQ = System.currentTimeMillis();

            System.out.println("time taken = " + (endTimeQ - startTimeQ)  + "millisecs");

        }
    }


}
