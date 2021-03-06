package com.mi.bookvillage.common.domain.point;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PointMapper {

    List<PointVO> getPointListById(String userId);
    List<PointVO> getPointListBySeq(int userSeq);
    PointVO getPointListByRentalSeq(int rentalSeq);
    Integer getPreviousTotalPoint(int userSeq);
    void transactionPoint(PointVO pointVO);
}
