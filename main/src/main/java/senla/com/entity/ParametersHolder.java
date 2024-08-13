package senla.com.entity;

import lombok.Getter;
import senla.com.annotation.Component;
import senla.com.annotation.Value;

@Component
public class ParametersHolder {
    @Getter
    @Value("parameters.holder.sometext")
    private String someText;
}
