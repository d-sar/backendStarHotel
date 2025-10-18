package com.StarHotel.StarHotel.service.interfac;

import com.StarHotel.StarHotel.dto.LoginRequest;
import com.StarHotel.StarHotel.dto.Response;
import com.StarHotel.StarHotel.entity.User;

public interface IUerService {
    Response register(User user);

    Response login(LoginRequest loginRequest);

    Response getAllUsers();

    Response getUserBookingHistory(String userId);

    Response deleteUser(String userId);

    Response getUserById(String userId);

    Response getMyInfo(String email);
}
