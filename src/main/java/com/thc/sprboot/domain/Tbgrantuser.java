package com.thc.sprboot.domain;

import com.thc.sprboot.dto.TbgrantuserDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Table(indexes = {
        @Index(columnList = "deleted")
        ,@Index(columnList = "process")
        ,@Index(columnList = "createdAt")
        ,@Index(columnList = "modifiedAt")
        }
        ,uniqueConstraints= {
        @UniqueConstraint(name = "UQ_tbgrantuser_tbgrantId_tbuserId", columnNames = {"tbgrantId", "tbuserId"})
}
)
@Entity
public class Tbgrantuser extends AuditingFields {

    @Setter @Column(nullable = false) private String tbgrantId;
    @Setter @Column(nullable = false) private String tbuserId;

    protected Tbgrantuser(){}
    private Tbgrantuser(String tbgrantId, String tbuserId) {
        this.tbgrantId = tbgrantId;
        this.tbuserId = tbuserId;
    }
    public static Tbgrantuser of(String tbgrantId, String tbuserId) {
        return new Tbgrantuser(tbgrantId, tbuserId);
    }

    public TbgrantuserDto.CreateResDto toCreateResDto() {
        return TbgrantuserDto.CreateResDto.builder().id(this.getId()).build();
    }
}
