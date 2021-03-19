package MainClass;

import Werkuren.TimePeroid;
import Werkuren.WerkurenFactory;


public class main {

    public static void main(String[] args) throws Exception {

        WerkurenFactory YEAR = new WerkurenFactory(TimePeroid.YEAR);
        YEAR.create();

        WerkurenFactory MONTH = new WerkurenFactory(TimePeroid.MONTH);
        MONTH.create();

        WerkurenFactory WEEK = new WerkurenFactory(TimePeroid.WEEK);
        WEEK.create();

        WerkurenFactory TODAY = new WerkurenFactory(TimePeroid.TODAY);
        TODAY.create();

    }



}
