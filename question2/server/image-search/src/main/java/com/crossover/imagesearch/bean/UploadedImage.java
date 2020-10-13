package com.crossover.imagesearch.bean;

public class UploadedImage {
    private Integer id;

    private String description;

    private String imageUrl;

    private String fileType;

    private Integer fileSize;

    public UploadedImage(ESImageData source) {
        this.id = source.getId();
        this.description = source.getDescription();
        this.imageUrl = source.getImage_url();
        this.fileSize = source.getFile_size();
        this.fileType = source.getFile_type();
    }

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
