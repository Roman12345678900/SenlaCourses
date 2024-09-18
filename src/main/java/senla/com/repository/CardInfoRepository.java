package senla.com.repository;

import senla.com.entity.CardInfo;

import java.util.List;

public interface CardInfoRepository {

    List<CardInfo> findAllWithUserCriteria();

    List<CardInfo> findAllWithUserJPQL();

    List<CardInfo> findAllWithUserEntityGraph();
}
