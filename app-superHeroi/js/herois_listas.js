//RECUPERA A INFORMACAO DE TODOS OS 
//PRODUTOS CADASTRADOS NO SISTEMA
function getAllProdutos() {

    axios.get('http://localhost:8080/produto')
    .then(function (response) {
        console.log(response);

        var jsonData = response.data;
        var tableBody = $("table tbody");

        jsonData.forEach(produto => {
            
            var markup = "<tr>" +
                         "<td>" + superHeroi.id + "</td>" +
                         "<td>" + superHeroi + "</td>" +
                         "<td> R$ " + primeiraAparicao + "</td>" +
                         "<td>" + apelido + "</td>" +
                         "<td>" + fraqueza + "</td>" +
                         "<td>" + historiaOrigem + "</td>" +
                         "<td>" + nome + "</td>" +
                         "<td>" + superPoder + "</td>" +
                         "<td>" + (superHeroi.bemOuMal ? superHeroi.bemOuMal.nome : "") + "</td>" +
                         "<td>" + (superHeroi.equipe ? superHeroi.equipe.nome : "") + "</td>" +

                         "<td> <button type='button' class='btn btn-danger' onclick='excluirProduto(" + superHeroi.id 
                         + ")'>Excluir</button> </td>" +

                         "</tr>";

            tableBody.append(markup);            
        });
    })
    .catch(function (error) {
        // handle error
        console.log(error);
    });

} //fim function

function excluirsuperHeroi(idsuperHeroi) {
    
    axios.delete("http://localhost:8080/produto/" + idsuperHeroi)
    .then()
    .catch();
}
function getAllBemOuMal() {
    
}