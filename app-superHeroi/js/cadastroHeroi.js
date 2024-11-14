$( "button.btn-secondary" ).on( "click", function( event ) {
    document.location = '../index.html';
});
function carregarSelect() {
    axios.get('http://localhost:8080/categoria')
    .then(function (response) {
        var jsonData = response.data;
        var select = $("#categoria");
        jsonData.forEach(bemOuMal => {
            optionText = bemOuMal.nome;
            optionValue = bemOuMal.id;
            let optionHTML = `
            <option value="${optionValue}">
                ${optionText} 
            </option>`;
            select.append(optionHTML);
        });
    })
    .catch(function (error) {
        // handle error
        console.log(error);
    });
    axios.get('http://localhost:8080/equipe')
    .then(function (response) {
        var jsonData = response.data;
        var select = $("#equipe");
        jsonData.forEach(equipe => {
            optionText = equipe.nome;
            optionValue = equipe.id;
            let optionHTML = `
            <option value="${optionValue}"> 
                ${optionText} 
            </option>`;
            select.append(optionHTML);
        });
    })
    .catch(function (error) {
        // handle error
        console.log(error);
    });
}
function cadastrarSuperHeroi() {
    var superHeroi = $("#superHeroi").val();
    var primeiraAparicao = $("#primeiraAparicao").val();
    var apelido = $("#apelido").val();
    var fraqueza = $("#fraqueza").val();
    var historiaOrigem = $("#historiaOrigem").val();
    var nome = $("#nome").val();
    var superPoder = $("#superPoder").val();
    
    axios.post('http://localhost:8080/produto',
    {
        "superHeroi" : superHeroi,
        "primeiraAparicao" : primeiraAparicao,
        "apelido" : apelido,
        "fraqueza" : fraqueza,
        "historiaOrigem" : historiaOrigem,
        "nome" : nome,
        "superPoder" : superPoder,

        "bemOuMal" : {
            "id" : bemOuMal
        },
        "equipe" : {
            "id" : equipe
        }
    })
    .then(function (response) {
        alert('Informação dos herois cadastrado com sucesso!');
        document.location = "listaHeroi.html";
    })
    .catch(function (error) {
        // handle error
        console.log(error);
    });
}
carregarSelect();