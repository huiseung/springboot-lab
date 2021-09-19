package com.example.page.utils;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Paginator {
    private Integer pagePerBlock;
    private Integer postPerPage;
    private Long totalPostCount;

    private Integer totalLastPageNum;


    public Paginator(Integer pagePerBlock, Integer postPerPage, Long totalPostCount){
        this.pagePerBlock = pagePerBlock;
        this.postPerPage = postPerPage;
        this.totalPostCount = totalPostCount;
        this.setTotalLastPageNum();
    }

    public void setTotalLastPageNum(){
        if(totalPostCount == 0){
            this.totalLastPageNum = 1;
        }
        else if(totalPostCount > 0){
            this.totalLastPageNum = (int) (Math.ceil((double) totalPostCount/postPerPage));
        }
    }

    public Map<String, Object> getFixedBlock(Integer currentPageNum){
        return this.getBlock(currentPageNum, true);
    }

    public Map<String, Object> getElasticBlock(Integer currentPageNum){
        return this.getBlock(currentPageNum, false);
    }

    private Map<String, Object> getBlock(Integer currentPageNum, Boolean isFixed){
        if(pagePerBlock % 2 == 0 && !isFixed){
            throw new IllegalStateException("ElasticBlock 의 page-per-block 은 홀수만 가능합니다.");
        }
        if(currentPageNum > totalLastPageNum && totalPostCount != 0){
            throw new IllegalStateException("currentPage 가 총 페이지 개수("+totalLastPageNum+")보다 큽니다.");
        }

        Integer blockLastPageNum = totalLastPageNum;
        Integer blockFirstPageNum = 1;

        if(isFixed){
            Integer mod = totalLastPageNum % pagePerBlock;
            if(totalLastPageNum-mod >= currentPageNum){
                blockLastPageNum = (int) (Math.ceil((float)currentPageNum/pagePerBlock)*pagePerBlock);
                blockFirstPageNum = blockLastPageNum - (pagePerBlock-1);
            }else{
                blockFirstPageNum = (int) (Math.ceil((float)currentPageNum/pagePerBlock)*pagePerBlock)-(pagePerBlock-1);
            }
        }else{
            Integer mid = pagePerBlock/2;

            if(currentPageNum <= pagePerBlock){
                blockLastPageNum = pagePerBlock;
            }else if(currentPageNum < totalLastPageNum - mid){
                blockLastPageNum = currentPageNum + mid;
            }

            blockFirstPageNum = blockLastPageNum - (pagePerBlock-1);

            if(totalLastPageNum < pagePerBlock){
                blockLastPageNum = totalLastPageNum;
                blockFirstPageNum = 1;
            }
        }

        List<Integer> pageList = new ArrayList<>();
        for(int i = 0, val = blockFirstPageNum; val <= blockLastPageNum; i++, val++){
            pageList.add(i, val);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("isPrevExist", (int)currentPageNum > (int)pagePerBlock);
        result.put("isNextExist", blockLastPageNum != 1 ? (int)blockLastPageNum != (int)totalLastPageNum : false);
        result.put("totalLastPageNum", totalLastPageNum);
        result.put("blockLastPageNum", blockLastPageNum);
        result.put("blockFirstPageNum", blockFirstPageNum);
        result.put("currentPageNum", currentPageNum);
        result.put("totalPostCount", totalPostCount);
        result.put("pagePerBlock", pagePerBlock);
        result.put("postPerPage", postPerPage);
        result.put("pageList", pageList);
        return result;
    }
}
