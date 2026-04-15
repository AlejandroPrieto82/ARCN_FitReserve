package com.fitreserve.application.usecase;

import com.fitreserve.domain.repository.ReservationRepository;
import com.fitreserve.domain.valueobject.UserId;
import com.fitreserve.interfaces.rest.response.ReservationResponse;

import java.util.List;
import java.util.stream.Collectors;

public class GetUserReservationsUseCase {

    private final ReservationRepository repository;

    public GetUserReservationsUseCase(ReservationRepository repository) {
        this.repository = repository;
    }

    public List<ReservationResponse> execute(String userId) {

        return repository.findByUserId(UserId.fromString(userId))
                .stream()
                .map(r -> new ReservationResponse(
                        r.getId().getValue().toString(),
                        r.getUserId().getValue().toString(),
                        r.getClassId().getValue().toString(),
                        r.getStatus().name()
                ))
                .collect(Collectors.toList());
    }
}