import java.util.*;

public class Main {

    public void matchMaking(int [][] men, int [][] women){
        HashMap<Integer, Integer> couples = findCouples(men, women);
        System.out.println("\n------------------Wyniki----------------------------");
        Set<Integer> set = couples.keySet();
        for (int key : set) {
            System.out.println("Kobieta: " + key + " wybrała mężczyznę: " + couples.get(key));
        }
    }

    public HashMap<Integer, Integer> findCouples(int [][] men, int [][] women){
        HashMap<Integer, Integer> couples = new HashMap<>();
        for (int i = 0; i <women.length ; i++) {
            couples.put(i, null);
        }

        Set<Integer> kawalerzy = new HashSet<>();
        for (int i = 0; i <men.length ; i++) {
            kawalerzy.add(i);
        }

        int kawalerzyCount = kawalerzy.size();

        while(kawalerzyCount>0){

            int currentkawaler = kawalerzy.iterator().next();
            System.out.println ("\nMężczyzna " + currentkawaler + " szuka w tym momencie kobiety");

            int diff = men.length - women.length;
            if (kawalerzyCount == diff) {
                System.out.println ("\nMężczyzna " + currentkawaler + " zostaje sam.");
                break;
            }

            for (int kobiety = 0; kobiety <men[currentkawaler].length ; kobiety++) {

                if(couples.get(kobiety)==null){
                    couples.put(kobiety, currentkawaler);
                    System.out.println("Kobieta " + kobiety + " wybrała: " + currentkawaler);
                    kawalerzy.remove(currentkawaler);
                    break;
                }else{
                    int juzWybraly = couples.get(kobiety);
                    if(zmianaPartnera(currentkawaler, juzWybraly, kobiety, women)){
                        couples.put(kobiety, currentkawaler);
                        kawalerzy.add(juzWybraly);
                        kawalerzy.remove(currentkawaler);
                        System.out.println("Kobieta " + kobiety + " porzuciła meżczyznę: " + juzWybraly);
                        System.out.println("Kobieta " + kobiety + " wybrała mężczyznę: " + currentkawaler);
                        break; //
                    }
                }
            }
            kawalerzyCount = kawalerzy.size();
        }
        return couples;
    }

    boolean zmianaPartnera(int currentkawaler, int juzWybrany, int currentkobieta, int [][]women){

        int pref_currentkawaler = -1;
        int prefJuzWybrany = -1;

        for (int i = 0; i <women[currentkobieta].length ; i++) {
            if(women[currentkobieta][i]==currentkawaler)
                pref_currentkawaler = i;
            if(women[currentkobieta][i]==juzWybrany)
                prefJuzWybrany = i;
        }
        return pref_currentkawaler < prefJuzWybrany;
    }

    public static void main(String[] args) {
    //pierwszy scenariusz
        //4 mężczyzn - więcej mężczyzn
        int[][] men = {
                {0, 1, 2},
                {0, 1, 2},
                {0, 1, 2},
                {0, 1, 2}
        };
        // 3 kobiety - mniej kobiet
        int[][] women = {
                {1, 0, 3, 2},
                {1, 2, 0, 3},
                {0, 3, 1, 2}
        };

    //drugi scenariusz
        //tyle samo mężczyzn co kobiet
        int[][] men2 = {
                {0, 1, 2, 3},
                {0, 1, 2, 3},
                {0, 1, 2, 3},
                {0, 1, 2, 3}
        };

        int[][] women2 = {
                {1, 0, 3, 2},
                {1, 2, 0, 3},
                {0, 3, 1, 2},
                {0, 1, 3, 2}
        };

    //trzeci scenariusz
        //mniej mężczyzn - trzech
        int[][] men3 = {
                {0, 1, 2, 3},
                {0, 1, 2, 3},
                {0, 1, 2, 3},
        };

        // więcej kobiet - cztery
        int[][] women3 = {
                {1, 0, 2},
                {1, 2, 0},
                {0, 1, 2},
                {0, 1, 2}
        };

        Main sm = new Main();
        sm.matchMaking(men, women);
        Main m = new Main();
        m.matchMaking(men2, women2);
        Main x = new Main();
        x.matchMaking(men3, women3);
    }
}