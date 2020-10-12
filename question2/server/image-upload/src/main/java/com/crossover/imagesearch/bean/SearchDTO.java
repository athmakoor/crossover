package com.crossover.imagesearch.bean;

public class SearchDTO {
    private String description;
    private String fileType;
    private Integer minFileSize;
    private Integer maxFileSize;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Integer getMinFileSize() {
        return minFileSize;
    }

    public void setMinFileSize(Integer minFileSize) {
        this.minFileSize = minFileSize;
    }

    public Integer getMaxFileSize() {
        return maxFileSize;
    }

    public void setMaxFileSize(Integer maxFileSize) {
        this.maxFileSize = maxFileSize;
    }
}
