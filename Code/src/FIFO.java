import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FIFO {
    List<Pages> refString = new ArrayList<Pages>();
    ArrayList<Pages> cache = new ArrayList<Pages>();
    ArrayList<Integer> slot_list = new ArrayList<Integer>();
    int hitCount;
    int missCount;
    int pageIndex;
    String retString;
    Pages page;
    int slotSize;
    int refStringLen;
    boolean isContained;

    FIFO(List<Pages> refString, int slotSize) {
        this.refString.addAll(refString);
        this.refStringLen = this.refString.size();
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
        for (int i = 0; i < refString.size()/2; i++) {
            for(int j=0; j< slotSize; j++){
                slot_list.add(j);
            }
        }

        for (int i = 0; i < refString.size(); i++) {
            refString.get(i).setSlotNum(slot_list.get(i));
        }

        for (Pages p:
             refString) {
            System.out.print(" " + p.slotNum);
        }

        System.out.println();

        while (!refString.isEmpty()) {
                this.page = this.refString.remove(0);
                this.isContained = false;

            for (Pages p: this.cache) {

                if(p.getRef_page().equals(this.page.getRef_page())) {
                    this.isContained = true;
                    this.pageIndex = this.cache.indexOf(p);
                    break;
                }

            }

                if (this.isContained) {

                    System.out.println("+ " + this.pageIndex);
                    this.hitCount++;
                } else {
                    this.missCount++;
                    if (this.cache.size() > 0 && this.cache.size() == this.slotSize) {
                        System.out.println(this.cache.get(0).getRef_page() + " Removed " + this.page.getSlotNum());
                        this.cache.remove(0);
                        this.cache.add(this.page);
                        System.out.println(this.page.getRef_page() + " Added to the cache " + this.page.getSlotNum());
                    } else {
                        this.cache.add(this.page);
                        System.out.println(this.page.getRef_page() + " Added to the cache " + this.page.getSlotNum());
                    }
                }

            System.out.println();

                for (Pages p :this.refString ) {
                System.out.print(" " + p.getRef_page() + " ");
                }
            System.out.println();
        }
    }


    public void setup(){
        String [][] setup = new String [slotSize][this.refStringLen];

        for (int i = 0; i < this.slotSize; i++) {
            for(int j = 0; j < this.refStringLen; j++){
                setup[i][j] = "Z";
            }
        }

        System.out.println(Arrays.deepToString(setup));
        for (String [] s: setup ) {
            for (String c: s) {
                System.out.print(" " + c);
            }
            System.out.println();
        }

        System.out.println("After For Loop");


    }

    public String getRetString() {
        return retString;
    }
}