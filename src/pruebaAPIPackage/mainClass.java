/**
 * 
 */
package pruebaAPIPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;


/**
 * @author Citizen
 *
 */
public class mainClass {

	
	//Conecta con la API de mercadolibre y realiza la consulta
	public static String consumeAPI(String uri) throws IOException {
		
		URL url = new URL(uri);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Accept", "application/json");

		InputStream streamResponse = connection.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(streamResponse));
		String lineResponse = reader.readLine();
		connection.disconnect();
		return lineResponse;
	}
	
	//Obtiene la cantidad de items segun un Id de vendedor determinado
	public static ClassResultTotal getAmountItemsById(String id) throws IOException {
		
		String uri = "https://api.mercadolibre.com/sites/MLC/search?seller_id="+id+"#json";
		String responseAPI = consumeAPI(uri);
		Gson gson = new Gson();
		ClassResultTotal recOBJ = gson.fromJson(responseAPI, ClassResultTotal.class);
		return recOBJ;
	}
	
	//Obtiene el nombre de un vendedor determinado dado su Id
	public static ClassResultName getNameUserById(String id) throws IOException {
		
		String uri = "https://api.mercadolibre.com/users/"+id;
		String responseAPI = consumeAPI(uri);
		Gson gson = new Gson();
		ClassResultName recOBJ = gson.fromJson(responseAPI, ClassResultName.class);
		return recOBJ;
	}
	
	//Obtiene nombre y cantidad de items de un vendedor dado su Id
	public static ClassReport getReportById(String id) throws IOException {
		
		ClassReport recOBJ = new ClassReport();
		recOBJ.setUserName( (getNameUserById(id)).getNickname() );  
		recOBJ.setTotalItems( (getAmountItemsById(id)).getPaging().getTotal() );  		
		return recOBJ;
	}
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Demo API ML");
		String idsArray[] = {"165833556","270710853","222517911"}; 
		ClassResultTotal curTotalOBJ;
		ClassResultName curNameOBJ;
		ClassReport curReportOBJ;
		
		System.out.println("\nObtiene cantidad de items segun Id de vendedor:  ");
		for (String curId : idsArray) {
			curTotalOBJ = getAmountItemsById(curId);
			System.out.println("Id # "+curId+" -> Cantidad de Items="+curTotalOBJ.getPaging().getTotal());
		}
		
		System.out.println("\nObtiene nombre segun Id de vendedor:  ");
		for(String curId : idsArray) {
			curNameOBJ = getNameUserById(curId);
			System.out.println("Id # "+curId+" -> Nombre="+curNameOBJ.getNickname());
		}
		
		System.out.println("\nReporte de cantidad de Items: ");
		System.out.println("\tVendedor\t\tCantidad de Items");
		for (int i=0; i<idsArray.length; i++ ) {
			curReportOBJ = getReportById(idsArray[i]);
			System.out.println("\t"+curReportOBJ.getUserName()+"\t\t"+curReportOBJ.getTotalItems());
		}
		
		System.out.println("\nfin...");
		
	
		
	}

}
