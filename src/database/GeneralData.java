package database;

public class GeneralData {
    public static CustomerDetails details = new CustomerDetails();
    public static void checkDate(int date, int month, int year){
        if(date < 0 || month < 0 || year < 0)
            throw new IllegalArgumentException("Incorrect date");
        switch(month){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                if(date > 31)
                    throw new IllegalArgumentException("Incorrect date");
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                if(date > 30)
                    throw new IllegalArgumentException("Incorrect date");
                break;
            case 2:
                //if((year%4 == 0) && (date > 29))
                //    throw new IllegalArgumentException("Incorrect date");
                if(date > 29)
                    throw new IllegalArgumentException("Incorrect date");
                break;
            default:
                throw new IllegalArgumentException("Incorrect date");
        }
    }
}
