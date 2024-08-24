package senla.com.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import senla.com.dto.PaymentsDto;
import senla.com.mapper.JsonMapper;
import senla.com.service.PaymentsService;

@Controller
@RequiredArgsConstructor
public class PaymentsController {

    private final PaymentsService paymentsService;
    private final JsonMapper jsonMapper;

    public String findById(Long id) {
        return jsonMapper.serialize(paymentsService.findById(id));
    }

    public String findAll() {
        return jsonMapper.serialize(paymentsService.findAll());
    }

    public void save(String paymentsDto) {
        paymentsService.save(jsonMapper.deserialize(paymentsDto, PaymentsDto.class));
    }

    public void deleteById(Long id) {
        paymentsService.deleteById(id);
    }
}
