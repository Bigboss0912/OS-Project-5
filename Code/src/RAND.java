import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.random.*;

public class RAND {
    List<Pages> refString = new ArrayList<Pages>();
    List<Pages> refString2 = new ArrayList<Pages>();
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
    Random randNum = new Random();

    RAND(List<Pages> refString, int slotSize) {
        this.refString.addAll(refString);
        this.refStringLen = this.refString.size();
        this.refString2.addAll(refString);
        this.slotSize = slotSize;
        this.randNum = randNum;
    }

    public int getHitCount() {
        return hitCount;
    }


    public void runSchedule() {
        for (int i = 0; i < refString.size()/2; i++) {
            for(int j=0; j< slotSize; j++){
                slot_list.add(j);
            }
        }

        for (int i = 0; i < refString.size(); i++) {
            refString.get(i).setSlotNum(slot_list.get(i));
            refString2.get(i).setSlotNum(slot_list.get(i));
        }

        this.pageIndex = 0;
        while (!refString.isEmpty()) {
            this.page = this.refString.remove(0);
            this.isContained = false;

            for (Pages p: this.cache) {

                if(p.getRef_page().equals(this.page.getRef_page())) {
                    this.isContained = true;
                    this.refString2.get(pageIndex).setSlotNum(p.getSlotNum());
                    break;
                }

            }

            if (this.isContained) {
                this.refString2.get(this.pageIndex).setHit(true);
                this.hitCount++;
            } else {
                this.missCount++;
                if (this.cache.size() > 0 && this.cache.size() == this.slotSize) {
                    int randIndex = this.randNum.nextInt(this.cache.size());
                    this.refString2.get(pageIndex).setSlotNum(this.cache.get(randIndex).getSlotNum());
                    this.cache.remove(randIndex);
                    this.cache.add(this.page);
                } else {
                    this.cache.add(this.page);
                }
            }

            this.pageIndex++;
        }
    }


    public void setup(){
        String [][] setup = new String [this.slotSize][this.refStringLen];

        for (int i = 0; i < this.slotSize; i++) {
            for(int j = 0; j < this.refStringLen+1; j++){
                for (Pages p: refString2) {
                    if (p.slotNum == i) {
                        if(p.getPageIndex() == j) {
                            if (p.getHit()) {
                                setup[i][j] = "+";
                            } else {
                                setup[i][j] = p.getRef_page();
                            }
                        }
                    }
                }
            }
        }
        int counter = 1;
        for (String [] s: setup ) {
            System.out.print("RAND  "+ counter + ":");
            for (String c: s) {
                if(c == null) {
                    System.out.print("  ");
                } else {
                    System.out.print(" " + c);
                }
            }
            counter ++;
            System.out.println();
        }

        System.out.println("---".repeat(this.refStringLen));


    }

    public int getMissCount() {
        return missCount;
    }
}

