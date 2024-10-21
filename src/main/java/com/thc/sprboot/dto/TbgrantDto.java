package com.thc.sprboot.dto;

import com.thc.sprboot.domain.Tbgrant;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

public class TbgrantDto {

    public static String[][] arrayTarget = {
            {"tbgrant", "권한"}
            ,{"tbuser", "사용자"}
            ,{"tbfaq", "FAQ"}
            ,{"tbnotice", "공지사항"}
    };

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ExistServDto extends DefaultDto.BaseDto{
        private String reqTbuserId;
        private boolean isAdmin;

        private String tbuserId;
        private String target;
        private String func;
    }

    /**/

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateReqDto extends DefaultDto.BaseDto {
        @Schema(description = "cate", example="카테고리")
        @NotNull
        @NotEmpty
        private String cate;
        @Schema(description = "title", example="")
        @NotNull
        @NotEmpty
        @Size(max=400)
        private String title;
        @Schema(description = "content", example="")
        @Size(max=4000)
        private String content;
    }
    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateServDto extends CreateReqDto{
        private String reqTbuserId;
        private boolean isAdmin;

        public Tbgrant toEntity(){
            return Tbgrant.of(getCate(), getTitle(), getContent());
        }
    }
    @Builder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateResDto{
        private String id;
    }
    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateReqDto extends DefaultDto.UpdateReqDto{
        @Schema(description = "cate", example="")
        private String cate;
        @Schema(description = "title", example="")
        @Size(max=400)
        private String title;
        @Schema(description = "content", example="")
        @Size(max=4000)
        private String content;
    }

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateServDto extends UpdateReqDto{
        private String reqTbuserId;
        private boolean isAdmin;
    }

    //여기는 빌더 붙이면 에러 나요!! 조심!!
    @Schema
    @Getter
    @Setter
    public static class DetailResDto extends DefaultDto.DetailResDto{
        @Schema(description = "cate", example="")
        private String cate;
        @Schema(description = "title", example="")
        private String title;
        @Schema(description = "content", example="")
        private String content;

        private List<TbgrantpartDto.DetailResDto> tbgrantparts;
        private List<TbgrantuserDto.DetailResDto> tbgrantusers;

        private String[][] target = arrayTarget;
    }

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ListReqDto extends DefaultDto.ListReqDto{
        @Schema(description = "cate", example="")
        private String cate;
        @Schema(description = "title", example="")
        private String title;
    }
    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ListServDto extends ListReqDto{
        private String reqTbuserId;
        private boolean isAdmin;
    }

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PagedListReqDto extends DefaultDto.PagedListReqDto{
        @Schema(description = "cate", example="")
        private String cate;
        @Schema(description = "title", example="")
        private String title;
    }
    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PagedListServDto extends DefaultDto.PagedListServDto{
        private String reqTbuserId;
        private boolean isAdmin;

        @Schema(description = "cate", example="")
        private String cate;
        @Schema(description = "title", example="")
        private String title;
    }

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ScrollListReqDto extends DefaultDto.ScrollListReqDto{
        @Schema(description = "cate", example="")
        private String cate;
        @Schema(description = "title", example="")
        private String title;
    }
    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ScrollListServDto extends ScrollListReqDto{
        private String reqTbuserId;
        private boolean isAdmin;
    }

}
