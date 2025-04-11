package br.com.felk.barbershopui.service;

import br.com.felk.barbershopui.entity.ScheduleEntity;

public interface IScheduleService {

    ScheduleEntity save(final ScheduleEntity entity);

    void delete(final long id);

}
