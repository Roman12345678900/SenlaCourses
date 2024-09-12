package senla.com.transactional;

public @interface Transaction {
    String value() default "";
}
