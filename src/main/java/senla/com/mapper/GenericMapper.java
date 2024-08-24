package senla.com.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GenericMapper {

    private final ModelMapper modelMapper;

    public <S, T> T convertToDto(S source, Class<T> targetClass) {
        return modelMapper.map(source, targetClass);
    }

    public <S, T> S convertToEntity(T target, Class<S> sourceClass) {
        return modelMapper.map(target, sourceClass);
    }
}