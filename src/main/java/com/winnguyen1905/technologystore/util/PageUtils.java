package com.winnguyen1905.technologystore.util;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class PageUtils {
    public static <T> Page<T> paginateList(List<T> list, Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<T> paginatedList;

        if (list.size() < startItem) {
            paginatedList = List.of(); // Empty list if the start item is beyond the list size
        } else {
            int toIndex = Math.min(startItem + pageSize, list.size());
            paginatedList = list.subList(startItem, toIndex);
        }

        return new PageImpl<>(paginatedList, pageable, list.size());
    }
}
