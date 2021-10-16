package testdata.sample;

public enum GlobalTestData {

    MANILA("manila");

    private String value;

    GlobalTestData(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public GlobalTestData getByValue(String value){
        for(GlobalTestData currentValue: values()){
            if(currentValue.value().equals(value)){
                return currentValue;
            }
        }
        throw new RuntimeException("Failed to find ConfigurationKeyEnum");
    }

}
