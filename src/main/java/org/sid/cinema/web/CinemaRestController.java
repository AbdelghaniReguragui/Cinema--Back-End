package org.sid.cinema.web;

import lombok.Data;

import org.sid.cinema.dao.FilmRepository;
import org.sid.cinema.dao.TicketRepository;
import org.sid.cinema.entity.Film;
import org.sid.cinema.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;



@RestController
@CrossOrigin("*")
public class CinemaRestController {

    @Autowired
    FilmRepository filmRepository;

    @Autowired
    TicketRepository ticketRepository;

    @GetMapping(value = "/imageFilm/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] image(@PathVariable(name = "id") Long id) throws IOException {
        Film f = filmRepository.findById(id).get();
        String photoName = f.getPhoto();
        File file = new File(System.getProperty("user.home") + "/cinema/images/" + photoName);
        Path path = Paths.get(file.toURI());
        return Files.readAllBytes(path);

    }

    @PostMapping("/payerTickets")
    public List<Ticket> payerTickets(@RequestBody TicketForm ticketForm){
        List<Ticket> tickets = new ArrayList<>();
        ticketForm.getTickets().forEach(idTicket -> {
            Ticket ticket = ticketRepository.findById(idTicket).get();
            ticket.setReservee(true);
            ticket.setCodePayment(ticketForm.getCodePayment());
            ticket.setNomClient(ticketForm.getNomClient());
            ticketRepository.save(ticket);
            tickets.add(ticket);
        });
        return tickets;
    }
}

@Data
class TicketForm{
    private String nomClient;
    private int codePayment;
    private List<Long> tickets=  new ArrayList<>();
}
