package co.ceiba.parking.logica;

public class RegistroVehiculo {
	

	public double totalParqueo(Vehiculo v){
		//variables
		double diff;
		
		double adicionales = 0;
		
		double horas;
		
		double minutos;
		
		double totalValorParqueo = 0;
		
		Carro car = null;
		
		Moto moto = null;
		
		
		//implementation
		diff = v.getHoraSalida().getTime() - v.getHoraIngreso().getTime();
		
				
		if (v.getClass().getName().endsWith(".Moto")){
			moto = (Moto)v;
		}else{
			car = (Carro)v;
		}
		
		
		if (moto != null){			
			if(moto.getCilindraje() > 500){
				adicionales = 2000;
			}
		}
		
				
		//Convert milliseconds to minutes
		minutos = ((diff)/60000);
		horas = Math.ceil(minutos/60);	
		
		
		// < 9 Hours
		if (horas < 9){
						
			
			if (moto != null){
				
				totalValorParqueo = obtenerValorPorHoras(moto, horas);
				
			} else{
				
				totalValorParqueo = obtenerValorPorHoras(car, horas);
				
			}
			
		// >= 9 Hours
		}else{
			
			if (moto != null){
				
				totalValorParqueo = obtenerValorPorDias(moto, horas);
				
			} else{
				
				totalValorParqueo = obtenerValorPorDias(car, horas);
				
			}

		}
				
		return totalValorParqueo + adicionales;
	}
	
	
	public double obtenerValorPorHoras(Vehiculo v, double horas){
		
		Tarifa t = v.getTarifa();
		
		if (v.getClass().getName().endsWith(".Carro")){
			
			return t.getValorHoraCarro()*horas;
			
		}else{
			
			return t.getValorHoraMoto()*horas;
			
		}
		
	}
	
	
	public double obtenerValorPorDias(Vehiculo v, double horas){
		double minutosRestanteCobroPorDia = 0;
		double dias;
		double valorParqueo;
		Tarifa t;
		
		if (horas < 24){
			horas = 24;
		}
		
		//convert to days
		dias = (int) (Math.round(horas)/24);
		
		//Valid how Valido cuanto tiempo resto despues de obtener los dias
		minutosRestanteCobroPorDia = ((horas)/24) - dias;
		horas = Math.ceil(minutosRestanteCobroPorDia * 24);
		
		//si las horas restantes son mayor a 9 aumento un dia 
		if (horas > 9){
			dias += 1;
		}
		
		if (v.getClass().getName().endsWith(".Moto")){
			
			t = v.getTarifa();
			valorParqueo = t.getValorDiaMoto()*dias;
			
			if (horas > 0){
				valorParqueo += obtenerValorPorHoras(v, horas);
			}
			
		} else {
			
			t = v.getTarifa();
			valorParqueo = t.getValorDiaCarro()*dias;
			
			if (horas > 0){
				valorParqueo += obtenerValorPorHoras(v, horas);
			}
			
		}
		
		return valorParqueo;
		
	}
	
}
