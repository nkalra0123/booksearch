import javax.print.Doc;
import java.util.*;

public class Searcher {

    Indexer idx;

    public Searcher(Indexer idx) {
        this.idx = idx;
    }

    List<TastySearch.DocScore> search(String queries)
    {
        List<TastySearch.DocScore> result = new ArrayList<>();

        PriorityQueue<TastySearch.DocScore> scores = new PriorityQueue<>(new Comparator<TastySearch.DocScore>() {
            @Override
            public int compare(TastySearch.DocScore o1, TastySearch.DocScore o2) {
                int result =  o2.score.compareTo(o1.score);
                if (result == 0)
                    return o2.document.score.compareTo(o1.document.score);
                else
                    return result;
            }
        });

        HashMap<Integer,Integer> docsProcessed = new HashMap<>();

        String[] querys = queries.split(",");

        for (String query : querys) {

            query = query.trim();

            HashSet<Integer> list = idx.hashMap.getOrDefault(query, new HashSet<>());

            for (Integer docId : list) {
                int score = docsProcessed.getOrDefault(docId,0);
                docsProcessed.put(docId,score+1);
            }
        }

        int queryLengh = querys.length;

        for(Map.Entry<Integer,Integer> entry : docsProcessed.entrySet())
        {
            int value = entry.getValue();
            Float score = (float)value/ (float) queryLengh;
            TastySearch.DocScore docScore = new TastySearch.DocScore();
            docScore.document = idx.documents.get(entry.getKey());
            docScore.score = score;
            scores.add(docScore);
        }


        int K = 20;

        while (K-- > 0 && scores.size() > 0) {
            result.add(scores.poll());
        }

        return result;

    }
}
