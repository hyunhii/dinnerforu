package com.hyunhii.dinnerForU.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFood is a Querydsl query type for Food
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFood extends EntityPathBase<Food> {

    private static final long serialVersionUID = 1172327446L;

    public static final QFood food = new QFood("food");

    public final StringPath foodName = createString("foodName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imgSrc = createString("imgSrc");

    public final EnumPath<FoodType> type = createEnum("type", FoodType.class);

    public final StringPath useYN = createString("useYN");

    public QFood(String variable) {
        super(Food.class, forVariable(variable));
    }

    public QFood(Path<? extends Food> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFood(PathMetadata metadata) {
        super(Food.class, metadata);
    }

}

