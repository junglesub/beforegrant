package com.thc.sprboot.service;


import com.thc.sprboot.dto.DefaultDto;
import com.thc.sprboot.dto.TbgrantDto;

import java.util.List;

public interface TbgrantService {
    TbgrantDto.CreateResDto create(TbgrantDto.CreateServDto param);
    TbgrantDto.CreateResDto update(TbgrantDto.UpdateServDto param);
    TbgrantDto.CreateResDto delete(DefaultDto.DeleteServDto param);
    TbgrantDto.CreateResDto deletes(DefaultDto.DeletesServDto param);
    TbgrantDto.DetailResDto detail(DefaultDto.DetailServDto param);
    List<TbgrantDto.DetailResDto> list(TbgrantDto.ListServDto param);
    DefaultDto.PagedListResDto pagedList(TbgrantDto.PagedListServDto param);
    List<TbgrantDto.DetailResDto> scrollList(TbgrantDto.ScrollListServDto param);
}
