
public class Pages {
    String ref_page;
    Integer slotNum;

    int pageIndex;
    boolean isHit;


    Pages(String page, int pageIndex){
        this.ref_page = page;
        this.pageIndex = pageIndex;
        this.isHit = false;
    }

    public void setSlotNum(Integer slotNum) {
        this.slotNum = slotNum;
    }

    public Integer getSlotNum() {
        return slotNum;
    }

    public String getRef_page() {
        return ref_page;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public boolean getHit() {
        return this.isHit;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }
}
