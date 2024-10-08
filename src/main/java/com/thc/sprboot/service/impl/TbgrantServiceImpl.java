package com.thc.sprboot.service.impl;

import com.thc.sprboot.domain.Tbgrant;
import com.thc.sprboot.dto.DefaultDto;
import com.thc.sprboot.dto.TbgrantDto;
import com.thc.sprboot.dto.TbgrantpartDto;
import com.thc.sprboot.exception.NoAuthorizationException;
import com.thc.sprboot.mapper.TbgrantMapper;
import com.thc.sprboot.mapper.TbgrantpartMapper;
import com.thc.sprboot.repository.TbgrantRepository;
import com.thc.sprboot.service.TbgrantService;
import com.thc.sprboot.service.TbgrantpartService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbgrantServiceImpl implements TbgrantService {

    private final TbgrantRepository tbgrantRepository;
    private final TbgrantMapper tbgrantMapper;
    private final TbgrantpartService tbgrantpartService;
    public TbgrantServiceImpl(
            TbgrantRepository tbgrantRepository
            , TbgrantMapper tbgrantMapper
            , TbgrantpartService tbgrantpartService
    ) {
        this.tbgrantRepository = tbgrantRepository;
        this.tbgrantMapper = tbgrantMapper;
        this.tbgrantpartService = tbgrantpartService;
    }

    @Override
    public TbgrantDto.CreateResDto create(TbgrantDto.CreateServDto param){
        if(!param.isAdmin()){ throw new NoAuthorizationException("no auth"); }
        TbgrantDto.CreateResDto createResDto = tbgrantRepository.save(param.toEntity()).toCreateResDto();
        return createResDto;
    }

    @Override
    public TbgrantDto.CreateResDto update(TbgrantDto.UpdateServDto param){
        if(!param.isAdmin()){ throw new NoAuthorizationException("no auth"); }
        Tbgrant tbgrant = tbgrantRepository.findById(param.getId()).orElseThrow(()->new RuntimeException("no data"));

        if(param.getDeleted() != null){
            tbgrant.setDeleted(param.getDeleted());
        }
        if(param.getProcess() != null){
            tbgrant.setProcess(param.getProcess());
        }
        if(param.getCate() != null){
            tbgrant.setCate(param.getCate());
        }
        if(param.getTitle() != null){
            tbgrant.setTitle(param.getTitle());
        }
        if(param.getContent() != null){
            tbgrant.setContent(param.getContent());
        }
        tbgrantRepository.save(tbgrant);
        return tbgrant.toCreateResDto();
    }
    public TbgrantDto.CreateResDto delete(DefaultDto.DeleteServDto param){
        TbgrantDto.UpdateServDto newParam = TbgrantDto.UpdateServDto.builder().id(param.getId()).deleted("Y").reqTbuserId(param.getReqTbuserId()).isAdmin(param.isAdmin()).build();
        return update(newParam);
    }
    public TbgrantDto.CreateResDto deletes(DefaultDto.DeletesServDto param){
        int count = 0;
        for(String each : param.getIds()){
            TbgrantDto.UpdateServDto newParam = TbgrantDto.UpdateServDto.builder().id(each).deleted("Y").reqTbuserId(param.getReqTbuserId()).isAdmin(param.isAdmin()).build();
            TbgrantDto.CreateResDto result = update(newParam);
            if(!(result.getId()).isEmpty()) {
                count++;
            }
        }
        return TbgrantDto.CreateResDto.builder().id(count + "").build();
    }

    @Override
    public TbgrantDto.DetailResDto detail(DefaultDto.DetailServDto param){
        //if(!param.isAdmin()){ throw new NoAuthorizationException("no auth"); }
        TbgrantDto.DetailResDto selectResDto = get(param);
        if(selectResDto == null){ throw new RuntimeException("no data"); }
        List<TbgrantpartDto.DetailResDto> tbgrantparts = tbgrantpartService.list(TbgrantpartDto.ListServDto.builder().tbgrantId(selectResDto.getId()).deleted("N").isAdmin(true).build());
        selectResDto.setTbgrantparts(tbgrantparts);
        return selectResDto;
    }

    public TbgrantDto.DetailResDto get(DefaultDto.DetailServDto param){
        TbgrantDto.DetailResDto selectResDto = tbgrantMapper.detail(param);
        return selectResDto;
    }

    @Override
    public List<TbgrantDto.DetailResDto> list(TbgrantDto.ListServDto param){
        return detailList(tbgrantMapper.list(param), DefaultDto.DetailServDto.builder().reqTbuserId(param.getReqTbuserId()).isAdmin(param.isAdmin()).build());
    }
    @Override
    public DefaultDto.PagedListResDto pagedList(TbgrantDto.PagedListServDto param){
        int[] returnSize = param.init(tbgrantMapper.pagedListCount(param));
        return param.afterBuild(returnSize, detailList(tbgrantMapper.pagedList(param), DefaultDto.DetailServDto.builder().reqTbuserId(param.getReqTbuserId()).isAdmin(param.isAdmin()).build()));
    }
    @Override
    public List<TbgrantDto.DetailResDto> scrollList(TbgrantDto.ScrollListServDto param){
        param.init();
        return detailList(tbgrantMapper.scrollList(param), DefaultDto.DetailServDto.builder().reqTbuserId(param.getReqTbuserId()).isAdmin(param.isAdmin()).build());
    }
    //
    public List<TbgrantDto.DetailResDto> detailList(List<TbgrantDto.DetailResDto> list, DefaultDto.DetailServDto param){
        List<TbgrantDto.DetailResDto> newList = new ArrayList<>();
        for(TbgrantDto.DetailResDto each : list){
            newList.add(get(DefaultDto.DetailServDto.builder().id(each.getId()).reqTbuserId(param.getReqTbuserId()).isAdmin(param.isAdmin()).build()));
        }
        return newList;
    }
}
