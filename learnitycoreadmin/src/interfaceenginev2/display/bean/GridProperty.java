package interfaceenginev2.display.bean;

import comv2.aunwesha.lfutil.GenericUtil;

public class GridProperty {
	private Boolean altRows;
	private Boolean autowidth;
	private Boolean shrinkToFit;
	private Boolean ignoreCase;
	private Boolean rowNumbers;
	private Boolean searchOnEnter;
	private Boolean columnChooser;
	private Boolean toolbarSearch;

	private String altClass;

	public GridProperty(Boolean altRows, Boolean autowidth, Boolean shrinkToFit, Boolean ignoreCase, Boolean rowNumbers, Boolean searchOnEnter, String altClass,
			Boolean columnChooser, Boolean toolbarSearch) {
		super();
		this.altRows = altRows;
		this.autowidth = autowidth;
		this.shrinkToFit = shrinkToFit;
		this.ignoreCase = ignoreCase;
		this.rowNumbers = rowNumbers;
		this.altClass = altClass;
		this.searchOnEnter = searchOnEnter;
		this.columnChooser = columnChooser;
		this.toolbarSearch = toolbarSearch;
	}

	public GridProperty() {

	}

	public boolean isDataExist() {
		if (this.altRows != null) {
			return true;
		} else if (this.autowidth != null) {
			return true;
		} else if (this.shrinkToFit != null) {
			return true;
		} else if (this.ignoreCase != null) {
			return true;
		} else if (this.rowNumbers != null) {
			return true;
		} else if (GenericUtil.hasString(this.altClass)) {
			return true;
		} else if (this.searchOnEnter != null) {
			return true;
		} else if (this.columnChooser != null) {
			return true;
		} else if (this.toolbarSearch != null) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean getAltRows() {
		return altRows;
	}

	public void setAltRows(Boolean altRows) {
		this.altRows = altRows;
	}

	public Boolean getAutowidth() {
		return autowidth;
	}

	public void setAutowidth(Boolean autowidth) {
		this.autowidth = autowidth;
	}

	public Boolean getShrinkToFit() {
		return shrinkToFit;
	}

	public void setShrinkToFit(Boolean shrinkToFit) {
		this.shrinkToFit = shrinkToFit;
	}

	public Boolean getIgnoreCase() {
		return ignoreCase;
	}

	public void setIgnoreCase(Boolean ignoreCase) {
		this.ignoreCase = ignoreCase;
	}

	public Boolean getRowNumbers() {
		return rowNumbers;
	}

	public void setRowNumbers(Boolean rowNumbers) {
		this.rowNumbers = rowNumbers;
	}

	public Boolean getSearchOnEnter() {
		return searchOnEnter;
	}

	public void setSearchOnEnter(Boolean searchOnEnter) {
		this.searchOnEnter = searchOnEnter;
	}

	public String getAltClass() {
		return altClass;
	}

	public Boolean getToolbarSearch() {
		return toolbarSearch;
	}

	public void setToolbarSearch(Boolean toolbarSearch) {
		this.toolbarSearch = toolbarSearch;
	}

	public void setAltClass(String altClass) {
		this.altClass = altClass;
	}

	public Boolean getColumnChooser() {
		return columnChooser;
	}

	public void setColumnChooser(Boolean columnChooser) {
		this.columnChooser = columnChooser;
	}

}
