package Werkuren;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@ToString
@AllArgsConstructor
public class WerkurenObj {

    private int timeSpendInMinutes = 0;
    private String  WERKNEMER;
    private String   WERKBONNUMMER;
    private String   DATUM;
    private String   BEGINTIJDH;
    private String   BEGINTIJDM60;
    private String   EINDTIJDH;
    private String    EINDTIJDM60;




}
