package cn.actadd.domain;

import java.util.List;

/**
 * @ClassName PageBean
 * @Description 分页对象
 * @Author Actadd
 * @Date 10:29 2019/11/29
 * @Version 1.0
 */
public class PageBean<T> {

    //总记录数
    private int totalCount;
    //总页码
    private int totalPage;
    //每页的数据
    private List<T> list;
    //当前页码
    private int currentPage;
    //每页显示的记录数
    private int rows;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", list=" + list +
                ", currentPage=" + currentPage +
                ", rows=" + rows +
                '}';
    }
}
