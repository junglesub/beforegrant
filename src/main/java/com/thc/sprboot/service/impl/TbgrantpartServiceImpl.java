package com.thc.sprboot.service.impl;

import com.thc.sprboot.domain.Tbgrantpart;
import com.thc.sprboot.dto.DefaultDto;
import com.thc.sprboot.dto.TbgrantpartDto;
import com.thc.sprboot.exception.NoAuthorizationException;
import com.thc.sprboot.mapper.TbgrantpartMapper;
import com.thc.sprboot.repository.TbgrantpartRepository;
import com.thc.sprboot.service.TbgrantpartService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbgrantpartServiceImpl implements TbgrantpartService {

    private final TbgrantpartRepository tbgrantpartRepository;
    private final TbgrantpartMapper tbgrantpartMapper;
    public TbgrantpartServiceImpl(
            TbgrantpartRepository tbgrantpartRepository
            , TbgrantpartMapper tbgrantpartMapper
    ) {
        this.tbgrantpartRepository = tbgrantpartRepository;
        this.tbgrantpartMapper = tbgrantpartMapper;
    }

    @Override
    public TbgrantpartDto.CreateResDto create(TbgrantpartDto.CreateServDto param){
        if(!param.isAdmin()){ throw new NoAuthorizationException("no auth"); }
        TbgrantpartDto.CreateResDto createResDto = tbgrantpartRepository.save(param.toEntity()).toCreateResDto();
        return createResDto;
    }

    @Override
    public TbgrantpartDto.CreateResDto update(TbgrantpartDto.UpdateServDto param){
        if(!param.isAdmin()){ throw new NoAuthorizationException("no auth"); }
        Tbgrantpart tbgrantpart = tbgrantpartRepository.findById(param.getId()).orElseThrow(()->new RuntimeException("no data"));

        if(param.getDeleted() != null){
            tbgrantpart.setDeleted(param.getDeleted());
        }
        if(param.getProcess() != null){
            tbgrantpart.setProcess(param.getProcess());
        }
        if(param.getTbgrantId() != null){
            tbgrantpart.setTbgrantId(param.getTbgrantId());
        }
        if(param.getTarget() != null){
            tbgrantpart.setTarget(param.getTarget());
        }
        if(param.getFunc() != null){
            tbgrantpart.setFunc(param.getFunc());
        }
        tbgrantpartRepository.save(tbgrantpart);
        return tbgrantpart.toCreateResDto();
    }
    public TbgrantpartDto.CreateResDto delete(DefaultDto.DeleteServDto param){
        TbgrantpartDto.UpdateServDto newParam = TbgrantpartDto.UpdateServDto.builder().id(param.getId()).deleted("Y").reqTbuserId(param.getReqTbuserId()).isAdmin(param.isAdmin()).build();
        return update(newParam);
    }
    public TbgrantpartDto.CreateResDto deletes(DefaultDto.DeletesServDto param){
        int count = 0;
        for(String each : param.getIds()){
            TbgrantpartDto.UpdateServDto newParam = TbgrantpartDto.UpdateServDto.builder().id(each).deleted("Y").reqTbuserId(param.getReqTbuserId()).isAdmin(param.isAdmin()).build();
            TbgrantpartDto.CreateResDto result = update(newParam);
            if(!(result.getId()).isEmpty()) {
                count++;
            }
        }
        return TbgrantpartDto.CreateResDto.builder().id(count + "").build();
    }

    @Override
    public TbgrantpartDto.DetailResDto detail(DefaultDto.DetailServDto param){
        //if(!param.isAdmin()){ throw new NoAuthorizationException("no auth"); }
        TbgrantpartDto.DetailResDto selectResDto = get(param);
        return selectResDto;
    }

    public TbgrantpartDto.DetailResDto get(DefaultDto.DetailServDto param){
        TbgrantpartDto.DetailResDto selectResDto = tbgrantpartMapper.detail(param);
        return selectResDto;
    }

    @Override
    public List<TbgrantpartDto.DetailResDto> list(TbgrantpartDto.ListServDto param){
        return detailList(tbgrantpartMapper.list(param), DefaultDto.DetailServDto.builder().reqTbuserId(param.getReqTbuserId()).isAdmin(param.isAdmin()).build());
    }
    @Override
    public DefaultDto.PagedListResDto pagedList(TbgrantpartDto.PagedListServDto param){
        int[] returnSize = param.init(tbgrantpartMapper.pagedListCount(param));
        return param.afterBuild(returnSize, detailList(tbgrantpartMapper.pagedList(param), DefaultDto.DetailServDto.builder().reqTbuserId(param.getReqTbuserId()).isAdmin(param.isAdmin()).build()));
    }
    @Override
    public List<TbgrantpartDto.DetailResDto> scrollList(TbgrantpartDto.ScrollListServDto param){
        param.init();
        return detailList(tbgrantpartMapper.scrollList(param), DefaultDto.DetailServDto.builder().reqTbuserId(param.getReqTbuserId()).isAdmin(param.isAdmin()).build());
    }
    //
    public List<TbgrantpartDto.DetailResDto> detailList(List<TbgrantpartDto.DetailResDto> list, DefaultDto.DetailServDto param){
        List<TbgrantpartDto.DetailResDto> newList = new ArrayList<>();
        for(TbgrantpartDto.DetailResDto each : list){
            newList.add(get(DefaultDto.DetailServDto.builder().id(each.getId()).reqTbuserId(param.getReqTbuserId()).isAdmin(param.isAdmin()).build()));
        }
        return newList;
    }
}
