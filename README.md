# baixadorDeImagensImdb

Aplicação Java que consome uma API com os 250 filmes melhor avaliados do IMDb, baixa o pôster de cada um e grava a nota sobre a imagem, gerando "figurinhas".

> Projeto de estudo (agosto/2022), feito acompanhando o exercício de figurinhas da Alura. Praticava `HttpClient`, parsing manual de JSON e manipulação de imagem com a API `Graphics2D`.

## Stack

- Java 11 (`java.net.http.HttpClient`)
- `javax.imageio` e `Graphics2D` (escrita da nota sobre o pôster)

## Como funciona

```
src/aluraFigurinhas/aluraFigurinhas.java    → ponto de entrada: busca a lista e itera
src/aluraFigurinhas/JsonParser.java         → parser de JSON escrito à mão
src/aluraFigurinhas/FabricaDeFigurinhas.java → recorta/escreve a nota e salva o PNG
```

Para cada filme da lista, o programa pega a URL do pôster, corta o sufixo de redimensionamento do IMDb (tudo após o `@`) para obter a imagem em tamanho cheio, baixa o arquivo e repassa o `InputStream` para a `FabricaDeFigurinhas`, que escreve a nota e salva como `{título}.png`.

Falhas no download de um pôster específico não interrompem o programa: ele imprime `Falha na obtenção do arquivo ou arquivo não encontrado.` e segue para o próximo filme.

## Como rodar

O projeto foi criado no Eclipse (há `.classpath` e `.project` no repositório), sem dependências externas além do JDK.

Pelo Eclipse: importe como *Existing Project* e rode `aluraFigurinhas` como Java Application.

Pela linha de comando:

```bash
javac -d bin src/aluraFigurinhas/*.java
java -cp bin aluraFigurinhas.aluraFigurinhas
```

As imagens são geradas na pasta de execução do programa.

## Limitações

A URL da API é um mock fixo no código (`mocki.io`) que pode não estar mais disponível — nesse caso o programa falha na primeira chamada. Os `.class` compilados estão versionados em `bin/`. O diretório de saída das imagens não é configurável.
