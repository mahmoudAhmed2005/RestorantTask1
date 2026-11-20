package com.restorant_springdemo.restorant.service.impl;

import com.restorant_springdemo.restorant.dto.ChefDto;
import com.restorant_springdemo.restorant.dto.ProductDto;
import com.restorant_springdemo.restorant.mapper.ChefMapper;
import com.restorant_springdemo.restorant.model.Chef;
import com.restorant_springdemo.restorant.model.Product;
import com.restorant_springdemo.restorant.repo.ChefRepo;
import com.restorant_springdemo.restorant.service.ChefService;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ChefServiceImpl implements ChefService {

    private ChefMapper chefMapper;
    private ChefRepo chefRepo;

    @Autowired
    public ChefServiceImpl(ChefMapper chefMapper, ChefRepo chefRepo) {
        this.chefMapper = chefMapper;
        this.chefRepo = chefRepo;
    }

    @Override
    public List<ChefDto> getAllChef() {
        return chefMapper.toDtoList(chefRepo.findAll());
    }

    @Override
    public ChefDto getChefById(Long id) throws SystemException {
       if (Objects.isNull(id)){
           throw new SystemException("chef.id.notNull");
       }

       Chef chef = chefRepo.findById(id).orElseThrow(()->new SystemException("chef.notFound"));

       return toDto(chef);
    }

    @Override
    public ChefDto saveChef(ChefDto chefDto) throws SystemException {
      if (Objects.nonNull(chefDto.getId())){
          throw new SystemException( "chef.id.mustBeNull");
      }
      Chef chef = chefMapper.toEntity(chefDto);

      return toDto(chefRepo.save(chef));
    }

    @Override
    public ChefDto updateChef(ChefDto chefDto) throws SystemException {
       if (Objects.isNull(chefDto.getId())){
           throw new SystemException("chef.id.notNull");
       }

       if (!chefRepo.existsById(chefDto.getId())){
           throw  new SystemException("chef.notFound");
       }

       Chef chef = chefMapper.toEntity(chefDto);
       Chef updatedChef =  chefRepo.save(chef);
       return toDto(updatedChef);
    }

    @Override
    public void deleteChefById(Long id) throws SystemException {

        if (Objects.isNull(id)){
            throw new SystemException("chef.id.notNull");
        }

        Chef chef = chefRepo.findById(id).orElseThrow(()->new SystemException("chef.notFound"));

        chefRepo.delete(chef);
    }


    public ChefDto toDto(Chef chef) {
        return chefMapper.toDto(chef);

    }
}
