package com.example.jpapractice.repository;

import com.example.jpapractice.entity.Hotel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class HotelRepositoryTest {

    @Autowired
    private HotelRepository hotelRepository;

    @Test
    @Transactional
    void save() {
        hotelRepository.save();
        Hotel hotel = hotelRepository.findByName("호텔");
        String name = hotel.getName();

        assertThat(name).isEqualTo("호텔");
    }
}