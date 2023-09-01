package com.hyunhii.dinnerForU.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.hyunhii.dinnerForU.dto.QNoticeDto is a Querydsl Projection type for NoticeDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QNoticeDto extends ConstructorExpression<NoticeDto> {

    private static final long serialVersionUID = -95719515L;

    public QNoticeDto(com.querydsl.core.types.Expression<Long> noticeId, com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<String> content, com.querydsl.core.types.Expression<String> createdBy, com.querydsl.core.types.Expression<java.time.LocalDateTime> createdDate, com.querydsl.core.types.Expression<String> modifiedBy, com.querydsl.core.types.Expression<java.time.LocalDateTime> modifiedDate) {
        super(NoticeDto.class, new Class<?>[]{long.class, String.class, String.class, String.class, java.time.LocalDateTime.class, String.class, java.time.LocalDateTime.class}, noticeId, title, content, createdBy, createdDate, modifiedBy, modifiedDate);
    }

    public QNoticeDto(com.querydsl.core.types.Expression<Long> noticeId, com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<String> content, com.querydsl.core.types.Expression<String> createdBy, com.querydsl.core.types.Expression<String> createdByName, com.querydsl.core.types.Expression<java.time.LocalDateTime> createdDate, com.querydsl.core.types.Expression<String> modifiedBy, com.querydsl.core.types.Expression<String> modifiedByName, com.querydsl.core.types.Expression<java.time.LocalDateTime> modifiedDate) {
        super(NoticeDto.class, new Class<?>[]{long.class, String.class, String.class, String.class, String.class, java.time.LocalDateTime.class, String.class, String.class, java.time.LocalDateTime.class}, noticeId, title, content, createdBy, createdByName, createdDate, modifiedBy, modifiedByName, modifiedDate);
    }

    public QNoticeDto(com.querydsl.core.types.Expression<Long> noticeId, com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<String> content, com.querydsl.core.types.Expression<String> createdBy, com.querydsl.core.types.Expression<String> createdByName, com.querydsl.core.types.Expression<java.time.LocalDateTime> createdDate, com.querydsl.core.types.Expression<String> modifiedBy, com.querydsl.core.types.Expression<java.time.LocalDateTime> modifiedDate) {
        super(NoticeDto.class, new Class<?>[]{long.class, String.class, String.class, String.class, String.class, java.time.LocalDateTime.class, String.class, java.time.LocalDateTime.class}, noticeId, title, content, createdBy, createdByName, createdDate, modifiedBy, modifiedDate);
    }

}

