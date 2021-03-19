package Werkuren;

import DBConnectorFATDB.DBConnectorFATDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WerkurenObjService {

private List<WerkurenObj> werkurenObjetcs;

private TimePeroid timeperoid;

    public WerkurenObjService(TimePeroid timeperoid) {
        this.timeperoid = timeperoid;
        this.werkurenObjetcs =  new ArrayList<>();
    }

    public List<WerkurenObj> getWerkurenObjetcs() {
        return werkurenObjetcs;
    }


    public void getTimeDiffinMinute() throws Exception {

        for(int i = 0; i < werkurenObjetcs.size(); i++) {
            Date beginDate = this.getBeginDate(werkurenObjetcs.get(i).getBEGINTIJDH(), werkurenObjetcs.get(i).getBEGINTIJDM60());
            Date endDate   = this.getEndDate(werkurenObjetcs.get(i).getEINDTIJDH(),  werkurenObjetcs.get(i).getEINDTIJDM60());

            long diffInMillies = Math.abs(beginDate.getTime() - endDate.getTime());
            int minutes_from_long = (int) ((diffInMillies / 1000) / 60);

            werkurenObjetcs.get(i).setTimeSpendInMinutes(minutes_from_long);
        }
    }


    public Date getBeginDate(String beginHour, String beginMinutes) throws Exception {
        return createTimeFromString(beginHour,beginMinutes);

    }

    public Date getEndDate(String beginHour, String beginMinutes) throws Exception {
        return createTimeFromString(beginHour,beginMinutes);

    }

    private Date createTimeFromString(String hours, String minutes) throws Exception {
        StringBuilder sb_hours = new StringBuilder(hours);
        StringBuilder sb_minutes = new StringBuilder(minutes);
        int parsed_hours  = Integer.parseInt(hours);

        if(parsed_hours > 24) {
            throw new Exception("parsed hours is over 24h format");
        }

        if(minutes.length() == 1) {
            sb_minutes.insert(0,"0");
        }
        if(hours.length() ==1) {
            sb_hours.insert(0,"0");
        }

        StringBuilder time = new StringBuilder();
            time.append(sb_hours);
            time.append(":");
            time.append(sb_minutes);

        DateFormat sdf = new SimpleDateFormat("HH:mm"); // or "hh:mm" for 12 hour format
        Date date = sdf.parse(String.valueOf(time));

        return date;
    }




    public void retriveWerkurenList() throws SQLException {
        Connection connection = DBConnectorFATDB.dbConnector();
        ResultSet rs = null;
        PreparedStatement pstmnt= null;

        String sql = "";

        try {
            if(timeperoid == TimePeroid.YEAR) {
                sql =   "select WERKNEMER ,WERKBONNUMMER , DATUM , BEGINTIJDH , BEGINTIJDM60 , EINDTIJDH ,EINDTIJDM60 \n" +
                            "from werkuren w \n" +
                            "where STATUS  = ? and  DATUM > (current_date() - interval 365 day )";
            }
            else if(timeperoid == TimePeroid.MONTH) {
                sql =   "select WERKNEMER ,WERKBONNUMMER , DATUM , BEGINTIJDH , BEGINTIJDM60 , EINDTIJDH ,EINDTIJDM60 \n" +
                        "from werkuren w \n" +
                        "where STATUS  = ? and  DATUM > (current_date() - interval 30 day )";
            }

            else if(timeperoid == TimePeroid.WEEK) {
                sql =   "select WERKNEMER ,WERKBONNUMMER , DATUM , BEGINTIJDH , BEGINTIJDM60 , EINDTIJDH ,EINDTIJDM60 \n" +
                        "from werkuren w \n" +
                        "where STATUS  = ? and DATUM > (current_date() - interval 7 day )";
            }

            else if(timeperoid == TimePeroid.TODAY) {
                sql =   "select WERKNEMER ,WERKBONNUMMER , DATUM , BEGINTIJDH , BEGINTIJDM60 , EINDTIJDH ,EINDTIJDM60 \n" +
                        "from werkuren w \n" +
                        "where STATUS  = ? and DATUM > (current_date() - interval 1 day )";
            }

                pstmnt = connection.prepareStatement(sql);
                pstmnt.setString(1, "20"); // not finished workbon


                rs = pstmnt.executeQuery();


                if (rs.next() == false) {
                    System.out.println("There is no record in database");
                } else {
                    do {
                        WerkurenObj ob = new WerkurenObj(
                                0,
                                rs.getString("WERKNEMER"),
                                rs.getString("WERKBONNUMMER"),
                                rs.getString("DATUM"),
                                rs.getString("BEGINTIJDH"),
                                rs.getString("BEGINTIJDM60"),
                                rs.getString("EINDTIJDH"),
                                rs.getString("EINDTIJDM60")
                        );
                        werkurenObjetcs.add(ob);
                    } while (rs.next());
                }
        }
        catch(Exception e) {
            System.out.println("Cannot Retrive from DB!");
        }
        finally {
            rs.close();
            pstmnt.close();
            connection.close();
        }
    }
}
