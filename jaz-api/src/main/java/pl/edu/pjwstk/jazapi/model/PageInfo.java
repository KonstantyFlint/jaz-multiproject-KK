package pl.edu.pjwstk.jazapi.model;

import org.springframework.data.domain.Sort;

import java.util.LinkedHashMap;
import java.util.Map;

public class PageInfo {
    int page;
    int size;
    Long entityCount;
    String sortedBy;
    String sortingDirection;

    public PageInfo(){}

    public PageInfo(int page, int size, Long entityCount, String sortingDirection, String sortedBy) {
        this.page = page;
        this.size = size;
        this.entityCount = entityCount;
        this.sortedBy = sortedBy;
        this.sortingDirection = sortingDirection;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Long getEntityCount() {
        return entityCount;
    }

    public void setEntityCount(Long entityCount) {
        this.entityCount = entityCount;
    }

    public String getSortedBy() {
        return sortedBy;
    }

    public void setSortedBy(String sortedBy) {
        this.sortedBy = sortedBy;
    }

    public String getSortingDirection() {
        return sortingDirection;
    }

    public void setSortingDirection(String sortingDirection) {
        this.sortingDirection = sortingDirection;
    }

    public Map<String,Object> map(){
        LinkedHashMap<String,Object> map = new LinkedHashMap<>();
        map.put("page",page);
        map.put("size",size);
        map.put("entityCount",entityCount);
        map.put("sortingDirection", sortingDirection);
        map.put("sortedBy",sortedBy);
        return map;
    }
}
