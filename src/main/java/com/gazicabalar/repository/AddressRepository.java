package com.gazicabalar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gazicabalar.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{

}
