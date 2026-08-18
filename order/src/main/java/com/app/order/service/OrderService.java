package com.app.order.service;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.order.dto.OrderRequestDto;
import com.app.order.entity.Order;
import com.app.order.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
	
	private final OrderRepository orderRepo;
	
	private final ModelMapper modelMapper;
	
	public String PlacedOrder(OrderRequestDto orderDto) {
		Order order = modelMapper.map(orderDto, Order.class);
		order.setCreateDtm(LocalDate.now());
		order.setCreateUser("Harsh");
		orderRepo.save(order);
		return "Congratulation!! Your order is placed";
	}

}
