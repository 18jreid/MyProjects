// Name Animation
const myName = document.querySelector(".Name");
const strMyName = myName.textContent;
const splitName = strMyName.split("");
myName.textContent = "";

for (let i = 0; i < splitName.length; i++) {
    if (splitName[i] === " ") {
        myName.innerHTML += "<span>&nbsp;</span>";
    }
    else {
        myName.innerHTML += "<span onmouseenter='hoverEnter()' onmouseleave='hoverExit()' class='Name'>" + splitName[i] + "</span>";
    }
}

let char = 0;
let timer = setInterval(onTickName, 15);

function onTickName() {
    const span = myName.querySelectorAll('span')[char];
    span.classList.add('fade');
    char++;
    if (char === splitName.length) {
        complete();
        contactAnimation();
    }
}

function complete() {
    clearInterval(timer);
    timer = null;
}
// End of Name Animation

const links = document.querySelectorAll('.Tool-Bar-Link');

const portfolio = links[0];
const portfolioStr = portfolio.textContent;
const splitPortfolio = portfolioStr.split("");
portfolio.textContent = "";

// Link Animations
const contact = links[2];
const contactStr = contact.textContent;
const splitContact = contactStr.split("");
contact.textContent = "";

function contactAnimation() {
    for (let i = 0; i < splitContact.length; i++) {
        if (splitProjects[i] === " ") {
            contact.innerHTML += "<portfolio>&nbsp;</portfolio>";
        }
        else {
            contact.innerHTML += "<portfolio>" + splitContact[i] + "</portfolio>";
        }
    }

    let char = 0;
    let timer1 = setInterval(function (){
        const span = contact.querySelectorAll('portfolio')[char];
        span.classList.add('fade');
        char++;
        if (char === splitContact.length) {
            clearInterval(timer1);
            timer1 = null;
            projectsAnimation();
        }
    }, 20);
}

const projects = links[1];
const projectsStr = projects.textContent;
const splitProjects = projectsStr.split("");
projects.textContent = "";

function projectsAnimation() {
    for (let i = 0; i < splitProjects.length; i++) {
        if (splitProjects[i] === " ") {
            projects.innerHTML += "<portfolio>&nbsp;</portfolio>";
        }
        else {
            projects.innerHTML += "<portfolio>" + splitProjects[i] + "</portfolio>";
        }
    }

    char = 0;
    timer = setInterval(function (){
        const span = projects.querySelectorAll('portfolio')[char];
        span.classList.add('fade');
        char++;
        if (char === splitProjects.length) {
            complete();
            portFolioAnimation();
        }
    }, 20);
}

function portFolioAnimation() {
    for (let i = 0; i < splitPortfolio.length; i++) {
        if (splitPortfolio[i] === " ") {
            portfolio.innerHTML += "<portfolio>&nbsp;</portfolio>";
        }
        else {
            portfolio.innerHTML += "<portfolio>" + splitPortfolio[i] + "</portfolio>";
        }
    }

    char = 0;
    timer = setInterval(function (){
        const span = portfolio.querySelectorAll('portfolio')[char];
        span.classList.add('fade');
        char++;
        if (char === splitPortfolio.length) {
            complete();
            if (document.querySelector('.Profile-Pic1')) {
                profilePicAnimation()
            }
        }
    }, 20);
}

function profilePicAnimation() {
    const img1 = document.querySelector('.Profile-Pic1');
    const img2 = document.querySelector('.Profile-Pic2');
    const img3 = document.querySelector('.Profile-Pic3');

    img1.classList.add('fade');
    img2.classList.add('fade');
    img3.classList.add('fade');

    const aboutDiv = document.querySelector('.About-Section');
    aboutDiv.classList.add('fade')
}

function hoverEnter() {
    let myElement = event.target;
    myElement.classList.add('hover');
}

function hoverExit() {
    let myElement = event.target;
    myElement.classList.remove('hover')
}
