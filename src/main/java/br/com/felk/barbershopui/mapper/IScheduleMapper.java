package br.com.felk.barbershopui.mapper;

import br.com.felk.barbershopui.controller.request.SaveScheduleRequest;
import br.com.felk.barbershopui.controller.response.ClientScheduleAppointmentResponse;
import br.com.felk.barbershopui.controller.response.SaveScheduleResponse;
import br.com.felk.barbershopui.controller.response.ScheduleAppointmentMonthResponse;
import br.com.felk.barbershopui.entity.ScheduleEntity;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface IScheduleMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "client.id", source = "clientId")
    br.com.felk.barbershopui.entity.ScheduleEntity toEntity(final br.com.felk.barbershopui.controller.request.@Valid SaveScheduleRequest request);

    @Mapping(target = "clientId", source = "client.id")
    SaveScheduleResponse toSaveResponse(final ScheduleEntity entity);

    @Mapping(target = "scheduledAppointments", expression = "java(toClientMonthResponse(entities))")
    ScheduleAppointmentMonthResponse toMonthResponse(final int year, final int month, final List<ScheduleEntity> entities);

    List<ClientScheduleAppointmentResponse> toClientMonthResponse(final List<ScheduleEntity> entities);

    @Mapping(target = "clientId", source = "client.id")
    @Mapping(target = "clientName", source = "client.name")
    @Mapping(target = "day", expression = "java(entity.getStartAt().getDayOfMonth())")
    ClientScheduleAppointmentResponse toClientMonthResponse(final ScheduleEntity entity);
}
