package aluraFigurinhas;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class aluraFigurinhas {

	public static void main(String[] args) throws IOException, InterruptedException {

		String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
		URI endereco = URI.create(url);
		HttpClient httpClient = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
		HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
		String body = response.body();

		JsonParser jsonParser = new JsonParser();
		List<Map<String, String>> listaDeFilmes = jsonParser.parse(body);

		for (Map<String, String> filme : listaDeFilmes) {

			String urlDaImagem = filme.get("image");
			String[] urlLista;
			urlLista =  urlDaImagem.split("@");
			
			urlDaImagem = urlLista[0]+"@.jpg";
			String nota = filme.get("imDbRating");		
			String nomeDoArquivo = filme.get("title") + ".png";

			InputStream inputStream = new InputStream() {

				@Override
				public int read() throws IOException {
					return 0;
				}
			};

			try {
				inputStream = new URL(urlDaImagem).openStream();
				FabricaDeFigurinhas fabrica = new FabricaDeFigurinhas();
				fabrica.cria(inputStream, nomeDoArquivo,nota);
				System.out.println("baixado: " + nomeDoArquivo);
			} catch (Exception e) {
				System.out.println("Falha na obtenção do arquivo ou arquivo não encontrado.");
			}
		}

	}

}
