package heaptest;

import java.util.LinkedList;
import java.util.List;

public class Katzenwahnsinn {
    public static void main( String [] args) {
        for (int i =0; i<4; i++) {
            List<Katze> k = machKatzen(100000);
            warte(6000);
        }
    }

        private static List<Katze> machKatzen(int anzahl) {
//            Thread.dumpStack(); //damit kann ich ohne Fehler den Stacktrace printen
            List<Katze> ret =new LinkedList<Katze>();
            for (int anz = 0; anz<anzahl; anz++) {
                ret.add( new Katze("Katze " + anz, anz));
            }
            return ret;
    }

    private static void warte(long millisekunden) {
        try {
            Thread.sleep(millisekunden);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
