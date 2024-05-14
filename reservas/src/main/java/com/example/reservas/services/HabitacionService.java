package com.example.reservas.services;

import com.example.reservas.DTO.HabitacionDTO.ActualizarHabitacionDTO;
import com.example.reservas.DTO.HabitacionDTO.CrearHabitacionDTO;
import com.example.reservas.entities.Habitacion;
import com.example.reservas.entities.Hotel;
import com.example.reservas.entities.Reserva;
import com.example.reservas.repositories.HabitacionRepository;
import com.example.reservas.repositories.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HabitacionService {

    private final HabitacionRepository habitacionRepository;
    private final HotelRepository hotelRepository;
    public boolean crearHabitacion(CrearHabitacionDTO crearHabitacionDTO){
        Hotel hotel = hotelRepository.findById(crearHabitacionDTO.getHotel_id()).orElse(null);
        if (hotel != null){
            Habitacion habitacion = new Habitacion();
            habitacion.setNumero_habitacion(crearHabitacionDTO.getNumero_habitacion());
            habitacion.setTipo(crearHabitacionDTO.getTipo());
            habitacion.setPrecio(crearHabitacionDTO.getPrecio());
            habitacion.setHotel(hotel);
            habitacion.setDisponible(true);
            habitacionRepository.save(habitacion);
            return true;
        }else return false;

    }

    public Habitacion findById(Integer id){
        return habitacionRepository.findById(id).orElse(null);
    }

    //TODO Si no existe la habitacion, hay que ponerle try catch?
    public boolean update(ActualizarHabitacionDTO actualizarHabitacionDTO) {
        Optional<Habitacion> habitacion = habitacionRepository.findById(actualizarHabitacionDTO.getId());
        Optional<Hotel> hotel = hotelRepository.findById(actualizarHabitacionDTO.getHotel_id());
        if (habitacion.isPresent() && hotel.isPresent()) {
            Habitacion habitacionNueva = habitacion.get();
            habitacionNueva.setHabitacion_id(habitacion.get().getHabitacion_id());
            habitacionNueva.setNumero_habitacion(actualizarHabitacionDTO.getNumero_habitacion());
            habitacionNueva.setTipo(actualizarHabitacionDTO.getTipo());
            habitacionNueva.setPrecio(actualizarHabitacionDTO.getPrecio());
            habitacionNueva.setDisponible(actualizarHabitacionDTO.getDisponible());
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



