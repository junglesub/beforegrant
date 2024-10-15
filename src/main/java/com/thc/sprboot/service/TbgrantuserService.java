package com.thc.sprboot.service;


import com.thc.sprboot.dto.DefaultDto;
import com.thc.sprboot.dto.TbgrantuserDto;

import java.util.List;

public interface TbgrantuserService {
    /**/
    TbgrantuserDto.CreateResDto create(TbgrantuserDto.CreateServDto param);
    TbgrantuserDto.CreateResDto update(TbgrantuserDto.UpdateServDto param);
    TbgrantuserDto.CreateResDto delete(DefaultDto.DeleteServDto param);
    TbgrantuserDto.CreateResDto deletes(DefaultDto.DeletesServDto param);
    TbgrantuserDto.DetailResDto detail(DefaultDto.DetailServDto param);
    List<TbgrantuserDto.DetailResDto> list(TbgrantuserDto.ListServDto param);
    DefaultDto.PagedListResDto pagedList(TbgrantuserDto.PagedListServDto param);
    List<TbgrantuserDto.DetailResDto> scrollList(TbgrantuserDto.ScrollListServDto param);
}
