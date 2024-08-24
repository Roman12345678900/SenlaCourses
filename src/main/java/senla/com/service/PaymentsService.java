package senla.com.service;

import org.springframework.stereotype.Component;
import senla.com.dto.PaymentsDto;

import java.util.List;

@Component
public interface PaymentsService {
    PaymentsDto findById(Long id);

    List<PaymentsDto> findAll();

    void save(PaymentsDto paymentsDto);

    void deleteById(Long id);
}
