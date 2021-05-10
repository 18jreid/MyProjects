let index = 0;
let timer = setInterval(onTick, 150);

function onTick() {
    let divs = document.querySelectorAll(".color");
    divs[index].classList.add('fade');
    index++;

    if (index === divs.length) {
        clearInterval(timer);
        timer = null;
    }
}

function onHoverEnter() {
    let element = event.target;

    element.classList.add('hover');
}

function onHoverExit() {
    let element = event.target;

    element.classList.remove('hover');
}

function linkHoverEnter() {
    let element = event.target;

    element.classList.add('linkHover');
}

function linkHoverExit() {
    let element = event.target;

    element.classList.remove('linkHover');
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
            alert("The Hexa-Decimal is:\n\n" + hexColor);
        })
}