import java.util.*;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) {

        String ref_str = "";
        ArrayList<Character> ref_rand_list = new ArrayList<>(); // for unique  page configuration
        List<String> final_ref_list = new ArrayList<>(); // final ref list
        ArrayList<Pages> pages_list_FIFO = new ArrayList<>();
        ArrayList<Pages> pages_list_LRU = new ArrayList<>();
        ArrayList<Pages> pages_list_MIN = new ArrayList<>();
        ArrayList<Pages> pages_list_RAND = new ArrayList<>();
        ArrayList<Double> rates_collections = new ArrayList<>();
        Random rand = new Random(61);
        char page;
        int count = 0;
        int page_len = 0;
        int uniq_page = 0;
        int slots = 0;
        String max_algor = "";
        String min_algor = "";
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
            Pages page_obj = new Pages(final_ref_list.get(j), j);
            pages_list_FIFO.add(page_obj);
        }

        for (int j = 0; j < final_ref_list.size(); j++) {
            Pages page_obj = new Pages(final_ref_list.get(j), j);
            pages_list_LRU.add(page_obj);
        }

        for (int j = 0; j < final_ref_list.size(); j++) {
            Pages page_obj = new Pages(final_ref_list.get(j), j);
            pages_list_MIN.add(page_obj);
        }

        for (int j = 0; j < final_ref_list.size(); j++) {
            Pages page_obj = new Pages(final_ref_list.get(j), j);
            pages_list_RAND.add(page_obj);
        }

        //FIFO Page replacement Algorithm
        FIFO fifoAlgo = new FIFO(pages_list_FIFO, slots) ;
        fifoAlgo.runSchedule();
        fifoAlgo.setup();

        //LRU Page replacement Algorithm
        LRU LRUAlgo = new LRU(pages_list_LRU,slots);
        LRUAlgo.runSchedule();
        LRUAlgo.setup();

        //MIN Page replacement Algorithm
        MIN MINAlgo = new MIN(pages_list_MIN,slots);
        MINAlgo.runSchedule();
        MINAlgo.setup();


        //RAND Page replacement Algorithm
        RAND RANDAlgo = new RAND(pages_list_RAND,slots);
        RANDAlgo.runSchedule();
        RANDAlgo.setup();

        //Best or Worse calculation
        rates_collections.add((double)fifoAlgo.getHitCount()/page_len); // index 0
        rates_collections.add((double)LRUAlgo.getHitCount()/page_len); // index 1
        rates_collections.add((double)MINAlgo.getHitCount()/page_len); //index 2
        rates_collections.add((double)RANDAlgo.getHitCount()/page_len); //index 3

        int max_index = rates_collections.indexOf(Collections.max(rates_collections)); // Worse algorithm

        if (max_index == 0){
            max_algor = "FIFO";
        }
        if (max_index == 1){
            max_algor = "LRU";
        }
        if (max_index == 2){
            max_algor = "MIN";
        }
        if (max_index == 3){
            max_algor = "RAND";
        }
        int min_index = rates_collections.indexOf(Collections.min(rates_collections)); // Best algorithm

        if (min_index == 0){
            min_algor = "FIFO";
        }
        if (min_index == 1){
            min_algor = "LRU";
        }
        if (min_index == 2){
            min_algor = "MIN";
        }
        if (min_index == 3){
            min_algor = "RAND";
        }


        //Cache Hits Rates
        System.out.println("Cache Hit Rates: ");
        System.out.printf("FIFO : %2s of %2s = %.2f", fifoAlgo.getHitCount(), page_len, (double)fifoAlgo.getHitCount()/page_len);
        System.out.printf("\nLRU  : %2s of %2s = %.2f", LRUAlgo.getHitCount(), page_len, (double)LRUAlgo.getHitCount()/page_len);
        System.out.printf("\nMIN  : %2s of %2s = %.2f", MINAlgo.getHitCount(), page_len, (double)MINAlgo.getHitCount()/page_len);
        System.out.printf("\nRAND : %2s of %2s = %.2f", RANDAlgo.getHitCount(), page_len, (double)RANDAlgo.getHitCount()/page_len);
        System.out.printf("\nBest: " + max_algor);
        System.out.printf("\nWorst: " + min_algor);

        System.out.println();
        System.out.println();

        System.out.println("Cache Miss Rates: ");
        System.out.printf("FIFO : %2s of %2s = %.2f", fifoAlgo.getMissCount(), page_len, (double)fifoAlgo.getMissCount()/page_len);
        System.out.printf("\nLRU  : %2s of %2s = %.2f", LRUAlgo.getMissCount(), page_len, (double)LRUAlgo.getMissCount()/page_len);
        System.out.printf("\nMIN  : %2s of %2s = %.2f", MINAlgo.getMissCount(), page_len, (double)MINAlgo.getMissCount()/page_len);
        System.out.printf("\nRAND : %2s of %2s = %.2f", RANDAlgo.getMissCount(), page_len, (double)RANDAlgo.getMissCount()/page_len);
        System.out.printf("\nBest: " + max_algor);
        System.out.printf("\nWorst: " + min_algor);

        System.out.println();
        System.out.println();

    }

}
