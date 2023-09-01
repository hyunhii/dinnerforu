package com.hyunhii.dinnerForU.controller.admin.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class NoticeForm {

    private Long noticeId;

    private String title;
    private String content;

    private String createdBy;
    private LocalDateTime createdDate;

    private String modifiedBy;
    private LocalDateTime modifiedDate;

    public NoticeForm(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public NoticeForm(String title, String content, String createdBy, LocalDateTime createdDate, String modifiedBy, LocalDateTime modifiedDate) {
        this.title = title;
        this.content = content;

        this.createdBy = createdBy;
        this.createdDate = createdDate;

        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifiedDate;
    }

    public NoticeForm(Long noticeId, String title, String content, String createdBy, LocalDateTime createdDate, String modifiedBy, LocalDateTime modifiedDate) {
        this.noticeId = noticeId;
        this.title = title;
        this.content = content;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifiedDate;
    }
}
