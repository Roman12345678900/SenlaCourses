package senla.com.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JsonMapper {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final ModelMapper modelMapper = new ModelMapper();

    @SneakyThrows
    public <T> String serialize(T object) {
        return objectMapper.writeValueAsString(object);
    }

    @SneakyThrows
    public <T> T deserialize(String json, Class<T> valueType) {
        return objectMapper.readValue(json, valueType);
    }

    public <T, S> T mapObject(S source, Class<T> destinationType) {
        return modelMapper.map(source, destinationType);
    }
}