package senla.com.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import senla.com.dto.CardInfoDto;
import senla.com.service.CardInfoService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/card-infos")
public class CardInfoController {

    private final CardInfoService cardInfoService;

    @GetMapping("/{id}")
    public CardInfoDto findById(@PathVariable("id") Long id) {
        return cardInfoService.findById(id);
    }

    @GetMapping
    public List<CardInfoDto> findAll() {
        return cardInfoService.findAll();
    }

    @PostMapping
    public void save(@RequestBody CardInfoDto cardInfoDto) {
        cardInfoService.save(cardInfoDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        cardInfoService.deleteById(id);
    }
}
