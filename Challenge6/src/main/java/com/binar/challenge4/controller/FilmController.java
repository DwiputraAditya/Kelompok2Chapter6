package com.binar.challenge4.controller;

import com.binar.challenge4.model.Film;
import com.binar.challenge4.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/film")
public class FilmController {
    @Autowired
    private FilmService filmService;

    @PostMapping("/addFilm")
    public ResponseEntity<Film> addFilm(@RequestBody Film film) {
        Film film1 = filmService.addFilm(film);
        return new ResponseEntity<>(film1, HttpStatus.CREATED);
    }

    @GetMapping("/getAllFilm")
    public ResponseEntity<List<Film>> getAllFilm() {
        List<Film> allFilm = filmService.getAllFilm();
        return ResponseEntity.ok(allFilm);
    }

    @GetMapping("/getAllFilmPagination")
    public Page<Film> getAllUsersPagination(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return filmService.getAllFilmPagination(page, size);
    }

    @GetMapping("/getFilmById/{filmId}")
    public ResponseEntity<Film> getFilmById(@RequestParam("filmCode") Long id) {
        Optional<Film> film = filmService.getFilmById(id);
        if (film.isPresent()) {
            return new ResponseEntity<>(film.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getFilmByIsPremiered")
    public ResponseEntity<List<Film>> getFilmByIsPremiered(@RequestParam(name = "isPremiered") Boolean isPremiered){
        List<Film> schedule = filmService.getScheduleByFilmIsPremiered(isPremiered);
        return ResponseEntity.ok(schedule);
    }

    @PutMapping("/updateFilm")
    public ResponseEntity<String> updateFilm(@RequestParam(name = "filmCode") Long id, @RequestBody Film film) {
        filmService.updateFilm(id, film);
        return new ResponseEntity<String>("Data berhasil di update", HttpStatus.OK);
    }

    @DeleteMapping("/deleteFilmById/{filmId}")
    public ResponseEntity<String> deleteFilmById(@RequestParam(name = "filmCode") Long id) {
        filmService.deleteFilmById(id);
        return ResponseEntity.ok("Data Berhasil dihapus");
    }
}
