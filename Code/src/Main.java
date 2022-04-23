import java.util.*;

public class Main {
    public static void main(String[] args) {

        String ref_str = "";
        ArrayList<Character> ref_rand_list = new ArrayList<>(); // for unique  page configuration
        List<String> final_ref_list = new ArrayList<>(); // final ref list
        Random rand = new Random();
        char page;
        int count = 0;

        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to this AWESOME Cache Replacement Algorithm Assignment!!!\n");
        System.out.print("Enter page reference pattern length: ");
        int page_len = input.nextInt();
        System.out.print("\nEnter number of unique pages: ");
        int uniq_page = input.nextInt();
        System.out.print("\nEnter number of slots: ");
        int slots = input.nextInt();

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
        for (int i = 0; i < ref_rand_list.size(); i++) {
            if (count < page_len) {
                ref_str += ref_rand_list.get(rand.nextInt(ref_rand_list.size())) + " ";
            }
        }

        final_ref_list = Arrays.asList(ref_str.split(" "));
        System.out.println("\nRef Str: " + ref_str);


    }
}
