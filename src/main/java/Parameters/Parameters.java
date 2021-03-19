package Parameters;

public class Parameters {

    public static final String FullPathToSave = "\\\\192.168.90.203\\Logistyka\\ListyBonowPracyKrotkiCzas\\this_year_only.xlsx";
    public static final String PathToDirectory = "\\\\192.168.90.203\\Logistyka\\ListyBonowPracyKrotkiCzas\\";
    public static final String FileName_year  = "year.xlsx";
    public static final String FileName_month  = "month.xlsx";
    public static final String FileName_week  = "week.xlsx";
    public static final String FileName_today  = "today.xlsx";


    public static String getFileName_year() {
        return FileName_year;
    }

    public static String getFileName_month() {
        return FileName_month;
    }

    public static String getFileName_week() {
        return FileName_week;
    }

    public static String getFileName_today() {
        return FileName_today;
    }

    public static String getPathToDirectory() {
        return PathToDirectory;
    }


    public static String getFullPathToSave() {
        return FullPathToSave;
    }
}
