package senla.com.repository.implementation;

import org.springframework.stereotype.Repository;
import senla.com.entity.CardInfo;
import senla.com.repository.AbstractRepository;
import senla.com.repository.CardInfoRepository;

@Repository
public class CardInfoRepositoryImpl extends AbstractRepository<CardInfo> implements CardInfoRepository {
}
