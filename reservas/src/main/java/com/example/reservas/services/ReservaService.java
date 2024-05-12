package com.example.reservas.services;

import com.example.reservas.DTO.DelvolverReservaDTO;
import com.example.reservas.DTO.ReservaDTO;
import com.example.reservas.DTO.UsuarioContrasenhaDTO;
import com.example.reservas.entities.Habitacion;
import com.example.reservas.entities.Reserva;
import com.example.reservas.repositories.HabitacionRepository;
import com.example.reservas.repositories.ReservaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final HabitacionRepository habitacionRepository;


    //TODO FALTA METER EL USUARIO Y EL ESTADO Y PREGUNTAR SI AL HACER UNA RESERVA HAY QUE COMPROBAR SI LA HABITACION ESTA DISPONIBLE
    public boolean guardar(ReservaDTO reservaDTO){
        Optional<Habitacion> habitacion = habitacionRepository.findById(reservaDTO.getHabitacion_id());
        if (habitacion.isPresent()){
            Reserva reserva = new Reserva();
            reserva.setFecha_inicio(LocalDate.parse(reservaDTO.getFecha_inicio()));
            reserva.setFecha_fin(LocalDate.parse(reservaDTO.getFecha_fin()));
            reserva.setHabitacionReservada(habitacion.get());
            reservaRepository.save(reserva);
            return true;
        }else return false;
    }



    @Transactional
    public boolean updateEstadoById(ReservaDTO reservaDTO){
        if (reservaDTO.getEstado().equalsIgnoreCase("Confirmada") || reservaDTO.getEstado().equalsIgnoreCase("Pendiente") || reservaDTO.getEstado().equalsIgnoreCase("Cancelada")){
            reservaRepository.updateEstadoById(reservaDTO.getEstado(),reservaDTO.getReserva_id());
            return true;
        }else return false;
    }

    public List<DelvolverReservaDTO> listarReservasUsuario(Integer idUsuario){
        List<Reserva> listaReservas = reservaRepository.listarReservasUsuario(idUsuario);
        List<DelvolverReservaDTO> reservas = new ArrayList<>();
        for (Reserva r:listaReservas) {
            reservas.add(new DelvolverReservaDTO(r.getFecha_inicio(),r.getFecha_fin(),r.getHabitacionReservada().getHabitacion_id()));
        }
        if (!reservas.isEmpty()){
            return reservas;
        }else return null;
    }


    //TODO LA LISTA QUE ME DEVUELVE CREA UN BUCLE ENTRE LOS CAMPOS
    public List<Reserva> listarReservasSegunEstado(String estado){
        if (estado.equalsIgnoreCase("Confirmada") || estado.equalsIgnoreCase("Pendiente") || estado.equalsIgnoreCase("Cancelada")){
            return reservaRepository.listarReservasSegunEstado(estado);
        }else return new ArrayList<Reserva>();

    }

    public Boolean checkReserva(Integer idUsuario,Integer idHotel, Integer idReserva){
    Integer numeroReservas = reservaRepository.checkReserva(idUsuario,idHotel,idReserva);
    if (numeroReservas>0){
        return true;
    }else return false;

    }
}
