package senla.com.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import senla.com.dto.PaymentsDto;
import senla.com.entity.Payments;
import senla.com.mapper.GenericMapper;
import senla.com.repository.PaymentsRepository;
import senla.com.service.PaymentsService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentsServiceImpl implements PaymentsService {

    private final PaymentsRepository paymentsRepository;
    private final GenericMapper genericMapper;

    @Override
    public PaymentsDto findById(Long id) {
        return genericMapper.convertToDto(paymentsRepository.findById(id), PaymentsDto.class);
    }

    @Override
    public List<PaymentsDto> findAll() {
        return paymentsRepository.findAll().stream()
                .map(payments -> genericMapper.convertToDto(payments,PaymentsDto.class))
                .toList();
    }

    @Override
    public void save(PaymentsDto paymentsDto) {
        Payments payments = genericMapper.convertToEntity(paymentsDto, Payments.class);
        paymentsRepository.save(payments);
    }

    @Override
    public void deleteById(Long id) {
        paymentsRepository.deleteById(id);
    }
}
