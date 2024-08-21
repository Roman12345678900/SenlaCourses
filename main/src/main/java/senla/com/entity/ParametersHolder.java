package senla.com.entity;

import lombok.Getter;
import senla.com.annotation.Component;
import senla.com.annotation.Value;

@Getter
@Component
public class ParametersHolder {
    @Value("parameters.holder.sometext")
    private String someText;
}
