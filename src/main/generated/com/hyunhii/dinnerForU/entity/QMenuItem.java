package com.hyunhii.dinnerForU.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMenuItem is a Querydsl query type for MenuItem
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMenuItem extends EntityPathBase<MenuItem> {

    private static final long serialVersionUID = 1268244682L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMenuItem menuItem = new QMenuItem("menuItem");

    public final QFood food;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMenu menu;

    public QMenuItem(String variable) {
        this(MenuItem.class, forVariable(variable), INITS);
    }

    public QMenuItem(Path<? extends MenuItem> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMenuItem(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMenuItem(PathMetadata metadata, PathInits inits) {
        this(MenuItem.class, metadata, inits);
    }

    public QMenuItem(Class<? extends MenuItem> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.food = inits.isInitialized("food") ? new QFood(forProperty("food")) : null;
        this.menu = inits.isInitialized("menu") ? new QMenu(forProperty("menu")) : null;
    }

}

