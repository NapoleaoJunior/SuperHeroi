 /*
 async function initMap() {
 // The location of brasilia
 const position = { lat: -15.812743484997265, lng: -47.89300692050525,  };
 // Request needed libraries.
 //@ts-ignore
 const { Map } = await google.maps.importLibrary("maps");
 const { AdvancedMarkerElement } = await google.maps.importLibrary("marker");

 // The map, centered at Loja
 map = new Map(document.getElementById("map"), {
     zoom: 4,
     center: position,
     mapId: "DEMO_MAP_ID",
 });

 // The marker, positioned at brasilia
 const marker = new AdvancedMarkerElement({
     map: map,
     position: position,
     title: "Loja 1 de Brasília",
 });
 }
*/

 // Initialize and add the map
 let map;
async function initMap(params) {
    $("#spinLoading").hide();
    $("#map").show();
    $("#titlemapasQG").show();

    map = L.map('map').setView([-15.812743484997265, -47.89300692050525], 13);

    L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
        maxZoom: 19,
        attribution: 
        '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
    }).addTo(map);

    axios.get("http://localhost:8080/loja")
    .then(response => {
        var jsonData = response.data;
        jsonData.forEach(mapasQG => {
            var marker = L.marker([mapasQG.latitude, mapasQG.longitude]).addTo(map);
            marker.bindPopup("<b>" + mapasQG.nome + "</b><br>" + mapasQG.endereco);
        });
    })
    .catch(error => {
        console.log(error);
    });    
}

$("#map").hide();
$("#titlemapasQG").hide();
setTimeout(initMap, 2000);