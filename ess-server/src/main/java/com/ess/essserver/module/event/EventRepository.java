package com.ess.essserver.module.event;

import org.springframework.stereotype.Repository;

    @Repository
    interface EventRepository extends org.springframework.data.jpa.repository.JpaRepository<EventModel, Integer> {}


