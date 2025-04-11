package br.com.felk.barbershopui.service.query.Impl;

import br.com.felk.barbershopui.entity.ScheduleEntity;
import br.com.felk.barbershopui.exception.NotFoundException;
import br.com.felk.barbershopui.exception.ScheduleInUseException;
import br.com.felk.barbershopui.repositories.IScheduleRepository;
import br.com.felk.barbershopui.service.query.IScheduleQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
@AllArgsConstructor
public class ScheduleQueryService implements IScheduleQueryService {

    private final IScheduleRepository repository;

    @Override
    public ScheduleEntity findById(final long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Agendamento não encontrado")
        );
    }

    @Override
    public List<ScheduleEntity> findInMonth(final OffsetDateTime startAt, final OffsetDateTime endAt) {
        return repository.findByStartAtGreaterThanEqualAndEndAtLessThanEqualOrderByStartAtAscEndAtAsc(startAt, endAt);
    }

    @Override
    public void verifyIfScheduleExists(final OffsetDateTime startAt, final OffsetDateTime endAt) {
        if (repository.existsByStartAtAndEndAt(startAt, endAt)){
            var message = String.format("Já existe um cliente agendado no horário para %s h ", startAt.toString());
            throw new ScheduleInUseException(message);
        }
    }

}
