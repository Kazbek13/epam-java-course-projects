package com.main;

import com.aircraft.instances.*;
import com.aircraft.logic.Aircraft;
import com.airline.Airline;

public class Main {
	public static void main(String[] args) {
		Aircraft aircraftAirbusA320a = new AircraftAirbusA320();
		Aircraft aircraftAirbusA320b = new AircraftAirbusA320();
		Aircraft aircraftDouglas = new AircraftDouglasMD11F();
		Aircraft aircraftCessna = new AircraftCessna172();
		Aircraft aircraftBoeing = new AircraftBoeing737();
		
		Airline ukraineInternationalAirline = new Airline("Ukraine International Airlines");
		ukraineInternationalAirline.addAircraft(aircraftAirbusA320a);
		ukraineInternationalAirline.addAircraft(aircraftAirbusA320b);
		ukraineInternationalAirline.addAircraft(aircraftDouglas);
		ukraineInternationalAirline.addAircraft(aircraftCessna);
		ukraineInternationalAirline.addAircraft(aircraftBoeing);
		
		ukraineInternationalAirline.showAirlineAircrafts();
		
		System.out.println("Aircrafts sorted by flight range:");
		ukraineInternationalAirline.sortPlanesByFlightRange();
		ukraineInternationalAirline.showAirlineAircrafts();
		System.out.println();
		
		ukraineInternationalAirline.filterAircraftsByFuelConsumption(2000, 3000, true);
		System.out.println();
		
		ukraineInternationalAirline.calculateTotalCargoPassengerCapacity();
	}
}
