import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created with IntelliJ IDEA.
 * User: sk.saad
 * Date: 6/24/13
 * Time: 2:51 PM
 * To change this template use File | Settings | File Templates.
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@interface EmployeeName {
    int minLengthOfName() default 3 ;
    int maxLengthOfName() default 50 ;
}
@Documented
@Retention(RetentionPolicy.RUNTIME)
@interface EmployeeAge {
    int maxAge() default 60 ;
    int minAge() default 20 ;
}
@Documented
@Retention(RetentionPolicy.RUNTIME)
@interface EmployeeTelephoneNumber {
    String telephoneNumberStartsWith() default "+88" ;
    int telephoneNumberLength() default 14 ;
}
@Documented
@Retention(RetentionPolicy.RUNTIME)
@interface EmployeeEmailID {
    String emailIDEndsWith() default "@therapservices.net" ;

}
