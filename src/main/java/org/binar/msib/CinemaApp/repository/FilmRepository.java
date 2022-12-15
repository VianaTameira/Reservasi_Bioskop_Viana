package org.binar.msib.CinemaApp.repository;

import org.binar.msib.CinemaApp.entity.Film;
import org.binar.msib.CinemaApp.entity.Orders;
import org.binar.msib.CinemaApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FilmRepository extends JpaRepository<Film,Integer> {
    List<Film> findBySedangTayang (Boolean sedangTayang);
    @Query(nativeQuery = true, value = "SELECT*FROM orders o INNER JOIN user u ON o.user_id = u.user_id INNER JOIN film f ON o.film_code = f.film_code INNER JOIN schedule s ON o.film_code = s.film_code where u.username = :username and f.film_name = :film_name")
    List<Film> getFilmOrder(@Param("username") String username, @Param("film_name") String film_name);
}