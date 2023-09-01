package com.hyunhii.dinnerForU.repositroy;

import com.hyunhii.dinnerForU.dto.NoticeDto;
import com.hyunhii.dinnerForU.dto.QNoticeDto;
import com.hyunhii.dinnerForU.entity.QNotice;
import com.hyunhii.dinnerForU.entity.QUser;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;

import java.util.List;

import static com.hyunhii.dinnerForU.entity.QNotice.notice;
import static com.hyunhii.dinnerForU.entity.QUser.user;

public class NoticeRepositoryImpl implements NoticeRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public NoticeRepositoryImpl (EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<NoticeDto> search(Pageable pageable) {

        QUser modifiedUser = new QUser("modifiedUser");

        QueryResults<NoticeDto> result = queryFactory
                .select(new QNoticeDto(
                        notice.id.as("noticeId"),
                        notice.title,
                        notice.content,
                        notice.createdBy,
                        user.userName.as("createdByName"),
                        notice.createdDate,
                        notice.lastModifiedBy,
                        modifiedUser.userName.as("lastModifiedByName"),
                        notice.lastModifiedDate
                )).from(notice)
                .leftJoin(user)
                .on(notice.createdBy.eq(user.id))
                .leftJoin(modifiedUser)
                .on(notice.lastModifiedBy.eq(modifiedUser.id))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(notice.createdDate.desc())
                .fetchResults();

        List<NoticeDto> content = result.getResults();

        long total = result.getTotal();

        return new PageImpl<>(content, pageable, total);
    }
}
