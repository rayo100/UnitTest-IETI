package org.adaschool.Weather;

import org.adaschool.Weather.data.WeatherReport;
import org.adaschool.Weather.service.WeatherReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class WeatherReportControllerTest {

	private WeatherReportController controller;
	private WeatherReportService weatherReportService;

	@BeforeEach
	public void setUp() {
		// Crear un mock para WeatherReportService
		weatherReportService = Mockito.mock(WeatherReportService.class);
		controller = new WeatherReportController(weatherReportService);
	}

	@Test
	public void testGetWeatherReport() {
		// Configurar el comportamiento del mock
		double expectedTemperature = 25.0;
		double expectedHumidity = 60.0;
		when(weatherReportService.getWeatherReport(37.8267, -122.4233))
				.thenReturn(new WeatherReport(expectedTemperature, expectedHumidity));

		// Llamar al método en el controlador
		WeatherReport report = controller.getWeatherReport(37.8267, -122.4233);

		// Verificar que el resultado sea el esperado
		assertEquals(expectedTemperature, report.getTemperature());
		assertEquals(expectedHumidity, report.getHumidity());
	}

	@Test
	public void testGetWeatherReportWithDifferentLocation() {
		// Configurar el comportamiento del mock para otra ubicación
		double expectedTemperature = 20.0;
		double expectedHumidity = 70.0;
		when(weatherReportService.getWeatherReport(40.7128, -74.0060))
				.thenReturn(new WeatherReport(expectedTemperature, expectedHumidity));

		// Llamar al método en el controlador con una ubicación diferente
		WeatherReport report = controller.getWeatherReport(40.7128, -74.0060);

		// Verificar que el resultado sea el esperado
		assertEquals(expectedTemperature, report.getTemperature());
		assertEquals(expectedHumidity, report.getHumidity());
	}

	@Test
	public void testGetWeatherReportWithInvalidLocation() {
		// Configurar el comportamiento del mock para una ubicación inválida
		when(weatherReportService.getWeatherReport(0.0, 0.0))
				.thenReturn(null); // Simular que no se encontró un informe para la ubicación

		// Llamar al método en el controlador con una ubicación inválida
		WeatherReport report = controller.getWeatherReport(0.0, 0.0);

		// Verificar que el resultado sea nulo
		assertEquals(null, report);
	}
}

