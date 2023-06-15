package com.example.demo.core.movement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.core.movement.converter.MovementToMovementDTO;
import com.example.demo.core.movement.web.MovementDTO;

@Service
public class MovementService
{
  private final MovementRepo movementRepo;

  private final MovementToMovementDTO movementToMovementDTO;

  MovementService(MovementRepo movementRepo, MovementToMovementDTO movementToMovementDTO) {
    this.movementRepo = movementRepo;
    this.movementToMovementDTO = movementToMovementDTO;
  }

  public Page<MovementDTO> findAllMovements(Pageable pageable) {

    Page<Movement> movements = movementRepo.findAll(pageable);
    List<MovementDTO> movementDTOList = new ArrayList<>();
    movements.forEach(movement -> {
      MovementDTO movementDTO = movementToMovementDTO.convert(movement);
      movementDTOList.add(movementDTO);
    });
    return new PageImpl<>(movementDTOList, pageable, movements.getTotalElements());
  }

  public Page<MovementDTO> findAllMovementsByAccount(Long accountNumber, Pageable pageable) {

    Page<Movement> movements = movementRepo.findByAccountNumber(accountNumber, pageable);
    List<MovementDTO> movementDTOList = new ArrayList<>();
    movements.forEach(movement -> {
      MovementDTO movementDTO = movementToMovementDTO.convert(movement);
      movementDTOList.add(movementDTO);
    });
    return new PageImpl<>(movementDTOList, pageable, movements.getTotalElements());
  }
}
