package com.code.dojo.demo.design.patterns.prototype.pattern;

public class SearchWord {

    private String keyword;
    private int  count;
    private long lastUpdateTime;
    public String getKeyword() {
        return keyword;
    }
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public long getLastUpdateTime() {
        return lastUpdateTime;
    }
    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
    public SearchWord(String keyword, int count, long lastUpdateTime) {
        super();
        this.keyword = keyword;
        this.count = count;
        this.lastUpdateTime = lastUpdateTime;
    }
    
    
}
