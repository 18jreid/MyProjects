function onHoverEnter() {
    let element = event.target;

    element.classList.add('hover');
}

function onHoverExit() {
    let element = event.target;

    element.classList.remove('hover');
}

function copy() {
    let element = event.target;
    let style = getComputedStyle(element);

    let color = style['backgroundColor'].replaceAll(" ", "");
    let url = `http://www.thecolorapi.com/id?rgb=${color}`;

    fetch(url)
        .then(response => response.json())
        .then(json => {
            let hexColor = json['hex']['value'];
            alert(hexColor);
        })
}