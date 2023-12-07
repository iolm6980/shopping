package com.example.shoping.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
abstract class BaseEntity {

    @Column(name = "regdate", updatable = false)
    @CreatedDate
    LocalDateTime regDate;

    @Column(name = "moddate")
    @LastModifiedDate
    LocalDateTime modDate;
}
