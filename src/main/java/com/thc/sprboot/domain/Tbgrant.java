package com.thc.sprboot.domain;

import com.thc.sprboot.dto.TbgrantDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Table(indexes = {
        @Index(columnList = "deleted")
        ,@Index(columnList = "process")
        ,@Index(columnList = "createdAt")
        ,@Index(columnList = "modifiedAt")
})
@Entity
public class Tbgrant extends AuditingFields {

    @Setter @Column(nullable = false) private String cate;
    @Setter @Column(nullable = false, length=400) private String title;
    @Setter @Column(nullable = true, length=10000) @Lob private String content; // 본문

    protected Tbgrant(){}
    private Tbgrant(String cate, String title, String content) {
        this.cate = cate;
        this.title = title;
        this.content = content;
    }
    public static Tbgrant of(String cate, String title, String content) {
        return new Tbgrant(cate, title, content);
    }

    public TbgrantDto.CreateResDto toCreateResDto() {
        return TbgrantDto.CreateResDto.builder().id(this.getId()).build();
    }
}
