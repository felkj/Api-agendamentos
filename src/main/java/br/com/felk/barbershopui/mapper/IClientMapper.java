package br.com.felk.barbershopui.mapper;

import br.com.felk.barbershopui.controller.request.SaveClientRequest;
import br.com.felk.barbershopui.controller.request.UpdateClientRequest;
import br.com.felk.barbershopui.controller.response.ClientDetailResponse;
import br.com.felk.barbershopui.controller.response.ListClientResponse;
import br.com.felk.barbershopui.controller.response.SaveClientResponse;
import br.com.felk.barbershopui.controller.response.UpdateClientResponse;
import br.com.felk.barbershopui.entity.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface IClientMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "schedules", ignore = true)
    ClientEntity toEntity(final SaveClientRequest request);

    SaveClientResponse toSaveResponse(final ClientEntity entity);

    @Mapping(target = "schedules", ignore = true)
    ClientEntity toEntity(final long id, final UpdateClientRequest request);

    UpdateClientResponse toUpdateResponse(final ClientEntity entity);

    ClientDetailResponse toDetailResponse(final ClientEntity entity);

    List<ListClientResponse> toListResponse(final List<ClientEntity> entities);

}
