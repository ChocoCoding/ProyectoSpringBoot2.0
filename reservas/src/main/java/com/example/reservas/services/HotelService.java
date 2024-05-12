package com.example.reservas.services;

import ch.qos.logback.core.joran.conditional.IfAction;
import com.example.reservas.DTO.HotelDTO;
import com.example.reservas.entities.Habitacion;
import com.example.reservas.entities.Hotel;
import com.example.reservas.repositories.HabitacionRepository;
import com.example.reservas.repositories.HotelRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;
    private final HabitacionRepository habitacionRepository;

    public void guardar(HotelDTO hotelNuevo){
        Hotel hotel = new Hotel();
        hotel.setNombre(hotelNuevo.getNombre());
        hotel.setDireccion(hotelNuevo.getDireccion());
        hotelRepository.save(hotel);
    }

    public boolean update(HotelDTO hotelActualizado){
        Optional<Hotel> hotel = hotelRepository.findById(hotelActualizado.getHotel_id());
        if (hotel.isPresent()){
            Hotel hotelNuevo = hotel.get();
            hotelNuevo.setHotel_id(hotel.get().getHotel_id());
            hotelNuevo.setNombre(hotelActualizado.getNombre());
            hotelNuevo.setDireccion(hotelActualizado.getDireccion());
            hotelRepository.save(hotelNuevo);
            return true;
        }else return false;

    }

    public Hotel findById(Integer id){
        return hotelRepository.findById(id).orElse(null);
    }

    @Transactional
    public boolean delete(Integer id){
        Optional<Hotel> hotel = hotelRepository.findById(id);
        if (hotel.isPresent()){
            List<Habitacion> listaHabitaciones = hotel.get().getHabitaciones();
            for (Habitacion h: listaHabitaciones) {
                h.setDisponible(false);
                h.setHotel(null);
            }
            hotel.get().setHabitaciones(null);
            habitacionRepository.deleteByHotel(hotel);
            hotelRepository.delete(hotel.get());
            return true;
        }else return false;
    }

    public Integer obtenerIdApartirNombre(String nombre){
        return hotelRepository.obtenerIdApartirNombre(nombre);
    }

    public String obtenerNombreAPartirId(Integer hotelId){
        return hotelRepository.obtenerNombreAPartirId(hotelId);
    }




}
