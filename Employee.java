/**
 * Created with IntelliJ IDEA.
 * User: sk.saad
 * Date: 6/24/13
 * Time: 2:40 PM
 * To change this template use File | Settings | File Templates.
 */


public class Employee {
    @EmployeeName
    final private String name;
    @EmployeeAge
    private int age;
    @EmployeeTelephoneNumber
    private String telephoneNumber;
    @EmployeeEmailID
    final private String emailID;

    public Employee( String name, int age, String telephoneNumber, String emailID ) {
        this.name= name ;
        this.age = age ;
        this.telephoneNumber = telephoneNumber ;
        this.emailID = emailID ;
    }

    public void setAge ( int age ) {
        this.age = age ;
    }
    public int getAge () {
        return age ;
    }

    public void setTelephoneNumber( String telephoneNumber ) {
        this.telephoneNumber = telephoneNumber ;
    }
    public String getTelephoneNumber () {
        return telephoneNumber ;
    }
}
