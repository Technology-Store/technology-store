package com.winnguyen1905.technologystore.util;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class PageUtils {
    public static <T> Page<T> paginateList(List<T> list, Pageable pageable) {
        Integer pageSize = pageable.getPageSize();
        Integer currentPage = pageable.getPageNumber();
        Integer startItem = currentPage * pageSize;
        List<T> paginatedList;

        if (list.size() < startItem) {
            paginatedList = List.of(); // Empty list if the start item is beyond the list size
        } else {
            Integer toIndex = Math.min(startItem + pageSize, list.size());
            paginatedList = list.subList(startItem, toIndex);
        }

        return new PageImpl<>(paginatedList, pageable, list.size());
    }
}
