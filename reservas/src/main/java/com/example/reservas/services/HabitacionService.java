package com.example.reservas.services;

import com.example.reservas.DTO.HabitacionDTO;
import com.example.reservas.entities.Habitacion;
import com.example.reservas.entities.Hotel;
import com.example.reservas.entities.Reserva;
import com.example.reservas.repositories.HabitacionRepository;
import com.example.reservas.repositories.HotelRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HabitacionService {

    private final HabitacionRepository habitacionRepository;
    private final HotelRepository hotelRepository;
    public boolean crearHabitacion(HabitacionDTO habitacionDTO){
        Hotel hotel = hotelRepository.findById(habitacionDTO.getHotel_id()).orElse(null);
        if (hotel != null){
            Habitacion habitacion = new Habitacion();
            habitacion.setNumero_habitacion(habitacionDTO.getNumero_habitacion());
            habitacion.setTipo(habitacionDTO.getTipo());
            habitacion.setPrecio(habitacionDTO.getPrecio());
            habitacion.setHotel(hotel);
            habitacion.setDisponible(true);
            habitacionRepository.save(habitacion);
            return true;
        }else return false;

    }

    public Optional<Habitacion> findById(Integer id){
        return habitacionRepository.findById(id);
    }

    //TODO Si no existe la habitacion, hay que ponerle try catch?
    public boolean update(HabitacionDTO habitacionActualizada) {
        Optional<Habitacion> habitacion = habitacionRepository.findById(habitacionActualizada.getHabitacion_id());
        Optional<Hotel> hotel = hotelRepository.findById(habitacionActualizada.getHotel_id());
        if (habitacion.isPresent() && hotel.isPresent()) {
            Habitacion habitacionNueva = habitacion.get();
            habitacionNueva.setHabitacion_id(habitacion.get().getHabitacion_id());
            habitacionNueva.setNumero_habitacion(habitacionActualizada.getNumero_habitacion());
            habitacionNueva.setTipo(habitacionActualizada.getTipo());
            habitacionNueva.setPrecio(habitacionActualizada.getPrecio());
            habitacionNueva.setDisponible(habitacionActualizada.getDisponible());
            habitacionNueva.setHotel(hotel.get());
            habitacionNueva.setReservas(habitacion.get().getReservas());
            habitacionRepository.save(habitacionNueva);
            return true;
        } else return false;
    }


    public boolean eliminarHabitacion(int id){
        Optional<Habitacion> habitacion = habitacionRepository.findById(id);
        if (habitacion.isPresent()){
            List<Reserva> listaReservas =  habitacion.get().getReservas();
            for (Reserva r : listaReservas) {
                r.setHabitacionReservada(null);
            }
            habitacionRepository.delete(habitacion.get());
            return true;
        }
        else return false;
    }



}



