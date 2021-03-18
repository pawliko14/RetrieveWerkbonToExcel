import Excel.ExcelFIle;
import Werkuren.WerkurenObj;
import Werkuren.WerkurenObjService;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;


public class main {

    public static void main(String[] args) throws Exception {


        WerkurenObjService service = new WerkurenObjService();
        service.retriveWerkurenList();

         service.getTimeDiffinMinute();


        //filter werkbnons < 10min
        List<WerkurenObj>  final_list_only_this_year = service.getWerkurenObjetcs().stream()
                .filter(x-> x.getTimeSpendInMinutes() <= 10)
                .filter(x-> x.getDATUM().contains(getCurrentYear()))
                .collect(Collectors.toList());


        // save to file

        ExcelFIle e =  new ExcelFIle();
        e.CreateFile2(final_list_only_this_year);

    }

    // should return 2021
    private static String getCurrentYear() {

        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);

        return  String.valueOf(year);
    }

}
