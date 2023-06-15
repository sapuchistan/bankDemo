package com.example.demo.core.movement.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.demo.core.movement.Movement;
import com.example.demo.core.movement.web.MovementDTO;

@Component
public class MovementToMovementDTO
    implements Converter<Movement, MovementDTO>
{

  @Override
  public MovementDTO convert(Movement movement) {
    MovementDTO dto = new MovementDTO();
    dto.setAccountNumber(movement.getAccount().getAccountNumber().getAccountNumberId());
    dto.setBalance(movement.getBalance());
    dto.setCredit(movement.getCredit());
    dto.setDebit(movement.getDebit());
    dto.setTimeStamp(movement.getTimeStamp());
    dto.setMovementType(movement.getMovementType().toString());
    return dto;
  }

}
