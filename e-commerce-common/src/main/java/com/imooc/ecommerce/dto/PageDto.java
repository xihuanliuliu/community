package com.imooc.ecommerce.dto;

import com.github.pagehelper.IPage;
import com.imooc.ecommerce.util.PrincipalUtil;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

public class PageDto implements IPage {

    public static final String ASC = "ASC";
    public static final String DESC = "DESC";

    /**
     * 最大分页大小，如果分页大小大于500，则用500作为分页大小，避免太大
     */

    public static final Integer MAX_PAGE_SIZE = 500;

    /**
     * 当前页
     */
    @NotNull(message = "pageNum不能为空")
    @ApiModelProperty(value = "当前页", required = true)
    private Integer pageNum;

    /**
     * 每页大小
     **/
    @NotNull(message = "pageSize 不能为空")
    @ApiModelProperty(value = "每页大小", required = true)
    private Integer pageSize;

    /**
     * 排序字段数组，用逗号隔开
     */
    private String[] columns;

    /**
     * 排序字段方式，用逗号隔开， ASC，DESC
     */
    private String[] orders;

    @Override
    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    @Override
    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        if (pageSize > MAX_PAGE_SIZE) {
            this.pageSize = pageSize;
            return;
        }
        this.pageSize = pageSize;
    }

    @Override
    public String getOrderBy() {
        return null;
    }

    public String[] getColumns() {
        return columns;
    }

    public void setColumns(String[] columns) {
        this.columns = columns;
    }

    public String[] getOrders() {
        return orders;
    }

    public void setOrders(String[] orders) {
        this.orders = orders;
    }

    public static String order(String[] columns, String[] orders) {
        if (columns == null || columns.length == 0) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int x = 0; x < columns.length; x++) {
            String column = columns[x];
            String order;
            if (orders != null && orders.length > 0) {
                order = orders[x].toUpperCase();
                // 未采用DESC，ASC
                if (!(order.equals(ASC) || order.equals(DESC))) {
                    throw new IllegalArgumentException("非法的排序策略: " + column);
                }
            } else {
                order = ASC;
            }
            // 判断列名称的合法性，防止SQL注入。只能是【字母，数字，下划线】
            if (PrincipalUtil.isField(column)) {
                throw new IllegalArgumentException("非法的排序字段名称：" + column);
            }

            // 驼峰转换为下划线
            column = humpConversionUnderscore(column);
            if (x != 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("`").append(column).append("` ").append(order);
        }
        return stringBuilder.toString();
    }

    public static String humpConversionUnderscore(String value) {
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = value.toCharArray();
        for (char character : chars) {
            if (Character.isUpperCase(character)) {
                stringBuilder.append("_");
                character = Character.toLowerCase(character);
            }
            stringBuilder.append(character);
        }
        return stringBuilder.toString();
    }
}
