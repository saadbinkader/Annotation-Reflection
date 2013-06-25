import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: sk.saad
 * Date: 6/24/13
 * Time: 3:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class EmployeeValidator {
    public static void main( String args[] ) {
        Employee[] employeeList = { new Employee("Ra",21,"+8801818941545","raqibul@therapservices.net")
                                ,new Employee("Dipto",20,"+880112345","diptopal@therapservices.net")
                                ,new Employee("Bibhash",21,"+8801818ABC123","bibhash@gmail.com") } ;


        for ( Employee employee : employeeList ) {
            Class reflectedClass = employee.getClass();
            String className = reflectedClass.getName() ;
            System.out.println("Class Name; ["+className+"]");

            Method[] classMethods = reflectedClass.getMethods();
            for ( Method method : classMethods ) {
                System.out.print( "Method Name:[" + method.getName() +"] " );
                if ( method.getName().startsWith("get") ) {
                    System.out.println(" Getter Method");
                }
                else if ( method.getName().startsWith("set") ) {
                    System.out.println(" Setter Method");
                }
                System.out.println("Return Type: " + method.getReturnType());
                Class[] parameterType = method.getParameterTypes();
                System.out.print("Parameters :[");
                for(Class parameter : parameterType) {
                    System.out.print(parameter.getName() + ',');
                }
                System.out.println("]");
            }

            Field nameField = null ;
            Field ageField = null ;
            Field telephoneNumberField = null ;
            Field emailIDField = null ;

            System.out.println("|--------------------------------------------|");

            try {
                nameField = reflectedClass.getDeclaredField("name") ;
                nameField.setAccessible(true);
                int minLengthOfName = nameField.getAnnotation(EmployeeName.class).minLengthOfName() ;
                int maxLengthOfName = nameField.getAnnotation(EmployeeName.class).maxLengthOfName() ;
                String name = (String) nameField.get(employee);

                if ( !regexChecker( "[A-Za-z]+" , name ) ) {
                    System.out.println("Invalid Name["+name+"]");
                }
                else if ( name.length() < minLengthOfName )
                    System.out.println("Name Too Short["+name+"]");
                else if ( name.length() > maxLengthOfName )
                    System.out.println("Name Too Long["+name+"]");
                else
                    System.out.println("Name :["+name+"] OK");

                ageField = reflectedClass.getDeclaredField("age") ;
                ageField.setAccessible(true);
                int minAge = ageField.getAnnotation(EmployeeAge.class).minAge();
                int maxAge = ageField.getAnnotation(EmployeeAge.class).maxAge();
                int age = ageField.getInt(employee) ;

                if ( age < minAge )
                    System.out.println("Under Aged["+age+"]");
                else if ( age > maxAge )
                    System.out.println("Over Aged["+age+"]");
                else
                    System.out.println("Age :["+age+"] OK");

                telephoneNumberField = reflectedClass.getDeclaredField("telephoneNumber") ;
                telephoneNumberField.setAccessible(true);

                String telephoneNumber = (String) telephoneNumberField.get(employee);
                String telephoneNumberStartsWith = telephoneNumberField.getAnnotation(EmployeeTelephoneNumber.class).telephoneNumberStartsWith() ;
                int telephoneNumberLength = telephoneNumberField.getAnnotation(EmployeeTelephoneNumber.class).telephoneNumberLength() ;

                String telephoneNumberPattern = "\\" + telephoneNumberStartsWith + "\\d{"+(telephoneNumberLength-3)+"}" ;

                if ( !regexChecker(telephoneNumberPattern,telephoneNumber) || telephoneNumber.length() != telephoneNumberLength )
                    System.out.println("Bad Telephone Number["+telephoneNumber+"]");
                else
                    System.out.println("Telephone Number["+telephoneNumber+"] OK");

                emailIDField = reflectedClass.getDeclaredField("emailID") ;
                emailIDField.setAccessible(true);
                String emailID = (String) emailIDField.get(employee);
                String emailIDEndsWith = emailIDField.getAnnotation(EmployeeEmailID.class).emailIDEndsWith() ;

                String emailIDPattern =  "[A-Za-z0-9_-]+" + emailIDEndsWith;
                if ( !regexChecker( emailIDPattern , emailID) )
                    System.out.println("Bad Email ID:["+emailID+"]");
                else
                    System.out.println("Email ID:["+emailID+"] OK");


                Class[] methodParameters = new Class[]{Integer.TYPE};
                Object[] parameters = new Object[]{new Integer(10)};

                Method privateMethod = employee.getClass().getDeclaredMethod("showJobStatistics", methodParameters);
                privateMethod.setAccessible(true);
                privateMethod.invoke(employee, parameters) ;

            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            System.out.println("|--------------------------------------------|");
                                                                                         }
    }
    public static boolean regexChecker(String regularExpression, String stringToCheck){
        Pattern checkRegex = Pattern.compile(regularExpression);
        Matcher regexMatcher = checkRegex.matcher( stringToCheck );
        if ( regexMatcher.find( ) && ( regexMatcher.start() == 0 && regexMatcher.end() == stringToCheck.length() ) )
            return true ;
        return false;
    }
}
