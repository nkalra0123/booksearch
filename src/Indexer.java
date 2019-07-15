import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Indexer {

    HashMap<String, HashSet<Integer>> hashMap;

    ArrayList<Document> documents;

    public Indexer() {
        this.hashMap = new HashMap<>();
        this.documents = new ArrayList<>();
    }

    void put(String word, Integer id)
    {
        HashSet<Integer> indexs = hashMap.getOrDefault(word,new HashSet<>());

        indexs.add(id);
        hashMap.put(word,indexs);

    }

    void putLine(String line , Integer id)
    {
        for(String word : line.trim().split("\\s+"))
        {
            put(word,id);
        }
    }

    @Override
    public String toString() {
        return "Indexer{" +
                "hashMap=" + hashMap.size() +
                ", documents=" + documents.size() +
                '}';
    }

    /*product/productId: B001E4KFG0
    review/userId: A3SGXH7AUHU8GW
    review/profileName: delmartian
    review/helpfulness: 1/1
    review/score: 5.0
    review/time: 1303862400
    review/summary: Good Quality Dog Food
    review/text: I have bought several of the Vitality canned dog food products and have found them all to be of good quality. The product looks more like a stew than a processed meat and it smells better. My Labrador is finicky and she appreciates this product better than  most.
}*/

    void parseFile(String path)
    {
        try {
            File file = new File(path);

            if (file.exists() && file.isFile())
            {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                int id = 0;

                Document document = new Document();

                while ((line = reader.readLine()) != null) {


                    if(line.contains(":")){

                        String inputLine[] = line.split(":");

                        if(inputLine.length <2) continue;

                        String feild1 = inputLine[0].trim();
                        String feild2 = inputLine[1].trim();

                        document.id = id;
                        if(feild1.equals("product/productId"))
                        {
                            document.productId = feild2;
                        }

                        else if(feild1.equals("review/userId"))
                        {
                            document.userId = feild2;
                        }

                        else if(feild1.equals("review/profileName"))
                        {
                            document.profileName = feild2;
                        }

                        else if(feild1.equals("review/helpfulness"))
                        {
                            document.helpfulness = feild2;
                        }

                        else if(feild1.equals("review/score"))
                        {
                            document.score = Float.parseFloat(feild2);
                        }

                        else if(feild1.equals("review/time"))
                        {
                            document.time = Long.parseLong(feild2);
                        }

                        else if(feild1.equals("review/summary"))
                        {
                            document.summary = feild2;
                        }

                        else if(feild1.equals("review/text"))
                        {
                            document.text = feild2;
                        }
                    }
                    else if(line.equals(""))
                    {
                        id++;
                        putLine(document.summary,document.id);
                        putLine(document.text, document.id);
                        documents.add(document);

                        document = new Document() ;
                    }
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
