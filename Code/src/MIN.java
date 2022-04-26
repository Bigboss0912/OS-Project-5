import java.util.ArrayList;
import java.util.List;

public class MIN {
    List<String> refString = new ArrayList<String>();
    ArrayList<String> cache = new ArrayList<String>();
    int hitCount;
    int missCount;
    int pageIndex;
    String retString;
    String page;
    int slotSize;
    MIN(List<String> refString, int slotSize) {
        this.refString.addAll(refString);
        this.slotSize = slotSize;
        this.cache = cache;
        this.page = page;
        this.pageIndex = pageIndex;
    }

    public int getHitCount() {
        return hitCount;
    }

    public int getMissCount() {
        return missCount;
    }

    public void runSchedule() {
        while (!refString.isEmpty()) {
            this.page = this.refString.remove(0);
            if (this.cache.contains(this.page)) {
                this.pageIndex = this.cache.indexOf(this.page);
                System.out.println("+");
                this.hitCount++;
            } else {
                this.missCount++;
                if (this.cache.size() > 0 && this.cache.size() == this.slotSize) {
                    System.out.println(this.cache.remove(0) + " Removed");
                    this.cache.add(this.page);
                    System.out.println(this.page + " Added to the cache");
                } else {
                    this.cache.add(this.page);
                    System.out.println(this.page + " Added to the cache");
                }
            }
            System.out.println(this.refString.toString());
        }
    }

    public String getRetString() {
        return retString;
    }
}
