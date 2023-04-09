package com.project.rest.service;

import com.project.rest.model.Projekt;
import com.project.rest.model.Zadanie;
import com.project.rest.repository.ProjektRepository;
import com.project.rest.repository.ZadaniaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjektServiceImpl implements ProjektService{
    private ProjektRepository projektRepository;
    private ZadaniaRepository zadanieRepository;

    @Autowired
    public ProjektServiceImpl(ProjektRepository projektRepository, ZadaniaRepository zadanieRepository){
        this.projektRepository = projektRepository;
        this.zadanieRepository = zadanieRepository;
    }

    @Override
    public Optional<Projekt> getProjekt(Integer projektId){
        return projektRepository.findById(projektId);
    }

    @Override
    public Projekt setProjekt(Projekt projekt){
        return null;
    }

    @Override
    public void deleteProjekt(Integer projektId){
        for(Zadanie zadanie : zadanieRepository.findZadaniaProjektu(projektId)){
            zadanieRepository.delete((zadanie));
        }aaa
        projektRepository.deleteById(projektId);
    }

    @Override
    public Page<Projekt> getProjekty(Pageable pageable){
        return null;
    }

    @Override
    public Page<Projekt> searchByNazwa(String nazwa, Pageable pageable){
        return null;
    }
}
