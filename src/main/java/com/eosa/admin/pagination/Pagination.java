package com.eosa.admin.pagination;

import lombok.Getter;
import lombok.Setter;

/**
 * packageName    : idenit.ble.admin.pagination
 * fileName       : Pagination
 * author         : Jihun Kim
 * date           : 2022-09-08
 * description    : 페이징 클래스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-08        Jihun Kim       최초 생성
 */
@Getter
@Setter
public class Pagination {

    private int pageSize = 10;           // 게시글 개수
    private int blockSize = 5;           // 페이징 사이즈
    private int page = 1;               // 현재 페이지
    private int block = 1;              // 현재 블록
    private int totalListCnt;           // 총 게시글 수
    private int totalPageCnt;           // 총 페이지 수
    private int totalBlockCnt;          // 총 블록 수
    private int startPage = 1;          // 블록 시작 페이지
    private int endPage = 1;            // 블록 마지막 페이지
    private int startIndex = 0;         // offset
    private int prevBlock;              // 이전 블록의 마지막 페이지
    private int nextBlock;              // 다음 블록의 시작 페이지

    public Pagination(int totalListCnt, int page) {
        setPage(page);
        setTotalListCnt(totalListCnt);

        setTotalPageCnt((int) Math.ceil(totalListCnt * 1.0 / pageSize));
        setTotalBlockCnt((int) Math.ceil(totalPageCnt * 1.0 / blockSize));
        setBlock((int) Math.ceil((page * 1.0) / blockSize));
        setStartPage((block - 1) * blockSize + 1);
        setEndPage(startPage + blockSize - 1);

        if (endPage > totalPageCnt) {
            this.endPage = totalPageCnt;
        }

        if (totalPageCnt == 0) {
            this.endPage = 1;
        }

        setPrevBlock((block * blockSize) - blockSize);

        if (prevBlock < 1) {
            this.prevBlock = 1;
        }

        setNextBlock((block * blockSize) + 1);

        if (nextBlock > totalPageCnt) {
            nextBlock = totalPageCnt;
        }

        if (totalPageCnt == 0) {
            nextBlock = 1;
        }
        setStartIndex((page - 1) * pageSize);

    }

}
