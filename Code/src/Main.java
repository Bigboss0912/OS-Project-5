import java.util.*;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) {

        String ref_str = "";
        ArrayList<Character> ref_rand_list = new ArrayList<>(); // for unique  page configuration
        List<String> final_ref_list = new ArrayList<>(); // final ref list
        ArrayList<Pages> pages_list = new ArrayList<>();
        Random rand = new Random(61);
        char page;
        int count = 0;
        int page_len = 0;
        int uniq_page = 0;
        int slots = 0;
        boolean page_len_catch = false;
        boolean uniq_page_catch = false;
        boolean slots_catch = false;

        Scanner input = new Scanner(System.in);
        System.out.println("\nWelcome to this AWESOME Cache Replacement Algorithm Assignment!!!\n");

        while (page_len_catch == false) {
                System.out.print("Enter page reference pattern length: ");
                page_len = input.nextInt();
                if (page_len <= 9 || page_len >= 101) {
                    System.out.println("Please enter page reference pattern length between 10-100!!!");
                    page_len_catch = false;
                } else {
                    page_len_catch = true;
                }
        }
        while (uniq_page_catch == false) {
            System.out.print("\nEnter number of unique pages: ");
            uniq_page = input.nextInt();
            if (uniq_page <= 1 || uniq_page >= 16) {
                System.out.println("Please enter unique page number between 2-15!!!");
                uniq_page_catch = false;
            } else {
                uniq_page_catch = true;
            }
        }
        while (slots_catch == false) {
            System.out.print("\nEnter number of slots: ");
            slots = input.nextInt();
            if (slots <= 1 || slots >= 11) {
                System.out.println("Please enter slots number between 2-10!!!");
                slots_catch = false;
            } else {
                slots_catch = true;
            }
        }


        for (page = 'A'; page <= 'Z'; page++) {
            if (count < uniq_page) {
                ref_rand_list.add(page);
                ref_str += page + " ";
                count++;
            } else {
                break;
            }
        }

        count = uniq_page;
        for (int i = 0; i < page_len-uniq_page; i++) {
            if (count < page_len) {
                ref_str += ref_rand_list.get(rand.nextInt(ref_rand_list.size())) + " ";
            }
        }

        final_ref_list = Arrays.asList(ref_str.split(" "));
        System.out.println("\nRef Str: " + ref_str);

        for (int j = 0; j < final_ref_list.size(); j++) {
            Pages page_obj = new Pages(final_ref_list.get(j));
            pages_list.add(page_obj);
        }

        //FIFO Page replacement Algorithm
        FIFO fifoAlgo = new FIFO(pages_list, slots) ;
        fifoAlgo.runSchedule();


        //LRU Page replacement Algorithm

        //MIN Page replacement Algorithm

        //RAND Page replacement Algorithm

        //Cache Hits Rates
        System.out.println("Cache Hit Rates: ");
        System.out.printf("FIFO : ", fifoAlgo.getHitCount(), " of ", page_len, " = ", (double)fifoAlgo.getHitCount()/page_len);
        System.out.println("\nLRU  : ");
        System.out.println("\nMIN  : ");
        System.out.println("\nRAND : ");
        System.out.println("\nBest: ");
        System.out.println("\nWorst: ");


    }

}
