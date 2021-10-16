package utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class TestDataGenerator {

    private String subsId;

    public String getSubsId() {
        return subsId;
    }

    public void setSubsId(String subsId) {
        this.subsId = subsId;
    }

    public String subsIdGenerator(){
        String pattern = " HH:mm:ss.SSSZ";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String generatedDate = simpleDateFormat.format(new Date());
        setSubsId(generatedDate);
        return generatedDate;
    }       // returns timestamp as unique param.

    public String randAmount(float min, float max) {
        String amt = null;
        Random rand = new Random();
        float random = min + rand.nextFloat() * (max - min);
        return amt.valueOf(random);
    }

    public String randAmount(int min, int max){
        Random random = new Random();
        int randomNumber = random.nextInt(max + 1 - min) + min;
        return String.valueOf(randomNumber);
    }

    public String getDayofTheWeek(){
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        return new String().valueOf(day);
    }

    public String getDateOfTheMonth(){
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return new String().valueOf(day);
    }

    public String invNoGenerator(){
        int max = 999999;
        int min = 100000;
        Random rand = new Random();
        int rand_int1 = rand.nextInt((max - min) + 1) + min;

        return String.format("%06d", rand_int1);
    }

}
