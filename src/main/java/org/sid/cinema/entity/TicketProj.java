package org.sid.cinema.entity;

import org.sid.cinema.entity.Place;
import org.sid.cinema.entity.Ticket;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "TicketProj",types = Ticket.class)
public interface TicketProj {

    public Long getId();
    public String getNomClient();
    public double getPrix();
    public Integer getCodePayment();
    public boolean getReservee();
    public Place getPlace();

}
