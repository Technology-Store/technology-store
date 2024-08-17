package com.winnguyen1905.technologystore.repository.rewriter;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.QueryRewriter;

public class ProductQueryRewriter implements QueryRewriter {

    @Override
    public String rewrite(String query, Sort sort) {
        return query.replaceAll("products", "products");
    }

}