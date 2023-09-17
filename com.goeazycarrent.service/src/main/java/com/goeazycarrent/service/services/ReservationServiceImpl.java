package com.goeazycarrent.service.services;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goeazycarrent.service.DateUtil;
import com.goeazycarrent.service.dto.ReservationRequestDto;
import com.goeazycarrent.service.exception.GoEazyException;
import com.goeazycarrent.service.model.Reservations;
import com.goeazycarrent.service.repository.ReservationRepository;

@Service
public class ReservationServiceImpl implements ReservationService {

	private static final String CANCELLED = "Cancelled";

	private static final String RESERVED = "Reserved";

	@Autowired
	ReservationRepository reservationRepo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public Reservations getReservationById(String reservationId) throws GoEazyException {

		Reservations findAllById = reservationRepo.findByUniqueId(reservationId);
		if (findAllById == null) {

			throw new GoEazyException(null);

		}
		return findAllById;
	}

	@Override
	public void cancelReservation(String id) throws GoEazyException {

		Reservations reservationObj = reservationRepo.findByUniqueId(id);
		if (reservationObj != null) {
			reservationObj.setStatus(CANCELLED);
			reservationRepo.save(reservationObj);
		} else {

			throw new GoEazyException("reservation details is not found");
		}

	}

	@Override
	public Reservations modifyReservation(ReservationRequestDto reservationDto) throws GoEazyException {
		Reservations reservationObj = reservationRepo.findByUniqueId(reservationDto.getReservationId());
		if (reservationObj != null) {
			if(StringUtils.isNotBlank(reservationDto.getDropoffDate())) {
				reservationObj.setEndDate(DateUtil.convertDateByTimezone(reservationObj.getLocation(),reservationDto.getDropoffDate()));
			}
			if(StringUtils.isNotBlank(reservationDto.getPickupDate())) {
				reservationObj.setStartEnd(DateUtil.convertDateByTimezone(reservationObj.getLocation(),reservationDto.getPickupDate()));
			}
			if(reservationDto.getVehicleId()!=null && reservationDto.getVehicleId()!=0) {
				reservationObj.setVehicleId(reservationDto.getVehicleId());
			}
			return reservationRepo.save(reservationObj);
		} else {
			throw new GoEazyException("reservation details is not found");
		}

	}

	@Override
	public Reservations confirmReservation(ReservationRequestDto reservationDto) throws GoEazyException {
		Reservations reservationMap = mapper.map(reservationDto, Reservations.class);
		reservationMap.setEndDate(DateUtil.convertDateByTimezone(reservationDto.getLocation(),reservationDto.getDropoffDate()));
		reservationMap.setStartEnd(DateUtil.convertDateByTimezone(reservationDto.getLocation(),reservationDto.getPickupDate()));
		reservationMap.setStatus(RESERVED);
		reservationMap.setReservationId(getShortUUID());
		Reservations savedReservation = reservationRepo.save(reservationMap);
		return savedReservation;
		
	}

	

	private String getShortUUID() {
		UUID uuid = UUID.randomUUID();
		long l = ByteBuffer.wrap(uuid.toString().getBytes()).getLong();
		return Long.toString(l, Character.MAX_RADIX);
	}

	@Override
	public List<Integer> getAllVehiclesByDate(String location,String fromDate, String toDate) throws GoEazyException {
		return reservationRepo.findVehiclesByDate(location,fromDate, toDate);
	}

}
