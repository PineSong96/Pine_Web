package com.pine.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 分页对应的实体类
 */
@JsonAutoDetect
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@ApiModel(value = "分页对象", description = "分页对象")
public class Page {

    private static int DEFAULT_PAGE_SIZE = 20;

    /**
     * 总条数
     */
    @ApiModelProperty(value = "总条数", name = "totalNumber")
    private int totalNumber;

    /**
     * 当前第几页
     */
    @ApiModelProperty(value = "当前第几页", name = "currentPage")
    private int currentPage;

    /**
     * 总页数
     */
    @ApiModelProperty(value = "总页数", name = "totalPage")
    private int totalPage;

    /**
     * 每页显示条数
     */
    @ApiModelProperty(value = "每页显示条数", name = "pageNumber")
    private int pageNumber = DEFAULT_PAGE_SIZE;

    /**
     * 数据库中limit的参数，从第几条开始取
     */
    @ApiModelProperty(value = "数据库中limit的参数，从第几条开始取", name = "dbIndex")
    private int dbIndex;

    /**
     * 数据库中limit的参数，一共取多少条
     */
    @ApiModelProperty(value = "数据库中limit的参数，一共取多少条", name = "dbNumber")
    private int dbNumber;


    /**
     * 根据当前对象中属性值计算并设置相关属性值
     */
    public void count() {

        // 计算总页数
        int totalPageTemp = this.totalNumber / this.pageNumber;
        int plus = (this.totalNumber % this.pageNumber) == 0 ? 0 : 1;
        totalPageTemp = totalPageTemp + plus;
        if (totalPageTemp <= 0) {
            totalPageTemp = 1;
        }
        this.totalPage = totalPageTemp;

        // 设置当前页数
        // 当前页数小于1设置为1
        if (this.currentPage < 1) {
            this.currentPage = 1;
        }

        // 设置limit的参数
        this.dbIndex = (this.currentPage - 1) * this.pageNumber;
        this.dbNumber = this.pageNumber;
    }


    public int getTotalNumber() {

        return totalNumber;
    }


    public void setTotalNumber(int totalNumber) {

        this.totalNumber = totalNumber;
        //记录总条数时进行操作
        this.count();
    }


    public int getCurrentPage() {

        return currentPage;
    }


    public void setCurrentPage(int currentPage) {

        this.currentPage = currentPage;
    }


    public int getTotalPage() {

        return totalPage;
    }


    public void setTotalPage(int totalPage) {

        this.totalPage = totalPage;
    }


    public int getPageNumber() {

        return pageNumber;
    }


    public void setPageNumber(int pageNumber) {

        this.pageNumber = pageNumber;
    }


    public int getDbIndex() {

        return dbIndex;
    }


    public void setDbIndex(int dbIndex) {

        this.dbIndex = dbIndex;
    }


    public int getDbNumber() {

        return dbNumber;
    }


    public void setDbNumber(int dbNumber) {

        this.dbNumber = dbNumber;
    }
}
