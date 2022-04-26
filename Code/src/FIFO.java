import java.util.ArrayList;

public class FIFO {
    ArrayList<String> refString = new ArrayList<String>();
    ArrayList<String> cache = new ArrayList<String>();
    int hitCount;
    int missCount;
    int pageIndex;
    String retString;
    String page;
    int slotSize;
    public void FIFO(ArrayList refString, int slotSize) {
        this.refString.addAll(refString);
        this.slotSize = slotSize;
        this.cache = cache;
        this.page = page;
        this.pageIndex = pageIndex;
    }

    public void runSchedule() {
        while (!refString.isEmpty()) {
            if (this.cache.size() < this.slotSize) {
                this.page = this.refString.remove(0);
                if (this.cache.contains(this.page)) {
                    this.pageIndex = this.cache.indexOf(this.page);
                    this.hitCount++;
                } else {
                    this.missCount++;
                    if (this.cache.size() > 0 && this.cache.size() == this.slotSize) {
                        this.cache.remove(0);
                        this.cache.add(this.page);
                    } else {
                        this.cache.add(this.page);
                    }
                }
                this.cache.add(this.refString.remove(0));
            }
        }
    }

    public String getRetString() {
        return retString;
    }
}
