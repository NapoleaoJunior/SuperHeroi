// function getAllProdutos() {
//     axios.get('http://localhost:8080/superHeroi')
//     .then(function (response) {
//         console.log(response.data);
//         var jsonData = response.data;
//         var tableBody = $("table tbody");
//         jsonData.forEach(superHeroi => {
//             // Atribuindo os valores adequados aos campos
//             var markup = "<tr>" +
//                          "<td>" + superHeroi.id + "</td>" +
//                          "<td>" + superHeroi.apelido + "</td>" +
//                          "<td> R$ " + superHeroi.primeiraApricao + "</td>" +
//                          "<td>" + superHeroi.apelido + "</td>" +
//                          "<td>" + superHeroi.fraqueza + "</td>" +
//                          "<td>" + superHeroi.historiaOrigem + "</td>" +
//                          "<td>" + superHeroi.nome + "</td>" +
//                          "<td>" + superHeroi.superPoder + "</td>" +
//                          "<td>" + (superHeroi.equipe ? superHeroi.equipe.nome : "") + "</td>" +
//                          "<td> <button type='button' class='btn btn-danger' onclick='excluirProduto(" + superHeroi.id 
//                          + ")'>Excluir</button> </td>" +
//                          "</tr>";
//             tableBody.append(markup);
//         });
//     })
//     .catch(function (error) {
//         console.log(error);
//     });
// }

// function excluirsuperHeroi(idsuperHeroi) {   
//     axios.delete("http://localhost:8080/superHeroi/" + idsuperHeroi)
//     .then()
//     .catch();
// }

// Função para obter todos os heróis e exibi-los na tabela
function getAllProdutos() {
    axios.get('http://localhost:8080/superHeroi')
    .then(function (response) {
        var jsonData = response.data;
        var tableBody = $("#herois-list"); // Seleciona o corpo da tabela
        tableBody.empty(); // Limpa qualquer conteúdo anterior da tabela (caso haja)

        jsonData.forEach(superHeroi => {
            // Formata a data de primeira aparição se houver
            var primeiraAparicao = superHeroi.primeiraApricao ? new Date(superHeroi.primeiraApricao).toLocaleDateString() : "Desconhecida";

            // Cria a linha da tabela para cada super-herói
            var markup = "<tr>" +
                         "<td>" + superHeroi.id + "</td>" +
                         "<td>" + superHeroi.apelido + "</td>" +
                         "<td>" + superHeroi.fraqueza + "</td>" +
                         "<td>" + superHeroi.historiaOrigem + "</td>" +
                         "<td>" + superHeroi.nome + "</td>" +
                         "<td>" + primeiraAparicao + "</td>" +
                         "<td>" + superHeroi.superPoder + "</td>" +
                         "<td>" + (superHeroi.equipe ? superHeroi.equipe.nome : "Sem equipe") + "</td>" +
                         "<td><button type='button' class='btn btn-danger' onclick='excluirsuperHeroi(" + superHeroi.id + ")'>Excluir</button></td>" +
                         "</tr>";
            tableBody.append(markup); // Adiciona a linha à tabela
        });
    })
    .catch(function (error) {
        console.log(error);
    });
}

// Função para excluir um super-herói
function excluirsuperHeroi(idsuperHeroi) {
    if (confirm("Você tem certeza que deseja excluir este super-herói?")) {
        axios.delete("http://localhost:8080/superHeroi/" + idsuperHeroi)
        .then(function(response) {
            alert("Super-herói excluído com sucesso!");
            getAllProdutos(); // Recarrega a lista de heróis após a exclusão
        })
        .catch(function(error) {
            alert("Erro ao excluir o super-herói!");
            console.log(error);
        });
    }
}

// Chama a função para carregar a lista de heróis assim que a página for carregada
$(document).ready(function() {
    getAllProdutos(); // Carrega a lista de heróis ao carregar a página
});
