
public class Pages {
    String ref_page;
    Integer slotNum;


    Pages(String page){
        this.ref_page = page;
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
}
