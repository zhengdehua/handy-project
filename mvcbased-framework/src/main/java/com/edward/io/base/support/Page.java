package com.edward.io.base.support;

import com.edward.io.base.util.StringUtils;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @author zhengdehua
 */
public class Page<T> implements Serializable {

	private static final long serialVersionUID = 7170934768062308645L;

	public static final String ASC = "asc";
	public static final String DESC = "desc";
	public static int DEFAULT_PAGE_SIZE = 10;
	protected int currentPageNo = 1;
	protected int pageSize = DEFAULT_PAGE_SIZE;
	protected List<T> result = Collections.emptyList();
	protected long totalCount = -1L;
	protected boolean autoCount = true;
	protected String formName;
	protected String orderBy;
	protected String order;

	public Page(int pageSize) {
		setPageSize(pageSize);
	}

	public Page(int pageSize, boolean autoCount) {
		setPageSize(pageSize);
		setAutoCount(autoCount);
	}

	public Page() {
		this(DEFAULT_PAGE_SIZE);
	}

	public long getTotalPageCount() {
		Assert.isTrue(this.pageSize > 0);
		if (this.totalCount % this.pageSize == 0)
			return (this.totalCount / this.pageSize);

		return (this.totalCount / this.pageSize + 1);
	}

	public boolean isOrderBySetted() {
		return ((StringUtils.isNotBlank(this.orderBy)) && (StringUtils
				.isNotBlank(this.order)));
	}

	public int getFirstOfPage() {
		return ((this.currentPageNo - 1) * this.pageSize + 1);
	}

	public int getLastOfPage() {
		return (this.currentPageNo * this.pageSize);
	}

	public int getCurrentPageNo() {
		return this.currentPageNo;
	}

	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getResult() {
		return this.result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public long getTotalCount() {
		return this.totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public boolean isAutoCount() {
		return this.autoCount;
	}

	public void setAutoCount(boolean autoCount) {
		this.autoCount = autoCount;
	}

	public String getOrderBy() {
		return this.orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getOrder() {
		return this.order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getFormName() {
		return this.formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

}