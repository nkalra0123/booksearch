import java.util.HashSet;

public class Document {

    /*
    product/productId: B001E4KFG0
    review/userId: A3SGXH7AUHU8GW
    review/profileName: delmartian
    review/helpfulness: 1/1
    review/score: 5.0
    review/time: 1303862400
    review/summary: Good Quality Dog Food
    review/text: I have bought several of the Vitality canned dog food products and have found them all to be of good quality. The product looks more like a stew than a processed meat and it smells better. My Labrador is finicky and she appreciates this product better than  most.
}*/

    int id;
    String productId;
    String userId;
    String profileName;
    String  helpfulness;
    Float score;
    long time;
    String summary;
    String text;

    public Document() {
    }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", score=" + score +
                ", summary='" + summary + '\'' +
                '}';
    }
}
