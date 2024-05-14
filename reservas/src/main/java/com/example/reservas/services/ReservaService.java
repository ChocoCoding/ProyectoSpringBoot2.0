package com.example.reservas.services;

import com.example.reservas.DTO.ReservasDTO.CambiarEstadoDTO;
import com.example.reservas.DTO.ReservasDTO.CrearReserva;
import com.example.reservas.DTO.ReservasDTO.DevolverReservaDTO;
import com.example.reservas.entities.Habitacion;
import com.example.reservas.entities.Reserva;
import com.example.reservas.repositories.HabitacionRepository;
import com.example.reservas.repositories.ReservaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final HabitacionRepository habitacionRepository;


    //TODO FALTA METER EL USUARIO Y EL ESTADO Y PREGUNTAR SI AL HACER UNA RESERVA HAY QUE COMPROBAR SI LA HABITACION ESTA DISPONIBLE
    public boolean guardar(CrearReserva crearReserva, Integer usuarioId){
        Habitacion habitacion = habitacionRepository.findById(crearReserva.getHabitacion_id()).orElse(null);
        if (habitacion!= null){
            Reserva reserva = new Reserva();
            reserva.setFecha_inicio(LocalDate.parse(crearReserva.getFecha_inicio()));
            reserva.setFecha_fin(LocalDate.parse(crearReserva.getFecha_fin()));
            reserva.setUsuario_id(usuarioId);
            reserva.setHabitacionReservada(habitacion);
            reserva.setEstado("Pendiente");
            reservaRepository.save(reserva);
            return true;
        }else return false;
    }



    @Transactional
    public boolean updateEstadoById(CambiarEstadoDTO cambiarEstadoDTO){
        if (cambiarEstadoDTO.getEstado().equalsIgnoreCase("Confirmada") || cambiarEstadoDTO.getEstado().equalsIgnoreCase("Pendiente") || cambiarEstadoDTO.getEstado().equalsIgnoreCase("Cancelada")){
            reservaRepository.updateEstadoById(cambiarEstadoDTO.getEstado(),cambiarEstadoDTO.getReserva_id());
            return true;
        }else return false;
    }

    public List<DevolverReservaDTO> listarReservasUsuario(Integer idUsuario){
        List<Reserva> listaReservas = reservaRepository.listarReservasUsuario(idUsuario);
        List<DevolverReservaDTO> reservas = new ArrayList<>();
        for (Reserva r:listaReservas) {
            reservas.add(new DevolverReservaDTO(r.getFecha_inicio(),r.getFecha_fin(),r.getHabitacionReservada().getHabitacion_id()));
        }
        if (!reservas.isEmpty()){
            return reservas;
        }else return null;
    }


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
