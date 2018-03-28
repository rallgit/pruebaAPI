# pruebaAPI
Prueba de Implementacion de API ML
Implementación de API MercadoLibre en java.
 
A continuación, se explicará una implementación de la API de MercadoLibre en java con entorno de desarrollo eclipse.

URLs de la API usadas

Obtiene resultado de una búsqueda con información sobre los ítems encontrados
https://api.mercadolibre.com/sites/MLU/search?category=MLU1574&official_store_id=all#options

Obtiene información sobre el usuario
https://api.mercadolibre.com/users/%7B165833556%7D#options

Específicamente se realizaron las siguientes consultas:

Con estas se consultó sobre la cantidad de ítems dado el Id de vendedor
https://api.mercadolibre.com/sites/MLC/search?seller_id=165833556
https://api.mercadolibre.com/sites/MLC/search?seller_id=270710853
https://api.mercadolibre.com/sites/MLC/search?seller_id=270710853

Para mapear la respuesta de la API hacia java se usaron las siguientes clases 
 
 public class ClassResultTotal {
	
	private ClassPaging paging;
	
	public void setTotal(ClassPaging paging) {
		this.paging=paging;
	}
	
	public ClassPaging getPaging() {
		return this.paging;
	}
	
}

public class ClassPaging {

private int total;
	
	public void setTotal(int total) {
		this.total=total;
	}
	
	public int getTotal() {
		return this.total;
	}
	
}

Con estas se consultó sobre el nombre del vendedor dado su Id. 
https://api.mercadolibre.com/users/165833556
https://api.mercadolibre.com/users/270710853
https://api.mercadolibre.com/users/222517911 

Para java se usó la siguiente clase:
 

public class ClassResultName {

	private String nickname;

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	
}

Finalmente en la clase principal, el metodo "getReportById(String id)" devuelve automaticamente el nombre y cantidad de items de un vendedor dado su Id, esto mediante un objeto con la siguiente estructura:

public class ClassReport {

	private String userName;
	private int totalItems;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getTotalItems() {
		return totalItems;
	}
	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}
	
}





La traducción de la respuesta de la API hacia objeto java se hace mediante la librería 
com.google.gson.Gson;
Luego es básicamente, instanciar el objeto y mediante el método “fromJson” se recibe la respuesta de la api y se convierte en objeto especificando su clase en el segundo parámetro.
Gson gson = new Gson();
ClassResultTotal recOBJ = gson.fromJson(responseAPI, ClassResultTotal.class);

Como cargar la librería Gson al eclipse.
Ir a la barra de menú Windows >preferences y luego navegar hacia la pestaña Java >Build path > User Libraries. Hacer clic en nuevo e ingresar el nombre de la librería, por ejemplo “gson_lib” y hacer clic en OK. Con la librería seleccionada “gson_lib” hacer clic en Add External JARs y cargar el archivo gson-2.6.2.jar luego hacer clic en Apply and Close.
