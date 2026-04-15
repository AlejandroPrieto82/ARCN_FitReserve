package com.fitreserve.domain.repository;

import com.fitreserve.domain.model.Reservation;

public interface ReservationRepository {

	Reservation save(Reservation reservation);
}
