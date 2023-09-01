package com.hyunhii.dinnerForU.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrderItem is a Querydsl query type for OrderItem
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrderItem extends EntityPathBase<OrderItem> {

    private static final long serialVersionUID = 1816064713L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderItem orderItem = new QOrderItem("orderItem");

    public final StringPath address1 = createString("address1");

    public final StringPath address2 = createString("address2");

    public final StringPath addressName = createString("addressName");

    public final NumberPath<Integer> count = createNumber("count", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMenu menu;

    public final StringPath message = createString("message");

    public final QOrder order;

    public final NumberPath<Integer> orderPrice = createNumber("orderPrice", Integer.class);

    public final StringPath phone = createString("phone");

    public final StringPath receiver = createString("receiver");

    public final EnumPath<OrderStatus> status = createEnum("status", OrderStatus.class);

    public QOrderItem(String variable) {
        this(OrderItem.class, forVariable(variable), INITS);
    }

    public QOrderItem(Path<? extends OrderItem> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrderItem(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrderItem(PathMetadata metadata, PathInits inits) {
        this(OrderItem.class, metadata, inits);
    }

    public QOrderItem(Class<? extends OrderItem> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.menu = inits.isInitialized("menu") ? new QMenu(forProperty("menu")) : null;
        this.order = inits.isInitialized("order") ? new QOrder(forProperty("order"), inits.get("order")) : null;
    }

}

