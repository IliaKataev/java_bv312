public class Student{
    private String fullName;
    private int age;


    public String getFullName(){
        return fullName;
    }
    public void setFullName(){
        this.fullName = fullName;
    }
    public int getAge(){
        return age;
    }
    public void setAge(int age){
        this.age = age;
    }

    public Student(String fullName, int age){
        this.fullName = fullName;
        this.age = age;
    }

    public String toString(){
        return fullName+ " " +age;
    }

}
