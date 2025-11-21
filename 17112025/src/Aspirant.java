public class Aspirant extends Student {

    private String workName;

    public Aspirant(String fullName, int age, String workName) {
        super(fullName, age);
        this.workName = workName;
    }

    public String getWorkName(){
        return workName;
    }

    public void setWorkName(String workName){
        this.workName = workName;
    }

    public String toString(){
        return super.toString() + " " +workName;
    }
}

