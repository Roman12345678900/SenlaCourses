package senla.com.repository;

import senla.com.entity.CardInfo;

import java.util.List;

public interface CardInfoRepository {
    CardInfo findById(Long id);

    List<CardInfo> findAll();

    void save(CardInfo cardInfo);

    void deleteById(Long id);
}
