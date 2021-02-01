package org.sid.cinema.entity;

import org.sid.cinema.entity.Film;
import org.sid.cinema.entity.Salle;
import org.sid.cinema.entity.Seance;
import org.sid.cinema.entity.Ticket;
import org.springframework.data.rest.core.config.Projection;

import java.util.Collection;
import java.util.Date;

@Projection(types = {org.sid.cinema.entity.Projection.class}, name = "p1")
public interface ProjectionProj {
    public Long getId();
    public Date getDateProjection();
    public double getPrix();
    public Salle getSalle();
    public Film getFilm();
    public Collection<Ticket> getTickets();
    public Seance getSeance();
}
