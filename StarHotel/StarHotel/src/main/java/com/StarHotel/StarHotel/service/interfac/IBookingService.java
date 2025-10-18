package com.StarHotel.StarHotel.service.interfac;

import com.StarHotel.StarHotel.dto.Response;
import com.StarHotel.StarHotel.entity.Booking;

public interface IBookingService {
    Response saveBooking(Long roomId, Long userId, Booking bookingRequest);

    Response findBookingByConfirmationCode(String confirmationCode);

    Response getAllBookings();

    Response cancelBooking(Long bookingId);
}
