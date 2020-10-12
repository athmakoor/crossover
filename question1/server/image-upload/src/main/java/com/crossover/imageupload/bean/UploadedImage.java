package com.crossover.imageupload.bean;

public class UploadedImage {
    private Integer id;

    private String description;

    private String imageUrl;

    private String fileType;

    private Integer fileSize;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public UploadedImage() {
        super();
    }

    public UploadedImage(String description, String fileType, Integer fileSize) {
        this.description = description;
        this.fileType = fileType;
        this.fileSize = fileSize;
    }

    @Override
    public String toString() {
        return "UploadedImage{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", fileType='" + fileType + '\'' +
                ", fileSize='" + fileSize + '\'' +
                '}';
    }
}
