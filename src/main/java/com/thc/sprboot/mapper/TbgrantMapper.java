package com.thc.sprboot.mapper;

import com.thc.sprboot.dto.DefaultDto;
import com.thc.sprboot.dto.TbgrantDto;

import java.util.List;

public interface TbgrantMapper {
    int exist(TbgrantDto.ExistServDto param);
    /**/
    TbgrantDto.DetailResDto detail(DefaultDto.DetailServDto param);
    List<TbgrantDto.DetailResDto> list(TbgrantDto.ListServDto param);

    List<TbgrantDto.DetailResDto> scrollList(TbgrantDto.ScrollListServDto param);
    List<TbgrantDto.DetailResDto> pagedList(TbgrantDto.PagedListServDto param);
    int pagedListCount(TbgrantDto.PagedListServDto param);
}
