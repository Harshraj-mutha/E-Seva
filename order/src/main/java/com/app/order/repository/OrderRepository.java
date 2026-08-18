package com.app.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.order.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
