package Werkuren;

import Excel.ExcelFIle;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class WerkurenFactory {

    private  TimePeroid timePeroid;

    public WerkurenFactory(TimePeroid t){
        this.timePeroid = t;
    }

    public void  create() throws Exception {

        WerkurenObjService service = new WerkurenObjService(timePeroid);
        service.retriveWerkurenList();

        service.getTimeDiffinMinute();

        service.getWerkurenObjetcs().stream()
                .filter(x-> x.getTimeSpendInMinutes() <= 10)
                .filter(x-> x.getDATUM().contains(getCurrentYear()))
                .collect(Collectors.toList());



        //filter werkbnons < 10min
        List<WerkurenObj> final_list_only_this_year = service.getWerkurenObjetcs().stream()
                .filter(x-> x.getTimeSpendInMinutes() <= 10)
                .filter(x-> x.getDATUM().contains(getCurrentYear()))
                .collect(Collectors.toList());


        // save to file

        ExcelFIle e =  new ExcelFIle(timePeroid);
        e.CreateFile2(final_list_only_this_year);
    }

    private static String getCurrentYear() {

        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        return  String.valueOf(year);
    }

}
