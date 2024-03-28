package com.mockcompany.webapp.controller;

import com.mockcompany.webapp.api.SearchReportResponse;
import com.mockcompany.webapp.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Management decided it is super important that we have lots of products that match the following terms.
 * So much so, that they would like a daily report of the number of products for each term along with the total
 * product count.
 */
@RestController
public class ReportController {

    /**
     * The people that wrote this code didn't know about JPA Spring Repository interfaces!
     */

    @Autowired
    SearchService searchService;


    @GetMapping("/api/products/report")
    public SearchReportResponse runReport() {
        Map<String, Integer> hits = new HashMap<>();
        SearchReportResponse response = new SearchReportResponse();
        response.setSearchTermHits(hits);

        //Get unique Ids of items matching search for cool
        HashSet<Number> matchingIds = new HashSet<>();
        searchService.search("cool").forEach((item) -> matchingIds.add(item.getId()));

        response.getSearchTermHits().put("Cool", matchingIds.size());

        response.setProductCount(searchService.search("").size());

        int kidCount = searchService.search("kids").size();
        int perfectCount = searchService.search("perfect").size();

        response.getSearchTermHits().put("Kids", kidCount);

        response.getSearchTermHits().put("Amazing", searchService.search("amazing").size());

        hits.put("Perfect", perfectCount);

        return response;
    }
}
