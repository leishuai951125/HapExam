package hbi.demo.mapper;

import hbi.demo.dto.OrderHeadersOnLines;

import java.util.List;

public interface OrderHeadersOnLinesMapper {
    List<OrderHeadersOnLines> selectHeaderById(Long Id);
}
