package com.thc.sprboot.domain;

import com.thc.sprboot.dto.TbgrantpartDto;
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
public class Tbgrantpart extends AuditingFields {

    @Setter @Column(nullable = false) private String tbgrantId;
    @Setter @Column(nullable = false) private String target; // 해당 테이블명 tbgrantpart, tbfaq
    @Setter @Column(nullable = false) private String func; // CRUD create, update,

    protected Tbgrantpart(){}
    private Tbgrantpart(String tbgrantId, String target, String func) {
        this.tbgrantId = tbgrantId;
        this.target = target;
        this.func = func;
    }
    public static Tbgrantpart of(String tbgrantId, String target, String func) {
        return new Tbgrantpart(tbgrantId, target, func);
    }

    public TbgrantpartDto.CreateResDto toCreateResDto() {
        return TbgrantpartDto.CreateResDto.builder().id(this.getId()).build();
    }
}
