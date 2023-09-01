package com.hyunhii.dinnerForU.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class NoticeDto {

    private Long noticeId;

    private String title;
    private String original_content;
    private String brContent;

    private String createdBy;
    private String createdByName;
    private LocalDateTime createdDate;

    private String modifiedBy;
    private String modifiedByName;
    private LocalDateTime modifiedDate;

    @QueryProjection
    public NoticeDto(Long noticeId, String title, String content, String createdBy, LocalDateTime createdDate, String modifiedBy, LocalDateTime modifiedDate) {
        this.noticeId = noticeId;
        this.title = title;
        this.original_content = content;
        this.brContent = content.replace("\r\n", "<br/>");
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifiedDate;
    }

    @QueryProjection
    public NoticeDto(Long noticeId, String title, String content, String createdBy, String createdByName, LocalDateTime createdDate, String modifiedBy, String modifiedByName, LocalDateTime modifiedDate) {
        this.noticeId = noticeId;
        this.title = title;
        this.original_content = content;
        this.brContent = content.replace("\r\n", "<br/>");
        this.createdBy = createdBy;
        this.createdByName = createdByName;
        this.createdDate = createdDate;
        this.modifiedBy = modifiedBy;
        this.modifiedByName = modifiedByName;
        this.modifiedDate = modifiedDate;
    }

    @QueryProjection
    public NoticeDto(Long noticeId, String title, String content, String createdBy, String createdByName, LocalDateTime createdDate, String modifiedBy, LocalDateTime modifiedDate) {
        this.noticeId = noticeId;
        this.title = title;
        this.original_content = content;
        this.brContent = content.replace("\r\n", "<br/>");
        this.createdBy = createdBy;
        this.createdByName = createdByName;
        this.createdDate = createdDate;
        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifiedDate;
    }
}
