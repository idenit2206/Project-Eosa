package com.eosa.admin.util.pagination;

import lombok.Data;

@Data
public class PageList {

    private int POST_COUNT; // 한 블록에서 보여줄 게시물의 개수
    private int BLOCK_COUNT; // 한번에 보여줄 블록의 수
    private int firstBlock; // 모든 블록 중 가장 첫 번째 블록 값 보통 1
    private int lastBlock; // 모든 블록 중 가장 마지막 블록의 값
    private int blockFirst; // 현재 나열된 블록 중 가장 첫 번째 블록의 값
    private int blockLast; // 현재 나열된 블록 중 가장 마지막 블록의 값
    private int prevBlock; // 현재 나열된 블록 목록 기준 이전 블록 목록의 가장 첫 번째 블록 값 
    private int nextBlock; // 현재 나열된 블록 목록 기준 다음 블록 목록의 가장 첫 번째 블록 값

    public PageList(int POST_COUNT, int BLOCK_COUNT, int currentPage, int allPostCount) {
        //int currentpage 현재 페이지 값
        //int allPostCount 페이징 처리를 하려는 포스트 항목의 모든 포스트 개수
        this.POST_COUNT = POST_COUNT;
        this.BLOCK_COUNT = BLOCK_COUNT;
        this.firstBlock = 1;
        this.lastBlock = (allPostCount / POST_COUNT) + 1;
        this.blockFirst = ((currentPage -1) / BLOCK_COUNT) * BLOCK_COUNT + 1;        
        // this.blockLast = (blockFirst + BLOCK_COUNT) - 1;        
        // this.prevBlock = blockFirst - BLOCK_COUNT;     
        // this.nextBlock = blockFirst + BLOCK_COUNT;
        
        // blockLast, prevBlock, nextBlock
        if(lastBlock == 1) {
            this.blockLast = 1;
            this.prevBlock = 1;
            this.nextBlock = 1;
        }
        else {
            this.blockLast = (blockFirst + BLOCK_COUNT) - 1;
            this.prevBlock = blockFirst - BLOCK_COUNT;
            this.nextBlock = blockFirst + BLOCK_COUNT;
        }
        
       
    }
    
}
