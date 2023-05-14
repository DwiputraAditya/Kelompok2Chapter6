package com.binar.challenge4.service;

import com.binar.challenge4.model.Film;
import com.binar.challenge4.repository.FilmRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class FilmService {
    @Autowired
    private FilmRepository filmRepository;

    public Film addFilm(Film film) {
        log.info("Add Data Film Success");
        return filmRepository.save(film);
    }

    public List<Film> getAllFilm(){
        log.info("Get All Data Film Success");
        return filmRepository.findAll();
    }

    public Page<Film> getAllFilmPagination(int page, int size) {
        PageRequest request = PageRequest.of(page, size);
        Page<Film> result = filmRepository.findAll(request);
        log.info("Get All Data User Pagination Success");
        return result;
    }

    public Optional<Film> getFilmById(Long id) {
        log.info("Get Data Film By Id Success");
        return filmRepository.findById(id);
    }

    public List<Film> getScheduleByFilmIsPremiered(Boolean isPremiered){
        log.info("Get Data Film By Is Premiered Success");
        return filmRepository.findFilmByIsPremiered(isPremiered);
    }

    public Film updateFilm(Long id, Film film) {
        Film film1 = filmRepository.findById(id).get();
        film1.setFilmName(film.getFilmName());
        film1.setIsPremiered(film.getIsPremiered());
        log.info("Update Data Film By Id Success");
        return filmRepository.save(film1);
    }
    public void deleteFilmById(Long id) {
        log.info("Delete Data Film By Id Success");
        filmRepository.deleteById(id);
    }

}
