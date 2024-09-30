package senla.com.repository;

import senla.com.entity.CardInfo;

import java.util.List;

public interface CardInfoRepository extends GenericRepository<CardInfo, Long> {

    List<CardInfo> findAllWithUserCriteria();

    List<CardInfo> findAllWithUserJPQL();

    List<CardInfo> findAllWithUserEntityGraph();
}
