
// Écoutez le changement de l'élément input de type fichier
document.getElementById('pdfFile').addEventListener('change', function (event) {
let file = event.target.files[0];

if (file && file.type === 'application/pdf') {
    // Le fichier est un PDF, affichez-le
    let pdfContainer = document.getElementById('pdfContainer');
    pdfContainer.innerHTML = ''; // Effacez le contenu précédent

    // Créez un élément <embed> pour prévisualiser le PDF
    let embedElement = document.createElement("embed");
    embedElement.setAttribute("src", URL.createObjectURL(file));
    embedElement.setAttribute("width", "100%");
    embedElement.setAttribute("height", "400");
    embedElement.setAttribute("type", "application/pdf");

    // Ajoutez l'élément <embed> au conteneur
    pdfContainer.appendChild(embedElement);
} else {
    alert("Veuillez sélectionner un fichier PDF.");
}
});
