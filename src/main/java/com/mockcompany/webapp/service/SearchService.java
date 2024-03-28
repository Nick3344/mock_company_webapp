package com.mockcompany.webapp.service;

import com.mockcompany.webapp.data.ProductItemRepository;
import com.mockcompany.webapp.model.ProductItem;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class SearchService {

    private final ProductItemRepository productItemRepository;

    public SearchService(ProductItemRepository productItemRepository) {
        this.productItemRepository = productItemRepository;
    }

    /**
     * The search method, takes a string param and queries the database for items with
     * name or description which match the query string.
     * @param query
     * @return The filtered products
     */
    public Collection<ProductItem> search(String query) {

        if (query == null) {
            return (List<ProductItem>) this.productItemRepository.findAll();
        }

        String queryLower = query.toLowerCase();

        //Check if search is in "" intending to be exact match.
        boolean isExactSearch = queryLower.startsWith("\"") && queryLower.endsWith("\"");

        if (isExactSearch) {
            String exactQueryLower = queryLower.substring(1, queryLower.length() - 1);
            return this.productItemRepository.findLikeSearchExact(exactQueryLower);
        } else {
            return this.productItemRepository.findLikeSearch(queryLower);
        }
    }
}
