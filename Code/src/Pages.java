
public class Pages {
    String ref_page;
    Integer slotNum;

    int pageIndex;


    Pages(String page, int pageIndex){
        this.ref_page = page;
        this.pageIndex = pageIndex;
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

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }
}
