package com.eosa.admin.util.pagination;

import java.util.List;

import lombok.Data;

@Data
public class PostList {

    private int POST_COUNT;
    private int BLOCK_COUNT;

    public PostList(int POST_COUNT, int BLOCK_COUNT) {
        this.POST_COUNT = POST_COUNT;
        this.BLOCK_COUNT = BLOCK_COUNT;
    }

    public int getCurrentPageStartPost(int currentPage) {
        return (currentPage - 1) * this.POST_COUNT;
    }
    
}
