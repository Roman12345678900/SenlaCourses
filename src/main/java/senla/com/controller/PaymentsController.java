package senla.com.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import senla.com.dto.PaymentsDto;
import senla.com.service.PaymentsService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payments")
public class PaymentsController {

    private final PaymentsService paymentsService;

    @GetMapping("/{id}")
    public PaymentsDto findById(@PathVariable("id") Long id) {
        return paymentsService.findById(id);
    }

    @GetMapping
    public List<PaymentsDto> findAll() {
        return paymentsService.findAll();
    }

    @PostMapping
    public void save(@RequestBody PaymentsDto paymentsDto) {
        paymentsService.save(paymentsDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        paymentsService.deleteById(id);
    }
}
